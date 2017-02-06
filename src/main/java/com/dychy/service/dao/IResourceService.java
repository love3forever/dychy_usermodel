package com.dychy.service.dao;

import com.dychy.model.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eclipse on 2017/2/6.
 */
@Service
public interface IResourceService {
    Resource getResbyid(String resid);


    List<Resource> getResbyOwnerid(String ownerid);


    List<Resource> getResbyDepid(String depId);

    List<Resource> getResbyType(int type);

    void saveRes(Resource resource);
}
