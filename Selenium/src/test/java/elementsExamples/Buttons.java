package elementsExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class Buttons {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        WebDriver cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        int milis = 500;//Bekleme için yarım saniye değişkeni tanımlanır
        int pageLoad = 2000;//Bekleme için 2 saniye değişkeni tanımlanır
        JavascriptExecutor jsx = (JavascriptExecutor) cdriver;//Sayfada scroll yapabilmek için yazdık

        //Beklenen durum mesajları tanımlanır
        String expectedDoubleClickMessage = "You have done a double click";
        String expectedRightClickMessage = "You have done a right click";
        String expectedClickMessage = "You have done a dynamic click";

        Actions actions = new Actions(cdriver);//Mouse tıklamaları için method tanımladık

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/buttons");
        Thread.sleep(pageLoad);//Sayfanın yüklenmesi için beklenir

        //Sayfadaki butonlar tanımlanır
        WebElement doubleClickButton = cdriver.findElement(By.id("doubleClickBtn"));
        WebElement rightClickButton = cdriver.findElement(By.id("rightClickBtn"));
        WebElement clickMeButton = cdriver.findElement(By.xpath("(//button[contains(text(),'Click Me')])[3]"));

        actions.doubleClick(doubleClickButton).perform();//Butona çift tıklama yapılır
        Thread.sleep(milis);

        String doubleClickMessage = cdriver.findElement(By.id("doubleClickMessage")).getText();//Çıkan mesaj değişkene atanır

        Assert.assertEquals(doubleClickMessage, expectedDoubleClickMessage);//Gerçekleşen ve beklenen mesaj karşılaştırılır

        jsx.executeScript("window.scrollBy(0,150)","");//Sayfa biraz aşağı kaydırılır

        actions.contextClick(rightClickButton).perform();//Butona sağ tıklama yapılır
        Thread.sleep(milis);

        String rightClickMessage = cdriver.findElement(By.id("rightClickMessage")).getText();//Çıkan mesaj değişkene atanır

        Assert.assertEquals(rightClickMessage, expectedRightClickMessage);//Gerçekleşen ve beklenen mesaj karşılaştırılır

        clickMeButton.click();//Butona tıklanır
        Thread.sleep(milis);

        String clickMessage = cdriver.findElement(By.id("dynamicClickMessage")).getText();//Çıkan mesaj değişkene atanır

        Assert.assertEquals(clickMessage, expectedClickMessage);//Gerçekleşen ve beklenen mesaj karşılaştırılır

        cdriver.quit();//Browser kapatılır

    }
}
