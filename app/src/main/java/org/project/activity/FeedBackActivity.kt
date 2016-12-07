package org.project.activity

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.project.R
import org.project.base.BaseActivity
import org.project.helper.thread.TcpThread
import java.util.*

class FeedBackActivity : BaseActivity() {
    val mChatLv: ListView by lazy { findViewById(R.id.lv_chat) as ListView }
    val mSendBt: Button by lazy { findViewById(R.id.bt_send) as Button }
    val mContentEt: EditText by lazy { findViewById(R.id.et_content) as EditText }
    val mSocketThread: TcpThread by lazy { TcpThread(TcpThread.IP, TcpThread.PORT) }

    val mAdapter:FeedAdapter by lazy { FeedAdapter(this) }

    override fun getContentViewId(): Int = R.layout.activity_feed_back

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSocketThread.start()
        mChatLv.adapter = mAdapter
        mSendBt.setOnClickListener {
            val text = mContentEt.text
            if (mSocketThread.getCurrentSocketStatus() == TcpThread.SOCKET_STATUS.CONNECTED && !TextUtils.isEmpty(text)) {
                try {

                    mSocketThread.sendMessage(text.toString())
                    mAdapter.addData(text.toString(),true)
                } catch (e: Exception) {

                }
            }
        }
        mSocketThread.setOnRemoteMessageReadedListener(object :TcpThread.ReadRemoteMessage{
            override fun onMessageReaded(content: String) {
                if (!TextUtils.isEmpty(content)){
                    mAdapter.addData(content,false)
                }
            }

        })

    }

    override fun onResume() {
        super.onResume()

    }


    override fun onDestroy() {
        super.onDestroy()
        mSocketThread.stopConnect()
    }



    class FeedAdapter(val context: Context) : BaseAdapter() {

        val mMessage = ArrayList<String>()
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val viewHolder: ViewHolder
            var view: View? = convertView
            if (view == null) {
                view = View.inflate(context, R.layout.adapter_feed_back, null)
                viewHolder = ViewHolder(view)
                view?.tag=viewHolder
            }else{
                viewHolder = view.tag as ViewHolder
            }
            val msg:String = getItem(position).toString()
            with(msg){
                if (contains("from")){
                    viewHolder.mRoot.setGravity(Gravity.LEFT)
                }else if (contains("to")){
                    viewHolder.mRoot.setGravity(Gravity.RIGHT)
                }
            }

            viewHolder.mMessageTv.text = msg.split(":")[1]
            return view!!
        }

        fun addData(str:String,isSend :Boolean){
            mMessage.add((if (isSend) "to:" else "from:") + str)
            notifyDataSetChanged()
        }

        override fun getItem(position: Int): Any {
            return if (position < mMessage.size) mMessage[position] else throw Exception()
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return mMessage.size
        }

        class ViewHolder(val view:View) {
            val mMessageTv: TextView by lazy { view.findViewById(R.id.tv_message) as TextView }
            val mRoot :LinearLayout by lazy { view.findViewById(R.id.ll_feed_item_root) as LinearLayout }
        }


    }
}
