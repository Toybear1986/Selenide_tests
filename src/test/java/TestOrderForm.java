import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestOrderForm {

  @Test
  void shouldSubmitRequest(){
    open("http://localhost:9999");
    SelenideElement form = $("form");
    form.$("[data-test-id=name] input").setValue("Василий Бонч-Бруевич");
    form.$("[data-test-id=phone] input").setValue("+79269998888");
    form.$("[data-test-id=agreement]").click();
    form.$(By.className("button")).click();
    $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
  }
}
