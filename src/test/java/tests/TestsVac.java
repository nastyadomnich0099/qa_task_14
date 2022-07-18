package tests;

import helpers.DriverUtils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class TestsVac extends tests.TestBase {
    private final String BASEURL = "https://rabota.by/";
    private final String SECOND_URL = "https://rabota.by/vacancies/uchitel";
    private final String BASE_TEXT = "Работа найдется для каждого";
    private final String SEARCH_VALUE = "Учитель";
    private final String VACANCY_NAME = "Учитель";

    private final String ENTERPRISE = "Гринхорнс";
    private final String INPUT_VALUE = "факультативных";


    @Test
    @DisplayName("Открываем сайт вакансий")
    void openVacancyPage() {

        step("Открываем сайт вакансий rabota.by", () -> {
            open(BASEURL);
        });

        step("Проверяем, что нужная страница открылась", () -> {
            $(".bloko-header-promo-3").shouldHave(Condition.text(BASE_TEXT));

        });
    }

    @Test
    @DisplayName("Ищем вакансию по тексту учитель")
    void searchForVacancy() {
            open(BASEURL);
            $("[id=a11y-search-input]").click();
            $("[id=a11y-search-input]").sendKeys(SEARCH_VALUE);
            $(".supernova-search-group__input").submit();
            $(".vacancy-serp-content").shouldHave(Condition.text(VACANCY_NAME));

        }


    @Test
    @DisplayName("Check work with Гринхорнс" )
    void checkCity() {
            open(BASEURL);
            $("[id=a11y-search-input]").click();
            $("[id=a11y-search-input]").sendKeys(SEARCH_VALUE);
            $(".supernova-search-group__input").submit();
        $(".vacancy-serp-item-body").shouldHave(Condition.text(ENTERPRISE ));
        }


    @Test
    @DisplayName("Check map of vacancy")
    void mapVacancy() {
        open(SECOND_URL);
        $("[data-qa='serp_settings__vacancy-map']").click();;
    }


    @Test
    @DisplayName("Исключить слова ")
    void omitWords() {
        step("Открываем сайт rabota.by", () -> {
            open(SECOND_URL);
            $("[data-qa='novafilters-excluded-text-input']").sendKeys(INPUT_VALUE);
            $("[data-qa='novafilters-excluded-text-input']").pressEnter();
        });
    }





}