package FirstCloneCoding.demo.comment;


import FirstCloneCoding.demo.board.BoardResponse;
import FirstCloneCoding.demo.board.BoardService;
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
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;



    // 댓글작성
    @PostMapping("/boards/{boardId}/comments/save")
    public String saveComment (@PathVariable(name = "boardId") Long boardId,CommentRequest.SaveDTO dto, HttpSession session) {

        // 인증 검사
        Member member = (Member) session.getAttribute(Define.SESSION_USER);

        dto.validate();
        commentService.save(dto,member.getId());
        return "redirect:/boards/" + boardId;
    }


    // 댓글 수정 요청
    @GetMapping("/boards/{boardId}/comments/{commentId}/edit")
    public String updateCommentText(HttpSession session , @PathVariable(name = "boardId")Long boardId , @PathVariable(name = "commentId") Long commentId, Model model){
        Member sessionMember = (Member) session.getAttribute(Define.SESSION_USER);
//        boardService.increaseViewCount(boardId);
        BoardResponse.DetailDTO board = boardService.detailBoard(boardId);

        List<CommentResponse.ListDTO> comments = commentService.findCommentListByBoardId(boardId,sessionMember.getId());



        Long boardOwner = board.getMemberId(); // 해당 보드와 주인이 맞는지 확인
        model.addAttribute("board",board);
        model.addAttribute("isOwner",sessionMember!=null&&boardOwner.equals(sessionMember.getId()));
        model.addAttribute("comments",comments);



        return "board/board-detail";

    }




    // 댓글 수정  TODO 추후 수정 페이지 정해지면 만들기
    @PostMapping("/boards/{boardId}/comments/{commentId}/edit")
    public String updateComment(CommentRequest.UpdateDTO updateDTO,@PathVariable(name = "boardId") Long boardId, @PathVariable(name = "commentId") Long commentId,HttpSession session) {

        Member member = (Member) session.getAttribute(Define.SESSION_USER);

        commentService.update(commentId,updateDTO);

        return "redirect:/boards/" + boardId;
    }

    // 댓글 삭제
    @PostMapping("/boards/{boardId}/comments/{commentId}/delete")
    public String delete (@PathVariable(name = "boardId")Long boardId , @PathVariable(name = "commentId") Long commentId, HttpSession session) {
        Member member = (Member) session.getAttribute(Define.SESSION_USER);
        commentService.delete(commentId);
        return "redirect:/boards/" + boardId;
    }


}
