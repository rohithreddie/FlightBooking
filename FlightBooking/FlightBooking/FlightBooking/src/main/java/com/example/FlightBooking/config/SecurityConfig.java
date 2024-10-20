package com.example.FlightBooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // Create users with roles
        UserDetails admin = User.withUsername("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("user")
                .password("user123")
                .roles("USER")
                .build();

        manager.createUser(admin);
        manager.createUser(user);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();  // Simple password encoder (not recommended for production)
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for APIs and H2 console
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/h2-console/**").permitAll()  // Allow unrestricted access to H2 console
                        .requestMatchers("/api/flights/**").hasRole("ADMIN")  // Only ADMIN can access flight APIs
                        .requestMatchers("/api/bookings/**", "/api/passengers/**").hasAnyRole("ADMIN", "USER")  // Allow ADMIN and USER
                        .anyRequest().authenticated()  // All other requests require authentication
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())  // Allow frames from same origin (H2 console)
                )
                .httpBasic(withDefaults());  // Enable Basic Authentication

        return http.build();
    }
}
