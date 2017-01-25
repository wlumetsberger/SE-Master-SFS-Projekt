package at.fhhagenberg.sfs.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * Programmatic configuration of the spring security.
 * <p>
 * Created by Wolfgang on 09.01.2017.
 */
@Configuration
@EnableOAuth2Client
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;


    /**
     * Configure the htt security for the spring application
     *
     * @param http the HttpSecurity object to configure
     * @throws Exception if an error occurs during the configuration
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable OAuth for all resources
        http.antMatcher("/**").authorizeRequests()
            // Exclude open resources such as css, js and open views
            .antMatchers("/", "/dist/**", "/vendor/**", "/login**", "/landing").permitAll()
            // protect listing view for roles 'ADMIN, USER' only
            .antMatchers("/admin/list").hasAnyRole("ADMIN", "USER").anyRequest().authenticated()
            // protect all admin views for role 'ADMIN' only
            .antMatchers("/admin/create", "/admin/delete").hasRole("ADMIN").anyRequest().authenticated()
            // Redirect to login page if not logged and tried to access protected resource
            .and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
            // Redirect to login page if successfully logged out
            .and().logout().logoutSuccessUrl("/").permitAll()
            // Enable cross site request forgery support
            .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            // Add the OAuth filter before the BasicAuthentication filter
            .and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }

    /**
     * Register OAuth filter
     *
     * @param filter the filter to append
     * @return the filter registration
     */
    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    /**
     * Helper method for configuring the BasicAuthentication filter instance.
     *
     * @return the configured filter instance
     */
    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>(2);

        // Configure the OAuth filter for facebook authentication
        OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/facebook");
        OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(facebook(), oauth2ClientContext);
        facebookFilter.setRestTemplate(facebookTemplate);
        facebookFilter.setTokenServices(new UserInfoTokenServices(facebookResource().getUserInfoUri(), facebook().getClientId()));
        facebookFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/home"));
        filters.add(facebookFilter);

        // Configure the OAuth filter for identity server authentication
        OAuth2ClientAuthenticationProcessingFilter identityServerFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/identityserver");
        OAuth2RestTemplate identityServerTemplate = new OAuth2RestTemplate(identityserver(), oauth2ClientContext);
        identityServerFilter.setRestTemplate(identityServerTemplate);
        identityServerFilter.setTokenServices(new UserInfoTokenServices(identityserverResource().getUserInfoUri(), identityserver().getClientId()));
        identityServerFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/home"));
        filters.add(identityServerFilter);

        // Set configured filters on the composite filter
        filter.setFilters(filters);

        return filter;
    }

    /**
     * @return the configured facebook client
     */
    @Bean
    @ConfigurationProperties("facebook.client")
    public AuthorizationCodeResourceDetails facebook() {
        return new AuthorizationCodeResourceDetails();
    }

    /**
     * @return the configured facebook resource
     */
    @Bean
    @ConfigurationProperties("facebook.resource")
    public ResourceServerProperties facebookResource() {
        return new ResourceServerProperties();
    }

    /**
     * @return the configured identity server client
     */
    @Bean
    @ConfigurationProperties("identityserver.client")
    public AuthorizationCodeResourceDetails identityserver() {
        return new AuthorizationCodeResourceDetails();
    }

    /**
     * @return the configured identity server resource
     */
    @Bean
    @ConfigurationProperties("identityserver.resource")
    public ResourceServerProperties identityserverResource() {
        return new ResourceServerProperties();
    }
}
