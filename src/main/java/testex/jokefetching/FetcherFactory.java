package testex.jokefetching;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Luke on 13/03/2017.
 */
public class FetcherFactory implements IFetcherFactory {
    private final List<String> availableTypes = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");

    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
    }

    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {
        // @todo: implement
        return null;
    }
}
