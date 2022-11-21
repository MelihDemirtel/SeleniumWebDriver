package elementsExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class RadioButton {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        WebDriver cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        int milis = 2000;//Bekleme için 2 saniye değişkeni tanımlanır
        JavascriptExecutor jsx = (JavascriptExecutor) cdriver;//Sayfada scroll yapabilmek için yazdık

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/radio-button");

        WebElement radioButtonYes = cdriver.findElement(By.xpath("//label[@for='yesRadio']"));
        WebElement radioButtonImpressive = cdriver.findElement(By.xpath("//label[@for='impressiveRadio']"));
        WebElement radioButtonNo = cdriver.findElement(By.xpath("//label[@for='noRadio']"));

        boolean isSelectedYes = radioButtonYes.isSelected();
        if(isSelectedYes == false) {
            radioButtonYes.click();
            WebElement displayYes = cdriver.findElement(By.xpath("//span[contains(text(),'Yes')]"));
            Assert.assertTrue(displayYes.isDisplayed());
            System.out.println("Yes Seçildi");
        }
        Thread.sleep(milis);

        boolean isSelectedImpressive = radioButtonImpressive.isSelected();
        if(isSelectedImpressive == false) {
            radioButtonImpressive.click();
            WebElement displayImpressive = cdriver.findElement(By.xpath("//span[contains(text(),'Impressive')]"));
            Assert.assertTrue(displayImpressive.isDisplayed());
            System.out.println("Impressive Seçildi");
        }
        Thread.sleep(milis);

        boolean isClickableNo = radioButtonNo.isEnabled();
        if(isClickableNo == true){
            System.out.println("No Seçilemez");
        }
        Thread.sleep(milis);

        cdriver.quit();

    }
}
