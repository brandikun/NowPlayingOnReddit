package com.brandonhimes.nowplayingonreddit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PayloadData {

    @SerializedName("children")
    List<SearchResult> searchResultList;

    public List<SearchResult> getSearchResultList() {
        return searchResultList;
    }

    public void setSearchResultList(List<SearchResult> searchResultList) {
        this.searchResultList = searchResultList;
    }

    public PayloadData(List<SearchResult> searchResultList) {

        this.searchResultList = searchResultList;
    }
}
