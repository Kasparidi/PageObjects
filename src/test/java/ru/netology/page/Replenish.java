package ru.netology.page;

import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class Replenish {
    public void transferMoney(String amount, DataHelper.CardInfo info) {
        $("[data-test-id='amount'] .input__control").setValue(amount);
        $("[data-test-id='from'] .input__control").setValue(info.getCardNumber());
        $("[data-test-id='action-transfer']").click();
    }
}
