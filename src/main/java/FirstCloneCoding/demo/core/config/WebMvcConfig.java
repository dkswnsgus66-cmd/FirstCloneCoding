package FirstCloneCoding.demo.core.config;


import FirstCloneCoding.demo.core.intercepter.LoginInterceptor;
import FirstCloneCoding.demo.core.intercepter.SessionInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;
    private final SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/members")
                .excludePathPatterns(
                        "/",
                        "/logout",
                        "/join",
                        "/login"
                );

        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**")
        ;
    }
}
