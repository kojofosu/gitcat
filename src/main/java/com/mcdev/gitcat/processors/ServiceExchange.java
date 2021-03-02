package com.mcdev.gitcat.processors;

import com.mcdev.gitcat.config.Constants;
import com.mcdev.gitcat.models.Repo;
import com.mcdev.gitcat.models.RepoResponse;
import com.mcdev.gitcat.repositories.ServiceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.*;

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
            if (body.getUrl().trim().isEmpty()) {
                logger.info("Repo's URL cannot be empty or null");
                response.setErrorMessage("Repo's URL cannot be empty or null");
                response.setStatus(HttpStatus.BAD_REQUEST);
            } else if (!body.getUrl().contains(Constants.GITHUB_URL_TEMPLATE)) {
                logger.info("Invalid github URL");
                response.setErrorMessage("Invalid github URL");
                response.setStatus(HttpStatus.BAD_REQUEST);
            } else if (body.getAuthor() == null || body.getAuthor().trim().isEmpty()) {
                //get author from url
                URI uri = new URI(body.getUrl());
                String[] path = uri.getPath().split("/", 0);
                List<String> getAuthor = new ArrayList<>(Arrays.asList(path));
                String author = getAuthor.get(1);   //this is the index to get author name

                body.setAuthor(author.trim());
                body.setUrl(body.getUrl().trim());
                response.setRepos(Collections.singletonList(serviceRepository.save(body)));
                response.setStatus(HttpStatus.OK);
            } else {
                body.setAuthor(body.getAuthor().trim());
                body.setUrl(body.getUrl().trim());
                response.setRepos(Collections.singletonList(serviceRepository.save(body)));
                response.setStatus(HttpStatus.OK);
            }

        } catch (Exception e) {
            logger.info("Error occurred adding repo : " + e);
            response.setErrorMessage("Error occurred adding repo : " + e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setTimestamp(new Date().toString());
        return response;
    }
}
