package utilitydy_10;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class SysProperties19 {
    private static Properties prop=null;
    private SysProperties19(){

    }

    public static Properties getInstance() {
        if(prop==null){
            ClassLoader loader=SysProperties19.class.getClassLoader();
            if(loader==null)
                loader=ClassLoader.getSystemClassLoader();

            String propFile="application.properties";
            java.net.URL url=loader.getResource(propFile);
            prop=new Properties();

            try{
                prop.load(url.openStream());
            }catch(IOException ex){
            }
        }
        return  prop;
    }
    public static  String getPropertiesValue(String key){
        return SysProperties19.getInstance().getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(getPropertiesValue("DB_PASSWORD"));
    }
}
