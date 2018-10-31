package com.share;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.share.book.entity.ReadBook;
import com.share.book.service.IReadBookService;
import com.share.book.service.impl.ReadBookServiceImpl;
import com.share.config.HttpsBerBer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadShareApplicationTests {

    @Autowired
    IReadBookService readBookService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test01() {
        String url = "https://www.biqudu.com/";
        try {
            Document document = Jsoup.connect(url).timeout(30000).get();
            HttpsBerBer httpsBerBer = new HttpsBerBer("book");
            System.out.println(document);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        ReadBook readBook = new ReadBook();
        readBook.setContent("ceshi");
        readBook.setTitle("第一次");
        boolean save = readBookService.save(readBook);
    }

    @Test
    public void test03() {
        try {
            String bookName = "将夜";
            String author = "猫腻";
            String url = "https://www.biqudu.com";
            Connection connection = Jsoup.connect(url + "/searchbook.php").validateTLSCertificates(false);
            connection.data("keyword", bookName);
            Document doc = connection.get();
            Elements items = doc.getElementsByClass("item");
            for (Element item : items) {
                if (StringUtils.isNotEmpty(author) && author.equals(item.getElementsByTag("span").text())) {
                    String contextUrl = url + item.getElementsByAttribute("href").get(0).attr("href");
                    //获取章节列表
                    Document document = connection.url(contextUrl).get();
                    Element list = document.getElementById("list");
                    System.out.println(list);
                    Elements as = list.getElementsByTag("a");
                    System.out.println(as.size());
                    for (Element a : as) {
                        String links = a.attr("href");
                        Document document1 = connection.url(url + links).get();
                        String content = document1.getElementById("content").text();
                        String bookTitle = document1.getElementsByTag("h1").text();
                        ReadBook readBook = new ReadBook();
                        readBook.setTitle(bookTitle);
                        readBook.setContent(content);
                        readBookService.save(readBook);
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
