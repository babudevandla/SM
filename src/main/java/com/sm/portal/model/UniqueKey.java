package com.sm.portal.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name= "unique_key")  
public class UniqueKey implements java.io.Serializable{

	private Integer id;
	private Integer userId;
	private String uniqueProperty;
	private Integer uniqueValue;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "uniqueProperty")
	public String getUniqueProperty() {
		return uniqueProperty;
	}
	public void setUniqueProperty(String uniqueProperty) {
		this.uniqueProperty = uniqueProperty;
	}
	@Column(name = "uniqueValue")
	public Integer getUniqueValue() {
		return uniqueValue;
	}
	public void setUniqueValue(Integer uniqueValue) {
		this.uniqueValue = uniqueValue;
	}
	
	
}
