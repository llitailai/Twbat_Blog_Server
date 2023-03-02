package cn.twbat.web.api.business.domain.user.dto;

import com.twbat.blog.common.web.valid.annotations.Valid;
import lombok.Data;


/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 22:21
 * @desciption
 */
@Data
public class VerificationCodeDto {

    @Valid(email = true)
    private String email;

    @Valid
    private String code;
}
