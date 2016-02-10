package com.mmventures.study.board.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmventures.study.core.domain.BoardContent;
import com.mmventures.study.core.domain.TableRelationInfo;

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
	 * List<BoardContent> list = sessionFactory.getCurrentSession()
	 * .createCriteria(BoardContent.class)
	 * .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	 */
	Session session = sessionFactory.getCurrentSession();

	Query selectQuery = session.createQuery("from BoardContent as content "
		+ "where content.commonField.isDelete = false "
		+ "order by content.commonField.createDate desc");
	
	selectQuery.setMaxResults(100);

	return (List<BoardContent>) selectQuery.list();
    }

    public final BoardContent selectBoardContent(final int boardId,
	    final int contentId) {
	Session session = sessionFactory.getCurrentSession();

	Query selectQuery = session
		.createQuery("from BoardContent as content "
			+ "where content.id = :contentId "
			+ "and content.commonField.isDelete = false")
		.setParameter("contentId", contentId);

	BoardContent content = (BoardContent) selectQuery.uniqueResult();

	return content;
    }

    public void insertContentFileMapping(TableRelationInfo relationInfo) {
	Session session = sessionFactory.getCurrentSession();
	session.save(relationInfo);
    }

    
    

}
