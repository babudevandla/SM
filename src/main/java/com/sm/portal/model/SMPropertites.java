package com.sm.portal.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sm_properties")
public class SMPropertites implements Serializable{
	
	private static final long serialVersionUID = -510621535905550367L;
	
	private int propertyid;
	private String propkey; 
	private String propvalue;
	private String datatype;
	private int prop_categoryid;
	private String comments;
	private Date createdon;
	private String updatedby;
	private Date updatedon;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "propertyid", nullable = false)
	public int getPropertyid() {
		return propertyid;
	}
	public void setPropertyid(int propertyid) {
		this.propertyid = propertyid;
	}
	@Column(name = "propkey",unique=true)
	public String getPropkey() {
		return propkey;
	}
	public void setPropkey(String propkey) {
		this.propkey = propkey;
	}
	@Column(name = "propvalue")
	public String getPropvalue() {
		return propvalue;
	}
	public void setPropvalue(String propvalue) {
		this.propvalue = propvalue;
	}
	
	@Column(name = "datatype")
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	
	@Column(name = "prop_categoryid")
	public int getProp_categoryid() {
		return prop_categoryid;
	}
	public void setProp_categoryid(int prop_categoryid) {
		this.prop_categoryid = prop_categoryid;
	}
	
	@Column(name = "comments")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Column(name = "createdon")
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	
	@Column(name = "updatedby")
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	
	@Column(name = "updatedon")
	public Date getUpdatedon() {
		return updatedon;
	}
	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}
	public String toString() {
		return "CaPropertites [propertyid=" + propertyid + ", propkey=" + propkey
				+ ", propvalue=" + propvalue + ", datatype=" + datatype
				+ ", prop_categoryid=" + prop_categoryid + ", comments =" +comments +"]";
	}
	

}
