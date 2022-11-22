package elementsExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Links {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        WebDriver cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        int milis = 500;//Bekleme için yarım saniye değişkeni tanımlanır
        int pageLoad = 2000;//Bekleme için 2 saniye değişkeni tanımlanır
        JavascriptExecutor jsx = (JavascriptExecutor) cdriver;//Sayfada scroll yapabilmek için yazdık

        String expectedUrl = "https://demoqa.com";

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.navigate().to("https://demoqa.com/links");
        Thread.sleep(pageLoad);//Sayfanın yüklenmesi için beklenir

        WebElement homeLink = cdriver.findElement(By.linkText("https://demoqa.com"));

        homeLink.click();
        Thread.sleep(pageLoad);

        Set<String> windows = cdriver.getWindowHandles();

        Iterator<String> iterator = windows.iterator();

        String parentId = iterator.next();
        String childId = iterator.next();

        cdriver.switchTo().window(childId);
        Thread.sleep(pageLoad);
        String actualUrl = cdriver.getCurrentUrl();

        Assert.assertEquals(actualUrl, expectedUrl);

        cdriver.close();

        Assert.assertTrue(homeLink.isDisplayed());

        cdriver.quit();

    }
}
