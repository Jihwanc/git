package com.mmventures.study.core.domain;

import java.util.Date;

import javax.persistence.Embeddable;

/**
 * Common field.
 * 
 * @author jihwan
 *
 */
@Embeddable
public class CommonField {
    /** Create date. */
    private Date createDate;
    /** Update date. */
    private Date updateDate;
    /** Last modifier. */
    private String modifier;
    /** Is delete. */
    private boolean isDelete;

    /**
     * Default constructor.
     */
    public CommonField() {

    }

    public Date getCreateDate() {
	return createDate;
    }

    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
    }

    public Date getUpdateDate() {
	return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
    }

    public String getModifier() {
	return modifier;
    }

    public void setModifier(String modifier) {
	this.modifier = modifier;
    }

    public boolean isDelete() {
	return isDelete;
    }

    public void setDelete(boolean isDelete) {
	this.isDelete = isDelete;
    }

    @Override
    public String toString() {
	return "CommonField [createDate=" + createDate + ", updateDate="
		+ updateDate + ", modifier=" + modifier + ", isDelete="
		+ isDelete + "]";
    }

}
