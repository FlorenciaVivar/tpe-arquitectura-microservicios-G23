package com.example.gateways.repository;

import com.example.gateways.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
        FROM User u JOIN FETCH u.authorities
        WHERE lower(u.username) =  ?1
    """)
    Optional<User> findOneWithAuthoritiesByUsernameIgnoreCase( String username );
}
