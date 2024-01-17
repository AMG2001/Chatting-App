package gov.iti.jets.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.util.Properties;


public class PropertiesFileUtil {

    private static String DbUrl = "jdbc:mysql://localhost:3306/telegraph";
    private static String DbUsername ="root";
    private static String DbPassword = "1234";

    public static Properties getPropertiesFromFile(){

        Properties props = new Properties();
        String currentDirectory = System.getProperty("user.dir");
        String outputPath = currentDirectory +"\\Server\\src\\main\\resources\\PropertyFiles\\DBprop.properties";
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(outputPath);
            props.load(fis);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        // Close file input stream in finally clause
        finally
        {
            try{
                fis.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        return props;

    }

    public static String getDbPassword() {
        return "DBPASS";
    }

    public static String getDbUsername() {
        return "DBUSER";
    }

    public static String getDbUrl() {
        return "DBURL";
    }

//    public static void WritePropertiesFile(){
//
//        Properties prop = new Properties();
//        OutputStream output = null;
//        String currentDirectory = System.getProperty("user.dir");
//        String outputPath = currentDirectory +"\\Server\\src\\main\\resources\\PropertyFiles\\DBprop.properties";
//
//        try{
//            output = new FileOutputStream(outputPath);
//
//            prop.setProperty("DBURL",DbUrl);
//            prop.setProperty("DBUSER",DbUsername);
//            prop.setProperty("DBPASS",DbPassword);
//
//            prop.store(output,null);
//        }
//        catch(IOException io){
//            io.printStackTrace();
//        }
//        finally{
//            if(output!=null){
//                try{
//                    output.close();
//                }
//                catch(IOException e){
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}

