package elementsExamplesTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.SocketException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestWebTables {
    static WebDriver cdriver;
    static JavascriptExecutor jsx;

    static int milis = 1000;//Bekleme için 1 saniye değişkeni tanımlanır

    //Tabloya eklenecek veriler
    static String firstNameAdd = "John";
    static String lastNameAdd = "Cane";
    static String ageAdd = "25";
    static String emailAdd = "john@example.com";
    static String salaryAdd = "10500";
    static String deparmentAdd = "Engineering";

    //Tablodaki mevcut veriler
    static WebElement firstNameLabelFirstRow;
    static WebElement lastNameLabelFirstRow;
    static WebElement ageLabelFirstRow;
    static WebElement emailLabelFirstRow;
    static WebElement salaryLabelFirstRow;
    static WebElement departmentLabelFirstRow;

    static List<WebElement> firstRows;

    static WebElement addButton;

    //Veri ekleme sayfasındaki elementlerin locator'ları
    static WebElement firstNameRegForm;
    static WebElement lastNameRegForm;
    static WebElement ageRegForm;
    static WebElement emailRegForm;
    static WebElement salaryRegForm;
    static WebElement deparmentRegForm;

    static WebElement submitButton;

    //Tabloya eklenen verilerin locator'ları
    static WebElement firstNameLabelLastRow;
    static WebElement lastNameLabelLastRow;
    static WebElement ageLabelLastRow;
    static WebElement emailLabelLastRow;
    static WebElement salaryLabelLastRow;
    static WebElement departmentLabelLastRow;

    static List<WebElement> lastRows;

    static WebElement editButton4;
    static WebElement registrationModal;
    static WebElement exitButton;

    static WebElement deleteButton4;

    static  WebElement searchBox;

    static WebElement firstRowSearching;

    static WebElement noRowsLabel;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();//Chrome browser kurulumu
        cdriver = new ChromeDriver();//Chrome driver çalıştırmak için
        jsx = (JavascriptExecutor) cdriver;

        cdriver.manage().window().maximize();//Browser Sayfasını büyütmek için
        cdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Hata fırlatmadan önce bekleme süresi

        cdriver.get("https://demoqa.com/webtables");
    }
    @AfterClass
    public static void tearDown() throws SocketException, InterruptedException {
        cdriver.quit();
    }
    @Test
    public void test01ListAndSoutFirstRow() throws InterruptedException {
        Thread.sleep(milis);
        firstRows = cdriver.findElements(By.xpath("(//div[@role='rowgroup'])[1]"));//Tablodaki ilk satırı listeliyoruz
        //Tablodaki ilk satırdaki verileri yazdırıyoruz
        for (WebElement firstRow : firstRows) {
            System.out.println("İlk Satırdaki Veriler : "+firstRow.getText());
        }
        System.out.println("==============================");
    }
    @Test
    public void test02Asserts() throws InterruptedException {
        Thread.sleep(milis);
        firstNameLabelFirstRow = cdriver.findElement(By.xpath("//div[contains(text(),'Cierra')]"));
        lastNameLabelFirstRow = cdriver.findElement(By.xpath("//div[contains(text(),'Vega')]"));
        ageLabelFirstRow = cdriver.findElement(By.xpath("//div[contains(text(),'39')]"));
        emailLabelFirstRow = cdriver.findElement(By.xpath("//div[contains(text(),'cierra@example.com')]"));
        salaryLabelFirstRow = cdriver.findElement(By.xpath("//div[contains(text(),'10000')]"));
        departmentLabelFirstRow = cdriver.findElement(By.xpath("//div[contains(text(),'Insurance')]"));
        //Tablodaki ilk satırdaki veriler görünür mü? doğruluyoruz
        Assert.assertTrue(firstNameLabelFirstRow.isDisplayed());
        Assert.assertTrue(lastNameLabelFirstRow.isDisplayed());
        Assert.assertTrue(ageLabelFirstRow.isDisplayed());
        Assert.assertTrue(emailLabelFirstRow.isDisplayed());
        Assert.assertTrue(salaryLabelFirstRow.isDisplayed());
        Assert.assertTrue(departmentLabelFirstRow.isDisplayed());
    }
    @Test
    public void test03AddButton() throws InterruptedException {
        Thread.sleep(milis);
        addButton = cdriver.findElement(By.id("addNewRecordButton"));
        //Veri ekleme butonuna tıkla
        addButton.click();
    }
    @Test
    public void test04SendDataToRegForm() throws InterruptedException {
        Thread.sleep(milis);
        firstNameRegForm = cdriver.findElement(By.id("firstName"));
        lastNameRegForm = cdriver.findElement(By.id("lastName"));
        ageRegForm = cdriver.findElement(By.id("age"));
        emailRegForm = cdriver.findElement(By.id("userEmail"));
        salaryRegForm = cdriver.findElement(By.id("salary"));
        deparmentRegForm = cdriver.findElement(By.id("department"));
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
    }
    @Test
    public void test05ClickSubmitButton() throws InterruptedException {
        Thread.sleep(milis);
        //Submit butonu
        submitButton = cdriver.findElement(By.id("submit"));

        //Submit butonu tıklama
        submitButton.click();
        Thread.sleep(milis);
        jsx.executeScript("window.scrollBy(0,250)","");//Sayfa aşağı kaydırılır
    }
    @Test
    public void test06ListAndSoutLastRow() throws InterruptedException {
        Thread.sleep(milis);
        //Tablodaki son satırı listeliyoruz
        lastRows = cdriver.findElements(By.xpath("(//div[@role='rowgroup'])[4]"));

        //Tablodaki yeni eklediğimiz satırdaki(son) verileri yazdırıyoruz
        for (WebElement lastRow : lastRows) {
            System.out.println("Son Satırdaki Yeni Eklenen Veriler : "+lastRow.getText());
        }
        System.out.println("==============================");
    }
    @Test
    public void test07Asserts() throws InterruptedException {
        Thread.sleep(milis);
        firstNameLabelLastRow = cdriver.findElement(By.xpath("//div[contains(text(),'John')]"));
        lastNameLabelLastRow = cdriver.findElement(By.xpath("//div[contains(text(),'Cane')]"));
        ageLabelLastRow = cdriver.findElement(By.xpath("//div[contains(text(),'25')]"));
        emailLabelLastRow = cdriver.findElement(By.xpath("//div[contains(text(),'john@example.com')]"));
        salaryLabelLastRow = cdriver.findElement(By.xpath("//div[contains(text(),'10500')]"));
        departmentLabelLastRow = cdriver.findElement(By.xpath("//div[contains(text(),'Engineering')]"));
        //Tablodaki son satırdaki veriler görünür mü? doğruluyoruz
        Assert.assertTrue(firstNameLabelLastRow.isDisplayed());
        Assert.assertTrue(lastNameLabelLastRow.isDisplayed());
        Assert.assertTrue(ageLabelLastRow.isDisplayed());
        Assert.assertTrue(emailLabelLastRow.isDisplayed());
        Assert.assertTrue(salaryLabelLastRow.isDisplayed());
        Assert.assertTrue(departmentLabelLastRow.isDisplayed());
    }
    @Test
    public void test08EditButton() throws InterruptedException {
        Thread.sleep(milis);
        //Edit buton görselini tanımladık
        editButton4 = cdriver.findElement(By.id("edit-record-4"));

        //Edit butona tıklıyoruz
        editButton4.click();
    }
    @Test
    public void test09ModalOpen() throws InterruptedException {
        Thread.sleep(milis);
        //Açılan modalı tanımlıyoruz
        registrationModal = cdriver.findElement(By.xpath("//div[@class='modal-body']"));

        //Modal açıldı mı? doğruluyoruz
        Assert.assertTrue(registrationModal.isDisplayed());
    }
    @Test
    public void test10ModalClose() throws InterruptedException {
        Thread.sleep(milis);
        //Exit butonu tanımladık
        exitButton = cdriver.findElement(By.xpath("//span[contains(text(),'×')]"));

        //X tıklıyoruz. Modal kapanacak
        exitButton.click();
    }
    @Test
    public void test11DeleteButton() throws InterruptedException {
        Thread.sleep(milis);
        //Silme buton görselini tanımlıyoruz
        deleteButton4 = cdriver.findElement(By.id("delete-record-4"));

        //Silme butonuna tıklıyoruz
        deleteButton4.click();
        Thread.sleep(milis);
    }
    @Test
    public void test12CheckDeletedItems() throws InterruptedException {
        Thread.sleep(milis);
        //En son eklediğimiz son satırdaki veriler görünür mü? doğruluyruz. Silinmiş olmalı
        //Bu kısımda element silindiği için bulunamıyor ve hata fırlatıyor biz de bu hatayı kullanarak verinin silindiğini anlıyoruz.
        try{
            Assert.assertFalse(firstNameLabelLastRow.isDisplayed());
        }
        catch (StaleElementReferenceException e){
            System.out.println("Veri Başarılı Bir Şekilde Silinmiştir. Tabloda Veri Yoktur.");
        }
    }
    @Test
    public void test13SearchBoxValidData() throws InterruptedException {
        Thread.sleep(milis);
        //Arama kısmını tanımlıyoruz
        searchBox = cdriver.findElement(By.id("searchBox"));

        //Arama kısmına olan bir verinin kısaltmasını yazıyoruz
        searchBox.sendKeys("ald");
        Thread.sleep(milis);

        //Arama sonrası ilk satırı tanımlıyoruz
        firstRowSearching = cdriver.findElement(By.xpath("//div[contains(text(),'Alden')]"));

        //Yazdığımız veri çıktımı doğruluyoruz
        Assert.assertTrue(firstRowSearching.isDisplayed());
    }
    @Test
    public void test14SearchBoxInvalidData() throws InterruptedException {
        Thread.sleep(milis);
        //Arama yerine sildiğimiz veriyi yazıyoruz
        searchBox.sendKeys("john");

        //Satır bulunamadı yazısını tanımlıyoruz
        noRowsLabel = cdriver.findElement(By.xpath("//*[contains(text(),'No rows found')]"));

        //Satır yok yazısı gözüküyor mu? doğruluyoruz
        Assert.assertTrue(noRowsLabel.isDisplayed());
    }
}
