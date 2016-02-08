package com.mmventures.study.board.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmventures.study.core.domain.ContentComment;

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
			+ "order by comment.commonField.createDate asc")
		.setParameter("contentId", boardContentId);

	List<ContentComment> commentList = (List<ContentComment>) selectQuery.list();

	return commentList;
    }

}
