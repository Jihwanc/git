package com.mmventures.study.core.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Table relation info.
 * 
 * @author jihwan
 *
 */
@Entity
public class TableRelationInfo {
    /** Id. */
    @Id
    @GeneratedValue
    private int id;
    /** Category. */
    private int category;
    /** Primary key 1. */
    private int pk1;
    /** Primary key 2. */
    private int pk2;
    /** Common field. */
    @Embedded
    private CommonField commonField;

    /**
     * Default constructor.
     */
    public TableRelationInfo() {

    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getCategory() {
	return category;
    }

    public void setCategory(int category) {
	this.category = category;
    }

    public int getPk1() {
	return pk1;
    }

    public void setPk1(int pk1) {
	this.pk1 = pk1;
    }

    public int getPk2() {
	return pk2;
    }

    public void setPk2(int pk2) {
	this.pk2 = pk2;
    }
    
    public CommonField getCommonField() {
	return commonField;
    }
    
    public void setCommonField(CommonField commonField) {
	this.commonField = commonField;
    }

    @Override
    public String toString() {
	return "TableRelationInfo [id=" + id + ", category=" + category
		+ ", pk1=" + pk1 + ", pk2=" + pk2 + ", commonField="
		+ commonField + "]";
    }

}
