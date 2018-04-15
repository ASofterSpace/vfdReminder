package reminder.vfd.asofterspace.com.vfdreminder;

/**
 * This class provides utilities for handling notifications.
 */

public class NotificationUtils {

    final public static String KEY_NOTIFICATION_ID = "NOTIFICATION_ID";

    final public static String KEY_RESULT = "RESULT";

    private static int latestNotification = 0;

    /**
     * Generates a UNIQUE notification id!
     * @return A notification id
     */
    public static int getNotificationId() {

        latestNotification++;

        return latestNotification;
    }

}
