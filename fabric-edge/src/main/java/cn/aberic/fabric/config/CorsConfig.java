package cn.aberic.fabric.config;

import cn.aberic.fabric.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);

        loginRegistry.addPathPatterns(loginInterceptor.LEAGUE);
        loginRegistry.addPathPatterns(loginInterceptor.ORG);
        loginRegistry.addPathPatterns(loginInterceptor.ORDERER);
        loginRegistry.addPathPatterns(loginInterceptor.PEER);
        loginRegistry.addPathPatterns(loginInterceptor.CHANNEL);
        loginRegistry.addPathPatterns(loginInterceptor.CHAINCODE);
        loginRegistry.addPathPatterns(loginInterceptor.APP);
        loginRegistry.addPathPatterns(loginInterceptor.CA);
        loginRegistry.addPathPatterns(loginInterceptor.USER);
        loginRegistry.addPathPatterns(loginInterceptor.INDEX);

        super.addInterceptors(registry);
    }

    /**
     * 解决 swagger静态资源无法访问的问题
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/screen/**")
                .addResourceLocations("classpath:/screen/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}
