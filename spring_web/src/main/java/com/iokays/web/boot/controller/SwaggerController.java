package com.iokays.web.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Swagger控制类")
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    @ApiOperation(value="GET访问",httpMethod = "GET")
    @RequestMapping(value="/get",method = RequestMethod.GET)
    public SwaggerEntity get(SwaggerEntity entity){
        return entity;
    }

    @ApiOperation(value="POST表单访问",httpMethod = "POST")
    @RequestMapping(value="/post/form",method = RequestMethod.POST)
    public SwaggerEntity post(SwaggerEntity entity){
        return entity;
    }

    @ApiOperation(value="POST请求体访问",httpMethod = "POST")
    @RequestMapping(value="/post/body",method = RequestMethod.POST)
    public SwaggerEntity requestBody(@RequestBody  SwaggerEntity entity){
        return entity;
    }

}
