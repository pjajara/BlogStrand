package com.blog.entity;





public class Blog {
	
	int blogid;
	
	String subject;
	String description;
	
	String userid;
	
	String timestamp;
	
	public int getBlogid() {
		return blogid;
	}

	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	

	@Override
	public String toString() {
		return "Blog [blogid=" + blogid + ", subject=" + subject + ", description="
				+ description + ", userid=" + userid + ", timestamp=" + timestamp
				+ "]";
	}
	
	/*public Blog convertBlog(String s) {
		Blog blog=new Blog();
		String [] values = s.split(",");
		String[] varray;
		varray=values[0].split("=");
		if(varray.length > 1){
			blog.setBlogId(Integer.parseInt(varray[1]));
			
			
		}
		
		return null;
		
		
		
	}*/
	

}
