package com.xiguo.www.group.repository;

import com.xiguo.www.group.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author ZGC
 * @date Created in 下午 9:00 2018/8/24
 */
@RepositoryRestResource(collectionResourceRel = "testEntity", path = "testEntity")
public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {
}
