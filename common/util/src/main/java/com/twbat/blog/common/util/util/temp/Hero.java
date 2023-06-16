package com.twbat.blog.common.util.util.temp;

/**
 * <b></b>
 *
 * @author litailai
 * @date 2023/6/16
 * @email <a href="mailto:darkltl@163.com">darkltl@163.com</a>
 */
public class Hero {

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        setName(title);
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                '}';
    }
}
