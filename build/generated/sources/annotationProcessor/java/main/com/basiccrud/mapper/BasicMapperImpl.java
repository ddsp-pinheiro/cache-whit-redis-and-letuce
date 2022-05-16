package com.basiccrud.mapper;

import com.basiccrud.entity.BasicEntity;
import com.basiccrud.request.BasicRequest;
import com.basiccrud.response.BasicResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-16T12:57:15-0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class BasicMapperImpl implements BasicMapper {

    @Override
    public BasicResponse toResponse(BasicEntity basicEntity) {
        if ( basicEntity == null ) {
            return null;
        }

        BasicResponse basicResponse = new BasicResponse();

        basicResponse.setName( basicEntity.getName() );

        return basicResponse;
    }

    @Override
    public BasicEntity toEntity(BasicRequest basicRequest, Long id) {
        if ( basicRequest == null && id == null ) {
            return null;
        }

        BasicEntity basicEntity = new BasicEntity();

        if ( basicRequest != null ) {
            basicEntity.setName( basicRequest.getName() );
        }
        if ( id != null ) {
            basicEntity.setId( id );
        }

        return basicEntity;
    }
}
