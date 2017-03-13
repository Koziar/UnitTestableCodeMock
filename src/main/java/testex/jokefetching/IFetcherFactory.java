package testex.jokefetching;

import java.util.List;

/**
 * Created by Luke on 13/03/2017.
 */
public interface IFetcherFactory {
    List<String> getAvailableTypes();
    List<IJokeFetcher> getJokeFetchers(String jokesToFetch);
}
