package com.mmventures.study.security.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmventures.study.core.domain.CommonData;
import com.mmventures.study.core.domain.TableRelationInfo;
import com.mmventures.study.core.domain.UserInfo;

public class SecurityUserInfoDao {
	
	/**
	 * Define SessionFactory
	 */
	private SessionFactory sessionFactory;

	/**
	 * return user by specific user email information.
	 * @param username String userId
	 * @return UserInfo instance
	 */
	//이것의 의미를 알자.(컴파일러에게 자바에서 발생하는 warning 무시해라~ 이 의미)
	@SuppressWarnings("unchecked")
	//In this method name, Username means "Id".
	//In the "userinfo" table "Id" is "email".
	public UserInfo findByUserName(final String username) {
		System.out.println("findByUserName");
		System.out.println("Username(email)"+username);

		if (!isUserNameNotNull(username)) {
			System.out.println("Username is null");
			return null;
		}
		
		//Id is condition, through this condition find user password.
//		if(this.sessionFactory ==null){
//			System.out.println("SessionFactory is null");
//		}else{
//			System.out.println("SessionFactory is not null");
//			
//		}
		
//		Session currentSession = getSessionFactory().openSession();
//		if(currentSession!=null) {
//			System.out.println("session is not null");
//		}else{
//			
//			System.out.println("session is null");
//		}
//		Query cquery = currentSession.createQuery("from UserInfo where email=?").setString(0, username);
////				setParameter(0, username);
//		
//		if(cquery !=null){
//			
//			System.out.println("cQuery is not null");
//		}else { 
//			System.out.println("cQuery is null");
//			
//		}
		List<UserInfo> users = new ArrayList<UserInfo>();
		Session session = getSessionFactory().openSession();
		try {
					
		users = session.createQuery(
				"from UserInfo where email=?").setParameter(0, username).list();

		} catch(HibernateException hex) {
			System.out.println("hibernate exception");	
		} finally {
			session.close();
		}

		if (users.size() > 0) {
			System.out.println("User id="+users.get(0).getEmail()+",pw ="+users.get(0).getPassword());
			return users.get(0);
		} else {
			System.out.println("User is null");
			return null;
		}

	}
	

	/**
	 * With userId and categoryNumber, find user authorities.
	 * @param tableRelationInfoCategoryInfo Integer type about category
	 * @param userId userId
	 * @return
	 */
	public List<TableRelationInfo> findAuthIdByUserId(
			final Integer tableRelationInfoCategoryInfo, final int userId) {
		System.out.println("findAuthorityByUserName");
		
		if (!isCategoryNumberNotNull(tableRelationInfoCategoryInfo)) {
			System.out.println("Username is null");
			return null;
		}
				
		System.out.println("createQuery");
		System.out.println("Category number="+ tableRelationInfoCategoryInfo+", user id ="+userId);
		//사용자 권한정보를 담고 있는 테이블에서 정보를 가져온다.
		//1.RelationTable에서 category가 1001번에 해당하는 결과 값을 가져온다.
		//2.그중에서 username과 일치하는 것 중에 authorities 만을 가져온다.		
		
		Session session = getSessionFactory().openSession();
		List<TableRelationInfo> userMapingAuthorityId = 
				new ArrayList<TableRelationInfo>();
		
		try {
			
			System.out.println("create TableRelationInfo Query");	
			Query query = session.
					createQuery("from TableRelationInfo where category = ? and pk2 = ?");
			query.setParameter(0, tableRelationInfoCategoryInfo);
			query.setParameter(1, userId);			
			userMapingAuthorityId = query.list();
			System.out.println("TableRelationInfo Query list");	

		} catch(HibernateException hex){
			System.out.println("hibernate exception");	
		} finally {
			session.close();
		}
		
		
		
		if(userMapingAuthorityId.size()>=1){
			System.out.println("catch userAuthoritie~!!!");			
		}
		
		return userMapingAuthorityId;
	}
	
	
	public CommonData getAuthNameWithAuthorityId(final Integer userAuthId) {
		// TODO Auto-generated method stub
		List<CommonData> commonData = new ArrayList<CommonData>();
		Session session = getSessionFactory().openSession();
		commonData = session.createQuery(
		"from CommonData where id= ? ").
		setParameter(0, userAuthId).list();
		session.close();
//		commonData = getSessionFactory().getCurrentSession().createQuery(
//				"from CommonData where id= ? ").
//				setParameter(0, userAuthId).list();

		if(commonData.size() > 0) {
			return commonData.get(0);
		} else {
			return null;
		}
	}
	
	public boolean isUserNameNotNull(final String userName){
		if(userName !=null){
			return true;
		}else{
			return false;
		}
	}
	public boolean isCategoryNumberNotNull(final Integer categoryNumber){
		if(categoryNumber !=null){
			return true;
		}else{
			return false;
		}
	}
	

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


}
