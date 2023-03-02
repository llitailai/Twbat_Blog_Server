package com.twbat.blog.common.web.valid.handler;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.twbat.blog.common.web.valid.annotations.Valid;
import com.twbat.blog.common.web.valid.enums.ValidEnum;
import com.twbat.blog.common.web.valid.enums.ValidExceptionEnum;
import com.twbat.blog.common.web.valid.exception.*;
import com.twbat.blog.common.web.valid.util.DateUtils;
import com.twbat.blog.common.web.valid.util.RegexUtil;
import com.twbat.blog.common.web.valid.util.StringUtils;

public class DefaultHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHandler.class);

    /**
     * 处理校验逻辑函数
     * 根据传入的校验枚举来判断由工厂中的某个对口函数处理
     *
     * @param validEnum 校验枚举 (状态)
     * @param arg       需要校验的参数值
     * @param valid     校验参数上的字段
     * @return boolean
     * true : 校验通过
     * false : 校验不通过
     */
    public static boolean handler(ValidEnum validEnum, Object arg, Valid valid) {
        // 如果用户传入的校验枚举是判断参数是否为空,则选择isNotNull函数进行处理
        if (validEnum.ordinal() == ValidEnum.VALID.ordinal()) {
            return DefaultHandlerFactory.isNotNull(arg);
        }
        // switch选择与首if一致
        switch (validEnum) {
            case TEL:
                // 校验手机号是否合法
                return DefaultHandlerFactory.isTel(arg, valid.regex().tel());
            case PASSWORD:
                // 校验密码是否合法
                return DefaultHandlerFactory.isPassword(arg, valid.regex().password());
            case MAX:
                // 校验是否超出最大限制
                return DefaultHandlerFactory.isMax(arg, valid.max().max());
            case MIN:
                // 校验是否超出最小限制
                return DefaultHandlerFactory.isMin(arg, valid.min().min());
            case LENGTH:
                // 校验字符串长度是否小于传入参数
                return DefaultHandlerFactory.validLength(arg, valid.lengthLt());
            case LENGTH_GT:
                // 校验字符串长度是否大于传入参数
                return !DefaultHandlerFactory.validLength(arg, valid.lengthLt());
            case EMAIL:
                return DefaultHandlerFactory.isEmail(arg,valid.regex().email());
            default:
                return true;
        }
    }

    /**
     * 校验验证是否通过
     * 如果未通过则抛出对应枚举中选择触发的异常
     * flag 为true 则代表校验成功
     * flag 为false 则代表校验失败
     *
     * @param flag          校验成功或失败标识
     * @param exceptionEnum 当校验失败时选择触发的异常
     */
    public static void valid(boolean flag, ValidExceptionEnum exceptionEnum) {
        if (!flag) {
            DefaultHandlerFactory.throwsException(exceptionEnum);
        }
    }


    private static class DefaultHandlerFactory {

        /**
         * 校验参数是否为空
         *
         * @param arg 参数
         * @return boolean
         * true : 不为空
         * false : 为空
         */
        private static final boolean isNotNull(Object arg) {
            return StringUtils.isNotEmpty(arg);
        }

        /**
         * 校验参数是否符合格式
         *
         * @param arg   参数
         * @param regex 正则表达式 (可以为空,为空则使用默认正则表达式)
         * @return boolean
         * true : 符合
         * false : 不符合
         */
        private static final boolean isTel(Object arg, String regex) {
            // 首先校验传入的参数是否为String类型,如果非String类型直接返回false
            if (arg instanceof String) {
                return RegexUtil.isTel(arg.toString(), regex);
            }
            return false;
        }

        /**
         * 校验传入的参数是否符合密码格式
         *
         * @param arg   参数
         * @param regex 正则表达式 (可以为空,为空则使用默认正则表达式)
         * @return
         */
        private static final boolean isPassword(Object arg, String regex) {
            if (arg instanceof String) {
                return RegexUtil.isPassword(arg.toString(), regex);
            }
            return false;
        }

        /**
         * 校验传入的参数是否小于Max最大限制
         * 如果小于则返回true
         * 反之返回false
         *
         * @param arg        传入的参数
         * @param compareNum max最大限制值
         * @return boolean
         * true 小于
         * false 大于
         */
        private static final boolean isMax(Object arg, Object compareNum) {
            if (RegexUtil.isNumber(arg)) {
                return ((Number) arg).longValue() < ((Number) compareNum).longValue();
            }
            return false;
        }


        /**
         * 校验传入的参数是否小于Min最小限制
         * 如果大于则返回true
         * 反之返回false
         *
         * @param arg        传入的参数
         * @param compareNum min最小限制
         * @return boolean
         * true 小于
         * false 大于
         */
        private static final boolean isMin(Object arg, Object compareNum) {
            if (RegexUtil.isNumber(arg)) {
                return !isMax(arg, compareNum);
            }
            return false;
        }


        /**
         * 校验字符串是否符合长度
         * 如某些评论,备注都有限制要求
         *
         * @param arg
         * @param length
         * @return
         */
        private static final boolean validLength(Object arg, int length) {
            if (arg instanceof String) {
                return ((String) arg).length() < length;
            }
            return false;
        }

        private static final boolean isEmail(Object arg,String regex) {
            if (arg instanceof String) {
                return RegexUtil.isEmail(arg.toString(), regex);
            }
            return false;
        }

        /**
         * 抛出异常函数
         * 通过选择的校验枚举抛出对应的异常
         *
         * @param exceptionEnum 异常枚举
         * @throws ParamCanNotBeNullException          参数为空异常
         * @throws TelFormatException                  电话格式异常
         * @throws PasswordFormatException             密码格式异常
         * @throws ValueExceedsMaxTheLimitException    超出最大限制异常
         * @throws ValueExceedsMinTheLimitException    超出最小限制异常
         * @throws ValueExceedsLengthTheLimitException 超出字符串最大长度异常
         */
        private static final void throwsException(ValidExceptionEnum exceptionEnum) {
            switch (exceptionEnum) {
                case PARAM_CAN_NOT_BE_NULL_EXCEPTION_ENUM:
                    // 因选择参数为空异常枚举 所以抛出参数为空异常
                    LOGGER.warn("参数为空,异常时间:{}", DateUtils.getTime());
                    throw new ParamCanNotBeNullException(exceptionEnum);
                case TEL_FORMAT_EXCEPTION_ENUM:
                    // 因选择电话格式异常枚举 所以抛出电话格式异常
                    LOGGER.warn("电话格式错误,异常时间:{}", DateUtils.getTime());
                    throw new TelFormatException(exceptionEnum);
                case PASSWORD_FORMAT_EXCEPTION_ENUM:
                    // 因选择密码格式异常枚举 所以抛出密码格式异常
                    LOGGER.warn("密码格式错误,异常时间:{}", DateUtils.getTime());
                    throw new PasswordFormatException(exceptionEnum);
                case VALUE_EXCEEDS_MAX_THE_LIMIT_EXCEPTION_ENUM:
                    // 因选择超出最大长度限制异常枚举 所以抛出最大长度限制异常
                    LOGGER.warn("超出最大长度限制,异常时间:{}", DateUtils.getTime());
                    throw new ValueExceedsMaxTheLimitException(exceptionEnum);
                case VALUE_EXCEEDS_MIN_THE_LIMIT_EXCEPTION_ENUM:
                    // 因选择超出最小长度限制异常枚举 所以抛出最小限制异常
                    LOGGER.warn("超出最小长度限制,异常时间:{}", DateUtils.getTime());
                    throw new ValueExceedsMinTheLimitException(exceptionEnum);
                case VALUE_EXCEEDS_LENGTH_THE_LIMIT_EXCEPTION_ENUM:
                    // 因选择超出字符串长度最大限制异常 所以抛出超出字符串长度最大限制异常
                    LOGGER.warn("超出字符串最大长度限制,异常时间:{}", DateUtils.getTime());
                    throw new ValueExceedsLengthTheLimitException(exceptionEnum);
            }
        }
    }


}
