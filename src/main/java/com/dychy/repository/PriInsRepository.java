package com.dychy.repository;

import com.dychy.model.PrivilegeIns;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eclipse on 2017/1/11.
 * 继承MongoRepository，自动实现一些对权限实例操作的简单方法，只需要方法名按照制定格式即可
 * findBy + 字段名
 */
@Repository
public interface PriInsRepository extends MongoRepository<PrivilegeIns,String> {
    PrivilegeIns findByresId(String resid);
    PrivilegeIns findByorId(String orid);

    PrivilegeIns findByid(String id);

    List<PrivilegeIns> findByuserid(String uid);
}
