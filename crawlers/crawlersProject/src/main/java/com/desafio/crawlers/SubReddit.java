package com.desafio.crawlers;

import java.util.Comparator;

public class SubReddit implements Comparable<SubReddit>{
	
	private String subreddit;
	private Integer upvotes;
	private String title;
	private String url;
	
	public String getSubreddit() {
		return subreddit;
	}
	public void setSubreddit(String subreddit) {
		this.subreddit = subreddit;
	}
	public Integer getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(Integer upvotes) {
		this.upvotes = upvotes;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPermalink() {
		return permalink;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	private String permalink;
	
	public int compareTo(SubReddit compareSubReddits) {
		int upVotes = ((SubReddit) compareSubReddits).getUpvotes();
		return upVotes - this.upvotes;
	}

	public static Comparator<SubReddit> SubRedditsUpVotesComparator
                          = new Comparator<SubReddit>() {
	    public int compare(SubReddit sub1, SubReddit sub2) {
	      Integer subUp1 = sub1.getUpvotes();
	      Integer subUp2 = sub2.getUpvotes();
	      return subUp2.compareTo(subUp1);
	    }

	};

	@Override
	public String toString() {
		return "SubReddit [subreddit=" + subreddit + ", upvotes=" + upvotes + ", title=" + title + ", url=" + url
				+ ", permalink=" + permalink + "]";
	}
	
	public String getURLFormatada() {
		return "\u2195 "+upvotes+"\n["+title.substring(0, title.length()/2)+"...]("+url+")\n\n";
	
	}

	

}
