package testex;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import testex.jokefetching.IFetcherFactory;

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

    @Mock
    private IDateFormatter dfMock;
    private IFetcherFactory ffMock;

    @Before
    public void setup() {
        jokeFetcher = new JokeFetcher(dfMock, ffMock);
    }
    /**
     * Tests if the getAvailableTypes() returns correct values
     * and if it returns the right amount of them.
     * @throws Exception
     */
    @Test
    public void testGetAvailableTypes() throws Exception {
        assertThat(jokeFetcher.getAvailableTypes(), hasItems("eduprog", "chucknorris", "moma", "tambal"));
        assertThat(jokeFetcher.getAvailableTypes().size(), is(4));
    }

    @Test
    public void testCheckIfValidToken() {
        // @todo: implement
    }

    @Test
    public void testGetJokes() throws Exception {
        given(dfMock.getFormattedDate(eq("Europe/Copenhagen"), anyObject())).willReturn("17 feb. 2017 10:56 AM");
        // state based testing
        assertThat(jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal", "Europe/Copenhagen").getTimeZoneString(), is("17 feb. 2017 10:56 AM"));
        // check that the mock was called ony one time
        verify(dfMock, times(1)).getFormattedDate(eq("Europe/Copenhagen"), anyObject());
    }

}