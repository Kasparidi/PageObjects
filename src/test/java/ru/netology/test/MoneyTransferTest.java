package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeAll
    static void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyFrom0002To0001() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val replenish = dashboard.replenish0001();
        replenish.transferMoney("10000", DataHelper.getCardNumberSecond());
        Assertions.assertEquals(20000, dashboard.getFirstCardBalance());
        Assertions.assertEquals(0, dashboard.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyFrom0001To0002() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val replenish = dashboard.replenish0002();
        replenish.transferMoney("10000",DataHelper.getCardNumberFirst());
        Assertions.assertEquals(10000, dashboard.getSecondCardBalance());
        Assertions.assertEquals(10000, dashboard.getFirstCardBalance());
    }

    @Test
    void shouldTransferMoreMoneyFrom0002To0001() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val replenish = dashboard.replenish0001();
        replenish.transferMoney("11000", DataHelper.getCardNumberFirst());
        $((WebElement) text("Недостаточно денег на счете"));
    }

}
