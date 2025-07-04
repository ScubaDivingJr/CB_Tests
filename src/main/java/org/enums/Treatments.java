package org.enums;

public enum Treatments {
    TRATAMENTUL_CU_PEELING_ENZIMATIC(0),
    TRATAMENTUL_CU_PHYTOPEELING(1),
    TRATAMENTUL_ACNEEI(2),
    TERAPIE_DNA_REPAIR(3),
    TERAPIE_CU_VITAMINA_C(4),
    TERAPIE_CU_CAVIAR(5),
    TRATAMENT_CU_HIDRATARE(6),
    TRATAMENT_DE_LIFTING_CU_PROTEINE(7),
    TRATAMENT_PENTRU_OCHI(8),
    TRATAMENT_PULBERE_DIAMANTE(9);
    private int value;
    Treatments(int value) {this.value = value;}

    public int getValue() {
        return this.value;
    }
}