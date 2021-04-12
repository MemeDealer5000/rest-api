package memedealer5000.restapi.parsers;

import memedealer5000.restapi.constants.ServiceConstants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MailRuParser implements HtmlParser{
    @Override
    public String parse(String city){
    Document doc;
    String result = "Nothing";
    try {
        doc = Jsoup.connect(ServiceConstants.MAIL_RU_WEATHER_URL.concat(city)).get();
        result = doc.title();
    } catch (IOException e) {
    }
    return result;
}

}
