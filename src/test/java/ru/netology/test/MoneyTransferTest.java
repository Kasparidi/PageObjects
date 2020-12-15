package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
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
        verificationPage.validVerify(verificationCode);
        val replenish0001 = DashboardPage.replenish0001();
        replenish0001.transferMoney();
        val dashboard = new DashboardPage();
        int excepted = 20000;
        int actual = dashboard.getFirstCardBalance();
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void shouldTransferMoneyFrom0001To0002() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val replenish0002 = DashboardPage.replenish0002();
        replenish0002.transferMoney();
        val dashboard = new DashboardPage();
        int excepted = 10000;
        int actual = dashboard.getSecondCardBalance();
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void shouldTransferMoreMoneyFrom0002To0001() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val replenish0001 = DashboardPage.replenish0001();
        replenish0001.transferMore();
        val dashboard = new DashboardPage();
        System.out.println(dashboard.getFirstCardBalance());
        System.out.println(dashboard.getSecondCardBalance());
    }

}
