package FirstCloneCoding.demo.comment;

import FirstCloneCoding.demo.member.Member;
import lombok.Data;

import java.sql.Timestamp;

public class CommentResponse {

    @Data
    public static class ListDTO {
        private Long id; // 댓글 pk 나중에 commentId 로 댓글 수정 삭제 예정
        private String memberName;
        private Timestamp createdAt;
        private String comment;
        private boolean isOwner;
        private Member member;

        public ListDTO(Comment comment,Long memberId) { // 댓글 작성한 멤버를 가져와야되
            this.memberName = comment.getMember().getName();
            this.createdAt = comment.getCreatedAt();
            this.comment = comment.getContent();
            this.id = comment.getId();
            this.member = comment.getMember();
            this.isOwner = comment.isOwner(comment.getMember().getId(),memberId);
        }

    }

    public static class UpdateDTO {

        private String content;

    }



}
