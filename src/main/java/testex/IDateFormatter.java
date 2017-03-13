package testex;

import java.util.Date;

/**
 * Created by Luke on 13/03/2017.
 */
public interface IDateFormatter {
    /**
     * Returns a formatted string representing NOW, adjusted to the time zone string
     * passed in
     * @param timeZone - Must be a valid time zone as returned by:TimeZone.getAvailableIDs()
     * @return - Time Zone string formatted like ("dd MMM yyyy hh:mm aa") and adjusted to the provided
     * time zone
     * @throws JokeException - If the timeZone string is not a valid string
     */
    String getFormattedDate(String timeZone, Date date) throws JokeException;
}
