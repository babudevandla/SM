package com.sm.portal.ebook.mongo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.sm.portal.constants.CollectionsConstant;
import com.sm.portal.ebook.enums.EbookChapterTypeEnum;
import com.sm.portal.ebook.enums.EbookStatusEnum;
import com.sm.portal.ebook.model.Ebook;
import com.sm.portal.ebook.model.EbookPage;
import com.sm.portal.ebook.model.EbookPageBean;
import com.sm.portal.ebook.model.UserBook;
import com.sm.portal.ebook.model.UserBooks;
import com.sm.portal.mongo.MongoDBUtil;

@Repository
public class EbookMongoDao {

	@Autowired
	MongoDBUtil mongoDBUtil ;
	
	public UserBooks getEbookList(Integer userId) {
		UserBooks userBooks =null;
		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_MONGO_COLLECTION);
		Document userBookDoc =this.getUserBookDocumentByUserId(userId, coll);
		if(userBookDoc!=null){
			userBooks =new UserBooks();
			userBooks.setUserId(userBookDoc.getInteger("userId"));
			@SuppressWarnings("unchecked")
			List<Document> bookListDocs = (List<Document>) userBookDoc.get("books");
			UserBook bv = null;
			List<UserBook> books = new ArrayList<>();
			if(bookListDocs!=null){
				for(Document doc:bookListDocs){
					bv =new UserBook();
					bv.setBookId(doc.getInteger("bookId"));
					bv.setBookTitle(doc.getString("bookTitle"));
					bv.setCreatedDate(doc.getDate("createdDate"));
					bv.setCoverPage(doc.getString("coverPage"));
					bv.setStatus(doc.getString("status"));
					books.add(bv);
				}//for closing
			}//if cllsing
			userBooks.setBooks(books);
		}//if closing
		return userBooks;
	}//getEbookList() closing

	public void createUserBook(Ebook ebook) {

		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_MONGO_COLLECTION);
		Bson filter=Filters.and(Filters.eq("userId",ebook.getUserId()));
		Document userBookDoc =this.getUserBookDocument(ebook,coll);
		coll.findOneAndUpdate(filter,new Document("$set", userBookDoc),new FindOneAndUpdateOptions().upsert(true)) ;
		this.createEbook(ebook);
	}

	public void createEbook(Ebook ebook) {
		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_LIST_MONGO_COLLECTON);
		Bson filter=Filters.and(Filters.eq("userId",ebook.getUserId()), Filters.eq("bookId",ebook.getBookId()));
		
		Document ebookDoc = new Document();
		ebookDoc.put("userId", ebook.getUserId());
		ebookDoc.put("bookId", ebook.getBookId());
		ebookDoc.put("bookTitle", ebook.getBookTitle());
		ebookDoc.put("coverImage", ebook.getCoverImage());
		ebookDoc.put("bookSize", ebook.getBookSize());
		ebookDoc.put("pageSize", ebook.getPageSize());
		
		List<Document> ebookPageDocs = new ArrayList<>();
		
		Document ebookPageDoc =null;
		
		for(int i=1;i<=ebook.getBookSize();i++){
			ebookPageDoc =new Document();
			ebookPageDoc.put("pageNo", i);
			ebookPageDoc.put("chapterName", "Chapter-1");
			ebookPageDoc.put("content", "");
			if(i==1)				
				ebookPageDoc.put("chaperType", EbookChapterTypeEnum.CHAPTER_NAME.toString());
			else
				ebookPageDoc.put("chaperType", EbookChapterTypeEnum.PAGE_CONTENT.toString());
			ebookPageDocs.add(ebookPageDoc);
			
		}//for closing
		
		
		ebookDoc.put("ebookPages", ebookPageDocs);
		coll.findOneAndUpdate(filter,new Document("$set", ebookDoc),new FindOneAndUpdateOptions().upsert(true)) ;
		
	}//createEbook

	@SuppressWarnings("unchecked")
	private Document getUserBookDocument(Ebook ebook, MongoCollection<Document> coll) {
		//Document userBookDoc = new Document();
		Document userBookDoc=null;
		List<Document> userBookList =null;
		if(coll!=null){
			userBookDoc =this.getUserBookDocumentByUserId(ebook.getUserId(), coll);
			if(userBookDoc!=null){
				userBookList =(List<Document>) userBookDoc.get("books");
			}
		}
		if(userBookDoc==null){//if already not available createing first
			userBookDoc=new Document();
			userBookDoc.put("userId", ebook.getUserId());
			userBookDoc.put("books", userBookList);
			coll.findOneAndUpdate(Filters.eq("userId",ebook.getUserId()),new Document("$set", userBookDoc),new FindOneAndUpdateOptions().upsert(true));//creating first and getting then
			userBookDoc =this.getUserBookDocumentByUserId(ebook.getUserId(), coll);
			if(userBookDoc!=null){
				userBookList =(List<Document>) userBookDoc.get("books");
			}
		}
		if(userBookList==null)userBookList =new ArrayList<>();
		
		Document bookDoc = new Document();
		
		bookDoc.put("bookId", ebook.getBookId());
		bookDoc.put("bookTitle", ebook.getBookTitle());
		bookDoc.put("bookSize", ebook.getBookSize());
		bookDoc.put("pageSize", ebook.getPageSize());
		bookDoc.put("createdDate", new Date());
		bookDoc.put("status", EbookStatusEnum.ACTIVE.toString());
		bookDoc.put("coverPage", ebook.getCoverImage());
		userBookList.add(bookDoc);
		userBookDoc.put("books", userBookList);
		return userBookDoc;
	}//getEbookDocument() closing

	private Document getUserBookDocumentByUserId(Integer userId, MongoCollection<Document> coll) {
		Document userBookDoc=null;
		MongoCursor<Document> cursor = null;
		if(coll==null){
			coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_MONGO_COLLECTION);
		}//if closing
		cursor =coll.find(Filters.eq("userId",userId)).iterator();
		
		while(cursor.hasNext()){
			 userBookDoc = cursor.next();
			 break;
			}
		return userBookDoc;
	}//getUserBookDocumentByUserId() closing

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
