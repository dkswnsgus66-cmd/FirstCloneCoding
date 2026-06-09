package FirstCloneCoding.demo.member;


import FirstCloneCoding.demo.core.exception.NotFoundException;
import FirstCloneCoding.demo.core.util.Define;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void join(MemberRequest.JoinDTO joinDTO){
        Member memberEntity = Member
                .builder()
                .name(joinDTO.getName())
                .password(joinDTO.getPassword())
                .phone(joinDTO.getPhone())
                .email(joinDTO.getEmail())
                .role(joinDTO.getRole())
                .status(Status.ACTIVE)
                .build();
        memberRepository.save(memberEntity);
    }

    @Transactional
    public void login(HttpSession session, MemberRequest.LoginDTO loginDTO) {

        Member memberEntity = memberRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() ->
                     new NotFoundException("존재하지 않는 회원 입니다.")
                );
        loginDTO.validate();
        session.setAttribute(Define.SESSION_USER,memberEntity);

    }

    public void logout(HttpSession session) {
        session.invalidate();
    }


}
