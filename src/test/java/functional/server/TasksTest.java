package functional.server;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

@FixMethodOrder(MethodSorters.JVM)
public class TasksTest {


    private WebDriver navegador;
    private Object MalformedURLException;
    private String currentPassword = "admin";
    private WebDriverWait wait;
    private Dimension dimension;
    private String textElement;

    @Before
    public void Setup() throws java.net.MalformedURLException {
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        navegador = new RemoteWebDriver(new URL("http://172.17.2.201:4444/wd/hub"), cap);

        navegador.manage().window().maximize();
        Dimension dimension = new Dimension(1280, 800);
        navegador.manage().window().setSize(dimension);

        navegador.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        navegador.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        navegador.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        WebDriverWait wait = null;
    }


    @After
    public void tearDown() {
        navegador.quit();
    }

    @Test
    public void QA___D___MASTER_FULL() throws InterruptedException {

        navegador.get("http://qa-webapp.vati.rocks/");
        String actualTitle = navegador.getTitle();
        String expectedTitle = "jw2_web_2app";
        //Thread.sleep(500);

        navegador.findElement(xpath("//input[contains(@type,'email')]")).sendKeys("master@email.com");
        navegador.findElement(xpath("//input[contains(@type,'password')]")).sendKeys("master");
        //Thread.sleep(500);
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Login')]")).click();
        //wait = new WebDriverWait(navegador, 15);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='v-btn__content'][contains(.,'Logout')]")));

        Thread.sleep(1000);
        //navegador.navigate().refresh();

        ////////APPLICATIONS INSTANCES//////////
        System.out.println("MASTER");
        System.out.println("Adicionar Applications Instances");

        navegador.findElement(xpath("//div[@class='v-list-item__title'][contains(.,'Core Settings')]")).click();
        Thread.sleep(500);
        navegador.findElement(xpath("//div[@class='v-list-item__title'][contains(.,'Applications Instances')]")).click();
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Create App Instance')]")).click();
        Thread.sleep(1000);
        navegador.findElement(xpath("//span[text()=' Save ']")).click();
        navegador.findElement(xpath("(//label[text()='Title']/following::input)[1]")).sendKeys("Contents");
        navegador.findElement(xpath("(//label[text()='Url']/following::input)[1]")).sendKeys("http://vati.rocks");
        navegador.findElement(xpath("(//div[contains(@class,'v-select__selections')])[2]")).click();
        navegador.findElement(xpath("//div[text()='Core']")).click();
        navegador.findElement(xpath("//span[text()=' Save ']")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        Assert.assertEquals("ApplicationInstance created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(1000);
        System.out.println("<OK>");

        ////////TENANTS/////////

        System.out.println("Incluir Tenants");
        navegador.findElement(xpath("//div[@class='v-list-item__title'][contains(.,'Core Settings')]")).click();
        Thread.sleep(500);
        navegador.findElement(xpath("//div[@class='v-list-item__title'][contains(.,'Tenants')]")).click();
        Thread.sleep(1000);
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Create Tenant')]")).click();
        Thread.sleep(1000);
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();
        Thread.sleep(500);
        navegador.findElement(xpath("(//input[contains(@type,'text')])[3]")).sendKeys("Vati");
        Thread.sleep(500);
        navegador.findElement(xpath("//input[contains(@autofocus,'autofocus')]")).sendKeys("Vati");
        navegador.findElement(xpath("(//input[contains(@type,'text')])[4]")).sendKeys("10.111.000.0001-11");

        navegador.findElement(xpath("(//div[contains(@class,'v-select__selections')])[2]")).click();
        navegador.findElement(By.xpath("(//div[text()='Contents'])[2]")).click();
        //navegador.findElement(xpath("//div[@class='v-list-item__title'][contains(.,'Contents')]")).click();
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Tenant created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(1000);

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Vati']")));
        textElement = navegador.findElement(By.xpath("//td[text()='Vati']")).getText();
        assertEquals("Vati", textElement);
        System.out.println("<OK>");

        /////ADD NEW_USER_ADMIN_TENANTS

        System.out.println("Vincular usuário ao Tenant");
        navegador.findElement(By.xpath("(//button[contains(@class,'v-icon notranslate')])[4]")).click();
        navegador.findElement(By.xpath("//span[text()=' Create Users ']")).click();

        navegador.findElement(xpath("//textarea[contains(@autocomplete,'email')]")).sendKeys("senelinum@protonmail.com");
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        Assert.assertEquals("User(s) added", textElement);

        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(1000);

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='senelinum@protonmail.com']"))).click();
        textElement = navegador.findElement(By.xpath("//td[text()='senelinum@protonmail.com']")).getText();
        assertEquals("senelinum@protonmail.com", textElement);
        Thread.sleep(3000);
/*
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Create Users')]")).click();

        navegador.findElement(xpath("//textarea[contains(@autocomplete,'email')]")).sendKeys("senelinum2@protonmail.com");
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        //Assert.assertEquals("Access Denied.", textElement);
        Assert.assertEquals("Invalid privilege", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();

 */
        Thread.sleep(26000);
/*
        Thread.sleep(3000);

        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Create Users')]")).click();

        navegador.findElement(xpath("//textarea[contains(@autocomplete,'email')]")).sendKeys("senelinum2@protonmail.com");
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        //Assert.assertEquals("Access Denied.", textElement);
        Assert.assertEquals("Invalid privilege", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();

 */
        Thread.sleep(1000);
        System.out.println("<OK>");

        navegador.findElement(xpath("(//div[@class='v-responsive__content'])[2]")).click();
        Thread.sleep(1000);
        navegador.findElement(xpath("//div[text()='Logout']")).click();
    }


    @Test
    public void QA___E___ACESSAR_EMAIL() throws InterruptedException {
        //////_ACESSAR_EMAIL_RESGATAR_SENHA_DE_ACESSO////////

/*        System.out.println("Acessar email resgatar senha enviada");
        ((JavascriptExecutor) navegador).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(navegador.getWindowHandles());
        //navegador.switchTo().window(tabs.get(1));



        navegador.switchTo().window(tabs.get(1));
        */

        navegador.get("https://account.protonmail.com/login");

        navegador.findElement(By.xpath("//input[contains(@id,'username')]")).sendKeys("senelinum@protonmail.com");
        navegador.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys("83daca4b007");
        navegador.findElement(By.xpath("//button[@type='submit']")).click();
        //navegador.findElement(By.xpath("//button[contains(.,'Iniciar')]")).click();

        wait = new WebDriverWait(navegador, 10);
        navegador.findElement(By.xpath("(//span[contains(@class,'flex-item-fluid text-ellipsis')])[1]")).click();
        //navegador.findElement(By.xpath("//span[@class='flex-item-fluid text-ellipsis max-w100'][contains(.,'Caixa de entrada')]")).click();
        navegador.findElement(By.xpath("//span[@title='JAM 2 user invite']")).click();

        String text = navegador.findElement(By.xpath("//div[contains(text(),'password')]")).getText();
        System.out.println(text.substring(103, 108));
        String currentPassword = text.substring(103, 108);


        //navegador.switchTo().window(tabs.get(0));
        navegador.get("http://qa-webapp.vati.rocks/");

        System.out.println("<OK>");
        Thread.sleep(2000);
        System.out.println("Login com senha recebida");

        navegador.findElement(By.xpath("//input[contains(@type,'email')]")).sendKeys("senelinum@protonmail.com");
        navegador.findElement(By.xpath("//input[contains(@type,'password')]")).sendKeys(currentPassword);
        Thread.sleep(2000);

        navegador.findElement(By.xpath("//span[text()=' Login ']")).click();
        System.out.println("<OK>");
        Thread.sleep(1000);
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();
        //navegador.findElement(By.xpath("//span[text()='Save']")).click();

        System.out.println("Cadastrar senha errada, validacao campos");
        navegador.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys("1234");
        navegador.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("5678");
        navegador.findElement(By.xpath("(//span[contains(.,'Save')])[2]")).click();
        Thread.sleep(1000);
        System.out.println("<OK>");

        System.out.println("Cadastrar senha correta, 1234");
        navegador.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        //navegador.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys(Keys.chord(Keys.BACK_SPACE));
        navegador.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys("1234");

        navegador.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        //navegador.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys(Keys.chord(Keys.BACK_SPACE));
        navegador.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("1234");
        System.out.println("<OK>");

        Thread.sleep(1000);
        navegador.findElement(By.xpath("(//span[contains(.,'Save')])[2]")).click();
        Thread.sleep(3000);

        navegador.findElement(By.xpath("//span[text()='s']")).click();

        //navegador.findElement(xpath("((//span[@class='black--text headline'][contains(.,'s')])[1]")).click();
        Thread.sleep(1000);
        navegador.findElement(xpath("//div[text()='Logout']")).click();
        Thread.sleep(1000);

        System.out.println("Login com senha alterada");
        navegador.findElement(xpath("//input[contains(@type,'email')]")).sendKeys("senelinum@protonmail.com");
        navegador.findElement(xpath("//input[contains(@type,'password')]")).sendKeys("1234");
        Thread.sleep(1000);
        //navegador.navigate().refresh();
        System.out.println("<OK>");
        Thread.sleep(2000);

        System.out.println("Clicando em Core Settings, deverá informar os iténs |Tenants|Companies|Groups|Users ");
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Login')]")).click();
        Thread.sleep(1000);

        //System.out.println(navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Login')]")));
        //System.out.println(navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Core Settings')]")));
        Thread.sleep(1000);


        /////INSERT_USER_ADMIN_IN_TENANT_VATI

        Thread.sleep(2000);
        System.out.println("Adicionar usuário admin@email.com ao Tenant VATI");
        Thread.sleep(1000);

        WebDriverWait wait = new WebDriverWait(navegador, 10);
        WebElement element = wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[contains(@class,'v-icon notranslate')])[4]"))));

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate')])[4]")).click();

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate')])[4]")).click();
        //navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Core Settings')]")).click();
        //navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Core Settings')]")).click();
        //navegador.findElement(By.xpath("//div[text()='Core Settings']")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Tenants']"))).getText();
        textElement = navegador.findElement(By.xpath("//div[text()='Tenants']")).getText();
        assertEquals("Tenants", textElement);
        Thread.sleep(500);

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Companies']"))).getText();
        textElement = navegador.findElement(By.xpath("//div[text()='Companies']")).getText();
        assertEquals("Companies", textElement);
        Thread.sleep(500);

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Groups']"))).getText();
        textElement = navegador.findElement(By.xpath("//div[text()='Groups']")).getText();
        assertEquals("Groups", textElement);
        Thread.sleep(500);

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Users']"))).getText();
        textElement = navegador.findElement(By.xpath("//div[text()='Users']")).getText();
        assertEquals("Users", textElement);
        Thread.sleep(500);


        System.out.println("<OK>");
        Thread.sleep(1000);

        System.out.println("Logout Sistema");
        navegador.findElement(By.xpath("//span[text()='s']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[text()='Logout']")).click();
        navegador.quit();
    }


