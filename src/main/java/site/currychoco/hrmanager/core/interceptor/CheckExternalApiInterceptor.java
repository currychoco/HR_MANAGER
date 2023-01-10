package site.currychoco.hrmanager.core.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import site.currychoco.hrmanager.api.service.ApiKeyService;
import site.currychoco.hrmanager.core.exception.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CheckExternalApiInterceptor implements HandlerInterceptor {

    @Autowired
    private ApiKeyService apiKeyService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        final String key = request.getHeader("Authorization");
        final String ip = request.getRemoteAddr();
        final String method = request.getMethod();
        final String uri = request.getRequestURI();

        boolean result = apiKeyService.isAuthorizedRequest(key, ip, method, uri);
        
        if(!result) {
            throw new UnauthorizedException("해당 API 에 접근할 수 없습니다");
        }
        return true;
    }
}
