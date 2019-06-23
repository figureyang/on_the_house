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
import java.util.List;

/**
 * Created by jingfeiyang on 17/3/15.
 */
public class GetHouseLink {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();


//        String readDirection = args[1];
//        String writeDirection = args[2];

        String readDirection = "/Users/jingfeiyang/Downloads/smallerThan100AndBiggerThan1200_VIC.csv";
        String writeDirection = "/Users/jingfeiyang/Downloads/smallerThan100AndBiggerThan1200_VIC_rest_2.csv";


        WebDriver driver;
//        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
//        driver = new ChromeDriver();


        System.setProperty("phantomjs.binary.path","/usr/local/bin/phantomjs");
        //System.setProperty("phantomjs.binary.path",args[0]);
        driver = new PhantomJSDriver();

        //read csv file part
        try
        {
            String nextLine[];
            CSVReader reader = new CSVReader(new FileReader(readDirection));
            while ((nextLine = reader.readNext())!=null)
            {
//                String proID = nextLine[0];
//                String address = nextLine[1];
//                String suburb = nextLine[2];
//                String postcode = nextLine[3];
//                String state = nextLine[4];
//                String lati = nextLine[5];
//                String lng = nextLine[6];
//                String size = nextLine[7];
//                String unit = nextLine[8];
//                String type = nextLine[9];


//                String number = nextLine[0];
//                String proID = nextLine[10];
//                String address = nextLine[5];
//                String suburb = nextLine[6];
//                String postcode = nextLine[3];
//                String state = nextLine[7];
//                String lati = nextLine[1];
//                String lng = nextLine[2];
//                String size = nextLine[8];
//                String unit = nextLine[9];
//                String type = nextLine[4];
//                String soldYear = nextLine[11];

                String proID = nextLine[0];
                String address = nextLine[2];
                String suburb = nextLine[3];
                String postcode = nextLine[5];
                String state = nextLine[4];
                String lati = nextLine[6];
                String lng = nextLine[7];
                String size = nextLine[8];
                String unit = nextLine[9];
                String type = nextLine[1];




                String houseAddress = address+" "+suburb+" "+state+" "+postcode;
                String houseAddressForCompare1 = address+" "+suburb+", "+state+" "+postcode;
                String houseAddressForCompare2 = houseAddressForCompare1.replace("Street","St");


                String streetNumber = address.substring(0,address.indexOf(" "));
                String street = address.substring(address.indexOf(" ")+1);

                System.out.println(street+streetNumber);

                //go to the website part
//                WebDriver driver;
//                System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
//                driver = new ChromeDriver();
//                Thread.sleep(1000);

                //System.setProperty("phantomjs.binary.path","/usr/local/bin/phantomjs");
//                System.setProperty("phantomjs.binary.path",args[0]);
//                driver = new PhantomJSDriver();


                try
                {

                    long startTime1 = System.currentTimeMillis();

                    driver.get("http://www.onthehouse.com.au/real_estate?addressQuery="+houseAddress);
                    //driver.get("http://www.onthehouse.com.au/real_estate"+"/"+state.toLowerCase()+"/"+suburb.toLowerCase()+"_"+postcode+"/"+street.toLowerCase().replaceAll(" ","_")+"?streetNumber="+streetNumber);
                    //System.out.println("http://www.onthehouse.com.au/real_estate"+"/"+state.toLowerCase()+"/"+suburb.toLowerCase()+"_"+postcode+"/"+street.toLowerCase().replaceAll(" ","_")+"?streetNumber="+streetNumber);
                    //      http://www.onthehouse.com.au/real_estate/nsw/camperdown_2050/fowler_street?streetNumber=2;
                    //http://www.onthehouse.com.au/real_estate/nsw/sydney_2000/henry_lawson_drive?streetNumber=392

                    long endTime1 = System.currentTimeMillis();

                    System.out.println("get webite time"+(endTime1-startTime1)+"ms");

                    //get houses part
                    try
                    {

                        long startTime1_5 = System.currentTimeMillis();
                        List<WebElement> houses = driver.findElements(By.className("property-item-details"));

                        long endTime1_5 = System.currentTimeMillis();
                        System.out.println("find every house"+(endTime1_5-startTime1_5)+"ms");

                        for(WebElement house : houses)
                        {
                            //get every house attribute part
                            try
                            {
                                String houseAddress1 = "null";
                                String houseWebsite = "null";
                                String bed = "null";
                                String bathroom = "null";
                                String car = "null";
                                String houseType = "null";

                                String theSame = "null";

                                //get houseAddress1 and houseWebsite
                                try
                                {
                                    long startTime2 = System.currentTimeMillis();
                                    WebElement houseGeneral = house.findElement(By.cssSelector("a.property-link.ng-scope"));
                                    try
                                    {
                                        houseAddress1 = houseGeneral.getText().replace("\n"," ");

                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println("get house address part wrong");
                                    }
                                    try
                                    {
                                        houseWebsite =  houseGeneral.getAttribute("href");

                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println("get house website part wrong");
                                    }

                                    long endTime2 = System.currentTimeMillis();

                                    System.out.println("get house address and house website"+(endTime2-startTime2)+"ms");
                                }
                                catch (Exception e5)
                                {
                                    e5.printStackTrace();
                                    System.out.println("get housegeneral part is wrong");
                                }

                                //get house bedroom bathroom and car
                                try
                                {
                                    long startTime3 = System.currentTimeMillis();
                                    bed = house.findElement(By.cssSelector("li.property-attribute.property-attribute-bed.ng-binding")).getText();

                                    long endTime3 = System.currentTimeMillis();

                                    System.out.println("gey house bedroom"+(endTime3-startTime3)+"ms");

                                }
                                catch (Exception e)
                                {
                                    System.out.println("get bed part wrong");
                                }
                                try
                                {
                                    long startTime4 = System.currentTimeMillis();
                                    bathroom = house.findElement(By.cssSelector("li.property-attribute.property-attribute-bath.ng-binding")).getText();

                                    long endTime4 = System.currentTimeMillis();

                                    System.out.println("get the bathroom"+(endTime4-startTime4)+"ms");
                                }
                                catch (Exception e)
                                {
                                    System.out.println("get bath part wrong");
                                }
                                try
                                {
                                    long startTime5 = System.currentTimeMillis();
                                    car = house.findElement(By.cssSelector("li.property-attribute.property-attribute-car.ng-binding")).getText();

                                    long endTime5 = System.currentTimeMillis();

                                    System.out.println("get the car"+(endTime5-startTime5)+"ms");
                                }
                                catch (Exception e)
                                {
                                    System.out.println("get car part wrong");
                                }
                                try
                                {
                                    long startTime6 = System.currentTimeMillis();
                                    houseType = house.findElement(By.cssSelector("div.property-type.ng-binding")).getText();

                                    long endTime6 = System.currentTimeMillis();

                                    System.out.println("get house type"+(endTime6-startTime6)+"ms");
                                }
                                catch (Exception e)
                                {
                                    System.out.println();
                                }
                                try
                                {
                                    long startTime7 = System.currentTimeMillis();
                                    if(houseAddress1.toLowerCase().equals(houseAddressForCompare1.toLowerCase()) || houseAddress1.toLowerCase().equals(houseAddressForCompare2.toLowerCase()))
                                    {
                                        theSame = "YES";
                                    }
                                    else
                                    {
                                        theSame = "NO";
                                    }
                                    long endTime7 = System.currentTimeMillis();

                                    System.out.println("get if the house is the one we want"+(endTime7-startTime7)+"ms");
                                }
                                catch (Exception e)
                                {
                                    System.out.println("judge if the same house");
                                }


                                //write part
                                try
                                {
                                    long startTime8 = System.currentTimeMillis();
                                    File CSVFile = new File(writeDirection);
                                    Writer fileWriter = new FileWriter(CSVFile, true);
                                    CSVWriter writer = new CSVWriter(fileWriter, ',');

                                    String entry[] = {proID, address, suburb, postcode,state, lati, lng, size, unit, type, houseAddress, houseAddress1, houseWebsite, bed, bathroom, car, houseType, theSame};

                                    //String entry[] = {number, lati, lng, postcode, type, address, suburb, state, size, unit, proID, soldYear, houseAddress, houseAddress1, houseWebsite, bed, bathroom, car, houseType, theSame};
                                    writer.writeNext(entry);
                                    writer.close();

                                    long endTime8 = System.currentTimeMillis();

                                    System.out.println("get the write time"+(endTime8-startTime8)+"ms");
                                }
                                catch (Exception e)
                                {
                                    System.out.println("write part is wrong");
                                }
                            }
                            catch (Exception e4)
                            {
                                e4.printStackTrace();
                                System.out.println("e4 is wrong, get every house attribute part is wrong");
                                File CSVFile = new File(writeDirection);
                                Writer fileWriter = new FileWriter(CSVFile, true);
                                CSVWriter writer = new CSVWriter(fileWriter, ',');

                                String entry[] = {proID, address, suburb, postcode, state, lati, lng, size, unit, type, houseAddress};
                                //String entry[] = {number, lati, lng, postcode, type, address, suburb, state, size, unit, proID, soldYear, houseAddress};


                                writer.writeNext(entry);
                                writer.close();
                            }

                        }
                    }
                    catch (Exception e3)
                    {
                        e3.printStackTrace();
                        System.out.println("e3 is wrong, get houses part is wrong");
                        File CSVFile = new File(writeDirection);
                        Writer fileWriter = new FileWriter(CSVFile, true);
                        CSVWriter writer = new CSVWriter(fileWriter, ',');

                        String entry[] = {proID, address, suburb, postcode, state, lati, lng, size, unit, type, houseAddress};
                        //String entry[] = {number, lati, lng, postcode, type, address, suburb, state, size, unit, proID, soldYear, houseAddress};


                        writer.writeNext(entry);
                        writer.close();
                    }
                }
                catch (Exception e2)
                {
                    e2.printStackTrace();
                    System.out.println("e2 is wrong, go to the website part is wrong");
                    File CSVFile = new File(writeDirection);
                    Writer fileWriter = new FileWriter(CSVFile, true);
                    CSVWriter writer = new CSVWriter(fileWriter, ',');

                    String entry[] = {proID, address, suburb, postcode, state, lati, lng, size, unit, type, houseAddress};

                    //String entry[] = {number, lati, lng, postcode, type, address, suburb, state, size, unit, proID, soldYear, houseAddress};


                    writer.writeNext(entry);
                    writer.close();
                }

                driver.manage().deleteAllCookies();
                //driver.quit();
            }
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
            System.out.println("e1 is wrong, read part is wrong");
        }



        long endTime = System.currentTimeMillis();

        System.out.println(endTime-startTime+"ms");


    }
}
