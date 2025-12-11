import com.autotest.pages.rentalformpage;
import com.autotest.pages.userdetailspage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.autotest.pages.mainpage;
import com.autotest.pages.faqsection;

public class basetest {
    // Открыть браузер
    WebDriver driver;
    mainpage mainPage;
    userdetailspage userInformationPage;
    rentalformpage rentalDataPages;
    faqsection faqSection;

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
        mainPage = new mainpage(driver);
        userInformationPage = new userdetailspage(driver);
        rentalDataPages = new rentalformpage(driver);
        faqSection = new faqsection(driver);
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