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

        String expectedUrl = "https://demoqa.com/";//Beklenen URL adresini tanımladık

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.navigate().to("https://demoqa.com/links");//Navigate metodunu kullanabilmek için URl i navigate to olarak çağırdık. Get ile çağırdığımızda bu metodları kullanamıyoruz
        Thread.sleep(pageLoad);//Sayfanın yüklenmesi için beklenir

        WebElement homeLink = cdriver.findElement(By.id("simpleLink"));//Sayfadaki link butonu tanımlanır

        homeLink.click();//Home linkine tıklanır
        Thread.sleep(pageLoad);

        //Açılan sekmeler için bir küme seti oluşturuypruz
        Set<String> windows = cdriver.getWindowHandles();//

        //Set arayüzündeki verileri iterator ile okuyoruz
        //List ve Set koleksiyonları üzerinde, bütün öğeleri tarayacak biçimde tekrarlanan eylemleri gerçekleştirmek için kullanılır
        Iterator<String> iterator = windows.iterator();

        //Next ile sıra ile sekmeleri değişkenlere tanımlıyoruz
        String parentId = iterator.next();//İlk sayfa
        String childId = iterator.next();//ikinci sekme

        cdriver.switchTo().window(childId);//2.Sekmeye geçilir
        Thread.sleep(pageLoad);
        String actualUrl = cdriver.getCurrentUrl();//2.Sekmenin URL i değişkene atanır

        Assert.assertEquals(actualUrl, expectedUrl);//.Gerçekleşen ve beklenen sonuç karşılaştırılır

        cdriver.close();//2.Sekme yani açık olan sekme kapanır
        Thread.sleep(pageLoad);

        cdriver.switchTo().window(parentId);//İlk sekmeye geri dönülür

        Assert.assertTrue(homeLink.isDisplayed());//İlk sekmede olduğumuz doğrulanır

        cdriver.quit();//Browser kapatılır

    }
}
