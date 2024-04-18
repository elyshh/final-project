// package vttp.csf.backend.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

// import vttp.csf.backend.security.CustomAccessDeniedHandler;
// import vttp.csf.backend.security.JwtAuthenticationEntryPoint;
// import vttp.csf.backend.security.JwtTokenFilter;
// import vttp.csf.backend.security.JwtTokenProvider;
// import vttp.csf.backend.services.CustomUserDetailsService;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private CustomUserDetailsService customUDS;

//     @Bean
//     public JwtTokenFilter jwtTokenFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService customUDS) {
//         return new JwtTokenFilter(jwtTokenProvider, customUDS);
//     }

//     @Bean
//     public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
//         return new JwtAuthenticationEntryPoint();
//     }

//     @Bean
//     public JwtTokenProvider jwtTokenProvider() {
//         return new JwtTokenProvider();
//     }

//     @Bean
//     public CustomAccessDeniedHandler customAccessDeniedHandler() {
//         return new CustomAccessDeniedHandler();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//                 .exceptionHandling(customizer -> customizer.authenticationEntryPoint(jwtAuthenticationEntryPoint()))
//                 .addFilterBefore(new JwtTokenFilter(jwtTokenProvider(), customUDS), BasicAuthenticationFilter.class)
//                 .csrf(AbstractHttpConfigurer::disable)
//                 .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                 .authorizeHttpRequests((requests) -> requests
//                         .requestMatchers(HttpMethod.POST, "/api/login", "/api/register").permitAll()
//                         .anyRequest().authenticated()
//                 );
//         return http.build();
//     }
// }
