import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.List;

/**
 * Created by jingfeiyang on 17/3/14.
 */
public class HouseAddressLinksLists {
    public static void main(String[] args) {
        WebDriver driver;
        System.setProperty("phantomjs.binary.path","/usr/local/bin/phantomjs");
        driver = new PhantomJSDriver();

        driver.get("http://www.onthehouse.com.au/real_estate/vic/melbourne_3000");

        try
        {
            List<WebElement> houses = driver.findElements(By.className("property-link"));


            int count = 0;
            for(WebElement house : houses)
            {
                //System.out.println(house.getText());


                try
                {
                    String houseAddress = house.getText().replace("\n"," ");
                    System.out.println(houseAddress);

                    count++;
                }
                catch (Exception e2)
                {
                    e2.printStackTrace();
                    System.out.println("e2 is wrong, get and print house address part is wrong");
                }

                //List<WebElement> houseLinks = house.findElements(By.tagName("href"));

                try
                {
                    System.out.println(house.getAttribute("href"));
                }
                catch (Exception e3)
                {
                    e3.printStackTrace();
                    System.out.println("e3 is wrong, get and print house network link part is wrong");
                }

                System.out.println();

            }

            System.out.println(count);

        }
        catch (Exception e1)
        {
            e1.printStackTrace();
            System.out.println("e1 wrong, get house lists part has benn wrong");
        }

        driver.quit();
    }
}
