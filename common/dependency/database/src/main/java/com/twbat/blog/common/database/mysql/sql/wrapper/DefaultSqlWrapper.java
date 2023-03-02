package com.twbat.blog.common.database.mysql.sql.wrapper;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.twbat.blog.common.database.mysql.sql.Test;
import com.twbat.blog.common.database.mysql.sql.wrapper.define.JoinDefine;
import com.twbat.blog.common.database.mysql.sql.wrapper.define.LikeDefine;
import com.twbat.blog.common.database.mysql.sql.wrapper.define.SQLDefine;
import com.twbat.blog.common.database.mysql.sql.wrapper.define.SqlMethodDefine;
import com.twbat.blog.common.database.mysql.sql.wrapper.lambda.FieldFunction;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @param <T> 类型
 * @author darkltl
 * @date 2021-09-22 14:58
 * 默认SQL组装器,提供组装规范,不符合条件可自定义
 */
public class DefaultSqlWrapper<T> implements SqlWrapper<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSqlWrapper.class);
    private static final String DEFAULT_METHOD = "{METHOD_DEFAULT_A001_DefaultSqlWrapper}";
    private static final String DEFAULT_SELECT_FIELD = "{SELECT_FIELD_DEFAULT_P002}";
    private static final String DEFAULT_TABLE_NAME = "{TABLE_NAME_DEFAULT_A002_DefaultSqlWrapper}";
    private static final String DEFAULT_INSERT_FIELD = "{INSERT_DEFAULT_P003}";
    private static final String DEFAULT_UPDATE_FIELD = "{UPDATE_DEFAULT_P004}";
    private static final HashMap<String, String> FIELD_CACHE = new HashMap<>();
    private String sql;
    private String where;
    private DefaultSqlWrapper defaultSqlWrapper;
    private String defaultAliasName;
    private String defaultAliasNameJoin;
    private List<String> fieldCache = new ArrayList<>();

    private List<String> selectFields = new ArrayList<>();

    private List<Object> params = new ArrayList<>();

    public DefaultSqlWrapper() {
        defaultSqlWrapper = this;
        this.defaultSqlWrapper.sql = DEFAULT_METHOD + " " + DEFAULT_SELECT_FIELD + " " + DEFAULT_TABLE_NAME;
        this.defaultSqlWrapper.where = " " + SQLDefine.WHERE + " ";
        this.defaultSqlWrapper.defaultAliasName = "defaultAs_0_1";
        this.defaultSqlWrapper.defaultAliasNameJoin = this.defaultSqlWrapper.defaultAliasName + ".";
    }

    public static void main(String[] args) {
        DefaultSqlWrapper<Test> sqlWrapper = new DefaultSqlWrapper<Test>();
        System.out.println(sqlWrapper.sqlMethod(SqlMethodDefine.UPDATE).tableName("test").and().eq(Test::getTableName, "abc").eq(Test::getIsSuccess, "success").or().eq(Test::getIsSuccess, "orSuccess").getSql());
        sqlWrapper.resolveWhere(',');
        System.out.println(sqlWrapper.getSql());

    }

    @Override
    public SqlWrapper<T> eq(FieldFunction<T, ?> function, Object param) {
        return eq(function, param, false);
    }

    @Override
    public SqlWrapper<T> eq(FieldFunction<T, ?> function, Object param, boolean flag) {
        String filedName = getFiledName(function);
        if (!flag) {
            String condition = this.defaultSqlWrapper.defaultAliasNameJoin + filedName + SQLDefine.EQ.getKey() + param;
            resolve(condition);
        }
        return this.defaultSqlWrapper;
    }

    @Override
    public SqlWrapper<T> like(FieldFunction<T, ?> function, Object param) {
        return like(function, param, LikeDefine.DEFAULT, false);
    }

    @Override
    public SqlWrapper<T> like(FieldFunction<T, ?> function, Object param, boolean flag) {
        return like(function, param, LikeDefine.DEFAULT, flag);
    }

    @Override
    public SqlWrapper<T> like(FieldFunction<T, ?> function, Object param, LikeDefine define) {
        return like(function, param, define, false);
    }

    @Override
    public SqlWrapper<T> like(FieldFunction<T, ?> function, Object param, LikeDefine define, boolean flag) {
        if (!flag) {
            String condition = "";
            String filedName = getFiledName(function);
            switch (define) {
                case END:
                    condition = this.defaultSqlWrapper.defaultAliasNameJoin + filedName + " " + SQLDefine.LIKE + " " + param + SQLDefine.LIKE_SYMBOL;
                case START:
                    condition = this.defaultSqlWrapper.defaultAliasNameJoin + filedName + " " + SQLDefine.LIKE + " " + SQLDefine.LIKE_SYMBOL + param;
                default:
                    condition = this.defaultSqlWrapper.defaultAliasNameJoin + filedName + " " + SQLDefine.LIKE + " " + SQLDefine.LIKE_SYMBOL + param + SQLDefine.LIKE_SYMBOL;
            }
            resolve(condition);
        }
        return this.defaultSqlWrapper;
    }

    @Override
    public SqlWrapper<T> notLike(FieldFunction<T, ?> function, Object param) {
        return null;
    }

    @Override
    public SqlWrapper<T> notLike(FieldFunction<T, ?> function, Object param, boolean flag) {
        return null;
    }

    @Override
    public SqlWrapper<T> notLike(FieldFunction<T, ?> function, Object param, LikeDefine define) {
        return null;
    }

    @Override
    public SqlWrapper<T> notLike(FieldFunction<T, ?> function, Object param, LikeDefine define, boolean flag) {
        return null;
    }

    @Override
    public SqlWrapper<T> binaryLike(FieldFunction<T, ?> function, Object param) {
        return null;
    }

    @Override
    public SqlWrapper<T> binaryLike(FieldFunction<T, ?> function, Object param, boolean flag) {
        return null;
    }

    @Override
    public SqlWrapper<T> binaryLike(FieldFunction<T, ?> function, Object param, LikeDefine define) {
        return null;
    }

    @Override
    public SqlWrapper<T> binaryLike(FieldFunction<T, ?> function, Object param, LikeDefine define, boolean flag) {
        return null;
    }

    @Override
    public SqlWrapper<T> ne(FieldFunction<T, ?> function, Object param) {
        return null;
    }

    @Override
    public SqlWrapper<T> ne(FieldFunction<T, ?> function, Object param, boolean flag) {
        return null;
    }

    @Override
    public SqlWrapper<T> in(FieldFunction<T, ?> function, Collection<T> param) {
        return null;
    }

    @Override
    public SqlWrapper<T> in(FieldFunction<T, ?> function, Collection<T> param, boolean flag) {
        return null;
    }

    @Override
    public SqlWrapper<T> select(FieldFunction<T, ?>... columns) {
        for (FieldFunction<T, ?> column : columns) {
            String filedName = getFiledName(column);
            this.defaultSqlWrapper.selectFields.add(filedName);
        }
        return this.defaultSqlWrapper;
    }

    @Override
    public SqlWrapper<T> lt(FieldFunction<T, ?> function, Object param) {
        return null;
    }

    @Override
    public SqlWrapper<T> lt(FieldFunction<T, ?> function, Object param, boolean flag) {
        return null;
    }

    @Override
    public SqlWrapper<T> gt(FieldFunction<T, ?> function, Object param) {
        return null;
    }

    @Override
    public SqlWrapper<T> gt(FieldFunction<T, ?> function, Object param, boolean flag) {
        return null;
    }

    @Override
    public SqlWrapper<T> customizeWhere(String where) {
        return null;
    }

    @Override
    public SqlWrapper<T> as(String aliasName) {
        return null;
    }

    @Override
    public SqlWrapper<T> join(String joinTableName, String aliasName) {
        return null;
    }

    @Override
    public SqlWrapper<T> join(String joinTableName, String aliasName, JoinDefine joinDefine) {
        return null;
    }

    @Override
    public SqlWrapper<T> join(String joinTableName, String aliasName, JoinDefine joinDefine, boolean flag) {
        return null;
    }

    @Override
    public SqlWrapper<T> on(String con0, String con1) {
        return null;
    }

    @Override
    public SqlWrapper<T> and() {
        this.defaultSqlWrapper.where += " {} " + SQLDefine.AND + " ";
        return this.defaultSqlWrapper;
    }

    @Override
    public SqlWrapper<T> or() {
        this.defaultSqlWrapper.where += " {} " + SQLDefine.OR + " ";
        return this.defaultSqlWrapper;
    }

    @Override
    public SqlWrapper<T> tableName(String name) {
        this.defaultSqlWrapper.sql = this.defaultSqlWrapper.sql.replace(DEFAULT_TABLE_NAME, " " + SQLDefine.FROM + " " + name + " " + SQLDefine.AS + " " + this.defaultSqlWrapper.defaultAliasName + " ");
        return this.defaultSqlWrapper;
    }

    @Override
    public SqlWrapper<T> sqlMethod(SqlMethodDefine sqlMethodDefine) {
        switch (sqlMethodDefine) {
            case INSERT:
                this.defaultSqlWrapper.sql = this.defaultSqlWrapper.sql.replace(DEFAULT_METHOD, SQLDefine.INSERT + " " + SQLDefine.INTO + "").replace(DEFAULT_SELECT_FIELD, DEFAULT_INSERT_FIELD);
                break;
            case UPDATE:
                this.defaultSqlWrapper.sql = (this.defaultSqlWrapper.sql.replace(DEFAULT_METHOD, SQLDefine.UPDATE + " ") + " " + SQLDefine.SET + " ").replace(DEFAULT_SELECT_FIELD, DEFAULT_UPDATE_FIELD);
                break;
            default:
                this.defaultSqlWrapper.sql = this.defaultSqlWrapper.sql.replace(DEFAULT_METHOD, SQLDefine.SELECT + " ");
                break;
        }
        return this.defaultSqlWrapper;
    }

    @Override
    public SqlWrapper<T> param(Object param) {
        this.defaultSqlWrapper.params.add(param);
        return this.defaultSqlWrapper;
    }

    @Override
    public String getSql() {
        if (this.defaultSqlWrapper.sql.contains(DEFAULT_SELECT_FIELD)) {
            AtomicReference<String> replace = new AtomicReference<>(this.defaultSqlWrapper.defaultAliasNameJoin + SQLDefine.ALL.getKey());
            if (fieldCache.size() != 0) {
                replace.set("");
                fieldCache.forEach(
                        fieldName -> {
                            replace.set(replace.get() + this.defaultSqlWrapper.defaultAliasNameJoin + fieldName + ",");
                        }
                );
                String s = replace.get();
                s = s.substring(0, replace.get().length() - 1);
                replace.set(s);
            }
            this.defaultSqlWrapper.sql = this.defaultSqlWrapper.sql.replace(DEFAULT_SELECT_FIELD, replace.get());
        }

        if (this.defaultSqlWrapper.sql.contains(DEFAULT_INSERT_FIELD)) {
            String replace = this.defaultSqlWrapper.sql.replace(DEFAULT_INSERT_FIELD, " ").replace(SQLDefine.FROM.getKey(), "");
            if (selectFields.size() != 0) {
                if (params.size() != selectFields.size()) {
                    //todo 需要改变为自定义异常
                    throw new RuntimeException("插入的字段与选择的字段未对应");
                }
                String leftBracket = "(";
                String rightBracket = ")";
                StringBuilder leftBracketBuilder = new StringBuilder(leftBracket);
                StringBuilder paramBuilder = new StringBuilder(leftBracket);
                for (int i = 0, len = selectFields.size(); i < len; i++) {
                    leftBracketBuilder.append(selectFields.get(i) + ",");
                    paramBuilder.append(params.get(i) + ",");
                }
                String insertColumns = leftBracketBuilder.substring(0, leftBracketBuilder.length() - 2) + rightBracket + " ";
                String insertParams = SQLDefine.VALUE.getKey() + paramBuilder.substring(0, paramBuilder.length() - 1) + rightBracket + " ";
                this.defaultSqlWrapper.sql = replace + insertColumns + insertParams;
            } else {
                String leftBracket = "(";
                String rightBracket = ")";
                StringBuilder leftBracketBuilder = new StringBuilder(leftBracket);
                for (Object param : params) {
                    leftBracketBuilder.append(param + " , ");
                }
                this.defaultSqlWrapper.sql = replace + SQLDefine.VALUES.getKey() + leftBracketBuilder.substring(0, leftBracketBuilder.length() - 2) + rightBracket;
            }
        }

        if (this.defaultSqlWrapper.sql.contains(DEFAULT_UPDATE_FIELD)) {
            String replace = this.defaultSqlWrapper.sql.replace(DEFAULT_UPDATE_FIELD, " ").replace(SQLDefine.FROM.getKey(), "");
            this.defaultSqlWrapper.sql = replace;
        }

        String res = handleWhere();
        return this.defaultSqlWrapper.sql + (res == null ? StringUtils.EMPTY : this.defaultSqlWrapper.where);
    }

    private String handleWhere() {
        if (SQLDefine.WHERE.getKey().equals(this.defaultSqlWrapper.where.trim())) {
            return null;
        }
        if (this.defaultSqlWrapper.where.endsWith(SQLDefine.AND.getKey()) || this.defaultSqlWrapper.where.endsWith(SQLDefine.OR.getKey())) {
            this.defaultSqlWrapper.where = this.defaultSqlWrapper.where.substring(0, defaultSqlWrapper.where.length() - 4);
        }
        return StringUtils.EMPTY;
    }

    private void resolve(String condition) {
        this.defaultSqlWrapper.where = this.defaultSqlWrapper.where.replaceFirst("\\{}", condition);
    }

    private void resolveWhere(char symbol) {
        char[] res = new char[5];
        int index;
        while ((index = this.defaultSqlWrapper.where.indexOf(SQLDefine.AND.getKey())) != -1 || (index = this.defaultSqlWrapper.where.indexOf(SQLDefine.OR.getKey())) != -1) {
            this.defaultSqlWrapper.where.getChars(index - 1, index + 3, res, 0);
            String resolve = String.valueOf(res);
            if (resolve.trim().length() == 3) {
                char[] chars = this.defaultSqlWrapper.where.toCharArray();
                chars[index] = ' ';
                chars[index + 1] = symbol;
                chars[index + 2] = ' ';
                this.defaultSqlWrapper.where = String.valueOf(chars);
            }
        }
    }

    private String getFiledName(FieldFunction<T, ?> function) {
        Method writeReplaceMethod = null;
        try {
            writeReplaceMethod = function.getClass().getDeclaredMethod("writeReplace");
        } catch (NoSuchMethodException e) {
            LOGGER.error("writeReplaceMethod not find,exception:{}", e);
            return StringUtils.EMPTY;
        }
        boolean accessible = writeReplaceMethod.isAccessible();
        writeReplaceMethod.setAccessible(true);
        SerializedLambda serializedLambda = null;
        try {
            serializedLambda = (SerializedLambda) writeReplaceMethod.invoke(function);
        } catch (IllegalAccessException e) {
            LOGGER.error("writeReplaceMethod访问权限被关闭 exception:{}", e);
            return StringUtils.EMPTY;
        } catch (InvocationTargetException e) {
            LOGGER.error("writeReplaceMethod未找到, exception:{}", e);
            return StringUtils.EMPTY;
        }
        writeReplaceMethod.setAccessible(accessible);
        String fieldName = serializedLambda.getImplMethodName().substring("get".length());
        fieldName = fieldName.replaceFirst(fieldName.charAt(0) + "", (fieldName.charAt(0) + "").toLowerCase());
        if (FIELD_CACHE.containsKey(fieldName)) {
            return FIELD_CACHE.get(fieldName);
        }
        Field field = null;
        try {
            Class<?> fieldClass = Class.forName(serializedLambda.getImplClass().replace("/", "."));
            field = fieldClass.getDeclaredField(fieldName);
            initTableFields(fieldClass);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        //转驼峰命名
        String databaseFieldName = fieldName.replaceAll("[A-Z]", "_$0").toLowerCase();

        FIELD_CACHE.put(fieldName, databaseFieldName);
        return databaseFieldName;
    }

    private void initTableFields(Class cls) {
        if (fieldCache.size() == 0) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                String fieldName = declaredField.getName().replaceAll("[A-Z]", "_$0").toLowerCase();
                if (!fieldCache.contains(declaredField.getName())) {
                    fieldCache.add(fieldName);
                }
            }
        }
    }

}

