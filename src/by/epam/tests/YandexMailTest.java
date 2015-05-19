package by.epam.tests;

import java.util.concurrent.TimeUnit;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import by.epam.pages.HomePage;
import by.epam.pages.MainPage;

public class YandexMailTest {
	
	public static final String UserLogin = "ludik.ludik2015";
	public static final String UserPsw = "Qwerty111";
	public static final String Subject = "test module 5";
	public static final String Body = "Hello!\n\nThis is test of Module 5.\n\nBye";
	private WebDriver driver;

	@BeforeTest
	public void openDriver(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testYandexMailBox(){
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		mainPage.loginToMail(UserLogin, UserPsw);
		HomePage homePage = new HomePage(driver);
		homePage.getLoggedUserName();
		Assert.assertTrue(homePage.getLoggedUserName().equals("ludik.ludik2015@yandex.ru"), "Verification Failed: You are not logged under user "+UserLogin);
//		System.out.println("Logged User = "+homePage.getLoggedUserName());
		homePage.writeNewEmail(Subject, Body);
		homePage.openDraftFolder();
		homePage.saveEmailToDraft();
		Assert.assertTrue(homePage.getTitleOfEmail().equals(Subject), "Verification Failed: No email in DRAFT folder with subject= "+Subject);
		homePage.sendNewEmail();
		homePage.openDraftFolder();
		Assert.assertFalse(homePage.getTitleOfEmail().equals(Subject), "Verification Failed: Email is still in DRAFT folder with subject= "+Subject);
		homePage.openSentFolder();
		Assert.assertTrue(homePage.getTitleEmailInSentFolder().equals(Subject), "Verification Failed: No email in SENT folder with subject= "+Subject);
		homePage.quitFromMailBox();
	}
	
	@AfterTest
	public void closeDriver(){
		driver.close();
	}
}
