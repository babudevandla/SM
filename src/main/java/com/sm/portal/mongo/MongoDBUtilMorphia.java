package com.sm.portal.mongo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


 
/**
 * A MongoDB and Morphia Example
 *
 */

//@Service("nestMongoDBMorphiaUtil")
public class MongoDBUtilMorphia {
	private static MongoClient mongoClient;
	private   String user;
	private   String password;
	private   String database;
	private   String serverAddress;
	private   String authDatabases;
	private String nlobdatabase;
	private String e2goolddatabase;
	
	public static TimeZone timeZone = TimeZone.getDefault();
	private static Properties prop = new Properties();
	
	private static final Map<String,Datastore> dataStoreCache = new HashMap<String,Datastore>();

	public MongoDBUtilMorphia() {
		System.setProperty("DEBUG.MONGO", "false");
	}
	
	
	public String getE2goolddatabase() {
		return e2goolddatabase;
	}


	public void setE2goolddatabase(String e2goolddatabase) {
		this.e2goolddatabase = e2goolddatabase;
	}


	public String getAuthDatabases() {
		return authDatabases;
	}

	public void setAuthDatabases(String authDatabases) {
		this.authDatabases = authDatabases;
	}

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

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public MongoClient getInstance() {
       if(mongoClient != null){
        	return mongoClient;
        }
		String[] serverAddressList = StringUtils.split(serverAddress, ",");
			List<ServerAddress> addressList = new ArrayList<ServerAddress>();
			for (int i = 0; i < serverAddressList.length; i++) {
				String[] serverdetails = StringUtils.split(serverAddressList[i], ":");
				if(serverdetails.length == 2)
					addressList.add(new ServerAddress(serverdetails[0],Integer.parseInt(serverdetails[1])));
				else
					addressList.add(new ServerAddress(serverdetails[0]));
			}
			
			if(StringUtils.isNotBlank(user) && StringUtils.isNotBlank(password)){
				final String[] dbtoAuth = StringUtils.split(authDatabases, ",");
	    		final List<MongoCredential> mongoCred = new ArrayList<MongoCredential>();
	    		for (int i = 0; i < dbtoAuth.length; i++) {
	    			mongoCred.add(MongoCredential.createMongoCRCredential(user, dbtoAuth[i], password.toCharArray()));
				}
	    		mongoClient = new MongoClient(addressList,mongoCred);
	    		MongoCredential mongoCredential =  null;
	    		mongoCredential = MongoCredential.createMongoCRCredential(user, database, password.toCharArray());
	    		//mongoClient = new MongoClient(new ServerAddress(serverAddress),Arrays.asList(mongoCredential));
	    		mongoClient = new MongoClient(addressList,Arrays.asList(mongoCredential));
	    		//com.mongodb.ReadPreference.primaryPreferred();
	    	}else{
	    		mongoClient = new MongoClient(addressList);
	    	}
			//BSON.addEncodingHook(DateTime.class, new JodaTimeTransformer());
			//BSON.addDecodingHook(Date.class, new JodaTimeTransformer());
			//BSON.addDecodingHook(Timestamp.class,new JodaTimeTransformer());
			//BSON.addDecodingHook(java.sql.Date.class,new JodaTimeTransformer());
			mongoClient.setWriteConcern(WriteConcern.SAFE);
			return mongoClient;
    }
	
	public  MongoCollection<Document> getMongoCollection(String db,String collectionName) {
		return mongoClient.getDatabase(db).getCollection(collectionName);
	}
	
	public  MongoCollection<Document> getMongoCollection(String collectionName) {
		return mongoClient.getDatabase(database).getCollection(collectionName);
	}
	
	public  MongoCollection<Document> getnlobMongoCollection(String db,String collectionName) {
		return mongoClient.getDatabase(db).getCollection(collectionName);
	}
	
	public  MongoCollection<Document> getnlobMongoCollection(String collectionName) {
		return mongoClient.getDatabase(nlobdatabase).getCollection(collectionName);
	}
	
	public  MongoCollection<Document> gete2goOldMongoCollection(String collectionName) {
		return mongoClient.getDatabase(e2goolddatabase).getCollection(collectionName);
	}
	
