import Pages.RentalFormPage;
import Pages.UserDetailsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import Pages.MainPage;

public class BaseTest {
    // Открыть браузер
    WebDriver driver;
    MainPage mainPage;
    UserDetailsPage userInformationPage;
    RentalFormPage rentalDataPages;
    Pages.FAQSection faqSection;

    @Before
    //для запуска выбранного браузера (по умолчанию это Хром)
    public void startUp(){
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("chrome")) {
            startBrowserChrome();
        } else if (browser.equals("firefox")) {
            startBrowserFirefox();
        }
        //создаем экземпляр каждой страницы( позволяет работать с элементами и методами страниц)
        mainPage = new MainPage(driver);
        userInformationPage = new UserDetailsPage(driver);
        rentalDataPages = new RentalFormPage(driver);
        faqSection = new Pages.FAQSection(driver);
    }
    //методы запуска браузеров
    public void startBrowserFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }
    public void startBrowserChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @After
    //закрываем браузер
    public void tearDown(){
        driver.quit();
    }
}