package elementsExamplesTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.SocketException;
import java.util.concurrent.TimeUnit;

public class TestButtons {
    static WebDriver cdriver;
    static JavascriptExecutor jsx;
    static Actions actions;

    static int milis = 1000;//Bekleme için 1 saniye değişkeni tanımlanır

    //Beklenen durum mesajları tanımlanır
    static String expectedDoubleClickMessage = "You have done a double click";
    static String expectedRightClickMessage = "You have done a right click";
    static String expectedClickMessage = "You have done a dynamic click";

    //Sayfadaki butonlar tanımlanır
    static WebElement doubleClickButton;
    static WebElement rightClickButton;
    static WebElement clickMeButton;

    //Gerçekleşen mesaj değişkenleri tanımlanır
    static String doubleClickMessage;
    static String rightClickMessage;
    static String clickMessage;


    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        jsx = (JavascriptExecutor) cdriver;
        actions = new Actions(cdriver);

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/buttons");
    }
    @AfterClass
    public static void tearDown() throws SocketException, InterruptedException {
        cdriver.quit();
    }
    @Test
    public void test01DoubleClick() throws InterruptedException {
        Thread.sleep(milis);
        doubleClickButton = cdriver.findElement(By.id("doubleClickBtn"));
        actions.doubleClick(doubleClickButton).perform();//Butona çift tıklama yapılır
    }
    @Test
    public void test02AssertDoubleClick() throws InterruptedException {
        Thread.sleep(milis);
        doubleClickMessage = cdriver.findElement(By.id("doubleClickMessage")).getText();//Çıkan mesaj değişkene atanır
        Assert.assertEquals(doubleClickMessage, expectedDoubleClickMessage);//Gerçekleşen ve beklenen mesaj karşılaştırılır
        jsx.executeScript("window.scrollBy(0,150)","");//Sayfa biraz aşağı kaydırılır
    }
    @Test
    public void test03RightClick() throws InterruptedException {
        Thread.sleep(milis);
        rightClickButton = cdriver.findElement(By.id("rightClickBtn"));
        actions.contextClick(rightClickButton).perform();//Butona sağ tıklama yapılır
    }
    @Test
    public void test04AssertRightClick() throws InterruptedException {
        Thread.sleep(milis);
        rightClickMessage = cdriver.findElement(By.id("rightClickMessage")).getText();//Çıkan mesaj değişkene atanır
        Assert.assertEquals(rightClickMessage, expectedRightClickMessage);//Gerçekleşen ve beklenen mesaj karşılaştırılır
    }
    @Test
    public void test05ClickMe() throws InterruptedException {
        Thread.sleep(milis);
        clickMeButton = cdriver.findElement(By.xpath("(//button[contains(text(),'Click Me')])[3]"));
        clickMeButton.click();//Butona tıklanır
    }
    @Test
    public void test06AssertClickMe() throws InterruptedException {
        Thread.sleep(milis);
        clickMessage = cdriver.findElement(By.id("dynamicClickMessage")).getText();//Çıkan mesaj değişkene atanır
        Assert.assertEquals(clickMessage, expectedClickMessage);//Gerçekleşen ve beklenen mesaj karşılaştırılır
    }
}
