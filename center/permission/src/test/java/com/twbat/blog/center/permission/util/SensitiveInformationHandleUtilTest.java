package com.twbat.blog.center.permission.util;

import org.junit.jupiter.api.Test;

/**
 * @author darkltl
 * @className SensitiveInformationHandleUtilTest
 * @email darkltl@163.com
 * @date 2021/7/22 - 14:28
 * @description
 */

public class SensitiveInformationHandleUtilTest {


    @Test
    public void testHandleMethodNoParam() {
        System.out.println(SensitiveInformationHandleUtil.handle(""));
        System.out.println(SensitiveInformationHandleUtil.handle(null));
        System.out.println(SensitiveInformationHandleUtil.handle("a"));
        System.out.println(SensitiveInformationHandleUtil.handle("ab"));
        System.out.println(SensitiveInformationHandleUtil.handle("abc"));
        System.out.println(SensitiveInformationHandleUtil.handle("a_b_c_d"));
    }

    @Test
    public void testHandleMethodWithParam() {
        System.out.println(SensitiveInformationHandleUtil.handle("", 0));
        System.out.println(SensitiveInformationHandleUtil.handle(null, 1));
        System.out.println(SensitiveInformationHandleUtil.handle("a", 2));
        System.out.println(SensitiveInformationHandleUtil.handle("ab", 3));
        System.out.println(SensitiveInformationHandleUtil.handle("abc", 4));
        System.out.println(SensitiveInformationHandleUtil.handle("a_b_c_d", 5));
        System.out.println(SensitiveInformationHandleUtil.handle("a_b_c_d_e_f_h_d_fg_s-_senti", 20));
    }
}
