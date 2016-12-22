package org.project.module.ticket;

import android.util.Log;

import org.project.entity.TicketEntity;
import org.project.net.ticket.TicketApi;
import org.project.net.config.RetrofitConfig;
import org.project.net.ticket.TicketResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wiesen on 2016/11/7.
 */

public class TicketManager {
    private static final String TAG = "ticket_manager";

    private static final String APP_KEY="07f611d1ee1204c39b7677b616df9d4a";
    public static void requestTicketSource(){
        RetrofitConfig.setBaseUrl("http://www.baidu.com");
        TicketApi ticketApi = RetrofitConfig.getRetrofit().create(TicketApi.class);
        Call<TicketResponse<List<TicketEntity>>> cn = ticketApi.getTicketSource(APP_KEY, "CN");
        cn.enqueue(new Callback<TicketResponse<List<TicketEntity>>>() {
            @Override
            public void onResponse(Call<TicketResponse<List<TicketEntity>>> call, Response<TicketResponse<List<TicketEntity>>> response) {
                Log.i(TAG,response.body().toString()+"\t size:"+response.body().result.size());
            }

            @Override
            public void onFailure(Call<TicketResponse<List<TicketEntity>>> call, Throwable t) {
                Log.i(TAG,t.toString());
            }
        });
    }
}
