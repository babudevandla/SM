package com.sm.portal.mongo.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository
public class CommentsMongoDao {

	private static final Logger logger = LoggerFactory.getLogger(CommentsMongoDao.class);
	
	/*@Autowired
	MongoDBUtil mongoDBUtil ;
	MongoMorphiaUtil mongoMorphiaUtil;
	
	public void saveComment(BlogComments blogComments) {
		MongoCollection<Document>   collection =null;	
		try{
			collection = mongoDBUtil.getMongoCollection(CollectionsConstant.COMMENT_COLLECTION);
			Document  document =new Document();
			Integer blogid=generateAutoId();
			document.put("blog_id", blogid);
			document.put("name", blogComments.getName());
			document.put("comment",blogComments.getComment());
			document.put("author", blogComments.getAuthor());
			document.put("email",blogComments.getEmail());
			document.put("status", true);
			document.put("createddate",blogComments.getCreatedate());
			List<Document> commentslist=new ArrayList<Document>();
			document.put("comments_list", commentslist);
			logger.info(" initialized mongo with collection ::"+CollectionsConstant.COMMENT_COLLECTION);
			collection.insertOne(document);
				logger.info(" saved the data to the collection ");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private Integer generateAutoId() {
		Integer commentId=0;
		MongoCollection<Document> coll = null;
		MongoCursor<Document> cursor = null;
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.COMMENT_COLLECTION);
		cursor=coll.find().sort(new Document("commentid",-1)).limit(1).iterator();;
		while(cursor.hasNext())  {
			Document document=cursor.next();
			commentId=document.getInteger("blog_id");
		}	
		if(commentId!=0){
			commentId=commentId+1;
		}else{
			commentId=100000;
		}
		return commentId;
	}

	public List<BlogComments> getBlogComments() {
		MongoCollection<Document> coll = null;
		MongoCursor<Document> cursor = null;
		List<BlogComments> blogsComments=new ArrayList<BlogComments>();
		
		coll = mongoDBUtil.getMongoCollection(CollectionsConstant.COMMENT_COLLECTION);
		cursor = coll.find(Filters.and(Filters.eq("status",true))).iterator();
		while(cursor.hasNext()){
			Document document = cursor.next();
			BlogComments comments=new BlogComments();
			comments.setBlogid((Integer)document.get("blog_id"));		
			comments.setName((String)document.get("name"));
			comments.setComment((String)document.get("comment"));
			comments.setAuthor((String)document.get("author"));
			comments.setEmail((String)document.get("email"));
			comments.setStatus((boolean)document.get("status"));
			comments.setCreatedate((Date)document.get("createddate"));
			
			blogsComments.add(comments);
		}
		return blogsComments;
	}
*/
	
}
