package com.example.bank.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.cors.CorsUtils;

/**
 *
 * This class is for JWT Token Authentication Filter for REST calls.
 *
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {



	public static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
			if (CorsUtils.isPreFlightRequest(request)) {
				response.setStatus(HttpServletResponse.SC_OK);
				chain.doFilter(request, response);
		    }
			chain.doFilter(request, response);
	}
}