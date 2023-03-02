package cn.twbat.logger.core.factory;

import cn.twbat.logger.core.log.Logger;
import org.slf4j.Marker;

import java.util.HashMap;

/**
 * @author : darkltl
 * @version : 1.0
 * @date : 2021/9/3 0003 下午 15:44
 * @description :
 * @since : jdk1.8
 */
public class LoggerFactory {

    private static HashMap<String, Logger> cache = new HashMap<>();

    /**
     * 获取封装后的Logger对象
     * 用于规范化行为
     *
     * @param cls class文件
     * @return {@link cn.twbat.logger.core.log.Logger}
     */
    public static Logger getLogger(Class<?> cls) {
        return getLogger(cls.getName());
    }

    public static Logger getLogger(String name) {
        final Logger logger = cache.get(name);
        if (logger == null) {
            final LoggerImpl loggerCache = new LoggerImpl(org.slf4j.LoggerFactory.getLogger(name));
            cache.put(name, loggerCache);
            return loggerCache;
        }
        return logger;
    }


    private static class LoggerImpl implements Logger {
        private final org.slf4j.Logger LOGGER;

        private LoggerImpl(org.slf4j.Logger logger) {
            this.LOGGER = logger;
        }

        @Override
        public String getName() {
            return LOGGER.getName();
        }

        @Override
        public boolean isTraceEnabled() {
            return LOGGER.isTraceEnabled();
        }

        @Override
        public void trace(String s) {
            if (isTraceEnabled()) {
                LOGGER.trace(s);
            }
        }

        @Override
        public void trace(String s, Object o) {
            if (isTraceEnabled()) {
                LOGGER.trace(s, o);
            }
        }

        @Override
        public void trace(String s, Object o, Object o1) {
            if (isTraceEnabled()) {
                LOGGER.trace(s, o, o1);
            }
        }

        @Override
        public void trace(String s, Object... objects) {
            if (isTraceEnabled()) {
                LOGGER.trace(s, objects);
            }
        }

        @Override
        public void trace(String s, Throwable throwable) {
            if (isTraceEnabled()) {
                LOGGER.trace(s, throwable);
            }
        }

        @Override
        public boolean isTraceEnabled(Marker marker) {
            return LOGGER.isTraceEnabled(marker);
        }

        @Override
        public void trace(Marker marker, String s) {
            if (isDebugEnabled(marker)) {
                LOGGER.trace(marker, s);
            }
        }

        @Override
        public void trace(Marker marker, String s, Object o) {
            if (isDebugEnabled(marker)) {
                LOGGER.trace(marker, s, o);
            }
        }

        @Override
        public void trace(Marker marker, String s, Object o, Object o1) {
            if (isDebugEnabled(marker)) {
                LOGGER.trace(marker, s, o, o1);
            }
        }

        @Override
        public void trace(Marker marker, String s, Object... objects) {
            if (isDebugEnabled(marker)) {
                LOGGER.trace(marker, s, objects);
            }
        }

        @Override
        public void trace(Marker marker, String s, Throwable throwable) {
            if (isDebugEnabled(marker)) {
                LOGGER.trace(marker, s, throwable);
            }
        }

        @Override
        public boolean isDebugEnabled() {
            return LOGGER.isDebugEnabled();
        }

        @Override
        public void debug(String s) {
            if (isDebugEnabled()) {
                LOGGER.debug(s);
            }
        }

        @Override
        public void debug(String s, Object o) {
            if (isDebugEnabled()) {
                LOGGER.debug(s, o);
            }
        }

        @Override
        public void debug(String s, Object o, Object o1) {
            if (isDebugEnabled()) {
                LOGGER.debug(s, o, o1);
            }
        }

        @Override
        public void debug(String s, Object... objects) {
            if (isDebugEnabled()) {
                LOGGER.debug(s, objects);
            }
        }

        @Override
        public void debug(String s, Throwable throwable) {
            if (isDebugEnabled()) {
                LOGGER.debug(s, throwable);
            }
        }

        @Override
        public boolean isDebugEnabled(Marker marker) {
            return LOGGER.isDebugEnabled(marker);
        }

        @Override
        public void debug(Marker marker, String s) {
            if (isDebugEnabled(marker)) {
                LOGGER.debug(marker, s);
            }
        }

        @Override
        public void debug(Marker marker, String s, Object o) {
            if (isDebugEnabled(marker)) {
                LOGGER.debug(marker, s, o);
            }
        }

        @Override
        public void debug(Marker marker, String s, Object o, Object o1) {
            if (isDebugEnabled(marker)) {
                LOGGER.debug(marker, s, o, o1);
            }
        }

        @Override
        public void debug(Marker marker, String s, Object... objects) {
            if (isDebugEnabled(marker)) {
                LOGGER.debug(marker, s, objects);
            }
        }

        @Override
        public void debug(Marker marker, String s, Throwable throwable) {
            if (isDebugEnabled(marker)) {
                LOGGER.debug(marker, s, throwable);
            }
        }

        @Override
        public boolean isInfoEnabled() {
            return LOGGER.isInfoEnabled();
        }

        @Override
        public void info(String s) {
            if (isInfoEnabled()) {
                LOGGER.info(s);
            }
        }

