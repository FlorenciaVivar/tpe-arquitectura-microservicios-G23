package com.example.gateways.config;

import com.example.gateways.security.AuthotityConstant;

import com.example.gateways.security.JWT.JwtFilter;
import com.example.gateways.security.JWT.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private TokenProvider tokenProvider;

    public SecurityConfig(TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( final HttpSecurity http ) throws Exception {
        http
            .csrf( AbstractHttpConfigurer::disable );
        http
            .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
            .securityMatcher("/api/admins/**" )
            .authorizeHttpRequests( authz -> authz
                    .requestMatchers(HttpMethod.GET, "/admins").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/admins/{id}").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/admins/addAdmin").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/admins/{id}").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/admins/scooter/addScooter").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/admins/scooter/deleteScooter/{id}").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/admins/scooter/maintenance/{id}").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/admins/scooter/finishMaintenance/{id}").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/admins/stations/addStation").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/admins/stations/deleteStation/{id}").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/admins/trips/reportTripsByScooter").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/admins/users/active/{id}").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/admins/trips/scootersWithMinTrips").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/admins/trips/totalInvoiced").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/admins/scooters/quantity").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/admins/pricing/{id}/{normalPrice}/{extraPrice}").hasAuthority("ROLE_ADMIN")
                    .anyRequest().authenticated()
            )
            .httpBasic( Customizer.withDefaults() )
            .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }

}
