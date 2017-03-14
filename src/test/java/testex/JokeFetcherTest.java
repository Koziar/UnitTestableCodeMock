package testex;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import testex.jokefetching.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Created by Luke on 13/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherTest {

    private JokeFetcher jokeFetcher;
    final String timeZone = "Europe/Copenhagen";

    @Mock private IDateFormatter dfMock;
    @Mock private IFetcherFactory ffMock;

    @Mock
    Moma moma;
    @Mock
    ChuckNorris chuck;
    @Mock
    EduJoke edu;
    @Mock
    Tambal tambal;

    Joke jokeEdu = new Joke("educational joke", "educationaljokes.org");
    Joke jokeChuck = new Joke("Chuck joke", "chucknorrisjokes.org");
    Joke jokeMoma = new Joke("mom joke", "jomamajokes.org");
    Joke jokeTambal = new Joke("tam joke", "tambalajokes.org");

    @Before
    public void setup() {
        List<IJokeFetcher> fetchers = Arrays.asList(edu, chuck, moma, tambal);
        when(ffMock.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal")).thenReturn(fetchers);

        List<String> types = Arrays.asList("EduJoke","ChuckNorris","Moma","Tambal");
        when(ffMock.getAvailableTypes()).thenReturn(types);

        jokeFetcher = new JokeFetcher(dfMock, ffMock);

        given(edu.getJoke()).willReturn(jokeEdu);
        given(chuck.getJoke()).willReturn(jokeChuck);
        given(moma.getJoke()).willReturn(jokeMoma);
        given(tambal.getJoke()).willReturn(jokeTambal);
    }
    /**
     * Tests if the getAvailableTypes() returns correct values
     * and if it returns the right amount of them.
     * @throws Exception
     */
    @Test
    public void testGetAvailableTypes() throws Exception {
        assertThat(jokeFetcher.getAvailableTypes().size(), is(4));
        assertThat(jokeFetcher.getAvailableTypes(), hasItems("EduJoke", "ChuckNorris" , "Moma" , "Tambal"));
    }

    @Test
    public void testCheckIfValidToken() throws JokeException {
        String validTokens = "EduJoke,ChuckNorris,Moma,Tambal";
        assertThat(jokeFetcher.isStringValid(validTokens), is(true));

        String invalidTokens = "Edu,Chuck,Mom,Tamballl";
        assertThat(jokeFetcher.isStringValid(invalidTokens), is(false));
    }

    @Test
    public void testGetJokes() throws Exception {
        given(dfMock.getFormattedDate(eq(timeZone), anyObject())).willReturn("17 feb. 2017 10:56 AM");
        // state based testing
        assertThat(jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal", timeZone).getTimeZoneString(), is("17 feb. 2017 10:56 AM"));
        // check that the mock was called ony one time
        verify(dfMock, times(1)).getFormattedDate(eq(timeZone), anyObject());
    }

    @Test
    public void eduJoke() throws JokeException {
        String expectedJoke = "educational joke";
        String expectedRef = "educationaljokes.org";

        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", timeZone);
        assertThat(jokes.getJokes().get(0).getJoke(), is(expectedJoke));
        assertThat(jokes.getJokes().get(0).getReference(), is(expectedRef));
    }

    @Test
    public void chuckNorrisJoke() throws Exception {
        String expectedJoke = "Chuck joke";
        String expectedReference = "chucknorrisjokes.org";

        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", timeZone);
        assertThat(jokes.getJokes().get(1).getJoke(), is(expectedJoke));
        assertThat(jokes.getJokes().get(1).getReference(), is(expectedReference));
    }

    @Test
    public void yoMamaJoke() throws Exception {
        String expectedJoke = "mom joke";
        String expectedReference = "jomamajokes.org";

        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", timeZone);
        assertThat(jokes.getJokes().get(2).getJoke(), is(expectedJoke));
        assertThat(jokes.getJokes().get(2).getReference(), is(expectedReference));
    }

    @Test
    public void tambalaJoke() throws Exception {
        String expectedJoke = "tam joke";
        String expectedReference = "tambalajokes.org";

        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", timeZone);
        assertThat(jokes.getJokes().get(3).getJoke(), is(expectedJoke));
        assertThat(jokes.getJokes().get(3).getReference(), is(expectedReference));
    }

}