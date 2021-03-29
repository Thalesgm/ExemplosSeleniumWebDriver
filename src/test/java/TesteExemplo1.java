import static org.junit.Assert.*;

import org.junit.AfterClass;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


//https://www.seleniumhq.org/docs/03_webdriver.jsp
public class TesteExemplo1 {


    //TODO MUDAR AQUI PARA APONTAR PARA O DRIVER QUE VC BAIXAR NA SUA MAQUINA
    //LEMBRAR QUE NO WINDOWS A BARRA É PARA O LADO OPOSTO. E EM JAVA PRECISAMOS
    //COLOCAR DUAS BARRAS NO LUGAR DE UMA - EX: C:\\TESTES2
    private String driverPath = "E:\\UFRN\\2020.2\\TEstes2\\ExemplosSeleniumWebDriver01\\chromedriver.exe";
    private WebDriver driver;

    @Test
    public void testFire() {

        System.out.println("Iniciando o browser Chrome");
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get("http://www.google.com");

        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();
        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());


        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());

        //Close the browser
        //driver.quit();
    }


    @Test
    public void testGooglePageTitleInIEBrowser() {
        System.out.println("Iniciando o browser Chrome");
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get("http://www.google.com");

        String strPageTitle = driver.getTitle();
        System.out.println("Page title: - "+ strPageTitle);
        assertEquals(strPageTitle,"Google");
        //assertEquals(strPageTitle,"IMD");
    }


}
