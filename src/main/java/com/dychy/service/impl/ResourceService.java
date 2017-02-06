package com.dychy.service.impl;

import com.dychy.model.Resource;
import com.dychy.repository.ResourceRepository;
import com.dychy.service.dao.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by eclipse on 2017/2/6.
 */
@Component
public class ResourceService implements IResourceService{
    @Autowired
    private ResourceRepository resourceRepository;


    @Override
    public Resource getResbyid(String resid) {
        return resourceRepository.findByid(resid);
    }

    @Override
    public List<Resource> getResbyOwnerid(String ownerid) {
        return resourceRepository.findByownerId(ownerid);
    }

    @Override
    public List<Resource> getResbyDepid(String depId) {
        return resourceRepository.findBydepId(depId);
    }

    @Override
    public List<Resource> getResbyType(int type) {
        return resourceRepository.findByresType(type);
    }

    @Override
    public void saveRes(Resource resource) {
        resourceRepository.save(resource);
    }
}
