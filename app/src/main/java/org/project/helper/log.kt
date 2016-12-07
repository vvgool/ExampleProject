package org.project.helper

import android.util.Log

/**
 * Created by wiesen on 16-12-6.
 */

inline fun <reified T> T.DebugI(any: Any){
    Log.i(T::class.simpleName,any.toString())
}

inline fun <reified T> T.DebugE(any: Any){
    Log.e(T::class.simpleName,any.toString())
}

inline fun <reified T> T.DebugV(any: Any){
    Log.v(T::class.simpleName,any.toString())
}

inline fun <reified T> T.DebugD(any: Any){
    Log.d(T::class.simpleName,any.toString())
}

inline fun <reified T> T.DebugW(any: Any){
    Log.w(T::class.simpleName,any.toString())
}