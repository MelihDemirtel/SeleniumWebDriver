package elementsExamplesTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.SocketException;
import java.util.concurrent.TimeUnit;

public class TestTextBox {
    static WebDriver cdriver;
    static int milis = 2000;//Bekleme için 2 saniye değişkeni tanımlanır

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        JavascriptExecutor jsx = (JavascriptExecutor) cdriver;//Sayfada scroll yapabilmek için yazdık

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/text-box");
    }
    @AfterClass
    public static void tearDown() throws SocketException {
        cdriver.quit();
    }
    @Test
    public void test01() throws InterruptedException {

    }
}
