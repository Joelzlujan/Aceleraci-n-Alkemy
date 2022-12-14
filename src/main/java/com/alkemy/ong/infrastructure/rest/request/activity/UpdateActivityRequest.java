package com.alkemy.ong.infrastructure.rest.request.activity;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateActivityRequest {

  @NotEmpty(message = "Content cannot be empty.")
  @CharactersWithWhiteSpaces(message = "Content must contain only spaces and letters.")
  private String content;

  @NotEmpty(message = "Image must be an URL.")
  private String imageUrl;

  @NotEmpty(message = "Name cannot be empty.")
  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  private String name;

}
