package ru.netology.page;

import static com.codeborne.selenide.Selenide.$;

public class Replenish0002 {
    public void transferMoney() {
        $("[data-test-id='amount'] .input__control").setValue("10000");
        $("[data-test-id='from'] .input__control").setValue("5559 0000 0000 0001");
        $("[data-test-id='action-transfer']").click();
    }

}
