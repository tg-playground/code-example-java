package com.taogen.springcloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.time.Duration;
import java.util.Arrays;

/**
 * @author taogen
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, ClientRegistrationRepository clientRegistrationRepository) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/logout", "/unauthorized").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/keycloak")
                        .defaultSuccessUrl("/", true)
                )
                // POST /logout
                .logout(logout -> logout
                                .logoutSuccessHandler(oidcLogoutSuccessHandler(clientRegistrationRepository))
//                .logout(logout -> logout
//                                .logoutUrl("/logout")
//                                .logoutSuccessUrl("/login?logoutSuccess=true")
//                        .logoutSuccessUrl("http://localhost:8081/realms/my-realm/protocol/openid-connect/logout?redirect_uri=http://localhost:8082/unauthorized")
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .deleteCookies("JSESSIONID")
                );
        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
//            configuration.setAllowCredentials(true);
            configuration.setAllowedOrigins(Arrays.asList("*"));
//            configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:[*]", "http://192.168.0.*:[*]"));
            configuration.setAllowedMethods(Arrays.asList("*"));
//            configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PATCH", "DELETE", "PUT", "PATCH"));
            configuration.setAllowedHeaders(Arrays.asList("*"));
//            configuration.setAllowedHeaders(Arrays.asList("Origin", "X-Requested-With", "Content-Type", "Accept"));
            configuration.setMaxAge(Duration.ofHours(24L));
            return configuration;
        }));
        return http.build();
    }

    @Bean
    public LogoutSuccessHandler oidcLogoutSuccessHandler(ClientRegistrationRepository clientRegistrationRepository) {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8082/"); // Or a specific URI
        return oidcLogoutSuccessHandler;
    }
}
