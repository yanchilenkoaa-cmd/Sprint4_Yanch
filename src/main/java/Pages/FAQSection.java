package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FAQSection {
    private WebDriver driver;
    //локаторы
   //ищем текст Вопросы о важном
    private By nameFaq = By.xpath("//div[text() = 'Вопросы о важном']");
    //ищем поля с вопросами
    private By questions = By.className("accordion__button");
    //ищем ответы
    private By answers = By.className("accordion__panel");

    //конструктор класса
    public FAQSection(WebDriver driver) {
        this.driver = driver;
    }
    //методы
    //находим элемент, прокручиваем вниз, ждем 8 сек.,ждем пока элемент ставится видимым
    public void scrollFaqSection() {
        WebElement element = driver.findElement(nameFaq);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameFaq));
    }
    //смотрим список всех вопросов сразу
    public List<WebElement> getAllQuestions() {
        return driver.findElements(questions);
    }
    //смотрим ответы
    public List<WebElement> getAllAnswers() {
        return driver.findElements(answers);
    }
    //получаем список всех вопросов, кликаем по каждому вопросу, ждем 8 сек.
    public void clickQuestion(int index){
        List<WebElement> questionsList = getAllQuestions();
        List<WebElement> answersList = getAllAnswers();
        questionsList.get(index).click();
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOf(answersList.get(index)));
    }
}