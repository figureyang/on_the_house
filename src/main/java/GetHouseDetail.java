import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

/**
 * Created by jingfeiyang on 17/3/16.
 */
public class GetHouseDetail {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        String readDirection = args[1];
        String writeDirection = args[2];


//        String readDirection = "/Users/jingfeiyang/Desktop/melburne_university/semester4/finalproject_sydney/mingzhaofullnsw/test/testForWebsite.csv";
//        String writeDirection = "/Users/jingfeiyang/Desktop/melburne_university/semester4/finalproject_sydney/mingzhaofullnsw/test/testForWebsiteResults.csv";


        try
        {
            String nextLine[];
            CSVReader reader = new CSVReader(new FileReader(readDirection));
            while ((nextLine = reader.readNext())!=null)
            {

                String proID = nextLine[0];
                String address = nextLine[1];
                String suburb = nextLine[2];
                String postcode = nextLine[3];
                String state = nextLine[4];
                String lati = nextLine[5];
                String lng = nextLine[6];
                String size = nextLine[7];
                String unit = nextLine[8];
                String type = nextLine[9];

                String houseAddressOriginal = nextLine[10];
                String houseAddressFromWebsite = nextLine[11];
                String website = nextLine[12];
                String bed = nextLine[13];
                String bathroom = nextLine[14];
                String car = nextLine[15];
                String houseType = nextLine[16];
                String theSame = nextLine[17];



                WebDriver driver;
//                System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
//                driver = new ChromeDriver();
//                Thread.sleep(1000);

                //System.setProperty("phantomjs.binary.path","/usr/local/bin/phantomjs");

                System.setProperty("phantomjs.binary.path",args[0]);

                driver = new PhantomJSDriver();


                //go to the website part
                try
                {
                    driver.get(website);

                    //get the house part
                    try
                    {
                        WebElement house = driver.findElement(By.tagName("property-info"));

                        String houseBuildYear;
                        try
                        {
                            WebElement buildYear = house.findElement(By.cssSelector("tr.legal-attribute-row.year-built"));
                            houseBuildYear= buildYear.getText().replace("Year Built","");
                        }
                        catch (Exception e)
                        {
                            System.out.println("get build year part wrong");
                            houseBuildYear = "null";
                        }

                        String houseLotPlan;
                        try
                        {
                            WebElement lotPlan = house.findElement(By.cssSelector("tr.legal-attribute-row.lot-plan"));
                            houseLotPlan = lotPlan.getText().replace("Lot/Plan","");
                        }
                        catch (Exception e)
                        {
                            System.out.println("get house lot/plan part wrong");
                            houseLotPlan = "null";
                        }

                        String houseZoing;
                        try
                        {
                            WebElement zoing = house.findElement(By.cssSelector("tr.legal-attribute-row.zoing"));
                            houseZoing = zoing.getText().replace("Zoing","");
                        }
                        catch (Exception e)
                        {
                            System.out.println("get house Zoing part wrong");
                            houseZoing = "null";
                        }

                        String houseLandUse;
                        try
                        {
                            WebElement landUse = house.findElement(By.cssSelector("tr.legal-attribute-row.primary-land-use"));
                            houseLandUse = landUse.getText().replace("Primary Land Use","");
                        }
                        catch (Exception e)
                        {
                            System.out.println("get house Primary Land Use part wrong");
                            houseLandUse = "null";
                        }

                        String houseIssueArea;
                        try
                        {
                            WebElement landUse = house.findElement(By.cssSelector("tr.legal-attribute-row.issuing-area"));
                            houseIssueArea = landUse.getText().replace("Issuing Area","");
                        }
                        catch (Exception e)
                        {
                            System.out.println("get house Issuing Area part wrong");
                            houseIssueArea = "null";
                        }

                        String houseLandSize;
                        try
                        {
                            WebElement landSize = house.findElement(By.cssSelector("tr.legal-attribute-row.land-size"));
                            houseLandSize = landSize.getText().replace("Land Size","").replace("m2","");
                        }
                        catch (Exception e)
                        {
                            System.out.println("get house Issuing Area part wrong");
                            houseLandSize = "null";
                        }



                        File CSVFile = new File(writeDirection);
                        Writer fileWriter = new FileWriter(CSVFile, true);
                        CSVWriter writer = new CSVWriter(fileWriter, ',');

                        String entry[] = {proID, address, suburb, postcode,state, lati, lng, size, unit, type, houseAddressOriginal, houseAddressFromWebsite, website, bed, bathroom, car, houseType, theSame, houseBuildYear, houseLotPlan, houseZoing, houseLandUse, houseIssueArea, houseLandSize};

                        writer.writeNext(entry);
                        writer.close();


                    }
                    catch (Exception e)
                    {
                        System.out.println("get the house part wrong");

                        File CSVFile = new File(writeDirection);
                        Writer fileWriter = new FileWriter(CSVFile, true);
                        CSVWriter writer = new CSVWriter(fileWriter, ',');

                        String entry[] = {proID, address, suburb, postcode,state, lati, lng, size, unit, type, houseAddressOriginal, houseAddressFromWebsite, website, bed, bathroom, car, houseType, theSame};

                        writer.writeNext(entry);
                        writer.close();
                    }


                }
                catch (Exception e)
                {
                    System.out.println("go to the website part wrong");

                    File CSVFile = new File(writeDirection);
                    Writer fileWriter = new FileWriter(CSVFile, true);
                    CSVWriter writer = new CSVWriter(fileWriter, ',');

                    String entry[] = {proID, address, suburb, postcode,state, lati, lng, size, unit, type, houseAddressOriginal, houseAddressFromWebsite, website, bed, bathroom, car, houseType, theSame};

                    writer.writeNext(entry);
                    writer.close();
                }


                driver.quit();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("read part wrong");
        }




        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime+"ms");
    }
}
