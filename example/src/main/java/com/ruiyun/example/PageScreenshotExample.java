package com.ruiyun.example;

import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.browser.BrowserFetcher;
import com.ruiyun.jvppeteer.core.page.ElementHandle;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.Clip;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;
import com.ruiyun.jvppeteer.options.ScreenshotOptions;
import com.ruiyun.jvppeteer.options.Viewport;
import com.ruiyun.jvppeteer.options.WaitForSelectorOptions;

import java.util.ArrayList;

public class PageScreenshotExample {

    public static void main(String[] args) throws Exception {
        //自动下载，第一次下载后不会再下载
//        BrowserFetcher.downloadIfNotExist(null);

        ArrayList<String> arrayList = new ArrayList<>();

        LaunchOptions options = new LaunchOptionsBuilder().withArgs(arrayList).withHeadless(true).withIgnoreHTTPSErrors(true).build();
        arrayList.add("--no-sandbox");
        arrayList.add("--disable-setuid-sandbox");
        Browser browser = Puppeteer.launch(options);
        Page page = browser.newPage();
//        page.goTo("https://www.baidu.com/?tn=98012088_10_dg&ch=3");
        page.setDefaultTimeout(1000 * 6);
        page.goTo("https://905028523puy.scd.wezhan.cn/", true);

        // 根据dom判断是否加载完
//            WaitForSelectorOptions waitForSelectorOptions = new WaitForSelectorOptions();
//            waitForSelectorOptions.setTimeout(1000*15);
//            waitForSelectorOptions.setVisible(Boolean.FALSE);

        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        screenshotOptions.setType("png");
        screenshotOptions.setFullPage(Boolean.TRUE);
        screenshotOptions.setPath("101.png");

        //鼠标移动到目标
        int step = 200;

        int y = 0;
        while (y < 5000) {
            System.out.println(y);
            page.mouse().wheel(0, step);
            y += step;
        }
//        page.mouse().move(0,5000);
        //开始鼠标滚动
        while (y > 0) {
            System.out.println(y);
            page.mouse().wheel(0, -1 * step);
            y -= step;
        }

        Thread.sleep(1000 * 2);
        page.screenshot(screenshotOptions);
//        Thread.sleep(1000 * 2);

        page.close();
        browser.close();
    }
}
