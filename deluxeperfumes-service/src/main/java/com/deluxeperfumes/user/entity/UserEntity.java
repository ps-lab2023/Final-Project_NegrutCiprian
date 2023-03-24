package com.deluxeperfumes.user.entity;

import com.deluxeperfumes.entity.BaseEntity;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

  @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(100)", unique = true)
  private String username;

  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> roles;

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.getRoles().stream()
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

}