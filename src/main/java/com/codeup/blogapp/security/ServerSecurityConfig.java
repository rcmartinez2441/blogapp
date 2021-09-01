package com.codeup.blogapp.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//Controls debugging support for Spring Security. Default is false. Returns: if true, enables debug support with Spring Security
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
//Aside from additional authenticating, we can use settings in this class to apply granular security to apply to specific methods or endpoints
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsService userDetailsService;
    //Locates the user based on the username. In the actual implementation, the search may possibly be case sensitive, or case insensitive depending on how the implementation instance is configured. In this case, the UserDetails object that comes back may have a username that is of a different case than what was actually requested

    public ServerSecurityConfig(@Qualifier("userService") UserDetailsService userDetailsService) { //@Qualifier - This annotation may be used on a field or parameter as a qualifier for candidate beans when autowiring. It may also be used to annotate other custom annotations that can then in turn be used as qualifiers.
        this.userDetailsService = userDetailsService;
    }

    @Bean
    //We take our UserDetail Services, Password encoder, and DAO(Repository) and it brings everything together
    //hands this to Spring security to take all the things we bundled up and know how to use it
    public DaoAuthenticationProvider authenticationProvider() {
        //An AuthenticationProvider implementation that retrieves user details from a UserDetailsService.
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    //Service interface for encoding passwords. The preferred implementation is BCryptPasswordEncoder
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    //Processes an Authentication request.
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
