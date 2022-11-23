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

public class TestRadioButton {
    static WebDriver cdriver;
    static JavascriptExecutor jsx;

    static int milis = 1000;//Bekleme için 1 saniye değişkeni tanımlanır

    static WebElement radioButtonYes;
    static WebElement radioButtonImpressive;
    static WebElement radioButtonNo;

    static WebElement displayYes;
    static boolean isSelectedYes;

    static WebElement displayImpressive;
    static boolean isSelectedImpressive;

    static  boolean isClickableNo;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        jsx = (JavascriptExecutor) cdriver;

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/radio-button");
    }
    @AfterClass
    public static void tearDown() throws SocketException, InterruptedException {
        cdriver.quit();
    }
    @Test
    public void test01ClickUpYes() throws InterruptedException {
        Thread.sleep(milis);
        radioButtonYes = cdriver.findElement(By.xpath("//label[@for='yesRadio']"));

        isSelectedYes = radioButtonYes.isSelected();
        if(!isSelectedYes) {
            radioButtonYes.click();
            displayYes = cdriver.findElement(By.xpath("//span[contains(text(),'Yes')]"));
            Assert.assertTrue(displayYes.isDisplayed());
            System.out.println("Yes Seçildi");
        }
    }
    @Test
    public void test02ClickUpImpressive() throws InterruptedException {
        Thread.sleep(milis);
        radioButtonImpressive = cdriver.findElement(By.xpath("//label[@for='impressiveRadio']"));

        isSelectedImpressive = radioButtonImpressive.isSelected();
        if(!isSelectedImpressive) {
            radioButtonImpressive.click();
            displayImpressive = cdriver.findElement(By.xpath("//span[contains(text(),'Impressive')]"));
            Assert.assertTrue(displayImpressive.isDisplayed());
            System.out.println("Impressive Seçildi");
        }
    }
    @Test
    public void test03NotClikableNo() throws InterruptedException {
        Thread.sleep(milis);
        radioButtonNo = cdriver.findElement(By.xpath("//label[@for='noRadio']"));

        isClickableNo = radioButtonNo.isEnabled();
        if(isClickableNo){
            System.out.println("No Seçilemez");
        }
    }
}
