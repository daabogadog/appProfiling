package io.github.dabogadog.models.BateryTemp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    public Integer percent;
    public Double temp;
}
