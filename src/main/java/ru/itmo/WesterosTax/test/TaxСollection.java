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

import java.util.List;

@FixMethodOrder(MethodSorters.JVM)
public class TaxСollection {

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
    public void testCreatingTax() throws InterruptedException {
        loginAsLord();
        driver.get("http://localhost:8080/landowner/index");

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement linkElement = driver.findElement(By.linkText("Налоги"));
        // Кликнуть по найденному элементу
        linkElement.click();

        driver.get("http://localhost:8080/taxType/index");

        WebElement addButton = driver.findElement(By.linkText("Добавить"));
        addButton.click();

        WebElement taxName = driver.findElement(By.id("name"));
        taxName.sendKeys("Подоходный налог");

        WebElement taxFormula = driver.findElement(By.id("formula"));
        taxFormula.sendKeys("1*2*2");

        WebElement taxDescription = driver.findElement(By.id("description"));
        taxDescription.sendKeys("Плати деньги");

        WebElement submitButton = driver.findElement(By.cssSelector("input[value='Сохранить']"));
        submitButton.click();

        // Пауза в 3 секунды
        Thread.sleep(1000);

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement linkElementMain = driver.findElement(By.linkText("Главная"));

        // Кликнуть по найденному элементу
        linkElementMain.click();

        driver.get("http://localhost:8080/lord/main");

        WebElement orderInput = driver.findElement(By.id("showAlertTax"));
        // Кликнуть по найденному элементу
        orderInput.click();

        // Пауза в 3 секунды
        Thread.sleep(1000);

        // Найти все элементы с заданным локатором
        List<WebElement> dataInputsOne = driver.findElements(By.id("dateBegin"));

        // Проверить, что список не пустой
        if (!dataInputsOne.isEmpty()) {
            // Выбрать второй элемент из списка (индекс 1)
            WebElement dataInputOne = dataInputsOne.get(1);
            // Ввести значение
            dataInputOne.sendKeys("12112023");
        } else {
            System.out.println("Элемент не найден");
        }

        // Найти все элементы с заданным локатором
        List<WebElement> dataInputsTwo = driver.findElements(By.id("dateEnd"));

        // Проверить, что список не пустой
        if (!dataInputsTwo.isEmpty()) {
            // Выбрать второй элемент из списка (индекс 1)
            WebElement dataInputOne = dataInputsTwo.get(1);
            // Ввести значение
            dataInputOne.sendKeys("12152023");
        } else {
            System.out.println("Элемент не найден");
        }

        // Найти все элементы с заданным локатором
        List<WebElement> commentInputs = driver.findElements(By.id("comment"));

        // Проверить, что список не пустой
        if (!commentInputs.isEmpty()) {
            // Выбрать второй элемент из списка (индекс 1)
            WebElement commentInput = commentInputs.get(1);
            // Ввести значение
            commentInput.sendKeys("Мне нужны деньги");
        } else {
            System.out.println("Элемент не найден");
        }

        // Пауза в 3 секунды
        Thread.sleep(1000);

        // Найти все элементы с заданным CSS селектором
        List<WebElement> submitButtons = driver.findElements(By.cssSelector("input[value='Отправить']"));

        // Проверить, что список не пустой
        if (!submitButtons.isEmpty()) {
            // Выбрать второй элемент из списка (индекс 1)
            WebElement submitButtonSend = submitButtons.get(1);

            // Кликнуть по выбранному элементу
            submitButtonSend.click();
        } else {
            System.out.println("Элемент не найден");
        }

    }

    @Test
    public void testCorierTax() throws InterruptedException {
        loginAsCorier();
        driver.get("http://localhost:8080/household/index");

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement reportElement = driver.findElement(By.cssSelector("input[value='Отправить подать']"));
        reportElement.click();
    }

    @Test
    public void testLandownerTax() throws InterruptedException {
        loginAsLandowner();
        driver.get("http://localhost:8080/courier/index");

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement linkElement = driver.findElement(By.linkText("Главная"));
        // Кликнуть по найденному элементу
        linkElement.click();

        driver.get("http://localhost:8080/landowner/main");

        WebElement reportReadyInput = driver.findElement(By.name("taxSubmit"));
        reportReadyInput.click();

        // Найти все элементы с заданным CSS селектором
        WebElement reportElement = driver.findElement(By.cssSelector("input[value='Отправить отчет']"));
        // Пауза в 3 секунды
        Thread.sleep(1000);
        reportElement.click();
    }

    @Test
    public void testLordTax() throws InterruptedException {
        loginAsLord();
        driver.get("http://localhost:8080/landowner/index");

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement linkElement = driver.findElement(By.linkText("Главная"));

        // Кликнуть по найденному элементу
        linkElement.click();

        driver.get("http://localhost:8080/lord/main");

        WebElement reportReadyInput = driver.findElement(By.name("taxSubmit"));
        reportReadyInput.click();

        // Пауза в 3 секунды
        Thread.sleep(1000);

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement reportElement = driver.findElement(By.cssSelector("input[value='Завершить']"));
        reportElement.click();
    }

    private void loginAsLord() throws InterruptedException {
        driver.get("http://localhost:8080/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("верховный_лорд");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("123455678");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        submitButton.click();
    }

    private void loginAsLandowner() throws InterruptedException {
        driver.get("http://localhost:8080/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("землевладелец");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("4534524524");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        submitButton.click();
    }

    private void loginAsCorier() throws InterruptedException {
        driver.get("http://localhost:8080/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("гонец");

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
