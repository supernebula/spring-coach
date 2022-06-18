package com.evol.restdata.dao;

import com.evol.restdata.domain.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "user", path = "user", excerptProjection = UserDtoExcerpt.class)
public interface UserRepository extends JpaRepository<UserDTO, Long> {
    @RestResource(path = "name", rel = "name")
    List<UserDTO> findByFirstName(@Param("name") String name);
}
