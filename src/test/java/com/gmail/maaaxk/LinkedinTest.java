package com.gmail.maaaxk;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static junit.framework.TestCase.fail;

/**
 * Created by max on 02.05.17.
 */
public class LinkedinTest {

    private static WebDriver driver;
    List<WebElement> listFriends;
    private static Config config;

    @BeforeClass
    public static void setup(){
        config = new Config();
        System.setProperty("webdriver.chrome.driver", config.pathForChromeDriver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(config.nameSite);
    }

    @Test
    public void findFriends() {
        try {
            Actions actions = new Actions(driver);
            loginToSite();
            //go to page "My Network"
            driver.findElement(config.elementMyNetwork).click();
            driver.findElement(config.elementPageMyFriends).click();
            TimeUnit.SECONDS.sleep(3);
            //read list friends
            for(int i=0; i<=config.numberForPage; i++){
                listFriends = driver.findElements(config.elementListMyFriends);
                int numberFound = listFriends.size();
                actions.moveToElement(listFriends.get(numberFound-1));
                actions.perform();
                TimeUnit.SECONDS.sleep(3);
            }
            printNameFriends();
            saveFile(listFriends);
        } catch (NoSuchElementException e) {
            fail("Element not found!!");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.close();

        }
    }

    private void loginToSite(){
        try {
            driver.findElement(config.elementLogin).sendKeys(config.login);
            driver.findElement(config.elementPass).sendKeys(config.pass);
            driver.findElement(config.elementButtonLogin).click();
        } catch (NoSuchElementException e) { }
    }

    private void printNameFriends(){
        for (int i=0; i<listFriends.size(); i++){
            System.out.println(listFriends.get(i).getText());
        }
    }

    private void saveFile(List<WebElement> listForSave) {
        try {
            FileWriter writer = new FileWriter(config.fileName, false);
            for (int i=0; i<listForSave.size(); i++){
                writer.write(listForSave.get(i).getText() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void endTest(){
        driver.quit();
    }
}
