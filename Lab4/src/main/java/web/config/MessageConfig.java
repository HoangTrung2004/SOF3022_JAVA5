package web.config;

import org.springframework.context.MessageSource;                         // // Bean tải file đa ngôn ngữ
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;                     // // Cơ chế lưu/ngầm định locale
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // // Cho phép đăng ký interceptor
import org.springframework.web.servlet.i18n.CookieLocaleResolver;        // // Lưu ngôn ngữ bằng Cookie
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;     // // Đổi ngôn ngữ qua ?lang=...

import java.time.Duration;
import java.util.Locale;

@Configuration
public class MessageConfig implements WebMvcConfigurer {

    @Bean("messageSource")
    public MessageSource messageSource() {                               // // Bean tải resource bundle
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasenames("classpath:i18n/layout");                        // // KHÔNG kèm _vi và .properties
        ms.setDefaultEncoding("UTF-8");                                   // // Đọc Unicode chuẩn
        ms.setCacheSeconds(5);                                           // // Dev: reload sau 5s
        return ms;
    }

    @Bean("localeResolver")
    public LocaleResolver localeResolver() {                             // // Lưu ngôn ngữ người dùng
        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setCookieName("LANG");                                       // // Tên cookie
        clr.setCookieMaxAge((int) Duration.ofDays(30).getSeconds());     // // Giữ 30 ngày
        clr.setCookiePath("/");                                          // // Áp dụng toàn site
        clr.setDefaultLocale(new Locale("vi"));                          // // Ngôn ngữ mặc định: VI
        return clr;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {          // // Bật interceptor đổi ngôn ngữ
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");                                        // // Đổi qua query: ?lang=vi|en
        registry.addInterceptor(lci);
    }
}