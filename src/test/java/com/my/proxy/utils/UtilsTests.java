package com.my.proxy.utils;

import com.my.proxy.resbody.SearchResult;
import org.json.JSONArray;
import org.json.JSONTokener;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UtilsTests {
    @Test
    public void googleSearchUrlGenTester() {
        String searchTerm = "test";
        String apiKey = "testApi";
        String searchEngine = "testEgine";
        String url = Utils.googleSearchUrlGen(searchTerm, apiKey, searchEngine);
        Assert.assertEquals("https://www.googleapis.com/customsearch/v1?key=testApi&cx=testEgine&q=test&num=10", url);
    }

    @Test
    public void mapSearchResultsTester() throws Exception {
        String strJson = "[{\n" +
                "        \"kind\": \"customsearch#result\",\n" +
                "        \"title\": \"Connectory Stuttgart - Powered by Robert Bosch GmbH ...\",\n" +
                "        \"htmlTitle\": \"Connectory Stuttgart - Powered by Robert \\u003cb\\u003eBosch GmbH\\u003c/b\\u003e ...\",\n" +
                "        \"link\": \"https://www.google.com/maps/search/?api=1&query=Connectory+Stuttgart+-+Powered+by+Robert+Bosch+GmbH%2C+K%C3%B6nigstra%C3%9Fe+78%2C+Stuttgart%2C+BW%2C+70173%2C+de&query_place_id=ChIJ26Nb4nzbmUcRLQTPb5YN9Cc\",\n" +
                "        \"displayLink\": \"www.google.com\",\n" +
                "        \"snippet\": \"Connectory Stuttgart - Powered by Robert Bosch GmbH, Königstraße 78, Stuttgart, BW, 70173, de. Your location. Trails. Dedicated lanes.\",\n" +
                "        \"htmlSnippet\": \"Connectory Stuttgart - Powered by Robert \\u003cb\\u003eBosch GmbH\\u003c/b\\u003e, Königstraße 78, Stuttgart, BW, 70173, de. Your location. Trails. Dedicated lanes.\",\n" +
                "        \"cacheId\": \"oaB-IktZP_gJ\",\n" +
                "        \"formattedUrl\": \"https://www.google.com/.../search/?...Bosch+GmbH%2C...\",\n" +
                "        \"htmlFormattedUrl\": \"https://www.google.com/.../search/?...\\u003cb\\u003eBosch\\u003c/b\\u003e+\\u003cb\\u003eGmbH\\u003c/b\\u003e%2C...\",\n" +
                "        \"pagemap\": {\n" +
                "          \"cse_thumbnail\": [\n" +
                "            {\n" +
                "              \"src\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQUDTMfgWSotdHvbOKW4AmY1steqsilnDJS4q33zGx-d0AXvvxPiFY9Mvs\",\n" +
                "              \"width\": \"204\",\n" +
                "              \"height\": \"247\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"metatags\": [\n" +
                "            {\n" +
                "              \"referrer\": \"origin\",\n" +
                "              \"twitter:card\": \"summary\",\n" +
                "              \"theme-color\": \"#3367d6\",\n" +
                "              \"viewport\": \"initial-scale=1.0, user-scalable=no\",\n" +
                "              \"google\": \"notranslate\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"cse_image\": [\n" +
                "            {\n" +
                "              \"src\": \"https://maps.gstatic.com/tactile/basepage/pegman_sherlock.png\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      }]";
        JSONArray jArray = (JSONArray) new JSONTokener(strJson).nextValue();
        ArrayList<SearchResult> mapResults = Utils.mapSearchResults(jArray);
        ArrayList<SearchResult> expected = new ArrayList<SearchResult>();
        SearchResult expSearchResult = new SearchResult();
        expSearchResult.setLink("https://www.google.com/maps/search/?api=1&query=Connectory+Stuttgart+-+Powered+by+Robert+Bosch+GmbH%2C+K%C3%B6nigstra%C3%9Fe+78%2C+Stuttgart%2C+BW%2C+70173%2C+de&query_place_id=ChIJ26Nb4nzbmUcRLQTPb5YN9Cc");
        expSearchResult.setTitle("Connectory Stuttgart - Powered by Robert Bosch GmbH ...");
        expSearchResult.setSnippet("Connectory Stuttgart - Powered by Robert Bosch GmbH, Königstraße 78, Stuttgart, BW, 70173, de. Your location. Trails. Dedicated lanes.");
        expSearchResult.setHtmlSnippet("Connectory Stuttgart - Powered by Robert <b>Bosch GmbH</b>, Königstraße 78, Stuttgart, BW, 70173, de. Your location. Trails. Dedicated lanes.");
        expSearchResult.setHtmlTitle("Connectory Stuttgart - Powered by Robert <b>Bosch GmbH</b> ...");
        expected.add(expSearchResult);
        Assert.assertEquals(expected, mapResults);
    }
}
