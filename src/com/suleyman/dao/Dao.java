package com.suleyman.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.suleyman.db.DatabaseManager;
import com.suleyman.model.User;

public class Dao {
	
	private static final String COLLECTION_NAME = "users";
	private DBCollection dbCollection;

	public Dao() {
		try {
			dbCollection = DatabaseManager.getDbConn().getCollection(COLLECTION_NAME);
		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
		}
	}

	private static DBObject getDBObj(User user) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		docBuilder.append("_id", user.getUserId());
		docBuilder.append("name", user.getName());
		docBuilder.append("surname", user.getSurname());
		docBuilder.append("phoneNumber", user.getPhoneNumber());
		return docBuilder.get();
	}

	public void addUser(User user) {
		dbCollection.insert(getDBObj(user));
	}

	public void deleteUser(int userId) {
		DBObject dbo = BasicDBObjectBuilder.start().add("_id", userId).get();
		dbCollection.remove(dbo);
	}

	public void updateUser(User user) {
		BasicDBObject oldObj = new BasicDBObject().append("_id", user.getUserId());
		BasicDBObject newObj = new BasicDBObject();
		newObj.put("name", user.getName());
		newObj.put("surname", user.getSurname());
		newObj.put("phoneNumber", user.getPhoneNumber());
		dbCollection.update(oldObj, newObj);
	}

	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		DBCursor cursor = dbCollection.find();
		while (cursor.hasNext()) {
			User user = new User();
			DBObject dbo = (DBObject) cursor.next();
			user.setUserId((int) dbo.get("_id"));
			user.setName((String) dbo.get("name"));
			user.setSurname((String) dbo.get("surname"));
			user.setPhoneNumber((String) dbo.get("phoneNumber"));
			userList.add(user);
		}
		return userList;
	}
}