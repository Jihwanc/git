package com.mmventures.study.core.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Common Data Domain.
 * 
 * @author jihwan
 *
 */
@Entity
public class CommonData {
    @Id
    @GeneratedValue
    private int id;
    /** Data category. */
    private int category;
    /** Integer value. */
    private int intValue;
    /** String value. */
    private String varcharValue1;
    /** String value2. */
    private String varcharValue2;
    /** Common field. */
    @Embedded
    private CommonField commonField;

    /**
     * Default constructor.
     */
    public CommonData() {

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

    public int getIntValue() {
	return intValue;
    }

    public void setIntValue(int intValue) {
	this.intValue = intValue;
    }

    public String getVarcharValue1() {
	return varcharValue1;
    }

    public void setVarcharValue1(String varcharValue1) {
	this.varcharValue1 = varcharValue1;
    }

    public String getVarcharValue2() {
	return varcharValue2;
    }

    public void setVarcharValue2(String varcharValue2) {
	this.varcharValue2 = varcharValue2;
    }

    public CommonField getCommonField() {
	return commonField;
    }

    public void setCommonField(CommonField commonField) {
	this.commonField = commonField;
    }

    @Override
    public String toString() {
	return "CommonData [id=" + id + ", category=" + category + ", intValue="
		+ intValue + ", varcharValue1=" + varcharValue1
		+ ", varcharValue2=" + varcharValue2 + ", commonField="
		+ commonField + "]";
    }

}
