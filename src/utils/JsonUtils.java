package utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class JsonUtils {

    private static ObjectMapper mapper;

    public JsonUtils() {
        mapper = new ObjectMapper();

        // Include only non empty/null properties
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Ignore comments in json
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    /**
     * Deserialize json file to POJO
     * Expects full path of json file
     *
     * @param jsonFilePath
     * @param clazz        Class type of deserialized POJO
     * @return
     * @throws IOException
     */
    public Object readJsonFile(String jsonFilePath, Class clazz) throws
            IOException {
        return mapper.readValue(new File(jsonFilePath), clazz);
    }

}



