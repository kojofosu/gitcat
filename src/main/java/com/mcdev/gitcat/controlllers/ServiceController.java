package com.mcdev.gitcat.controlllers;

import com.mcdev.gitcat.models.Repo;
import com.mcdev.gitcat.models.RepoResponse;
import com.mcdev.gitcat.processors.ServiceExchange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {
  Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
  @Autowired ServiceExchange serviceExchange;

  /*
   * Add repo info
   * */
  @PostMapping
  @ResponseBody
  public RepoResponse addRepo(@NonNull @RequestBody Repo body) {
    logger.info("Received request to add repo.");
    return serviceExchange.addRepo(body);
  }

  /*
   * Get all available repos
   * */
  @GetMapping
  @ResponseBody
  public RepoResponse getAllRepos() {
    logger.info("Received request to get all available repos.");
    return serviceExchange.getAllRepos();
  }
}
