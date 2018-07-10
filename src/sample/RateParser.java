package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;


public class RateParser {

    private String url = "https://www.yandex.ru/";
    private Document doc = null;

    public RateParser() {
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsdRate() {
        Element kursUSD = doc.select(".inline-stocks__value_inner").first();
        return kursUSD.text();
    }

    public String getEurRate() {
        Element kursEUR = doc.select(".inline-stocks__value_inner").last();
        return kursEUR.text();
    }

}
