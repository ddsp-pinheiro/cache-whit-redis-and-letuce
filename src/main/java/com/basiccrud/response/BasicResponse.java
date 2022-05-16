package com.basiccrud.response;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class BasicResponse {
    @Length(max = 10, message = "Name can't have more than 10 characters")
    private String name;
}
