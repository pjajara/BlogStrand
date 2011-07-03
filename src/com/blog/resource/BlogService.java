package com.blog.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.wink.json4j.JSONObject;
import org.apache.wink.server.utils.LinkBuilders;

import com.blog.entity.Blog;
import com.blog.entity.BlogEntry;


/**
 * @author mycomputer
 *
 */

@Path("/blog")
public interface BlogService {
	
	
	//for creating Blog
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createBlog(Blog blog);
	
	
	@Path("/{blogId}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getBlog(@PathParam("blogId") int blogid);
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList <Blog> getAllBlog();
	
	@Path("/{blogid}/{userid}")
	@DELETE
	public void deleteBlog(@PathParam("blogid") int blogId,@PathParam("userid") String userId);
	
	//for creating blog entry
	@Path("/{blogid}/blogentry")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createBlogEntry(@PathParam("blogid")int blogId,BlogEntry blogEntry);
	
	@Path("/{blogId}/blogentry")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getBlogEntries(@PathParam("blogid") int blogId);
	
	@Path("/{blogid}/blogentries/{blogentryid}/{userid}")
	@DELETE
	public void deleteBlogEntry(@PathParam("blogid") int blogId, @PathParam("blogentryid") int blogEntryId, @PathParam("userid") String userId);
	
	
}
