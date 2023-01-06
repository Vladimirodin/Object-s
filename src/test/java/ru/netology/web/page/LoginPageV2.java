package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPageV2 {
  private SelenideElement heading = $("[data-test-id=dashboard]");
  private static SelenideElement amountInput = $("[data-test-id='amount'] input");
  private static SelenideElement fromInput = $("[data-test-id='from'] input");
  private static SelenideElement transferHead = $("[data-test-id='action-transfer']");


  public LoginPageV2() {
    heading.shouldBe(Condition.visible);
  }

  public DashboardPage cardReplenishment(int amount, String number) {
    amountInput.setValue(String.valueOf(amount));
    fromInput.setValue(number);
    transferHead.click();
    return new DashboardPage();
  }
}
