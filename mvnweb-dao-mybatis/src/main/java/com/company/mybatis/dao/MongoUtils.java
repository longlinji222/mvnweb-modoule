package com.company.mybatis.dao;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class MongoUtils {
	/**
	 * mongodb数据库ip地址
	 */
	public final static String MONGO_IP_PATH = "127.0.0.1";
	/**
	 * 端口号
	 */
	public final static Integer MONGO_PORT = 27017;
	/**
	 * 数据库名称
	 */
	public final static String MONGO_NAME = "config";
	/**
	 * 集合名称
	 */
	public final static String COLLECTION_NAME = "order";
	
	
	/**
	 * @MethodDesc 获取collection
	 * @Return MongoCollection<Document>
	 * @Author Tcc
	 * @Date 2018年1月22日下午4:40:21
	 */
	public static MongoCollection<Document> getCollection(){
		MongoClient mongoClient = new MongoClient(MONGO_IP_PATH ,MONGO_PORT);
		System.out.println(mongoClient);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(MONGO_NAME);  
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);
        System.out.println("Connect to mongodb database successfully");
        return collection;
	}

}
