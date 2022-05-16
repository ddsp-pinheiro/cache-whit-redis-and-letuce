package com.basiccrud.controller;

import com.basiccrud.entity.BasicEntity;
import com.basiccrud.mapper.BasicMapper;
import com.basiccrud.request.BasicRequest;
import com.basiccrud.response.BasicResponse;
import com.basiccrud.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/basiccrud/test-cache/")
public class BasicController {

    @Autowired
    private BasicMapper basicMapper;

    @Autowired
    private BasicService basicService;

    private static final String ID = "x-id";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BasicResponse post(@Valid @RequestBody BasicRequest basicRequest,
                                @RequestHeader(ID) Long id){
        BasicEntity entity = basicService.save(basicMapper.toEntity(basicRequest,
                id));
        return basicMapper.toResponse(entity);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BasicResponse getId(@PathVariable Long id){
        BasicEntity entity = basicService.getById(id);
        return basicMapper.toResponse(entity);
    }

    @GetMapping("name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public BasicResponse getName(@PathVariable String name){
        BasicEntity entity = basicService.getByName(name);
        return basicMapper.toResponse(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteId(@PathVariable Long id){
        basicService.deleteById(id);
    }

}
