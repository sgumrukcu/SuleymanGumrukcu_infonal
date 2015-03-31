package com.suleyman.db;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
 
public class DatabaseManager {

	private static final String HOST = "localhost";
	private static final String DB = "SMT_DB";
	private static DB db;
	
	private DatabaseManager() {
	}
	
	public static DB getDbConn() throws UnknownHostException {
		if(db == null){
			MongoClient mongoClient = new MongoClient(HOST, 27017);
			db = mongoClient.getDB(DB);
		}
		return db;
	}
}