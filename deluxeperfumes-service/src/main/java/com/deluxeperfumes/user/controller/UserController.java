package com.deluxeperfumes.user.controller;

import com.deluxeperfumes.user.security.utils.JwtService;
import com.deluxeperfumes.user.security.tokens.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  @Autowired
  private JwtService jwtService;


  @PostMapping("/login")
  public Token loginUser(String username, String password) {
    return jwtService.getTokensAtLogin(username, password);
  }

  @PostMapping("/register")
  public Token registerUser(String username, String password, String confirmPassword) {
    return jwtService.getTokensAtRegister(username, password, confirmPassword);
  }

  @PostMapping("/refresh")
  public Token refreshAccessToken(@RequestBody String refreshToken) {
    return jwtService.getTokensAtRefresh(refreshToken);
  }

  @GetMapping("/profile")
  @PreAuthorize("hasRole('USER')")
  public String profile() {
    return "profile page";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String admin() {
    return "admin page";
  }

}
