package org.enums;

public enum OnlineAppointmentScheduleOptions {
    DIMINEATA("Dimineata"),
    LA_PRANZ("La Pranz"),
    SEARA("Seara");

    private final String value;

    OnlineAppointmentScheduleOptions(String value) {
        this.value  = value;
    }
    public String getValue(){
        return this.value;
    }
}
