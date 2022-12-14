package com.alkemy.ong.infrastructure.rest.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterUserResponse {

  private String firstName;
  private String lastName;
  private String email;
  private String token;

}
