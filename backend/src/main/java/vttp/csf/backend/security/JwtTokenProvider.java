// package vttp.csf.backend.security;

// import java.util.Date;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.ExpiredJwtException;
// import io.jsonwebtoken.Jws;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.MalformedJwtException;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.SignatureException;
// import io.jsonwebtoken.UnsupportedJwtException;
// import jakarta.servlet.http.HttpServletRequest;
// import vttp.csf.backend.services.CustomUserDetailsService;

// @Component
// public class JwtTokenProvider {
    
//     @Value("${jwt.secret}")
//     private String jwtSecret;

//     @Value("${jwt.expiration}")
//     private int jwtExpirationInMs;

//     public String generateToken(String username) {
//         Date now = new Date();
//         Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

//         return Jwts.builder()
//                 .setSubject(username)
//                 .setIssuedAt(now)
//                 .setExpiration(expiryDate)
//                 .signWith(SignatureAlgorithm.HS256, jwtSecret)
//                 .compact();
//     }

//     public String resolveToken(HttpServletRequest request) {
//         String authorizationHeader = request.getHeader("Authorization");
//         if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//             return authorizationHeader.substring(7);
//         }
//         return null;
//     }

//     public boolean validateToken(String authToken) {
//         try {
//             Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//             return !claims.getBody().getExpiration().before(new Date());
//         } catch (SignatureException ex) {
//             throw new RuntimeException("Invalid JWT signature", ex); // Invalid signature
//         } catch (MalformedJwtException ex) {
//             throw new RuntimeException("Malformed JWT token", ex); // Invalid JWT token
//         } catch (ExpiredJwtException ex) {
//             throw new RuntimeException("Expired JWT token", ex); // Expired JWT token
//         } catch (UnsupportedJwtException ex) {
//             throw new RuntimeException("Unsupported JWT token", ex); // Unsupported JWT token
//         } catch (IllegalArgumentException ex) {
//             throw new RuntimeException("Empty or null JWT token", ex); // Empty or null JWT token
//         }
//     }

//     public Authentication getAuthentication(String token, CustomUserDetailsService customUDS) {
//         String username = getUsernameFromToken(token);
//         UserDetails userDetails = customUDS.loadUserByUsername(username);
//         return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//     }

//     public String getUsernameFromToken(String token) {
//         Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
//         return claims.getSubject();
//     }
    
// }
