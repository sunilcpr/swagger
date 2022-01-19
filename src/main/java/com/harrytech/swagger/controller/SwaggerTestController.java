package com.harrytech.swagger.controller;


import com.harrytech.swagger.model.domain.UserModel;
import com.harrytech.swagger.model.requestModel.UserRequestModel;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = {"testing"})
public class SwaggerTestController {


    @GetMapping(value = "/getAll")
    public ResponseEntity<Object> getAll() {
        UserModel userModel = new UserModel();
        userModel.setId(1);
        userModel.setName("sunil");
        userModel.setEmail("test@gmail.com");
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody UserRequestModel userRequestModel) {

        return new ResponseEntity<>(userRequestModel, HttpStatus.OK);
    }
}
