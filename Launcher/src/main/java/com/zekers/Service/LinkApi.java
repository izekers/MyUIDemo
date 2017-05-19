package com.zekers.Service;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Zoker on 2017/3/29.
 */

public interface LinkApi {
    @GET("/")
    Call<String> getItem();
}
