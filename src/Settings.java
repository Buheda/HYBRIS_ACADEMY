import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
	public static Properties getDbConfig() throws IOException{
		InputStream input = new FileInputStream("local.properties");
		Properties prop = new Properties();
        prop.load(input);
                
        return prop;
	}
}
