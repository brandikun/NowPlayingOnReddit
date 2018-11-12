package com.brandonhimes.nowplayingonreddit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchResultData {

    private String subreddit, title;
    private List<String> links;

    @SerializedName("ups")
    private String upVotes;

    @SerializedName("selftext")
    private String messageText;

    public SearchResultData(String subreddit, String title, String upvotes, String messageText) {
        this.subreddit = subreddit;
        this.title = title;
        this.upVotes = upvotes;
        this.messageText = messageText;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(String upVotes) {
        this.upVotes = upVotes;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLinks() {
        this.links = new ArrayList<>();

        String [] splitMessage = messageText.split(" ");
        for(String word : splitMessage) {
            if(word.toLowerCase().contains("https://open.spotify.com/track/")) {
                String tempWord = word.substring(word.indexOf("https"));
                if(tempWord.contains("\n")) {
                    this.links.add(tempWord.substring(0, tempWord.indexOf("\n")));
                } else {
                    this.links.add(tempWord);
                }
            }
        }
    }

    public List<String> getLinks() {
        return links;
    }
}
