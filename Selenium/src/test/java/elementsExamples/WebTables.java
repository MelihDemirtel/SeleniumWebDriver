package elementsExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebTables {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        WebDriver cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        int milis = 1000;//Bekleme için 1 saniye değişkeni tanımlanır
        JavascriptExecutor jsx = (JavascriptExecutor) cdriver;//Sayfada scroll yapabilmek için yazdık

        //Tabloya eklenecek veriler
        String firstNameAdd = "John";
        String lastNameAdd = "Cane";
        String ageAdd = "25";
        String emailAdd = "john@example.com";
        String salaryAdd = "10500";
        String deparmentAdd = "Engineering";


        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/webtables");

        //Tablodaki mevcut veriler
        WebElement firstNameLabelFirstRow = cdriver.findElement(By.xpath("//div[contains(text(),'Cierra')]"));
        WebElement lastNameLabelFirstRow = cdriver.findElement(By.xpath("//div[contains(text(),'Vega')]"));
        WebElement ageLabelFirstRow = cdriver.findElement(By.xpath("//div[contains(text(),'39')]"));
        WebElement emailLabelFirstRow = cdriver.findElement(By.xpath("//div[contains(text(),'cierra@example.com')]"));
        WebElement salaryLabelFirstRow = cdriver.findElement(By.xpath("//div[contains(text(),'10000')]"));
        WebElement departmentLabelFirstRow = cdriver.findElement(By.xpath("//div[contains(text(),'Insurance')]"));

        List<WebElement> firstRows = cdriver.findElements(By.xpath("(//div[@role='rowgroup'])[1]"));//Tablodaki ilk satırı listeliyoruz

        //Tablodaki ilk satırdaki verileri yazdırıyoruz
        for (WebElement firstRow : firstRows) {
            System.out.println("İlk Satırdaki Veriler : "+firstRow.getText());
        }
        System.out.println("==============================");
        Thread.sleep(milis);

        //Tablodaki ilk satırdaki veriler görünür mü? doğruluyoruz
        Assert.assertTrue(firstNameLabelFirstRow.isDisplayed());
        Assert.assertTrue(lastNameLabelFirstRow.isDisplayed());
        Assert.assertTrue(ageLabelFirstRow.isDisplayed());
        Assert.assertTrue(emailLabelFirstRow.isDisplayed());
        Assert.assertTrue(salaryLabelFirstRow.isDisplayed());
        Assert.assertTrue(departmentLabelFirstRow.isDisplayed());
        Thread.sleep(milis);

        //Yeni veri ekleme butonu
        WebElement addButton = cdriver.findElement(By.id("addNewRecordButton"));
        Thread.sleep(milis);

        //Veri ekleme butonuna tıkla
        addButton.click();
        Thread.sleep(milis);

        //Veri ekleme sayfasındaki elementlerin locator'ları
        WebElement firstNameRegForm = cdriver.findElement(By.id("firstName"));
        WebElement lastNameRegForm = cdriver.findElement(By.id("lastName"));
        WebElement ageRegForm = cdriver.findElement(By.id("age"));
        WebElement emailRegForm = cdriver.findElement(By.id("userEmail"));
        WebElement salaryRegForm = cdriver.findElement(By.id("salary"));
        WebElement deparmentRegForm = cdriver.findElement(By.id("department"));

        //Veri ekleme sayfasına verileri gönderiyoruz
        firstNameRegForm.sendKeys(firstNameAdd);
        Thread.sleep(milis);
        lastNameRegForm.sendKeys(lastNameAdd);
        Thread.sleep(milis);
        ageRegForm.sendKeys(ageAdd);
        Thread.sleep(milis);
        emailRegForm.sendKeys(emailAdd);
        Thread.sleep(milis);
        salaryRegForm.sendKeys(salaryAdd);
        Thread.sleep(milis);
        deparmentRegForm.sendKeys(deparmentAdd);
        Thread.sleep(milis);

        //Submit butonu
        WebElement submitButton = cdriver.findElement(By.id("submit"));

        //Submit butonu tıklama
        submitButton.click();
        Thread.sleep(milis);
        jsx.executeScript("window.scrollBy(0,250)","");//Sayfa aşağı kaydırılır
        Thread.sleep(milis);

        //Tabloya eklenen verilerin locator'ları
        WebElement firstNameLabelLastRow = cdriver.findElement(By.xpath("//div[contains(text(),'John')]"));
        WebElement lastNameLabelLastRow = cdriver.findElement(By.xpath("//div[contains(text(),'Cane')]"));
        WebElement ageLabelLastRow = cdriver.findElement(By.xpath("//div[contains(text(),'25')]"));
        WebElement emailLabelLastRow = cdriver.findElement(By.xpath("//div[contains(text(),'john@example.com')]"));
        WebElement salaryLabelLastRow = cdriver.findElement(By.xpath("//div[contains(text(),'10500')]"));
        WebElement departmentLabelLastRow = cdriver.findElement(By.xpath("//div[contains(text(),'Engineering')]"));

        //Tablodaki son satırı listeliyoruz
        List<WebElement> lastRows = cdriver.findElements(By.xpath("(//div[@role='rowgroup'])[4]"));

        //Tablodaki yeni eklediğimiz satırdaki(son) verileri yazdırıyoruz
        for (WebElement lastRow : lastRows) {
            System.out.println("Son Satırdaki Yeni Eklenen Veriler : "+lastRow.getText());
        }
        System.out.println("==============================");
        Thread.sleep(milis);

        //Tablodaki son satırdaki veriler görünür mü? doğruluyoruz
        Assert.assertTrue(firstNameLabelLastRow.isDisplayed());
        Assert.assertTrue(lastNameLabelLastRow.isDisplayed());
        Assert.assertTrue(ageLabelLastRow.isDisplayed());
        Assert.assertTrue(emailLabelLastRow.isDisplayed());
        Assert.assertTrue(salaryLabelLastRow.isDisplayed());
        Assert.assertTrue(departmentLabelLastRow.isDisplayed());
        Thread.sleep(milis);

        //Edit buton görselini tanımladık
        WebElement editButton4 = cdriver.findElement(By.id("edit-record-4"));

        //Edit butona tıklıyoruz
        editButton4.click();
        Thread.sleep(milis);

        //Açılan modalı tanımlıyoruz
        WebElement registrationModal = cdriver.findElement(By.xpath("//div[@class='modal-body']"));

        //Modal açıldı mı? doğruluyoruz
        Assert.assertTrue(registrationModal.isDisplayed());
        Thread.sleep(milis);

        //Exit butonu tanımladık
        WebElement exitButton = cdriver.findElement(By.xpath("//span[contains(text(),'×')]"));

        //X tıklıyoruz. Modal kapanacak
        exitButton.click();
        Thread.sleep(milis);


        //Silme buton görselini tanımlıyoruz
        WebElement deleteButton4 = cdriver.findElement(By.id("delete-record-4"));

        //Silme butonuna tıklıyoruz
        deleteButton4.click();
        Thread.sleep(milis);

         //En son eklediğimiz son satırdaki veriler görünür mü? doğruluyruz. Silinmiş olmalı
        //Bu kısımda element silindiği için bulunamıyor ve hata fırlatıyor biz de bu hatayı kullanarak verinin silindiğini anlıyoruz.
         try{
             Assert.assertFalse(firstNameLabelLastRow.isDisplayed());
         }
         catch (StaleElementReferenceException e){
             System.out.println("Veri Başarılı Bir Şekilde Silinmiştir. Tabloda Veri Yoktur.");
         }
         Thread.sleep(milis);

         //Arama kısmını tanımlıyoruz
         WebElement searchBox = cdriver.findElement(By.id("searchBox"));

         //Arama kısmına olan bir verinin kısaltmasını yazıyoruz
         searchBox.sendKeys("ald");
         Thread.sleep(milis);

         //Arama sonrası ilk satırı tanımlıyoruz
         WebElement firstRowSearching = cdriver.findElement(By.xpath("//div[contains(text(),'Alden')]"));

         //Yazdığımız veri çıktımı doğruluyoruz
         Assert.assertTrue(firstRowSearching.isDisplayed());
         Thread.sleep(milis);

         //Arama yerine sildiğimiz veriyi yazıyoruz
         searchBox.sendKeys("john");

         //Satır bulunamadı yazısını tanımlıyoruz
         WebElement noRowsLabel = cdriver.findElement(By.xpath("//*[contains(text(),'No rows found')]"));

         //Satır yok yazısı gözüküyor mu? doğruluyoruz
         Assert.assertTrue(noRowsLabel.isDisplayed());
         Thread.sleep(milis);

         //Browserı kapatıyoruz
         cdriver.quit();


    }
}
