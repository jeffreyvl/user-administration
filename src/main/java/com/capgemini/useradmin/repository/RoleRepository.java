package com.capgemini.useradmin.repository;

import com.capgemini.useradmin.model.domain.Role;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>, QueryByExampleExecutor<Role> {

    List<Role> findByName(@Param("name") String name);

}
