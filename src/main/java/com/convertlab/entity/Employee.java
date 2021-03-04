package com.convertlab.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author limei
 * @since 2021-03-02
 */
@Data
@ApiModel(value="Employee对象", description="")
@TableName("employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "员工姓名")
    private String empName;

    @ApiModelProperty(value = "联系电话")
    private String tel;

    @ApiModelProperty(value = "所在部门名称")
    private String depart;

    @ApiModelProperty(value = "基础薪资")
    private BigDecimal salary;

    @ApiModelProperty(value = "入职日期")
    private LocalDateTime dateIn;

    @ApiModelProperty(value = "状态：在职 离职")
    private String status;

    @ApiModelProperty(value = "离职日期")
    private LocalDateTime dateOut;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
