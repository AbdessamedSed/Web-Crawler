package org.example;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        ArrayList<WebCrawler> webSpider = new ArrayList<>();
        webSpider.add(new WebCrawler("https://abcnews.go.com", 1));
        webSpider.add(new WebCrawler("https://www.npr.org", 2));
        webSpider.add(new WebCrawler("https://www.nytimes.com", 3));

        for (WebCrawler wc : webSpider) {
            try {
                wc.thread.join();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

    }
}