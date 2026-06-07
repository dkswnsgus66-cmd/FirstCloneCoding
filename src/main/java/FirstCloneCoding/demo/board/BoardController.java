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




}
