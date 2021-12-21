package com.lojadomecanico.desafio.infra.adapters;

import com.lojadomecanico.desafio.domain.exceptions.ServerErrorException;
import com.lojadomecanico.desafio.domain.protocols.AuthenticateProtocol;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class AuthenticateJwtImpl implements AuthenticateProtocol {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String generateToken(Integer idUser, Date today) {
        try{
            Date expirationDate = new Date(today.getTime() + Long.parseLong(this.expiration));
            return Jwts.builder()
                    .setIssuer("API Desafio Loja do Mecanico")
                    .setSubject(idUser.toString())
                    .setIssuedAt(today)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS512, this.secret)
                    .compact();

        }catch (Exception e){
            throw new ServerErrorException(e.getMessage());
        }
    }

    @Override
    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public String getTokenHeader(HttpServletRequest request) {
        try{
            String auth = request.getHeader("Authorization");
            return auth.substring(7);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Integer getIdUserByToken(String token) {
        try{
            Claims claims = Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token).getBody();
            return Integer.parseInt(claims.getSubject());
        }catch(Exception e){
            return  null;
        }
    }
}
