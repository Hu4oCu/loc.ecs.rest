package loc.ecs.config;

import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/api/*" };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {DataConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {
            WebMvcConfig.class,
            RepositoryRestMvcConfiguration.class,
            WebSecurityConfig.class
        };
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] {
            new OpenEntityManagerInViewFilter(), new SimpleCorsFilter()
        };
    }
}
