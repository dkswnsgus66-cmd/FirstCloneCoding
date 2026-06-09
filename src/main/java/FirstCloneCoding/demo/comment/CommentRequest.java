package FirstCloneCoding.demo.comment;

import FirstCloneCoding.demo.board.Board;
import FirstCloneCoding.demo.core.exception.BadRequestException;
import FirstCloneCoding.demo.member.Member;
import lombok.Data;

import java.sql.Timestamp;

public class CommentRequest {

    @Data
    public static class SaveDTO {
        private String content;
        private Long boardId;
        public void validate() {
            if (this.content.trim().isEmpty() || this.content == null) {
                throw new RuntimeException("내용입력은 필수 입니다.");
            }
            if (this.content.length() > 500) {
                throw new RuntimeException("댓글은 500자 이하 여야합니다");

            }
            if (boardId == null) {

                throw new RuntimeException("잘못된 요청 입니다.");
            }
        }
        public Comment toEntity(Member member , Board board) {
            return Comment
                    .builder()
                    .content(this.content)
                    .member(member) // 해당 댓글의 사용자 이름 출력
                    .board(board) // 어느 보드인지
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        private String content;
        private Timestamp createdAt;

        public void validate() {
            if(this.content == null || this.content.trim().isEmpty()){
                throw new BadRequestException("내용을 입력해 주세요");
            }
        }

    }


}
