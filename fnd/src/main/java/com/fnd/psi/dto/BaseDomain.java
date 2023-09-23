package com.fnd.psi.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc:
 * @See:
 */
@Getter
@Setter
public class BaseDomain implements Serializable {
    /**
     * 主键 默认主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    /**
     *  主键
     */
    private Long id;
    /**
     * 创建时间 gmt_create
     */
    private Date gmtCreate;
    /**
     * 修改时间 gmt_modified
     */
    private Date gmtModified;
    /**
     * 是否删除 is_deleted
     */
    @TableLogic
    private Integer isDeleted;

    public void  addNecessaryProperties(){
        this.setGmtCreate(new Date());
        this.setGmtModified(new Date());
    }

    public void  updateNecessaryProperties(){
        this.setGmtModified(new Date());
    }

}
