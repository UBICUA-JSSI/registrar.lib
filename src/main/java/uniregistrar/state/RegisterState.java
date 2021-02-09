package uniregistrar.state;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonPropertyOrder({"jobId", "didState", "registrarMetadata", "methodMetadata"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterState {

    public static final String MIME_TYPE = "application/json";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @JsonProperty
    private String jobId;

    @JsonProperty
    private Map<String, Object> didState;

    @JsonProperty
    private Map<String, Object> registrarMetadata;

    @JsonProperty
    private Map<String, Object> methodMetadata;

    private RegisterState(String jobId, Map<String, Object> didState, Map<String, Object> registrarMetadata, Map<String, Object> methodMetadata) {

        this.jobId = jobId;
        this.didState = didState;
        this.registrarMetadata = registrarMetadata;
        this.methodMetadata = methodMetadata;
    }

    /*
	 * Factory methods
     */
    @JsonCreator
    public static RegisterState build(@JsonProperty(value = "jobId", required = false) String jobId, @JsonProperty(value = "didState", required = true) Map<String, Object> didState, @JsonProperty(value = "registrarMetadata", required = false) Map<String, Object> registrarMetadata, @JsonProperty(value = "methodMetadata", required = false) Map<String, Object> methodMetadata) {
        return new RegisterState(jobId, didState, registrarMetadata, methodMetadata);
    }

    public static RegisterState build() {
        return new RegisterState(null, new HashMap<String, Object>(), new HashMap<String, Object>(), new HashMap<String, Object>());
    }

    /*
	 * Serialization
     */
    public static RegisterState fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(json, RegisterState.class);
    }

    public static RegisterState fromJson(Reader reader) throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(reader, RegisterState.class);
    }

    public String toJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }

    /*
	 * Getters and setters
     */
    @JsonGetter
    public final String getJobId() {
        return this.jobId;
    }

    @JsonSetter
    public final void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @JsonGetter
    public final Map<String, Object> getDidState() {
        return this.didState;
    }

    @JsonSetter
    public final void setDidState(Map<String, Object> didState) {
        this.didState = didState;
    }

    @JsonGetter
    public final Map<String, Object> getRegistrarMetadata() {
        return this.registrarMetadata;
    }

    @JsonSetter
    public final void setRegistrarMetadata(Map<String, Object> registrarMetadata) {
        this.registrarMetadata = registrarMetadata;
    }

    @JsonGetter
    public final Map<String, Object> getMethodMetadata() {
        return this.methodMetadata;
    }

    @JsonSetter
    public final void setMethodMetadata(Map<String, Object> methodMetadata) {
        this.methodMetadata = methodMetadata;
    }

    /*
	 * Object methods
     */
    @Override
    public String toString() {

        try {
            return this.toJson();
        } catch (JsonProcessingException ex) {
            return ex.getMessage();
        }
    }
}
