package com.mmventures.study.security.service;

import static com.mmventures.study.core.config.TableRelationInfoCategory.USER_AUTHORITY_MAPPING_INFORMATION;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.mmventures.study.core.domain.CommonData;
import com.mmventures.study.core.domain.TableRelationInfo;
import com.mmventures.study.security.dao.SecurityUserInfoDao;
/**
 * 
 * @author heechanglee
 *
 */
public class UserInfoDetailsService implements UserDetailsService {
	
	private SecurityUserInfoDao userInfoDao;
	
	public final SecurityUserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public final void setUserInfoDao(SecurityUserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	/**
	 * search user by userEmail.
	 */
//	@Transactional(readOnly=true)
	@Override
	public final UserDetails loadUserByUsername(final String username) 
			throws UsernameNotFoundException {
		
		System.out.println("loadUserByUsername in UserInfoDetailsService");
		//find userInfo
		com.mmventures.study.core.domain.UserInfo user =	
				userInfoDao.findByUserName(username);
		if (user != null) {
			System.out.println(user.getId() + "in UserInfoDetailsService");
		} else { 
			System.out.println("userId is null");
		}
		

		List<GrantedAuthority> authorities = 
				buildUserAuthority(loadUserAuthIdByUserId(user.getId()));
		
		 
		return buildUserForAuthentication(user, authorities);
	}
	
	
	/**
	 * Search auth name by user Id. 
	 * @param userId int user id.
	 * @return user having authorities
	 */
	public final List<String> loadUserAuthIdByUserId(final int userId) {
		
		List<TableRelationInfo> authUserMapTable =  
				new ArrayList<TableRelationInfo>();

		authUserMapTable = 
				userInfoDao.findAuthIdByUserId(
						USER_AUTHORITY_MAPPING_INFORMATION, userId);
		
		if (authUserMapTable != null) {
			System.out.println("authroty is not null");			
		} else {
			System.out.println("authroty is null");			
		}
		
		//이거를 리턴해야함.
		return loadUserAuthNameByAuthId(authUserMapTable);

	}

	/**
	 * return user having authorities.
	 * @param authUserMapTable TableRelationInfo 
	 * @return List<String> user having authorities
	 */
	public final List<String> loadUserAuthNameByAuthId(
			final List<TableRelationInfo> authUserMapTable) {
		
		List<String> userHavingAuthorities = new ArrayList<String>();
		
		for (TableRelationInfo authUserMap : authUserMapTable) {
			//CommonData에서 권한 정보를 불러들인다.
			//1.권한 사용자 매필 테이블에서 pk1을 불러들인다.
			//2.pk1를 통해 CommonData에서 권한(varchar1)을 불러들인다.
			String auth = userInfoDao.getAuthNameWithAuthorityId(
					authUserMap.getPk1()).getVarcharValue1();
			System.out.println("auth = "+auth);
			userHavingAuthorities.add(auth);
		}
		
		return userHavingAuthorities;
	}
	
	
	
	/**
	 * convert com.mmventures.study.core.domain.UserInfo to
	 * org.springframework.security.core.userdetails.User .
	 * @param user UserInfo type instance
	 * @param authorities user having authorities 
	 * @return User type instance
	 */
	private User buildUserForAuthentication(
			final com.mmventures.study.core.domain.UserInfo user,
			final List<GrantedAuthority> authorities) {
		
		System.out.println("buildUserForAuthentication in UserInfoDetailsService");
		System.out.println("user email="+user.getEmail()+", user pw="+ user.getPassword()+",user enable="+!user.getCommonField().isDelete());
		System.out.println("user authroties"+authorities);
		
		//!user.getCommonField().isDelete() means 
		//user account is not deleted and enabled status.
		//현재 계정 만료와 계정 삭제 구분이 없음(현재 동일 개념).
		return new User(user.getEmail(), user.getPassword(),
				!user.getCommonField().isDelete(), 
				true, true, true, authorities);
	}
	
	/**
	 * create user authority.
	 * @param userAuthorities user having authroties
	 * @return List<GrantedAuthority> 
	 */
	private List<GrantedAuthority> buildUserAuthority(
			final List<String> userAuthorities) {
		
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (String userAuth : userAuthorities) {
			
			System.out.println("user auth="+userAuth);
			
			setAuths.add(new SimpleGrantedAuthority(userAuth));
		}

		List<GrantedAuthority> result = 
				new ArrayList<GrantedAuthority>(setAuths);

		return result;
	}

}
