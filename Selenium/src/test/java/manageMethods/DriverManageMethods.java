package manageMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Time;

public class DriverManageMethods {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        WebDriver cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        int milis = 2000;//2 saniye bekleme süresi

        cdriver.get("https://www.google.com");
        Thread.sleep(milis);//Bekleme adımı için gerekli

        cdriver.manage().window().maximize();//Browser'ı tam ekran yapar
        Thread.sleep(milis);

        System.out.println("Pencere Boyutu: "+cdriver.manage().window().getSize());//Browser penceresinin boyutlarını verir
        Thread.sleep(milis);

        cdriver.manage().window().setSize(new Dimension(640,360));//Browser penceresinin boyutunu belirlemek için
        Thread.sleep(milis);

        cdriver.manage().window().setPosition(new Point(100,100));//Browser penceresinin açılma konumu koordinatlarını belirler
        Thread.sleep(milis);

        System.out.println("Pencere Konumu: "+cdriver.manage().window().getPosition());//Browser penceresinin açılma konumu koordinatlarını verir
        Thread.sleep(milis);

        cdriver.manage().window().fullscreen();//Browser penceresini full ekran yapar
        Thread.sleep(milis);

        cdriver.quit();//Browser'ı kapatır

    }
}
