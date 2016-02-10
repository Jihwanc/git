package com.mmventures.study.board.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmventures.study.core.domain.CommonData;
import com.mmventures.study.core.domain.ContentComment;
import com.mmventures.study.core.domain.TableRelationInfo;

@Repository("contentCommentDao")
public class ContentCommentDao {
    /** Session factory. */
    @Autowired
    private SessionFactory sessionFactory;

    public void insertComment(ContentComment comment) {
	Session session = sessionFactory.getCurrentSession();
	session.save(comment);
    }

    public List<ContentComment> selectCommentList(final int boardContentId) {
	Session session = sessionFactory.getCurrentSession();

	Query selectQuery = session
		.createQuery("from ContentComment as comment "
			+ "where comment.contentId = :contentId "
			+ "and comment.commonField.isDelete = false "
			+ "order by comment.commonField.createDate asc")
		.setParameter("contentId", boardContentId);

	List<ContentComment> commentList = (List<ContentComment>) selectQuery
		.list();

	return commentList;
    }

    public void insertCommentRelation(TableRelationInfo commentRelationInfo) {
	Session session = sessionFactory.getCurrentSession();
	session.save(commentRelationInfo);
    }

    public void getCommentRelation() {

    }

    public ContentComment selectComment(final Integer commentId) {
	Session session = sessionFactory.getCurrentSession();

	return (ContentComment) session.createQuery(
		"from ContentComment as comment "
		+ "where comment.id = :commentId "
		+ "and comment.commonField.isDelete = false")
		.setParameter("commentId", commentId)
		.uniqueResult();
    }

    public void saveOrUpdateComment(ContentComment comment) {
	Session session = sessionFactory.getCurrentSession();
	session.saveOrUpdate(comment);
    }
}
