package FirstCloneCoding.demo.core.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 이 클래스가 설정 클래스임을 선언
@EnableWebSecurity // Spring Security 활성화
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()) // csrf보호 비활성화 (API) 서버나 개발단계에서 비활성화
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())  // 모든경로 인증없이 허용
                .formLogin(form -> form.disable()); // Spring Security 기본 로그인 폼 비활성화
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        // 비밀번호를 BCrypt 방식으로 암호화 하는 Bean 등록
        // 회원가입 / 로그인 시 비밀번호 처리에 사용
    }


}
