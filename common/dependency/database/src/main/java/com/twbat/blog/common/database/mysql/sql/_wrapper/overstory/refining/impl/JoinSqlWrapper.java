package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.refining.impl;

import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.dialect.mysql.JoinMethod;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.global.GlobalAttribute;
import com.twbat.blog.common.database.mysql.sql.wrapper.define.SQLDefine;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author darkltl
 * @date 2021-10-12 10:01
 * @description 连接Sql封装器
 */
public class JoinSqlWrapper<T> {

    private String alias;

    protected JoinSqlWrapper() {
    }

    protected JoinSqlWrapper<T> join(final String tableName, final String aliasName, JoinMethod joinMethod) {
        if (joinMethod == null) {
            joinMethod = JoinMethod.LEFT_JOIN;
        }
        GlobalAttribute instance = GlobalAttribute.getInstance();
        String joinSql = joinMethod.getValue() + " " + tableName + " " + SQLDefine.AS + " " + aliasName;
        instance.putJoin(joinSql);
        this.alias = aliasName;
        return this;
    }

    protected void on(final String tableAlias, final String fieldOne, final String fieldTwo, final SQLDefine contactMethod) {
        GlobalAttribute instance = GlobalAttribute.getInstance();
        AtomicReference<String> joinStr = instance.getJoinStr();
        instance.putJoin(joinStr.get() + " " + SQLDefine.ON + " " + tableAlias + "." + fieldOne + " " + contactMethod.getKey() + " " + alias + "." + fieldTwo + " ");
    }

//    public static void main(String[] args) {
//        JoinSqlWrapper joinSqlWrapper = new JoinSqlWrapper(null);
//        joinSqlWrapper.join("testA","pingA",null).on("a","id","id",SQLDefine.EQ);
//        GlobalAttribute instance = GlobalAttribute.getInstance();
//        System.out.println(instance.getJoinStr());
//    }

}

