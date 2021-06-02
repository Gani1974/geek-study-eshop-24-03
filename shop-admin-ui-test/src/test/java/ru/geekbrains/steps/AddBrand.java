package ru.geekbrains.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.geekbrains.DriverInitializer;

public class AddBrand {

        private WebDriver webDriver;

    @Given("^I open chrome browser$")
        public void iOpenChromeBrowser() throws Throwable {
            webDriver = DriverInitializer.getDriver();
        }

        @When("^I navigate to the login page$")
        public void iNavigateToLoginHtmlPage() throws Throwable {
            webDriver.get(DriverInitializer.getProperty("login.url"));
        }

        @When("^I click on the login button$")
        public void iClickOnLoginButton() throws Throwable {
            WebElement webElement = webDriver.findElement(By.id("btn-login"));
            webElement.click();
        }

        @When("^I provide the username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
        public void iProvideUsernameAsAndPasswordAs(String username, String password) throws Throwable {
            WebElement webElement = webDriver.findElement(By.id("inp-username"));
            webElement.sendKeys(username);
            Thread.sleep(2000);
            webElement = webDriver.findElement(By.id("inp-password"));
            webElement.sendKeys(password);
            Thread.sleep(2000);
        }

        @When("^I navigate to brands page$")
        public void iNavigateToBrandsPage() throws InterruptedException {
            webDriver.get(DriverInitializer.getProperty("brands.url"));
            Thread.sleep(2000);
        }

        @And("^I click on create new brand button$")
        public void iClickOnCreateNewBrandButton() throws InterruptedException {
            WebElement webElement = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/form/button"));
            webElement.click();
            Thread.sleep(2000);
        }

        @And("^I provide brand name$")
        public void iProvideNewBrandName() throws InterruptedException {
            WebElement webElement = webDriver.findElement(By.id("name"));
            webElement.sendKeys("Brand 6");
            Thread.sleep(2000);
        }

        @And("^I click on submit button$")
        public void iClickOnSubmitButton() throws InterruptedException {
            WebElement webElement = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div/div[2]/button"));
            webElement.click();
            Thread.sleep(2000);
        }

        @When("^Open the dropdown menu$")
        public void openDropDownMenu() throws InterruptedException {
            WebElement webElement = webDriver.findElement(By.id("logged-in-username"));
            Thread.sleep(1000);
            webElement.click();
            Thread.sleep(10000);
        }

        @When("^click the logout button$")
        public void clickLogoutButton() {
            WebElement webElement = webDriver.findElement(By.id("link-logout"));
            webElement.click();
        }

        @Then("^user has logged out$")
        public void userLoggedOut() {
            webDriver.findElement(By.id("inp-username"));
            webDriver.findElement(By.id("inp-password"));
        }

        @After
        public void quitBrowser() {
            webDriver.quit();
        }
    }
