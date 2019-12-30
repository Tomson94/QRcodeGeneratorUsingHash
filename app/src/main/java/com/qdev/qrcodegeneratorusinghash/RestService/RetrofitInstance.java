package com.qdev.qrcodegeneratorusinghash.RestService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public   class RetrofitInstance {

    public static Retrofit getRetrofitInsatnce(){
        Retrofit retrofit = null;
        String BASE_URL ="https://api.myjson.com/bins/";
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }

}
