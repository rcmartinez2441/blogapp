package com.codeup.blogapp.security;

import com.codeup.blogapp.errors.CustomAccessDeniedHandler;
import com.codeup.blogapp.errors.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer //This is saying that our resource server is sitting under the realm of "api" spo anything that is server or responding through api is the resource server we want to protect
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public ResourceServerConfiguration(CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Override //look at resource ID "api and to
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("api");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                //TODO: if you need to test all end point, just comment out the next three lines
                    .antMatchers("/api/users").hasAnyAuthority("ADMIN", "USER") // it doesnt know if the request are for get/post/delete/etc... perhaps I want to allow api/posts if it was a get request
                    .antMatchers("/api/posts/**").hasAnyAuthority("ADMIN", "USER")
                    .antMatchers("/swager-ui/**", "/v3/api-docs/**").permitAll()
                    .antMatchers("/api/users/create").permitAll() //Would have to append to paths for any Controller methods you need access to
                    .antMatchers("/**").permitAll()
                    .anyRequest().authenticated()
            .and()
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).accessDeniedHandler(new CustomAccessDeniedHandler());
    }

}
