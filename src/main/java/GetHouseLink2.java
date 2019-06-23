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
import java.util.List;



/**
 * Created by jingfeiyang on 17/3/20.
 */
public class GetHouseLink2 {
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

                String entry[] = {"proID", "type", "address", "suburb", "state","postcode","latu","lng","size","m2","soldyear","HouseAddress","addressWeb","website","bed","bathroom","car","houseType","thesame"};

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

                if(counterR > counterW)
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


                        String houseAddress = address+" "+suburb+" "+state+" "+postcode; // this can be used for search
                        String houseAddressForCompare1 = (address+" "+suburb+", "+state+" "+postcode).toLowerCase();
                        String houseAddressForCompare2 = houseAddressForCompare1.replace("street","st").replace("court","ct").replace("crescent","cres").replace("drive","dr").replace("avenue","ave").replace("building","bldg").replace("boulevard","bvd").replace("close","cl").replace("expressway","expway").replace("highway","hwy").replace("interchange","inter").replace("lane","la").replace("property","p").replace("parade","pde").replace("place","pl").replace("road","rd").replace("serviceway","serway").replace("terrace","tce").replace("throughway","throway").replace("circuit","cct").replace("esplanade","esp");

                        String houseAddressOnlyForSearch;

                        if(address.contains("/"))
                        {


                            //this prt need change

                            String unitStreet = address.substring(0,address.indexOf(" "));

                            String unitNumber = unitStreet.substring(0,unitStreet.indexOf("/"));


                            String streetNumber = unitStreet.substring(unitStreet.indexOf("/")+1);


                            String street = address.substring(address.indexOf(" ")+1);


                            houseAddressOnlyForSearch = "/"+state.toLowerCase()+"/"+suburb.replaceAll(" ","_").toLowerCase()+"_"+postcode+"/"+street.toLowerCase().replaceAll(" ","_")+"?unitNumber="+unitNumber+"&streetNumber="+streetNumber;
                            //example  http://www.onthehouse.com.au/real_estate    /vic/dallas_3047/edmund_street?unitNumber=6&streetNumber=5
                            //example 6/5 EDMUND STREET Dallas Vic 3047
                            //example address  6/5 EDMUND STREET

                        }
                        else
                        {
                            //this prt need change
                            String streetNumber = address.substring(0,address.indexOf(" "));
                            String street = address.substring(address.indexOf(" ")+1);


                            houseAddressOnlyForSearch = "/"+state.toLowerCase()+"/"+suburb.replaceAll(" ","_").toLowerCase()+"_"+postcode+"/"+street.toLowerCase().replaceAll(" ","_")+"?streetNumber="+streetNumber;
                            //example  http://www.onthehouse.com.au/real_estate    /vic/dallas_3047/rubicon_street?streetNumber=19
                            //example 19 Rubicon Street Dallas Vic 3047
                            //example address   19 Rubicon Street


                        }

                        System.out.println("houseAddress is : http://www.onthehouse.com.au/real_estate?addressQuery=" + houseAddress);
                        System.out.println("houseAddressOnlyForSearch is : http://www.onthehouse.com.au/real_estate" + houseAddressOnlyForSearch);

                        try
                        {

                            long startTime1 = System.currentTimeMillis();

                            //driver = new PhantomJSDriver();

                            //driver.get("http://www.onthehouse.com.au/real_estate?addressQuery="+houseAddress);
                            driver.get("http://www.onthehouse.com.au/real_estate"+houseAddressOnlyForSearch);

                            whenToClearCache ++;


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
                                        String houseAddressWeb = "null";
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
                                                houseAddressWeb = houseGeneral.getText().replace("\n"," ");

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
                                            if(houseAddressWeb.toLowerCase().equals(houseAddressForCompare1) || houseAddressWeb.toLowerCase().equals(houseAddressForCompare2) || houseAddressWeb.toLowerCase().equals(houseAddressForCompare2.replace("brdmeadows","broadmeadows")) || houseAddressWeb.toLowerCase().replaceAll("/","").equals(houseAddressForCompare2.replace("brdmeadows","broadmeadows").replaceAll("/","")))
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

                                            String entry[] = {proID,type,address,suburb,state,postcode,lati,lng,size,unit,soldYear,houseAddress,houseAddressWeb,houseWebsite,bed,bathroom,car,houseType,theSame};

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

                                        System.out.println("e4 is wrong, get every house attribute part is wrong");

                                    }
                                }
                            }
                            catch (Exception e3)
                            {
                                System.out.println("get houses part is wrong");
                                File CSVFile = new File(writeDirection);
                                Writer fileWriter = new FileWriter(CSVFile, true);
                                CSVWriter writer = new CSVWriter(fileWriter, ',');

                                String entry[] = {proID,type, address,suburb,state,postcode,lati,lng,size,unit,soldYear,houseAddress};

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
                        catch (Exception e)
                        {
                            System.out.println("go to the website part is wrong");
                            File CSVFile = new File(writeDirection);
                            Writer fileWriter = new FileWriter(CSVFile, true);
                            CSVWriter writer = new CSVWriter(fileWriter, ',');

                            String entry[] = {proID,type, address,suburb,state,postcode,lati,lng,size,unit,soldYear,houseAddress};

                            writer.writeNext(entry);
                            writer.close();
                        }
                    }
                    catch (Exception ex)
                    {
                        System.out.println("explain the address part wrong");
                    }



                    //driver.manage().deleteAllCookies();


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
