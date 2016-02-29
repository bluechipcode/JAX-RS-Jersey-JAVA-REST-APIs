package com.betterworld;

import org.bson.types.ObjectId; 
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

public class DAO {

	static MongoClient mongoClient; 
	DB db;
	DBCollection collection;
	DBCursor cursor;
	BasicDBObject searchQuery;
	String data;
	ObjectId id;
	JSONObject errorObj = new JSONObject();
	JSON json; 

	String connection = openConnection();

	public String openConnection() {
		mongoClient = new MongoClient(new MongoClientURI("mongodb://djmongo:d1j2p3r4@ds039185.mongolab.com:39185/betterworld"));
		return "success";
	}

	public void closeConnection() {
		mongoClient.close();
	}

	public String findAll() throws JSONException {

		try {
			if (connection != "success")
				errorObj.put("message", connection);

			db = mongoClient.getDB("betterworld");

			collection = db.getCollection("company");

			cursor = collection.find();

			json = new JSON();
			data = json.serialize(cursor);
            
			errorObj.put("error", 0);
			errorObj.put("message", "");
			mongoClient.close();
			return data;

		} catch (Exception e) {
			e.printStackTrace();
			errorObj.put("error", 1);
			errorObj.put("message", e);
		}

		return errorObj.toString();
	} 

	public String createCompany(String company) throws JSONException {

		DBObject companydata = (DBObject) JSON.parse(company);

		try {
			if (connection != "success")
				errorObj.put("message", connection);

			db = mongoClient.getDB("betterworld");

			collection = db.getCollection("company");

			collection.insert(companydata);
			id = (ObjectId) companydata.get("_id");

			searchQuery = new BasicDBObject();
			searchQuery.put("_id", id);

			cursor = collection.find(searchQuery);

			json = new JSON();
			data = json.serialize(cursor);

			errorObj.put("error", 0);
			errorObj.put("message", "");
			mongoClient.close();

			return data;

		} catch (Exception e) {
			e.printStackTrace();
			errorObj.put("error", 1);
			errorObj.put("message", e);
		}

		return errorObj.toString();
	}

	public String updateCompany(String company) throws JSONException {

		DBObject companydata;
		companydata = (DBObject) JSON.parse(company);

		try {
			if (connection != "success")
				errorObj.put("message", connection);

			db = mongoClient.getDB("betterworld");

			collection = db.getCollection("company");

			id = (ObjectId) companydata.get("_id");
			searchQuery = new BasicDBObject();
			searchQuery.put("_id", id);
			collection.update(searchQuery, companydata);

			cursor = collection.find(searchQuery);

			json = new JSON();
			data = json.serialize(cursor);

			errorObj.put("error", 0);
			errorObj.put("message", "");
			mongoClient.close();

			return data;

		} catch (Exception e) {
			e.printStackTrace();
			errorObj.put("error", 1);
			errorObj.put("message", e);
		}

		return errorObj.toString();
	}

	public String deleteCompany(String company) throws JSONException {

		DBObject companydata = (DBObject) JSON.parse(company);

		try {
			if (connection != "success")
				errorObj.put("message", connection);

			db = mongoClient.getDB("betterworld");

			collection = db.getCollection("company");

			id = (ObjectId) companydata.get("_id");

			searchQuery = new BasicDBObject();
			searchQuery.put("_id", id);

			cursor = collection.find(searchQuery);
			json = new JSON();
			data = json.serialize(cursor);
			System.out.println(data);

			collection.remove(searchQuery);

			errorObj.put("error", 0);
			errorObj.put("message", "");
			mongoClient.close();

			return data;

		} catch (Exception e) {
			e.printStackTrace();
			errorObj.put("error", 1);
			errorObj.put("message", e);
		}

		return errorObj.toString();
	} 

}
