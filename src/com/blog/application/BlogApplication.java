package com.blog.application;



import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

import com.blog.resource.BlogServiceImpl;


public class BlogApplication extends Application {
	 @Override
	    public Set<Class<?>> getClasses() {
	        Set<Class<?>> classes = new HashSet<Class<?>>();
	        classes.add(BlogServiceImpl.class);
	        return classes;
	    } 
	 @Override
	  public Set<Object> getSingletons() {
	    Set<Object> s = new HashSet<Object>();
	    
	    // Register the Jackson provider for JSON
	    
	    // Make (de)serializer use a subset of JAXB and (afterwards) Jackson annotations
	    // See http://wiki.fasterxml.com/JacksonJAXBAnnotations for more information
	    ObjectMapper mapper = new ObjectMapper();
	    AnnotationIntrospector primary = new JaxbAnnotationIntrospector();
	    AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
	    AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary, secondary);
	    mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
	    mapper.getSerializationConfig().setAnnotationIntrospector(pair);
	    
	    // Set up the provider
	    JacksonJaxbJsonProvider jaxbProvider = new JacksonJaxbJsonProvider();
	    jaxbProvider.setMapper(mapper);
	    
	    s.add(jaxbProvider);
	    return s;
	  }
	    

}
