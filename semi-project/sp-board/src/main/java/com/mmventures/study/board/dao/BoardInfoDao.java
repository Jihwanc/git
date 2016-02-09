package com.mmventures.study.board.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmventures.study.core.domain.BoardInfo;
import com.mmventures.study.core.domain.CommonData;

/**
 * Board Info DAO.
 * 
 * @author Jihwan
 *
 */
@Repository("boardInfoDao")
public class BoardInfoDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * DefaultConstructor.
     */
    public BoardInfoDao() {

    }

    public void insertBoardInfo(BoardInfo boardInfo) {
	Session session = sessionFactory.getCurrentSession();
	session.save(boardInfo);
    }

    public BoardInfo getBoardInfo(final int boardId) {
	Session session = sessionFactory.getCurrentSession();

	BoardInfo boardInfo = (BoardInfo) session
		.createQuery(
			"from BoardInfo as boardInfo where boardInfo.id = :boardId")
		.setInteger("boardId", boardId)
		.uniqueResult();
	
	return boardInfo;
    }

    public List<CommonData> getBoardCategoryList(int boardId) {
	Session session = sessionFactory.getCurrentSession();

	Query relationQuery = session
		.createQuery(
			"select info.pk2 from TableRelationInfo as info where info.pk1 = :boardId")
		.setParameter("boardId", boardId);

	List<Integer> categoryRelationList = relationQuery.list();

	Query categoryQuery = session
		.createQuery(
			"from CommonData as category where category.id IN (:relationList)")
		.setParameterList("relationList", categoryRelationList);

	List<CommonData> categoryList = categoryQuery.list();

	return categoryList;
    }

}
