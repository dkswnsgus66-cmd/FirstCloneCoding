package FirstCloneCoding.demo.core.exception;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.zip.DataFormatException;

@Slf4j
@ControllerAdvice // 컨트롤러 전반에서 발생하는 예외를 한 곳에서 처리하는 전역 예외 핸들러 역할
// 서비스 컨트롤러 어디서 예외가 터지면 여기로 모인다
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class) // 400 잘못된 요청 예외를 잡아서 alert 메시지 history.back()이르
    // 이전 페이지로 되돌리는 코드
    @ResponseBody
    public String badReuest (BadRequestException e , HttpServletRequest request){
        log.warn("=== 400 Bad Request ===");
        log.warn("요청 URL : {}",request.getRequestURL());
        log.warn("에러 메세지 : {}" , e.getMessage());
        String message = e.getMessage().replace("'","\\'");
        return """
                <script>
                    alert('%s');
                    history.back();
                </script>
                """.formatted(message);
    }


    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody // 뷰 이름이 아니라 문자열 자체를 HTTP 응답 body 로 내려 보내겠다는 의미
    // 브라우저가 받은 script 태그를 실행하게 되는 방식
    public String unauthorized (UnauthorizedException e, HttpServletRequest request){
        log.warn("=== 401 Unauthrized ===");
        log.warn("요청 URL : {}" , request.getRequestURL());
        log.warn("에러메세지 : {}" , e.getMessage());
        String message = e.getMessage().replace("'","\\'");
        return """
                <script>
                    alert('%s');
                    location.href='/login-form';
                </script>
                """.formatted(message);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody
    // HttpServletRequest request 는 요청 정보를 담고있는 객체 예외가 발생 했을때 어떤 요청에서 터진건지 알기위한 객체
    public String fobidden (ForbiddenException e, HttpServletRequest request){

        log.warn("=== 403 Forbbiden ===");
        log.warn("요청 URL : {}" , request.getRequestURL());
        log.warn("에러 메세지 : {}" , e.getMessage());

        // 예외 메세지안에 작은 따옴표 ' 가 있으면 JS alert('...') 구문이 깨지기 때문에 이스케이프 처리
        // 만약 문자열 구문 안에 ' 작은 따옴표가오면 에러발생 이 ' 를 \\ 로 봐라고 설정
        String message = e.getMessage().replace("'","\\'");

        return """
                <script>
                    alert('%s');
                    history.back();
                </script>
                """.formatted(message);
        // alert(%s) 는 formatted(message) 를 가져 오는 것이다.
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public String notFount(NotFoundException e , HttpServletRequest request){
        log.warn("=== 404 Not Found ===");
        log.warn("요청 URL : {}" , request.getRequestURL());
        log.warn("에러 메세지 : {}" , e.getMessage());
        request.setAttribute("msg",e.getMessage());
        return "err/404";
    }

    @ExceptionHandler(InternalException.class)
    @ResponseBody
    public String internalServer (InternalException e , HttpServletRequest request){
        log.warn("=== 500 Internal Server ===");
        log.warn("요청 URL : {}" , request.getRequestURL());
        log.warn("에러 메세지 : {}" , e.getMessage());
        request.setAttribute("msg",e.getMessage());
        return "err/500";
    }

    @ExceptionHandler(DataFormatException.class)
    @ResponseBody
    public String dataIntegrity(DataIntegrityViolationException e, HttpServletRequest request) {
        log.warn("=== DB 제약조건 위반 ===");
        log.warn("요청 URL : {}" , request.getRequestURL());
        log.warn("에러 메세지 : {}" , e.getMessage());
        if (e.getMessage() != null && e.getMessage().contains("FOREIGN KEY")) {
            request.setAttribute("msg","관련돤 데이터가 있어 삭제할수 없습니다.");
        }else {
            request.setAttribute("msg","데이터 처리중 오류가 발생 했습니다.");
        }
        return "err/500";
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String runtimeException (RuntimeException e, HttpServletRequest request) {
        log.warn("=== 예상치 못한 에러 발생 RuntimeException ===");
        log.warn("요청 URL : {}" , request.getRequestURL());
        log.warn("에러 메세지 : {}" , e.getMessage());
        request.setAttribute("msg","시스템 오류가 발생 했습니다. 관리자 에게 문의해 주세요");
        return "err/500";
    }

}
