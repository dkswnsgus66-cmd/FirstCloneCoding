package FirstCloneCoding.demo.board;


import FirstCloneCoding.demo.comment.Comment;
import FirstCloneCoding.demo.comment.CommentRepository;
import FirstCloneCoding.demo.member.Member;
import FirstCloneCoding.demo.member.MemberRepository;
import FirstCloneCoding.demo.member.MemberService;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;


    // 제목 검색 기능
    public Page<BoardResponse.ListDTO> findAllByBoardType(BoardType boardType, String keyword, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("createdAt").descending());
        Page<Board> boardPage = boardRepository.findAllByBoardTypeAndKeyword(boardType, keyword, pageable);
        return boardPage.map(BoardResponse.ListDTO::new);
    }

    // 내가 올린 게시판 목록
    public List<BoardResponse.ListDTO> findAllByMyBoard(Long memberId) {
        return boardRepository.findByMyBoard(memberId);
    }

    // 나중에 추가
//    public void increaseViewCount(Long boardId) {
//
//
//
//    }

    public BoardResponse.DetailDTO detailBoard(Long boardId) {

        Board boardEntity = boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("게시글을 찾을수 없습니다.")
        );

        List<Comment> comments = commentRepository.findByBoardId(boardId);


        return new BoardResponse.DetailDTO(boardEntity);


    }

    // 게시글 저장
    @Transactional
    public void boardSave(Member member, BoardRequest.SaveDTO saveDTO) {

        Member memberEntity = memberRepository.findById(member.getId()).orElseThrow(() ->
                new IllegalArgumentException("회원을 찾을수 없습니다.")
        );

        // 나중에 보드 출력할때 멤버 이름도 출력해야 하기에 member 정보도 넣음
        Board boardEntity = saveDTO.toEntity(memberEntity);

        boardRepository.save(boardEntity);

    }


    @Transactional
    public BoardResponse.UpdateDTO updatePage(Long boardId) {

        Board boardEntity = boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글을 찾을수 없습니다.")
        );

        return new BoardResponse.UpdateDTO(boardEntity);
    }

    @Transactional
    public void update(Long boardId, BoardRequest.UpdateDTO updateDTO) {

        Board boardEntity = boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글을 찾을수 없습니다.")
        );
        updateDTO.validate();
        updateDTO.update(boardEntity);

    }

    @Transactional
    public void delete(Long memberId, Long boardId) {

        Board boardEntity = boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글을 찾을수 없습니다.")
        );

        if (boardEntity.getMember().getId() == memberId) {
            boardEntity.delete();
        }

    }
}
