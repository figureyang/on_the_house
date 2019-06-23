import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

/**
 * Created by jingfeiyang on 17/3/24.
 */
public class GetHouseDetail2 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        String driverDirection = args[0];
        String readDirection = args[1];
        String writeDirection = args[2];

        WebDriver driver;
        System.setProperty("phantomjs.binary.path",driverDirection);
        driver = new PhantomJSDriver();



        int whenToClearCache = 0;



        int counterW = 0 ;
        String temCount = "null";

        try
        {
            String nextLine0[];
            CSVReader reader = new CSVReader(new FileReader(writeDirection));
            while ((nextLine0 = reader.readNext()) != null) {

                temCount =nextLine0[0];
            }
            counterW = Integer.parseInt(temCount);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("no result file");

            try
            {
                String nextLine1[];
                CSVReader reader = new CSVReader(new FileReader(readDirection));
                nextLine1 = reader.readNext();
                String proID = nextLine1[0];
                counterW = Integer.parseInt(proID)-1;
            }
            catch (Exception e1)
            {
                System.out.println("read part is wrong");
            }

            try
            {
                File CSVFile = new File(writeDirection);
                Writer fileWriter = new FileWriter(CSVFile, true);
                CSVWriter writer = new CSVWriter(fileWriter, ',');

                String entry[] = {"proID", "type", "address", "suburb", "state","postcode","latu","lng","size","m2","soldyear","HouseAddress","addressWeb","website","bed","bathroom","car","houseType","thesame","houseBuildYear", "houseLotPlan", "houseZoing", "houseLandUse", "houseIssueArea", "houseLandSize"};

                writer.writeNext(entry);
                writer.close();
            }
            catch(Exception e1)
            {
                System.out.println("create the file part is wrong");
            }
        }

        try
        {
            String nextLine[];
            CSVReader reader = new CSVReader(new FileReader(readDirection));
            while ((nextLine = reader.readNext())!=null)
            {
                String proID = nextLine[0];
                int counterR = Integer.parseInt(proID);

                if(counterR > counterW )
                {
                    try
                    {
                        String type = nextLine[1];
                        String address = nextLine[2];
                        String suburb = nextLine[3];
                        String state = nextLine[4];
                        String postcode = nextLine[5];
                        String lati = nextLine[6];
                        String lng = nextLine[7];
                        String size = nextLine[8];
                        String unit = nextLine[9];
                        String soldYear = nextLine[10];

                        String houseAddress = nextLine[11];
                        String houseAddressWeb = nextLine[12];
                        String houseWebsite = nextLine[13];
                        String bed = nextLine[14];
                        String bathroom = nextLine[15];
                        String car = nextLine[16];
                        String houseType = nextLine[17];
                        String theSame = nextLine[18];


                        System.out.println("house address is "+houseAddress);
                        System.out.println("website is " + houseWebsite);


                        try
                        {
                            long startTime1 = System.currentTimeMillis();

                            //driver = new PhantomJSDriver();

                            driver.get(houseWebsite);

                            whenToClearCache ++;


                            long endTime1 = System.currentTimeMillis();

                            System.out.println("get webite time"+(endTime1-startTime1)+"ms");

                            //get house part
                            try
                            {

                                long startTime1_5 = System.currentTimeMillis();
                                WebElement house = driver.findElement(By.tagName("property-info"));

                                long endTime1_5 = System.currentTimeMillis();
                                System.out.println("find house"+(endTime1_5-startTime1_5)+"ms");


                                String houseBuildYear;
                                try
                                {

                                    long startTime2 = System.currentTimeMillis();
                                    WebElement buildYear = house.findElement(By.cssSelector("tr.legal-attribute-row.year-built"));
                                    houseBuildYear= buildYear.getText().replace("Year Built ","");

                                    long endTime2 = System.currentTimeMillis();

                                    System.out.println("get house build year"+(endTime2-startTime2)+"ms");

                                }
                                catch (Exception e)
                                {
                                    System.out.println("get build year part wrong");
                                    houseBuildYear = "null";
                                }

                                String houseLotPlan;
                                try
                                {
                                    long startTime3 = System.currentTimeMillis();

                                    WebElement lotPlan = house.findElement(By.cssSelector("tr.legal-attribute-row.lot-plan"));
                                    houseLotPlan = lotPlan.getText().replace("Lot/Plan ","");

                                    long endTime3 = System.currentTimeMillis();

                                    System.out.println("gey house lot/plan"+(endTime3-startTime3)+"ms");
                                }
                                catch (Exception e)
                                {
                                    System.out.println("get house lot/plan part wrong");
                                    houseLotPlan = "null";
                                }

                                String houseZoing;
                                try
                                {

                                    long startTime4 = System.currentTimeMillis();

                                    WebElement zoing = house.findElement(By.cssSelector("tr.legal-attribute-row.zoning"));
                                    houseZoing = zoing.getText().replace("Zoning ","");

                                    long endTime4 = System.currentTimeMillis();

                                    System.out.println("get the zoing"+(endTime4-startTime4)+"ms");

                                }
                                catch (Exception e)
                                {
                                    System.out.println("get house Zoing part wrong");
                                    houseZoing = "null";
                                }

                                String houseLandUse;
                                try
                                {
                                    long startTime5 = System.currentTimeMillis();
                                    WebElement landUse = house.findElement(By.cssSelector("tr.legal-attribute-row.primary-land-use"));
                                    houseLandUse = landUse.getText().replace("Primary Land Use ","");
                                    long endTime5 = System.currentTimeMillis();

                                    System.out.println("get the houseLandUse"+(endTime5-startTime5)+"ms");

                                }
                                catch (Exception e)
                                {
                                    System.out.println("get house Primary Land Use part wrong");
                                    houseLandUse = "null";
                                }

                                String houseIssueArea;
                                try
                                {


                                    long startTime6 = System.currentTimeMillis();
                                    WebElement landUse = house.findElement(By.cssSelector("tr.legal-attribute-row.issuing-area"));
                                    houseIssueArea = landUse.getText().replace("Issuing Area ","");

                                    long endTime6 = System.currentTimeMillis();

                                    System.out.println("get house issueArea"+(endTime6-startTime6)+"ms");

                                }
                                catch (Exception e)
                                {
                                    System.out.println("get house Issuing Area part wrong");
                                    houseIssueArea = "null";
                                }

                                String houseLandSize;
                                try
                                {
                                    long startTime7 = System.currentTimeMillis();
                                    WebElement landSize = house.findElement(By.cssSelector("tr.legal-attribute-row.land-size"));
                                    houseLandSize = landSize.getText().replace("Land Size ","").replace("m2","");
                                    long endTime7 = System.currentTimeMillis();

                                    System.out.println("get house landuse"+(endTime7-startTime7)+"ms");
                                }
                                catch (Exception e)
                                {
                                    System.out.println("get house Issuing Area part wrong");
                                    houseLandSize = "null";
                                }



                                File CSVFile = new File(writeDirection);
                                Writer fileWriter = new FileWriter(CSVFile, true);
                                CSVWriter writer = new CSVWriter(fileWriter, ',');

                                String entry[] = {proID,type, address,suburb,state,postcode,lati,lng,size,unit,soldYear,houseAddress,houseAddressWeb,houseWebsite,bed,bathroom,car,houseType,theSame, houseBuildYear, houseLotPlan, houseZoing, houseLandUse, houseIssueArea, houseLandSize};

                                writer.writeNext(entry);
                                writer.close();


                            }
                            catch (Exception e)
                            {
                                System.out.println("get the house part wrong");

                                File CSVFile = new File(writeDirection);
                                Writer fileWriter = new FileWriter(CSVFile, true);
                                CSVWriter writer = new CSVWriter(fileWriter, ',');

                                String entry[] = {proID,type, address,suburb,state,postcode,lati,lng,size,unit,soldYear,houseAddress,houseAddressWeb,houseWebsite,bed,bathroom,car,houseType,theSame};

                                writer.writeNext(entry);
                                writer.close();
                            }



                            if(whenToClearCache == 30)
                            {
                                driver.quit();
                                System.out.println("\u001B[31m"+"has clear the cache"+"\u001B[0m");
                                System.out.println("\u001B[31m"+whenToClearCache+"\u001B[0m");
                                driver = new PhantomJSDriver();
                                whenToClearCache = 0;
                                System.out.println("\u001B[31m"+whenToClearCache+"\u001B[0m");
                            }
                            else
                            {
                                driver.manage().deleteAllCookies();
                            }


                        }
                        catch (Exception e1)
                        {
                            System.out.println("go to the website part is wrong");
                            File CSVFile = new File(writeDirection);
                            Writer fileWriter = new FileWriter(CSVFile, true);
                            CSVWriter writer = new CSVWriter(fileWriter, ',');

                            String entry[] = {proID,type, address,suburb,state,postcode,lati,lng,size,unit,soldYear,houseAddress,houseAddressWeb,houseWebsite,bed,bathroom,car,houseType,theSame};

                            writer.writeNext(entry);
                            writer.close();
                        }




                    }
                    catch (Exception ex)
                    {
                        System.out.println("explain the address part wrong");
                    }



                    counterW = counterR;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("read part is wrong");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("whole time is " + (endTime - startTime) +"ms");
    }
}
