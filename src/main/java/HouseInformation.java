import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * Created by jingfeiyang on 17/3/14.
 */
public class HouseInformation {
    public static void main(String[] args) {


        WebDriver driver;
        System.setProperty("phantomjs.binary.path","/usr/local/bin/phantomjs");
        driver = new PhantomJSDriver();

//        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
//        driver = new ChromeDriver();




        driver.get("http://www.onthehouse.com.au/18436138/2801_220_spencer_st_melbourne_vic_3000");


        try
        {
            WebElement landSize = driver.findElement(By.tagName("square-meters"));

            try
            {
                String size = landSize.getAttribute("size");
                System.out.println(size);
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
                System.out.println("e2 is wrong, get the size part is wrong");
            }


        }
        catch (Exception e1)
        {
            e1.printStackTrace();
            System.out.println("e1 is wrong, get landsize part is wrong");
        }


        driver.quit();
        //System.out.println(driver.getPageSource());
    }
}
