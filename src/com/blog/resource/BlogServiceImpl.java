package com.blog.resource;



import java.util.ArrayList;
import java.util.Properties;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.UriInfo;

import org.apache.wink.json4j.JSONObject;
import org.apache.wink.server.utils.LinkBuilders;

import com.blog.database.MongoDB;
import com.blog.entity.Blog;
import com.blog.entity.BlogEntry;


public class BlogServiceImpl implements BlogService {
	
	
		private Properties conf;
		private Properties conf1;
		public BlogServiceImpl() {
		conf = new Properties();
		conf.setProperty(MongoDB.sHost, "localhost");
    	conf.setProperty(MongoDB.sDB, "blogdb");
    	conf.setProperty(MongoDB.sCollection, "blog");
    	conf1 = new Properties();
		conf1.setProperty(MongoDB.sHost, "localhost");
    	conf1.setProperty(MongoDB.sDB, "blogdb");
    	conf1.setProperty(MongoDB.sCollection, "blogEntry");
    	
	}
	
	
	

	@Override
	public void createBlog(Blog blog) {
		
		
		System.out.println("cntl here");
		
		//MongoBlogDB mBlogDB=new MongoBlogDB();
		MongoDB mc = new MongoDB(conf);
		
		
		mc.addBlog(blog);
		
				
	}




	@Override
	public String  getBlog(@PathParam("blogId") int blogid) {
		
		System.out.println("cntl 1");
		
		MongoDB mc = new MongoDB(conf);
		
		
		String b=mc.getBlog(blogid);
		return b;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ArrayList<Blog>  getAllBlog() {
		
		System.out.println("cntl 1");
		MongoDB mc = new MongoDB(conf);
				
		ArrayList str_blg =  mc.getAllBlog();
		return str_blg;
	}
	@Override
	public void deleteBlog(@PathParam("blogid") int blogId,@PathParam("userid") String userId)
	{
		MongoDB mc = new MongoDB(conf);
		mc.deleteBlog(blogId,userId);
	}
	
	@Override
	public void createBlogEntry(@PathParam("blogid")int blogId,BlogEntry blogEntry)
	{
		MongoDB mc1 = new MongoDB(conf1);
		MongoDB mc = new MongoDB(conf);
		String uId = mc.getUsrId(blogId);
		mc1.addBlogEntry(blogId,blogEntry, uId);
		
	}
	/*@Override
	public Blog getBlog(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	@Override
	public String getBlogEntries(@PathParam("blogId") int blogid)
	{
		MongoDB mc = new MongoDB(conf1);
		String str_blg=	mc.getBlogEntry(blogid);
		return str_blg;
	}
	
	public void deleteBlogEntry(@PathParam("blogid") int blogId, @PathParam("blogentryid") int blogEntryId, @PathParam("userid") String userId) {
		
		
		System.out.println("cntl here");
		
		MongoDB mc1 = new MongoDB(conf1);
		
		mc1.deleteBlogEntry(blogId, blogEntryId, userId);

}
}
