package com.twbat.blog.common.util.util.domain;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 15:09
 * @desciption
 */
public class BasicVo {

    public static final String AGE_TEXT = "岁";

    public static final String SEX_MAN = "男性";

    public static final String SEX_WOMAN = "女性";

    private static final Integer SEX_MAN_FLAG = 0;

    public static final String getSex(Integer sex) {
        if (SEX_MAN_FLAG.equals(sex)) {
            return SEX_MAN;
        } else {
            return SEX_WOMAN;
        }
    }

}
