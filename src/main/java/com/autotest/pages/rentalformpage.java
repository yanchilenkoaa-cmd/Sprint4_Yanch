package com.autotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class rentalformpage {
    private WebDriver driver;
    //локаторы
    //ищем поле с датой
    private By orderDate = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    //ищем поле выбора срока аренды
    private By rentalPeriod = By.xpath(".//div[@class = 'Dropdown-control']");
    //ищем поля выбора цвета
    private By blackScooterColour = By.xpath(".//label[@for = 'black']");
    private By greyScooterColour = By.xpath(".//label[@for = 'grey']");
    //ищем поле комментарий
    private By commentField = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    //ищем кнопку заказать (вторая страница)
    private By confirmOrderButton = By.xpath("(//button[text() = 'Заказать'])[2]");
    //ищем окно подтверждения
    private By modalConfirmOrder = By.xpath("//div[contains(@class, 'Order_Modal')]");
    //ищем кнопку Да
    private By confirmYesButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(text(), 'Да')]");
    //ищем окно подтверждения Заказ оформлен
    private By orderAcceptHeader = By.xpath("//div[contains(@class,'Order_ModalHeader') and text()='Заказ оформлен']");

    //ищем конкретный день в календаре
    private By calendarDay(String day) {
        return By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + day + "']");
    }

    //конструктор
    public rentalformpage(WebDriver driver) {
        this.driver = driver;
    }

    //методы
    //открываем календарь, выбираем дату
    public void setOrderDate(String day) {
        driver.findElement(orderDate).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(calendarDay(day)));
        driver.findElement(calendarDay(day)).click();
    }

    //нажимаем на поле аренды, ждем выпадающий список, выбираем
    public void setRentalPeriod(String period) {
        driver.findElement(rentalPeriod).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By periodList = By.xpath("//div[contains(@class,'Dropdown-option') and (text()='" + period + "')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(periodList));
        driver.findElement(periodList).click();
    }

    //методы кликают на нужный цвет самоката
    public void setGreyScooterColour() {
        driver.findElement(greyScooterColour).click();
    }

    public void setBlackScooterColour() {
        driver.findElement(blackScooterColour).click();
    }

    //вводит текст комментария
    public void setCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    //кликаем по кнопке заказа
    public void clickOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }

    //ждём появления окна подтверждения и кликаем на кнопку Да
    public void clickConfirmOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalConfirmOrder));
        wait.until(ExpectedConditions.elementToBeClickable(confirmYesButton));
        driver.findElement(confirmYesButton).click();
    }

    //проверяем, что сообщение о успешном оформлении заказа отображается
    public boolean isOrderAccept() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(orderAcceptHeader));
        return successMessage.isDisplayed();
    }

    //объединяет вызовы методов, чтобы заполнить всю форму за один шаг
    public void fillRentalDataPage(String day, String period, String comment) {
        setOrderDate(day);
        setRentalPeriod(period);
        setGreyScooterColour();
        setCommentField(comment);
    }
}