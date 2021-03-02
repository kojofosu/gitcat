package com.mcdev.gitcat.repositories;

import com.mcdev.gitcat.models.Repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Repo, String> {}
