package com.my.proxy.searchproxy;

import com.my.proxy.resbody.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.my.proxy.reqbody.SearchTerm;
import com.my.proxy.utils.Utils;
import org.json.*;

import java.util.ArrayList;


@RestController
public class ProxyController {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Autowired
    private Environment env;
    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @PostMapping("/searchproxy")
    public ArrayList<SearchResult> searchProxy(@RequestBody SearchTerm searchTerm){
        // get api key and search engine id from env file
        String apiKey = env.getProperty("search.apikey");
        String engineId = env.getProperty("search.engineId");
        String searchTearm = searchTerm.getSearchterm();
        // generate google api url
        String url = Utils.googleSearchUrlGen(searchTearm, apiKey, engineId);
        // set search request to google

        String responseInString = restTemplate.getForObject(url, String.class);
        JSONObject responseInJson = new JSONObject(responseInString);
        JSONArray items = responseInJson.getJSONArray("items");
        return Utils.mapSearchResults(items);
    }
}
