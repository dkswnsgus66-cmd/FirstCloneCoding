package FirstCloneCoding.demo.core.intercepter;

import FirstCloneCoding.demo.core.util.Define;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    // 컨트롤러 실행 직전에 호출
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false); // 세션 없으면 세션을 안만듬
        if (session == null || session.getAttribute(Define.SESSION_USER) == null){
            throw new RuntimeException("로그인이 필요합니다"); // 로그인 안되어 예외처리
        }
        return true;

    }
}
