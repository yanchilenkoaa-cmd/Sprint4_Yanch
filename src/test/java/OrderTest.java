import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    private final String name;
    private final String secondName;
    private final String address;
    private final String stationShort;
    private final String stationFull;
    private final String phone;
    private final String day;
    private final String period;
    private final String comment;
    private final String buttonType;

    public OrderTest(String name, String secondName, String address,
                     String stationShort, String stationFull, String phone,
                     String day, String period, String comment, String buttonType) {

        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.stationShort = stationShort;
        this.stationFull = stationFull;
        this.phone = phone;
        this.day = day;
        this.period = period;
        this.comment = comment;
        this.buttonType = buttonType;
    }

    @Contract(value = " -> new", pure = true)
    @Parameterized.Parameters
    public static Object[] @NotNull [] getRentalData() {
        return new Object[][]{
                {"Анастасия", "Янч", "Богатырева, 23", "Сокол", "Сокольники", "+79095554433", "12", "трое суток", "Как можно скорее", "header"},
                {"Матвей", "Янч", "Ленина, 65", "Комсо", "Комсомольская", "+79626662211", "13", "двое суток", "Жду", "bottom"}
        };
    }

    @Test
// Оформление заказа на кнопку Заказать
    public void getOrder() {
        //открываем сайт
        mainPage.openPage();
        //проверяем и принимаем куки
        mainPage.acceptCookiesIfPresent();
        //нажимаем кнопку Заказать
        if ("header".equals(buttonType)) {
            mainPage.clickOrderButtonHeader();
        } else if ("bottom".equals(buttonType)) {
            mainPage.clickOrderButtonBottom();
        }
        //заполняем формы информация о пользователе
        userInformationPage.fillUserInfoPage(name, secondName, address, stationShort, stationFull, phone);
        //нажимаем кнопку Далее
        userInformationPage.clickNextButton();
        //заполняем данные аренды
        rentalDataPages.fillRentalDataPage(day, period, comment);
        //кликаем кнопку Заказать
        rentalDataPages.clickOrderButton();
        //подтверждаем оформление заказа
        rentalDataPages.clickConfirmOrderButton();
        //сравниваем ОР (заказ принят) и ФР
        Assert.assertTrue("Сообщение о принятом заказе не отображается", rentalDataPages.isOrderAccept());
    }
}