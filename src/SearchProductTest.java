import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

class SearchProductTest {

	@Test
	void testSearchProduct() throws Exception {
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:9515"), new ChromeOptions());

		driver.get("https://www.notino.co.uk/");
		String productName = "Chanel Coco Mademoiselle";

		WebElement element = driver.findElement(By.name("exps"));
		element.sendKeys(productName);
		element.submit();

		WebElement item = driver.findElement(By.cssSelector("[data-product-code='60525']"));
		WebElement name = item.findElement(By.className("name"));
		String itemName = name.getText().replace("\n", " ");

		assertEquals(productName, itemName);

		WebElement itemLink = item.findElement(By.className("spc"));
		String itemURL = itemLink.getAttribute("href");

		driver.navigate().to(itemURL);
		assertTrue(driver.getTitle().startsWith(productName));

		driver.quit();
	}

}
