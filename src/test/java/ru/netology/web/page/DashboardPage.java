package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
  private SelenideElement heading = $("[data-test-id=dashboard]");
  private ElementsCollection cards = $$(".list__item div");
  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";

  public DashboardPage() {
    heading.shouldBe(visible);
  }

  public int getFirstCardBalance() {
    var text = cards.first().text();
    return extractBalance(text);
  }

  public int getSecondCardBalance() {
    var text = cards.last().text();
    return extractBalance(text);
  }

  private int extractBalance(String text) {
    var start = text.indexOf(balanceStart);
    var finish = text.indexOf(balanceFinish);
    var value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }

  public TransferPage selectCardButton(String cardId) {
    SelenideElement element = $("[data-test-id='" + cardId + "']");
    element.find("button[data-test-id=action-deposit]").click();
    return new TransferPage();
  }
}