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
            boolean isAuthed = grantList.contains(anno.authCode());
            if(!isAuthed) {

                /*
                if(페이지 요청이라면) {
                    response.sendRedirect("/");
                } else { // ajax 라면
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                }
                 */

                response.sendRedirect("/");
                return false;
            }
        }

        return true;
    }
}
