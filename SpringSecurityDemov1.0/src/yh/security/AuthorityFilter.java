package yh.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class AuthorityFilter  extends AbstractSecurityInterceptor implements Filter{
	
	//与spring-security.xml里的myFilter的属性source对应
	//其他的两个组件，已经在AbstractSecurityInterceptor定义 
	private FilterInvocationSecurityMetadataSource source;
	
	public FilterInvocationSecurityMetadataSource getSource() {
		return source;
	}

	public void setSource(FilterInvocationSecurityMetadataSource source) {
		this.source = source;
	}

	@Override
	public Class<?> getSecureObjectClass() {
		//下面的MyAccessDecisionManager的supports方面必须返回true,否则会提醒类型错误
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		
		return this.source;
	} 

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response,filterChain);
		invoke(fi);
	}
	private void invoke(FilterInvocation fi) throws IOException,ServletException{
		//进入页面第一步
		System.out.println("AuthorityFilter"+"-------"+"invoke");
		// object为FilterInvocation对象 
		//super.beforeInvocation(fi);源码 
		//1.获取请求资源的权限
		//执行Collection<ConfigAttribute> attributes = SecurityMetadataSource.getAttributes(object); 
		//2.是否拥有权限 
		//this.accessDecisionManager.decide(authenticated, object, attributes); 
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try{
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		}finally{
			super.afterInvocation(token, null);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
