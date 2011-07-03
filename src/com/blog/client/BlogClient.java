package com.blog.client;





import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;



import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.JSONObject;

import com.blog.entity.Blog;
import com.blog.resource.BlogServiceImpl;





	public class BlogClient {
	private  String endPoint = "http://localhost:8080/ikat";
	
	public BlogClient(){
		
	}
	
	public BlogClient(String url){
		endPoint = url;
	}

	/**
	 * @param args
	 */
	public  void createBlog(){
		String url = endPoint + "/blog";
		RestClient client = new RestClient();
		Resource resource = client.resource(url);
		JSONObject sendobject = new JSONObject();
		try {
			//sendobject.put("blogId", 1);
			sendobject.put("subject", "My blog.google");
			sendobject.put("description", "This is my google Blogspot");
			sendobject.put("userid", "bloguser2");
			sendobject.put("timestamp","10/11/2011");
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String jsonStringObj = sendobject.toString();
		ClientResponse created = resource.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(jsonStringObj);
		System.out.println("Response" + created.getMessage()+ created.getStatusCode());
	}
	
	public void getBlog(){
		int blogid = 1;
		String url = endPoint + "/blog" + "/"+blogid;
		System.out.println("url "+url);
		RestClient client = new RestClient();
		Resource resource = client.resource(url);
		
		resource = client.resource(url);
		
		 String b = resource.contentType(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).get(String.class);
		
	}
	
	public void getAllBlog()
	{
		String url = endPoint + "/blog" ;
		System.out.println("url "+url);
		RestClient client = new RestClient();
		Resource resource = client.resource(url);
		resource = client.resource(url);
		
		
		String s= resource.contentType(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).get(String.class);
		
	}
	public void deleteBlog()
	{
		 int blogid=1;
		 String userid="sruthi";
		String url = endPoint + "/blog"+ "/"+blogid+"/"+userid;
		System.out.println("url "+url);
		RestClient client = new RestClient();
		Resource resource = client.resource(url);
		
		resource = client.resource(url);
		
	
		ClientResponse created = resource.contentType(MediaType.APPLICATION_JSON).delete();
		System.out.println("successfully deleted"+created.getMessage()+created.getStatusCode());
		
	}
	public  void createBlogEntry(){
		int blogid=2;
		String url = endPoint + "/blog"+"/"+blogid+"/blogentry";
		RestClient client = new RestClient();
		Resource resource = client.resource(url);
		JSONObject sendobject = new JSONObject();
		try {
			//sendobject.put("blogId", 1);
			sendobject.put("content", "chapter1 :myblog");
			//sendobject.put("desc", "Test creating a blog from a client");
			//sendobject.put("userId", "yukti");
			sendobject.put("timestamp","04/21/2011");
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String jsonStringObj = sendobject.toString();
		ClientResponse created = resource.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(jsonStringObj);
		System.out.println("Response" + created.getMessage()+ created.getStatusCode());
		//echoResponse("hello");
	}
	
	public void deleteBlogEntry()
	{
		int blogid = 1;
		int blogentryid = 1;
		String userid = "sruthi";
		String url = endPoint + "/blog"+ "/" +blogid+"/blogentries" + "/" +blogentryid + "/" +userid ;
		System.out.println("url "+url);
		RestClient client = new RestClient();
		Resource resource = client.resource(url);
		
		resource = client.resource(url);
		
	
		ClientResponse created = resource.contentType(MediaType.APPLICATION_JSON).delete();
		System.out.println("successfully deleted"+created.getMessage()+created.getStatusCode());
		
	}
	
	public void getBlogEntry()
	{
		int blogid = 1;
		String url = endPoint + "/blog" +"/"+blogid+"/blogentry" ;
		System.out.println("url "+url);
		RestClient client = new RestClient();
		Resource resource = client.resource(url);
		resource = client.resource(url);
		
		String b = resource.contentType(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).get(String.class);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 //String url = "http://localhost:8080/royalblog/rest/blog";
		 //RestClient client = new RestClient();
		//Resource resource = client.resource(url);
		//String value = resource.contentType(MediaType.APPLICATIO).accept(MediaType.APPLICATION_JSON).get(String.class);
		
		/*Blog b = new Blog();
		
		//b.setSubject("New blog");
		//b.setDesc("Test creating a blog from a client");
		b.setUserID("me");
		b.setTimeStamp("10/11/2011");
		b.setBlogId(1);*/
		
		
		
		
		
		//Blog b=(Blog) resource.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
	//	resource.contentType(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).post(Blog.class, b);
		
		
		/*BlogServiceImpl bImpl=new BlogServiceImpl();
		//bImpl.createBlog(null, null, b);
		System.out.println( " \n BLOG INFORMTION");
		try {
			System.out.println(b.toString());
			//System.out.println("BlogId : "+b.getInt("id"));
			//System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		BlogClient b = new BlogClient("http://localhost:8080/ikat");
		b.createBlog();
		b.getBlog();
		b.getAllBlog();
		b.deleteBlog();
		b.createBlogEntry();
		b.getBlogEntry();
		b.deleteBlogEntry();
		
	}

}
