package com.blog.entity;

public class BlogEntry {
	
	
	
	int blogentryid;
		
	String content;
		
	String timestamp;

	

	public int getBlogEntryId() {
		return blogentryid;
	}



	public void setBlogEntryId(int blogentryid) {
		this.blogentryid = blogentryid;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}



	@Override
	public String toString() {
		return "BlogEntry [ blogentryid=" + blogentryid
				+ ", content=" + content + ", "
				+ ", timestamp=" + timestamp + "]";
	}
	

}
