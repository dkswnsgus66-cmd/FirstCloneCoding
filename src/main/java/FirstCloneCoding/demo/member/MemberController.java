package FirstCloneCoding.demo.member;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    // 회원가입 기능
    @PostMapping("/members/join")
    public String join(MemberRequest.JoinDTO joinDTO) {
        System.out.println(joinDTO);
        memberService.join(joinDTO);
        return "redirect:/login";
    }

    // 로그인 기능
    @PostMapping("/members/login")
    public String login(HttpSession session,MemberRequest.LoginDTO loginDTO) {

        memberService.login(session,loginDTO);
        log.info("로그인 성공");
        return "redirect:/";
    }
    // 로그아웃 기능
    @GetMapping("/members/logout")
    public String logout(HttpSession session){
        memberService.logout(session);
        log.info("로그아웃 성공");
        return "redirect:/";
    }
}
