package util;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * This class provides utility methods
 * @author Viraj
 *
 */
public class Utility {

	/**
	 * This method returns property value from config.properties
	 * @param propertyName
	 * @return
	 * @throws IOException
	 */
	public static int getProperty(String propertyName) throws IOException{
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream("config.properties");
		prop.load(input);
		return Integer.parseInt(prop.getProperty(propertyName));
	}
}
