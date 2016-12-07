package org.project.helper.thread

import org.project.helper.DebugD
import java.io.*
import java.net.InetSocketAddress
import java.net.Socket

/**
 * Created by wiesen on 16-12-6.
 */
class TcpThread(var mIP :String,var mPort:Int) :Thread(){

    companion object{
        val IP = "192.168.31.240"
        val PORT = 9999
        val CONNECT_TIME_OUT = 3000
        val RE_CONNECT_TIME = 3000
    }

    var mSocket:Socket? = null
    var mOut :OutputStream? = null
    var mInput :InputStream? = null
    var mIsRunning:Boolean = true

    @Throws(Exception::class)
    private fun startConnectSocket(){
        mSocket = Socket()
        with(mSocket!!){
            connect(InetSocketAddress(mIP,mPort), CONNECT_TIME_OUT)
            mOut = outputStream
            mInput = inputStream
        }
    }

    private fun socketReConnect(){
        while (mIsRunning) {
            try {
                sleep(RE_CONNECT_TIME.toLong())
                disConnectSocket()
                DebugD("start to reconnect")
                startConnectSocket()
                mSocketStatus?.onReconnected()
                mStatus = SOCKET_STATUS.RECONNECTED
                DebugD("reconnect success")
                break
            }catch (e:Exception){
            }
        }
    }

    @Throws(IOException::class)
    private fun disConnectSocket(){
        DebugD("start to disconnect")
        mOut?.close().run { mOut = null }
        mInput?.close().run { mInput = null }
        mSocket?.close().run { mSocket = null }
        DebugD("socket disconnect success")
    }

    override fun run() {
        super.run()
        try {
            DebugD("start to connect socket")
            startConnectSocket()
            DebugD("connect success")
            mSocketStatus?.onConnected()
            mStatus =SOCKET_STATUS.CONNECTED
        }catch (e:Exception){
            mSocketStatus?.onConnecting()
            mStatus = SOCKET_STATUS.CONNECTING
            socketReConnect()
            mSocketStatus?.onConnected()
            mStatus =SOCKET_STATUS.CONNECTED
        }
        while (mIsRunning){
            try {
                readData()
                DebugD("read a pack message")
            }catch (e:Exception){
                DebugD("message read error")
                if (mIsRunning) {
                    mSocketStatus?.onDisConnected()
                    mSocketStatus?.onConnecting()
                    socketReConnect()
                }
            }
        }
        try {
            mSocketStatus?.onDisConnected()
            mStatus = SOCKET_STATUS.DISCONNECTED
            disConnectSocket()
        }catch (e:Exception){}
    }

    @Throws(IOException::class)
    private fun readData(){
        val content:StringBuffer = StringBuffer()
        var status:Int? = mInput?.read()
        while (status != -1){
            content.append(status?.toChar())
            status = mInput?.read()
        }
        DebugD(content.toString())
        mReadRemoteMessage?.onMessageReaded(content.toString())
    }

    @Throws(IOException::class)
    fun sendMessage(str:String){
        mOut?.write(str.toByteArray())
        mOut?.flush()
    }

    fun stopConnect(){
        mIsRunning = false
    }


    var mReadRemoteMessage :ReadRemoteMessage? = null

    fun setOnRemoteMessageReadedListener(readRemoteMessage: ReadRemoteMessage){
        mReadRemoteMessage = readRemoteMessage
    }

    var mSocketStatus :SocketStatus?  = null
    fun setOnSocketStatusListener(socketStatus: SocketStatus?){
        mSocketStatus = socketStatus
    }


    var mStatus : SOCKET_STATUS = SOCKET_STATUS.NULL
    fun getCurrentSocketStatus(): SOCKET_STATUS = mStatus

    enum class SOCKET_STATUS {
        CONNECTING,
        CONNECTED,
        RECONNECTED,
        DISCONNECTED,
        NULL
    }

    interface ReadRemoteMessage{
        fun onMessageReaded(content:String)
    }

    interface SocketStatus{
        fun onConnecting()
        fun onReconnected()
        fun onConnected()
        fun onDisConnected()
    }
}