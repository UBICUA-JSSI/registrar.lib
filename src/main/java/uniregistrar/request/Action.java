package uniregistrar.request;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Action {

    public static final String MIME_TYPE = "application/json";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @JsonProperty
    private String endorser;
    
    @JsonProperty
    private String identifier;

    @JsonProperty
    private Map<String, Object> operation;

    @JsonProperty
    private int protocolVersion;

    @JsonProperty
    private long reqId;
    
    @JsonProperty
    private Map<String, Object> signatures;

    public Action() {
       
    }
    
    public Action(String json) {
        try {
            Action action = fromJson(json);
            this.endorser = action.getEndorser();
            this.identifier = action.getIdentifier();
            this.operation = action.getOperation();
            this.signatures = action.getSignatures();
            this.protocolVersion = action.getProtocolVersion();
            this.reqId = action.getReqId();

        } catch (IOException ex) {
        }
    }

    public Action(String endorser, String identifier, Map<String, Object> operation, int protocolVersion, long reqId, Map<String, Object> signatures) {

        this.endorser = endorser;
        this.identifier = identifier;
        this.operation = operation;
        this.signatures = signatures;
        this.protocolVersion = protocolVersion;
        this.reqId = reqId;
    }

    /*
	 * Serialization
     */
    public static Action fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(json, Action.class);
    }

    public static Action fromJson(Reader reader) throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(reader, Action.class);
    }

    public String toJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }

    /*
     * Getters and setters
     */
    @JsonGetter
    public final String getEndorser() {
        return endorser;
    }

    @JsonSetter
    public final void setEndorser(String endorser) {
        this.endorser = endorser;
    }
    
    @JsonGetter
    public final String getIdentifier() {
        return identifier;
    }

    @JsonSetter
    public final void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @JsonGetter
    public final Map<String, Object> getOperation() {
        return operation;
    }

    @JsonSetter
    public final void setOperation(Map<String, Object> operation) {
        this.operation = operation;
    }

    @JsonGetter
    public int getProtocolVersion() {
        return protocolVersion;
    }

    @JsonSetter
    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @JsonGetter
    public long getReqId() {
        return reqId;
    }

    @JsonSetter
    public void setReqId(long reqId) {
        this.reqId = reqId;
    }

    @JsonSetter
    public final void setSignatures(Map<String, Object> signatures) {
        this.signatures = signatures;
    }

    @JsonGetter
    public final Map<String, Object> getSignatures() {
        return signatures;
    }

    /*
     * Object methods
     */
    @Override
    public String toString() {
        try {
            return toJson();
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}
