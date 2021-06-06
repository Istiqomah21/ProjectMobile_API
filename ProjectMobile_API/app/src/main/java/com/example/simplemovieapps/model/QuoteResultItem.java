package com.example.simplemovieapps.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuoteResultItem {

	public QuoteResultItem(boolean jsonMemberPrivate, int favoritesCount, String author, boolean dialogue, int upvotesCount, String authorPermalink, int id, String body, String url, List<String> tags, int downvotesCount) {
		this.jsonMemberPrivate = jsonMemberPrivate;
		this.favoritesCount = favoritesCount;
		this.author = author;
		this.dialogue = dialogue;
		this.upvotesCount = upvotesCount;
		this.authorPermalink = authorPermalink;
		this.id = id;
		this.body = body;
		this.url = url;
		this.tags = tags;
		this.downvotesCount = downvotesCount;
	}

	@SerializedName("private")
	private boolean jsonMemberPrivate;

	@SerializedName("favorites_count")
	private int favoritesCount;

	@SerializedName("author")
	private String author;

	@SerializedName("dialogue")
	private boolean dialogue;

	@SerializedName("upvotes_count")
	private int upvotesCount;

	@SerializedName("author_permalink")
	private String authorPermalink;

	@SerializedName("id")
	private int id;

	@SerializedName("body")
	private String body;

	@SerializedName("url")
	private String url;

	@SerializedName("tags")
	private List<String> tags;

	@SerializedName("downvotes_count")
	private int downvotesCount;

	public void setJsonMemberPrivate(boolean jsonMemberPrivate){
		this.jsonMemberPrivate = jsonMemberPrivate;
	}

	public boolean isJsonMemberPrivate(){
		return jsonMemberPrivate;
	}

	public void setFavoritesCount(int favoritesCount){
		this.favoritesCount = favoritesCount;
	}

	public int getFavoritesCount(){
		return favoritesCount;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setDialogue(boolean dialogue){
		this.dialogue = dialogue;
	}

	public boolean isDialogue(){
		return dialogue;
	}

	public void setUpvotesCount(int upvotesCount){
		this.upvotesCount = upvotesCount;
	}

	public int getUpvotesCount(){
		return upvotesCount;
	}

	public void setAuthorPermalink(String authorPermalink){
		this.authorPermalink = authorPermalink;
	}

	public String getAuthorPermalink(){
		return authorPermalink;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setTags(List<String> tags){
		this.tags = tags;
	}

	public List<String> getTags(){
		return tags;
	}

	public void setDownvotesCount(int downvotesCount){
		this.downvotesCount = downvotesCount;
	}

	public int getDownvotesCount(){
		return downvotesCount;
	}

	@Override
	public String toString(){
		return
				"Quote{" +
						"private = '" + jsonMemberPrivate + '\'' +
						",favorites_count = '" + favoritesCount + '\'' +
						",author = '" + author + '\'' +
						",dialogue = '" + dialogue + '\'' +
						",upvotes_count = '" + upvotesCount + '\'' +
						",author_permalink = '" + authorPermalink + '\'' +
						",id = '" + id + '\'' +
						",body = '" + body + '\'' +
						",url = '" + url + '\'' +
						",tags = '" + tags + '\'' +
						",downvotes_count = '" + downvotesCount + '\'' +
						"}";
	}
}