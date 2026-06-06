package FirstCloneCoding.demo.member;

import lombok.Data;

public class MemberRequest {

    @Data
    public static class JoinDTO {

        private String name;
        private String email;
        private String password;
        private String phone;
        private Role role;

    }

    @Data
    public static class LoginDTO {

        private String email;
        private String password;

        public void validate() {

            if (email == null || email.trim().isEmpty()) {

                throw new RuntimeException("이메일을 입력해 주세요.");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new RuntimeException("비밀번호를 입력해 주세요");
            }

        }


    }


}
