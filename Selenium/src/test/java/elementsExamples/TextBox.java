package elementsExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class TextBox {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        WebDriver cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        int milis = 2000;//Bekleme için 2 saniye değişkeni tanımlanır
        JavascriptExecutor jsx = (JavascriptExecutor) cdriver;//Sayfada scroll yapabilmek için yazdık

        String fullName = "Test";
        String email = "test@deneme.com";
        String currentAddress = "Test, Test/Deneme";

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/text-box");

        WebElement fullNameLabel = cdriver.findElement(By.id("userName"));
        WebElement emailLabel = cdriver.findElement(By.id("userEmail"));
        WebElement currentAddressLabel = cdriver.findElement(By.id("currentAddress"));
        WebElement submitButton = cdriver.findElement(By.id("submit"));

        fullNameLabel.sendKeys(fullName);
        Thread.sleep(milis);

        emailLabel.sendKeys(email);
        Thread.sleep(milis);

        currentAddressLabel.sendKeys(currentAddress);
        Thread.sleep(milis);

        jsx.executeScript("window.scrollBy(0,350)","");//Sayfa aşağı kaydırılır
        submitButton.click();
        Thread.sleep(milis);

        String actualResultName = cdriver.findElement(By.id("name")).getText();
        String actualResultEmail = cdriver.findElement(By.id("email")).getText();
        String actualResultAddress = cdriver.findElement(By.xpath("//p[@id='currentAddress']")).getText();

        Assert.assertEquals(actualResultName, "Name:"+fullName);
        Assert.assertEquals(actualResultEmail, "Email:"+email);
        Assert.assertEquals(actualResultAddress, "Current Address :"+currentAddress);

        System.out.println(actualResultName);
        System.out.println(actualResultEmail);
        System.out.println(actualResultAddress);

        cdriver.quit();

    }
}
