import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    public void testCadastrarUsuarioPF() throws IOException, ParseException {
        FileReader reader = new FileReader("userPF.json");
        Object obj = jsonParser.parse(reader);
        JSONObject userPF = (JSONObject) obj;
        driver.findElement(By.id("sign-up-username")).sendKeys((CharSequence) userPF.get("email"));
        driver.findElement(By.id("verify-email-button")).click();
        driver.findElement(By.id("person-name")).sendKeys((CharSequence) userPF.get("name"));
        driver.findElement(By.id("person-last-name")).sendKeys((CharSequence) userPF.get("lastName"));

        //Alternando entre os generos
        driver.findElement(By.id("label-male")).click();
        driver.findElement(By.id("label-female")).click();
        driver.findElement(By.id("label-male")).click();

        //Data de Nascimento
        WebElement day = driver.findElement(By.id("dateofbirth-day"));
        new Select(day).selectByVisibleText((String) userPF.get("dateofbirth-day"));
        WebElement month = driver.findElement(By.id("dateofbirth-month"));
        new Select(month).selectByVisibleText((String) userPF.get("dateofbirth-month"));
        WebElement year = driver.findElement(By.id("dateofbirth-year"));
        new Select(year).selectByVisibleText((String) userPF.get("dateofbirth-year"));

        driver.findElement(By.id("cpf")).sendKeys((CharSequence) userPF.get("cpf"));

        //Dados Referentes ao Endereço
        driver.findElement(By.id("address-zipcode")).sendKeys((CharSequence) userPF.get("address-zipcode"));
        driver.findElement(By.id("address-number")).sendKeys((CharSequence) userPF.get("address-number"));
        driver.findElement(By.id("address-additional-info")).sendKeys((CharSequence) userPF.get("address-additional-info"));
        driver.findElement(By.id("reference")).sendKeys((CharSequence) userPF.get("address-reference"));
        driver.findElement(By.id("phones-home")).sendKeys((CharSequence) userPF.get("phones-home"));
        driver.findElement(By.id("phones-mobile")).sendKeys((CharSequence) userPF.get("phones-mobile"));
        driver.findElement(By.id("password")).sendKeys((CharSequence) userPF.get("password"));
        driver.findElement(By.id("check-sms")).click();
        //driver.findElement(By.id("save-register-natural"))/click();
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
