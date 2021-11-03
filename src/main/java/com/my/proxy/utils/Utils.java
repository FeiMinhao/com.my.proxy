package com.my.proxy.utils;

import com.my.proxy.resbody.SearchResult;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Utils {
    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String SNIPPET = "snippet";
    private static final String HTMLTITLE = "htmlTitle";
    private static final String HTMLSNIPPET = "htmlSnippet";
    /**
     * generate google search url
     * @param searchterm search term for google search
     * @return google url
     */
    public static String googleSearchUrlGen(String searchterm, String apiKey, String engineId){
        final Integer length = 10;
        String url = "https://www.googleapis.com/customsearch/v1?key=%s&cx=%s&q=%s&num=%s";
        return  String.format(url, apiKey, engineId, searchterm, length);
    }

    /**
     * map google search results
     * only keep the import properties in the structure
     * @param items items of search response
     * @return results in array
     * */
    public static ArrayList<SearchResult> mapSearchResults(JSONArray items) {
        ArrayList<SearchResult> resultsInArrayList = new ArrayList<>();
        for(int i = 0; i < items.length(); i++ ){
            SearchResult item = new SearchResult();
            item.setTitle(items.getJSONObject(i).getString(TITLE));
            item.setLink(items.getJSONObject(i).getString(LINK));
            item.setSnippet(items.getJSONObject(i).getString(SNIPPET));
            item.setHtmlTitle(items.getJSONObject(i).getString(HTMLTITLE));
            item.setHtmlSnippet(items.getJSONObject(i).getString(HTMLSNIPPET));
            resultsInArrayList.add(item);
        }
        return resultsInArrayList;
    }
}
