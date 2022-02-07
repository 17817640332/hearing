package com.jie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生
 * </p>
 *
 * @author jie
 * @since 2021-03-02
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@ApiModel(value="Student对象", description="学生")
public class Student implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "学生名字")
      private String studentName;

      @ApiModelProperty(value = "用户名")
      private String userName;

      @ApiModelProperty(value = "密码")
      private String password;

      @ApiModelProperty(value = "性别(1男,2女)")
      private Integer sex;

      @ApiModelProperty(value = "学号")
      private String studentNumber;

      @ApiModelProperty(value = "年级")
      private String grade;

      @ApiModelProperty(value = "手机号")
      private String phone;


       @ApiModelProperty(value = "头像非必要")
       @ApiParam(required = false)
     private String face;
}
