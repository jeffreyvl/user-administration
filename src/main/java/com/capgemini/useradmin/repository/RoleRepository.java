package com.capgemini.useradmin.repository;

import com.capgemini.useradmin.model.domain.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
}
