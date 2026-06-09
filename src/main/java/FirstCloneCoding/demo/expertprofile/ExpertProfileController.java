package FirstCloneCoding.demo.expertprofile;


import FirstCloneCoding.demo.core.util.Define;
import FirstCloneCoding.demo.member.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExpertProfileController {

    private final ExpertProfileService expertProfileService;


    // 전문가 목록
    @GetMapping("/experts")
    public String expertslist(@RequestParam(required = false) String role, Model model) {

        List<ExpertProfileResponse.ListDTO> listDTO = expertProfileService.expertList(role);
        // 들어오는 role 과 문자가 같은 것끼리 걸러 지는 코드
        if(role != null && !role.isEmpty()){
            listDTO = listDTO.stream().filter(dto -> dto.getRole().equalsIgnoreCase(role)).toList();
        }
        model.addAttribute("expertProfiles", listDTO);
        model.addAttribute("isAll", role == null || role.isEmpty()); // listDTO
        model.addAttribute("isFullstack","FULLSTACK".equalsIgnoreCase(role));
        model.addAttribute("isBackend","BACKEND".equalsIgnoreCase(role));
        model.addAttribute("isFrontend","FRONTEND".equalsIgnoreCase(role));
        model.addAttribute("isApp","APP".equalsIgnoreCase(role));
        model.addAttribute("isUiux","UIUX".equalsIgnoreCase(role));
        return "expertProfile/expertProfile-list";
    }

    // 전문가 단건 조회 페이지
    @GetMapping("experts/{memberId}")
    public String expertDetail (@PathVariable(name = "memberId") Long memberId, Model model,HttpSession session) {
        Member member = (Member) session.getAttribute(Define.SESSION_USER);
        ExpertProfileResponse.DetailDTO expertProfile = expertProfileService.Detail(memberId);
        model.addAttribute("expertProfile", expertProfile);
        model.addAttribute("isOwner",member.getId() != null && member.getId() == memberId);
        return "expertProfile/expertProfile-detail";
    }

    // 전문가 수정 페이지
    @GetMapping("/experts/updateform")
    public String updateForm (@RequestParam(name = "memberId") Long memberId) {
        return "expertProfile/expertProfile-form";
    }

    // 전문가 수정 기능
    @PostMapping("/expert-profile/save")
    public String update (@RequestParam(name = "memberId") Long memberId) {

        return "redirect:/experts/" + memberId;
    }

}
