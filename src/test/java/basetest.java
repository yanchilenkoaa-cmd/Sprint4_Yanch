import com.autotest.pages.RentalFormPage;
import com.autotest.pages.UserDetailsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.autotest.pages.MainPage;
import com.autotest.pages.FaqSection;

public class BaseTest {
    // Открыть браузер
    WebDriver driver;
    MainPage mainPage;
    UserDetailsPage userInformationPage;
    RentalFormPage rentalDataPages;
    FaqSection faqSection;

    @Before
    //для запуска выбранного браузера (по умолчанию это Хром)
    public void startUp(){
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("chrome")) {
            startBrowserChrome();
            driver.manage().window().maximize(); //полноэкранный режим
        } else if (browser.equals("firefox")) {
            startBrowserFirefox();
            driver.manage().window().maximize(); //полноэкранный режим
        }
        //создаем экземпляр каждой страницы( позволяет работать с элементами и методами страниц)
        mainPage = new MainPage(driver);
        userInformationPage = new UserDetailsPage(driver);
        rentalDataPages = new RentalFormPage(driver);
        faqSection = new FaqSection(driver);
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