package memedealer5000.restapi.requests;

import memedealer5000.restapi.constants.ServiceConstants;
import memedealer5000.restapi.parsers.MailRuParser;
import memedealer5000.restapi.parsers.SinoptikParser;
import memedealer5000.restapi.parsers.YandexParser;

import java.util.HashMap;
import java.util.Map;

public class GetWeatherRequest {
    public static Map<String, String> getWeatherByCity(String city){
        var resultHashMap = new HashMap<String,String>();
        resultHashMap.put(ServiceConstants.YANDEX, new YandexParser().parse(city));
        resultHashMap.put(ServiceConstants.MAIL_RU, new MailRuParser().parse(city));
        resultHashMap.put(ServiceConstants.SIMOPTIK, new SinoptikParser().parse(city));
        return resultHashMap;
    }
}
