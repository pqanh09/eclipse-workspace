package com.ov.main;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;


public class MainDemo {
	
	public static void main(String[] args) {
		System.out.println(MainDemo.class);
		try {
			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("demoDatabase");
			System.out.println("Connect to database successfully");
			
			DBCollection songTable = db.getCollection("song_table");
			System.out.println("get collection successfully");
			
			BasicDBObject document = new BasicDBObject();
			document.append("name", "chung ta khong thuoc ve nhau");
			document.append("author", "Son Tac");
			document.append("type", "nhac trau tre");
			
			songTable.insert(document);
			System.out.println("insert document successfully");
			
			DBCursor cursor = songTable.find();
			int i = 1;
			while (cursor.hasNext()) {
				System.out.println("Inserted document: "+ i);
				System.out.println(cursor.next());
				i++;
			}
			
			
			
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
		
	}

}
