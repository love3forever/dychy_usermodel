package com.dychy.repository;

import com.dychy.model.PrivilegeIns;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by eclipse on 2017/1/11.
 */
public interface PriInsRepository extends MongoRepository<PrivilegeIns,String> {
    PrivilegeIns findByresId(String resid);
    PrivilegeIns findByorId(String orid);

    PrivilegeIns findByid(String id);
}
