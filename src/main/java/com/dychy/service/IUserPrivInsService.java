package com.dychy.service;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.UserPriRel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eclipse on 2017/1/11.
 */
@Service
public interface IUserPrivInsService {
    List<PrivilegeIns> getPrivsByUserId(String userid);

    boolean saveUserPrivsRel(UserPriRel userPriRel);

    boolean deleteUserPrivsRelWithUserid(String userid);

    boolean isUserHasPrivs(String userid, String privid);

}
