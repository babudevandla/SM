package com.sm.portal.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "users")
public class Users implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String firstname;
	private String profileImage;
	private String modifiedImage;
	private String about;
	private String lastname;
	private String email;
	private String userName;
	private String password;
	private String userRole;
	private String changebleRole;
	private String website;
	private boolean enabled;
	private boolean facbookUser;
	private Date createdDate;
	private String phoneNumber;
	private String address1;
	private String address2;
	private String city;
	private String zipCode;
	private String country;
	private String state;
	private String dateofbirth;
	private String gender;
	private MultipartFile profilePhoto;
	private String dynamic_access_code;
	private String mobile_no;
	private boolean dynamic_status;
	
	
	
	private List<Role> roles = new ArrayList<Role>(0);

	public Users() {
	}

	public Users(Integer userId) {
		super();
		this.userId = userId;
	}

	public Users(String firstname, String lastname, String email,
			String password, boolean enabled, String role, Date createdDate,
			List<Role> roles) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.createdDate = createdDate;
		this.roles = roles;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "firstname")
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "lastname")
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "email", unique = true, length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled")
	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 0)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "profileImage")
	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	@Column(name = "about")
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Column(name = "userRole")
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Column(name = "changebleRole")
	public String getChangebleRole() {
		return changebleRole;
	}

	public void setChangebleRole(String changebleRole) {
		this.changebleRole = changebleRole;
	}

	@Column(name = "phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "website")
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "facbookUser")
	public boolean isFacbookUser() {
		return facbookUser;
	}

	public void setFacbookUser(boolean facbookUser) {
		this.facbookUser = facbookUser;
	}

	@Column(name = "userName", unique = true, length = 200)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "address1")
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name = "address2")
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "zipCode")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "modifiedImage")
	public String getModifiedImage() {
		return modifiedImage;
	}

	public void setModifiedImage(String modifiedImage) {
		this.modifiedImage = modifiedImage;
	}

	@Column(name = "dateofbirth")
	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	@Transient
	public MultipartFile getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(MultipartFile profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })
	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Column(name = "dynamic_access_code")
	public String getDynamic_access_code() {
		return dynamic_access_code;
	}

	public void setDynamic_access_code(String dynamic_access_code) {
		this.dynamic_access_code = dynamic_access_code;
	}
	@Column(name = "mobile_no")
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	@Column(name = "dynamic_status")
	public boolean isDynamic_status() {
		return dynamic_status;
	}

	public void setDynamic_status(boolean dynamic_status) {
		this.dynamic_status = dynamic_status;
	}
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

}
