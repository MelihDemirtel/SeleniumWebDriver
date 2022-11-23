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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCheckBox {
    static WebDriver cdriver;
    static JavascriptExecutor jsx;

    static int milis = 1000;//Bekleme için 1 saniye değişkeni tanımlanır

    static WebElement checkBoxHome;
    static boolean isSelectedHome;
    static List<WebElement> resultsHome;
    static WebElement toggleButton1;
    static WebElement checkBoxDesktop;
    static WebElement checkBoxDocuments;
    static WebElement checkBoxDownloads;
    static  boolean isSelectedDesktop;
    static  List<WebElement> resultsDesktop;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        jsx = (JavascriptExecutor) cdriver;

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/checkbox");
    }
    @AfterClass
    public static void tearDown() throws SocketException, InterruptedException {
        cdriver.quit();
    }
    @Test
    public void test01ClickUpHome() throws InterruptedException {
        Thread.sleep(milis);
        checkBoxHome = cdriver.findElement(By.xpath("//span[contains(text(),'Home')]"));
        isSelectedHome = checkBoxHome.isSelected();
        if(isSelectedHome == false) {
            checkBoxHome.click();
        }
    }
    @Test
    public void test02SoutResultsHome() throws InterruptedException {
        Thread.sleep(milis);
        resultsHome = cdriver.findElements(By.xpath("//span[@class='text-success']"));

        for(WebElement result : resultsHome) {
            System.out.println(result.getText());
        }
        System.out.println("=============");
    }
    @Test
    public void test03ClickDownHome() throws InterruptedException {
        Thread.sleep(milis);
        if(isSelectedHome == false) {
            checkBoxHome.click();
        }
    }
    @Test
    public void test04ClickToggle() throws InterruptedException {
        Thread.sleep(milis);
        toggleButton1 = cdriver.findElement(By.xpath("//button[@title='Toggle']"));
        toggleButton1.click();
    }
    @Test
    public void test05AssertsCheckBoxes() throws InterruptedException {
        Thread.sleep(milis);
        checkBoxDesktop = cdriver.findElement(By.xpath("//span[contains(text(),'Desktop')]"));
        checkBoxDocuments = cdriver.findElement(By.xpath("//span[contains(text(),'Documents')]"));
        checkBoxDownloads= cdriver.findElement(By.xpath("//span[contains(text(),'Downloads')]"));

        Assert.assertTrue(checkBoxDesktop.isDisplayed());
        Assert.assertTrue(checkBoxDocuments.isDisplayed());
        Assert.assertTrue(checkBoxDownloads.isDisplayed());
    }
    @Test
    public void test06ClickUpDesktop() throws InterruptedException {
        Thread.sleep(milis);
        isSelectedDesktop = checkBoxDesktop.isSelected();
        if(isSelectedDesktop == false) {
            checkBoxDesktop.click();
        }
    }
    @Test
    public void test07SoutResultsDesktop() throws InterruptedException {
        Thread.sleep(milis);
        resultsDesktop = cdriver.findElements(By.xpath("//span[@class='text-success']"));
        for(WebElement result : resultsDesktop) {
            System.out.println(result.getText());
        }
        System.out.println("=============");
    }
    @Test
    public void test08ClickDownDesktop() throws InterruptedException {
        Thread.sleep(milis);
        if(isSelectedDesktop == false) {
            checkBoxDesktop.click();
        }
    }
}
