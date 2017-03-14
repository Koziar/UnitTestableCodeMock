package testex.jokefetching;

import testex.JokeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Luke on 13/03/2017.
 */
public class FetcherFactory implements IFetcherFactory {
    private final List<String> availableTypes = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");

    private IJokeFetcher eduJoke;
    private IJokeFetcher chuckNorrisJoke;
    private IJokeFetcher momaJoke;
    private IJokeFetcher tambalJoke;

    public FetcherFactory(IJokeFetcher eduJoke, IJokeFetcher chuckNorrisJoke, IJokeFetcher momaJoke, IJokeFetcher tambalJoke) {
        this.eduJoke = eduJoke;
        this.chuckNorrisJoke = chuckNorrisJoke;
        this.momaJoke = momaJoke;
        this.tambalJoke = tambalJoke;
    }

    public FetcherFactory() {
    }

    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
    }

    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokeFetchers) {
        List<IJokeFetcher> list = new ArrayList<>();
        String[] tokens = jokeFetchers.split(",");
        try {
            for (String token : tokens) {
                if (!getAvailableTypes().contains(token)) {
                    throw new JokeException("Type unknown");
                }
                switch (token) {
                    case "EduJoke":
                        list.add(new EduJoke());
                        break;
                    case "ChuckNorris":
                        list.add(new ChuckNorris());
                        break;
                    case "Moma":
                        list.add(new Moma());
                        break;
                    case "Tambal":
                        list.add(new Tambal());
                        break;
                }
            }
        } catch (JokeException e) {
            System.out.println(e);
        }
        return list;
    }
}
