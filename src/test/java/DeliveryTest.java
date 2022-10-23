import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Keys;
import util.ScreenShooterReportPortalExtension;
import org.junit.jupiter.api.extension.Extension;


import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static sun.java2d.marlin.MarlinUtils.logInfo;

import util.LoggingUtils;
@ExtendWith({ScreenShooterReportPortalExtension.class})

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 3;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 5;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $x("//*[@data-test-id='city']//input").setValue(validUser.getCity());
        logInfo("В поле ввода введён город " + validUser.getCity());
        $x("//*[@data-test-id='date']//input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input").setValue(firstMeetingDate);
        logInfo("В поле ввода введена дата " + firstMeetingDate);
        $x("//*[@data-test-id='name']//input").setValue(validUser.getName());
        logInfo("В поле ввода введено имя " + validUser.getName());
        $x("//*[@data-test-id='phone']//input").setValue(validUser.getPhone());
        logInfo("В поле ввода введен номер телефона " + validUser.getPhone());
        $x("//*[@data-test-id='agreement']//span").click();
        logInfo("Выполнен клик по чекбоксу");
        $x("//span[@class='button__text']").click();
        logInfo("Выполнен клик по кнопке запланировать");
        $x("//*[@data-test-id='success-notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $x("//*[@class='notification__content']").shouldHave(Condition.exactText("Встреча успешно запланирована на " + firstMeetingDate));
        logInfo("Встреча успешно запланирована на " + firstMeetingDate);
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(secondMeetingDate);
        logInfo("В поле ввода введена дата " + secondMeetingDate);
        $x("//span[@class='button__text']").click();
        logInfo("Выполнен клик по кнопке запланировать");
        $x("//*[@data-test-id='success-notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $x("//*[@class='notification__content']").shouldHave(Condition.exactText("Встреча успешно запланирована на " + secondMeetingDate));
        logInfo("Встреча успешно запланирована на " + secondMeetingDate);
    }
}
