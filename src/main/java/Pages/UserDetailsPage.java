package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserDetailsPage {
    private WebDriver driver;
    //локаторы
    //ищем поле Имя
    private By nameField = By.xpath(".//input[@placeholder = '* Имя']");
    //ищем поле Фамилия
    private By secondNameField = By.xpath(".//input[@placeholder = '* Фамилия']");
    //Ищем поле Адрес
    private By addressField = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    //ищем поля выборы станции
    private By stationField = By.xpath("//input[@placeholder='* Станция метро']");
    //ищем поля ввода телефона
    private By phoneField = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //ищем кнопку Дальше
    private By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //конструктор
    public UserDetailsPage(WebDriver driver) {
        this.driver = driver;
    }
    //методы
    //вводим значение в поле имя
    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    //вводим фамилию
    public void setSecondNameField(String secondName) {
        driver.findElement(secondNameField).sendKeys(secondName);
    }
    //вводим адрес
    public void setAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    //вводим краткое название станции, ждем появления полного варианта, кликаем по ней
    public void setStationField(String station, String fullStationName) {
        driver.findElement(stationField).sendKeys(station);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By option = By.xpath("//button[contains(@class,'select-search__option') and contains(.,'" + fullStationName + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(option));
        driver.findElement(option).click();
    }
    //вводим номер телефона
    public void setPhoneField(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }
    //кликаем по кнопке Дальше
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    //для одновременного заполнения всех полей формы
    public void fillUserInfoPage(String name, String secondName, String address, String stationShort,
                                 String stationFull, String phone) {
        setNameField(name);
        setSecondNameField(secondName);
        setAddressField(address);
        setStationField(stationShort, stationFull);
        setPhoneField(phone);
    }
}