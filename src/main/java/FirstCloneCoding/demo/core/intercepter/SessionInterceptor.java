package FirstCloneCoding.demo.core.intercepter;

import FirstCloneCoding.demo.core.util.Define;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
public class SessionInterceptor implements HandlerInterceptor {


    // 매 컨트롤러 마다 model.addAttribute("isLoggedIn",...) 반복 하지않고 헤더같은 공통뷰에 한번에 처리하기 위한 목적
    @Override // 컨트롤러가 실행된후 뷰가 렌더링 중 실행
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

        if (modelAndView != null){ // 뷰가 있는 요청만 동작
            HttpSession session = request.getSession(false); // 세션이 없으면 null 반환
            if (session != null){ //
                Object sessionUser = session.getAttribute(Define.SESSION_USER);
                if (sessionUser != null){
                    modelAndView.addObject("isLoggedIn",true); // 세션에 로그인 유저 있으면 true
                    modelAndView.addObject("sessionUser", sessionUser);
                }else {
                    modelAndView.addObject("isLoggedIn",false); // 세션에 로그인 유저없으면 false
                }
            } else {
                modelAndView.addObject("isLoggedIn",false);
            }
        }
    }
}
