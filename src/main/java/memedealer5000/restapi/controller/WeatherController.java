package memedealer5000.restapi.controller;

import memedealer5000.restapi.constants.ServiceConstants;
import memedealer5000.restapi.requests.GetWeatherRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("weather")
public class WeatherController {
    @GetMapping
    public Map<String,String> list(){
        return GetWeatherRequest.getWeatherByCity("Ekaterinburg");
    }
    @GetMapping("services")
    public List<Map<String,String>> getServices(){
        List<Map<String,String>> servicesInformation = new ArrayList<>();

        HashMap<String,String> yandexInfo = new HashMap<String, String>();
        yandexInfo.put(ServiceConstants.SERVICE_NAME, ServiceConstants.YANDEX);
        yandexInfo.put(ServiceConstants.AVAILABILITY, checkInternetConnection(ServiceConstants.YANDEX_WEATHER_URL_RAW).toString());
        servicesInformation.add(yandexInfo);

        HashMap<String,String> mailRuInfo = new HashMap<String, String>();
        mailRuInfo.put(ServiceConstants.SERVICE_NAME, ServiceConstants.MAIL_RU);
        mailRuInfo.put(ServiceConstants.AVAILABILITY, checkInternetConnection(ServiceConstants.MAIL_RU_WEATHER_URL_RAW).toString());
        servicesInformation.add(mailRuInfo);

        HashMap<String,String> sinoptikInfo = new HashMap<String, String>();
        sinoptikInfo.put(ServiceConstants.SERVICE_NAME, ServiceConstants.SIMOPTIK);
        sinoptikInfo.put(ServiceConstants.AVAILABILITY, checkInternetConnection(ServiceConstants.SINOPTIK_WEATHER_URL_RUS_RAW).toString());
        servicesInformation.add(sinoptikInfo);

        return servicesInformation;
    }

    private Boolean checkInternetConnection(String url){
        Boolean result = false;
        HttpURLConnection con = null;
        try {
            // HttpURLConnection.setFollowRedirects(false);
            // HttpURLConnection.setInstanceFollowRedirects(false)
            con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("HEAD");
            result = (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
