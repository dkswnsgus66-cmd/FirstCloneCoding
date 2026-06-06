package FirstCloneCoding.demo.member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberPageController {

    private final MemberService memberService;

    // 메인 페이지
    @GetMapping("/")
    public String mainPage() {
        return "main";
    }


    // 로그인 페이지
    @GetMapping("/members/login")
    public String loginPage() {
        return "member/login-form";
    }


    // 회원가입 페이지
    @GetMapping("/members/join")
    public String joinPage() {
        return "member/join-form";
    }
}
