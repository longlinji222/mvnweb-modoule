package com.company.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import com.mongodb.client.MongoCollection;

public class AddTestMongo {
	public static void main( String args[] ){
	      try{   
	    	  //获取mongodb的collection
	    	  MongoCollection<Document> collection = MongoUtils.getCollection();
	         /** 
	         * 1. 创建文档 org.bson.Document 参数为key-value的格式 
	         * 2. 创建文档集合List<Document> 
	         * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document) 
	         * */
	         Document document = new Document("title", "Mongo").  
	         append("description", "dae").  
	         append("likes", 10).  
	         append("by", "Ff10");  
	         List<Document> documents = new ArrayList<Document>();  
	         documents.add(document);  
	         collection.insertMany(documents);  
	         System.out.println("文档插入成功");  
	      }catch(Exception e){
	    	  e.printStackTrace();
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
	   }

}