    @Test
    public void QA___F___ADMIN_FULL() throws InterruptedException {

        navegador.get("http://qa-webapp.vati.rocks/");
        String actualTitle = navegador.getTitle();
        String expectedTitle = "jw2_web_2app";
        //Thread.sleep(500);
        System.out.println("ADMIN");
        System.out.println("Acessar com privilégios ADMIN usuário senelinum@protonmail.com");

        navegador.findElement(xpath("//input[contains(@type,'email')]")).sendKeys("senelinum@protonmail.com");
        navegador.findElement(xpath("//input[contains(@type,'password')]")).sendKeys("1234");
        //Thread.sleep(500);
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Login')]")).click();
        System.out.println("<OK>");

        Thread.sleep(1000);


////////////////////COMPANIES-VATI-INATIVAR/////////////////////

        Thread.sleep(3000);
        System.out.println("Inativar Companies padrão do sistema");
        //navegador.findElement(By.xpath("(//div[contains(.,'Core Settings')])[6]"));

        WebDriverWait wait = new WebDriverWait(navegador, 10);
        WebElement element;
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-list-item__title'][contains(.,'Core Settings')]"))));
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Core Settings')]")).click();
        //navegador.findElement(By.xpath("//div[text()='Core Settings']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Companies')]")).click();
        navegador.findElement(By.xpath("//button[contains(@class,'v-icon notranslate')]")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[@class='v-input--selection-controls__ripple primary--text']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();
        Thread.sleep(1000);
        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Company edited", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(3000);
        System.out.println("<OK>");


///////////////////COMPANIE_BCA//////////////////////

        System.out.println("Cadastrar Companie BCA");
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Core Settings')]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Companies')]")).click();
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Create company')]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//input[contains(@autofocus,'autofocus')]")).sendKeys("BCA");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//input[contains(@type,'text')])[4]")).sendKeys("bca");
        navegador.findElement(By.xpath("(//input[contains(@required,'required')])[3]")).sendKeys("13392325000109");
        navegador.findElement(By.xpath("(//div[contains(@class,'v-select__selections')])[2]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Vati')]")).click();
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Company created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(1000);
        System.out.println("<OK>");

////////////////////COMPANIES-VL VATICANO////////////////////

