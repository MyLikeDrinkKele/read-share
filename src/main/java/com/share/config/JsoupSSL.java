package com.share.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.share.book.entity.ReadBook;
import com.share.book.service.IReadBookService;
import com.share.book.service.impl.ReadBookServiceImpl;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class JsoupSSL {
    @Autowired
    IReadBookService readBookService;

    public static void main(String[] args) throws IOException {
        ReadBookServiceImpl readBookService = new ReadBookServiceImpl();
        String bookName = "将夜";
        String author = "猫腻";
        String url = "https://www.biqudu.com";
        Connection connection = Jsoup.connect(url+"/searchbook.php").validateTLSCertificates(false);
        connection.data("keyword", bookName);
        Document doc = connection.get();
        Elements items = doc.getElementsByClass("item");
        for (Element item : items) {
            if (StringUtils.isNotEmpty(author)&& author.equals(item.getElementsByTag("span").text())) {
                String contextUrl = url+item.getElementsByAttribute("href").get(0).attr("href");
                Document document = connection.url(contextUrl).get();
                Element list = document.getElementById("list");
                Elements as = list.getElementsByTag("a");
                for (Element a : as) {
                    String links = a.attr("href");
                    Document document1 = connection.url(url + links).get();
                    String content = document1.getElementById("content").text();
                    String bookTitle = document1.getElementsByTag("h1").text();
                    ReadBook readBook = new ReadBook();
                    readBook.setTitle(bookTitle);
                    readBook.setContent(content);
                    System.out.println(bookTitle);
                    System.out.println(content);
//                    readBookService.save(readBook);
                    break;
                }
            }
        }
    }

//    /**
//     * 现在很多站点都是SSL对数据传输进行加密，这也让普通的HttpConnection无法正常的获取该页面的内容，
//     * 而Jsoup起初也对此没有做出相应的处理，
//     * 想了一下是否可以让Jsoup可以识别所有的SSL加密过的页面，查询了一些资料，发现可以为本地HttpsURLConnection配置一个“万能证书”，其原理是就是：
//     * 重置HttpsURLConnection的DefaultHostnameVerifier，使其对任意站点进行验证时都返回true
//     * 重置httpsURLConnection的DefaultSSLSocketFactory， 使其生成随机证书
//     * 后来Jsoup Connection提供了validateTLSCertificates(boolean validate)//是否进行TLS证书验证,不推荐
//     */
//    static {
//        try {
//            // 重置HttpsURLConnection的DefaultHostnameVerifier，使其对任意站点进行验证时都返回true
//            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            });
//            // 创建随机证书生成工厂
//            //SSLContext context = SSLContext.getInstance("TLS");
//            SSLContext context = SSLContext.getInstance("TLSv1.2");
//            context.init(null, new X509TrustManager[]{new X509TrustManager() {
//                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                }
//
//                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                }
//
//                public X509Certificate[] getAcceptedIssuers() {
//                    return new X509Certificate[0];
//                }
//            }}, new SecureRandom());
//
//            // 重置httpsURLConnection的DefaultSSLSocketFactory， 使其生成随机证书
//            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}