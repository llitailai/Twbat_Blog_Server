package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.global;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author dakrltl
 * @date 2021-10-12 10:09
 * @description 全局属性
 */
public class GlobalAttribute {

    private static final byte[] LOCK = new byte[0];

    private static volatile GlobalAttribute instance;

    private AtomicReference<String> joinStr = new AtomicReference<>();

    private AtomicReference<String> tableName = new AtomicReference<>();

    private AtomicReference<String> aliasName = new AtomicReference<>();

    private GlobalAttribute() {
    }

    public static GlobalAttribute getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new GlobalAttribute();
                }
            }
        }
        return instance;
    }

    public GlobalAttribute putJoin(String joinSql) {
        joinStr.set(joinSql);
        return instance;
    }

    public GlobalAttribute putTableName(String tableName) {
        instance.tableName.set(tableName);
        return instance;
    }

    public GlobalAttribute putAliasName(String aliasName) {
        instance.aliasName.set(aliasName);
        return instance;
    }

    public AtomicReference<String> getAliasName() {
        return aliasName;
    }

    public AtomicReference<String> getTableName() {
        return tableName;
    }

    public AtomicReference<String> getJoinStr() {
        return joinStr;
    }

    public GlobalAttribute renew() {
        return instance;
    }

}
