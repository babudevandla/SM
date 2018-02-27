package com.sm.portal.digilocker.mongo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.sm.portal.constants.CollectionsConstant;
import com.sm.portal.digilocker.model.DigiLockerStatusEnum;
import com.sm.portal.digilocker.model.FilesInfo;
import com.sm.portal.digilocker.model.FolderInfo;
import com.sm.portal.digilocker.model.FolderInfoVo;
import com.sm.portal.mongo.MongoDBUtil;




@Repository
public class DigiLockerMongoDao {

	private static final Logger logger = LoggerFactory.getLogger(DigiLockerMongoDao.class);
	
	@Autowired
	MongoDBUtil mongoDBUtil ;
	private Gson gson = new Gson();
	
	 private static final String FILE_PATTERN ="([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf|xls))$)";
	 
	@SuppressWarnings("unchecked")
	public List<FolderInfo> getFolderInfo(Long userId){
		
		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.DIGILOCKER_MONGO_COLLETION);
		Document folderResultDoc = null;
		folderResultDoc =coll.find(Filters.and(Filters.eq("userId",userId),Filters.eq("foldersList.folderStatus",DigiLockerStatusEnum.ACTIVE.toString()))).first();
		//folderResultDoc =coll.find(Filters.and(Filters.eq("userId",userId))).first();
		//Document fileDoc = coll.find(Filters.and(Filters.eq("userId",userId),Filters.eq("parentId",userId))).first();
		List<FolderInfo> folderInfoList = new ArrayList<>();
		if(folderResultDoc!=null){			
			List<Document> foldersList=(List<Document>) folderResultDoc.get("foldersList");	
			
			
			FolderInfo folderInfo=null;
			
			List<FilesInfo> filesInfoList = null;
			FilesInfo filesInfo =null;
			
			List<Document> filesDocs=null;
			
			for(Document folderDoc:foldersList){
				
				folderInfo =new FolderInfo();
				
				folderInfo.setfId(Long.valueOf(folderDoc.getInteger("folderId")));
				folderInfo.setfName(folderDoc.getString("folderName"));
				folderInfo.setParentId(Long.valueOf(folderDoc.getInteger("parentId")));
				folderInfo.setFolderPath(folderDoc.getString("folderPath"));
				folderInfo.setFolderNamePath(folderDoc.getString("folderNamePath"));
				folderInfo.setFolderStatus(folderDoc.getString("folderStatus"));
				filesDocs = (List<Document>) folderDoc.get("files");
				if(filesDocs!=null){
					filesInfoList = new ArrayList<>();
					for(Document fileDocument:filesDocs){
						if(fileDocument.getString("fileStatus").equals(DigiLockerStatusEnum.ACTIVE.toString())){
							filesInfo =new FilesInfo();
							filesInfo.setFileName(fileDocument.getString("fileName"));
							filesInfo.setDumy_filename(fileDocument.getString("dumy_filename"));
							filesInfo.setFilePath(fileDocument.getString("filePath"));
							filesInfo.setFileId(fileDocument.getInteger("fileId"));
							filesInfo.setFileStatus(fileDocument.getString("fileStatus"));
							//filesInfo.setCreateddate(fileDocument.getDate("createddate"));
							//filesInfo.setUpdateddate(fileDocument.getDate("updateddate"));
							
							if(filesInfo.getFileName().endsWith(".jpg") || filesInfo.getFileName().endsWith(".png") || 
									filesInfo.getFileName().endsWith(".jpeg") ||  filesInfo.getFileName().endsWith(".gif")){
								filesInfo.setFileType("img");
							}else if(filesInfo.getFileName().endsWith(".pdf") ){
								filesInfo.setFileType("pdf");
							}else if(filesInfo.getFileName().endsWith(".xls")|| filesInfo.getFileName().endsWith(".xlsx")){
								filesInfo.setFileType("xls");
							}else if(filesInfo.getFileName().endsWith(".txt") ){
								filesInfo.setFileType("txt");
							}else if(filesInfo.getFileName().endsWith(".doc") || filesInfo.getFileName().endsWith(".docx") ){
								filesInfo.setFileType("doc");
							}
							filesInfoList.add(filesInfo);
						}
					}//inner for closing
				}//if closing
				
				folderInfo.setLocalFilesInfo(filesInfoList);
				if(folderInfo.getFolderStatus().equals(DigiLockerStatusEnum.ACTIVE.toString()))
					folderInfoList.add(folderInfo);
			}//for closing
		}
		return folderInfoList;
	}//getDigiLockerHomeData() closing

	public void storeNewFileOrFolderInfo(FolderInfo newFolderInfo, Integer folderId,Integer userId) {
		
		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.DIGILOCKER_MONGO_COLLETION);
		Bson filter = Filters.eq("userId", userId);
		
		FindIterable<Document> folderInfoVos=coll.find(filter);
		if(null != folderInfoVos){
			for (Document cur :  folderInfoVos ) {
				boolean isFolderAlreadyExists =false;
				FolderInfoVo folderInfoVo = gson.fromJson(cur.toJson(), FolderInfoVo.class);
				List<FolderInfo> folderInfoList=folderInfoVo.getFoldersList();
				for(FolderInfo folderInfo :folderInfoList){
					if(folderInfo.getfId()==folderId.intValue()){
						List<FilesInfo>  fileList=folderInfo.getLocalFilesInfo();
						if(fileList!=null){
							fileList.add(newFolderInfo.getLocalFilesInfo().get(0));
						}else{
							fileList = new ArrayList<>();
							fileList.add(newFolderInfo.getLocalFilesInfo().get(0));
							folderInfo.setLocalFilesInfo(fileList);
						}
						isFolderAlreadyExists=true;
					}
				}//for closing
				
				if(!isFolderAlreadyExists){
					newFolderInfo.setLocalFilesInfo(new ArrayList<>());
					folderInfoList.add(newFolderInfo);
				}//if closing
				String folderInfoVoJson = gson.toJson(folderInfoVo);
				Document folderInfoVoJsonDoc = Document.parse(folderInfoVoJson);
				coll.findOneAndUpdate(filter,new Document("$set", folderInfoVoJsonDoc),new FindOneAndUpdateOptions().upsert(true)) ;
				
			}//outer for closing
		}
	}//storeFileInfoInDB() closing
	public void updateFileOrFolderSatus(String deleteInfo, String action,Integer folderId,Integer fildId, Integer userId) {
		
		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.DIGILOCKER_MONGO_COLLETION);
		Bson filter = Filters.eq("userId", userId);
		
		FindIterable<Document> folderInfoVos=coll.find(filter);
		if(null != folderInfoVos){
			for (Document cur :  folderInfoVos ) {
				FolderInfoVo folderInfoVo = gson.fromJson(cur.toJson(), FolderInfoVo.class);
				List<FolderInfo> folderInfoList=folderInfoVo.getFoldersList();
				for(FolderInfo folderInfo :folderInfoList){
					if(folderInfo.getfId()==folderId.intValue()){
						if(deleteInfo.equals("File")){
							List<FilesInfo>  fileList=folderInfo.getLocalFilesInfo();
							List<FilesInfo> updatableFileInfoList=fileList.stream().filter(folder->folder.fileId == fildId.intValue()).collect(Collectors.toList());
							if(updatableFileInfoList!=null && updatableFileInfoList.size()>0){
								FilesInfo updatableFile=updatableFileInfoList.get(0);
								if(action.equals("Hide")){
									updatableFile.setFileStatus(DigiLockerStatusEnum.HIDDEN.toString());
								}else if(action.equals("Delete")){
									updatableFile.setFileStatus(DigiLockerStatusEnum.DELETED.toString());
								}//else closing
							}//if closing
						}else{
							if(action.equals("Hide")){
								folderInfo.setFolderStatus(DigiLockerStatusEnum.HIDDEN.toString());
							}else if(action.equals("Delete")){
								folderInfo.setFolderStatus(DigiLockerStatusEnum.DELETED.toString());
							}
						}//else cloisng
						
					}//if closing
				}//for closing
				String folderInfoVoJson = gson.toJson(folderInfoVo);
				Document folderInfoVoJsonDoc = Document.parse(folderInfoVoJson);
				coll.findOneAndUpdate(filter,new Document("$set", folderInfoVoJsonDoc),new FindOneAndUpdateOptions().upsert(true)) ;
				
			}//outer for closing
		}
	}//storeFileInfoInDB() closing

	public void showHiddenFoldersAndFiles(Integer fid, Integer userId) {
		
		MongoCollection<Document> coll = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.DIGILOCKER_MONGO_COLLETION);
		Bson filter = Filters.eq("userId", userId);
		
		FindIterable<Document> folderInfoVos=coll.find(filter);
		if(null != folderInfoVos){
			for (Document cur :  folderInfoVos ) {
				FolderInfoVo folderInfoVo = gson.fromJson(cur.toJson(), FolderInfoVo.class);
				List<FolderInfo> folderInfoList=folderInfoVo.getFoldersList();
				for(FolderInfo folderInfo :folderInfoList){
					if(folderInfo.getfId()==fid.intValue() || folderInfo.getParentId()==fid.intValue()){
						if(!folderInfo.getFolderStatus().equals(DigiLockerStatusEnum.DELETED.toString())){
							folderInfo.setFolderStatus(DigiLockerStatusEnum.ACTIVE.toString());
							List<FilesInfo>  fileList=folderInfo.getLocalFilesInfo();
							if(fileList!=null && fileList.size()>0){
								for(FilesInfo filesInfo:fileList){
									if(!filesInfo.getFileStatus().equals(DigiLockerStatusEnum.DELETED.toString()))
										filesInfo.setFileStatus(DigiLockerStatusEnum.ACTIVE.toString());
								}//for closing
								
							}//if closing
						}
						//break;
					}//if closing
					
					
					String folderInfoVoJson = gson.toJson(folderInfoVo);
					Document folderInfoVoJsonDoc = Document.parse(folderInfoVoJson);
					coll.findOneAndUpdate(filter,new Document("$set", folderInfoVoJsonDoc),new FindOneAndUpdateOptions().upsert(true)) ;
				}//for closing
			}//for closing
		}//if closing
	}//showHiddenFoldersAndFiles() closing
	
}//class closing
