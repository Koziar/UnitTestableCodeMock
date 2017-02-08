package testex;

/**
 * Encapsulates a single Joke and it's origin
 */
public class Joke {
   private final String joke;
   private final String reference;

  public Joke(String joke,String reference) {
    this.joke = joke;
    this.reference = reference;
  }

  public String getJoke() {
    return joke;
  }

  public String getReference() {
    return reference;
  }

  @Override
  public String toString() {
    return "Joke{" + "joke=" + joke + ", reference=" + reference + '}';
  }

}
