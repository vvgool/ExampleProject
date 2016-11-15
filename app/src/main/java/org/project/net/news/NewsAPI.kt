package org.project.net.news

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by wiesen on 2016/11/15.
 */
interface NewsApi {

    @GET("http://v.juhe.cn/toutiao/index") fun getNews(@Query("type")type:String,@Query("key")key:String): Call<NewsResponse>

}