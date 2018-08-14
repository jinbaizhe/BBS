package com.parker.bbs.shiro;

import com.parker.bbs.pojo.User;
import com.parker.bbs.service.UserService;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RememberMeFilter extends FormAuthenticationFilter {
    @Autowired
    private UserService userService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        if ((!subject.isAuthenticated()) && subject.isRemembered() && (session.getAttribute("user") == null)){
            Object principal = subject.getPrincipal();
            if (principal != null){
                String username = principal.toString();
                User user = userService.getUserByUsername(username);
                session.setAttribute("user", user);
            }
        }
        return (subject.isAuthenticated() || subject.isRemembered());
    }
}
