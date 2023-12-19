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
public class AddingLordTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        String chromeExecutablePath = ConfProperties.getProperty("chromebrouzer");
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setBinary(chromeExecutablePath);
        driver = new ChromeDriver(options);
    }

    @Test
    public void testCreateLord() throws InterruptedException {
        loginAsAdmin();
        driver.get("http://localhost:8080/admin/lord");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("верховный_лорд");

        // Пауза в 3 секунды
        //Thread.sleep(3000);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("123455678");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // Пауза в 3 секунды
        //Thread.sleep(3000);

    }

    @Test
    public void testCreateRegion() throws InterruptedException {
        loginAsAdmin();
        driver.get("http://localhost:8080/admin/lord");

        WebElement addDistrictLink = driver.findElement(By.linkText("Добавление округов"));
        addDistrictLink.click();

        driver.get("http://localhost:8080/admin/region");

        WebElement regionNameInput = driver.findElement(By.name("name"));
        regionNameInput.sendKeys("Васиостковский");

        // Пауза в 3 секунды
        //Thread.sleep(3000);

        // Находим <select> элемент
        WebElement selectElement = driver.findElement(By.cssSelector("select.form-control"));

        // Создаем объект Select
        Select select = new Select(selectElement);

        // Выбираем значение по тексту или по значению
        // Например, по тексту
        select.selectByVisibleText("верховный_лорд");

        // Пауза в 3 секунды
        //Thread.sleep(3000);

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

    }

    @Test
    public void testCreateDistrict() throws InterruptedException {
        loginAsAdmin();
        driver.get("http://localhost:8080/admin/lord");

        WebElement addDistrictLink = driver.findElement(By.linkText("Добавление округов"));
        addDistrictLink.click();

        driver.get("http://localhost:8080/admin/district");


        WebElement districtNameInput = driver.findElement(By.name("name"));
        districtNameInput.sendKeys("Восток");
        // Пауза в 3 секунды
        //Thread.sleep(3000);

        // Находим <select> элемент
        WebElement selectElement = driver.findElement(By.name("region"));

        // Создаем объект Select
        Select select = new Select(selectElement);

        // Выбираем значение по тексту или по значению
        // Например, по тексту
        select.selectByVisibleText("Васиостковский");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // Пауза в 3 секунды
        //Thread.sleep(3000);

    }



    private void loginAsAdmin() throws InterruptedException {
        driver.get("http://localhost:8080/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("ADMIN");
        // Пауза в 3 секунды
        //Thread.sleep(3000);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("ADMIN");

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
