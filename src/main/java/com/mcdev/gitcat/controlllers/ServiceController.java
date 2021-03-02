package com.mcdev.gitcat.controlllers;

import com.mcdev.gitcat.models.Repo;
import com.mcdev.gitcat.models.RepoResponse;
import com.mcdev.gitcat.processors.ServiceExchange;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {
    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    @Autowired
    ServiceExchange serviceExchange;

    /*
     * Add repo info
     * */
    @PostMapping
    @ResponseBody
    public RepoResponse addRepo(@NonNull @RequestBody Repo body) {
        logger.info("Received request to add repo.");
        return serviceExchange.addRepo(body);
    }
}
