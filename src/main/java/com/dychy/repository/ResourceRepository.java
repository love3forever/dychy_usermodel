package com.dychy.repository;

import com.dychy.model.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eclipse on 2017/2/6.
 */
@Repository
public interface ResourceRepository extends MongoRepository<Resource,String> {
    Resource findByid(String id);

    List<Resource> findByresType(int type);

    List<Resource> findByownerId(String ownerid);

    List<Resource> findBydepId(String depId);
}
