package com.capgemini.useradmin.repository;

import com.capgemini.useradmin.model.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, QueryByExampleExecutor<User> {

}
