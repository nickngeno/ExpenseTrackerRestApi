package com.kimmy.ExpenseTrackerREStAPI.filters;

import com.kimmy.ExpenseTrackerREStAPI.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader == null) {
            httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value(), "UnAuthorized /" +
                    " missing authorization token");
            return;
        } else {
            String[] authHeaderArr = authHeader.split("Bearer");
            if (authHeaderArr.length > 1 && authHeaderArr[1] != null) {
                String token = authHeaderArr[1].stripLeading();
                try {
                    Claims claims = Jwts.parser().setSigningKey(Constants.JWT_TOKEN_SECRET)
                            .parseClaimsJws(token)
                            .getBody();
                    httpServletRequest.setAttribute("userId", claims.get("userId"));
                } catch (Exception e) {
                    httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Expired/Invalid token");
                    return;
                }

            } else {
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid Token");
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
