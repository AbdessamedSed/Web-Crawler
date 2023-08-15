package org.example;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        ArrayList<WebCrawler> webSpider = new ArrayList<>();
        webSpider.add(new WebCrawler("https://en.wikipedia.org", 1));
        webSpider.add(new WebCrawler("https://www.bbc.com/news", 2));
        webSpider.add(new WebCrawler("https://github.com", 3));
        webSpider.add(new WebCrawler("https://www.reddit.com", 4));
        webSpider.add(new WebCrawler("https://www.etsy.com", 5));


        for (WebCrawler wc : webSpider) {
            try {
                wc.thread.join();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

    }
}