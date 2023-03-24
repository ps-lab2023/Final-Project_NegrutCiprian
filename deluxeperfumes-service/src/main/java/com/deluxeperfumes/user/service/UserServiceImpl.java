package com.deluxeperfumes.user.service;

import com.deluxeperfumes.user.entity.UserEntity;
import com.deluxeperfumes.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserEntity saveUser(UserEntity user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public UserEntity findUser(String username) {
    return userRepository.findUserByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
  }
}

