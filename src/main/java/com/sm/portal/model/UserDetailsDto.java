package com.sm.portal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDetailsDto
{
  private Integer userId;
  private String firstname;
  private String lastname;
  private String gender;
  private Boolean enabled;
  private Date createdDate;
  private String city;
  private String about;
  private String email;
  private String phoneNumber;
  private String address1;
  private String address2;
  private String zipCode;
  private String country;
  private String state;
  private String profileImage;
  private String modifiedImage;
  private String website;
  private String birthday_date;
  private String birthday_month;
  private String birthday_year;
  private String mobile_no;
  private String study_cource;
  private String university;
  private Integer study_from;
  private Integer study_to;
  private String study_description;
  private String companyname;
  private String designation;
  private Integer work_from;
  private Integer work_to;
  private String work_city;
  private String work_description;
  List<String> interests = new ArrayList();
  private boolean account_enable;
  private boolean send_notifications;
  private boolean send_messages;
  private boolean enable_tagging;
  
  public UserDetailsDto() {}
  
  public Integer getUserId()
  {
    return userId;
  }
  
  public void setUserId(Integer userId) { this.userId = userId; }
  
  public String getFirstname() {
    return firstname;
  }
  
  public void setFirstname(String firstname) { this.firstname = firstname; }
  
  public String getLastname() {
    return lastname;
  }
  
  public void setLastname(String lastname) { this.lastname = lastname; }
  
  public String getGender() {
    return gender;
  }
  
  public void setGender(String gender) { this.gender = gender; }
  
  public Boolean getEnabled() {
    return enabled;
  }
  
  public void setEnabled(Boolean enabled) { this.enabled = enabled; }
  
  public Date getCreatedDate() {
    return createdDate;
  }
  
  public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
  
  public String getCity() {
    return city;
  }
  
  public void setCity(String city) { this.city = city; }
  
  public String getAbout() {
    return about;
  }
  
  public void setAbout(String about) { this.about = about; }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) { this.email = email; }
  
  public String getPhoneNumber() {
    return phoneNumber;
  }
  
  public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
  
  public String getAddress1() {
    return address1;
  }
  
  public void setAddress1(String address1) { this.address1 = address1; }
  
  public String getAddress2() {
    return address2;
  }
  
  public void setAddress2(String address2) { this.address2 = address2; }
  
  public String getZipCode() {
    return zipCode;
  }
  
  public void setZipCode(String zipCode) { this.zipCode = zipCode; }
  
  public String getCountry() {
    return country;
  }
  
  public void setCountry(String country) { this.country = country; }
  
  public String getState() {
    return state;
  }
  
  public void setState(String state) { this.state = state; }
  
  public String getProfileImage() {
    return profileImage;
  }
  
  public void setProfileImage(String profileImage) { this.profileImage = profileImage; }
  
  public String getModifiedImage() {
    return modifiedImage;
  }
  
  public void setModifiedImage(String modifiedImage) { this.modifiedImage = modifiedImage; }
  
  public String getWebsite() {
    return website;
  }
  
  public void setWebsite(String website) { this.website = website; }
  
  public String getBirthday_date() {
    return birthday_date;
  }
  
  public void setBirthday_date(String birthday_date) { this.birthday_date = birthday_date; }
  
  public String getBirthday_month() {
    return birthday_month;
  }
  
  public void setBirthday_month(String birthday_month) { this.birthday_month = birthday_month; }
  
  public String getBirthday_year() {
    return birthday_year;
  }
  
  public void setBirthday_year(String birthday_year) { this.birthday_year = birthday_year; }
  
  public String getMobile_no() {
    return mobile_no;
  }
  
  public void setMobile_no(String mobile_no) { this.mobile_no = mobile_no; }
  
  public String getStudy_cource() {
    return study_cource;
  }
  
  public void setStudy_cource(String study_cource) { this.study_cource = study_cource; }
  
  public String getUniversity() {
    return university;
  }
  
  public void setUniversity(String university) { this.university = university; }
  
  public Integer getStudy_from() {
    return study_from;
  }
  
  public void setStudy_from(Integer study_from) { this.study_from = study_from; }
  
  public Integer getStudy_to() {
    return study_to;
  }
  
  public void setStudy_to(Integer study_to) { this.study_to = study_to; }
  
  public String getStudy_description() {
    return study_description;
  }
  
  public void setStudy_description(String study_description) { this.study_description = study_description; }
  
  public String getCompanyname() {
    return companyname;
  }
  
  public void setCompanyname(String companyname) { this.companyname = companyname; }
  
  public String getDesignation() {
    return designation;
  }
  
  public void setDesignation(String designation) { this.designation = designation; }
  
  public Integer getWork_from() {
    return work_from;
  }
  
  public void setWork_from(Integer work_from) { this.work_from = work_from; }
  
  public Integer getWork_to() {
    return work_to;
  }
  
  public void setWork_to(Integer work_to) { this.work_to = work_to; }
  
  public String getWork_city() {
    return work_city;
  }
  
  public void setWork_city(String work_city) { this.work_city = work_city; }
  
  public String getWork_description() {
    return work_description;
  }
  
  public void setWork_description(String work_description) { this.work_description = work_description; }
  
  public List<String> getInterests() {
    return interests;
  }
  
  public void setInterests(List<String> interests) { this.interests = interests; }
  
  public boolean isAccount_enable() {
    return account_enable;
  }
  
  public void setAccount_enable(boolean account_enable) { this.account_enable = account_enable; }
  
  public boolean isSend_notifications() {
    return send_notifications;
  }
  
  public void setSend_notifications(boolean send_notifications) { this.send_notifications = send_notifications; }
  
  public boolean isSend_messages() {
    return send_messages;
  }
  
  public void setSend_messages(boolean send_messages) { this.send_messages = send_messages; }
  
  public boolean isEnable_tagging() {
    return enable_tagging;
  }
  
  public void setEnable_tagging(boolean enable_tagging) { this.enable_tagging = enable_tagging; }
}
