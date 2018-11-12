package com.brandonhimes.nowplayingonreddit;

import com.google.gson.annotations.SerializedName;

public class ResultPayload {
    @SerializedName("data")
    private PayloadData searchData;

    public ResultPayload(PayloadData searchData) {
        this.searchData = searchData;
    }

    public PayloadData getSearchData() {
        return searchData;
    }

    public void setSearchData(PayloadData searchData) {
        this.searchData = searchData;
    }
}
