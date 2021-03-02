package com.mcdev.gitcat.models;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RepoResponse {
  private HttpStatus status;
  private String errorMessage;
  private String timestamp;
  private List<Repo> repos;
}