	public  org.mongodb.morphia.Datastore getConnection() {
		return getMorphiaDatastore(database);
	}
	
	public DBCollection getConnForDbcoll(String db, String coll)
	{
		return (DBCollection) mongoClient.getDatabase(db).getCollection(coll);
	}
	// To be completed
	/*public AggregateIterable<Document> aggregate(StockAggregationPipeLine pipeline){
		try {
			mongoClient = getInstance();
			MongoDatabase mongoDatabase =  mongoClient.getDatabase(database);
			return pipeline.aggregate(mongoDatabase);
		} catch (UnknownHostException e) {
			throw new MongoNotAvailableException(e);
		}
	}*/
	
	/*public AggregateIterable<Document> aggregatenlob(StockAggregationPipeLine pipeline){
		try {
			mongoClient = getInstance();
			MongoDatabase mongoDatabase =  mongoClient.getDatabase(nlobdatabase);
			return pipeline.aggregate(mongoDatabase);
		} catch (UnknownHostException e) {
			throw new MongoNotAvailableException(e);
		}
	}*/
	
	public  MongoDatabase getMongoConnection(String collection) {
			return mongoClient.getDatabase(database);
	}
	/*public  MongoCollection<Document> getMongoCollection(String collection) {
		return getMongoConnection(database).getCollection(database);
	}*/
	public  Datastore getMorphiaDatastore() {
		return getMorphiaDatastore(database);
	}
	public  Datastore getMorphiaDatastore(String schema) {
		if(dataStoreCache.containsKey(schema))
			return dataStoreCache.get(schema);		
		mongoClient = getInstance();
		mongoClient.setWriteConcern(WriteConcern.FSYNCED);
		Morphia morphia1 = new Morphia();
		morphia1.getMapper().getOptions().setStoreEmpties(true);
		morphia1.getMapper().getOptions().setStoreNulls(true);
		Datastore db = morphia1.createDatastore(mongoClient, schema);
		db.ensureIndexes();
		dataStoreCache.put(schema, db);
		return db;
	}
	public String getNlobdatabase() {
		return nlobdatabase;
	}
	public void setNlobdatabase(String nlobdatabase) {
		this.nlobdatabase = nlobdatabase;
	}
	
/*	public  Datastore getCollection(String collectionName) throws UnknownHostException
	{
		//MongoCollection<Document> collection =null;				
		Datastore
		return ;		
	}*/
	 
	    /*public static void main( String[] args ) throws UnknownHostException, MongoException {
	 
	     String dbName = new String("bank");
	     Mongo mongo = new Mongo("localhost",27017);
	     Morphia morphia = new Morphia();
	     DB db = mongo.getDB("test");
	     
	     db.authenticate(username, password);
	     Datastore datastore = morphia.createDatastore(mongo, dbName);  
	     //Datastore datastore1 = morphia.createDatastore(mongo, dbName,"","");  
	 
	     morphia.mapPackage("com.city81.mongodb.morphia.entity");
	     
	     
	     
	     Morphia morphia1 = new Morphia();
	     ServerAddress addr = new ServerAddress("host", 27017);
	     List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
	     MongoCredential credentia = MongoCredential.createPlainCredential(
	         "username", "dbname", "password".toCharArray());
	     credentialsList.add(credentia);
	     MongoClient client = new MongoClient(addr, credentialsList);
	     datastore = morphia1.createDatastore(client, "dbname");
	     
	         
	     Address address = new Address();
	     address.setNumber("81");
	     address.setStreet("Mongo Street");
	     address.setTown("City");
	     address.setPostcode("CT81 1DB");  
	 
	     Account account = new Account();
	     account.setName("Personal Account");
	 
	     List<Account> accounts = new ArrayList<Account>();
	     accounts.add(account);  
	 
	     Customer customer = new Customer();
	     customer.setAddress(address);
	     customer.setName("Mr Bank Customer");
	     customer.setAccounts(accounts);
	     
	     Key<Customer> savedCustomer = datastore.save(customer);    
	     System.out.println(savedCustomer.getId());
	 */
	}