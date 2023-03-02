package com.twbat.blog.common.database.mysql.sql;

import lombok.Data;

import java.io.Serializable;

@Data
public class Test implements Serializable {

    private Long tableId;

    private String tableName;

    private String isSuccess;

    public static void main(String[] args) {
        System.out.println("test");
    }
}
