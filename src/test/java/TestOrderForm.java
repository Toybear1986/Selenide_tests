import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOrderForm {

  @Test
  void shouldSubmitRequest() {
    open("http://localhost:9999");
    SelenideElement form = $("form");
    form.$("[data-test-id=name] input").setValue("Василий Бонч-Бруевич");
    form.$("[data-test-id=phone] input").setValue("+79269998888");
    form.$("[data-test-id=agreement]").click();
    form.$(".button").click();
    $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
  }

  @Test
  void shouldValidateNameField() {
    open("http://localhost:9999");
    SelenideElement form = $("form");
    form.$("[data-test-id=name] input").setValue("1");
    form.$(".button").click();
    $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
  }

  @Test
  void nameFieldCouldNotBeEmpty() {
    open("http://localhost:9999");
    SelenideElement form = $("form");
    form.$("[data-test-id=phone] input").setValue("+79269998888");
    form.$(".button").click();
    $(".input_type_text .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
  }

  @Test
  void shouldValidatePhoneField() {
    open("http://localhost:9999");
    SelenideElement form = $("form");
    form.$("[data-test-id=name] input").setValue("Василий Бонч-Бруевич");
    form.$("[data-test-id=phone] input").setValue("+");
    form.$(".button").click();
    $(".input_type_tel .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
  }

  @Test
  void phoneFieldCouldNotBeEmpty() {
    open("http://localhost:9999");
    SelenideElement form = $("form");
    form.$("[data-test-id=name] input").setValue("Василий Бонч-Бруевич");
    form.$(".button").click();
    $(".input_type_tel .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
  }

  @Test
  void checkboxTextShouldBeRedIfNotMarked() {
    open("http://localhost:9999");
    SelenideElement form = $("form");
    form.$("[data-test-id=name] input").setValue("Василий Бонч-Бруевич");
    form.$("[data-test-id=phone] input").setValue("+79269998888");
    form.$(".button").click();
    assertEquals("rgba(255, 92, 92, 1)", $(".checkbox__text").getCssValue("color"));
  }
}