        System.out.println("Cadastrar Companie VL VATICANO");
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Create company')]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//input[contains(@autofocus,'autofocus')]")).sendKeys("VL Vaticano");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//input[contains(@type,'text')])[4]")).sendKeys("vl_vaticano");
        navegador.findElement(By.xpath("(//input[contains(@required,'required')])[3]")).sendKeys("055316186000184");
        navegador.findElement(By.xpath("(//div[contains(@class,'v-select__selections')])[2]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Vati')]")).click();
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Company created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(3000);
        System.out.println("<OK>");


////////////////////CREATE NEW GROUPS_PRESIDENCIA////////////////////

        System.out.println("Cadastrar Grupo_Presidência | Tenant VATI");
        navegador.findElement(xpath("//div[@class='v-list-item__title'][contains(.,'Core Settings')]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Groups')]")).click();
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Create group')]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        navegador.findElement(By.xpath("//input[@autofocus='autofocus']")).sendKeys("Grupo_Presidência");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//input[contains(@type,'text')])[3]")).sendKeys("grupo_precidencia");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[1]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Vati')]")).click();

        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[2]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'BCA')]")).click();

        Thread.sleep(500);

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[3]")).click();
        navegador.findElement(By.xpath("(//div[@class='v-list-item__title'][contains(.,'Core')])[2]")).click();
        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//div[@class='col col-12'][contains(.,'Active')]")).click();
        Thread.sleep(500);

        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500);

        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("<OK>");

        navegador.findElement(By.xpath("//div[text()='Vati']")).click();
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//div[text()='BCA']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[text()='Grupo_Presidência']")).click();


