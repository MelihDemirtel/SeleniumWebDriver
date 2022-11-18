package manageMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Time;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

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

        cdriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        /*
            Driver'a kullanacagimiz webelementlerin bulunmasi icin
            bekleyecegi maximum sureyi belirtir.
            Driver bu sure icerisinde web elementi bulursa
            beklemeden calismaya devam eder.
            Bu sure bittigi halde webElement bulunamamissa
            exception vererek calismayi durdurur.
         */

        cdriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
        /*
            Bu, bir hata atmadan önce bir sayfanın tamamen yüklenmesi için
            beklenecek süreyi ayarlar.
            Zaman aşımı negatifse, sayfa yüklemeleri süresiz olabilir.
         */

        cdriver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        /*
            Bu, WebDriver'ın bir hata atmadan önce eşzamansız bir komut dosyasının
            yürütmeyi bitirmesini beklemesi gereken süreyi ayarlamak için kullanılır.
            Zaman aşımı negatifse, betiğin süresiz olarak çalışmasına izin verilir.
         */

        cdriver.quit();//Browser'ı kapatır

    }
}
