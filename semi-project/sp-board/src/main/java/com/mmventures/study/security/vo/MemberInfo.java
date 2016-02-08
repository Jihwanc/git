//package com.mmventures.study.security.vo;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Set;
//import java.util.SortedSet;
//import java.util.TreeSet;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.SpringSecurityCoreVersion;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.util.Assert;
//
//import com.mmventures.study.core.domain.UserInfo;
///**
// * member information.
// * @author heechanglee
// *
// */
//public class MemberInfo  implements UserDetails {
//
//	private static final long serialVersionUID = -4086869747130410600L;
//    
//    /** Set of authorities.*/
//	private Set<GrantedAuthority> authorities;
//	/**contains infomation of all user.*/
//	private UserInfo userInfo;
//	
//	private MemberInfo() { }
//	
//	public MemberInfo(final UserInfo authenticationUserInfo, 
//			final Collection <? extends GrantedAuthority> userAuthorities) {
//	    
//		this.userInfo = authenticationUserInfo;
//		this.authorities = Collections.unmodifiableSet(
//				sortAuthorities(userAuthorities));
//	}
//	
//
//	
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return authorities;
//	}
//	
//	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
//	}
//	
//
//
//	/**
//	 * user account is not expired.
//	 */
//	@Override
//	public final boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	/**
//	 * user account is not locked.
//	 */
//	@Override
//	public  final boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	//why this value return true? Reason???
//	/**
//	 * user password is not expired.
//	 */
//	@Override
//	public final boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	
//	//why this value return true? Reason???
//	@Override
//	public final boolean isEnabled() {
//		// TODO Auto-generated method stub
//
//		
//		return true;
//	}
//
//	
//	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
//        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
//        // Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
//        SortedSet<GrantedAuthority> sortedAuthorities =
//            new TreeSet<GrantedAuthority>(new AuthorityComparator());
//
//        for (GrantedAuthority grantedAuthority : authorities) {
//            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
//
//            //sortAuthorities through treeset.
//            sortedAuthorities.add(grantedAuthority);
//        }
//
//        return sortedAuthorities;
//    }
//	
//	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
//        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
//
//        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
//            // Neither should ever be null as each entry is checked before adding it to the set.
//            // If the authority is null, it is a custom authority and should precede others.
//            if (g2.getAuthority() == null) {
//                return -1;
//            }
//
//            if (g1.getAuthority() == null) {
//                return 1;
//            }
//
//            return g1.getAuthority().compareTo(g2.getAuthority());
//        }
//    }
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return this.userInfo.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return this.userInfo.getEmail();
//	}
//}
