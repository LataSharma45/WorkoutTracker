package com.example.Workout.config;

import com.example.Workout.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.Field;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException,
            IOException {
       //chatgpt
        String path = request.getServletPath();

        // 🔥 SKIP AUTH ENDPOINTS
        if (path.startsWith("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletRequest response,
//            @NonNull FilterChain filterChain
//            )throws ServletException, IOException{
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final  String userEmail;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
//        jwt= authHeader.substring(7);
//        userEmail= jwtService.extractUsername(jwt);
//        if(userEmail !=null && SecurityContextHolder.getContext().getAuthentication()== null){
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//            if(jwtService.isTokenValid(jwt,userDetails)){
//                Integer userId = jwtService.extractUserId(jwt);
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        userId,
//                        userDetails.getAuthorities()
//                );
//              //  authToken.setDetails(
//                //        new WebAuthenticationDetailsSource().buildDetails(request)
//            //    );
//                SecurityContextHolder.getContext().setAuthentication(authToken);
////            }else{
////                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////                return;
//            }
//        }
//        filterChain.doFilter(request, response);
//
//
//    }
//
//}
       jwt = authHeader.substring(7);

        String username;
        try {
            username = jwtService.extractUsername(jwt);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(username);


                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }


        filterChain.doFilter(request, response);
    }
}
