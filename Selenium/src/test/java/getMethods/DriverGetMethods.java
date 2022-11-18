package getMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverGetMethods {

    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        WebDriver cdriver = new ChromeDriver();//Chrome driver çalıştırmak için

        cdriver.get("https://www.google.com");//Bu url'i çağırır

        System.out.println("Sayfa Title: "+cdriver.getTitle());//Açık sayfanın ismini verir

        System.out.println("Açık Sayfa URL'i: "+cdriver.getCurrentUrl());//Açık sayfanın url'ini verir

        System.out.println("Açık Sayfanın Hash Code'u: "+cdriver.getWindowHandle());//Açık Sayfanın Hash Code'unu verir

        System.out.println("Açık Tüm Sayfaların Hash Code'u: "+cdriver.getWindowHandles());//Açık Tüm Sayfaların Hash Code'unu  verir

        System.out.println("=================================");
        System.out.println(cdriver.getPageSource());//Sayfanın kaynak kodunu verir.
        System.out.println("=================================");

        cdriver.quit();//Browserı kapatmak için

        WebDriverManager.firefoxdriver().setup();//Mozilla Firefox browser kurulumu
        WebDriver fdriver = new FirefoxDriver();//Mozilla Firefox driver çalıştırmak için

        fdriver.get("https://www.google.com");//Bu url'i çağırır

        System.out.println("Sayfa Title: "+fdriver.getTitle());//Açık sayfanın ismini verir

        System.out.println("Açık Sayfa URL'i: "+fdriver.getCurrentUrl());//Açık sayfanın url'ini verir

        System.out.println("Açık Sayfanın Hash Code'u: "+fdriver.getWindowHandle());//Açık Sayfanın Hash Code'unu verir

        System.out.println("Açık Tüm Sayfaların Hash Code'u: "+fdriver.getWindowHandles());//Açık Tüm Sayfaların Hash Code'unu  verir

        System.out.println("=================================");
        System.out.println(fdriver.getPageSource());//Sayfanın kaynak kodunu verir.
        System.out.println("=================================");

        fdriver.quit();//Browserı kapatmak için

        WebDriverManager.edgedriver().setup();//Edge browser kurulumu
        WebDriver edriver = new EdgeDriver();//Edge driver çalıştırmak için

        edriver.get("https://www.google.com");//Bu url'i çağırır

        System.out.println("Sayfa Title: "+edriver.getTitle());//Açık sayfanın ismini verir

        System.out.println("Açık Sayfa URL'i: "+edriver.getCurrentUrl());//Açık sayfanın url'ini verir

        System.out.println("Açık Sayfanın Hash Code'u: "+edriver.getWindowHandle());//Açık Sayfanın Hash Code'unu verir

        System.out.println("Açık Tüm Sayfaların Hash Code'u: "+edriver.getWindowHandles());//Açık Tüm Sayfaların Hash Code'unu  verir

        System.out.println("=================================");
        System.out.println(edriver.getPageSource());//Sayfanın kaynak kodunu verir.
        System.out.println("=================================");

        edriver.quit();//Browserı kapatmak için
    }
}
