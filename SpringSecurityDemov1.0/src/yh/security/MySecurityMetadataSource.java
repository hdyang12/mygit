package yh.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import yh.dao.PrivilegeDao;
import yh.model.Privilege;


//1 加载资源与权限的对应关系
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
	
	//resourceDao是为了拿到存放在数据库中的权限数据
	private PrivilegeDao privilegeDao;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null; 
	public MySecurityMetadataSource(PrivilegeDao privilegeDao) { 
		this.privilegeDao = privilegeDao;
		//在tomcat运行的时候，resourceMap就拿到了所有权限的url和权限名称
		loadResourceDefine(); 
	} 


	public PrivilegeDao getPrivilegeDao() {
		return privilegeDao;
	}


	public void setPrivilegeDao(PrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}


	public Collection<ConfigAttribute> getAllConfigAttributes() {
		
		return null;
	}
	//这个一定要return true
	public boolean supports(Class<?> arg0) {
		
		return true;
	}
	//加载所有资源与权限的关系 ,在构造函数中调用
	private void loadResourceDefine(){
		if(resourceMap == null) { 
			//初始化resourceMap,第一个参数放的是url,第二个参数放的是权限名集合		
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>(); 
			List<Privilege> privileges = privilegeDao.findAll(); 
			for (Privilege privilege : privileges) { 
			Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>(); 
			//以权限名封装为Spring的security Object 
			ConfigAttribute configAttribute = new SecurityConfig(privilege.getName());
			
			configAttributes.add(configAttribute);
			System.out.println(privilege.getUrl());
			resourceMap.put(privilege.getUrl(), configAttributes); 
			} 
			}
			//这里在tomcat运行的时候就运行了
			System.out.println("loadResourceDefine"+resourceMap);
	}
	//返回所请求资源所需要的权限 
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException { 
		//进入页面第二步
		System.out.println("MySecurityMetadataSource"+"-------"+"getAttributes");
		//获取当前请求的url
		String requestUrl = ((FilterInvocation) object).getRequestUrl(); 
		System.out.println("requestUrl is " + requestUrl); 
		if(resourceMap == null) { 
			loadResourceDefine(); 
		} 
		return resourceMap.get(requestUrl); 
		} 

}
