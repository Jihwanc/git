package com.mmventures.study.core.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Comment domain.
 * 
 * @author jihwan
 *
 */
@Entity
public class ContentComment {
    /** Comment id. */
    @Id
    @GeneratedValue
    private int id;

    /** Content id. */
    private int contentId;

    /** Writer's User id. */
    private int userId;

    /** Comment. */
    private String comment;

    /** Common field. */
    @Embedded
    private CommonField commonField;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getContentId() {
	return contentId;
    }

    public void setContentId(int contentId) {
	this.contentId = contentId;
    }

    public int getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
	this.userId = userId;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public CommonField getCommonField() {
	return commonField;
    }

    public void setCommonField(CommonField commonField) {
	this.commonField = commonField;
    }

    @Override
    public String toString() {
	return "ContentComment [id=" + id + ", contentId=" + contentId
		+ ", userId=" + userId + ", comment=" + comment
		+ ", commonField=" + commonField + "]";
    }

}
