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
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestLinks {
    static WebDriver cdriver;
    static JavascriptExecutor jsx;

    static int milis = 1000;//Bekleme için 1 saniye değişkeni tanımlanır
    static int pageLoad = 2000;//Bekleme için 2 saniye değişkeni tanımlanır

    static String expectedUrl = "https://demoqa.com/";//Beklenen URL adresini tanımladık

    static WebElement homeLink;

    static Set<String> windows;

    static Iterator<String> iterator;

    static String parentId;
    static String childId;

    static String actualUrl;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        jsx = (JavascriptExecutor) cdriver;

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.navigate().to("https://demoqa.com/links");//Navigate metodunu kullanabilmek için URl i navigate to olarak çağırdık. Get ile çağırdığımızda bu metodları kullanamıyoruz

    }
    @AfterClass
    public static void tearDown() throws SocketException, InterruptedException {
        cdriver.quit();
    }
    @Test
    public void test01ClickHome() throws InterruptedException {
        Thread.sleep(milis);
        homeLink = cdriver.findElement(By.id("simpleLink"));//Sayfadaki link butonu tanımlanır
        Thread.sleep(pageLoad);
        homeLink.click();//Home linkine tıklanır
    }
    @Test
    public void test02WindowHandles() throws InterruptedException {
        Thread.sleep(milis);
        //Açılan sekmeler için bir küme seti oluşturuypruz
        windows = cdriver.getWindowHandles();//

        //Set arayüzündeki verileri iterator ile okuyoruz
        //List ve Set koleksiyonları üzerinde, bütün öğeleri tarayacak biçimde tekrarlanan eylemleri gerçekleştirmek için kullanılır
        iterator = windows.iterator();

        //Next ile sıra ile sekmeleri değişkenlere tanımlıyoruz
        parentId = iterator.next();//İlk sayfa
        childId = iterator.next();//ikinci sekme
    }
    @Test
    public void test03OpenSecondPage() throws InterruptedException {
        Thread.sleep(milis);
        cdriver.switchTo().window(childId);//2.Sekmeye geçilir
        Thread.sleep(pageLoad);
        actualUrl = cdriver.getCurrentUrl();//2.Sekmenin URL i değişkene atanır
        Assert.assertEquals(actualUrl, expectedUrl);//.Gerçekleşen ve beklenen sonuç karşılaştırılır
    }
    @Test
    public void test04CloseSecondPage() throws InterruptedException {
        Thread.sleep(pageLoad);
        cdriver.close();//2.Sekme yani açık olan sekme kapanır
    }
    @Test
    public void test05BackFirstPage() throws InterruptedException {
        Thread.sleep(pageLoad);
        cdriver.switchTo().window(parentId);//İlk sekmeye geri dönülür
        Assert.assertTrue(homeLink.isDisplayed());//İlk sekmede olduğumuz doğrulanır
    }
}
