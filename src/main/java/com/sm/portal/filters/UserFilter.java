package com.sm.portal.filters;

import java.io.IOException;
import java.security.Principal;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.sm.portal.model.Users;
import com.sm.portal.service.UserService;
import com.sm.portal.service.UserServiceImpl;

public class UserFilter implements Filter{

	
	/*@Autowired
    private UserService userService;*/
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain)
			throws IOException, ServletException {
		
		try{
			HttpServletRequest localHttpServletRequest =(HttpServletRequest)paramServletRequest;
			@SuppressWarnings("unused")
			HttpServletResponse localHttpServletResponse = (HttpServletResponse) paramServletResponse;
			
			HttpSession localHttpsession = localHttpServletRequest.getSession(false);
			if(localHttpsession==null){
				return; //return login page
			}
			Principal principal = localHttpServletRequest.getUserPrincipal();
			
			if(principal==null){
				return; //return login page
			}
			
			String userName = principal.getName();
			
			/*if(userService==null){
				System.out.println("creating userService manually>>>>>>>>>>>>>>>>>>>>>>>>");
				userService =new UserServiceImpl();
			}
			Users user =userService.findUserByUserName(principal.getName());*/
			MysqlDb myDb =new MysqlDb();
			Integer userId=myDb.getUserDetaisl(principal.getName());
			//Integer userId =user.getUserId();
			setThreadLocalValues("USER_ID",userId );
			paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
		
		}catch(Exception e){e.printStackTrace();}
		finally{
			clearThreadLocal();
		}
		
	}

	private Users getUserDetails(String name) {
		Users users =null;
		
		
		return users;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private void setThreadLocalValues(String paramString, Object paramObject){
		
		Object localObject;
		if(ThreadLocalInfoContainer.INFO_CONTAINER.get()==null){
			localObject = new Hashtable(5);
			((Hashtable)localObject).put(paramString, paramObject);
			ThreadLocalInfoContainer.INFO_CONTAINER.set((Map<String, Object>) localObject);;
		}else{
			localObject=(Map)ThreadLocalInfoContainer.INFO_CONTAINER.get();
			((Map)localObject).put(paramString, localObject);
		}
	}

	private void clearThreadLocal(){
		if(ThreadLocalInfoContainer.INFO_CONTAINER.get()!=null){
			((Map<String, Object>)ThreadLocalInfoContainer.INFO_CONTAINER.get()).clear();
		}
	}
}
