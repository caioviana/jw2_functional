package functional.server;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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


public class ST_TasksTest {

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
        //navegador.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
        //navegador.manage().timeouts().setScriptTimeout(90, TimeUnit.SECONDS);
        WebDriverWait wait = null;
    }


    @After
    public void tearDown() {
        navegador.quit();
    }

    @Test
    public void ST___CADASTRO_MASTER_FULL() throws InterruptedException {

        navegador.get("http://staging-webapp.vati.rocks/");
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

        ////////APPLICATIONS INSTANCES///////////
        System.out.println("=== ST - Acessando como MASTER ===");
        System.out.println("========== ST - Adicionar Applications Instances ==========");

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

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        Assert.assertEquals("ApplicationInstance created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(1000);
        System.out.println("> OK ");

        ////////TENANTS////////

        System.out.println("========== ST - Incluir Tenants ==========");
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

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Tenant created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(1000);

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Vati']")));
        textElement = navegador.findElement(By.xpath("//td[text()='Vati']")).getText();
        assertEquals("Vati", textElement);
        System.out.println("> OK ");

        /////ADD NEW_USER_ADMIN_TENANTS

        System.out.println("========== ST - Vincular usuário ao Tenant ==========");
        navegador.findElement(By.xpath("(//button[contains(@class,'v-icon notranslate')])[4]")).click();
        navegador.findElement(By.xpath("//span[text()=' Create Users ']")).click();

        navegador.findElement(xpath("//textarea[contains(@autocomplete,'email')]")).sendKeys("senelinum@protonmail.com");
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        Assert.assertEquals("User(s) added", textElement);

        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(1000);

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='senelinum@protonmail.com']"))).click();
        textElement = navegador.findElement(By.xpath("//td[text()='senelinum@protonmail.com']")).getText();
        assertEquals("senelinum@protonmail.com", textElement);
        Thread.sleep(3000);
/*
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Create Users')]")).click();

        navegador.findElement(xpath("//textarea[contains(@autocomplete,'email')]")).sendKeys("senelinum2@protonmail.com");
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        //Assert.assertEquals("Access Denied.", textElement);
        Assert.assertEquals("Invalid privilege", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();

 */
        Thread.sleep(15000);
        System.out.println("> OK ");

        Thread.sleep(1000);
/*
        Thread.sleep(3000);

        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Create Users')]")).click();

        navegador.findElement(xpath("//textarea[contains(@autocomplete,'email')]")).sendKeys("senelinum2@protonmail.com");
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        //Assert.assertEquals("Access Denied.", textElement);
        Assert.assertEquals("Invalid privilege", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();

 */

        //navegador.findElement(By.xpath("(//div[@class='v-responsive__content'])[2]")).click();
        //Thread.sleep(1000);
        //navegador.findElement(By.xpath("//div[text()='Logout']")).click();

    }


    @Test
    public void ST___RESGATAR_SENHA_ACESSAR_EMAIL() throws InterruptedException {
        //////_ACESSAR_EMAIL_RESGATAR_SENHA_DE_ACESSO////////

/*        System.out.println("========== ST - Acessar email resgatar senha enviada ==========");
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

        wait = new WebDriverWait(navegador, 29);
        navegador.findElement(By.xpath("(//span[contains(@class,'flex-item-fluid text-ellipsis')])[1]")).click();
        //navegador.findElement(By.xpath("//span[@class='flex-item-fluid text-ellipsis max-w100'][contains(.,'Caixa de entrada')]")).click();
        navegador.findElement(By.xpath("//span[@title='JAM 2 user invite']")).click();

        String text = navegador.findElement(By.xpath("//div[contains(text(),'password')]")).getText();
        System.out.println(text.substring(103, 108));
        String currentPassword = text.substring(103, 108);


        //navegador.switchTo().window(tabs.get(0));
        navegador.get("http://staging-webapp.vati.rocks/");

        System.out.println("> OK ");
        Thread.sleep(2000);
        System.out.println("========== ST - Login com senha recebida ==========");

        navegador.findElement(By.xpath("//input[contains(@type,'email')]")).sendKeys("senelinum@protonmail.com");
        navegador.findElement(By.xpath("//input[contains(@type,'password')]")).sendKeys(currentPassword);
        Thread.sleep(2000);

        navegador.findElement(By.xpath("//span[text()=' Login ']")).click();
        System.out.println("> OK ");
        Thread.sleep(1000);

/*
        /// ALTERACAO DE SENHA MANUAL///

        System.out.println("========== ST - Alterar a senha manualmente pela aplicacao ==========");
        navegador.findElement(By.xpath("//span[text()='s']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[text()='Change password']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys("1234");
        Thread.sleep(1000);
        navegador.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("1234");
        Thread.sleep(2000);
        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();
        Thread.sleep(3000);
        navegador.findElement(xpath("//div[text()='Logout']")).click();


 */


        ///TROCA DE SENHA AUTOMATICA

        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Save')]")).click();
        //navegador.findElement(By.xpath("//span[text()='Save']")).click();

        System.out.println("========== ST - Cadastrar senha errada, validacao campos ==========");
        navegador.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys("1234");
        navegador.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("5678");
        navegador.findElement(By.xpath("(//span[contains(.,'Save')])[2]")).click();
        Thread.sleep(1000);
        System.out.println("> OK ");

        System.out.println("========== ST - Cadastrar senha correta, 1234 ==========");
        navegador.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        //navegador.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys(Keys.chord(Keys.BACK_SPACE));
        navegador.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys("1234");

        navegador.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        //navegador.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys(Keys.chord(Keys.BACK_SPACE));
        navegador.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("1234");
        System.out.println("> OK ");

        Thread.sleep(1000);
        navegador.findElement(By.xpath("(//span[contains(.,'Save')])[2]")).click();


        Thread.sleep(3000);

        navegador.findElement(By.xpath("//span[text()='s']")).click();

        //navegador.findElement(xpath("((//span[@class='black--text headline'][contains(.,'s')])[1]")).click();
        Thread.sleep(1000);
        navegador.findElement(xpath("//div[text()='Logout']")).click();
        Thread.sleep(1000);


        System.out.println("========== ST - Login com senha alterada ==========");
        navegador.findElement(xpath("//input[contains(@type,'email')]")).sendKeys("senelinum@protonmail.com");
        navegador.findElement(xpath("//input[contains(@type,'password')]")).sendKeys("1234");
        Thread.sleep(1000);
        //navegador.navigate().refresh();
        System.out.println("> OK ");
        Thread.sleep(2000);

        System.out.println("========== ST - Clicando em Core Settings, deverá informar os iténs |Tenants|Companies|Groups|Users ========== ");
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Login')]")).click();
        Thread.sleep(1000);

        //System.out.println(navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Login')]")));
        //System.out.println(navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Core Settings')]")));
        Thread.sleep(1000);


        /////INSERT_USER_ADMIN_IN_TENANT_VATI

        Thread.sleep(2000);
        System.out.println("========== ST - Adicionar usuário admin@email.com ao Tenant VATI ==========");
        Thread.sleep(1000);

        WebDriverWait wait = new WebDriverWait(navegador, 10);
        WebElement element = wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[contains(@class,'v-icon notranslate')])[4]"))));

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate')])[4]")).click();

        navegador.findElement(By.xpath("(//i[contains(@class,'v-icon notranslate')])[4]")).click();
        //navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Core Settings')]")).click();
        //navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Core Settings')]")).click();
        //navegador.findElement(By.xpath("//div[text()='Core Settings']")).click();

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Tenants']"))).getText();
        textElement = navegador.findElement(By.xpath("//div[text()='Tenants']")).getText();
        assertEquals("Tenants", textElement);
        Thread.sleep(500);

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Companies']"))).getText();
        textElement = navegador.findElement(By.xpath("//div[text()='Companies']")).getText();
        assertEquals("Companies", textElement);
        Thread.sleep(500);

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Groups']"))).getText();
        textElement = navegador.findElement(By.xpath("//div[text()='Groups']")).getText();
        assertEquals("Groups", textElement);
        Thread.sleep(500);

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Users']"))).getText();
        textElement = navegador.findElement(By.xpath("//div[text()='Users']")).getText();
        assertEquals("Users", textElement);
        Thread.sleep(500);


        System.out.println("> OK ");
        Thread.sleep(1000);

        System.out.println("========== ST - Logout Sistema ==========");
        navegador.findElement(By.xpath("//span[text()='s']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[text()='Logout']")).click();
    }


    @Test
    public void ST___CADASTRO_ADMIN_FULL() throws InterruptedException {

        navegador.get("http://staging-webapp.vati.rocks/");
        String actualTitle = navegador.getTitle();
        String expectedTitle = "jw2_web_2app";
        //Thread.sleep(500);
        System.out.println("=== ST - Acessando como ADMIN ===");
        System.out.println("========== ST - Acessar com privilégios ADMIN usuário senelinum@protonmail.com ==========");

        navegador.findElement(xpath("//input[contains(@type,'email')]")).sendKeys("senelinum@protonmail.com");
        navegador.findElement(xpath("//input[contains(@type,'password')]")).sendKeys("1234");
        //Thread.sleep(500);
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Login')]")).click();
        System.out.println("> OK ");

        Thread.sleep(1000);


////////////////////COMPANIES-VATI-INATIVAR/////////////////////

        Thread.sleep(3000);
        System.out.println("========== ST - Inativar Companies padrão do sistema ==========");
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
        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        //assertEquals("Access Denied.", textElement);
        assertEquals("Company edited", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(3000);
        System.out.println("> OK ");


///////////////////COMPANIE_BCA//////////////////////

        System.out.println("========== ST - Cadastrar Companie BCA ==========");
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

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Company created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(1000);
        System.out.println("> OK ");

////////////////////COMPANIES-VL VATICANO////////////////////

        System.out.println("========== ST - Cadastrar Companie VL VATICANO ==========");
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

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Company created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(3000);
        System.out.println("> OK ");


////////////////////CREATE NEW GROUPS_PRESIDENCIA////////////////////

        System.out.println("========== ST - Cadastrar Grupo_Presidência | Tenant VATI ==========");
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

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("> OK ");

        navegador.findElement(By.xpath("//div[text()='Vati']")).click();
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//div[text()='BCA']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[text()='Grupo_Presidência']")).click();


////////////////////CREATE NEW GRUPO COMERCIAL GESTAO////////////////////

        System.out.println("========== ST - Cadastrar Grupo Comercial Gestão | Tenant VATI ==========");
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

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("> OK ");


        ////////////////////CREATE NEW GRUPO Grupo Comercial Operação////////////////////

        System.out.println("========== ST - Cadastrar Grupo Comercial Operação | Tenant VATI ==========");
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

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("> OK ");


        ////////////////////CREATE NEW GRUPO Grupo Grupo RH Gestão////////////////////

        System.out.println("========== ST - Cadastrar Grupo RH Gestão | Tenant VATI ==========");
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

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("> OK ");


        ////////////////////CREATE NEW GRUPO Grupo RH Operação////////////////////

        System.out.println("========== ST - Cadastrar Grupo RH Operação | Tenant VATI ==========");
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

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("> OK ");


        ////////////////////CREATE NEW GRUPO Grupo Grupo Financeiro////////////////////

        System.out.println("========== ST - Cadastrar Grupo Financeiro | Tenant VATI ==========");
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

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("> OK ");


        ////////////////////CREATE NEW GRUPO Grupo Produção Gestão////////////////////

        System.out.println("========== ST - Cadastrar Grupo Produção Gestão | Tenant VATI ==========");
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

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("> OK ");


        ////////////////////CREATE NEW GRUPO Grupo Produção Operação Volkswagen////////////////////

        System.out.println("========== ST - Cadastrar Grupo Produção Operação Volkswagen | Tenant VATI ==========");
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

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("> OK ");


        ////////////////////CREATE NEW Grupo Produção Operação Vivo////////////////////
        System.out.println("========== ST - Cadastrar Grupo Produção Operação Vivo | Tenant VATI ==========");
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

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("Group created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("> OK ");
        Thread.sleep(2000);

        navegador.findElement(By.xpath("//span[text()='s']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[text()='Logout']")).click();
        System.out.println("========== ST - Logout ==========");

    }


    @Test
    public void ST___INSERIR_USER_ADMIN_TENANT() throws InterruptedException {

        navegador.get("http://staging-webapp.vati.rocks/");
        String actualTitle = navegador.getTitle();
        String expectedTitle = "jw2_web_2app";
        //Thread.sleep(500);
        System.out.println("========== ST - Acessando com login senelinum@protonmail.com ==========");

        navegador.findElement(xpath("//input[contains(@type,'email')]")).sendKeys("senelinum@protonmail.com");
        navegador.findElement(xpath("//input[contains(@type,'password')]")).sendKeys("1234");
        //Thread.sleep(500);
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Login')]")).click();

        Thread.sleep(1000);
        //navegador.navigate().refresh();
        System.out.println("> OK ");

        /////INSERT_USER_ADMIN_IN_TENANT_VATI

        Thread.sleep(2000);
        System.out.println("========== ST - Adicionar usuário admin@email.com ao Tenant VATI ==========");
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
        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        Assert.assertEquals("User(s) added", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(1000);
        System.out.println("> OK ");
        navegador.findElement(By.xpath("//span[text()='s']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[text()='Logout']")).click();
        System.out.println("========== ST - Logout ==========");
    }

    @Test
    public void ST___CADASTRO_CONTENT() throws InterruptedException {

        navegador.get("http://staging-webapp.vati.rocks/");
        String actualTitle = navegador.getTitle();
        String expectedTitle = "jw2_web_2app";
        //Thread.sleep(500);
        System.out.println("========== ST - Acessando com login senelinum@protonmail.com ==========");

        navegador.findElement(xpath("//input[contains(@type,'email')]")).sendKeys("senelinum@protonmail.com");
        navegador.findElement(xpath("//input[contains(@type,'password')]")).sendKeys("1234");
        //Thread.sleep(500);
        navegador.findElement(xpath("//span[@class='v-btn__content'][contains(.,'Login')]")).click();

        Thread.sleep(1000);
        //navegador.navigate().refresh();
        System.out.println("> OK ");

        /////// CONTENTS ///////

        Thread.sleep(2000);
        System.out.println("========== ST - Acessar Content ==========");
        Thread.sleep(1000);

        System.out.println("========== ST - Clicar botão lateral esquerdo Content ==========");
        navegador.findElement(By.xpath("//div[text()='Contents']")).click();
        System.out.println("> OK ");

        Thread.sleep(1000);
        System.out.println("========== ST - Clicar em Content Settings ==========");
        navegador.findElement(By.xpath("//span[text()=' Content Settings ']")).click();
        Thread.sleep(1000);

        /////// ADD FILE SERVERS ///////

        System.out.println("========== ST - Clicar em File Servers ==========");
        navegador.findElement(By.xpath("//div[text()='File Servers']")).click();
        System.out.println("> OK ");
        Thread.sleep(1000);

        System.out.println("========== ST - Clicar em Create File Server ==========");
        navegador.findElement(By.xpath("//span[text()=' Create File Server ']")).click();
        System.out.println("> OK ");
        Thread.sleep(2000);

        System.out.println("========== ST - Validacao de todos os campos obrigatorios  ==========");
        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();

        System.out.println("========== ST - Inserir  [Label]: File Server Geral ==========");
        navegador.findElement(By.xpath("(//label[text()='Label']/following::input)[1]")).sendKeys("File Server Geral");
        System.out.println("> OK ");
        Thread.sleep(500);

        System.out.println("========== ST - Inserir [URL]: 172.17.1.152 ==========");
        navegador.findElement(By.xpath("(//label[text()='Url']/following::input)[1]")).sendKeys("172.17.1.152");
        System.out.println("> OK ");
        Thread.sleep(500);

        System.out.println("========== ST - Selecionar [Type]: Native  ==========");
        navegador.findElement(By.xpath("(//div[@class='v-input__icon v-input__icon--append']//i)[2]")).click();
        System.out.println("> OK ");
        Thread.sleep(500);

        navegador.findElement(By.xpath("//div[text()='Native']")).click();
        Thread.sleep(500);

        System.out.println("========== ST - Selecionar [Tenant]: Vati  ==========");
        navegador.findElement(By.xpath("(//div[@class='v-input__icon v-input__icon--append']//i)[3]")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//div[text()='Vati']")).click();
        //navegador.findElement(By.xpath("//div[text()='Tenant']")).click();
        System.out.println("> OK ");

        Thread.sleep(500);

        System.out.println("========== ST - Salvar  ==========");
        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();
        //navegador.findElement(By.xpath("(//span[text()=' Close '])[2]")).click();

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        assertEquals("File Server Created", textElement);
        navegador.findElement(By.xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(1000);
        System.out.println("> OK ");

        System.out.println("========== ST - Clicar no Breadcrumbs ==========");
        navegador.findElement(By.linkText("Content Settings")).click();
        System.out.println("> OK ");
        Thread.sleep(1000);
        //navegador.findElement(By.xpath("//div[text()='Contents']")).click();
        //Thread.sleep(1000);
        //navegador.findElement(By.xpath("//span[text()=' Content Settings ']")).click();
        //Thread.sleep(1000);
        //navegador.findElement(By.xpath("//div[text()='Repositories']")).click();

        /////// ADD REPOSITORIES ///////

        System.out.println("========== ST - Clicar em Repositories  ==========");
        navegador.findElement(By.xpath("//div[text()='Repositories']")).click();
        System.out.println("> OK ");
        Thread.sleep(1000);

        System.out.println("========== ST - Clicar em Create Repository ==========");
        navegador.findElement(By.xpath("//span[text()=' Create Repository ']")).click();
        System.out.println("> OK ");

        System.out.println("========== ST - Validacao de todos os campos obrigatorios  ==========");
        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();
        System.out.println("> OK ");

        ////// ADMINISTRATIVO /////

        System.out.println("========== ST - Inserir [Title]: Administrativo ==========");
        navegador.findElement(By.xpath("(//label[text()='Title']/following::input)[1]")).sendKeys("Administrativo");
        System.out.println("> OK ");
        Thread.sleep(1000);

        System.out.println("========== ST - Inserir [Folder Name]: Administrativo ==========");
        navegador.findElement(By.xpath("(//label[text()='Folder Name']/following::input)[1]")).sendKeys("Administrativo");
        System.out.println("> OK ");
        Thread.sleep(1000);

        System.out.println("========== ST - Selecionar [File Server]: File Server Geral ==========");
        navegador.findElement(By.xpath("(//div[@class='v-input__icon v-input__icon--append']//i)[2]")).click();
        System.out.println("> OK ");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("(//div[text()='File Server Geral'])[1]")).click();
        System.out.println("> OK ");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        Assert.assertEquals("Repository Created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("> OK ");

        ////// COMERCIAL /////

        navegador.findElement(By.xpath("//span[text()=' Create Repository ']")).click();
        System.out.println("========== ST - Inserir [Title]: Comercial ==========");
        navegador.findElement(By.xpath("(//label[text()='Title']/following::input)[1]")).sendKeys("Comercial");
        System.out.println("> OK ");
        Thread.sleep(1000);

        System.out.println("========== ST - Inserir [Folder Name]: Comercial ==========");
        navegador.findElement(By.xpath("(//label[text()='Folder Name']/following::input)[1]")).sendKeys("Comercial");
        System.out.println("> OK ");
        Thread.sleep(1000);

        System.out.println("========== ST - Selecionar [File Server]: File Server Geral ==========");
        navegador.findElement(By.xpath("(//div[@class='v-input__icon v-input__icon--append']//i)[2]")).click();
        System.out.println("> OK ");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("(//div[text()='File Server Geral'])[1]")).click();
        System.out.println("> OK ");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        Assert.assertEquals("Repository Created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("> OK ");

        ////// PRODUCAO /////

        navegador.findElement(By.xpath("//span[text()=' Create Repository ']")).click();
        System.out.println("========== ST - Inserir [Title]: Producao ==========");
        navegador.findElement(By.xpath("(//label[text()='Title']/following::input)[1]")).sendKeys("Producao");
        System.out.println("> OK ");
        Thread.sleep(1000);

        System.out.println("========== ST - Inserir [Folder Name]: Producao ==========");
        navegador.findElement(By.xpath("(//label[text()='Folder Name']/following::input)[1]")).sendKeys("Producao");
        System.out.println("> OK ");
        Thread.sleep(1000);

        System.out.println("========== ST - Selecionar [File Server]: File Server Geral ==========");
        navegador.findElement(By.xpath("(//div[@class='v-input__icon v-input__icon--append']//i)[2]")).click();
        System.out.println("> OK ");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//div[text()='File Server Geral']")).click();
        System.out.println("> OK ");
        Thread.sleep(1000);

        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        Assert.assertEquals("Repository Created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(2000);
        System.out.println("> OK ");

        //navegador.findElement(By.xpath("//span[text()='s']")).click();
        //Thread.sleep(1000);
        //navegador.findElement(By.xpath("//div[text()='Logout']")).click();
        //System.out.println("========== ST - Logout ==========");
    }

    @Test
    public void ST___CADASTRO_CDP() throws InterruptedException {

        navegador.get("http://staging-webapp.vati.rocks/");
        String actualTitle = navegador.getTitle();
        String expectedTitle = "jw2_web_2app";
        //Thread.sleep(500);

        navegador.findElement(By.xpath("//input[contains(@type,'email')]")).sendKeys("admin@email.com");
        navegador.findElement(By.xpath("//input[contains(@type,'password')]")).sendKeys("admin");
        //Thread.sleep(500);
        navegador.findElement(By.xpath("//span[@class='v-btn__content'][contains(.,'Login')]")).click();
        //wait = new WebDriverWait(navegador, 15);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='v-btn__content'][contains(.,'Logout')]")));

        String textElement = navegador.findElement(By.className("v-main")).getText();
        //assertEquals("Home", textElement);
        Thread.sleep(1000);
        //navegador.navigate().refresh();
        System.out.println("========== ST - Acessando CDP ==========");
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[text()='CDP']")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//span[text()=' CDP Settings ']")).click();
        System.out.println("========== ST - Listar |Customer Properties|Unomi Config|Unomi Console|Attribute Mapping|Profile View ==========");
        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Customer Properties']"))).getText();
        textElement = navegador.findElement(By.xpath("//div[text()='Customer Properties']")).getText();
        assertEquals("Customer Properties", textElement);
        Thread.sleep(500);
        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Unomi Config']"))).getText();
        textElement = navegador.findElement(By.xpath("//div[text()='Unomi Config']")).getText();
        assertEquals("Unomi Config", textElement);
        Thread.sleep(500);
        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Unomi Console']"))).getText();
        textElement = navegador.findElement(By.xpath("//div[text()='Unomi Console']")).getText();
        assertEquals("Unomi Console", textElement);
        Thread.sleep(500);

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Attribute Mapping']"))).getText();
        textElement = navegador.findElement(By.xpath("//div[text()='Attribute Mapping']")).getText();
        assertEquals("Attribute Mapping", textElement);
        Thread.sleep(500);

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Profile View']"))).getText();
        textElement = navegador.findElement(By.xpath("//div[text()='Profile View']")).getText();
        assertEquals("Profile View", textElement);
        Thread.sleep(500);

        System.out.println("> OK ");

        //////CUSTOMER PROPERTIES///////

        System.out.println("========== ST - Cadastrar Customer Propertis ==========");
        navegador.findElement(By.xpath("//div[text()='Customer Properties']")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[text()=' Create New ']")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//label[text()='Title']/following::input)[1]")).sendKeys("zare_sports_website");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//div[@class='v-select__selections'])[2]")).click();
        navegador.findElement(By.xpath("//div[text()='Tenant']")).click();
        navegador.findElement(By.xpath("(//div[@class='v-select__selections'])[3]")).click();
        navegador.findElement(By.xpath("//div[text()='Website']")).click();
        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        Assert.assertEquals("Customer Property created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//td[text()='zare_sports_website'])[1]"))).getText();
        textElement = navegador.findElement(By.xpath("(//td[text()='zare_sports_website'])[1]")).getText();
        assertEquals("zare_sports_website", textElement);
        Thread.sleep(500);
        navegador.findElement(By.xpath("//a[@class='v-breadcrumbs__item']")).click();
        System.out.println("> OK ");

        //////UNOMI CONFIG/////

        System.out.println("========== ST - Cadastrar Unomi Config ==========");
        navegador.findElement(By.xpath("//div[text()='Unomi Config']")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[text()=' Create New ']")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//label[text()='Unomi Id']/following::input)[1]")).sendKeys("copy property to profile");
        Thread.sleep(500);
        navegador.findElement(By.xpath("(//label[text()='Description']/following::input)[1]")).sendKeys("copy every event property to profile");
        Thread.sleep(500);
        navegador.findElement(By.xpath("//input[@type='number']")).sendKeys("1");
        Thread.sleep(500);
        navegador.findElement(By.tagName("textarea")).sendKeys("{\n" +
                "  \"metadata\": {\n" +
                "    \"id\": \"CopyEventViewToProfile\",\n" +
                "    \"name\": \"CopyEventToProfileView\",\n" +
                "    \"description\": \"Copy every event properties to profile properties\",\n" +
                "    \"scope\": \"systemscope\"\n" +
                "  },\n" +
                "  \"condition\": {\n" +
                "      \"type\": \"eventTypeCondition\",\n" +
                "      \"parameterValues\": {\n" +
                "        \"eventTypeId\" : \"view\"\n" +
                "      }\n" +
                "  },\n" +
                "  \"actions\": [\n" +
                "    {\n" +
                "      \"type\": \"allEventToProfilePropertiesAction\"\n" +
                "    }\n" +
                "  ]\n" +
                "}");

        Thread.sleep(1000);


        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");


        Thread.sleep(1000);
        navegador.findElement(By.className("v-card__actions")).click();

        Thread.sleep(500);

        navegador.findElement(By.xpath("(//div[@class='v-input__icon v-input__icon--append']//i)[2]")).click();
        //navegador.findElement(By.xpath("(//div[text()='Tenant'])[2]")).click();
        //navegador.findElement(By.xpath("(//div[text()='Tenant'])[2]")).click();
        Thread.sleep(500);

        navegador.findElement(By.className("v-card__actions")).click();
        navegador.findElement(By.xpath("(//div[contains(@class,'v-select__selections')])[2]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Tenant')]")).click();
        Thread.sleep(500);

        navegador.findElement(By.className("v-card__actions")).click();
        navegador.findElement(By.xpath("(//div[contains(@class,'v-select__selections')])[3]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'Rule')]")).click();
        Thread.sleep(500);

        navegador.findElement(By.className("v-card__actions")).click();
        navegador.findElement(By.xpath("(//div[contains(@class,'v-select__selections')])[4]")).click();
        navegador.findElement(By.xpath("//div[@class='v-list-item__title'][contains(.,'zare_sports_website')]")).click();
        Thread.sleep(500);

        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();
        Thread.sleep(500);

        navegador.findElement(By.xpath("//td[text()='copy property to profile']")).click();
        Thread.sleep(500);

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        Assert.assertEquals("Customer Property created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(500);

        navegador.findElement(By.xpath("//td[text()='copy property to profile']")).click();
        Thread.sleep(500);

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='copy property to profile']"))).getText();
        textElement = navegador.findElement(By.xpath("//td[text()='copy property to profile']")).getText();
        assertEquals("copy property to profile", textElement);
        Thread.sleep(500);
        navegador.findElement(By.xpath("//a[@class='v-breadcrumbs__item']")).click();
        System.out.println("> OK ");


        //////UNOMI CONSOLE/////

        System.out.println("========== ST - Acessando Unomi Console ==========");
        navegador.findElement(By.xpath("//div[text()='Unomi Console']")).click();
        Thread.sleep(500);
        System.out.println("> OK ");
        Thread.sleep(1000);
        System.out.println("========== ST - Executar comando profile-list no Unomi Console ==========");
        navegador.findElement(By.tagName("input")).sendKeys("profile-list");
        Thread.sleep(500);

        navegador.findElement(By.xpath("(//span[@class='v-btn__content']//i)[3]")).click();
        Thread.sleep(2000);
        System.out.println("> OK ");

        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);

        WebElement element = navegador.findElement(By.xpath("//a[@class='v-breadcrumbs__item']"));
        Actions actions = new Actions(navegador);
        actions.moveToElement(element);
        actions.perform();

        Thread.sleep(1000);
        navegador.findElement(By.xpath("//a[@class='v-breadcrumbs__item']")).click();

        //////ATTRIBUTE MAPPING /////

        System.out.println("========== ST - Cadastrar Attribute Mapping ==========");
        navegador.findElement(By.xpath("//div[text()='Attribute Mapping']")).click();
        navegador.findElement(By.xpath("//span[text()=' Create New ']")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();

        navegador.findElement(By.xpath("(//label[text()='Customer Property Attribute']/following::input)[1]")).sendKeys("firstName");
        Thread.sleep(500);

        navegador.findElement(By.xpath("(//label[text()='Media Attribute']/following::input)[1]")).sendKeys("name");
        Thread.sleep(500);

        navegador.findElement(By.xpath("(//div[@class='v-select__selections'])[2]")).click();
        navegador.findElement(By.xpath("//div[text()='Google']")).click();
        Thread.sleep(500);

        navegador.findElement(By.xpath("(//div[@class='v-select__selections'])[3]")).click();
        navegador.findElement(By.xpath("//div[text()='Tenant']")).click();
        Thread.sleep(500);

        navegador.findElement(By.xpath("(//div[@class='v-select__selections'])[4]")).click();
        navegador.findElement(By.xpath("//div[text()='zare_sports_website']")).click();
        Thread.sleep(500);

        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        Assert.assertEquals("Attribute Mapping created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(500);

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='firstName']"))).getText();
        textElement = navegador.findElement(By.xpath("//td[text()='firstName']")).getText();
        assertEquals("firstName", textElement);
        Thread.sleep(500);
        navegador.findElement(By.xpath("//a[@class='v-breadcrumbs__item']")).click();
        Thread.sleep(500);
        System.out.println("> OK ");

        //////PROFILE VIEW /////

        System.out.println("========== ST - Cadastrar Profile View ==========");
        navegador.findElement(By.xpath("//div[text()='Profile View']")).click();
        navegador.findElement(By.xpath("//span[text()=' Create New ']")).click();
        Thread.sleep(500);
        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();
        Thread.sleep(500);

        navegador.findElement(By.xpath("(//label[text()='Title']/following::input)[1]")).sendKeys("All");
        Thread.sleep(500);

        //navegador.findElement(By.xpath("(//label[text()='Key']/following::input)[1]")).sendKeys("all");
        //Thread.sleep(500);

        navegador.findElement(By.xpath("(//label[text()='Description']/following::input)[1]")).sendKeys("View all profiles");
        Thread.sleep(500);

        ((JavascriptExecutor) navegador)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);

        element = navegador.findElement(By.xpath("//span[text()=' Save ']"));
        actions = new Actions(navegador);
        actions.moveToElement(element);
        actions.perform();

        navegador.findElement(By.xpath("//textarea[@rows='5']")).sendKeys("{\n" +
                "  \"offset\": 0,\n" +
                "  \"limit\": 1000,\n" +
                "  \"sortby\": \"properties.lastName:asc,properties.firstName:desc\",\n" +
                "  \"condition\": {\n" +
                "    \"type\": \"booleanCondition\",\n" +
                "    \"parameterValues\": {\n" +
                "      \"operator\": \"or\",\n" +
                "      \"subConditions\": [\n" +
                "        {\n" +
                "          \"type\": \"profilePropertyCondition\",\n" +
                "          \"parameterValues\": {\n" +
                "            \"propertyName\": \"properties.firstVisit\",\n" +
                "            \"comparisonOperator\": \"exists\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"profilePropertyCondition\",\n" +
                "          \"parameterValues\": {\n" +
                "            \"propertyName\": \"properties.lastVisit\",\n" +
                "            \"comparisonOperator\": \"exists\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}");
        Thread.sleep(500);

        navegador.findElement(By.xpath("(//div[@class='v-select__selections'])[2]")).click();
        navegador.findElement(By.xpath("//div[text()='Tenant']")).click();
        Thread.sleep(500);

        navegador.findElement(By.xpath("(//div[@class='v-select__selections'])[3]")).click();
        navegador.findElement(By.xpath("//div[text()='zare_sports_website']")).click();
        Thread.sleep(500);

        navegador.findElement(By.xpath("(//div[@class='v-select__selections'])[4]")).click();
        navegador.findElement(By.xpath("//div[text()='Atendimento']")).click();
        Thread.sleep(500);


        //navegador.findElement(By.className("v-card__actions")).click();
        //Thread.sleep(500);

        navegador.findElement(By.xpath("//span[text()=' Save ']")).click();

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(className("v-snack__content")));
        textElement = navegador.findElement(className("v-snack__content")).getText();
        Assert.assertEquals("Profile View created", textElement);
        navegador.findElement(xpath("(//span[text()=' Close '])[1]")).click();
        Thread.sleep(500);
        System.out.println("> OK ");

        //navegador.findElement(By.xpath("//span[text()=' zare_sports_website ']")).getText();

        wait = new WebDriverWait(navegador, 29);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' zare_sports_website ']"))).getText();
        textElement = navegador.findElement(By.xpath("//span[text()=' zare_sports_website ']")).getText();
        assertEquals("zare_sports_website", textElement);
        Thread.sleep(500);


        navegador.findElement(By.xpath("//div[text()='CDP']")).click();
        Thread.sleep(500);
        //navegador.findElement(By.xpath("//span[text()=' CDP Settings ']")).click();

        navegador.findElement(By.xpath("//button[contains(@class,'v-icon notranslate')]")).click();
        Thread.sleep(500);

        System.out.println("========== ST - Realizar Download | Profile View Export ==========");
        navegador.findElement(By.xpath("//span[text()=' download ']")).click();
        Thread.sleep(500);
        System.out.println("> OK ");

        navegador.findElement(By.xpath("//div[text()='CDP']")).click();
        Thread.sleep(500);

        navegador.findElement(By.xpath("//span[text()=' CDP Settings ']")).click();
        Thread.sleep(500);

        navegador.findElement(By.xpath("//div[text()='Customer Properties']")).click();
        Thread.sleep(500);

        navegador.findElement(By.xpath("(//button[contains(@class,'v-icon notranslate')])[2]")).click();
        Thread.sleep(500);

        navegador.findElement(By.xpath("//div[@class='v-card__text']//code[1]")).click();
        Thread.sleep(500);
        System.out.println("========== ST - Visualizar informacoes cadastradas no Customer Propertis |Snippet ==========");

        navegador.findElement(By.xpath("(//span[text()=' Close '])[2]")).click();
        Thread.sleep(500);
        System.out.println("> OK ");
        Thread.sleep(500);

        navegador.findElement(By.xpath("(//div[@class='v-responsive__content'])[2]")).click();
        Thread.sleep(1000);
        navegador.findElement(By.xpath("//div[text()='Logout']")).click();
        System.out.println("========== ST - Logout ==========");
    }
}