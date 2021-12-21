package com.lojadomecanico.desafio.infra.security;

import com.lojadomecanico.desafio.domain.protocols.AuthenticateProtocol;
import com.lojadomecanico.desafio.infra.database.repository.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoadUserDetails authSecurity;
    private final AuthenticateProtocol jwtService;
    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authSecurity).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // public routes
                .antMatchers(HttpMethod.POST,
                        "/login",
                        "/users/retrieve-password",
                        "/users"
                ).permitAll()
                .antMatchers(HttpMethod.PUT,
                        "/users/redefine-password"
                ).permitAll()


                .anyRequest().authenticated()
                .and().cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticateFilterRequest(jwtService, userRepositoryJpa),
                UsernamePasswordAuthenticationFilter.class)
        ;
    }
    @Override
    public void configure(WebSecurity web)  {
        web.ignoring()
                .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

}
