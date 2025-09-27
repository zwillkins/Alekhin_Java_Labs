package ru.inventory.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"materialType"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = PLASpool.class, name = "PLA"),
    @JsonSubTypes.Type(value = PETGSpool.class, name = "PETG")
})
public abstract class FilamentSpool implements Trackable {
    protected long id;
    protected String color;
    protected int weight; 

    public FilamentSpool() {} 

    public FilamentSpool(long id, String color, int weight) {
        this.id = id;
        this.color = color;
        this.weight = weight;
    }

    @Override
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    @Override
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}