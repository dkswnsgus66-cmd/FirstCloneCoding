package FirstCloneCoding.demo.board;


import FirstCloneCoding.demo.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Table(name = "board_tb")
@Entity
@NoArgsConstructor
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    @Enumerated(EnumType.STRING)
    private BoardType boardType;
    @Column(nullable = false)
    private String title;
    @ColumnDefault("0")
    private Integer viewCount;
    @CreationTimestamp
    private Timestamp createdAt;
    @ColumnDefault("true")
    private Boolean isActive;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    @Builder
    public Board(BoardType boardType,String content, String title, Integer viewCount, Timestamp createdAt, Boolean isActive, Member member) {
        this.content = content;
        this.title = title;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.isActive = isActive;
        this.member = member;
        this.boardType = boardType;
    }


    public void delete() {
        this.isActive = false;
    }
}
