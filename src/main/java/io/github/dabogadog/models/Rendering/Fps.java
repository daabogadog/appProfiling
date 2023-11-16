package io.github.dabogadog.models.Rendering;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fps {
    public String type;
    public Data data;
}
