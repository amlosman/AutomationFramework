package BasePackage;
import FileWrappers.ReadFromPropertiesFile;
import com.google.common.io.Files;

import org.apache.http.HttpResponse;
import org.openqa.selenium.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WebActionsForElement {
WebDriver driver;
    public WebActionsForElement(WebDriver driver)
    {
        this.driver=driver;
    }

    public  WebElement waitUntilCanValidat(By locator, ExpectedConditionsEnum condition) {
        try {
            WebElement element = null;
            switch (condition) {
                case presenceOfElement:
                    element = waitUntil((ExpectedConditions.presenceOfElementLocated(locator)));
                    return element;

                case ElementToBeClickable:
                    element = waitUntil((ExpectedConditions.elementToBeClickable(locator)));
                    return element;
                case visibilityOfElement:
                    element=waitUntil((ExpectedConditions.elementToBeClickable(locator)));
                    return element;

                default:
                    element = null;
                    Assert.fail("Wrong condition");
            }
            return element ;
        } catch (Exception e) {
            return null;
        }
    }
    public WebElement waitUntil(ExpectedCondition<WebElement> s){
        try{
            return new WebDriverWait(driver, 100).until(s);

        }
        catch(Exception e){
            return null;
        }
    }
    public  By returnElementLocatorBy(String selector, Locators l){
        switch (l){
            case XPath:
                return new By.ByXPath(selector);

            case id:
                return new By.ById(selector);


            case CSS:
                return new By.ByCssSelector(selector);
            case linkText:
                return new By.ByLinkText(selector);

            default: return  null;
        }
    }
    public  WebDriver clickonElement(String  selector , Locators l, ExpectedConditionsEnum ConditionOnCurrentPage) {

            By locator = returnElementLocatorBy(selector,l);
            waitUntilCanValidat(locator, ExpectedConditionsEnum.presenceOfElement);
            WebElement element=waitUntilCanValidat(locator,ConditionOnCurrentPage);
        element.click();
        return  driver;
        }
    public enum Locators {
        XPath,
        CSS,
        id,
        linkText
    }

    public enum ExpectedConditionsEnum{
        presenceOfElement,
        ElementToBeClickable,
        visibilityOfElement
    }
    public void takeScreenShoot(String Error ){


        var camera = (TakesScreenshot)driver;
        File sceenShot = camera.getScreenshotAs(OutputType.FILE);
        try {
            Error = String.format(ReadFromPropertiesFile.getValue("screenShot"),Error);
            Files.move(sceenShot, new File(Error));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void DoubleClickonButton(WebElement element)
    {

        Actions action = new Actions(driver);
        action.doubleClick(element).perform();
    }

    public  void VerifyLink(String urlLink) {
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
    public void DragDrop(WebElement source ,WebElement Target){
    Actions actions = new Actions(driver);
    actions.dragAndDrop(source,Target).perform();
    }
   public void AlertSimple(WebElement element , String AssertionString)
   {
       Alert alert = driver.switchTo().alert();
       Assert.assertEquals(AssertionString,alert.getText());
       alert.accept();

   }
    public void AlertpromptAcepted(WebElement element,WebElement AssertE , String keys,String AssertionString )
    {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(keys);
        alert.accept();
        Assert.assertEquals(AssertionString,AssertE.getText());

    }
    public void Alertpromptcancel(WebElement element,WebElement AssertE ,String AssertionString ) {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        Assert.assertEquals(AssertionString,AssertE.getText());

    }
    public void AlertConfirmAcepted(WebElement element,WebElement AssertE , String AssertionString ) {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Assert.assertEquals(AssertionString,AssertE.getText());

    }
    public void AlertConfirmcancel(WebElement element,WebElement AssertE ,String AssertionString ) {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        Assert.assertEquals(AssertionString,AssertE.getText());

    }
    public void ScrollWithJS() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(200,300)");
            Thread.sleep(2000);
        js.executeScript("window.scrollTo(300,100)");

    }
    public void ContextMenu(WebElement ClickMeBtn,WebElement contextMenu) throws InterruptedException {
        Actions builder = new Actions(driver);
        builder
                .contextClick(ClickMeBtn)
                .moveToElement(contextMenu)
                .click()
                .perform();

        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, 20);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals("clicked: edit", alert.getText());
        alert.dismiss();
    }
    public int VerifyImageActive(WebElement imgElement)  {
        HttpClient client = (HttpClient) HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(imgElement.getAttribute("src"));

        try {
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
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
    public static ChromeOptions chromeOption()
    {
        String downloadPath = System.getProperty("user.dir")+"\\Downloads";
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default.content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);
        options.setExperimentalOption("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return options ;
    }
    

    public void Implicitwait()
    {
        //will wait All 20 second
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    public void ExplicitWait(WebElement element)
    {
        // will wait untill Element Apper
        WebDriverWait wait = new WebDriverWait(driver,200);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void fleuntwait(String  element)
    {
        /*can retry times of search for element and ignoring NonsuchElement Exception helpul in AjAx Applection
        * becouse AJAX server not send its update frequently , we can use it if time changed of Appered element
        * */
        Wait<WebDriver>wait = new FluentWait<>(driver).withTimeout(20,TimeUnit.SECONDS)
                .pollingEvery(2,TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

        WebElement element1 = wait.until(new Function<WebDriver,WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id(element));
            }
        });
    }
    
    
    

}
