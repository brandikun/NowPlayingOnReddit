package com.brandonhimes.nowplayingonreddit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {

    @SerializedName("data")
    SearchResultData searchResultDataList;

    public SearchResult(SearchResultData searchResultDataList) {
        this.searchResultDataList = searchResultDataList;
    }

    public SearchResultData getSearchResultDataList() {
        return searchResultDataList;
    }

    public void setSearchResultDataList(SearchResultData searchResultDataList) {
        this.searchResultDataList = searchResultDataList;
    }
}
