package com.sm.portal.ebook.mongo.dao;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.sm.portal.constants.CollectionsConstant;
import com.sm.portal.ebook.model.Ebook;
import com.sm.portal.ebook.model.EbookPageBean;
import com.sm.portal.mongo.MongoDBUtil;

@Repository
public class EbookMongoDao {

	@Autowired
	MongoDBUtil mongoDBUtil ;
	public void getEbookList(Integer userId) {

		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_LIST_MONGO_COLLECTON);
	}

	public void createEbook(Ebook ebook) {

		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_MONGO_COLLECTION);
	}

	public void creatChapter(EbookPageBean ebookPageBean) {

		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_MONGO_COLLECTION);
	}

	public void updateChapter(EbookPageBean ebookPageBean) {

		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_MONGO_COLLECTION);
	}

	public void updateEbookPage(EbookPageBean ebookPageBean) {

		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_MONGO_COLLECTION);
	}

	public void updateEbookDetails(Ebook ebook) {

		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_MONGO_COLLECTION);
	}

}
