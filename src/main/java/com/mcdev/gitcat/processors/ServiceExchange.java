package com.mcdev.gitcat.processors;

import com.mcdev.gitcat.models.Repo;
import com.mcdev.gitcat.models.RepoResponse;
import com.mcdev.gitcat.repositories.ServiceRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class ServiceExchange {
    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceExchange(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public RepoResponse addRepo(Repo body) {
        /*
        * todo
        *  1.   get the author from the url if author filed is empty or null
        *   2.  make sure url has github string in it
        *   3.  check for duplicates*/
        RepoResponse response = new RepoResponse();
        try {
            response.setRepos(Collections.singletonList(serviceRepository.save(body)));
            response.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Error occurred adding repo : " + e);
            response.setErrorMessage("Error occurred adding repo : " + e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setTimestamp(new Date().toString());
        return response;
    }
}
