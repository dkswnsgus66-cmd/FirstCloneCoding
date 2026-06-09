package FirstCloneCoding.demo.board;

import FirstCloneCoding.demo.core.exception.BadRequestException;
import FirstCloneCoding.demo.member.Member;
import lombok.Builder;
import lombok.Data;

public class BoardRequest {


    @Data
    @Builder
    public static class SaveDTO {

        private String title;
        private String content;
        private BoardType boardType;


        public Board toEntity(Member member) {

            return Board.builder()
                    .title(this.title)
                    .content(this.content)
                    .boardType(this.boardType)
                    .member(member)
                    .viewCount(0)
                    .isActive(true)
                    .build();

        }

        public void validate() {

            if (this.title == null || this.title.trim().isEmpty()) {
                throw new BadRequestException("제목 입력은 필수 입니다.");
            }
            if (this.content == null || this.content.trim().isEmpty()) {

                throw new BadRequestException("내용 입력은 필수 입니다.");
            }
        }
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;

        public void update(Board board) {

           board.setTitle(this.title);
           board.setContent(this.content);

        }

        public void validate() {

            if (this.title == null || this.title.trim().isEmpty()) {
                throw new RuntimeException("제목 입력은 필수 입니다.");
            }
            if (this.content == null || this.content.trim().isEmpty()) {

                throw new RuntimeException("내용 입력은 필수 입니다.");
            }

        }
    }


}
