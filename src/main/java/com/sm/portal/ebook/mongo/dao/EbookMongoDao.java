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
import com.sm.portal.ebook.model.EbookPageDto;
import com.sm.portal.ebook.model.UserBook;
import com.sm.portal.ebook.model.UserBooks;
import com.sm.portal.filters.ThreadLocalInfoContainer;
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
					bv.setPageSize(doc.getInteger("pageSize"));
					bv.setBookSize(doc.getInteger("bookSize"));
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

	public void saveEbookPageContent(EbookPageDto eBookPageDto) {

		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_LIST_MONGO_COLLECTON);
		Bson filter=Filters.and(Filters.eq("userId",eBookPageDto.getUserId()), Filters.eq("bookId",eBookPageDto.getBookId()));
		MongoCursor<Document> cursor = null;
		cursor =coll.find(filter).iterator();
		Document ebookDoc =null;
		while(cursor.hasNext()){
			ebookDoc =cursor.next();
			List<Document> ebookPageDocs= (List<Document>) ebookDoc.get("ebookPages");
			for(Document pageDoc: ebookPageDocs){
				if(pageDoc.getInteger("pageNo").intValue()==eBookPageDto.getPageNo().intValue()){
					pageDoc.put("content", eBookPageDto.getContent());
				}
			}//for closing
			
		}//while closing
		
		coll.findOneAndUpdate(filter,new Document("$set", ebookDoc),new FindOneAndUpdateOptions().upsert(true)) ;
		
	}//saveEbookPageContent() closing
	
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

	public Ebook getEbookContent(Integer userId, Integer bookId) {
		MongoCursor<Document> cursor = null;
		MongoCollection<Document> coll=null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_LIST_MONGO_COLLECTON);
		Bson filter =Filters.and(Filters.eq("userId", userId), Filters.eq("bookId", bookId));
		cursor =coll.find(filter).iterator();
		
		Ebook ebook =null;
		Document ebookDoc=null;
		List<Document> ebookPages =new ArrayList<>();
		EbookPage ebookPage =null;
		List<EbookPage> pageList = new ArrayList<>();
		while(cursor.hasNext()){
			ebookDoc =cursor.next();
			ebook =new Ebook();
			ebook.setUserId(ebookDoc.getInteger("userId"));
			ebook.setBookId(ebookDoc.getInteger("bookId"));
			ebook.setBookTitle(ebookDoc.getString("bookTitle"));
			ebook.setCoverImage(ebookDoc.getString("coverImage"));
			ebook.setBookSize(ebookDoc.getInteger("bookSize"));
			ebook.setPageSize(ebookDoc.getInteger("pageSize"));
			
			ebookPages = (List<Document>) ebookDoc.get("ebookPages");
			for(Document pageDoc: ebookPages){
				ebookPage =new EbookPage();
				ebookPage.setPageNo(pageDoc.getInteger("pageNo"));
				ebookPage.setChapterName(pageDoc.getString("chapterName"));
				ebookPage.setContent(pageDoc.getString("content"));
				ebookPage.setChaperType(pageDoc.getString("chaperType"));
				pageList.add(ebookPage);
			}//for closing
			ebook.setEbookPages(pageList);
			
		}//while closing
		
		
		
		return ebook;
	}//getEbookContent() closing
	
	
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

	public void createNewChapter(Integer bookId, int pageNo, String chapterName, Integer userId) {
		MongoCollection<Document> coll = null;
		if(userId==null)
			userId=(Integer) ThreadLocalInfoContainer.INFO_CONTAINER.get().get("USER_ID");
		
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_LIST_MONGO_COLLECTON);
		Bson filter=Filters.and(Filters.eq("userId",userId), Filters.eq("bookId",bookId));
		MongoCursor<Document> cursor = null;
		cursor =coll.find(filter).iterator();
		Document ebookDoc =null;
		while(cursor.hasNext()){
			ebookDoc =cursor.next();
			List<Document> ebookPageDocs= (List<Document>) ebookDoc.get("ebookPages");
			for(Document pageDoc: ebookPageDocs){
				if(pageDoc.getInteger("pageNo").intValue()==pageNo){
					pageDoc.put("chapterName", chapterName);
					pageDoc.put("chaperType", EbookChapterTypeEnum.CHAPTER_NAME.toString());
				}else if(pageDoc.getInteger("pageNo").intValue()>pageNo){
					if(pageDoc.getString("chaperType").equals(EbookChapterTypeEnum.CHAPTER_NAME.toString()))break;
					pageDoc.put("chapterName", chapterName);
				}
			}//for closing
			
		}//while closing
		
		coll.findOneAndUpdate(filter,new Document("$set", ebookDoc),new FindOneAndUpdateOptions().upsert(true)) ;
	}//createNewChapter() closing

	public void updateChapter(Integer bookId, int pageNo, String chapterName, String existingName, Integer userId) {
		if(userId==null)
			userId=(Integer) ThreadLocalInfoContainer.INFO_CONTAINER.get().get("USER_ID");
		
		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_LIST_MONGO_COLLECTON);
		Bson filter=Filters.and(Filters.eq("userId",userId), Filters.eq("bookId",bookId));
		MongoCursor<Document> cursor = null;
		cursor =coll.find(filter).iterator();
		Document ebookDoc =null;
		while(cursor.hasNext()){
			ebookDoc =cursor.next();
			List<Document> ebookPageDocs= (List<Document>) ebookDoc.get("ebookPages");
			for(Document pageDoc: ebookPageDocs){
				if(pageDoc.getString("chapterName").equals(existingName)){
					pageDoc.put("chapterName", chapterName);
				}
			}//for closing
			
		}//while closing
		
		coll.findOneAndUpdate(filter,new Document("$set", ebookDoc),new FindOneAndUpdateOptions().upsert(true)) ;
	}//updateChapter() closing

	public void updateBookCoverImg(Ebook ebook) {
		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_MONGO_COLLECTION);
		Document match = new Document();
		match.put("userId", ebook.getUserId());
		match.put("books.bookId", ebook.getBookId());
		Document updateEbookDoc = new Document();
		updateEbookDoc.put("books.$.coverPage", ebook.getCoverImage());
		
		Document update = new Document();
		update.put( "$set", updateEbookDoc);
		
		coll.updateOne( match, update );
	}

	private Document getUserBookDocumentForCoverImg(Ebook ebook, MongoCollection<Document> coll) {
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
		bookDoc.put("coverPage", ebook.getCoverImage());
		userBookList.add(bookDoc);
		userBookDoc.put("books", userBookList);
		return userBookDoc;
	}

	

	

}//class closing
