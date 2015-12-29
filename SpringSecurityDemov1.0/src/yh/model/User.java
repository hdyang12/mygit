package yh.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 用户
 * 
 * @author yh
 * 
 */
public class User implements UserDetails{
	private Long id;
	private Set<Role> roles = new HashSet<Role>();

	private  String password;
	private  String username;
	private  Set<GrantedAuthority> authorities;
	private  boolean enables;
	private  boolean accountNonExpired;
	private  boolean accountNonLocked;
	private  boolean credentialsNonExpired;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User(String username, String password, boolean enables,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<GrantedAuthority> grantedAuths) {
		this.username=username;
		this.password=password;
		this.enables=enables;
		this.accountNonExpired=accountNonExpired;
		this.credentialsNonExpired=credentialsNonExpired;
		this.accountNonLocked=accountNonLocked;
		this.authorities=(Set<GrantedAuthority>)grantedAuths;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Set<Role> getRoles() {
		return roles;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}



	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}



	public boolean isEnables() {
		return enables;
	}



	public void setEnables(boolean enables) {
		this.enables = enables;
	}



	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}



	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}



	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}



	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}



	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}



	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}



	public boolean isEnabled() {
		return true;
	}






}
