package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;


public class RateParser {

    private String url = "https://finance.ua/ua/currency";
    private Document doc = null;

    public RateParser() {
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBuyUsdRate() {
        Element kursUSD = doc.select("td.c2").get(0);
        return kursUSD.text();
    }

    public String getBuyEurRate() {
        Element kursEUR = doc.select("td.c2").get(1);
        return kursEUR.text();
    }

    public String getBuyRubRate() {
        Element kursRUB = doc.select("td.c2").get(2);
        return kursRUB.text();
    }

    public String getSellUsdRate() {
        Element kursUSD = doc.select("td.c3").get(0);
        return kursUSD.text();
    }

    public String getSellEurRate() {
        Element kursEUR = doc.select("td.c3").get(1);
        return kursEUR.text();
    }

    public String getSellRubRate() {
        Element kursRUB = doc.select("td.c3").get(2);
        return kursRUB.text();
    }

}
