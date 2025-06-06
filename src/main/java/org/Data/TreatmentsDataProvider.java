package org.Data;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TreatmentsDataProvider {

    public static List<String> getExpectedTreatmentTitles() {
        return Arrays.asList(
                "TRATAMENTUL CU PEELING ENZIMATIC",
                "TRATAMENTUL CU PHYTOPEELING",
                "TRATAMENTUL ACNEEI",
                "TERAPIE DNA-REPAIR",
                "TERAPIE CU VITAMINA C",
                "TERAPIE CU CAVIAR",
                "TRATAMENT CU HIDRATARE",
                "TRATAMENT DE LIFTING CU PROTEINE",
                "TRATAMENT PENTRU OCHI",
                "TRATAMENTUL CU PULBERE DE DIAMANTE"
        );
    }

    public static LinkedHashMap<String, String> expectedTreatmentDescriptionExcerpts() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put(getExpectedTreatmentTitles().get(0), "Contine extracte de fructe exotice ananas si papaia, cu actiune keratolitica datorita enzimei pancreazym.");
        map.put(getExpectedTreatmentTitles().get(1), "Reprezinta un peeling chimic mediu si in acelasi timp, o terapie rapida de intinerire a celulelor pielii. Se bazeaza pe efectul combinat de exfoliere si intinerire dat de plantele uscate SPONGILLA  FRAGILIS si EPHYDATIA(din lacurile din Canada si Rusia) combinate cu acizii alfa hidroxi (AHA) in concentratii de pana la 40% si un pH de 2,5.");
        map.put(getExpectedTreatmentTitles().get(2), "Acneea reprezinta o afectiune dermatologica la nivelul glandelor sebacee lezionara a foliculelor acestora.Etiopatogeneza este complexa si incomplet elucidata. Se disting patru factori patogenici esentiali in producerea leziunilor:");
        map.put(getExpectedTreatmentTitles().get(3), "Terapie destinata tuturor tipurilor de ten.  Stimuleaza sistemul enzimatic al pielii care va fi capabil sa recunoasca si sa repare leziunile celulare. La niveluri profunde se actioneaza asupra matricei de baza a pielii cu ajutorul bacteriei Bifidus,inclusa in serum si crema.");
        map.put(getExpectedTreatmentTitles().get(4), "Terapie destinata tenurilor deshidratate, cu pete, riduri.  Tripla actiune a vitaminei C: rehidratare, tonifiere, albire, combinata cu actiunea Retinol A si a acizilor de fructe  formeaza un tratament care regenereaza si ilumineaza pielea.");
        map.put(getExpectedTreatmentTitles().get(5), "Destinata tenurilor cu riduri vizibile ( peste 40 de ani).");
        map.put(getExpectedTreatmentTitles().get(6), "Destinat tenurilor deshidratate, ingredientul principal este acidul hialuronic, liderul hidratarii. Pielea contine jumatate din cantitatea de acid hialuronic din corp, cantitate care se diminueaza pe masura ce imbatranim.");
        map.put(getExpectedTreatmentTitles().get(7), "Combinatie a doua proteine superioare: proteina din zer, proteina din ou si germenii de grau care contin un nivel ridicat de aminoacizi esentiali si in special aminoacizi BCAA care previn pierderea de masa musculara. Tratamentul are rol antioxidant si imunostimulator puternic.");
        map.put(getExpectedTreatmentTitles().get(8), "Terapie completa si complexa care impiedica formarea ridurilor si cearcanelor.");
        map.put(getExpectedTreatmentTitles().get(9), "Tratamentul cu pulbere de diamante");
        return map;
    }
}