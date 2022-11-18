package elementsExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckBox {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        WebDriver cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        int milis = 2000;//Bekleme için 2 saniye değişkeni tanımlanır
        JavascriptExecutor jsx = (JavascriptExecutor) cdriver;//Sayfada scroll yapabilmek için yazdık

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/checkbox");

        WebElement checkBoxHome = cdriver.findElement(By.xpath("//span[contains(text(),'Home')]"));

        boolean isSelectedHome = checkBoxHome.isSelected();
        if(isSelectedHome == false) {
            checkBoxHome.click();
        }
        Thread.sleep(milis);

        List<WebElement> resultsHome = cdriver.findElements(By.xpath("//span[@class='text-success']"));

        for(WebElement result : resultsHome) {
            System.out.println(result.getText());
        }
        System.out.println("=============");

        if(isSelectedHome == false) {
            checkBoxHome.click();
        }
        Thread.sleep(milis);

        WebElement toggleButton1 = cdriver.findElement(By.xpath("//button[@title='Toggle']"));

        toggleButton1.click();
        Thread.sleep(milis);

        WebElement checkBoxDesktop = cdriver.findElement(By.xpath("//span[contains(text(),'Desktop')]"));
        WebElement checkBoxDocuments = cdriver.findElement(By.xpath("//span[contains(text(),'Documents')]"));
        WebElement checkBoxDownloads= cdriver.findElement(By.xpath("//span[contains(text(),'Downloads')]"));

        Assert.assertTrue(checkBoxDesktop.isDisplayed());
        Assert.assertTrue(checkBoxDocuments.isDisplayed());
        Assert.assertTrue(checkBoxDownloads.isDisplayed());

        boolean isSelectedDesktop = checkBoxDesktop.isSelected();
        if(isSelectedDesktop == false) {
            checkBoxDesktop.click();
        }
        Thread.sleep(milis);

        List<WebElement> resultsDesktop = cdriver.findElements(By.xpath("//span[@class='text-success']"));

        for(WebElement result : resultsDesktop) {
            System.out.println(result.getText());
        }
        System.out.println("=============");

        if(isSelectedDesktop == false) {
            checkBoxDesktop.click();
        }
        Thread.sleep(milis);

        cdriver.quit();

    }
}
