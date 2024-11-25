package com.example.gateways.config;

import com.example.gateways.security.AuthorityConstant;

import com.example.gateways.security.Jwt.JwtFilter;
import com.example.gateways.security.Jwt.TokenProvider;
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
            .securityMatcher("/api/**" )
            .authorizeHttpRequests( authz -> authz
                    .requestMatchers(HttpMethod.DELETE, "/api/usersSecurity/{id}").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/usersSecurity").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/admins").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.GET, "/api/admins/{id}").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.POST, "/api/admins/addAdmin").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.DELETE, "/api/admins/{id}").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.POST, "/api/scooters/addScooter").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.DELETE, "/api/scooters/{id}").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.PUT, "/api/scooters/maintenance/{id}").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.PUT, "/api/scooters/finishMaintenance/{id}").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.POST, "/api/stations/addStation").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.DELETE, "/api/stations/{id}").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.GET, "/api/trips/reportTripsByScooter").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.GET, "/api/users").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.PUT, "/api/users/active/{id}").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.GET, "/api/trips/scootersWithMinTrips").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.GET, "/api/trips/totalInvoiced").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.GET, "/api/scooters/quantity").hasAuthority(AuthorityConstant._ADMIN)
                    .requestMatchers(HttpMethod.PUT, "/api/admins/pricing/{id}/{normalPrice}/{extraPrice}").hasAuthority(AuthorityConstant._ADMIN)
                    .anyRequest().authenticated()
            )
            .httpBasic( Customizer.withDefaults() )
            .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }

}
