package testex.jokefetching;

import com.jayway.restassured.response.ExtractableResponse;
import testex.Joke;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Luke on 13/03/2017.
 */
public class EduJoke implements IJokeFetcher {
    @Override
    public Joke getJoke() {
        try {
            ExtractableResponse res = given().get("http://jokes-plaul.rhcloud.com/api/joke").then().extract();
            String joke = res.path("joke");
            String reference = res.path("reference");
            return new Joke(joke, reference);
        } catch (Exception e) {
            return null;
        }
    }
}
