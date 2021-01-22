package com.example.demo.pttgamesale.test;

import java.io.IOException;
import java.util.ArrayList;

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

import com.example.demo.unit.LoadFile;

public class HttpClientTest {

	public void getHtml(String url) {
		//1.生成httpclient，相當於該開啟一個瀏覽器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        //2.建立get請求，相當於在瀏覽器位址列輸入 網址
        HttpGet request = new HttpGet(url);
        try {
            //3.執行get請求，相當於在輸入位址列後敲回車鍵
            response = httpClient.execute(request);
            
            //4.判斷響應狀態為200，進行處理
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //5.獲取響應內容
                HttpEntity httpEntity = response.getEntity();
                String html = EntityUtils.toString(httpEntity, "utf-8");
                getNewArticleTitle(html);
                
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
    }
	//Jsoup解析html獲得需要的標題
	public ArrayList<String> getNewArticleTitle(String html) {
		ArrayList<String> newArticleTitles = new ArrayList<>();
		int i = 0;
		Document document = Jsoup.parse(html);
		Elements postItems = document.getElementsByClass("title");
		for(Element postItem : postItems) {
			newArticleTitles.add(postItem.text());
			System.out.println(postItem.text());
		}
		System.out.println("==============");
		for(String oldTitle : new LoadFile().loadPttGameSaleTitle()) {
			System.out.println(oldTitle);
		}
		
//		new SaveFile().savePttGameSaleTitle(newArticleTitles);
		return newArticleTitles;
	}

}
