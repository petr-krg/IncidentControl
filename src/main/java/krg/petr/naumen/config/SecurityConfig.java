package krg.petr.naumen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/img/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/welcome","/login").permitAll()
                        //.requestMatchers("/index").authenticated()
                        .anyRequest().authenticated()
                ).formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .usernameParameter("userLogin")
                        .passwordParameter("userPassword")
                        .defaultSuccessUrl("/index", true)
                        //.permitAll()
                ).rememberMe( rememberMe -> rememberMe.key("№gAk7_2i8tf_t,w:№Q4fY61!8:QJjK!0&B.LHgENyD8f;D7,zdjGb{4c-,;wx№PK"))
                .logout(logout -> logout.logoutUrl("/logout"));

        return httpSecurity.build();
    }
}