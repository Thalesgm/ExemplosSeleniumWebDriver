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

import static org.junit.Assert.assertEquals;
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

    @After
    public void tearDown() throws Exception {
        //driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
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
        driver.findElement(By.id("reference")).sendKeys((CharSequence) userPF.get("reference"));
        driver.findElement(By.id("phones-home")).sendKeys((CharSequence) userPF.get("phones-home"));
        driver.findElement(By.id("phones-mobile")).sendKeys((CharSequence) userPF.get("phones-mobile"));
        driver.findElement(By.id("password")).sendKeys((CharSequence) userPF.get("password"));
        driver.findElement(By.id("check-sms")).click();
        //driver.findElement(By.id("save-register-natural")).click();

        String actual = driver.findElement(By.id("email")).getAttribute("value");
        assertEquals(userPF.get("email"), actual);
    }

    @Test
    public void testCadastrarUsuarioPJ() throws IOException, ParseException {
        FileReader reader = new FileReader("userPJ.json");
        Object obj = jsonParser.parse(reader);
        JSONObject userPJ = (JSONObject) obj;
        //Acessando o Cadastro
        driver.findElement(By.id("sign-up-username")).sendKeys((CharSequence) userPJ.get("email"));
        driver.findElement(By.id("verify-email-button")).click();

        //Alterando para a aba de Pessoa Juridica
        driver.findElement(By.id("legal-tab")).click();

        //Inserindo Dados da empresa
        driver.findElement(By.id("person-company-name")).sendKeys((CharSequence) userPJ.get("name"));
        driver.findElement(By.id("person-trading-name")).sendKeys((CharSequence) userPJ.get("trading-name"));
        driver.findElement(By.id("person-cnpj")).sendKeys((CharSequence) userPJ.get("cnpj"));
        driver.findElement(By.id("person-municipal-inscription")).sendKeys((CharSequence) userPJ.get("municipal-inscription"));
        driver.findElement(By.id("person-state-inscription")).sendKeys((CharSequence) userPJ.get("state-inscription"));
        driver.findElement(By.id("exempt")).click();
        driver.findElement(By.id("address-zipcode")).sendKeys((CharSequence) userPJ.get("address-zipcode"));
        WebElement tipoRua = driver.findElement(By.id("address-street-type"));
        new Select(tipoRua).selectByVisibleText((String) userPJ.get("address-street-type"));
        driver.findElement(By.id("address-name")).sendKeys((CharSequence) userPJ.get("address-name"));
        driver.findElement(By.id("address-number")).sendKeys((CharSequence) userPJ.get("address-number"));
        driver.findElement(By.id("neighborhood")).sendKeys((CharSequence) userPJ.get("neighborhood"));
        driver.findElement(By.id("address-additional-info")).sendKeys((CharSequence) userPJ.get("address-additional-info"));
        WebElement estado = driver.findElement(By.id("address-state"));
        new Select(estado).selectByVisibleText((String) userPJ.get("address-state"));
        driver.findElement(By.id("city")).sendKeys((CharSequence) userPJ.get("city"));
        driver.findElement(By.id("reference")).sendKeys((CharSequence) userPJ.get("reference"));
        driver.findElement(By.id("phones-home")).sendKeys((CharSequence) userPJ.get("phones-home"));
        driver.findElement(By.id("phones-mobile")).sendKeys((CharSequence) userPJ.get("phones-mobile"));
        driver.findElement(By.id("password")).sendKeys((CharSequence) userPJ.get("password"));
        driver.findElement(By.id("check-sms")).click();

        //driver.findElement(By.id("save-register-natural")).click();

        String actual = driver.findElement(By.id("email")).getAttribute("value");
        assertEquals(userPJ.get("email"), actual);
    }


}