////////////////////CREATE NEW GRUPO COMERCIAL GESTAO////////////////////

        System.out.println("Cadastrar Grupo Comercial Gestão | Tenant VATI");
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Create group')]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        navegador.findElement(By.xpath("//input[@autofocus='autofocus']")).sendKeys("Grupo Comercial Gestão");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//input[contains(@type,'text')])[3]")).sendKeys("grupo_comercial_gestao");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[1]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Vati')]")).click();

        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[2]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'BCA')]")).click();

        Thread.sleep(500);

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[3]")).click();
        navegador.findElement(By.xpath("(//div[@class='v-list-item__title'][contains(.,'Core')])[2]")).click();
        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//div[@class='col col-12'][contains(.,'Active')]")).click();
        Thread.sleep(500);
        //navegador.findElement(By.xpath("//div[@class='col col-12'][contains(.,'Active')]")).click();
        Thread.sleep(500);

        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500);

        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("<OK>");


        ////////////////////CREATE NEW GRUPO Grupo Comercial Operação////////////////////

        System.out.println("Cadastrar Grupo Comercial Operação | Tenant VATI");
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Create group')]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        navegador.findElement(By.xpath("//input[@autofocus='autofocus']")).sendKeys("Grupo Comercial Operação\n");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//input[contains(@type,'text')])[3]")).sendKeys("grupo_comercial_operacao");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[1]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Vati')]")).click();

        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[2]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'BCA')]")).click();

        Thread.sleep(500);

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[3]")).click();
        navegador.findElement(By.xpath("(//div[@class='v-list-item__title'][contains(.,'Core')])[2]")).click();
        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//div[@class='col col-12'][contains(.,'Active')]")).click();
        Thread.sleep(500);

        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500);

        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("<OK>");


        ////////////////////CREATE NEW GRUPO Grupo Grupo RH Gestão////////////////////

        System.out.println("Cadastrar Grupo RH Gestão | Tenant VATI");
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Create group')]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        navegador.findElement(By.xpath("//input[@autofocus='autofocus']")).sendKeys("Grupo RH Gestão");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//input[contains(@type,'text')])[3]")).sendKeys("grupo_rh_gestao");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[1]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Vati')]")).click();

        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[2]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'BCA')]")).click();

        Thread.sleep(500);

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[3]")).click();
        navegador.findElement(By.xpath("(//div[@class='v-list-item__title'][contains(.,'Core')])[2]")).click();
        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//div[@class='col col-12'][contains(.,'Active')]")).click();
        Thread.sleep(500);

        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500);

        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("<OK>");


        ////////////////////CREATE NEW GRUPO Grupo RH Operação////////////////////

        System.out.println("Cadastrar Grupo RH Operação | Tenant VATI");
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Create group')]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        navegador.findElement(By.xpath("//input[@autofocus='autofocus']")).sendKeys("Grupo RH Operação");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//input[contains(@type,'text')])[3]")).sendKeys("grupo_rh_operacao");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[1]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Vati')]")).click();

        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[2]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'BCA')]")).click();

        Thread.sleep(500);

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[3]")).click();
        navegador.findElement(By.xpath("(//div[@class='v-list-item__title'][contains(.,'Core')])[2]")).click();
        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//div[@class='col col-12'][contains(.,'Active')]")).click();
        Thread.sleep(500);

        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500);

        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("<OK>");


        ////////////////////CREATE NEW GRUPO Grupo Grupo Financeiro////////////////////

        System.out.println("Cadastrar Grupo Financeiro | Tenant VATI");
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Create group')]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        navegador.findElement(By.xpath("//input[@autofocus='autofocus']")).sendKeys("Grupo Financeiro");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//input[contains(@type,'text')])[3]")).sendKeys("grupo_financeiro");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[1]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Vati')]")).click();

        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[2]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'BCA')]")).click();

        Thread.sleep(500);

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[3]")).click();
        navegador.findElement(By.xpath("(//div[@class='v-list-item__title'][contains(.,'Core')])[2]")).click();
        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//div[@class='col col-12'][contains(.,'Active')]")).click();
        Thread.sleep(500);

        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500);

        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("<OK>");


        ////////////////////CREATE NEW GRUPO Grupo Produção Gestão////////////////////

        System.out.println("Cadastrar Grupo Produção Gestão | Tenant VATI");
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Create group')]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        navegador.findElement(By.xpath("//input[@autofocus='autofocus']")).sendKeys("Grupo Produção Gestão");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//input[contains(@type,'text')])[3]")).sendKeys("Grupo_producao_gestao");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[1]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Vati')]")).click();

        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[2]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'BCA')]")).click();

        Thread.sleep(500);

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[3]")).click();
        navegador.findElement(By.xpath("(//div[@class='v-list-item__title'][contains(.,'Core')])[2]")).click();
        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//div[@class='col col-12'][contains(.,'Active')]")).click();
        Thread.sleep(500);

        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500);

        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("<OK>");


        ////////////////////CREATE NEW GRUPO Grupo Produção Operação Volkswagen////////////////////

        System.out.println("Cadastrar Grupo Produção Operação Volkswagen | Tenant VATI");
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Create group')]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        navegador.findElement(By.xpath("//input[@autofocus='autofocus']")).sendKeys("Grupo Produção Operação Volkswagen");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//input[contains(@type,'text')])[3]")).sendKeys("grupo_producao_operacao_volkswagen");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[1]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Vati')]")).click();

        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[2]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'BCA')]")).click();

        Thread.sleep(500);

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[3]")).click();
        navegador.findElement(By.xpath("(//div[@class='v-list-item__title'][contains(.,'Core')])[2]")).click();
        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//div[@class='col col-12'][contains(.,'Active')]")).click();
        Thread.sleep(500);
        //navegador.findElement(By.xpath("//div[@class='col col-12'][contains(.,'Active')]")).click();
        Thread.sleep(500);

        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500);

        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("<OK>");


        ////////////////////CREATE NEW Grupo Produção Operação Vivo////////////////////
        System.out.println("Cadastrar Grupo Produção Operação Vivo | Tenant VATI");
        Thread.sleep(1000);
        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollBy(0,-250)");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Create group')]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        navegador.findElement(By.xpath("//input[@autofocus='autofocus']")).sendKeys("Grupo Produção Operação Vivo");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//input[contains(@type,'text')])[3]")).sendKeys("grupo_producao_operacao_vivo");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[1]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Vati')]")).click();

        Thread.sleep(500);
        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[2]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'BCA')]")).click();

        Thread.sleep(500);

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate mdi mdi-menu-down theme--light')])[3]")).click();
        navegador.findElement(By.xpath("(//div[@class='v-list-item__title'][contains(.,'Core')])[2]")).click();
        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//div[@class='col col-12'][contains(.,'Active')]")).click();
        Thread.sleep(500);

        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500);

        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("<OK>");
        Thread.sleep(2000);

        navegador.findElement(By.xpath("//span[text()='s']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[text()='Logout']")).click();
        System.out.println("Logout");
        navegador.quit();

    }


    @Test
    public void QA___G___INSERT_USER_ADMIN_TENANT() throws InterruptedException {

        navegador.get("http://qa-webapp.vati.rocks/");
        String actualTitle = navegador.getTitle();
        String expectedTitle = "jw2_web_2app";
        //Thread.sleep(500);
        System.out.println("Acessando com login senelinum@protonmail.com");

        navegador.findElement(xpath("//input[contains(@type,'email')]")).sendKeys("senelinum@protonmail.com");
        navegador.findElement(xpath("//input[contains(@type,'password')]")).sendKeys("1234");
        //Thread.sleep(500);
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Login')]")).click();

        Thread.sleep(1000);
        //navegador.navigate().refresh();
        System.out.println("<OK>");

        /////INSERT_USER_ADMIN_IN_TENANT_VATI

        Thread.sleep(2000);
        System.out.println("Adicionar usuário admin@email.com ao Tenant VATI");
        Thread.sleep(1000);


        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate')])[4]")).click();

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate')])[4]")).click();
        //navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Core Settings')]")).click();
        //navegador.findElement(By.xpath("//div[text()='Core Settings']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Tenants')]")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//button[contains(@class,'v-icon notranslate')]")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//span[text()=' Create Users ']")).click();
        Thread.sleep(1000);
        navegador.findElement(xpath("//textarea[contains(@autocomplete,'email')]")).sendKeys("admin@email.com");
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();
        wait = new WebDriverWait(navegador, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        Assert.assertEquals("User(s) added", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(1000);
        System.out.println("<OK>");
        navegador.findElement(By.xpath("//span[text()='s']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[text()='Logout']")).click();
        System.out.println("Logout");
        navegador.quit();
    }
}

