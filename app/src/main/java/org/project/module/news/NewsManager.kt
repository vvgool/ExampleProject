package org.project.module.news

import android.util.Log
import android.widget.Toast
import org.project.base.App
import org.project.net.config.CallBackResponse
import org.project.net.config.RetrofitConfig
import org.project.net.news.NewsApi
import org.project.net.news.NewsEntity
import org.project.net.news.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by wiesen on 2016/11/15.
 */
class NewsManager {
    companion object{
        val TAG = "news_api"
        private val APP_KEY = "8c08f0aac1a377c47426ce9eba9effc7"
        val TOP = "top"
        val SH ="shehui"
        val GN = "guonei"
        val GJ = "guoji"
        val YL = "yule"
        val TY = "tiyu"
        val JS = "junshi"
        val KJ = "keji"
        val CJ = "caijing"
        val SS = "shishang"

        fun getNewsByType(type:String,callResponse:CallBackResponse<NewsEntity>){
            RetrofitConfig.setBaseUrl("http://www.baidu.com")
            val newsApi=RetrofitConfig.getRetrofit().create(NewsApi::class.java)
            val news = newsApi.getNews(type, APP_KEY)
            news.enqueue(object : Callback<NewsResponse>{
                override fun onResponse(call: Call<NewsResponse>?, response: Response<NewsResponse>?) {
                    Log.i(TAG, response?.body().toString() + "\t size:" + response?.body()?.result?.data?.size)
                    callResponse.onSuccess(response?.body()?.result)
                }

                override fun onFailure(call: Call<NewsResponse>?, t: Throwable?) {
                    Toast.makeText(App.getInstance(),t?.message,Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}




