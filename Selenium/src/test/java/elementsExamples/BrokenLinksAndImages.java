package elementsExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrokenLinksAndImages {

    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        WebDriver cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        int milis = 1000;//Bekleme için 1 saniye değişkeni tanımlanır
        int pageLoad = 2000;//Bekleme için 2 saniye değişkeni tanımlanır
        JavascriptExecutor jsx = (JavascriptExecutor) cdriver;//Sayfada scroll yapabilmek için yazdık

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/broken");
        Thread.sleep(pageLoad);//Sayfanın yüklenmesi için beklenir

        jsx.executeScript("window.scrollBy(0,250)","");//Sayfa biraz aşağı kaydırılır

        //Kırık link için
        String urlBroken = cdriver.findElement(By.cssSelector("a[href*='http://the-internet.herokuapp.com/status_codes/500']")).getAttribute("href");// a[href*='brokenlink'] // a[href*='soapui']

        HttpURLConnection connLinkBroken = (HttpURLConnection)new URL(urlBroken).openConnection();

        connLinkBroken.setRequestMethod("HEAD");
        connLinkBroken.connect();
        int responseCodeLinkBroken = connLinkBroken.getResponseCode();
        if(responseCodeLinkBroken != 200)
        {
            System.out.println("Code : "+responseCodeLinkBroken);
            System.out.println("Broken Link : "+urlBroken);
            Assert.assertEquals(responseCodeLinkBroken,500);
        }else{
            System.out.println("Wrong Code : "+responseCodeLinkBroken+" | Expected Code : 500");
        }

        Thread.sleep(milis);

        //Kırık görsel için, Normalde bu link kırık olmalı ancak sitede kırık değil o nedenle else komutu çalışıyor
        String imgBroken = cdriver.findElement(By.cssSelector("img[src*='/images/Toolsqa_1.jpg']")).getAttribute("src");// a[href*='brokenlink'] // a[href*='soapui']

        HttpURLConnection connImgBroken = (HttpURLConnection)new URL(imgBroken).openConnection();

        connImgBroken.setRequestMethod("HEAD");
        connImgBroken.connect();
        int responseCodeImgBroken = connImgBroken.getResponseCode();
        if(responseCodeImgBroken != 200)
        {
            System.out.println("Code : "+responseCodeImgBroken);
            System.out.println("Broken Image : "+imgBroken);
            Assert.assertEquals(responseCodeImgBroken,500);
        }else{
            System.out.println("Wrong Code : "+responseCodeImgBroken+" | Expected Code : 500");
        }

        Thread.sleep(milis);

        //Valid link için
        String urlValid = cdriver.findElement(By.cssSelector("a[href*='http://demoqa.com']")).getAttribute("href");// a[href*='brokenlink'] // a[href*='soapui']

        HttpURLConnection connLinkValid = (HttpURLConnection)new URL(urlValid).openConnection();

        connLinkValid.setRequestMethod("HEAD");
        connLinkValid.connect();
        int responseCodeLinkValid = connLinkValid.getResponseCode();
        if(responseCodeLinkValid == 301)
        {
            System.out.println("Code : "+responseCodeLinkValid);
            System.out.println("Valid Link : "+urlValid);
            Assert.assertEquals(responseCodeLinkValid,301);
        }else{
            System.out.println("Wrong Code : "+responseCodeLinkValid+" | Expected Code : 301");
        }

        Thread.sleep(milis);

        //Valid görsel için
        String imgValid = cdriver.findElement(By.cssSelector("img[src*='/images/Toolsqa.jpg']")).getAttribute("src");// a[href*='brokenlink'] // a[href*='soapui']

        HttpURLConnection connImgValid = (HttpURLConnection)new URL(imgValid).openConnection();

        connImgValid.setRequestMethod("HEAD");
        connImgValid.connect();
        int responseCodeImgValid = connImgValid.getResponseCode();
        if(responseCodeImgValid == 200)
        {
            System.out.println("Code : "+responseCodeImgValid);
            System.out.println("Valid Image : "+imgValid);
            Assert.assertEquals(responseCodeImgValid,200);
        }else{
            System.out.println("Wrong Code : "+responseCodeImgValid+" | Expected Code : 200");
        }

        cdriver.quit();//Browser kapatalır

    }
}
