package com.hibernate4perf.movies.domain;

public enum Certification {
    TOUS_PUBLIC(1, "Tous public"), INTERDIT_MOINS_12(2, "Interdit aux moins de 12 ans"),
    INTERDIT_MOINS_16(3, "Interdit aux moins de 16 ans"), INTERDIT_MOINS_18(4, "Interdit aux moins de 18 ans");

    private final Integer key;
    private final String description;

    Certification(Integer key, String description) {
        this.key = key;
        this.description = description;
    }

    public Integer getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

}
