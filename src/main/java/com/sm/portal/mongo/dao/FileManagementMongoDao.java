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
import com.sm.portal.model.FileManagementVO;
import com.sm.portal.mongo.MongoDBUtil;

@Repository
public class FileManagementMongoDao {

	private static final Logger logger = LoggerFactory.getLogger(FileManagementMongoDao.class);
	
	@Autowired
	MongoDBUtil mongoDBUtil ;
	
	public void createFolder(FileManagementVO filevo) {
		MongoCollection<Document>   collection =null;	
		try{
			collection = mongoDBUtil.getMongoCollection(CollectionsConstant.DOCUMENTS_MONGO_COLLETION);
			Document  document =new Document();
			Bson filter=Filters.and(Filters.eq("user_id", filevo.getUser_id()));
			document.put("user_id",filevo.getUser_id());
			List<Document> folderOrFile=new ArrayList<Document>();
			Document folderDoc=new Document();
			folderDoc.put("file_id",getFilesId(filter));
			folderDoc.put("foldername",filevo.getFoldername());
			folderDoc.put("created_date",filevo.getCreated_date());
			folderDoc.put("uploaded_by",filevo.getUploaded_by());
			folderDoc.put("file_size",filevo.getFile_size());
			folderDoc.put("file_status",filevo.isFile_status());
			folderDoc.put("folder_parent_id",filevo.getFolder_parent_id());
			folderOrFile.add(folderDoc);
			document.put("document_list", folderOrFile);
			
			logger.info(" initialized mongo with collection ::"+CollectionsConstant.DOCUMENTS_MONGO_COLLETION);
			
			Document tempDoc=collection.find(filter).first();
			if(tempDoc!=null){
				List<Document> docList= (List<Document>) tempDoc.get("document_list");
				docList.add(folderDoc);
				collection.updateOne(filter, new Document("$set",new Document("document_list",docList)));
			}else{
				Integer doc_id=generateAutoId();
				document.put("_id",doc_id);
				collection.insertOne(document);
			}
			logger.info(" saved the data to the collection ");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private Object getFilesId(Bson filter) {
		Integer fileId=0;
		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.DOCUMENTS_MONGO_COLLETION);
		Document tempDoc=coll.find(filter).first();
		if(tempDoc!=null){
			List<Document> docList= (List<Document>) tempDoc.get("document_list");	
			for(Document doc:docList){
				fileId=doc.getInteger("file_id");
			}
			if(fileId!=0){
				fileId=fileId+1;
			}else{
				fileId=1;
			}
		}else{
			fileId=1;
		}
		
		return fileId;
	}

	private Integer generateAutoId() {
		Integer commentId=0;
		MongoCollection<Document> coll = null;
		MongoCursor<Document> cursor = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.DOCUMENTS_MONGO_COLLETION);
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

	public List<FileManagementVO> getDocumentsAndFoldesList(Integer userId) {
		MongoCollection<Document> coll = null;
		List<FileManagementVO> list=new ArrayList<FileManagementVO>();
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.DOCUMENTS_MONGO_COLLETION);
		Document fileDoc = coll.find(Filters.and(Filters.eq("user_id",userId))).first();
		if(fileDoc!=null){
			List<Document> filesList=(List<Document>) fileDoc.get("document_list");	
			for(Document file:filesList){
				FileManagementVO fileMgnt=new FileManagementVO();
				fileMgnt.setFile_id(file.getInteger("file_id"));		
				fileMgnt.setFoldername(file.getString("foldername"));
				fileMgnt.setUploaded_by(file.getString("uploaded_by"));
				fileMgnt.setFile_size(file.getString("file_size"));
				fileMgnt.setFolder_parent_id(file.getInteger("folder_parent_id"));
				fileMgnt.setCreated_date(file.getDate("created_date"));
				fileMgnt.setFile_status(file.getBoolean("file_status"));
				
				list.add(fileMgnt);
			}
		}
		return list;
	}
}
