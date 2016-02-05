package com.mmventures.study.security.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * 
 * @author heechanglee
 *
 */
public class MemberInfo implements UserDetails {
	
	private static final long serialVersionUID = -4086869747130410600L;
	
	/**
	 * user Id. 
	 */
	private String id;
	
	/**
	 * user password. 
	 */
	private String password;
	
	/**
	 *Added User information. 
	 */
	private String name;
	
	/**
	 * Set of authorities.
	 */
	private Set<GrantedAuthority> authorities;

	/**
	 * Initailize memberInfo instance.
	 * @param userId String type userId
	 * @param userPassword String type userPassword
	 * @param addedInfo String type user added information
	 * @param userAuthorities Collection type user having authroties 
	 */
	public MemberInfo(final String userId, final String  userPassword,
			final String addedInfo, 
			final Collection<? extends GrantedAuthority> userAuthorities) {
		this.id = userId;
		this.password = userPassword;
		this.name = addedInfo;
		this.authorities = Collections.unmodifiableSet(
				sortAuthorities(userAuthorities));
	}

	/**
	 * getUserId.
	 * @return userId String type user id
	 */
	public final String getId() {
		return id;
	}
	
	/**
	 * set userId.
	 * @param userId String user id
	 */
	public final void setId(final String userId) {
		this.id = userId;
	}

	/**
	 * get user added information.
	 * @return String added info.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * set user added information.
	 * @param name String user added info.
	 */
	public final void setName(String name) {
		this.name = name;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {

		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		// Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
		SortedSet<GrantedAuthority> sortedAuthorities =
				new TreeSet<GrantedAuthority>(new AuthorityComparator());

		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}

	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			// Neither should ever be null as each entry is checked before adding it to the set.
			// If the authority is null, it is a custom authority and should precede others.
			if (g2.getAuthority() == null) {
				return -1;
			}

			if (g1.getAuthority() == null) {
				return 1;
			}

			return g1.getAuthority().compareTo(g2.getAuthority());
		}
	}
}
