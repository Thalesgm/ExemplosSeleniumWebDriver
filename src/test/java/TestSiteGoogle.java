
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestSiteGoogle {
    private WebDriver driver;

    //TODO MUDAR AQUI PARA APONTAR PARA O DRIVER QUE VC BAIXAR NA SUA MAQUINA
    //LEMBRAR QUE NO WINDOWS A BARRA É PARA O LADO OPOSTO. E EM JAVA PRECISAMOS
    //COLOCAR DUAS BARRAS NO LUGAR DE UMA - EX: C:\\TESTES2
    private String driverPath = "E:\\UFRN\\2020.2\\TEstes2\\ExemplosSeleniumWebDriver01\\chromedriver.exe";
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.out.println("Iniciando o browser Chrome");
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com.br/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testSiteGoogle() throws Exception {
        driver.get(baseUrl);
        //driver.findElement(By.id("input")).clear();
        driver.findElement(By.id("input")).sendKeys("portal inova");

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }


}
