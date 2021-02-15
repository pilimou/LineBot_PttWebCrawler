package com.example.demo.crawler.pttgamesale.html;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class HttpClientTest {

	public Map<String, String> getHtml(String url) {
		//1.生成httpclient，相當於該開啟一個瀏覽器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        Map<String, String> newTitles = null;
        String html = null;
        //2.建立get請求，相當於在瀏覽器位址列輸入 網址
        HttpGet request = new HttpGet(url);
        try {
            //3.執行get請求，相當於在輸入位址列後Enter
            response = httpClient.execute(request);
            
            //4.判斷響應狀態為200，進行處理
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //5.獲取響應內容
                HttpEntity httpEntity = response.getEntity();
                html = EntityUtils.toString(httpEntity, "utf-8");             
            } else {
                //如果返回狀態不是200，比如404（頁面不存在）等，根據情況做處理，這裡略
                System.out.println("返回狀態不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.關閉
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
        
        if(html!=null) {
        	newTitles = getNewArticleTitle(html);
        }
        
        return newTitles;
    }
	//Jsoup解析html獲得需要的標題
	public Map<String, String> getNewArticleTitle(String html) {
		Map<String, String> newArticleTitles = new LinkedHashMap<> ();
		Document document = Jsoup.parse(html);
		
		Elements postItems = document.getElementsByClass("title");

		//key=網址 , value=標題
		for(Element postItem : postItems) {
			if(!postItem.select("a").attr("href").equals("")) {
				newArticleTitles.put("https://www.ptt.cc" + postItem.select("a").attr("href"), postItem.text());
			}
		}
		
		return newArticleTitles;
	}

}
