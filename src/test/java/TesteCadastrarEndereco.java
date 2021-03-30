import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TesteCadastrarEndereco{
    private WebDriver driver;

    private String driverPath = "chromedriver.exe";
    private String baseUrl;
    private  boolean acceptNextAlert = true;

    //Realizando Autenticação
    private StringBuffer verificationErrors = new StringBuffer();
    private JSONParser jsonParser = new JSONParser();
    private FileReader reader = new FileReader("user.json");
    Object obj = jsonParser.parse(reader);
    JSONObject user = (JSONObject) obj;
    public TesteCadastrarEndereco() throws IOException, ParseException {
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("Iniciando o browser Chrome");
        System.setProperty("webdriver.chrome.driver", driverPath);
        //Cofigurando Entrada Manual
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        baseUrl = "https://www.netshoes.com.br/new-account/my-addresses";
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.get(baseUrl);
        //Campo de Login
        driver.findElement(By.id("username")).sendKeys((CharSequence) user.get("email"));;
        //Campo de  Senha
        driver.findElement(By.id("password")).sendKeys((CharSequence) user.get("password"));
        //Botão de Login
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void testEndereco() throws Exception {
        reader = new FileReader("addressData.json");
        obj = jsonParser.parse(reader);
        JSONObject addressData = (JSONObject) obj;
        //Inserir Dados e Logar
        WebElement title = driver.findElement(By.className("title"));
        driver.findElement(By.xpath("//button[contains(text(),'Adicionar novo endereço')]")).click();
        driver.findElement(By.id("name")).sendKeys((CharSequence) addressData.get("name"));
        driver.findElement(By.id("recipientName")).sendKeys((CharSequence) addressData.get("recipientName"));
        driver.findElement(By.id("zipCode")).sendKeys((CharSequence) addressData.get("zipCode"));
        driver.findElement(By.id("streetNumber")).sendKeys((CharSequence) addressData.get("streetNumber"));
        driver.findElement(By.id("additionalInfo")).sendKeys((CharSequence) addressData.get("additionalInfo"));
        driver.findElement(By.id("reference")).sendKeys((CharSequence) addressData.get("reference"));
        //driver.findElement(By.xpath("//button[contains(text(),'Salvar Informações')]")).click();

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