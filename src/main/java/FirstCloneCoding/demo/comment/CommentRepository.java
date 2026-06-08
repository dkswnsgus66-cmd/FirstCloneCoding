package FirstCloneCoding.demo.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    // 커맨트의 멤버조인 (커맨트 쓴사람) 해당 커맨트가 있는 board를 찾아라
    @Query("""
    SELECT c FROM Comment c JOIN FETCH c.member
        WHERE c.board.id = :boardId
    """)
    List<Comment> findByBoardIdWithMember(@Param("boardId") Long boardId);

    @Query("""
    SELECT c FROM Comment c JOIN FETCH c.board WHERE c.board.id = :boardId
    """)
    List<Comment> findByBoardId(@Param("boardId") Long boardId);

}
