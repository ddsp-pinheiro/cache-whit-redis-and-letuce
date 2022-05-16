package com.basiccrud.service;

import com.basiccrud.entity.BasicEntity;
import com.basiccrud.exception.NotFoundException;
import com.basiccrud.repository.BasicRepository;
import com.basiccrud.util.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class BasicService {

    @Autowired
    private final RedisRepository redisRepository;

    private static final String NAME_NOT_FOUND = "Name not found";
    private static final String ID_NOT_FOUND = "Id not found";

    @Autowired
    private BasicRepository basicRepository;

    public BasicService(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    public BasicEntity save(BasicEntity basicEntity){
        if (basicRepository.findById(basicEntity.getId()).isPresent()){
            throw new DuplicateKeyException(String.format("Id already exists"));
        }
        BasicEntity entitySaved = basicRepository.save(basicEntity);
        return entitySaved;
    }

    public BasicEntity getById(final Long id){
        if (redisRepository.get("id") != null) {
            return (BasicEntity) redisRepository.get("id");

        } else {
            return basicRepository.findById(id).orElseThrow(() -> new NotFoundException(ID_NOT_FOUND));
            redisRepository.put("id",id);
            return (BasicEntity) redisRepository.get("id");
        }
    }

    public BasicEntity getByName(final String name){
        return basicRepository.findByName(name).orElseThrow(() -> new NotFoundException(NAME_NOT_FOUND));
    }

    public void deleteById(Long id){
        basicRepository.deleteById(id);
    }

}
