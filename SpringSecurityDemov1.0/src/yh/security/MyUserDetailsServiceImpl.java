package yh.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import yh.dao.UserDao;
import yh.model.Privilege;
import yh.model.Role;
import yh.model.User;

//两个方法,其中一个调用另外一个
public class MyUserDetailsServiceImpl implements UserDetailsService{

	private  UserDao userDao; 



	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDetails loadUserByUsername(String username)
		throws UsernameNotFoundException {
		System.out.println("MyUserDetailsServiceImpl"+"-------"+"loadUserByUsername");
		System.out.println("username is " + username); 
		User user = this.userDao.findByName(username); 
		if(user == null) { 
		throw new UsernameNotFoundException(username); 
		}
		System.out.println("=====user====="+user.toString());
		//获取用户的权限
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user); 
		
		boolean enables = true; 
		boolean accountNonExpired = true; 
		boolean credentialsNonExpired = true; 
		boolean accountNonLocked = true; 
		user.setEnables(enables);
		user.setAccountNonExpired(accountNonExpired);
		user.setCredentialsNonExpired(credentialsNonExpired);
		user.setAccountNonLocked(accountNonLocked);
		user.setAuthorities((Set<GrantedAuthority>)grantedAuths);
		System.out.println(user.getAuthorities());
		return user;
		} 
		
		//取得用户的权限：user-->role-->privilege
		private Set<GrantedAuthority> obtionGrantedAuthorities(User user) { 
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>(); 
		Set<Role> roles = user.getRoles(); 
		System.out.println("==role=="+roles);
		
		for(Role role : roles) { 
		Set<Privilege> tempPri = role.getPrivileges();
		for(Privilege pri : tempPri) { 
			System.out.println(pri.getName());
		authSet.add(new GrantedAuthorityImpl(pri.getName())); 
		  } 
		} 
		return authSet; 
		} 

	}
