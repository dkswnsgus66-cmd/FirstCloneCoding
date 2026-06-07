package FirstCloneCoding.demo.board;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {

    @Data
    public static class ListDTO {

        private Long id;
        private String title;
        private String content;
        private BoardType boardType;
        private String memberName;
        private Timestamp createdAt;
        private Integer viewCount;

        public ListDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.boardType = board.getBoardType();
            this.memberName = board.getMember().getName();
            this.createdAt = board.getCreatedAt();
            this.viewCount = board.getViewCount();
        }
    }


    @Data

    public static class DetailDTO {
        private Long id;
        private BoardType boardType;
        private String title;
        private String memberName;
        private Timestamp createdAt;
        private Integer viewCount;
        private String content;
        private Long memberId;

        public DetailDTO(Board board) {
            this.id = board.getId();
            this.boardType = board.getBoardType();
            this.title = board.getTitle();
            this.memberName = board.getMember().getName();
            this.createdAt = board.getCreatedAt();
            this.viewCount = board.getViewCount();
            this.content = board.getContent();
            this.memberId = board.getMember().getId();
        }
    }

    @Data
    public static class UpdateDTO {

        private Long id;
        private String title;
        private String content;
        private BoardType boardType;

        public UpdateDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.boardType = board.getBoardType();
        }
    }
}
