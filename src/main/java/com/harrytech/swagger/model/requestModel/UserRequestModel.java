package com.harrytech.swagger.model.requestModel;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserRequestModel {


    @ApiModelProperty(notes = "SearchId", example = "1", required = true, position = 1)
    private Integer searchId;

    private Integer id;


    private String firstName;

    private String lastName;

    private String email;
}
