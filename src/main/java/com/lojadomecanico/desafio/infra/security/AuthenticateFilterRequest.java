package com.lojadomecanico.desafio.infra.security;

import com.lojadomecanico.desafio.domain.protocols.AuthenticateProtocol;
import com.lojadomecanico.desafio.infra.database.entity.UserEntity;
import com.lojadomecanico.desafio.infra.database.repository.UserRepositoryJpa;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class AuthenticateFilterRequest extends OncePerRequestFilter {

    private final AuthenticateProtocol jwtService;
    private final UserRepositoryJpa userRepositoryJpa;

    public AuthenticateFilterRequest(AuthenticateProtocol jwtService, UserRepositoryJpa userRepositoryJpa) {
        this.jwtService = jwtService;
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        String token = validateTokenRequest(httpServletRequest);

        Integer idUser = jwtService.getIdUserByToken(token);

        Optional<UserEntity> user = getUser(idUser);

       if (jwtService.isValidToken(token) && user.isPresent()) {
            setContext(user.get());
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String retrieveToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7);
    }

    private String validateTokenRequest(HttpServletRequest httpServletRequest){
        return retrieveToken(httpServletRequest);
    }

    private Optional<UserEntity> getUser(Integer id){
        if(id == null) return Optional.empty();
        return  userRepositoryJpa.findById(id);
    }

    private void setContext(UserEntity user){
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        user,null, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
