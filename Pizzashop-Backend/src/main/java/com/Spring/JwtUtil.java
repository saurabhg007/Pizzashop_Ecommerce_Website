package com.Spring;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtil {

	@Value("${jwt.secret}")  // SpEL - Spring Expression Language notation 
	private String jwtSecret;

	@Value("${jwt.token.validity}")
	private long tokenValidity;

	public String getUserName(final String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

			return body.getSubject();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}

		return null;
	}
	
	
	
	public String generateToken(Authentication authentication) {
		MyUserInfo user = (MyUserInfo) authentication.getPrincipal();
		Claims claims = Jwts.claims().setSubject(user.getUsername());

		final long nowMillis = System.currentTimeMillis();
		
		final long expMillis = nowMillis + tokenValidity;

		Date exp = new Date(expMillis);

		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	
	
	public void validateToken(final String token) throws Exception {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}