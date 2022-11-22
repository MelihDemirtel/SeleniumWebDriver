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

        String url = cdriver.findElement(By.cssSelector("a[href*='http://the-internet.herokuapp.com/status_codes/500']")).getAttribute("href");// a[href*='brokenlink'] // a[href*='soapui']

        HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();

        conn.setRequestMethod("HEAD");
        conn.connect();
        int responseCode = conn.getResponseCode();
        System.out.println("Code : "+responseCode);
        if(responseCode > 400)
        {
            System.out.println("Broken Link : "+url);
            Assert.assertTrue(false);
        }

    }
}
