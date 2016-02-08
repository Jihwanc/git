package com.mmventures.study.board.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmventures.study.core.domain.BoardContent;
import com.mmventures.study.core.domain.CommonData;

/**
 * BoardContent dao.
 * 
 * @author Jihwan
 *
 */
@Repository("boardContentDao")
public class BoardContentDao {
    /** Session factory. */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Default constructor.
     */
    public BoardContentDao() {

    }

    /**
     * Insert content.
     * 
     * @param boardContent
     *            boardContent
     */
    public final void insertBoardContent(final BoardContent boardContent) {
	Session session = sessionFactory.openSession();
	session.save(boardContent);
	session.close();
    }

    public final List<BoardContent> selectBoardContentList(final int boardId) {
	/*
	List<BoardContent> list = sessionFactory.getCurrentSession()
		.createCriteria(BoardContent.class)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	 */

	Criteria criteria = sessionFactory.getCurrentSession()
		.createCriteria(BoardContent.class);
	
	criteria.setMaxResults(20);
//	criteria.addOrder(Order.desc("createDate"));
	criteria.addOrder(Order.desc("commonField.createDate"));
	
	List<BoardContent> list = criteria.list();

	return list;
    }
    
    public final BoardContent selectBoardContent(final int boardId, final int contentId) {
	Session session = sessionFactory.getCurrentSession();

	Query selectQuery = session
		.createQuery(
			"from BoardContent as content where content.id = :contentId")
		.setParameter("contentId", contentId);

	BoardContent content = (BoardContent) selectQuery.uniqueResult();
	
	return content;
    }
    
    
    

}
