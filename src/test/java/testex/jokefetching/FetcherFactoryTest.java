package testex.jokefetching;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Luke on 14/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class FetcherFactoryTest {

    @Mock
    EduJoke eduJokeMock;

    @Mock
    ChuckNorris chuckNorrisMock;

    @Mock
    Moma momaMock;

    @Mock
    Tambal tambalMock;

    IFetcherFactory factory;

    @Before
    public void setup() {
        factory = new FetcherFactory(eduJokeMock, chuckNorrisMock, momaMock, tambalMock);
    }

    @Test
    public void testGetJokeFetchers() throws Exception {
        List<IJokeFetcher> result = factory.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal");

        assertThat(result.size(), is(4));

        assertThat(result.get(0), is(instanceOf(EduJoke.class)));
        assertThat(result.get(1), is(instanceOf(ChuckNorris.class)));
        assertThat(result.get(2), is(instanceOf(Moma.class)));
        assertThat(result.get(3), is(instanceOf(Tambal.class)));
    }

}