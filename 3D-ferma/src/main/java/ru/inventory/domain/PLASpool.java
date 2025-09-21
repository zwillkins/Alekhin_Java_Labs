package ru.inventory.domain;

public class PLASpool extends FilamentSpool {
    private int printTemperature; 

    public PLASpool() {}

    public PLASpool(long id, String color, int weight, int printTemperature) {
        super(id, color, weight);
        this.printTemperature = printTemperature;
    }

    @Override
    public String getMaterialType() {
        return "PLA";
    }
    
    public int getPrintTemperature() { return printTemperature; }
    public void setPrintTemperature(int temp) { this.printTemperature = temp; }
}