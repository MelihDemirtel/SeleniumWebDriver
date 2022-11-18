package webElements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebElements1 {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        WebDriver cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        int milis = 2000;//Bekleme için 2 saniye değişkeni tanımlanır
        JavascriptExecutor jsx = (JavascriptExecutor) cdriver;//Sayfada scroll yapabilmek için yazdık

        cdriver.manage().window().maximize();
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        String userName = "Test"; //Testte kullanacağımız kullanıcı adı değişkeni
        String password = "123456";//Testte kullanacağımız kullanıcı şifresi değişkeni
        String expectedErrorMessage = "Invalid username or password!";//Test sonucu beklediğimiz mesaj

        cdriver.get("https://demoqa.com/login");//Bu url'e gidilir

        WebElement userNameLabel = cdriver.findElement(By.id("userName"));
        /*
        Kullanıcı adı elementini tanımladık normalde şu şekilde de kullanılabilmekte:

        cdriver.findElement(By.id("userName")).sendKeys("Test");
         */
        WebElement passwordLabel = cdriver.findElement(By.id("password"));
        WebElement loginButton = cdriver.findElement(By.id("login"));

        userNameLabel.sendKeys(userName);//WebElementlerimize istediğimiz komutları gönderdik. sendKeys ile klavyeden istediğimiz String'i gönderebiliriz
        Thread.sleep(milis);//Bekleme komutu
        passwordLabel.sendKeys(password);
        Thread.sleep(milis);//Bekleme komutu
        jsx.executeScript("window.scrollBy(0,350)","");//Sayfa aşağı kaydırılır
        Thread.sleep(milis);//Bekleme komutu
        loginButton.click();//İlgili elemente tıklamak için click kullanılır
        Thread.sleep(milis);//Bekleme komutu

        String actualErrorMessage = cdriver.findElement(By.id("name")).getText();
        //Test sonucu çıkan mesajı ilgili elementten getText metodu ile alarak
        //String bir değişkene atadık

        //Eğer test sonucu çıkan mesaj bizim beklediğimiz mesaja eşitse test başarılı, değilse test başarısız yazı yazacak bir if döngüsü kurduk
        if(actualErrorMessage.equals(expectedErrorMessage)){
            System.out.println("Test Başarılı");
        } else {
            System.out.println("Test Başarısız");
        }

        cdriver.quit();
    }
}
