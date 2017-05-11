package com.gmail.maaaxk;
import org.openqa.selenium.By;
/**
 * Created by max on 11.05.17.
 */
public class Config {
    public String login = "";
    public String pass = "";
    public By elementLogin = By.id("login-email");
    public By elementPass = By.id("login-password");
    public By elementButtonLogin = By.id("login-submit");
    public By elementMyNetwork = By.id("mynetwork-nav-item");
    public By elementPageMyFriends = By.xpath("//a[text()='See all']");
    public By elementListMyFriends = By.xpath("//*[@class='mn-person-info__name Sans-17px-black-85%-semibold-dense']");
    public int numberForPage = 4;
    public String fileName = "friends.txt";
    public String nameSite = "https://www.linkedin.com";
    public String pathForChromeDriver = "/home/max/chromedriver/chromedriver";
}
