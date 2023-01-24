package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
  private SelenideElement heading = $("[data-test-id=dashboard]");
  private static SelenideElement amountInput = $("[data-test-id='amount'] input");
  private static SelenideElement fromInput = $("[data-test-id='from'] input");
  private static SelenideElement transferHead = $("[data-test-id='action-transfer']");

  private static SelenideElement cardOutNumber = $("[data-test-id=from] input");
  private static SelenideElement transferButton = $("[data-test-id=action-transfer]");
  private static SelenideElement cancelButton = $("[data-test-id=action-cancel]");
  private static SelenideElement errorMessage = $("[data-test-id=error-notification]");


  public TransferPage() {
    heading.shouldBe(Condition.visible);
  }

  public static DashboardPage cardReplenishment(int amount, String number) {
    amountInput.setValue(String.valueOf(amount));
    fromInput.setValue(number);
    transferHead.click();
    return new DashboardPage();
  }

  public void cardErrorMessage(String amount, String cardNumber) {
    amountInput.setValue(amount);
    cardOutNumber.setValue(cardNumber);
    transferButton.click();
    errorMessage.shouldBe(Condition.visible);
  }
}
