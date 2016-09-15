package com.appium.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class ParallelTest {

	public AndroidDriver<MobileElement> driver;

	public static DesiredCapabilities cap;
	SimpleDateFormat current_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String initial_value = "未登录", input_value = "apple00", before_login,
			afeter_login;

	@BeforeTest(alwaysRun = true)
	@Parameters({ "port", "device" })
	public void testsetup(String port, String device)
			throws MalformedURLException, InterruptedException {

		//String path = "D://Appium//apkfiles//com.gorillalogic.monkeytalk.demo1.apk";
		//File file = new File(path);

		cap = new DesiredCapabilities();

		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0.1");

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability("noReset", "true");

		//cap.setCapability(MobileCapabilityType.APP, file);
		cap.setCapability("appPackage", "com.updrv.lifecalendar");
		cap.setCapability("appActivity", ".activity.MainActivity");

		driver = new AndroidDriver<>(new URL("http://localhost:" + port
				+ "/wd/hub"), cap);

		System.out.println("session id is---" + driver.getSessionId());

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(enabled = false)
	public void ValidLoginTest() throws InterruptedException {

		driver.findElementById("com.gorillalogic.monkeytalk.demo1:id/login_usr")
				.sendKeys("admin");

		Thread.sleep(5000L);
		driver.findElementById("com.gorillalogic.monkeytalk.demo1:id/login_pwd")
				.sendKeys("admin");

		driver.tap(
				1,
				driver.findElementById("com.gorillalogic.monkeytalk.demo1:id/login_btn"),
				1);

		String name = driver.findElementById(
				"com.gorillalogic.monkeytalk.demo1:id/logout_txt").getText();

		Assert.assertTrue(name.contains("admin"));

	}

	@Test(enabled = false)
	public void InvalidValidLoginTest() throws InterruptedException {

		List<MobileElement> tabs = driver
				.findElementsById("com.gorillalogic.monkeytalk.demo1:id/tab_txt");
		for (int i = 0; i <= 3; i++) {
			if (tabs.get(i).getText().contains("forms")) {
				tabs.get(i).click();
				driver.findElementById(
						"com.gorillalogic.monkeytalk.demo1:id/forms_checkbox")
						.click();

				driver.findElementById(
						"com.gorillalogic.monkeytalk.demo1:id/forms_radio_c")
						.click();
			}
			if (tabs.get(i).getText().contains("web")) {
				tabs.get(i).click();
				driver.findElementByAccessibilityId("Hello!").clear();
				driver.findElementByAccessibilityId("Hello!").sendKeys(
						"this is arvind");
				driver.findElementByAccessibilityId("B").click();
				driver.findElementByAccessibilityId("GO!").click();

			}

		}

	}

	@Test
	public void Login() throws InterruptedException {
		WebElement d = driver
				.findElement(By
						.id("com.updrv.lifecalendar:id/lin_menu_main_personal_account"));
		d.click();
		// 判断是否为登录状态
		before_login = driver
				.findElement(
						By.id("com.updrv.lifecalendar:id/tv_personal_account_user_name"))
				.getText();// 适用于索尼手机
		// 用户未登录直接进入注册页面登录
		if (before_login.equals(initial_value)) {
			System.out.println("用户未登录进入登录页面");
			WebElement account = driver.findElement(By
					.id("com.updrv.lifecalendar:id/account_linear"));
			account.click();
			WebElement user_accounts = driver.findElement(By
					.id("com.updrv.lifecalendar:id/user_accounts"));
			user_accounts.sendKeys(input_value);
			WebElement user_password = driver.findElement(By
					.id("com.updrv.lifecalendar:id/user_password"));
			user_password.sendKeys("123456");

			WebElement btn_login = driver.findElement(By
					.id("com.updrv.lifecalendar:id/btn_login"));
			btn_login.click();
			// Thread.sleep(2000);
			afeter_login = driver
					.findElement(
							By.id("com.updrv.lifecalendar:id/tv_personal_account_user_name"))
					.getText();
			System.out.print(current_time.format(new Date()) + " ");
			System.out.println("定位用户名成功状态为：" + afeter_login + " ");

			if (afeter_login.equals(input_value)) {
				System.out.print(current_time.format(new Date()) + " ");
				System.out.println("当前用户登录状态为：登录成功");

			} else if (afeter_login.equals(initial_value)) {
				System.out.print(current_time.format(new Date()) + " ");
				System.out.println("用户未登录");

			}
		} else {
			System.out.println("用户已登录退出登录再登录");
			WebElement e = driver.findElement(By
					.id("com.updrv.lifecalendar:id/account_linear"));
			e.click();
			WebElement e1 = driver.findElement(By
					.id("com.updrv.lifecalendar:id/account_linear"));
			e1.click();

			List<MobileElement> textFieldsList = driver
					.findElementsByClassName("android.widget.RelativeLayout");
			textFieldsList.get(4).click();
			WebElement e2 = driver.findElement(By
					.id("com.updrv.lifecalendar:id/dialog_text2"));
			e2.click();
			WebElement account = driver.findElement(By
					.id("com.updrv.lifecalendar:id/account_linear"));
			account.click();
			WebElement user_accounts = driver.findElement(By
					.id("com.updrv.lifecalendar:id/user_accounts"));
			user_accounts.sendKeys(input_value);
			WebElement user_password = driver.findElement(By
					.id("com.updrv.lifecalendar:id/user_password"));
			user_password.sendKeys("123456");

			WebElement btn_login = driver.findElement(By
					.id("com.updrv.lifecalendar:id/btn_login"));
			btn_login.click();
			afeter_login = driver
					.findElement(
							By.id("com.updrv.lifecalendar:id/tv_personal_account_user_name"))
					.getText();
			System.out.print(current_time.format(new Date()) + " ");
			System.out.println("定位用户名成功状态为：" + afeter_login + " ");
			if (afeter_login.equals(input_value)) {
				System.out.print(current_time.format(new Date()) + " ");
				System.out.println("当前用户登录状态为：登录成功");

			} else if (afeter_login.equals(initial_value)) {
				System.out.print(current_time.format(new Date()) + " ");
				System.out.println("用户未登录");

			}
		}

	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

}
