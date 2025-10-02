package web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                HibernateConfig.class  // Конфигурация БД (Root context)
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class  // Конфигурация MVC (Servlet context)
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}