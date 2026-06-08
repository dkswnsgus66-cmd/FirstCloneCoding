package FirstCloneCoding.demo.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository <Board, Long> {


    @Query("""
    SELECT b FROM Board b JOIN FETCH b.member WHERE b.boardType = :boardType AND b.isActive = true
    """)
    List<Board> findAllByBoardTypeIsActive(@Param("boardType") BoardType boardType);


    // 보드 타입으로 찾고 keyword 검색이 빈문자면 '' = '' true 가 되어서 그냥 보드타입으로 전체검색 keyword가 들어오면 OR의 LIKE 동작
    // 즉 제목을 검색하는 기능
    @Query("""
    SELECT b FROM Board b JOIN FETCH b.member
    WHERE b.boardType = :boardType AND b.isActive = true
    AND (:keyword = '' OR b.title LIKE %:keyword%)
    """)
    Page<Board> findAllByBoardTypeAndKeyword(@Param("boardType") BoardType boardType,@Param("keyword") String keyword, Pageable pageable);




    @Query("""
    SELECT b FROM Board b JOIN FETCH b.member WHERE b.member.id = :memberId
    """)
    List<BoardResponse.ListDTO> findByMyBoard(@Param("memberId") Long memberId);




}
