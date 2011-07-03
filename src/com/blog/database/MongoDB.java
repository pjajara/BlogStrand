package com.blog.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import org.apache.wink.json4j.JSONObject;


import com.blog.entity.Blog;
import com.blog.entity.BlogEntry;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class MongoDB {
	// TODO need slf4j log setup

	// mongodb setup - each collection should be thought of as a database
	public static final String sCollection = "mongo.collection";
	public static final String sDB = "mongo.db";
	public static final String sHost = "mongo.host";

	private DBCollection collection;
	private Properties props;

	public MongoDB() {
	}

	public MongoDB(Properties config) {
		init(config);
	}

	/**
	 * connection setup
	 * 
	 * @param props
	 */
	public void init(Properties props) {
		// log.debug("---> ExampleClient.init()");

		this.props = props;
	}

	/**
	 * 
	 */
	public void release() {
		collection = null;
	}

	

	/**
	 * insert data into the collection - note this does not enforce unique
	 * records
	 * 
	 * @param p
	 */
	public void addBlog(Blog p) {
		try {
			DBCollection col = connect();
			DBObject dob = convertToMongo(p, col);
			System.out.println("insertin values");
			
			DBCursor c=col.find();
			System.out.println("size: "+c.size());
			WriteResult w=col.insert(dob);
			
			
			while(c.hasNext()){
				System.out.println("values: "+c.next()+"collection name"+col);
			}
			//System.out.println("result"+w.toString());
			
			System.out.println("insertin success ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("\n\n\n\n\n\nexception ocuured \n\n\n\n\n\n\n");
			e.printStackTrace();
		}
	}

	/**
	 * delete an object - the email is assumed to be the natural unique key
	 * 
	 * @param email
	 */
	public String getBlog(int id) {
		//Blog b=new Blog();
		String s=null;
		int iIndex = 0;
		int endIndex = 0;
		//String str_blg="";
		String str1="";
		String final_str = "";
		
		try {
			System.out.println("cntl 2");
			DBCollection col = connect();
			BasicDBObject dob = new BasicDBObject();
			dob.append("blogid", id);
			DBCursor c=col.find(dob);
			 s=(c.next()).toString();
			/*JSONObject object =(JSONObject) JSON.parse(value);
			b.setBlogId((Integer) object.get("id"));
			b.setDesc((String) object.get("desc"));
			b.setSubject((String) object.get("subject"));
			b.setTimeStamp((String) object.get("timeStamp"));
			b.setUserId((String) object.get("userId"));
			System.out.println("cntl 3");*/
			
			 iIndex = s.lastIndexOf("blogid");
				endIndex = s.indexOf(",", iIndex);
				str1 = s.substring(iIndex, endIndex);
				str1 = str1.replace('"', ' ');
				final_str = final_str + str1 + "\n";
				//System.out.println(str1);
				
				iIndex = s.indexOf("subject");
				endIndex = s.indexOf(",", iIndex);
				str1 = s.substring(iIndex, endIndex);
				str1 = str1.replace('"', ' ');
				final_str = final_str + str1 + "\n";
				//System.out.println(str1);
				
				iIndex = s.indexOf("description");
				endIndex = s.indexOf(",", iIndex);
				str1 = s.substring(iIndex, endIndex);
				str1 = str1.replace('"', ' ');
				final_str = final_str + str1 + "\n";
				//System.out.println(str1);
				
				iIndex = s.indexOf("userid");
				endIndex = s.indexOf(",", iIndex);
				str1 = s.substring(iIndex, endIndex);
				str1 = str1.replace('"', ' ');
				final_str = final_str + str1 + "\n";
				//System.out.println(str1);
				
				iIndex = s.indexOf("timestamp");
				endIndex = s.indexOf("}", iIndex);
				str1 = s.substring(iIndex, endIndex);
				str1 = str1.replace('"', ' ');
				final_str = final_str + str1 + "\n";
				//System.out.println(str1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return final_str;
	}
	
@SuppressWarnings("null")
public ArrayList<Blog> getAllBlog() {
    	
    	//MongoDB mc = new MongoDB(conf);
    	DBCollection col = connect();
		DBCursor c=col.find();
		System.out.println("yukti");
/*		String finalString = null;
		while(c.hasNext()){
			finalString= finalString+c.next().toString();
		}
		System.out.println("The data is "+finalString);*/
	ArrayList<Blog> ArrayList = null;
	//ArrayList <Blog> bloglist=new ArrayList;
		int iIndex = 0;
		int endIndex = 0;
		String str_blg="";
		String str1="";
		String final_str = "";
		
		while(c.hasNext()){
			BasicDBObject basicDBObject =(BasicDBObject)com.mongodb.util.JSON.parse(c.next().toString());
			System.out.println("basicDBObject: "+basicDBObject.toString());
			Blog blog = new Blog();
			blog.setBlogid(Integer.parseInt((basicDBObject.getString("blogid"))));
			blog.setSubject(basicDBObject.getString("subject"));
			blog.setDescription(basicDBObject
					.getString("description"));
			blog.setUserid(basicDBObject.getString("userid"));
			ArrayList.add(blog);
			//blogList.add(basicDBObject);
/*str_blg = basicDBObject.toString();
			
			iIndex = str_blg.lastIndexOf("blogid");
			endIndex = str_blg.indexOf(",", iIndex);
			str1 = str_blg.substring(iIndex, endIndex);
			str1 = str1.replace('"', ' ');
			final_str = final_str + str1 + "\n";
			//System.out.println(str1);
			
			iIndex = str_blg.indexOf("subject");
			endIndex = str_blg.indexOf(",", iIndex);
			str1 = str_blg.substring(iIndex, endIndex);
			str1 = str1.replace('"', ' ');
			final_str = final_str + str1 + "\n";
			//System.out.println(str1);
			
			iIndex = str_blg.indexOf("description");
			endIndex = str_blg.indexOf(",", iIndex);
			str1 = str_blg.substring(iIndex, endIndex);
			str1 = str1.replace('"', ' ');
			final_str = final_str + str1 + "\n";
			//System.out.println(str1);
			
			iIndex = str_blg.indexOf("userid");
			endIndex = str_blg.indexOf(",", iIndex);
			str1 = str_blg.substring(iIndex, endIndex);
			str1 = str1.replace('"', ' ');
			final_str = final_str + str1 + "\n";
			//System.out.println(str1);
			
			iIndex = str_blg.indexOf("timestamp");
			endIndex = str_blg.indexOf("}", iIndex);
			str1 = str_blg.substring(iIndex, endIndex);
			str1 = str1.replace('"', ' ');
			final_str = final_str + str1 + "\n";
			//System.out.println(str1);
			
			final_str = final_str + "\n" + "----------------------------------------------------------------------------" + "\n" + "\n";*/
		}

        return ArrayList;
    }
    

	/**
	 * just like a jdbc connect - get me a connection to the data
	 * 
	 * @return
	 */
	private DBCollection connect() {
		try {
			if (collection != null && collection.getName() != null)
				return collection;
		} catch (Exception ex) {
			collection = null;
		}

		try {
			Mongo m = new Mongo(props.getProperty(sHost));
			DB db = m.getDB(props.getProperty(sDB));
			collection = db.getCollection(props.getProperty(sCollection));
			if (collection == null)
				throw new RuntimeException("Missing collection: " + props.getProperty(sCollection));

			return collection;
		} catch (Exception ex) {
			// should never get here unless no directory is available
			throw new RuntimeException("Unable to connect to mongodb on " + props.getProperty(sHost));
		}
	}

	private DBObject convertToMongo(Blog p,DBCollection col) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		String str =  dateFormat.format(date);
		BasicDBObject rtn = new BasicDBObject();
		int id=0;
		//if (p.getBlogId() != 0)
		//rtn.append("id",1);
		
		DBCursor c=col.find();
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int size=c.size();
		if(size==0)
		   {
			   id=1;
			   System.out.println("BLOG"+id);
		   }
		 
	    //Add elements to Arraylist
	    
		//System.out.println("yukti");
		if(size!=0)
		{
	    while(c.hasNext())
	    {
	    	BasicDBObject basicDBObject =(BasicDBObject)com.mongodb.util.JSON.parse(c.next().toString());
			int blogid=basicDBObject.getInt("blogid");
			System.out.println("blogid"+blogid);
			arrayList.add(new Integer(blogid));
		    
	    	
	    }
	    Object obj = Collections.max(arrayList);
	     id=Integer.parseInt(obj.toString());
	     id=id+1;
	    System.out.println("BLOG"+id);
		}
		
		//System.out.println("size: "+c.size());
		//rtn.append("id",(c.size()+1));
		rtn.append("blogid",(id));
		rtn.append("subject",p.getSubject());
		
	//if (p.getDesc() != null)
		rtn.append("description",p.getDescription());
		rtn.append("userid",p.getUserid());
	//if (p.getSubject() != null)
		
	//if (p.getTimeStamp() != null)
		//rtn.append("timestamp",p.getTimestamp());
		rtn.append("timestamp",str);
	//if (p.getUserID() != null)
		
		System.out.println("mongo object \n\n\n\n"+rtn.toString());
		return rtn;
	
	}
	public String getUsrId(int blgid)
	{   
		DBCollection col = connect();
		DBCursor c1=col.find();
	    String userid="";
		//System.out.println("yukti");
		
		
		while(c1.hasNext())
			
	{
			BasicDBObject basicDBObject =(BasicDBObject)com.mongodb.util.JSON.parse(c1.next().toString());
			int bid=basicDBObject.getInt("blogid");
			
			if(bid==blgid)
			{
				 userid=basicDBObject.getString("userid");
				System.out.println("userid"+userid);
			}
			//System.out.println("c"+c.next());
		}
		return userid;
	}
	
	private DBObject convertToMongo1(BlogEntry p,DBCollection col,int blogid,String uid) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		String str =  dateFormat.format(date);
		BasicDBObject rtn = new BasicDBObject();
		int blogentryid=0;
		
		//BasicDBObject doc = new BasicDBObject();
	    //DBCursor c1=col.find();
	
		//if (p.getBlogEntryId() != 0)
		//rtn.append("id",1);
		
		DBCursor c=col.find();
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int size=c.size();
		if(size==0)
		   {
			blogentryid=1;
			   System.out.println("BLOG"+blogentryid);
		   }
		 
	    //Add elements to Arraylist
	    
		//System.out.println("yukti");
		if(size!=0)
		{
	    while(c.hasNext())
	    {
	    	BasicDBObject basicDBObject =(BasicDBObject)com.mongodb.util.JSON.parse(c.next().toString());
			 blogentryid=basicDBObject.getInt("blogentryid");
			System.out.println("blogid"+blogentryid);
			arrayList.add(new Integer(blogentryid));
		    
	    	
	    }
	    Object obj = Collections.max(arrayList);
	    blogentryid=Integer.parseInt(obj.toString());
	    blogentryid=blogentryid+1;
	    System.out.println("BLOG"+blogentryid);
		}
		//System.out.println("size: "+c.size());
		
		rtn.append("blogentryid",blogentryid);
		
	//if (p.getDesc() != null)
		rtn.append("content",p.getContent());
		rtn.append("userid",uid);
	//if (p.getSubject() != null)
		//rtn.append("timestamp",p.getTimestamp());
		rtn.append("timestamp",str);
		
		//rtn.append("userId",userid);
		rtn.append("blogid",blogid);
	//if (p.getTimeStamp() != null)
		//rtn.append("timeStamp",p.getTimeStamp());
	//if (p.getUserID() != null)
		//rtn.append("userId",p.getUserId());
		System.out.println("mongo object \n\n\n\n"+rtn.toString());
		return rtn;
	
	}
	
	public void deleteBlog(int blogId,String userId)
	{
		DBCollection col = connect();
		 BasicDBObject dob = new BasicDBObject();
		 dob.append("blogid", blogId);
			dob.append("userid", userId);
			 BasicDBObject dob1=dob; 
			col.remove(dob1);
		
	}
	
	public void addBlogEntry(int blogid,BlogEntry blogEntry,String uid)
	{
		String val=null;
		DBCollection col = connect();
		//BasicDBObject field = new BasicDBObject();
		BasicDBObject doc = new BasicDBObject();
		//field.put("userId", "yukti");
//		//DBCursor cursor=col.find(doc,field);
//		
//		while(cursor.hasNext())
//		{
//			BasicDBObject obj=(BasicDBObject)cursor.next();
//			 val=(String) obj.get("userId");
//			System.out.println("val"+val);
//		}
		//if(val!=null)
		//{
			DBObject dob=convertToMongo1(blogEntry,col,blogid,uid);
			col.insert(dob);
			
		//}
		
	}
	public String getBlogEntry(int bid )
	{ 	
		int iIndex = 0;
		int endIndex = 0;
		String str_blg="";
		String str1="";
		String final_str = "";
		BasicDBObject query = new BasicDBObject();
		DBCollection col = connect();
		
		query.put("blogid", bid);
		DBCursor resultsCursor = col.find(query);
		while(resultsCursor.hasNext()){
			BasicDBObject basicDBObject =(BasicDBObject)com.mongodb.util.JSON.parse(resultsCursor.next().toString());
			System.out.println("basicDBObject: "+basicDBObject.toString());
			//str_blg = str_blg + basicDBObject.toString() + "\n";
			str_blg = basicDBObject.toString();
			//System.out.println("str_blg: "+str_blg);
			iIndex = str_blg.indexOf("blogentryid");
			endIndex = str_blg.indexOf(",", iIndex);
			str1 = str_blg.substring(iIndex, endIndex);
			str1 = str1.replace('"', ' ');
			final_str = final_str + str1 + "\n";
			//System.out.println(str1);
			
			iIndex = str_blg.indexOf("content");
			endIndex = str_blg.indexOf(",", iIndex);
			str1 = str_blg.substring(iIndex, endIndex);
			str1 = str1.replace('"', ' ');
			final_str = final_str + str1 + "\n";
			//System.out.println(str1);
			
			iIndex = str_blg.indexOf("userid");
			endIndex = str_blg.indexOf(",", iIndex);
			str1 = str_blg.substring(iIndex, endIndex);
			str1 = str1.replace('"', ' ');
			final_str = final_str + str1 + "\n";
			//System.out.println(str1);
			
			iIndex = str_blg.indexOf("timestamp");
			endIndex = str_blg.indexOf(",", iIndex);
			str1 = str_blg.substring(iIndex, endIndex);
			str1 = str1.replace('"', ' ');
			final_str = final_str + str1 + "\n";
			//System.out.println(str1);
			
			iIndex = str_blg.lastIndexOf("blogid");
			endIndex = str_blg.indexOf("}", iIndex);
			str1 = str_blg.substring(iIndex, endIndex);
			str1 = str1.replace('"', ' ');
			final_str = final_str + str1 + "\n";
			//System.out.println(str1);
			
			final_str = final_str + "\n" + "----------------------------------------------------------------------------" + "\n" + "\n";
			
		}

		return final_str;
		
		
	}



	public void deleteBlogEntry(int blogId, int blogEntryId, String userId) 
	{
		DBCollection col = connect();
		BasicDBObject dob = new BasicDBObject();
		dob.append("blogid", blogId);
		dob.append("blogentryid",blogEntryId);
		dob.append("userid", userId);
		BasicDBObject dob1=dob; 
		col.remove(dob1);
		
	}


}
