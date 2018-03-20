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
import com.sm.portal.ebook.enums.EbookStatusEnum;
import com.sm.portal.ebook.model.BookVo;
import com.sm.portal.ebook.model.Ebook;
import com.sm.portal.ebook.model.EbookPageBean;
import com.sm.portal.ebook.model.UserBooks;
import com.sm.portal.mongo.MongoDBUtil;

@Repository
public class EbookMongoDao {

	@Autowired
	MongoDBUtil mongoDBUtil ;
	public void getEbookList(Integer userId) {

		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_LIST_MONGO_COLLECTON);
	}

	@SuppressWarnings("unchecked")
	public void createEbook(Ebook ebook) {

		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_MONGO_COLLECTION);
		Bson filter=Filters.and(Filters.eq("userId",ebook.getUserId()));
		UserBooks userBook =this.getUserBook(ebook);
		List<BookVo> books =null;
		if(userBook!=null){
			books=userBook.getBooks();
			BookVo bv = new BookVo();
			bv.setBookId(ebook.getBookId());
			bv.setBookTitle(ebook.getBookTitle());
			bv.setCreatedDate(new Date());
			bv.setCoverPage(ebook.getCoverImage());
			bv.setStatus(EbookStatusEnum.ACTIVE.toString());
			books.add(bv);
			
		}
		userBook.setBooks(books);
		Document userBookDoc = this.getUserBookDocument(userBook);
		coll.findOneAndUpdate(filter,new Document("$set", userBookDoc),new FindOneAndUpdateOptions().upsert(true)) ;
	}

	private Document getUserBookDocument(UserBooks userBook) {
		Document userBookDoc =new Document();
		userBookDoc.put("userId", userBook.getUserId());
		List<Document> booksDoc = new ArrayList<>();
		Document bookVoDoc = null;
		for(BookVo bv:userBook.getBooks()){
			bookVoDoc =new Document();
			bookVoDoc.put("bookId", bv.getBookId());
			bookVoDoc.put("bookTitle", bv.getBookTitle());
			bookVoDoc.put("createdDate", bv.getCreatedDate());
			bookVoDoc.put("coverPage", bv.getCoverPage());
			bookVoDoc.put("status",bv.getStatus());
			booksDoc.add(bookVoDoc);
		}//for closing
		userBookDoc.put("books", booksDoc);
		return userBookDoc;
	}//getUserBookDocument() closings

	@SuppressWarnings("unchecked")
	private UserBooks getUserBook(Ebook ebook) {
		Document userBookDoc=null;
		MongoCollection<Document> coll = null;
		coll=mongoDBUtil.getMongoCollection(CollectionsConstant.EBOOK_MONGO_COLLECTION);
		userBookDoc =this.getUserBookDocumentByUserId(ebook.getUserId(), coll);
		if(userBookDoc==null){//if already not available createing first
			userBookDoc=new Document();
			userBookDoc.put("userId", ebook.getUserId());
			userBookDoc.put("books", new ArrayList<Document>());
			coll.findOneAndUpdate(Filters.eq("userId",ebook.getUserId()),new Document("$set", userBookDoc),new FindOneAndUpdateOptions().upsert(true));//creating first and getting then
			userBookDoc =this.getUserBookDocumentByUserId(ebook.getUserId(), coll);
		}//if closing
		UserBooks ub =new UserBooks();
		ub.setUserId(userBookDoc.getInteger("userId"));
		List<Document> bookListDocs = (List<Document>) userBookDoc.get("books");
		BookVo bv = null;
		List<BookVo> books = new ArrayList<>();
		if(bookListDocs!=null){
			for(Document doc:bookListDocs){
				bv =new BookVo();
				bv.setBookId(doc.getInteger("bookId"));
				bv.setBookTitle(doc.getString("bookTitle"));
				bv.setCreatedDate(doc.getDate("createdDate"));
				bv.setCoverPage(doc.getString("coverPage"));
				bv.setStatus(doc.getString("status"));
				books.add(bv);
			}//for closing
		}//if cllsing
		ub.setBooks(books);
		return ub;
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
