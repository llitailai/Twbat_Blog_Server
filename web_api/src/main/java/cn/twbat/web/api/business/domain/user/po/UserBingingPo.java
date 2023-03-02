package cn.twbat.web.api.business.domain.user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 21:28
 * @desciption
 * 用户绑定信息PO实体
 */
@Data
@TableName("user_binging")
public class UserBingingPo {

    /**
     * 用户绑定表主键ID
     */
    @TableId(value = "bind_id",type = IdType.AUTO)
    private Integer bindId;

    /**
     * 绑定值
     */
    @TableField("bind_value")
    private String bindValue;

    /**
     * 绑定类型,0手机 1邮箱 2qq 3微信
     */
    @TableField("bind_type")
    private Integer bindType;

    /**
     * 用户主键ID(关联)
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 绑定时间
     */
    @TableField("bind_time")
    private Date bindTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
}
