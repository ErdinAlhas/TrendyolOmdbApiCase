import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import model.GetResponse;
import service.ByIdOrTitleService;
import spec.ResponseSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseServiceTest {
    ByIdOrTitleService byIdOrTitleService = new ByIdOrTitleService();
    RequestMaps requestMaps = new RequestMaps();

    public List<GetResponse> getMovies(String movieName ){
        Map<String, Object> titleMap = requestMaps.titleMap(movieName);
        Response byIdOrTitle = byIdOrTitleService.get(titleMap, ResponseSpec.checkStatusCodeOk());

        Gson g = new Gson();
        JsonArray jsonArray = g.fromJson(byIdOrTitle.jsonPath().prettify(), JsonObject.class).getAsJsonArray("Search");

        List<GetResponse> films = new ArrayList<>();
        for (int i=0; i<jsonArray.size();i++){
            GetResponse film = new GetResponse();
            JsonElement jsonElement = jsonArray.get(i);
            film.setTitle(jsonElement.getAsJsonObject().get("Title").getAsString());
            film.setYear(jsonElement.getAsJsonObject().get("Year").getAsString());
            film.setImdbID(jsonElement.getAsJsonObject().get("imdbID").getAsString());
            films.add(film);
        }

        return films;
    }

}