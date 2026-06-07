package FirstCloneCoding.demo.board;


import FirstCloneCoding.demo.member.Member;
import FirstCloneCoding.demo.member.MemberRepository;
import FirstCloneCoding.demo.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;



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

        Board boardEntity =  boardRepository.findById(boardId).orElseThrow(() ->
                    new IllegalArgumentException("게시글을 찾을수 없습니다.")
                );

        return new BoardResponse.DetailDTO(boardEntity);


    }
}
