package com.sm.portal.edairy.mongo.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.sm.portal.constants.CollectionsConstant;
import com.sm.portal.edairy.model.Dairy;
import com.sm.portal.edairy.model.DairyInfo;
import com.sm.portal.edairy.model.DairyPage;
import com.sm.portal.edairy.model.UserDairies;
import com.sm.portal.model.EDairyDto;
import com.sm.portal.mongo.MongoDBUtil;

@Repository
public class EdairyDaoImpl implements EdairyDao{

	@Autowired
	private MongoDBUtil mongoDBUtil ;

	@SuppressWarnings("unchecked")
	@Override
	public UserDairies gerUserDairies(int userId) {
		UserDairies userDairies =null;
		
		MongoCollection<Document> coll = null;
		MongoCursor<Document> cursor = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EDAIRY_MONGO_USER_DAIRIES);
		cursor = coll.find(Filters.and(Filters.eq("userId",userId))).iterator();
		List<Document> dairyDocList =null;
		List<Dairy> dairyList=new ArrayList<>();
		Dairy dairy =null;
		
		while(cursor.hasNext()){
			Document document = cursor.next();
			userDairies=new UserDairies();
			userDairies.setUserId(document.getInteger("userId"));

			dairyDocList=(List<Document>) document.get("daires");
			for(Document dairyDoc:dairyDocList){
				dairy =new Dairy();
				dairy.setDairyId(dairyDoc.getInteger("dairyId"));
				dairy.setName(dairyDoc.getString("name"));
				dairy.setCreatedDate(dairyDoc.getDate("createdDate"));
				dairy.setStatus(dairyDoc.getString("status"));
				dairyList.add(dairy);
			}
			userDairies.setDairyList(dairyList);
			
		}
		return userDairies;
		
	}//gerUserDairies() closing

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public DairyInfo getDairyInfo(int userId, int dairyId) {

		DairyInfo dairyInfo =null;
		MongoCollection<Document> coll = null;
		MongoCursor<Document> cursor = null;
		
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EDAIRY);
		cursor = coll.find(Filters.and(Filters.eq("userId",userId), Filters.eq("dairyId",dairyId))).iterator();
		List<Document> pagesDocList =null;
		List<DairyPage> pageList=new ArrayList<>();
		DairyPage page =null;
		
		while(cursor.hasNext()){
			Document document = cursor.next();
			dairyInfo=new DairyInfo();
			dairyInfo.setUserId(document.getInteger("userId"));
			dairyInfo.setDairyId(document.getInteger("dairyId"));
			dairyInfo.setDairyName(document.getString("dairyName"));
			dairyInfo.setCoverImage(document.getString("coverImage"));
			dairyInfo.setLastModifiedDate(document.getDate("lastModifiedDate"));
			
			pagesDocList =(List<Document>) document.get("pages");
			
			for(Document pageDoc : pagesDocList){
				page =new DairyPage();
				page.setPageNo(pageDoc.getInteger("pageNo"));
				page.setPageName(pageDoc.getString("pageName"));
				page.setDate(pageDoc.getDate("date"));
				page.setContent(pageDoc.getString("content"));
				page.setPageStatus(pageDoc.getString("pageStatus"));
				pageList.add(page);
				
			}//for closing
			dairyInfo.setPages(pageList);
		}//while closing
		return dairyInfo;
	}//getDairyInfo() closing

	@Override
	public DairyInfo editPageContent(int userId, int dairyId, DairyPage page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean  savePageContent(int userId, int dairyId, DairyPage dairyPage) {

		
		
		
		return true;
	}
	
	
	
	
}
