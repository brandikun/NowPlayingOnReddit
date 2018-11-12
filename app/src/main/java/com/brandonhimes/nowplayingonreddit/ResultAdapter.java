package com.brandonhimes.nowplayingonreddit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultHolder> {
    private List<SearchResult> mSearchResultList;

    public ResultAdapter(List<SearchResult> searchResultList) {
        mSearchResultList = searchResultList;
    }

    @NonNull
    @Override
    public ResultAdapter.ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View resultItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item, parent, false);

        return new ResultHolder(resultItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int i) {
        SearchResultData postData = mSearchResultList.get(i).getSearchResultDataList();
        List<String> links = postData.getLinks();

        if(links != null && links.size() > 0) {
            holder.mTitleTextView.setText(postData.getTitle());
            holder.mSubredditTextView.setText(String.format("r/%s", postData.getSubreddit().toLowerCase()));
            holder.mUpVotesTextView.setText(String.format("%s upvotes", postData.getUpVotes()));
        }
        if(links != null && links.size() == 1) {
            holder.mLinksTextView.setText(links.get(0));
        } else if(links != null && links.size() > 1) {
            for(String link : links) {
                holder.mLinksTextView.append(link + "\n");
            }
        }
    }

    @Override
    public int getItemCount() {
        return mSearchResultList.size();
    }

    public static class ResultHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView, mLinksTextView, mUpVotesTextView, mSubredditTextView;

        public ResultHolder(@NonNull View view) {
            super(view);

            mTitleTextView = view.findViewById(R.id.title_text_view);
            mLinksTextView = view.findViewById(R.id.links_text_view);
            mUpVotesTextView = view.findViewById(R.id.upvote_text_view);
            mSubredditTextView = view.findViewById(R.id.subreddit_text_view);
        }
    }
}
