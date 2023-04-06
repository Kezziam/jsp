package ma.gov.pfe.presentation.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ma.gov.pfe.services.AuthServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthServiceImpl userAuthService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService);
        System.out.println("config global");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println("aut config");

        //.hasRole("admin").antMatchers("")
        http.authorizeRequests().
                antMatchers("/").permitAll().
                antMatchers("/login").permitAll().
                //antMatchers("/welcome").permitAll().
                //anyRequest().authenticated().
                        and().csrf().disable().
                formLogin().loginPage("/login").
                //failureUrl("/login?error=true").
                        defaultSuccessUrl("/welcome").
                usernameParameter("pseudo").
                passwordParameter("password").
                and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").
                and().exceptionHandling().accessDeniedPage("/access_denied");
    }


}
