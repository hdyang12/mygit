package yh.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限
 * 
 * @author yh
 * 
 */
public class Privilege implements java.io.Serializable {
	private Long id;
	private String url;
	private String name; // 权限名称
	private Set<Role> roles = new HashSet<Role>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


}
