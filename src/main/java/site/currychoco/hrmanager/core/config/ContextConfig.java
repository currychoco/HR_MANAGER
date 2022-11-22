package site.currychoco.hrmanager.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.currychoco.hrmanager.core.interceptor.CheckAuthorityInterceptor;

@Configuration
public class ContextConfig implements WebMvcConfigurer {

    @Autowired
    private CheckAuthorityInterceptor checkAuthorityInterceptor;

    /**
     * CORS 방지용
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkAuthorityInterceptor)
                .addPathPatterns("/**");
    }
}
