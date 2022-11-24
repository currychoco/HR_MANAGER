package site.currychoco.hrmanager.core.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import site.currychoco.hrmanager.core.annotation.CheckAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class CheckAuthorityInterceptor implements HandlerInterceptor {

    final String AJAX_HEADER_CODE = "XMLHttpRequest";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        CheckAuthority anno = null;
        try {
            anno = ((HandlerMethod)handler).getMethodAnnotation(CheckAuthority.class);
        } catch(ClassCastException cce) {
            // 그냥 가~
        }

        if(anno != null) {
            HttpSession session = request.getSession();
            List<String> grantList = (List<String>) session.getAttribute("grant");
            if(grantList == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "님 누구세유~?");
                return false;
            }

            boolean isAuthed = grantList.contains(anno.authCode());
            if(!isAuthed) {

                if(isAjaxRequest(request)) { // api 요청인 경우
                    response.sendError(HttpStatus.FORBIDDEN.value(), "님 권한이 없네유~");
                } else { // page 요청인 경우
                    response.sendRedirect("/");
                }
                return false;
            }
        }

        return true;
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return AJAX_HEADER_CODE.equals(header);
    }
}
