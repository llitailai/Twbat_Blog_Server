package cn.twbat.logger.core.log.util;

/**
 * @author darkltl
 * @className SensitiveInfomationHandleUtil
 * @email darkltl@163.com
 * @date 2021/7/22 - 14:01
 * @description 敏感信息处理工具
 * @test 2021-07-22
 * unittest 100%
 */
public class SensitiveInformationHandleUtil {

    private static final String EMPTY_STRING = "";

    private static final int DEFAULT_ASTERISK = 6;

    private static final int PREFIX_LENGTH = 3;

    private static final int SUFFIX_LENGTH = 2;

    public static String handle(final String sensitiveInformation) {
        return handle(sensitiveInformation, DEFAULT_ASTERISK);
    }


    public static String handle(String sensitiveInformation, final int asterisk) {
        if (!valid(sensitiveInformation)) {
            return EMPTY_STRING;
        }
        String prefix = "";
        String suffix = "";
        final int sensitiveInformationLength = sensitiveInformation.length();
        // 如果传入字符串整个长度小于2,直接返回*号
        if (sensitiveInformationLength < SUFFIX_LENGTH) {
            return generateAsterisk(asterisk);
        }
        // 如果传入字符串长度等于2 或者 传入字符串长度小于前缀+后缀之和 ,前尾个取一份加上星号返回
        if (sensitiveInformationLength == SUFFIX_LENGTH || sensitiveInformationLength < SUFFIX_LENGTH + PREFIX_LENGTH) {
            prefix = sensitiveInformation.substring(0, 1);
            suffix = sensitiveInformation.substring(1, 2);
            return prefix + generateAsterisk(asterisk) + suffix;
        }
        prefix = sensitiveInformation.substring(0, PREFIX_LENGTH);
        suffix = sensitiveInformation.substring(sensitiveInformationLength - SUFFIX_LENGTH, sensitiveInformationLength);
        return prefix + generateAsterisk(asterisk) + suffix;
    }

    private static String generateAsterisk(int number) {
        if (number > DEFAULT_ASTERISK || number <= 0) {
            number = DEFAULT_ASTERISK;
        }
        StringBuilder results = new StringBuilder(number);
        for (int i = 0; i < number; i++) {
            results.append("*");
        }
        return results.toString();
    }

    /**
     * 校验传入的敏感信息字符串是否为空
     * 如果为空则返回false
     * 如果不为空则返回true
     *
     * @param sensitiveInformation 敏感信息
     * @return boolean
     */
    private static boolean valid(final String sensitiveInformation) {
        if (sensitiveInformation == null || EMPTY_STRING.equals(sensitiveInformation)) {
            return false;
        }
        return true;
    }
}
