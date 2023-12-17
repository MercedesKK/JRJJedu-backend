package com.company.project.utils;

public final class Logger {

    public static void warn(Object obj, String message, Exception e) {

        org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(obj.getClass());
        if (e == null) {
            log.warn(message);
        } else {
            log.warn(message, e);
        }
    }

    public static void warn(Object obj, String message) {
        warn(obj, message, null);
    }

    public static void error(Object obj, String message) {
        error(obj, message, null);
    }

    public static void error(Class<?> clz, String message) {
        error(clz, message, null);
    }

    public static void error(Object obj, String message, Throwable e) {
        org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(obj.getClass());
        if (e == null) {
            log.error(message);
        } else {
            log.error(message, e);
        }

    }

    public static void info(Object obj, String message) {
        info(obj.getClass(), message);
    }

    public static void info(Class<?> clz, String message) {
        org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(clz);
        if (log.isInfoEnabled()) {
            log.info(message);
        }
    }

    public static void infoAndTrace(Object obj, String message) {
        infoAndTrace(obj.getClass(), message);
    }

    public static void infoAndTrace(Class<?> clz, String message) {
        info(clz, message);

        trace(clz, message);
    }

    private static void trace(Class<?> clz, String message) {
        org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(clz);
        if (log.isTraceEnabled()) {
            log.trace(message);
        }
    }

    public static void debug(Object obj, String message) {
        debug(obj.getClass(), message);
    }

    public static void debug(Class<?> clz, String message) {
        org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(clz);
        if (log.isDebugEnabled()) {
            log.debug(message);
        }
    }
}
