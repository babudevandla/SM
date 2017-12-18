package com.sm.portal.constants;

import java.util.ArrayList;
import java.util.List;

public class GenericResponse {
	
	private boolean warning ;
	private boolean information ;
	private boolean error ;
	private String message ;
	private List<FieldError> fieldErrors = new ArrayList<FieldError>();
	private String title ;
	private String next ;
	private String token ;
	private String id ;
	private String profileId ;
	private boolean sortlisted;
	
	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public boolean isWarning() {
		return warning;
	}

	public void setWarning(boolean warning) {
		this.warning = warning;
	}

	public boolean isInformation() {
		return information;
	}

	public void setInformation(boolean information) {
		this.information = information;
	}


	public String getToken() {
		return token;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public void addFieldError(String field, String message){
		fieldErrors.add(new FieldError(field, message));
	}
	
	
	
	public String getNext() {
		return next;
	}



	public void setNext(String next) {
		this.next = next;
	}



	public boolean isError() {
		return error;
	}


	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	public void setError(boolean error) {
		this.error = error;
	}




	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}




	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}




	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}




	public boolean isSortlisted() {
		return sortlisted;
	}

	public void setSortlisted(boolean sortlisted) {
		this.sortlisted = sortlisted;
	}




	class FieldError {
		private String field ;
		private String fieldMessage ;
		
		public FieldError(String field, String message) {
			this.field = field ;
			this.fieldMessage = message ;
		}
		
		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public String getFieldMessage() {
			return fieldMessage;
		}
		public void setFieldMessage(String fieldMessage) {
			this.fieldMessage = fieldMessage;
		}
		
		
	}

}
