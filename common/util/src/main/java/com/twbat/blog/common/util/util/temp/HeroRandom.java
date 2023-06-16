package com.twbat.blog.common.util.util.temp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Random;

/**
 * <b></b>
 *
 * @author litailai
 * @date 2023/6/16
 * @email <a href="mailto:darkltl@163.com">darkltl@163.com</a>
 */
public class HeroRandom {

//    private static final Logger LOG = LoggerFactory.getLogger(HeroRandom.class);

    private static final String HERO_LINK = "https://101.qq.com/#/hero";


    private static HeroRandom instance;

    private static final byte[] LOCK = new byte[0];

    public static HeroRandom getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new HeroRandom();
                }
            }
        }
        return instance;
    }


    private HeroRandom() {

    }


    public String heroRandom() {
        try {
            List<Hero> heroList = getHeroList();
            return heroList.get(new Random().nextInt(heroList.size())).getName();
        } catch (IOException e) {
            System.out.println(String.format("get hero list error , please check or update your hero configuration \r\n cause: %s", e));
        }
        return null;
    }

    private List<Hero> getHeroList() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("/Users/litailai/Documents/Repository/Code/Server_Project/blog_server/common/util/src/main/java/com/twbat/blog/common/util/util/temp/hero_list.js"));
        String temp;
        String text = "";
        while ((temp = in.readLine()) != null) {
            text += temp;
        }
        in.close();
        return JSONObject.toJavaObject(JSONObject.parseObject(text), HeroList.class).getHero();
    }

    public static void main(String[] args) {
        HeroRandom heroRandom = new HeroRandom();
        System.out.println(heroRandom.heroRandom());
    }



}
