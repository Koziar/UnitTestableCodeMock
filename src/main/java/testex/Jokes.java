package testex;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a number of Joke's and a string value containing a time zone adjusted string indicating
 * when the jokes was fetched
 */
public class Jokes {

  List<Joke> jokes = new ArrayList();
  String timeZoneString;

  void addJoke(Joke joke) {
    jokes.add(joke);
  }

  public List<Joke> getJokes() {
    return jokes;
  }

  public void setTimeZoneString(String timeZoneString) {
    this.timeZoneString = timeZoneString;
  }

  public String getTimeZoneString() {
    return timeZoneString;
  }

}
