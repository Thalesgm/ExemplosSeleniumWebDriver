import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class TesteCadastrarUsuario {
    private WebDriver driver;
    private String driverPath = "chromedriver.exe";

    private String baseUrl;
    private  boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private JSONParser jsonParser = new JSONParser();

    @Before
    public void setUp() throws Exception {
        System.out.println("Iniciando o browser Chrome");
        System.setProperty("webdriver.chrome.driver", driverPath);
        //Cofigurando Entrada Manual
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        baseUrl = "https://www.netshoes.com.br/login";
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @Test
    public void testCadastrarUsuarioPF(){
        driver.findElement(By.id("sign-up-username")).sendKeys("testecadastrarpf@teste.com");
        driver.findElement(By.id("verify-email-button")).click();
        driver.findElement(By.id("person-name")).sendKeys("Nome");
        driver.findElement(By.id("person-last-name")).sendKeys("Sobrenome");

        //Alternando entre os generos
        driver.findElement(By.id("label-female")).click();
        driver.findElement(By.id("label-male")).click();
        driver.findElement(By.id("label-female")).click();

        //Data de Nascimento
        WebElement day = driver.findElement(By.id("dateofbirth-day"));
        new Select(day).selectByVisibleText("25");
    }

    @Test
    public void testCadastrarUsuarioPJ(){
        driver.findElement(By.id("sign-up-username")).sendKeys("testecadastrarpj@teste.com");
        driver.findElement(By.id("verify-email-button")).click();
        driver.findElement(By.id("legal-tab")).click();
    }

    @After
    public void tearDown() throws Exception {
        //driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
