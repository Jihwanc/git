package com.mmventures.study.board.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmventures.study.core.domain.FileInfo;

@Repository("fileInfoDao")
public class FileInfoDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public List<FileInfo> selectContentFileList(int contentId) {
	Session session = sessionFactory.getCurrentSession();

	Query relationQuery = session
		.createQuery(
			"select info.pk2 from TableRelationInfo as info where info.pk1 = :contentId")
		.setParameter("contentId", contentId);

	List<Integer> fileIdList = relationQuery.list();
	
	if(fileIdList == null || fileIdList.size() == 0) {
	    return null;
	}

	Query categoryQuery = session
		.createQuery(
			"from CommonData as category where category.id IN (:fileIdList)")
		.setParameterList("fileIdList", fileIdList);

	List<FileInfo> fileInfoList = categoryQuery.list();

	return fileInfoList;	
    }
}
