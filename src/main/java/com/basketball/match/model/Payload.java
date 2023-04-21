
package com.basketball.match.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "before",
    "after",
    "source",
    "op",
    "ts_ms",
    "transaction"
})
public class Payload {

    @JsonProperty("before")
    private Object before;
    @JsonProperty("after")
    private After after;
    @JsonProperty("source")
    private Source source;
    @JsonProperty("op")
    private String op;
    @JsonProperty("ts_ms")
    private Long tsMs;
    @JsonProperty("transaction")
    private Object transaction;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("before")
    public Object getBefore() {
        return before;
    }

    @JsonProperty("before")
    public void setBefore(Object before) {
        this.before = before;
    }

    @JsonProperty("after")
    public After getAfter() {
        return after;
    }

    @JsonProperty("after")
    public void setAfter(After after) {
        this.after = after;
    }

    @JsonProperty("source")
    public Source getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(Source source) {
        this.source = source;
    }

    @JsonProperty("op")
    public String getOp() {
        return op;
    }

    @JsonProperty("op")
    public void setOp(String op) {
        this.op = op;
    }

    @JsonProperty("ts_ms")
    public Long getTsMs() {
        return tsMs;
    }

    @JsonProperty("ts_ms")
    public void setTsMs(Long tsMs) {
        this.tsMs = tsMs;
    }

    @JsonProperty("transaction")
    public Object getTransaction() {
        return transaction;
    }

    @JsonProperty("transaction")
    public void setTransaction(Object transaction) {
        this.transaction = transaction;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
