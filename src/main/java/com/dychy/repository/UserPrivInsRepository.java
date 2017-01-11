package com.dychy.repository;

import com.dychy.model.UserPriRel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by eclipse on 2017/1/11.
 */
public interface UserPrivInsRepository extends MongoRepository<UserPriRel,String> {
    List<UserPriRel> findByuserId(String userid);

    List<UserPriRel> findBypriInsId(String priInsId);

//    UserPriRel findByuserIdAndpriInsId(String userid, String priInsId);
}
