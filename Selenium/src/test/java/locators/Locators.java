package locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        WebDriver cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        int milis = 2000;

        cdriver.get("https://www.demoqa.com/login");
        Thread.sleep(milis);

        
    }
}
