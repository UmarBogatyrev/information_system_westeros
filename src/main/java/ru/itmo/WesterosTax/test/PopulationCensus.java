package ru.itmo.WesterosTax.test;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

@FixMethodOrder(MethodSorters.JVM)
public class PopulationCensus {

    private WebDriver driver;

    @Before
    public void setUp() {
        String chromeExecutablePath = "C:\\Develop\\test\\chrome-win64\\chrome.exe";
        System.setProperty("webdriver.chrome.driver",
                "C:\\Develop\\test\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setBinary(chromeExecutablePath);
        driver = new ChromeDriver(options);
    }

    @Test
    public void testLordPopulation() throws InterruptedException {
        loginAsLord();
        driver.get("http://localhost:8080/landowner/index");

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement linkElement = driver.findElement(By.linkText("Главная"));

        // Кликнуть по найденному элементу
        linkElement.click();

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        driver.get("http://localhost:8080/lord/main");

        WebElement orderInput = driver.findElement(By.id("showAlertCensus"));

        // Кликнуть по найденному элементу
        orderInput.click();

        // Пауза в 3 секунды
        Thread.sleep(1000);

        WebElement dataInputOne = driver.findElement(By.id("dateBegin"));

        dataInputOne.sendKeys("12.11.2023");

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement dataInputTwo = driver.findElement(By.id("dateEnd"));

        dataInputTwo.sendKeys("12.15.2023");

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement commentInput = driver.findElement(By.name("comment"));
        commentInput.sendKeys("Плати налог");

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement submitButton = driver.findElement(By.cssSelector("input[value='Отправить']"));
        submitButton.click();

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement submitButtonExit = driver.findElement(By.cssSelector("input[class='btn text-white']"));

        submitButtonExit.click();
        // Пауза в 3 секунды
        // Thread.sleep(3000);

    }

    @Test
    public void testCorierPopulation() throws InterruptedException {
        loginAsCorier();
        driver.get("http://localhost:8080/household/index");

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement linkElement = driver.findElement(By.linkText("+"));

        // Кликнуть по найденному элементу
        linkElement.click();

        driver.get("http://localhost:8080/household/create");

        WebElement humanNameInput = driver.findElement(By.name("name"));
        humanNameInput.sendKeys("Иван Петров");

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement addressNameInput = driver.findElement(By.name("address"));
        addressNameInput.sendKeys("улица.Жукова");

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement residentsNameInput = driver.findElement(By.name("residentCount"));
        // Очищаем текущее значение
        residentsNameInput.clear();
        residentsNameInput.sendKeys("5");

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement incomeNameInput = driver.findElement(By.name("income"));
        // Очищаем текущее значение
        incomeNameInput.clear();
        incomeNameInput.sendKeys("213231");

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement squareNameInput = driver.findElement(By.name("landArea"));
        // Очищаем текущее значение
        squareNameInput.clear();
        squareNameInput.sendKeys("45");

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement cattleNameInput = driver.findElement(By.name("cattleHeadcount"));
        // Очищаем текущее значение
        cattleNameInput.clear();
        cattleNameInput.sendKeys("3");

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        // Убрал это поле, возможно временно
        // WebElement taxNameInput = driver.findElement(By.name("taxesCollected"));
        // // Очищаем текущее значение
        // taxNameInput.clear();
        // taxNameInput.sendKeys("1256");

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // Пауза в 3 секунды
        Thread.sleep(1000);

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement reportElement = driver.findElement(By.cssSelector("input[value='Отправить отчет']"));
        reportElement.click();

        WebElement submitButtonExit = driver.findElement(By.cssSelector("input[class='btn text-white']"));
        submitButtonExit.click();

        // Пауза в 3 секунды
        // Thread.sleep(3000);

    }

    @Test
    public void testLandownerPopulation() throws InterruptedException {
        loginAsLandowner();
        driver.get("http://localhost:8080/courier/index");

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement linkElement = driver.findElement(By.linkText("Главная"));

        // Кликнуть по найденному элементу
        linkElement.click();

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        driver.get("http://localhost:8080/landowner/main");

        WebElement reportReadyInput = driver.findElement(By.cssSelector("button[type='submit']"));
        reportReadyInput.click();

        // Пауза в 3 секунды
        Thread.sleep(1000);

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement reportElement = driver.findElement(By.name("report"));
        reportElement.click();

        // Пауза в 3 секунды
        // Thread.sleep(3000);

    }

    @Test
    public void testApprovalLord() throws InterruptedException {
        loginAsLord();
        driver.get("http://localhost:8080/landowner/index");

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement linkElement = driver.findElement(By.linkText("Главная"));

        // Кликнуть по найденному элементу
        linkElement.click();

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        driver.get("http://localhost:8080/lord/main");

        WebElement reportReadyInput = driver.findElement(By.cssSelector("button[type='submit']"));
        reportReadyInput.click();

        // Пауза в 3 секунды
        Thread.sleep(1000);

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement reportElement = driver.findElement(By.cssSelector("input[value='Завершить']"));
        reportElement.click();

        // Пауза в 3 секунды
        // Thread.sleep(3000);

    }

    private void loginAsLord() throws InterruptedException {
        driver.get("http://localhost:8080/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("верховный_лорд");
        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("123455678");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        submitButton.click();
    }

    private void loginAsLandowner() throws InterruptedException {
        driver.get("http://localhost:8080/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("землевладелец");
        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("4534524524");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        submitButton.click();
    }

    private void loginAsCorier() throws InterruptedException {
        driver.get("http://localhost:8080/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("гонец");
        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("131231233");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        submitButton.click();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
