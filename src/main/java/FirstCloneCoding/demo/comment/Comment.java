package FirstCloneCoding.demo.comment;


import FirstCloneCoding.demo.board.Board;
import FirstCloneCoding.demo.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.sql.Timestamp;

@Table(name = "comment_tb")
@Entity
@NoArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(length = 500,nullable = false)
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Comment(Board board, Member member, String content) {
        this.board = board;
        this.member = member;
        this.content = content;
    }

    public boolean isOwner(Long commentMemberId, Long memberId){ // 해당 댓글의 작성자 ID
        if (commentMemberId != null && memberId != null) {
            if(commentMemberId == memberId){
                return true;
            }
        }
        return false;
    }

}
