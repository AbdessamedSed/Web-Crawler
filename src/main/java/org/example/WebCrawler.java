package org.example;

import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class WebCrawler implements Runnable {

    private static final int MAX_DEPTH = 3;
    public Thread thread;
    private ArrayList<String> visitedLikns;
    private String first_link;
    private int ID;
    Document doc;

    public WebCrawler(String link, int num) {
        first_link = link;
        ID = num;
        visitedLikns = new ArrayList<>();

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public  void run() {
        crawl(1, first_link);
    }
    private void crawl(int level, String url) {
        String title;
        String next_link;
        try {
            if (level <= MAX_DEPTH) {
                Connection con = Jsoup.connect(url);
                doc = con.get();

                if (doc == null) throw new Exception("Exception : Empty Document");

                if (con.response().statusCode() == 200) {
                    title = doc.title();
                    visitedLikns.add(url);
                    System.out.println("\n" + title);
                }

                for (Element element : doc.select("a[href]")) {
                    next_link = element.absUrl("href");
                    if (!visitedLikns.contains(next_link)) {
                        crawl(level + 1 , next_link );
                    }
                }
            }

        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
