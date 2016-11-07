package org.project.net.ticket;

import org.project.module.ticket.TicketBean;

import java.io.Serializable;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wiesen on 2016/11/7.
 */

public interface TicketApi {

    @GET("http://v.juhe.cn/boxoffice/rank")
    Call<TicketResponse<List<TicketBean>>> getTicketSource(@Query("key")String key, @Query("area")String area);
}
