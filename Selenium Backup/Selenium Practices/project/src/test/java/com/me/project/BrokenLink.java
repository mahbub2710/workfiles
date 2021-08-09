package com.me.project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BrokenLink {

    public static void main(String[] args) throws InterruptedException {


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

//        driver.get("https://www.amazon.com/");
        driver.navigate().to("https://www.amazon.com/");
        Thread.sleep(1000);

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("No of links are " + links.size());

        List<String> urlList = new ArrayList<String>();

        for (WebElement e : links) {
            String url = e.getAttribute("href");
            urlList.add(url);
        }

        long startTime = System.currentTimeMillis();
            urlList.parallelStream().forEach(e -> checkBrokenLink(e));
        long endTime = System.currentTimeMillis();

        System.out.println("Total Time Taken" +(endTime-startTime));

    driver.quit();

    }

    public static void checkBrokenLink(String linkurl) {

     try{
         URL url = new URL(linkurl);
         HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
         httpURLConnection.setConnectTimeout(5000);
         httpURLConnection.connect();

         if (httpURLConnection.getResponseCode() >=400) {
             System.out.println(linkurl + "--->" + httpURLConnection.getResponseMessage() + "is a broken image.");
         }
         else {
             System.out.println(linkurl + "--->" + httpURLConnection.getResponseMessage());
         }

     } catch (Exception e) {
         e.printStackTrace();
     }

    }
}
