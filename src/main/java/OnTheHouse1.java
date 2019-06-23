import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.List;

/**
 * Created by jingfeiyang on 17/3/11.
 */
public class OnTheHouse1 {
    public static void main(String[] args) throws Exception {
        WebDriver driver;
        System.setProperty("phantomjs.binary.path","/usr/local/bin/phantomjs");
        driver = new PhantomJSDriver();


//        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
//        driver = new ChromeDriver();


        //driver = new HtmlUnitDriver();


        driver.get("http://www.onthehouse.com.au/real_estate/vic/melbourne_3000");

        //System.out.println(driver.getPageSource());

        //Thread.sleep(5000);

        //List<WebElement> houses = driver.findElements(By.className("property-link"));
        //List<WebElement> houses = driver.findElements(By.className("property-attribute"));
        //List<WebElement> houses = driver.findElements(By.xpath("//a[@class='property-attribute property-attribute-bed ng-binding']"));
        //List<WebElement> houses = driver.findElements(By.cssSelector("li.property-attribute.property-attribute-bed.ng-binding"));
        List<WebElement> houses = driver.findElements(By.className("property-type"));



        int count = 0;
        for(WebElement house : houses)
        {
            //System.out.println(house.getText());

            System.out.println(house.getText());

//            String houseAddress = house.getText().replace("\n"," ");
//            System.out.println(houseAddress);
//
            count++;
//
//
//            //List<WebElement> houseLinks = house.findElements(By.tagName("href"));
//
//            System.out.println(house.getAttribute("href"));
//
//
//            System.out.println();
//            System.out.println();
//            System.out.println();
//            System.out.println();
//            System.out.println();


        }

        System.out.println(count);
    }
}
