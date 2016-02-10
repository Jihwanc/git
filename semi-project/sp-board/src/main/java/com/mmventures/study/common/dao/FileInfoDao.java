package com.mmventures.study.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmventures.study.core.domain.FileInfo;

/**
 * FileInfo dao.
 * 
 * @author jihwan
 *
 */
@Repository("fileInfoDao")
public class FileInfoDao {
    /** SessionFactory. */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Select content fileList.
     * 
     * @param contentId boardContentId
     * @return fileList
     */
    @SuppressWarnings("unchecked")
    public final List<FileInfo> selectContentFileList(final int contentId) {
	Session session = sessionFactory.getCurrentSession();

	// Get file ids
	Query relationQuery = session
		.createQuery(
			"select info.pk2 from TableRelationInfo as info "
			+ "where info.category = 1004 "
			+ "and info.pk1 = :contentId "
			+ "and info.commonField.isDelete = false")
		.setParameter("contentId", contentId);

	List<Integer> fileIdList = relationQuery.list();

	if (fileIdList == null || fileIdList.size() == 0) {
	    return null;
	}

	// Get fileInfo
	Query categoryQuery = session
		.createQuery(
			"from FileInfo as fileInfo "
			+ "where fileInfo.commonField.isDelete = false "
			+ "and fileInfo.id IN (:fileIdList)")
		.setParameterList("fileIdList", fileIdList);

	List<FileInfo> fileInfoList = categoryQuery.list();

	return fileInfoList;
    }
    
    /**
     * FileInfo insert.
     * @param fileInfo uploaded FileInfo
     */
    public final void insertFileInfo(final FileInfo fileInfo) {
	Session session = sessionFactory.getCurrentSession();
	session.save(fileInfo);
    }
}
