package com.xiguo.www.group.repository.user;

import com.xiguo.www.group.entity.Noutoasiakas;
import com.xiguo.www.group.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


/**
 * @author ZGC
 * @date Created in 下午 9:00 2018/8/24
 */
@RepositoryRestResource(collectionResourceRel = "noutoasiakases", path = "noutoasiakases")
public interface NoutoasiakasRepository extends JpaRepository<Noutoasiakas, Long> {
    // noutoasiakases
    //

    /**
     * api/notoasiakas/search/findByUserId?userId=xxx
     * 根据用户名查找
     * @param userId 用户对象
     * @return 指定条件的所有noutoasiakas
     */
    @RestResource(path = "findByUserId", rel = "findByUserId")
    @Query(value="select * from Noutoasiakas where user_id=?1",nativeQuery=true)
    List<Noutoasiakas> findByUserId(@Param("userId") Long userId);


}
