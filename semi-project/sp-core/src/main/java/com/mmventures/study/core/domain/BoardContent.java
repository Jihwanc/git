package com.mmventures.study.core.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Board Content Domain.
 * 
 * @author jihwan
 *
 */
@Entity
public class BoardContent {
    /** Board content id. */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    /** Board info id. */
    private int boardId;
    /** Category id. */
    private int categoryId;
    /** User id. */
    private int userId;
    /** Subject. */
    private String subject;
    /** Content. */
    private String content;
    /** Is notice content. */
    private boolean isNotice;
    /** Is secret content. */
    private boolean isSecret;
    /** Secret content password. */
    private String password;
    /** Link field. */
    private String link;
    /** Common field. */
    @Embedded
    private CommonField commonField;

    /**
     * Default constructor.
     */
    public BoardContent() {

    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getBoardId() {
	return boardId;
    }

    public void setBoardId(int boardId) {
	this.boardId = boardId;
    }

    public int getCategoryId() {
	return categoryId;
    }

    public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
    }

    public int getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
	this.userId = userId;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public boolean isNotice() {
	return isNotice;
    }

    public void setNotice(boolean isNotice) {
	this.isNotice = isNotice;
    }

    public boolean isSecret() {
	return isSecret;
    }

    public void setSecret(boolean isSecret) {
	this.isSecret = isSecret;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }
    
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public CommonField getCommonField() {
	return commonField;
    }

    public void setCommonField(CommonField commonField) {
	this.commonField = commonField;
    }

    @Override
    public String toString() {
	return "BoardContent [id=" + id + ", boardId=" + boardId
		+ ", categoryId=" + categoryId + ", userId=" + userId
		+ ", subject=" + subject + ", content=" + content
		+ ", isNotice=" + isNotice + ", isSecret=" + isSecret
		+ ", password=" + password + ", link=" + link + ", commonField="
		+ commonField + "]";
    }
}
