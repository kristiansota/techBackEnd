package com.kristian.test.repository;

import com.kristian.test.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles,Long> {

}
