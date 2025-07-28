package spring.talk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * Web CORS 설정
     * <p>"http://localhost:3000"에 대해
     * 모든 HTTP 요청 해더와 쿠키 정보가 포함된 메소드들을 60m동안 CORS를 허용한다.
     *
     * @param registry CORS 등록자
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
