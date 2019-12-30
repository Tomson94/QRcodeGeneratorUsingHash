package com.qdev.qrcodegeneratorusinghash.RestService;

import com.qdev.qrcodegeneratorusinghash.PogoModel.HashKey;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("sqvwc")
    Call<HashKey>getKey();
}
