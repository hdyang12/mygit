package yh.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;




public class MyAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 决定能不能访问,第一个参数,第二个参数,第三个参数是权限库
	 */
	
	public void decide(Authentication authentication, Object obj,
			Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
		System.out.println("MyAccessDecisionManager"+"-------"+"decide");
			if(configAttributes == null) { 
			return; 
			} 
			//所请求的资源拥有的权限(一个资源对多个权限) 
			Iterator<ConfigAttribute> iterator = configAttributes.iterator(); 
			while(iterator.hasNext()) { 
			ConfigAttribute configAttribute = iterator.next(); 
			//访问所请求资源所需要的权限 
			String needPermission = configAttribute.getAttribute(); 
			System.out.println("needPermission is " + needPermission); 
			//用户所拥有的权限authentication 
			for(GrantedAuthority ga : authentication.getAuthorities()) { 
				System.out.println("==用户权限=="+ga.getAuthority());
			if(needPermission.equals(ga.getAuthority())) { 
				//进了这里就能进去
				System.out.println("====equals====");
			return; 
			} 
			} 
			} 
			//没有权限 
			throw new AccessDeniedException(" 没有权限访问�?"); 
			} 

	//MyAccessDecisionManager的supports方面必须返回true,否则会提醒类型错误
	public boolean supports(ConfigAttribute arg0) {

		return true;
	}

	public boolean supports(Class<?> arg0) {

		return true;
	}

}
