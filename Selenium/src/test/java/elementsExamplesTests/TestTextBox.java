package elementsExamplesTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.SocketException;
import java.util.concurrent.TimeUnit;

public class TestTextBox {
    static WebDriver cdriver;
    static JavascriptExecutor jsx;

    static int milis = 1000;//Bekleme için 1 saniye değişkeni tanımlanır
    static String fullName = "Test";
    static String email = "test@deneme.com";
    static String currentAddress = "Test, Test/Deneme";

    static WebElement fullNameLabel;
    static WebElement emailLabel;
    static WebElement currentAddressLabel;
    static WebElement submitButton;

    static String actualResultName;
    static String actualResultEmail;
    static String actualResultAddress;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        jsx = (JavascriptExecutor) cdriver;

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/text-box");

        fullNameLabel = cdriver.findElement(By.id("userName"));
        emailLabel = cdriver.findElement(By.id("userEmail"));
        currentAddressLabel = cdriver.findElement(By.id("currentAddress"));
        submitButton = cdriver.findElement(By.id("submit"));
    }
    @AfterClass
    public static void tearDown() throws SocketException, InterruptedException {
        System.out.println(actualResultName);
        Thread.sleep(milis);
        System.out.println(actualResultEmail);
        Thread.sleep(milis);
        System.out.println(actualResultAddress);
        Thread.sleep(milis);

        cdriver.quit();
    }
    @Test
    public void test01SendName() throws InterruptedException {
        Thread.sleep(milis);
        fullNameLabel.sendKeys(fullName);
    }
    @Test
    public void test02SendEmail() throws InterruptedException {
        Thread.sleep(milis);
        emailLabel.sendKeys(email);
    }
    @Test
    public void test03SendAddress() throws InterruptedException {
        Thread.sleep(milis);
        currentAddressLabel.sendKeys(currentAddress);
    }
    @Test
    public void test04ScrollDown() throws InterruptedException{
        Thread.sleep(milis);
        jsx.executeScript("window.scrollBy(0,350)","");//Sayfa aşağı kaydırılır
    }
    @Test
    public void test05ClickSubmit() throws InterruptedException {
        Thread.sleep(milis);
        submitButton.click();

        actualResultName = cdriver.findElement(By.id("name")).getText();
        actualResultEmail = cdriver.findElement(By.id("email")).getText();
        actualResultAddress = cdriver.findElement(By.xpath("//p[@id='currentAddress']")).getText();
    }
    @Test
    public void test06Asserts() throws InterruptedException {
        Thread.sleep(milis);
        Assert.assertEquals(actualResultName, "Name:"+fullName);
        Assert.assertEquals(actualResultEmail, "Email:"+email);
        Assert.assertEquals(actualResultAddress, "Current Address :"+currentAddress);
    }
}
