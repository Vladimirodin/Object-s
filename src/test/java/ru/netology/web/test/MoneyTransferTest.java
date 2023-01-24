package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.DataHelper.getFirstCardInfo;
import static ru.netology.web.data.DataHelper.getSecondCardInfo;


public class MoneyTransferTest {
  LoginPage loginPage;

  @BeforeEach
  void setup() {
    loginPage = open("http://localhost:9999", LoginPage.class);
    Configuration.holdBrowserOpen = true;
    Configuration.startMaximized = true;
  }

  @Test
  void moneyTransferFirstToSecond() {
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    var dashboardPage = verificationPage.validVerify(verificationCode);
    var firstCardInfo = getFirstCardInfo();
    var secondCardInfo = getSecondCardInfo();
    int amountInput = 1000;
    var firstCardBalanceStart = dashboardPage.getFirstCardBalance() - amountInput;
    var secondCardBalanceStart = dashboardPage.getSecondCardBalance() + amountInput;
    var TransferPage = dashboardPage.selectCardButton(secondCardInfo.getTestId());
    dashboardPage = TransferPage.cardReplenishment(Integer.parseInt(String.valueOf(amountInput)), String.valueOf(firstCardInfo));
    var firstCardBalanceFinish = dashboardPage.getFirstCardBalance();
    var secondCardBalanceFinish = dashboardPage.getSecondCardBalance();
    Assertions.assertEquals(firstCardBalanceStart, firstCardBalanceFinish);
    Assertions.assertEquals(secondCardBalanceStart, secondCardBalanceFinish);
  }

  @Test
  void moneyTransferSecondToFirst() {
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    var dashboardPage = verificationPage.validVerify(verificationCode);
    var firstCard = getFirstCardInfo();
    var secondCard = getSecondCardInfo();
    int amountInput = 100;
    var firstCardBalanceStart = dashboardPage.getFirstCardBalance() + amountInput;
    var secondCardBalanceStart = dashboardPage.getSecondCardBalance() - amountInput;
    var TransferPage = dashboardPage.selectCardButton(firstCard.getTestId());
    dashboardPage = TransferPage.cardReplenishment(Integer.parseInt(String.valueOf(amountInput)), String.valueOf(secondCard));
    var firstCardBalanceFinish = dashboardPage.getFirstCardBalance();
    var secondCardBalanceFinish = dashboardPage.getSecondCardBalance();
    Assertions.assertEquals(firstCardBalanceStart, firstCardBalanceFinish);
    Assertions.assertEquals(secondCardBalanceStart, secondCardBalanceFinish);
  }
}