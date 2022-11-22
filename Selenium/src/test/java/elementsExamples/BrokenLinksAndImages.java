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
        int milis = 500;//Bekleme için yarım saniye değişkeni tanımlanır
        int pageLoad = 2000;//Bekleme için 2 saniye değişkeni tanımlanır
        JavascriptExecutor jsx = (JavascriptExecutor) cdriver;//Sayfada scroll yapabilmek için yazdık

        String expectedUrl = "https://demoqa.com/";//Beklenen URL adresini tanımladık

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/broken");
        Thread.sleep(pageLoad);//Sayfanın yüklenmesi için beklenir

        jsx.executeScript("window.scrollBy(0,250)","");//Sayfa biraz aşağı kaydırılır

        //Kırık link için
        String url = cdriver.findElement(By.cssSelector("a[href*='http://the-internet.herokuapp.com/status_codes/500']")).getAttribute("href");// a[href*='brokenlink'] // a[href*='soapui']

        HttpURLConnection connLink = (HttpURLConnection)new URL(url).openConnection();

        connLink.setRequestMethod("HEAD");
        connLink.connect();
        int responseCodeLink = connLink.getResponseCode();
        System.out.println("Code : "+responseCodeLink);
        if(responseCodeLink > 400)
        {
            System.out.println("Broken Link : "+url);
            Assert.assertEquals(responseCodeLink,500);
        }

        Thread.sleep(milis);

        //Kırık görsel için
        String img = cdriver.findElement(By.cssSelector("img[src*='/images/Toolsqa_1.jpg']")).getAttribute("src");// a[href*='brokenlink'] // a[href*='soapui']

        HttpURLConnection connImg = (HttpURLConnection)new URL(url).openConnection();

        connImg.setRequestMethod("HEAD");
        connImg.connect();
        int responseCodeImg = connImg.getResponseCode();
        System.out.println("Code : "+responseCodeImg);
        if(responseCodeImg > 400)
        {
            System.out.println("Broken Image : "+img);
            Assert.assertEquals(responseCodeImg,500);
        }

        cdriver.quit();//Browser kapatalır

    }
}
