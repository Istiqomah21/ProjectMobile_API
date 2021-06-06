package com.example.simplemovieapps.model;

import com.example.simplemovieapps.model.QuoteResultItem;
import com.google.gson.annotations.SerializedName;

public class QuoteResponse {

	@SerializedName("quote")
	private QuoteResultItem quote;

	@SerializedName("qotd_date")
	private String qotdDate;

	public void setQuote(QuoteResultItem quote){
		this.quote = quote;
	}

	public QuoteResultItem getQuote(){
		return quote;
	}

	public void setQotdDate(String qotdDate){
		this.qotdDate = qotdDate;
	}

	public String getQotdDate(){
		return qotdDate;
	}

	@Override
	public String toString(){
		return
				"Response{" +
						"quote = '" + quote + '\'' +
						",qotd_date = '" + qotdDate + '\'' +
						"}";
	}
}