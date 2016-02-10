package com.mmventures.study.core.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User info domain.
 * 
 * @author jihwan
 *
 */
@Entity
public class UserInfo {
    /** User info id. */
    @Id
    @GeneratedValue
    private int id;
    /** User e-mail address. */
    private String email;
    /** User nickname. */
    private String nickName;
    /** User name. */
    private String name;
    /** User password. */
    private String password;
    /** User address. */
    private String address;
    /** User phone number. */
    private String phoneNumber;
    /** Common field. */
    @Embedded
    private CommonField commonField;

    /**
     * Default constructor.
     */
    public UserInfo() {

    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getNickName() {
	return nickName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }
    
    public CommonField getCommonField() {
	return commonField;
    }
    
    public void setCommonField(CommonField commonField) {
	this.commonField = commonField;
    }

    @Override
    public String toString() {
	return "UserInfo [id=" + id + ", email=" + email + ", nickName="
		+ nickName + ", name=" + name + ", password=" + password
		+ ", address=" + address + ", phoneNumber=" + phoneNumber
		+ ", commonField=" + commonField + "]";
    }

}