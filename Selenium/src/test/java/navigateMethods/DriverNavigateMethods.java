package navigateMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverNavigateMethods {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        WebDriver cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        int milis = 2000;

        cdriver.get("https://www.facebook.com");
        Thread.sleep(milis);

        cdriver.navigate().to("https://www.google.com");//driver.get farkı bu şekilde verirseniz sayfada ileri geri yapabilirsiniz
        Thread.sleep(milis);

        cdriver.navigate().refresh();//Sayfayı yeniler
        Thread.sleep(milis);

        cdriver.navigate().back();//Bir önceki sayfaya döner
        Thread.sleep(milis);

        cdriver.navigate().forward();//Bir sonraki sayfaya gider
        Thread.sleep(milis);

        cdriver.close();//driver.quit farkı quit tüm pencereleri kapatır close açık olan sekmeyi kapatır

    }
}
