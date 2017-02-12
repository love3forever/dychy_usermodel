package com.dychy.service.impl;

import com.dychy.model.Resource;
import com.dychy.repository.ResourceRepository;
import com.dychy.service.dao.IResourceService;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/**
 * Created by eclipse on 2017/2/6.
 */
@Component
public class ResourceService implements IResourceService{
    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

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

    @Override
    public void saveFileRes(Resource resource, InputStream inputStream) {
        String id = gridFsTemplate.store(inputStream, resource.getResDesc(), resource.getResMIME()).getId().toString();
        if (id != null) {
            System.out.println("File: "+resource.getResDesc()+" has been saved!");
            resource.setId(id);
            resource.setResURL("res/getfile/"+id);
            resourceRepository.save(resource);
        }
    }

    @Override
    public GridFSDBFile getFileRes(String fileid) {
        GridFSDBFile gridFsdbFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileid)));
        if (gridFsdbFile != null) {
            return gridFsdbFile;
        }
        return null;
    }
}
