package selenium.test.formfiller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class FormfillerTest {

    private WebDriver webDriver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        webDriver = new ChromeDriver();
    }

    @After
    public void tearDown() throws Exception {
        webDriver.close();
    }

    @Test
    public void resourceIsAvailable() {
        webDriver.get("https://www.deutsche-bank.de/opra/pbc/internet/process/internet_onlinekredit/start.do?restartApplication=true");
        assertEquals("Deutsche Bank - Eröffnungsantrag PrivatKredit direkt - Ihr Kreditwunsch", webDriver.getTitle());
    }

    @Test
    public void fillTheForm() {
        webDriver.get("https://www.deutsche-bank.de/opra/pbc/internet/process/internet_onlinekredit/start.do?restartApplication=true");
        webDriver.findElement(By.id("text-creditRequest_creditRequest")).sendKeys("2000");
        webDriver.findElement(By.id("select-creditRequest_duration")).sendKeys("24");
        webDriver.findElement(By.id("select-creditRequest_paymentDay")).sendKeys("15. des Monats");
        webDriver.findElement(By.id("label_radio-first_customerDetails_customerStateString-2")).click();
        webDriver.findElement(By.id("text-first_customerDetails_branchId")).sendKeys("123");
        webDriver.findElement(By.id("text-first_customerDetails_id")).sendKeys("1234567890");
        webDriver.findElement(By.id("text-first_customerDetails_bankIdentificationCode")).sendKeys("123456781234567");
        webDriver.findElement(By.id("button_next")).click();
        WebElement filialnummerError = webDriver.findElement(By.id("div-first_customerDetails_branchId")).findElement(By.className("error"));
        assertEquals("Bitte überprüfen Sie die Angabe 'Filialnummer'.", filialnummerError.getText());
        WebElement kundennummerError = webDriver.findElement(By.id("div-first_customerDetails_id")).findElement(By.className("error"));
        assertEquals("Bitte überprüfen Sie die Angabe 'Kundennummer'.", kundennummerError.getText());
        WebElement bankleitzahlError = webDriver.findElement(By.id("div-first_customerDetails_bankIdentificationCode")).findElement(By.className("error"));
        assertEquals("Gemäß der von Ihnen angegebenen Bankleitzahl sind Sie noch kein Kunde der Deutschen Bank " +
                        "Privat- und Geschäftskunden AG oder der Deutsche Bank AG. Bitte geben Sie die Bankleitzahl " +
                        "Ihrer Deutsche Bank AG oder Deutsche Bank Privat- und Geschäftskunden AG ein.",
                bankleitzahlError.getText());


    }

}