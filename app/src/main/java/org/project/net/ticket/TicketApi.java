package org.project.net.ticket;

import org.project.entity.TicketEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wiesen on 2016/11/7.
 */

public interface TicketApi {

    @GET("http://v.juhe.cn/boxoffice/rank")
    Call<TicketResponse<List<TicketEntity>>> getTicketSource(@Query("key")String key, @Query("area")String area);
}
