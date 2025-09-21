package ru.inventory.domain;

public class PETGSpool extends FilamentSpool {
    private boolean isFoodSafe; 

    public PETGSpool() {}

    public PETGSpool(long id, String color, int weight, boolean isFoodSafe) {
        super(id, color, weight);
        this.isFoodSafe = isFoodSafe;
    }
    
    @Override
    public String getMaterialType() {
        return "PETG";
    }

    public boolean isFoodSafe() { return isFoodSafe; }
    public void setFoodSafe(boolean foodSafe) { this.isFoodSafe = foodSafe; }
}