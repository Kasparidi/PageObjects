package ru.netology.page;

import static com.codeborne.selenide.Selenide.$;

public class Replenish0001 {
    public void transferMoney() {
        $("[data-test-id='amount'] .input__control").setValue("10000");
        $("[data-test-id='from'] .input__control").setValue("5559 0000 0000 0002");
        $("[data-test-id='action-transfer']").click();
    }

    public void transferMore() {
        $("[data-test-id='amount'] .input__control").setValue("11000");
        $("[data-test-id='from'] .input__control").setValue("5559 0000 0000 0002");
        $("[data-test-id='action-transfer']").click();
    }
}
