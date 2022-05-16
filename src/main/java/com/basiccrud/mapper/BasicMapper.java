package com.basiccrud.mapper;

import com.basiccrud.entity.BasicEntity;
import com.basiccrud.request.BasicRequest;
import com.basiccrud.response.BasicResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        mappingControl = DeepClone.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasicMapper {

     BasicMapper INSTANCE = Mappers.getMapper(BasicMapper.class);
     BasicResponse toResponse(BasicEntity basicEntity);

     @Mappings({
             @Mapping(target = "id", source = "id")
     })
    BasicEntity toEntity(BasicRequest basicRequest, Long id);


}
