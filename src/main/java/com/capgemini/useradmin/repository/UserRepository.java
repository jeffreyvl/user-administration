package com.capgemini.useradmin.repository;

import com.capgemini.useradmin.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

}
