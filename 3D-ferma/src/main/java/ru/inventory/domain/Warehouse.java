package ru.inventory.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Warehouse {
    private List<FilamentSpool> spools = new ArrayList<>();

    public List<FilamentSpool> getSpools() { return spools; }
    public void setSpools(List<FilamentSpool> spools) { this.spools = spools; }

    public void addSpool(FilamentSpool spool) {
        this.spools.add(spool);
    }

    public Optional<FilamentSpool> findSpoolById(long id) {
        return spools.stream().filter(s -> s.getId() == id).findFirst();
    }
}