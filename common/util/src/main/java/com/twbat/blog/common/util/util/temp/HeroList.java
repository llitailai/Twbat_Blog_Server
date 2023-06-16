package com.twbat.blog.common.util.util.temp;

import java.util.Date;
import java.util.List;

/**
 * <b></b>
 *
 * @author litailai
 * @date 2023/6/16
 * @email <a href="mailto:darkltl@163.com">darkltl@163.com</a>
 */
public class HeroList {
    private Date fileTime;

    private String fileName;

    private List<Hero> hero;

    private String version;

    public Date getFileTime() {
        return fileTime;
    }

    public void setFileTime(Date fileTime) {
        this.fileTime = fileTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Hero> getHero() {
        return hero;
    }

    public void setHero(List<Hero> hero) {
        this.hero = hero;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "HeroList{" +
                "fileTime=" + fileTime +
                ", fileName='" + fileName + '\'' +
                ", hero=" + hero +
                ", version='" + version + '\'' +
                '}';
    }
}
