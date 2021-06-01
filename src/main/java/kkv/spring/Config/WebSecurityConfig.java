package kkv.spring.Config;

import kkv.spring.Security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("kkv.spring.Security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProviderImpl authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
<<<<<<< Updated upstream
                .antMatchers("/authorization/room","/createRoom","/compare_photos").permitAll()
                .antMatchers("/authorization/enter","/","/authorization","/authorization/registration").anonymous()
                .antMatchers("/users").hasAnyRole("EMPLOYEE")
                .antMatchers("/users/*/inf").hasAnyRole("EMPLOYEE")
                .antMatchers("/users/roles").hasAnyRole("ADMIN")
                .antMatchers("/users/save_roles").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
=======
                    .antMatchers("/myCompare","/event_count","/","/authorization","/authorization/registration","/authorization/enter","/createRoom","/compare_photos").permitAll()
                    .antMatchers("/users").hasAnyRole("EMPLOYEE")
                    .antMatchers("/users/*/inf").hasAnyRole("EMPLOYEE")
                    .antMatchers("/users/roles").hasAnyRole("ADMIN")
                    .antMatchers("/users/save_roles").hasAnyRole("ADMIN")
                    .anyRequest().authenticated()
>>>>>>> Stashed changes
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/authorization/enter")
                .loginProcessingUrl("/spring_enter")
                .usernameParameter("email")
                .permitAll()
                .and()
<<<<<<< Updated upstream
                .exceptionHandling()
                .accessDeniedPage("/exceptionHandling")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/authorization?logout=true")
                .permitAll();
=======
                    .logout()
                    .logoutSuccessUrl("/authorization")
                    .permitAll();

>>>>>>> Stashed changes
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}