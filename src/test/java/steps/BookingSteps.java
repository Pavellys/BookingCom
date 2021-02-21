package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class BookingSteps {
    String city;

    @Given("User is looking for {string} city")
    public void userIsLookingForCity(String city) {
        this.city = city;
    }

    @When("User does search")
    public void userDoesSearch() {
        open("https://www.booking.com/");
        $(By.id("ss")).sendKeys(city);
        $(".sb-searchbox__button").click();
    }

    @Then("Hotel {string} should be on the first page")
    public void hotelShouldBeOnTheFirstPage(String hotel) {
        ArrayList<String> hotelsNames = new ArrayList<>();
        for(SelenideElement element : $$(".sr-hotel__name")) {
            hotelsNames.add(element.getText());
        }
        Assert.assertTrue(hotelsNames.contains(hotel));
    }

    @And("Rating of the hotel {string} is {string}")
    public void ratingOfTheHotel(String hotel, String rate){
        Assert.assertEquals($(By.xpath(String.format("//*[contains(@class, 'sr-hotel__name') and contains(text(), '%s')]"
        + "/ancestor::*[contains(@class, 'sr_item_content_slider_wrapper')]" +
                "//*[@class='bui-review-score__badge']", hotel))).getText(), rate);
    }
}
