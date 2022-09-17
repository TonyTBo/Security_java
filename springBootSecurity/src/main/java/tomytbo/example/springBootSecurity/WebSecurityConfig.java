package tomytbo.example.springBootSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //create user in memory
        //note, only use this method to minh hoa
        //in reality, we will check user in csdl
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withDefaultPasswordEncoder() //user to encoder password basic
                        .username("tabo")
                        .password("tabo")
                        .roles("USER") //use to role users
                        .build()
        );
        return manager;
    }

    // HttpSecurity is main object of spring security, permit all config need security
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/home").permitAll()//permit all access to this two address
                    .anyRequest().authenticated()// other all same need authenticate to access
                    .and()
                .formLogin()//permit user use authenticate by login form
                    .defaultSuccessUrl("/hello")
                    .permitAll()// permit all to access this address
                    .and()
                .logout()// permit logout
                    .permitAll();
    }
}
