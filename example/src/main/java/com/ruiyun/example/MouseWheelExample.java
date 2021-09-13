package com.ruiyun.example;

import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.browser.BrowserFetcher;
import com.ruiyun.jvppeteer.core.page.ElementHandle;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.Clip;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;
import org.junit.Test;

import java.util.ArrayList;

public class MouseWheelExample {

    @Test
    public void test1() throws Exception {
        //自动下载，第一次下载后不会再下载
        BrowserFetcher.downloadIfNotExist(null);
        ArrayList<String> arrayList = new ArrayList<>();
        LaunchOptions options = new LaunchOptionsBuilder().withArgs(arrayList).withHeadless(false).build();
        arrayList.add("--no-sandbox");
        arrayList.add("--disable-setuid-sandbox");
        Browser browser = Puppeteer.launch(options);
        Page page = browser.newPage();
        page.goTo("http://news.baidu.com/");

        ElementHandle elem = page.$("html");
        Clip boundingBox = elem.boundingBox();

        double height = boundingBox.getHeight();
        System.out.println(height);

        //鼠标移动到目标
        int y = 0;
        while (y < 5000) {
            System.out.println(y);
            page.mouse().wheel(0, y);
            y += 5;
        }

        //开始鼠标滚动
//        y = 0;
//        page.mouse().move(0,5000);
//        while (y < 5000) {
//            System.out.println(y);
//
//            y += 5;
//        }

        page.mouse().wheel(0, -10000);
        page.mouse().wheel(0, -10000);

        //观察效果
        Thread.sleep(400000L);
    }
}
