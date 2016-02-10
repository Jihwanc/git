package com.mmventures.study.core.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Menu info domain.
 * 
 * @author jihwan
 *
 */
@Entity
public class MenuInfo {
    /** Menu id. */
    @Id
    @GeneratedValue
    private int id;
    /** Menu name. */
    private String menuName;
    /** Menu url link. */
    private String url;
    /** Menu order. */
    private int menuOrder;
    /** Common field. */
    @Embedded
    private CommonField commonField;

    /**
     * Default constructor.
     */
    public MenuInfo() {

    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getMenuName() {
	return menuName;
    }

    public void setMenuName(String menuName) {
	this.menuName = menuName;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }
        
    public int getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(int menuOrder) {
        this.menuOrder = menuOrder;
    }

    public CommonField getCommonField() {
	return commonField;
    }
    
    public void setCommonField(CommonField commonField) {
	this.commonField = commonField;
    }

    @Override
    public String toString() {
	return "MenuInfo [id=" + id + ", menuName=" + menuName + ", url=" + url
		+ ", menuOrder=" + menuOrder + ", commonField=" + commonField
		+ "]";
    }

}