import java.awt.*;
import java.awt.TrayIcon.MessageType;

/**
 * Utilize and send BASIC Windows OS desktop push notifications.
 * 
 * @author jtwhitehd
 * @version 0.1
 * 
 *          https://docs.oracle.com/javase/8/docs/api/java/awt/SystemTray.html
 *          https://stackoverflow.com/questions/34490218/how-to-make-a-windows-notification-in-java
 */

public class PushNotification {

	/**
	 * Attempts to send a Windows 10 system tray desktop push
	 * notification and returns information of type String in regards
	 * to the success or failure of the attempt.
	 * 
	 * Method serves as an interface between the caller and the class's
	 * private implementations.
	 * 
	 * @param title   The title of the notification - the larger, bolded text.
	 * @param message The finer details of the notification - smaller and un-bolded.
	 * @return information a String containing details about the success or failure
	 *         of the method's execution.
	 */
	public static String tryPush(String title, String message)
	{
		String information = null;
		if (PushNotification.isValid()) {
			try {
				PushNotification.pushNotif(title, message);
			}
			catch (AWTException e) {
				information = "AWTException encountered.\n";
				information += e.getStackTrace();
			}
			catch (Exception e) {
				information = "Some exception encountered.\n";
				information += e.getStackTrace();
			}
		}
		else {
			information = "All operations completed successfully.";
		}
		return information;
	}

	/**
	 * Method verifies the operating system's ability to utilize
	 * PushNotification.PushNotif().
	 * 
	 * It is recommend to call this method prior to .PushNotif().
	 * 
	 * @return flag Boolean to determine if pushNotif()
	 *         method may be used on calling operating system.
	 */
	private static boolean isValid()
	{
		boolean flag;
		if (SystemTray.isSupported()) {
			flag = true;
		}
		else {
			flag = false;
		}

		return flag;
	}

	/**
	 * Sends a windows 10 system tray desktop notification with desired
	 * title and message text.
	 * 
	 * @param title   The title of the notification - the larger, bolded text.
	 * @param message The finer details of the notification - smaller and un-bolded.
	 * @throws AWTException Exception may be thrown if the AWT library is unable
	 *                      to perform its functions on the given operating system.
	 */
	private static void pushNotif(String title, String message) throws AWTException
	{
		// ~ METHOD IN NEED OF REFINEMENT ~

		// Obtain only one instance of the SystemTray object
		SystemTray tray = SystemTray.getSystemTray();

		// If the icon is a file OR if no image is provided
		Image image = Toolkit.getDefaultToolkit().createImage("icon.png"); // TODO
		// Alternative (if the icon is on the classpath):
		// Image image =
		// Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

		TrayIcon trayIcon = new TrayIcon(image, "Java Application");
		// Let the system resize the image if needed
		trayIcon.setImageAutoSize(true);
		// Set tooltip text for the tray icon
		trayIcon.setToolTip("Java Application");
		tray.add(trayIcon);

		trayIcon.displayMessage(title, message, MessageType.INFO);
	}
}
