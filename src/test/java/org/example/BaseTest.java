package org.example;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class BaseTest {


      public static WebDriver wd;
    public static String textReject="РЕШЕНИЕ ПО КРЕДИТУ: ОТКАЗАНО";


    @BeforeClass
    public static void setup() {
         System.setProperty("webdriver.chrome.driver", TestData.getProperty("chromedriver"));
         wd = new ChromeDriver();
         wd.manage().window().maximize();
         wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         wd.get(TestData.getProperty("startpage")); }

@Test
   public void positive1(){
        calculate("testPositive_1");
        String actualRes=wd.findElement(By.id("resumeId")).getText();
        String actualSum=wd.findElement(By.id("sumId")).getText();
        Assert.assertEquals(actualRes, TestData.getProperty("expectedResPos_1"));
        Assert.assertEquals(actualSum, TestData.getProperty("expextedSumPos_1"));
}

    @Test
    public void positive2(){
        calculate("testPositive_2");
        String actualRes=wd.findElement(By.id("resumeId")).getText();
        String actualSum=wd.findElement(By.id("sumId")).getText();
        Assert.assertEquals(actualRes, TestData.getProperty("expectedResPos_2"));
        Assert.assertEquals(actualSum, TestData.getProperty("expextedSumPos_2"));
    }

    @Test
    public void positive3(){
        calculate("testPositive_3");
        String actualRes=wd.findElement(By.id("resumeId")).getText();
        String actualSum=wd.findElement(By.id("sumId")).getText();
        Assert.assertEquals(actualRes, TestData.getProperty("expectedResPos_3"));
        Assert.assertEquals(actualSum, TestData.getProperty("expextedSumPos_3"));
    }

    @Test
    public void positive4(){
        calculate("testPositive_4");
        String actualRes=wd.findElement(By.id("resumeId")).getText();
        String actualSum=wd.findElement(By.id("sumId")).getText();
        Assert.assertEquals(actualRes, TestData.getProperty("expectedResPos_4"));
        Assert.assertEquals(actualSum, TestData.getProperty("expextedSumPos_4"));
    }

    @Test
    public void positive5(){
        calculate("testPositive_5");
        String actualRes=wd.findElement(By.id("resumeId")).getText();
        String actualSum=wd.findElement(By.id("sumId")).getText();
        Assert.assertEquals(actualRes, TestData.getProperty("expectedResPos_5"));
        Assert.assertEquals(actualSum, TestData.getProperty("expextedSumPos_5"));
    }

    @Test
    public void reject1(){
        calculate("testReject_1");
        String actualRes=wd.findElement(By.id("resumeId")).getText();
        Assert.assertEquals(actualRes, textReject);

    }

    @Test
    public void reject2(){
        calculate("testReject_2");
        String actualRes=wd.findElement(By.id("resumeId")).getText();
        Assert.assertEquals(actualRes, textReject);

    }

    @Test
    public void reject3(){
        calculate("testReject_3");
        String actualRes=wd.findElement(By.id("resumeId")).getText();
        Assert.assertEquals(actualRes, textReject);

    }
    @Test
    public void reject4(){
        calculate("testReject_4");
        String actualRes=wd.findElement(By.id("resumeId")).getText();
        Assert.assertEquals(actualRes, textReject);

    }
    @Test
    public void reject5(){
        calculate("testReject_5");
        String actualRes=wd.findElement(By.id("resumeId")).getText();
        Assert.assertEquals(actualRes, textReject);

    }

    @Test
    public void reject6(){
        calculate("testReject_6");
        String actualRes=wd.findElement(By.id("resumeId")).getText();
        Assert.assertEquals(actualRes, textReject);

    }
    public void calculate(String dataTest) {
        fillForm(TestData.getProperty(dataTest));
        clickCalculate();


    }

    public void fillForm(String customerAge, String revenue, String amount, String period, String gender, String source, String rating, String purpose) {
        fillField(By.id("customerAgeId"), customerAge);
        fillField(By.id("revenueId"), revenue);
        fillField(By.id("amountCreditId"), amount);
        fillField(By.id("periodId"), period);
        selectValue(By.id("customerSexId"), gender);
        selectValue(By.id("sourceOfIncomeId"), source);
        selectValue(By.id("ratingId"), rating);
        selectValue(By.id("purposeCreditId"), purpose);
    }
    public void fillForm( String testValue) {

        String[] args = (testValue.split(", "));
        fillField(By.id("customerAgeId"), args[0]);
        fillField(By.id("revenueId"), args[1]);
        fillField(By.id("amountCreditId"), args[2]);
        fillField(By.id("periodId"), args[3]);
        selectValue(By.id("customerSexId"), args[4]);
        selectValue(By.id("sourceOfIncomeId"), args[5]);
        selectValue(By.id("ratingId"), args[6]);
        selectValue(By.id("purposeCreditId"), args[7]);
    }

    public void selectValue(By locator, String text) {
        new Select(wd.findElement(locator)).selectByVisibleText(text);
    }

    public void clickCalculate() {
        wd.findElement(By.id("calculate")).click();
    }

    public void fillField(By locator, String text) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    @AfterClass
    public static void tearDown() {

        wd.quit(); }
    }
