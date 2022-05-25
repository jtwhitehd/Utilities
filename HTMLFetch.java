import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Simple HTML Fetch.
 * @author jtwhitehd
 * @version 1.0
 */

public class HTMLFetch {

	/**
	 * Accepts a given URL and fetches and returns the entirety of the 
	 * HTML source available at the specified location.
	 * 
	 * @param url	the official/absolute URL of the desired destination
	 * @return content	the entirety of the HTML source from the given URL
	 */
	public static String fetch(String url)
	{
		String content = null;
		URLConnection connection = null;
		try {
			connection = new URL(url).openConnection();
			Scanner scanner = new Scanner(connection.getInputStream());
			scanner.useDelimiter("\\Z");
			content = scanner.next();
			scanner.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}
