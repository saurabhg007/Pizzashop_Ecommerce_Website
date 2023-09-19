package com.Spring;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userAuthService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");

		try {
		if (header == null || !header.startsWith("HTTP_TOKEN")) {
			throw new Exception("No JWT token found in the request headers");
		}

		String token = header.substring("HTTP_TOKEN".length() + 1);

		// Optional - verification
		
			jwtUtil.validateToken(token);
		

		String userName = jwtUtil.getUserName(token);

		UserDetails userDetails = userAuthService.loadUserByUsername(userName);

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());

		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filterChain.doFilter(request, response);
	}

}


/*
OncePerRequestFilter
This is Filter base class that aims to guarantee a single execution per request dispatch, on any servlet container. As of Servlet 3.0, a filter may be invoked as part of a REQUEST or ASYNC dispatches that occur in separate threads. You may read more on OncePerRequestFilter.

A common use case is in Spring Security, where authentication and access control functionality is typically implemented as a filter that sits in front of the main application Servlet. When a request is dispatched using a request dispatcher, it has to go through the filter chain again (or possibly a different one) before it gets to the Servlet that is going to deal with it. The problem is that some of the security filter actions should only be performed once for a request. Hence the need for OncePerRequestFilter.

You must override the method doFilterInternal() method in order to extend the functionalities of this filter.

I have checked for Authorization with HTTP_TOKEN token (JWT) in the HTTP header and if JWT presents then I have extracted it for further operations.

I have optionally validated the token before extracting user information from this token.

Further I have loaded user details from database and set authentication into SecurityContext.*/
