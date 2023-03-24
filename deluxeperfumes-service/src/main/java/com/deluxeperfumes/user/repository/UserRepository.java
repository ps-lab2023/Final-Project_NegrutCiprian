package com.deluxeperfumes.user.repository;


import java.util.Optional;
import com.deluxeperfumes.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByUsername(String username);
}
