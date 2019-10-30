import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

class RegisterNewUserTest {

	@Test
	void testRegisterForm() throws Exception {

		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:9515"), new ChromeOptions());

		driver.get("https://www.notino.co.uk/");
		WebElement userIcon = driver.findElement(By.xpath("//*[@id=\"outer-wrap\"]/div[2]/div/div[2]/div/a"));
		userIcon.click();

		WebElement registerButton = driver.findElement(By.id("ui-id-2"));
		registerButton.click();

		WebElement emailBox = driver.findElement(By.id("register-email"));
		String emailBoxType = emailBox.getAttribute("type");
		assertEquals("email", emailBoxType);

		WebElement registerNowButton = driver
				.findElement(By.cssSelector("#registrace > div.col-h-l > fieldset > p.buttons > button"));
		new Actions(driver).sendKeys(Keys.PAGE_DOWN).perform();
		assertTrue(registerNowButton.isDisplayed());

		driver.quit();

	}
}
