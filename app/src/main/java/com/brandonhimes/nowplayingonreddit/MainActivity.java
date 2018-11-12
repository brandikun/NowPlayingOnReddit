package com.brandonhimes.nowplayingonreddit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    private static final String BASE_URL = "https://www.reddit.com/";
    private RedditService mRedditService;
    private RecyclerView mResultRecycler;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SearchView searchView = findViewById(R.id.search_view);
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        Button viewAllButton = findViewById(R.id.view_all_button);

        mResultRecycler = findViewById(R.id.result_recycler);
        mLayoutManager = new LinearLayoutManager(this);
        mResultRecycler.setLayoutManager(mLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRedditService = retrofit.create(RedditService.class);

        radioGroup.check(R.id.day_radio_button);

        searchView.setOnSearchClickListener(v -> findViewById(R.id.app_name_text_view).setVisibility(View.GONE));

        searchView.setOnCloseListener(() -> {
            findViewById(R.id.app_name_text_view).setVisibility(View.VISIBLE);
            return false;
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                queryReddit(query, ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString().toLowerCase());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        viewAllButton.setOnClickListener(v -> {
            searchView.clearFocus();
            queryReddit("", ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString().toLowerCase());
        });
    }

    private void queryReddit(String query, String duration) {
        Call<ResultPayload> call = mRedditService.getSearchResults("open.spotify.com/track/ " + query, "top", "message", duration, "100");
        call.enqueue(new Callback<ResultPayload>() {
            @Override
            public void onResponse(Call<ResultPayload> call, Response<ResultPayload> response) {
                if(response.body() != null) {
                    List<SearchResult> results = response.body().getSearchData().getSearchResultList();
                    for(SearchResult result : results)
                        result.getSearchResultDataList().setLinks();

                    results.removeIf(i -> i.getSearchResultDataList().getLinks() == null || i.getSearchResultDataList().getLinks().size() == 0);
                    RecyclerView.Adapter adapter = new ResultAdapter(response.body().getSearchData().getSearchResultList());
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mResultRecycler.getContext(), mLayoutManager.getOrientation());
                    mResultRecycler.addItemDecoration(dividerItemDecoration);
                    mResultRecycler.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ResultPayload> call, Throwable t) {

            }
        });
    }
}
