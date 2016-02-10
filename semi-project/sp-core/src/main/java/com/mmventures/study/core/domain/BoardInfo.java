package com.mmventures.study.core.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Board info Domain.
 * 
 * @author jihwan
 *
 */
@Entity
public class BoardInfo {
    /** Board id. */
    @Id
    @GeneratedValue
    private int id;
    /** Board title. */
    private String title;
    /** Board url. */
    private String url;
    /** Enable content category. */
    private boolean categoryEnable;
    /** Enable show content list under content view. */
    private boolean listViewEnable;
    /** Upload file size limit. */
    private int fileVolumeLimit;
    /** Enable auto link field. */
    private boolean autoLinkEnable;
    /** Enable secret content. */
    private boolean secretEnable;
    /** Common field. */
    @Embedded
    private CommonField commonField;

    /**
     * Category list. intValue: order varcharValue1: CategoryName
     * 
     * @OneToMany private List<CommonData> categoryList;
     */

    /**
     * Default constructor.
     */
    public BoardInfo() {

    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public boolean isCategoryEnable() {
	return categoryEnable;
    }

    public void setCategoryEnable(boolean categoryEnable) {
	this.categoryEnable = categoryEnable;
    }

    public boolean isListViewEnable() {
	return listViewEnable;
    }

    public void setListViewEnable(boolean listViewEnable) {
	this.listViewEnable = listViewEnable;
    }

    public int getFileVolumeLimit() {
	return fileVolumeLimit;
    }

    public void setFileVolumeLimit(int fileVolumeLimit) {
	this.fileVolumeLimit = fileVolumeLimit;
    }

    public boolean isAutoLinkEnable() {
	return autoLinkEnable;
    }

    public void setAutoLinkEnable(boolean autoLinkEnable) {
	this.autoLinkEnable = autoLinkEnable;
    }

    public boolean isSecretEnable() {
	return secretEnable;
    }

    public void setSecretEnable(boolean secretEnable) {
	this.secretEnable = secretEnable;
    }

    public CommonField getCommonField() {
	return commonField;
    }

    public void setCommonField(CommonField commonField) {
	this.commonField = commonField;
    }

    @Override
    public String toString() {
	return "BoardInfo [id=" + id + ", title=" + title + ", url=" + url
		+ ", categoryEnable=" + categoryEnable + ", listViewEnable="
		+ listViewEnable + ", fileVolumeLimit=" + fileVolumeLimit
		+ ", autoLinkEnable=" + autoLinkEnable + ", secretEnable="
		+ secretEnable + ", commonField=" + commonField + "]";
    }

}
