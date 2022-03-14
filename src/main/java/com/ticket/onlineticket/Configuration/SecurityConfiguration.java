package com.ticket.onlineticket.Configuration;


import com.ticket.onlineticket.Domain.User;
import com.ticket.onlineticket.Service.ConcertService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;


    public SecurityConfiguration(@Lazy UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
                /*.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("user123")).roles("USER");*/
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/createAccount").permitAll() //th get
                .antMatchers("/user/create").permitAll() //th
                .antMatchers("/user/insertion").permitAll() //RE
                .antMatchers("/booking/create").hasAnyRole() //RE
                .antMatchers("/seat/allSeats").hasAnyRole() //RE
                .antMatchers("/seat/allSeat").hasAnyRole() //th
                .antMatchers("/concert/getAll").permitAll() //RE
                .antMatchers("/concert/addConcerts").hasRole("ADMIN") //RE
                .antMatchers("/concert/delete/*").hasRole("ADMIN") //RE
                .antMatchers("/concert/DTOAvailableSeat/*").hasAnyRole() //RE
                .antMatchers("/concert/addConcert").hasRole("ADMIN") //th get
                .antMatchers("/concert/addNewConcert").hasRole("ADMIN") //th post
                /*.antMatchers("/seats/get").hasRole("USER")*/
               /* .antMatchers("/employee/employees/*").hasAnyRole("ADMIN", "USER")*/
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
