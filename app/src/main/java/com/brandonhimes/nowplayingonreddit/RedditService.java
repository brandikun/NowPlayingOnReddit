package com.brandonhimes.nowplayingonreddit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditService {

    @GET("search.json?")
    Call<ResultPayload> getSearchResults(@Query("q") String searchTerm, @Query("sort") String sort, @Query("type") String type, @Query("t") String timeRange, @Query("limit") String limit);
}
