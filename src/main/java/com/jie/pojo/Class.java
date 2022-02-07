package com.jie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @author jie
 * @since 2021-03-02
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@ApiModel(value="Class对象", description="")
public class Class implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "id")
        @TableId(value = "id", type = IdType.ID_WORKER_STR)
      private Integer id;

      @ApiModelProperty(value = "班级名称")
      private String className;

      @ApiModelProperty(value = "班级人数")
      private Integer studentAmount;

      @ApiModelProperty(value = "创建者")
      private Integer teacherId;

     @ApiModelProperty(value = "班级邀请码")
     private Integer code;
}
