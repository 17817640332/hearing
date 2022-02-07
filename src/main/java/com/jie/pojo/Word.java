package com.jie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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
@ApiModel(value="Word对象", description="")
public class Word implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "书本名称")
      private String book;

      @ApiModelProperty(value = "单元")
      private String unit;

      @ApiModelProperty(value = "单词名")
      private String wordName;

      @ApiModelProperty(value = "单词地址")
      private String wordUrl;

      @JsonIgnore
      @TableField(exist = false)
      @ApiModelProperty(value = "单词")
      private List<TaskWord> words;

      @ApiModelProperty(value = "新加")
      private String isSelect;
}