        @Override
        public void info(String s, Object o) {
            if (isInfoEnabled()) {
                LOGGER.info(s, o);
            }
        }

        @Override
        public void info(String s, Object o, Object o1) {
            if (isInfoEnabled()) {
                LOGGER.info(s, o, o1);
            }
        }

        @Override
        public void info(String s, Object... objects) {
            if (isInfoEnabled()) {
                LOGGER.info(s, objects);
            }
        }

        @Override
        public void info(String s, Throwable throwable) {
            if (isInfoEnabled()) {
                LOGGER.info(s, throwable);
            }
        }

        @Override
        public boolean isInfoEnabled(Marker marker) {
            return LOGGER.isInfoEnabled(marker);
        }

        @Override
        public void info(Marker marker, String s) {
            if (isInfoEnabled(marker)) {
                LOGGER.info(marker, s);
            }
        }

        @Override
        public void info(Marker marker, String s, Object o) {
            if (isInfoEnabled(marker)) {
                LOGGER.info(marker, s, o);
            }
        }

        @Override
        public void info(Marker marker, String s, Object o, Object o1) {
            if (isInfoEnabled(marker)) {
                LOGGER.info(marker, s, o, o1);
            }
        }

        @Override
        public void info(Marker marker, String s, Object... objects) {
            if (isInfoEnabled(marker)) {
                LOGGER.info(marker, s, objects);
            }
        }

        @Override
        public void info(Marker marker, String s, Throwable throwable) {
            if (isInfoEnabled(marker)) {
                LOGGER.info(marker, s, throwable);
            }
        }

        @Override
        public boolean isWarnEnabled() {
            return LOGGER.isWarnEnabled();
        }

        @Override
        public void warn(String s) {
            if (isWarnEnabled()) {
                LOGGER.warn(s);
            }
        }

        @Override
        public void warn(String s, Object o) {
            if (isWarnEnabled()) {
                LOGGER.warn(s, o);
            }
        }

        @Override
        public void warn(String s, Object... objects) {
            if (isWarnEnabled()) {
                LOGGER.warn(s, objects);
            }
        }

        @Override
        public void warn(String s, Object o, Object o1) {
            if (isWarnEnabled()) {
                LOGGER.warn(s, o, o1);
            }
        }

        @Override
        public void warn(String s, Throwable throwable) {
            if (isWarnEnabled()) {
                LOGGER.warn(s, throwable);
            }
        }

        @Override
        public boolean isWarnEnabled(Marker marker) {
            return LOGGER.isWarnEnabled(marker);
        }

        @Override
        public void warn(Marker marker, String s) {
            if (isWarnEnabled(marker)) {
                LOGGER.warn(marker, s);
            }
        }

        @Override
        public void warn(Marker marker, String s, Object o) {
            if (isWarnEnabled(marker)) {
                LOGGER.warn(marker, s, o);
            }
        }

        @Override
        public void warn(Marker marker, String s, Object o, Object o1) {
            if (isWarnEnabled(marker)) {
                LOGGER.warn(marker, s, o, o1);
            }
        }

        @Override
        public void warn(Marker marker, String s, Object... objects) {
            if (isWarnEnabled(marker)) {
                LOGGER.warn(marker, s, objects);
            }
        }

        @Override
        public void warn(Marker marker, String s, Throwable throwable) {
            if (isWarnEnabled(marker)) {
                LOGGER.warn(marker, s, throwable);
            }
        }

        @Override
        public boolean isErrorEnabled() {
            return LOGGER.isErrorEnabled();
        }

        @Override
        public void error(String s) {
            if (isErrorEnabled()) {
                LOGGER.error(s);
            }
        }

        @Override
        public void error(String s, Object o) {
            if (isErrorEnabled()) {
                LOGGER.error(s, o);
            }
        }

        @Override
        public void error(String s, Object o, Object o1) {
            if (isErrorEnabled()) {
                LOGGER.error(s, o, o1);
            }
        }

        @Override
        public void error(String s, Object... objects) {
            if (isErrorEnabled()) {
                LOGGER.error(s, objects);
            }
        }

        @Override
        public void error(String s, Throwable throwable) {
            if (isErrorEnabled()) {
                LOGGER.error(s, throwable);
            }
        }

        @Override
        public boolean isErrorEnabled(Marker marker) {
            return isErrorEnabled(marker);
        }

        @Override
        public void error(Marker marker, String s) {
            if (isErrorEnabled(marker)) {
                LOGGER.error(marker, s);
            }
        }

        @Override
        public void error(Marker marker, String s, Object o) {
            if (isErrorEnabled(marker)) {
                LOGGER.error(marker, s, o);
            }
        }

        @Override
        public void error(Marker marker, String s, Object o, Object o1) {
            if (isErrorEnabled(marker)) {
                LOGGER.error(marker, s, o, o1);
            }
        }

        @Override
        public void error(Marker marker, String s, Object... objects) {
            if (isErrorEnabled(marker)) {
                LOGGER.error(marker, s, objects);
            }
        }

        @Override
        public void error(Marker marker, String s, Throwable throwable) {
            if (isErrorEnabled(marker)) {
                LOGGER.error(marker, s, throwable);
            }
        }
    }

}


