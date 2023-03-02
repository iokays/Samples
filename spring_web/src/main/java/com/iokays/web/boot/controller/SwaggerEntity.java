package com.iokays.web.boot.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel("Swagger请求对象")
public class SwaggerEntity implements Serializable {

    @ApiModelProperty(name = "id", value="唯一标识符")
    private Integer id;

    @ApiModelProperty(name = "status", value = "状态")
    private SwaggerStatusEnum status;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SwaggerStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SwaggerStatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
