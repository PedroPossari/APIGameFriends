package Game.friends.GameFriends.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final TokenService tokenService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.headers().frameOptions()
                .disable()
                .and().cors().and().csrf().disable()
                .authorizeHttpRequests((authz) -> authz

                        .antMatchers("/", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .antMatchers("/usuario/admin/**").hasRole("ADMIN")
                        .antMatchers("/usuario/**").permitAll()
                        .antMatchers("/auth/**").permitAll()
                        .antMatchers(HttpMethod.GET,"/jogos/**").hasAnyRole("ADMIN", "USUARIO")
                        .antMatchers(HttpMethod.PUT, "/jogos/favoritos").hasAnyRole("ADMIN", "USUARIO")
                        .antMatchers("/jogos/review").hasAnyRole("ADMIN", "USUARIO")
                        .antMatchers("/jogos/favoritos").hasAnyRole("ADMIN", "USUARIO")
                        .antMatchers(HttpMethod.GET, "/jogos/favoritos/**").hasAnyRole("ADMIN", "USUARIO")
                        .antMatchers("/jogos/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                );

        http.addFilterBefore(new TokenAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer()
    {
        return (web) -> web.ignoring()
                .antMatchers("/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/swagger-ui/**");
    }

    @Bean
    WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .exposedHeaders("Authorization");
            }
        };
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}