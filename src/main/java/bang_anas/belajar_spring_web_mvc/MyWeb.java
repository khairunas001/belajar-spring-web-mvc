package bang_anas.belajar_spring_web_mvc;

import bang_anas.belajar_spring_web_mvc.interceptor.SessionInterceptor;
import bang_anas.belajar_spring_web_mvc.resolver.PartnerArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MyWeb implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Autowired
    private PartnerArgumentResolver partnerArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(partnerArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/user/*");
        // "/user/*" -> "/user/Joko", "/user/Budiman", dimana hanya ditambah 1 path setelah user
        // "/user/**" -> "/user/Budi/Makio" dimanaakan mengambil berapapun setelah / user
        // "/user/**/mantap" -> mengambil semua yang penting akhirnya ada /mantap
        // "/user/?jkw" -> kalau tanda tanya hanya mengambil 1 karakter seperti "/user/Ojkw" dll
    }
}
