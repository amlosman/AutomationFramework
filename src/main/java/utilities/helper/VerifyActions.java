package utilities.helper;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class VerifyActions {


    public static boolean isDisplayed(WebDriver driver, By locator) {
        return new WebDriverWait(driver, 500).until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
    }

    public static boolean isClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, 500).
                until(ExpectedConditions.elementToBeClickable(locator)).isEnabled();
    }
    public static int verifyImageActive(WebElement imgElement)  {
        HttpClient client = (HttpClient) HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(imgElement.getAttribute("src"));

        try {
            HttpResponse response = client.execute((ClassicHttpRequest) request);

            if (response.getCode() != 200) {
                return 1 ;
            }
            else
            {
                return  0;
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return 1;

        } catch (IOException e) {
            e.printStackTrace();
            return 1;

        }
    }
    public static void VerifyLink(String urlLink) {
        try {
            URL link = new URL(urlLink);
            // create a connection using URL object
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            httpConn.setConnectTimeout(2000);
            httpConn.connect();

            // use getResponseCode() to get the response code
            if(httpConn.getResponseCode() == 200)
            {
                System.out.println(urlLink+" - "+httpConn.getResponseMessage());
            }
            if (httpConn.getResponseCode() == 404) {
                System.out.println(urlLink+" - "+httpConn.getResponseMessage());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
