package io.github.simonxianyu.util.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截请求以便检查相关请求方法的 Func annotation
 * Created by simon on 2015/2/16.
 */
public class FuncInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private String loginUrl = "/loginform";

    private PermissionChecker permissionChecker;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("__ModuleCode", "");
        log.debug("[start]funcHandler:preHandler");
        String contextPath = request.getContextPath();
        String requestUri = request.getRequestURI().substring(contextPath.length());

        if (requestUri.startsWith(loginUrl)
                || requestUri.startsWith("/logout")
                || requestUri.startsWith("/error404")
                || requestUri.startsWith("/error500")
                || requestUri.startsWith("/WEB-INF/error/")
                || requestUri.startsWith("/api")
                ) {
            return true;
        }
        request.setAttribute("__ModuleCode", ""); // avoid null exception in ftl
        if (handler instanceof HandlerMethod) {
            log.debug("method is {}", ((HandlerMethod) handler).getMethod() );
            Method method = ((HandlerMethod) handler).getMethod();
            Func f = method.getAnnotation(Func.class);
            if (null != f) {
                request.setAttribute("__ModuleCode", f.moduleCode());
                log.debug("mcode {} , func {}", f.moduleCode(),f.funcCode());
                if (f.enablePermission() ) {
                    return null != permissionChecker && permissionChecker.isPermitted(request , f);
// check shiro permission
//                    Subject subject = SecurityUtils.getSubject();
//                    if (!subject.isAuthenticated()) {
//                        WebUtils.saveRequest(request);
//                        WebUtils.issueRedirect(request, response, loginUrl);
////                        response.sendRedirect(contextPath+"/loginform");
//                        return false;
//                    }
//                    if (!subject.hasRole("1") && !subject.isPermitted(f.moduleCode()+":"+f.funcCode())) {
//                        response.sendRedirect(contextPath+"/unauthed");
//                        return false;
//                    }
                }
            }

        }
        return true;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public void setPermissionChecker(PermissionChecker permissionChecker) {
        this.permissionChecker = permissionChecker;
    }
}
