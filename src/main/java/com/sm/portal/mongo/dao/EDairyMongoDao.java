package com.sm.portal.mongo.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.sm.portal.constants.CollectionsConstant;
import com.sm.portal.model.EDairyDto;
import com.sm.portal.model.FileManagementVO;
import com.sm.portal.model.Users;
import com.sm.portal.mongo.MongoDBUtil;

@Repository
public class EDairyMongoDao {

	private static final Logger logger = LoggerFactory.getLogger(EDairyMongoDao.class);
	
	@Autowired
	MongoDBUtil mongoDBUtil ;

	public void saveEDairyData(EDairyDto eDairyDto, Users user) {
		MongoCollection<Document>   collection =null;	
		try{
			collection = mongoDBUtil.getMongoCollection(CollectionsConstant.EDAIRY_MONGO_COLLECTION);
			logger.info(" initialized mongo with collection ::"+CollectionsConstant.EDAIRY_MONGO_COLLECTION);
			
			Document  document =new Document();
			document.put("user_id",user.getUserId());
			document.put("dairy_name",eDairyDto.getDairyname());
			document.put("created_date",eDairyDto.getDairyDate());
			document.put("content",eDairyDto.getDairy_content());
			document.put("status",eDairyDto.isStatus());
			
			Integer doc_id=generateAutoId();
			document.put("_id",doc_id);
			collection.insertOne(document);
			logger.info(" saved the data to the collection ");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public List<EDairyDto> getEDairyList(Integer userId) {
		MongoCollection<Document> coll = null;
		MongoCursor<Document> cursor = null;
		List<EDairyDto> list=new ArrayList<EDairyDto>();
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EDAIRY_MONGO_COLLECTION);
		cursor = coll.find(Filters.and(Filters.eq("user_id",userId))).iterator();
		while(cursor.hasNext()){
			Document document = cursor.next();
			EDairyDto dairyDto=new EDairyDto();
			dairyDto.setDairyId(document.getInteger("_id"));		
			dairyDto.setDairyname(document.getString("dairy_name"));
			dairyDto.setDairyDate(document.getDate("created_date"));
			dairyDto.setStatus(document.getBoolean("status"));
			dairyDto.setDairy_content(document.getString("content"));
			list.add(dairyDto);
		}
		return list;
	}
	
	
	private Integer generateAutoId() {
		Integer commentId=0;
		MongoCollection<Document> coll = null;
		MongoCursor<Document> cursor = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EDAIRY_MONGO_COLLECTION);
		cursor=coll.find().sort(new Document("_id",-1)).limit(1).iterator();;
		while(cursor.hasNext())  {
			Document document=cursor.next();
			commentId=document.getInteger("_id");
		}	
		if(commentId!=0){
			commentId=commentId+1;
		}else{
			commentId=1;
		}
		return commentId;
	}


	public EDairyDto getEDairyDataById(Integer dairyId) {
		MongoCollection<Document> coll = null;
		EDairyDto dairyDto=new EDairyDto();
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EDAIRY_MONGO_COLLECTION);
		Document document = coll.find(Filters.and(Filters.eq("_id",dairyId))).first();
		if(document!=null){
			dairyDto.setDairyId(document.getInteger("_id"));		
			dairyDto.setDairyname(document.getString("dairy_name"));
			dairyDto.setDairyDate(document.getDate("created_date"));
			dairyDto.setStatus(document.getBoolean("status"));
			dairyDto.setDairy_content(document.getString("content"));
		}
		return dairyDto;
	}


	public void updateEDairyData(EDairyDto eDairyDto, Users user) {
		MongoCollection<Document>   collection =null;	
		try{
			collection = mongoDBUtil.getMongoCollection(CollectionsConstant.EDAIRY_MONGO_COLLECTION);
			logger.info(" initialized mongo with collection ::"+CollectionsConstant.EDAIRY_MONGO_COLLECTION);
			
			Document  update =new Document();
			update.put("$set", new Document("content",eDairyDto.getDairy_content()));
			Document filter=new Document("_id",eDairyDto.getDairyId());
			collection.updateOne(filter, update);
			logger.info(" update the data to the collection ");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
