package com.lzg.webproject.action;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.ForwardedHeaderFilter;

@Controller
@RequestMapping("/login")
public class LoginAction {
	
	@ResponseBody
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(){
		
        return forward();
    }
	
	protected String forward() {
		// 获得当前方法名
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		return forward(method);
	}
	
	protected String forward(String method) {
		if (method.startsWith("/")) {
			return method;
		}
		if ("search".equals(method)) {
			method = "list";
		}
		if ("edit".equals(method)) {
			method = "form";
		}
		String className = this.getClass().getSimpleName();
		className = StringUtils.uncapitalize(className);
		className = className.replace("Action", "");
		String viewName = this.getClass().getPackage().getName().replace(".", "/") + "/" + className + "/" + method;
		return viewName;
	}
}
