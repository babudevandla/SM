package com.sm.portal.mongo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bson.BSON;
import org.bson.Document;
import org.joda.time.DateTime;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

	static Logger logger = Logger.getLogger(MongoDBUtil.class);
	private MongoClient mongoClient;

	public static TimeZone timeZone = TimeZone.getDefault();
	private String user;// "nlob_user";
	private String password;// "k7XkVQU3jD";
	private String defDatabase;// "nlob";
	private String authDatabases;// "nlob";
	private String serverAddress;// "127.0.0.1";

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * public String getDefDatabase() { return defDatabase; }
	 */

	public void setDefDatabase(String defDatabase) {
		this.defDatabase = defDatabase;
	}

	/*
	 * public String getAuthDatabases() { return authDatabases; }
	 */

	public void setAuthDatabases(String authDatabases) {
		this.authDatabases = authDatabases;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public void initIt() throws Exception {

		logger.trace("-----------------------------------------------------------");
		logger.trace("initializing the mongo client with ");
		logger.trace(" userr " + user);
		logger.trace("def database "+defDatabase);
		logger.trace(" host " + serverAddress);
		logger.trace("-----------------------------------------------------------");

		String[] serverAddressList = org.apache.commons.lang3.StringUtils.split(serverAddress, ",");
		List<ServerAddress> addressList = new ArrayList<ServerAddress>();
		for (int i = 0; i < serverAddressList.length; i++) {
			String[] serverdetails = org.apache.commons.lang3.StringUtils.split(serverAddressList[i], ":");
			if (serverdetails.length == 2)
				addressList.add(new ServerAddress(serverdetails[0], Integer.parseInt(serverdetails[1])));
			else
				addressList.add(new ServerAddress(serverdetails[0]));
		}

		if (StringUtils.isNotBlank(user) && StringUtils.isNotBlank(password)) {
			//MongoCredential mongoCredential = null;
			final String[] dbtoAuth = StringUtils.split(authDatabases, ",");
			final List<MongoCredential> mongoCred = new ArrayList<MongoCredential>();
			for (int i = 0; i < dbtoAuth.length; i++) {
    			mongoCred.add(MongoCredential.createCredential(user, dbtoAuth[i], password.toCharArray()));
			}			
			// mongoClient = new MongoClient(new
			// ServerAddress(serverAddress),Arrays.asList(mongoCredential));
			mongoClient = new MongoClient(addressList,mongoCred/*Arrays.asList(mongoCredential)*/);
			// com.mongodb.ReadPreference.primaryPreferred();
		} else {
			mongoClient = new MongoClient(addressList);
		}
		BSON.addEncodingHook(DateTime.class, new JodaTimeTransformer());
		BSON.addDecodingHook(Date.class, new JodaTimeTransformer());
		BSON.addDecodingHook(Timestamp.class, new JodaTimeTransformer());
		// BSON.addDecodingHook(java.sql.Date.class,new JodaTimeTransformer());
		mongoClient.setWriteConcern(WriteConcern.SAFE);
	}

	/*
	 * public void getReplicaSetStatus() { //ReplicaSetStatus rss =
	 * ReplicaSetStatus.DOWN; MongoClient freshClient = null; try { if (
	 * mongoClient != null ) { ReplicaSetStatus replicaSetStatus =
	 * mongoClient.getReplicaSetStatus(); if ( replicaSetStatus != null ) { if (
	 * replicaSetStatus.getMaster() != null ) {
	 * System.out.println(" master available "); } else { // // When
	 * mongo.getReplicaSetStatus().getMaster() returns null, it takes a a //
	 * fresh client to assert whether the ReplSet is read-only or completely //
	 * down. I freaking hate this, but take it up with 10gen. // freshClient =
	 * new MongoClient( mongoClient.getAllAddress(),
	 * mongoClient.getMongoClientOptions() ); replicaSetStatus =
	 * freshClient.getReplicaSetStatus(); if ( replicaSetStatus != null ) {
	 * System.out.println( "fresh look " + replicaSetStatus.getMaster() != null
	 * ? "mater - read write " : "slave - read" ); } else { logger.warn(
	 * "freshClient.getReplicaSetStatus() is null" ); } } } else { logger.warn(
	 * "mongo.getReplicaSetStatus() returned null" ); } } else { throw new
	 * IllegalStateException( "mongo is null?!?" ); } } catch ( Throwable t ) {
	 * logger.error( "Ingore unexpected error", t ); } finally { if (
	 * freshClient != null ) { freshClient.close(); } }
	 * 
	 * }
	 */

	MongoDatabase getMongoDB(String str) {
		return mongoClient.getDatabase(str);
	}

	public DB getConnection(String str) {
		return mongoClient.getDB(str);
	}

	public MongoCollection<Document> getMongoCollection(String collectionName) {
		MongoCollection<Document> collection = null;
		collection = getMongoDB(defDatabase).getCollection(collectionName);
		return collection;
	}

	public DBCollection getCollection(String collectionName) {
		// TODO Auto-generated method stub
		return null;
	}


}

class JodaTimeTransformer implements org.bson.Transformer {

	@Override
	public Object transform(Object o) {
		if (o instanceof DateTime) {
			// DateTimeZone timeZone = DateTimeZone.forID("Europe/Stockholm");
			return ((DateTime) o).toDate();
		} else if (o instanceof Timestamp) {
			long time = ((Timestamp) o).getTime();
			return new DateTime(time).toDate();
		} else if (o instanceof Date) {
			return new DateTime((Date) o);
		}
		/*
		 * else if(o instanceof java.sql.Date) { return new
		 * DateTime((java.sql.Date) o); }
		 */
		throw new IllegalArgumentException(
				"JodaTimeTransformer can only be used with DateTime or Date");
	}

}
