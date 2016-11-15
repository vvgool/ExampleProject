package org.project.net.news

import java.io.Serializable
import java.util.*

/**
 * Created by wiesen on 2016/11/15.
 */
data class NewsResponse(var reason:String,var result: NewsEntity,var error_code:Int)

data class NewsEntity(var stat:String,var data: ArrayList<NewsMessage>) : Serializable

data class NewsMessage(var title:String,var date:String,var author_name:String,
                       var thumbnail_pic_s:String,var thumbnail_pic_s02:String?,
                       var thumbnail_pic_s03:String?,var url:String,
                       var uniquekey:String,var type:String,
                       var realtype:String): Serializable
