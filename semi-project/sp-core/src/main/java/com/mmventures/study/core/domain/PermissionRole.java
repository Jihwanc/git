package com.mmventures.study.core.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * PermissionRole domain.
 * 
 * @author jihwan
 *
 */
@Entity
public class PermissionRole {
    /** Permission id. */
    @Id
    private int id;
    /** Permission name. */
    private String permissionName;
    /** Permission url. */
    private String permissionUrl;
    /**
     * Permission. 1: read 2: write 4: create 8: delete 16: administer (same as
     * base permission)
     */
    private int permission;
    /** Description. */
    private String description;
    /** Common field. */
    @Embedded
    private CommonField commonField;

    /**
     * Default constructor.
     */
    public PermissionRole() {

    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getPermissionName() {
	return permissionName;
    }

    public void setPermissionName(String permissionName) {
	this.permissionName = permissionName;
    }

    public String getPermissionUrl() {
	return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
	this.permissionUrl = permissionUrl;
    }

    public int getPermission() {
	return permission;
    }

    public void setPermission(int permission) {
	this.permission = permission;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
    
    public CommonField getCommonField() {
	return commonField;
    }
    
    public void setCommonField(CommonField commonField) {
	this.commonField = commonField;
    }

    @Override
    public String toString() {
	return "PermissionRole [id=" + id + ", permissionName=" + permissionName
		+ ", permissionUrl=" + permissionUrl + ", permission="
		+ permission + ", description=" + description + ", commonField="
		+ commonField + "]";
    }

}
