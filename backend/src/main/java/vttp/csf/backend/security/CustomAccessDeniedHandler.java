// package vttp.csf.backend.security;

// import java.io.IOException;

// import org.springframework.http.HttpStatus;
// import org.springframework.security.access.AccessDeniedException;
// import org.springframework.security.web.access.AccessDeniedHandler;
// import org.springframework.stereotype.Component;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    
//     @Override
//     public void handle(HttpServletRequest request,
//                        HttpServletResponse response,
//                        AccessDeniedException accessDeniedException) throws IOException, ServletException {
//         ObjectMapper objectMapper = new ObjectMapper();
//         String jsonResponse = objectMapper.writeValueAsString(apiResponse);
//         response.setContentType("application/json");
//         response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//         response.getWriter().write(jsonResponse);
//     }
    
// }
