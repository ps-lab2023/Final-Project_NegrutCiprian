package com.deluxeperfumes.user.exceptions;

import org.springframework.security.core.AuthenticationException;

public class IncorrectPasswordException extends AuthenticationException {

  public IncorrectPasswordException(String t) {
    super(t);
  }

}
