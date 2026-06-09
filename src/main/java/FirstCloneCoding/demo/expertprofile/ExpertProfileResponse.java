package FirstCloneCoding.demo.expertprofile;

import lombok.Data;

public class ExpertProfileResponse {
    @Data
    public static class ListDTO {

        private Long memberId;
        private String memberName;
        private boolean isCertified;
        private String career;
        private Double avgRating;
        private Integer totalReviews;
        private String intro;
        private String role;




        public ListDTO(ExpertProfile expert) {
            this.memberId = expert.getMember().getId();
            this.memberName = expert.getMember().getName();
            this.isCertified = expert.isCertified();
            this.career = expert.getCareer();
            this.avgRating = expert.getAvgRating();
            this.totalReviews = expert.getTotalReviews();
            this.intro = expert.getIntro();
            this.role = expert.getExpertRole().toString();

        }
    }
    @Data
    public static class DetailDTO {

        private String memberName;
        private boolean isCertified;
        private String career;
        private Double avgRating;
        private String intro;
        private String skillList; // 기술스택
        private Long memberId;

        public DetailDTO(ExpertProfile expert) {
            this.memberName = expert.getMember().getName();
            this.isCertified = expert.isCertified();
            this.career = expert.getCareer();
            this.avgRating = expert.getAvgRating();
            this.intro = expert.getIntro();
            this.skillList = expert.getExpertRole().toString();
            this.memberId = expert.getMember().getId();
        }
    }


}
