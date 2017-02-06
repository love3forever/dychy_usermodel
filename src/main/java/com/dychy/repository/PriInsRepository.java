package com.dychy.repository;

import com.dychy.model.PrivilegeIns;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eclipse on 2017/1/11.
 */
@Repository
public interface PriInsRepository extends MongoRepository<PrivilegeIns,String> {
    PrivilegeIns findByresId(String resid);
    PrivilegeIns findByorId(String orid);

    PrivilegeIns findByid(String id);

    List<PrivilegeIns> findByuserid(String uid);
}
