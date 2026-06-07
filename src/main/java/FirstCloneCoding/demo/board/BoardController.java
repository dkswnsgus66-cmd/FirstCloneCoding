package FirstCloneCoding.demo.board;


import FirstCloneCoding.demo.core.util.Define;
import FirstCloneCoding.demo.member.Member;
import FirstCloneCoding.demo.member.Role;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.metamodel.mapping.EntityIdentifierMapping;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 보드 게시판

    @GetMapping("/boards")
    public String boardList(@RequestParam(defaultValue = "FREE") String boardType,
                            @RequestParam(defaultValue = "") String keyword,
                            @RequestParam(defaultValue = "1") int page,
                            Model model, HttpSession session
    ){

        Member sessionMember = (Member) session.getAttribute(Define.SESSION_USER);

        Page<BoardResponse.ListDTO> boardPage = boardService.findAllByBoardType(BoardType.valueOf(boardType.toUpperCase()),keyword,page);

        model.addAttribute("isFree",boardType.equalsIgnoreCase("FREE"));
        model.addAttribute("isInquiry",boardType.equalsIgnoreCase("INQUIRY"));
        model.addAttribute("isNotice",boardType.equalsIgnoreCase("NOTICE"));
        model.addAttribute("isAdmin",sessionMember != null && sessionMember.getRole() == Role.ADMIN);
        model.addAttribute("boards",boardPage.getContent());
        model.addAttribute("boardCount",boardPage.getTotalElements());
        // TODO 나중에 페이지 추가
        model.addAttribute("totalPages",boardPage.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("prevPage", page > 1 ? page - 1 : null );
        model.addAttribute("nextPage", page < boardPage.getTotalPages() ? page + 1 : null );
        model.addAttribute("boardType", boardType);

        return "board/board-list";
    }

    // 상세 화면
    @GetMapping("/boards/{boardId}")
    public String boardDetailPage(@PathVariable(name = "boardId") Long boardId, Model model,HttpSession session) {

        Member sessionMember = (Member) session.getAttribute(Define.SESSION_USER);
//        boardService.increaseViewCount(boardId);
        BoardResponse.DetailDTO board = boardService.detailBoard(boardId);
        Long boardOwner = board.getMemberId(); // 해당 보드와 주인이 맞는지 확인
        model.addAttribute("board",board);
        model.addAttribute("isOwner",sessionMember!=null&&boardOwner.equals(sessionMember.getId()));
        model.addAttribute("comments",null);
        return "board/board-detail";
    }

    // 글쓰기 화면
    @GetMapping("/boards/save")
    public String boardSavePage(@RequestParam(name = "boardType") String boardType , Model model , HttpSession session){

        Member member = (Member) session.getAttribute(Define.SESSION_USER);

        // 공지사랑 작성은 관리자 ROLE 만 허용
        if(boardType.equalsIgnoreCase("NOTICE")){
            if (member == null || member.getRole() != Role.ADMIN) {
                throw new RuntimeException("공지 작성 권한이 없습니다");
            }
        }

        // 자유나 문의는 로그인만
        if(boardType.equalsIgnoreCase("FREE") || boardType.equalsIgnoreCase("INQUIRY")){
            if(member == null){ // 로그인 안되어 있으면 다시 로그인 화면
                return "redirect:/login-form";
            }
        }
        model.addAttribute("boardType",boardType);
        model.addAttribute("isFree",boardType.equalsIgnoreCase("FREE"));
        model.addAttribute("isNotice",boardType.equalsIgnoreCase("NOTICE"));
        model.addAttribute("isInquiry",boardType.equalsIgnoreCase("INQUIRY"));

        return "board/board-save";

    }

    // 글쓰기 기능
    @PostMapping("/boards/save")
    public String boardSave(BoardRequest.SaveDTO saveDTO , HttpSession session) {

        Member member = (Member) session.getAttribute(Define.SESSION_USER);

        saveDTO.validate();

        boardService.boardSave(member,saveDTO);

        return "redirect:/boards";

    }

    // 업데이트 화면
    @GetMapping("/boards/{boardId}/edit")
    public String boardUpdatePage(@PathVariable(name = "boardId") Long boardId, Model model) {

        BoardResponse.UpdateDTO board = boardService.updatePage(boardId);

        model.addAttribute("isFree",board.getBoardType().name().equalsIgnoreCase("FREE"));
        model.addAttribute("isNotice",board.getBoardType().name().equalsIgnoreCase("NOTICE"));
        model.addAttribute("board",board);

        return "board/board-update";
    }


    // 업데이트 기능
    @PostMapping("/boards/{boardId}/edit")
    public String boardUpdate (@PathVariable(name = "boardId") Long boardId,BoardRequest.UpdateDTO updateDTO) {

        boardService.update(boardId,updateDTO);
        return "redirect:/boards";

    }

    // 삭제 기능
    @PostMapping("/boards/{boardId}/delete")
    public String boardDelete (@PathVariable(name = "boardId") Long boardId,HttpSession session){

        Member member = (Member) session.getAttribute(Define.SESSION_USER);

        boardService.delete(member.getId(),boardId);

        return "redirect:/boards";
    }

}
