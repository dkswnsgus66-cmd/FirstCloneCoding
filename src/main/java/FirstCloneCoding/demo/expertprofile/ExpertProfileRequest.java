package FirstCloneCoding.demo.expertprofile;

import FirstCloneCoding.demo.core.exception.BadRequestException;
import lombok.Data;

public class ExpertProfileRequest {

    @Data
    public static class UpdateDTO {

        private Integer experienceYears;
        private String skills; // 기술 스택
        private String introduce; // 자기소개
        private String portfolioUrl;

        public void validate() {

            if(skills == null || skills.trim().isEmpty()){
                throw new BadRequestException("기술스택은 필수로 입력해야 합니다");
            }

        }
    }

}
