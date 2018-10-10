package com.xiguo.www.group.repository.user;

import com.xiguo.www.group.entity.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "userAuthentications", path = "userAuthentications")
public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Long> {

    UserAuthentication findByUser_id(@Param("userId") Long userId);

}
