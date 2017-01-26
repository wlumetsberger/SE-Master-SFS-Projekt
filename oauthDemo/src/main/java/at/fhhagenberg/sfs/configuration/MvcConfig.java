package at.fhhagenberg.sfs.configuration;

import at.fhhagenberg.sfs.OauthDemoApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Wolfgang on 09.01.2017.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("landing");
        registry.addViewController("/landing").setViewName("landing");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/admin/create").setViewName("listing");
        registry.addViewController("/admin/list").setViewName("listing");
        registry.addViewController("/admin/delete").setViewName("listing");
    }
}
