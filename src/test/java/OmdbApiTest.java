import io.restassured.response.Response;
import model.GetResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import spec.ResponseSpec;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class OmdbApiTest extends BaseServiceTest {

    @Test
    public void shouldSearchByImdbId() {
        List<GetResponse> films = getMovies("Harry Potter");
        String Id="" ;
        for(int i=0; i<films.size(); i++){
            if(films.get(i).getTitle().equals("Harry Potter and the Sorcerer's Stone")){
                Id=films.get(i).getImdbID();
            }
        }
        Map<String, Object> bySearchParams = requestMaps.imdbIdMap(Id);
        Response bySearch = byIdOrTitleService.get(bySearchParams, ResponseSpec.checkStatusCodeOk());

        GetResponse as = bySearch.as(GetResponse.class);

        assertThat(bySearch.getStatusCode(), Matchers.is(200));
        assertEquals(as.getTitle(),"Harry Potter and the Sorcerer's Stone");
        assertEquals(as.getYear(),"2001");
        assertEquals(as.getReleased(),"16 Nov 2001");
        assertNotNull(as.getImdbID());
    }
}