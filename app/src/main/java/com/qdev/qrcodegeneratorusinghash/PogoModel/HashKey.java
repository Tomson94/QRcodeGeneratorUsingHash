package com.qdev.qrcodegeneratorusinghash.PogoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HashKey {

    @SerializedName("hashkey")
    @Expose
    private String hashkey;

    public String getHashkey() {
        return hashkey;
    }

    public void setHashkey(String hashkey) {
        this.hashkey = hashkey;
    }

}