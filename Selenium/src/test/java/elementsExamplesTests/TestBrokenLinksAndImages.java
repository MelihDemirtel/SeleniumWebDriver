package elementsExamplesTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBrokenLinksAndImages {
    static WebDriver cdriver;
    static JavascriptExecutor jsx;

    static int milis = 1000;//Bekleme için 1 saniye değişkeni tanımlanır

    static String urlBroken;
    static String imgBroken;
    static String urlValid;
    static String imgValid;

    static HttpURLConnection connLinkBroken;
    static int responseCodeLinkBroken;

    static HttpURLConnection connImgBroken;
    static int responseCodeImgBroken;

    static HttpURLConnection connLinkValid;
    static int responseCodeLinkValid;

    static HttpURLConnection connImgValid;
    static int responseCodeImgValid;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        jsx = (JavascriptExecutor) cdriver;

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/broken");
    }
    @AfterClass
    public static void tearDown() throws SocketException, InterruptedException {
        cdriver.quit();
    }
    @Test
    public void test01BrokenLink() throws InterruptedException, IOException {
        Thread.sleep(milis);
        //Kırık link için
        urlBroken = cdriver.findElement(By.cssSelector("a[href*='http://the-internet.herokuapp.com/status_codes/500']")).getAttribute("href");// a[href*='brokenlink'] // a[href*='soapui']

        connLinkBroken = (HttpURLConnection)new URL(urlBroken).openConnection();

        connLinkBroken.setRequestMethod("HEAD");
        connLinkBroken.connect();
        responseCodeLinkBroken = connLinkBroken.getResponseCode();
        if(responseCodeLinkBroken != 200)
        {
            System.out.println("Code : "+responseCodeLinkBroken);
            System.out.println("Broken Link : "+urlBroken);
            Assert.assertEquals(responseCodeLinkBroken,500);
        }else{
            System.out.println("Wrong Code : "+responseCodeLinkBroken+" | Expected Code : 500");
        }
    }
    @Test
    public void test02BrokenImage() throws InterruptedException, IOException {
        Thread.sleep(milis);
        //Kırık görsel için, Normalde bu link kırık olmalı ancak sitede kırık değil o nedenle else komutu çalışıyor
        imgBroken = cdriver.findElement(By.cssSelector("img[src*='/images/Toolsqa_1.jpg']")).getAttribute("src");// a[href*='brokenlink'] // a[href*='soapui']

        connImgBroken = (HttpURLConnection)new URL(imgBroken).openConnection();

        connImgBroken.setRequestMethod("HEAD");
        connImgBroken.connect();
        responseCodeImgBroken = connImgBroken.getResponseCode();
        if(responseCodeImgBroken != 200)
        {
            System.out.println("Code : "+responseCodeImgBroken);
            System.out.println("Broken Image : "+imgBroken);
            Assert.assertEquals(responseCodeImgBroken,500);
        }else{
            System.out.println("Wrong Code : "+responseCodeImgBroken+" | Expected Code : 500");
        }
    }
    @Test
    public void test03UrlValid() throws InterruptedException, IOException {
        Thread.sleep(milis);
        //Valid link için
        urlValid = cdriver.findElement(By.cssSelector("a[href*='http://demoqa.com']")).getAttribute("href");// a[href*='brokenlink'] // a[href*='soapui']

        connLinkValid = (HttpURLConnection)new URL(urlValid).openConnection();

        connLinkValid.setRequestMethod("HEAD");
        connLinkValid.connect();
        responseCodeLinkValid = connLinkValid.getResponseCode();
        if(responseCodeLinkValid == 301)
        {
            System.out.println("Code : "+responseCodeLinkValid);
            System.out.println("Valid Link : "+urlValid);
            Assert.assertEquals(responseCodeLinkValid,301);
        }else{
            System.out.println("Wrong Code : "+responseCodeLinkValid+" | Expected Code : 301");
        }
    }
    @Test
    public void test04ImgValid() throws InterruptedException, IOException {
        Thread.sleep(milis);
        //Valid görsel için
        imgValid = cdriver.findElement(By.cssSelector("img[src*='/images/Toolsqa.jpg']")).getAttribute("src");// a[href*='brokenlink'] // a[href*='soapui']

        connImgValid = (HttpURLConnection)new URL(imgValid).openConnection();

        connImgValid.setRequestMethod("HEAD");
        connImgValid.connect();
        responseCodeImgValid = connImgValid.getResponseCode();
        if(responseCodeImgValid == 200)
        {
            System.out.println("Code : "+responseCodeImgValid);
            System.out.println("Valid Image : "+imgValid);
            Assert.assertEquals(responseCodeImgValid,200);
        }else{
            System.out.println("Wrong Code : "+responseCodeImgValid+" | Expected Code : 200");
        }
    }
}
