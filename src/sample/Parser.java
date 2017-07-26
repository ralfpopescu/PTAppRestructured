/**
 * Created by ralfpopescu on 4/27/17.
 */
package sample;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Parser {

    public Parser(){

    }

    //MOD, Server, Bartender, Barback, Busser, Host, Food Runner, Parking, Security, Maintenance, Sushi, Kitchen
    //Dishwasher, Banquet Bartender, Banquet Cook, Banquet Server, Banquet Dishwasher, Basecamp, Event Sales, Inventory

    public String getJob(String cell){
        String job = "";

        if(cell.contains("MOD")){
            return "MOD";
        }
        if(cell.contains("Server")){
            if(!cell.contains("Banquet")){
                return "Server";
            }

        }
        if(cell.contains("Bartender")){
            if(!cell.contains("Banquet")){
                return "Bartender";
            }
        }
        if(cell.contains("Barback")){
            return "Barback";
        }
        if(cell.contains("Busser")){
            return "Busser";
        }
        if(cell.contains("Host")){
            return "Host";
        }
        if(cell.contains("Food Runner")){
            return "FoodRunner";
        }
        if(cell.contains("Parking")){
            return "Parking";
        }
        if(cell.contains("Security")){
            return "Security";
        }
        if(cell.contains("Maintenance")){
            return "Maintenance";
        }
        if(cell.contains("Sushi")){
            return "Sushi";
        }
        if(cell.contains("Kitchen")){
            return "Kitchen";
        }
        if(cell.contains("Dishwasher")){
            if(!cell.contains("Banquet")){
                return "Dishwasher";
            }
        }
        if(cell.contains("Banquet Bartender")){
            return "BanquetBartender";
        }
        if(cell.contains("Banquet Cook")){
            return "BanquetCook";
        }
        if(cell.contains("Banquet Server")){
            return "BanquetServer";
        }
        if(cell.contains("Banquet Dishwasher")){
            return "BanquetDishwasher";
        }
        if(cell.contains("Basecamp")){
            return "Basecamp";
        }
        if(cell.contains("Event Sales")){
            return "EventSales";
        }
        if(cell.contains("Inventory")){
            return "Inventory";
        }

        System.out.println("XXX" + cell);
        return "n";
    }

    public String reverse(String job){
        if(job.equals("MOD")){
            return "MOD";
        }
        if(job.equals("Server")){
            return "Server";
        }
        if(job.equals("Bartender")){
            return "Bartender";
        }
        if(job.equals("Barback")){
            return "Barback";
        }
        if(job.equals("Busser")){
            return "Busser";
        }
        if(job.equals("Host")){
            return "Host";
        }
        if(job.equals("FoodRunner")){
            return "Food Runner";
        }
        if(job.equals("Parking")){
            return "Parking";
        }
        if(job.equals("Security")){
            return "Security";
        }
        if(job.equals("Maintenance")){
            return "Maintenance";
        }
        if(job.equals("Sushi")){
            return "Sushi";
        }
        if(job.equals("Kitchen")){
            return "Kitchen";
        }
        if(job.equals("Dishwasher")){
            return "Dishwasher";
        }
        if(job.equals("BanquetBartender")){
            return "Banquet Bartender";
        }
        if(job.equals("BanquetCook")){
            return "Banquet Cook";
        }
        if(job.equals("BanquetServer")){
            return "Banquet Server";
        }
        if(job.equals("BanquetDishwasher")){
            return "Banquet Dishwasher";
        }
        if(job.equals("Basecamp")){
            return "Basecamp";
        }
        if(job.equals("EventSales")){
            return "Event Sales";
        }
        if(job.equals("Inventory")){
            return "Inventory";
        }
        return "n";
    }


}
