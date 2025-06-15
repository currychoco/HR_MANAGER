package site.currychoco.hrmanager.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.currychoco.hrmanager.core.interceptor.CheckAuthorityInterceptor;
import site.currychoco.hrmanager.core.interceptor.CheckExternalApiInterceptor;
import site.currychoco.hrmanager.core.interceptor.CheckLoginInterceptor;

import java.util.Arrays;

@Configuration
public class ContextConfig implements WebMvcConfigurer {

    @Autowired
    private CheckLoginInterceptor checkLoginInterceptor;

    @Autowired
    private CheckAuthorityInterceptor checkAuthorityInterceptor;

    @Autowired
    private CheckExternalApiInterceptor checkExternalApiInterceptor;

    /**
     * CORS 방지용
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(checkLoginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(Arrays.asList("/js/**", "/image/**", "/favicon.png"))
                .excludePathPatterns(Arrays.asList("/login", "/join"))
                .excludePathPatterns(Arrays.asList("/account/**", "/error"))
                .excludePathPatterns(Arrays.asList("/external-api/**"));

        registry.addInterceptor(checkAuthorityInterceptor)
                .addPathPatterns("/**");

        registry.addInterceptor(checkExternalApiInterceptor)
                .addPathPatterns("/external-api/**");
    }
}
