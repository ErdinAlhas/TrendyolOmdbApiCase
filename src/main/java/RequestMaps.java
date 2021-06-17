import java.util.HashMap;
import java.util.Map;

public class RequestMaps {

    public Map<String, Object> imdbIdMap(String imdbId) {
        Map<String, Object> bySearchParams = new HashMap<>();
        bySearchParams.put("i", imdbId);
        bySearchParams.put("apiKey", "1a5ce4ee");
        return bySearchParams;
    }

    public Map<String, Object> titleMap(String title) {
        Map<String, Object> params = new HashMap<>();
        params.put("s", title);
        params.put("apiKey", "1a5ce4ee");
        return params;
    }
}
