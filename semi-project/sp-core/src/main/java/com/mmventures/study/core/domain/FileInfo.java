package com.mmventures.study.core.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * File info domain.
 * 
 * @author jihwan
 *
 */
@Entity
public class FileInfo {
    /** File info id. */
    @Id
    @GeneratedValue
    private int id;
    /** File name. */
    private String fileName;
    /** File saved url. */
    private String saveUrl;
    /** File download count. */
    private int downloadCount;
    /** File size. */
    private int fileSize;
    /** Common field. */
    @Embedded
    private CommonField commonField;

    /**
     * Default constructor.
     */
    public FileInfo() {

    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getFileName() {
	return fileName;
    }

    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    public String getSaveUrl() {
	return saveUrl;
    }

    public void setSaveUrl(String saveUrl) {
	this.saveUrl = saveUrl;
    }

    public int getDownloadCount() {
	return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
	this.downloadCount = downloadCount;
    }

    public int getFileSize() {
	return fileSize;
    }

    public void setFileSize(int fileSize) {
	this.fileSize = fileSize;
    }

    public CommonField getCommonField() {
	return commonField;
    }

    public void setCommonField(CommonField commonField) {
	this.commonField = commonField;
    }

    @Override
    public String toString() {
	return "FileInfo [id=" + id + ", fileName=" + fileName + ", saveUrl="
		+ saveUrl + ", downloadCount=" + downloadCount + ", fileSize="
		+ fileSize + ", commonField=" + commonField + "]";
    }

}
