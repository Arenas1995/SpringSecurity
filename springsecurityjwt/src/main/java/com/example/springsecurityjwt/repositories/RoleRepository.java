package com.example.springsecurityjwt.repositories;

import com.example.springsecurityjwt.models.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    List<RoleEntity> findRoleEntitiesByNameIn(List<String> roleNames);
}
