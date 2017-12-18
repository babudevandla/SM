package com.sm.portal.model;

import java.util.Date;

public class FileManagementVO {

	private Integer doc_id;
	private Integer user_id;
	private String foldername;
	private Date created_date;
	private String file_size;
	private String uploaded_by;
	private Integer folder_parent_id;
	private boolean file_status;
	private Integer file_id;
	
	public Integer getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(Integer doc_id) {
		this.doc_id = doc_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getFoldername() {
		return foldername;
	}
	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	public String getUploaded_by() {
		return uploaded_by;
	}
	public void setUploaded_by(String uploaded_by) {
		this.uploaded_by = uploaded_by;
	}
	public Integer getFolder_parent_id() {
		return folder_parent_id;
	}
	public void setFolder_parent_id(Integer folder_parent_id) {
		this.folder_parent_id = folder_parent_id;
	}
	public boolean isFile_status() {
		return file_status;
	}
	public void setFile_status(boolean file_status) {
		this.file_status = file_status;
	}
	public Integer getFile_id() {
		return file_id;
	}
	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}
	@Override
	public String toString() {
		return "FileManagementVO [doc_id=" + doc_id + ", user_id=" + user_id + ", foldername=" + foldername
				+ ", created_date=" + created_date + ", file_size=" + file_size + ", uploaded_by=" + uploaded_by
				+ ", folder_parent_id=" + folder_parent_id + ", file_status=" + file_status + "]";
	}
	
}
