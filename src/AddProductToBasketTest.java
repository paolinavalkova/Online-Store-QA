import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

class AddProductToBasketTest {

	@Test
	void testProductCheckout() throws MalformedURLException, InterruptedException {
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:9515"), new ChromeOptions());
		driver.get("https://www.notino.co.uk/chanel/coco-mademoiselle-eau-de-parfum-for-women/");

		WebElement priceContainer = driver.findElement(By.id("pd-price"));
		String price = priceContainer.getText().replace("\n", "");

		WebElement addToBasketButton = driver.findElement(By.id("pd-buy-button"));
		addToBasketButton.click();

		WebElement goToBasketLink = driver.findElement(By.xpath("//*[text()='Go to shopping basket']"))
				.findElement(By.xpath("../.."));
		String goToBasketURL = goToBasketLink.getAttribute("href");

		driver.navigate().to(goToBasketURL);

		WebElement basketProductPrice = driver.findElement(By.cssSelector("td.price-subtotal.right"));

		assertEquals(price, basketProductPrice.getText());

		WebElement proceedButton = driver.findElement(By.id("submit_next_2b"));
		new Actions(driver).sendKeys(Keys.PAGE_DOWN).perform();

		proceedButton.click();
		assertTrue(driver.getTitle().startsWith("Enter e-mail"));

		driver.quit();

	}

}
