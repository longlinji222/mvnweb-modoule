package com.company.mybatis.dao;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class SearchTestMongodb {
	public static void main( String args[] ){
	      try{   
	    	//获取mongodb的collection
	    	  MongoCollection<Document> collection = MongoUtils.getCollection();
	         /** 
	         * 1. 获取迭代器FindIterable<Document> 
	         * 2. 获取游标MongoCursor<Document> 
	         * 3. 通过游标遍历检索出的文档集合 
	         * */  
	    	  Document doc= new Document();
	    	  doc.append("provice", 1);//1表示升序、-1表示降序
	    	  doc.append("city", 1);
	    	  
	         FindIterable<Document> findIterable = collection.find().sort(new BasicDBObject("provice",1));  
	         MongoCursor<Document> mongoCursor = findIterable.iterator();  
	         while(mongoCursor.hasNext()){  
	            System.out.println(mongoCursor.next());  
	         }  
	      
	      }catch(Exception e){
	    	  e.printStackTrace();
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
	   }

}
