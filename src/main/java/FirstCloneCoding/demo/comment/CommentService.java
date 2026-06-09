package FirstCloneCoding.demo.comment;

import FirstCloneCoding.demo.board.Board;
import FirstCloneCoding.demo.board.BoardRepository;
import FirstCloneCoding.demo.core.exception.NotFoundException;
import FirstCloneCoding.demo.member.Member;
import FirstCloneCoding.demo.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 댓글 등록
    @Transactional
    public Comment save(CommentRequest.SaveDTO saveDTO, Long memberId) { // 여기는 내가 댓글달 보드 ID
        Board boardEntity = boardRepository.findById(saveDTO.getBoardId()).orElseThrow(() ->
                new NotFoundException("게시글을 찾을수 없습니다.")
        );
        // id로 사용자 조회
        Member memberEntity = memberRepository.findById(memberId).orElseThrow(() ->
                new NotFoundException("사용자를 찾을수 없습니다.")
        );

        Comment comment = saveDTO.toEntity(memberEntity, boardEntity);
        commentRepository.save(comment);
        return comment;
    }

    // 해당 보드의 댓글목록
    public List<CommentResponse.ListDTO> findCommentListByBoardId(Long boardId,Long memberId) {
        // 해당 보드의 댓글 목록
        List<Comment> commentList = commentRepository.findByBoardIdWithMember(boardId);



        return commentList.stream().map(c -> new CommentResponse.ListDTO(c, memberId)).toList();

    }


    @Transactional
    public void update(Long commentId, CommentRequest.UpdateDTO updateDTO) {

        // 내가 쓴 댓글중 수정할 댓글
        Comment commentEntity = commentRepository.findById(commentId).orElseThrow(() ->
                new NotFoundException("해당 댓글은 존재하지 않습니다.")
        );

        commentEntity.setContent(updateDTO.getContent());
    }

    @Transactional
    public void delete (Long commentId) {
       // 삭제할려는 댓글
       Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new NotFoundException("해당 댓글은 존재하지 않습니다.")
               );

       commentRepository.delete(comment);
    }

}
