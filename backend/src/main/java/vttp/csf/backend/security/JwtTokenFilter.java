// package vttp.csf.backend.security;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import vttp.csf.backend.services.CustomUserDetailsService;

// import java.io.IOException;

// @Component
// public class JwtTokenFilter extends OncePerRequestFilter {

//     private final JwtTokenProvider jwtTokenProvider;
//     private final CustomUserDetailsService customUDS;

//     public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService customUDS) {
//         this.jwtTokenProvider = jwtTokenProvider;
//         this.customUDS = customUDS;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {

//         // Extract JWT token from the request header
//         String token = jwtTokenProvider.resolveToken(request);

//         if (token != null && jwtTokenProvider.validateToken(token)) {
//             // If token is valid, set authentication in the Spring Security context
//             Authentication auth = jwtTokenProvider.getAuthentication(token, customUDS);
//             SecurityContextHolder.getContext().setAuthentication(auth);
//         }

//         filterChain.doFilter(request, response);
//     }
    
// }
