package rafal.nazarko.restapi.ROZWIAZANIA;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import rafal.nazarko.restapi.ODPOWIEDZI.ODPOWIEDZI;
import rafal.nazarko.restapi.ODPOWIEDZIROZWIAZANIA.ODPOWIEDZIROZWIAZANIARepository;
import rafal.nazarko.restapi.PROGIPUNKTOWE.PROGIPUNKTOWE;
import rafal.nazarko.restapi.PROGIPUNKTOWE.PROGIPUNKTOWERepository;
import rafal.nazarko.restapi.PYTANIA.PYTANIA;
import rafal.nazarko.restapi.PYTANIA.PYTANIAController;

import org.springframework.stereotype.Controller;

@Controller
public class ROZWIAZANIAController {

    @Autowired
    private PYTANIAController pytaniaController;

    @Autowired
    private PROGIPUNKTOWERepository progiPunktoweRepository;

    @Autowired
    private ROZWIAZANIARepository rozwiazaniaRepository;
    
    @Autowired
    private ODPOWIEDZIROZWIAZANIARepository odpowiedzRozwiazanieRepository;

    @PostMapping("/ROZWIAZANIE")
    @ResponseBody
	  public Object Start(@RequestBody ROZWIAZANIAPostRequest inputPayload) {
        float maxPunktowZaTest = 0;
        float zdobytePunkty = 0;
        List<PYTANIA> listaPytanZBazy =  (List<PYTANIA>) pytaniaController.getAllAnswers(inputPayload.getTestId());
        ROZWIAZANIAPostResponse response = new ROZWIAZANIAPostResponse();
        List<PytaniaIntefaceResponse> pytaniaResponseList = new ArrayList<PytaniaIntefaceResponse>();

        for (int i=0; i<inputPayload.getRozwiazanie().size(); i++) 
        {   
            PytaniaInteface pytanieZRequesta = inputPayload.getRozwiazanie().get(i);
            Predicate<PYTANIA> byId = pytanie -> pytanie.getId().equals(pytanieZRequesta.getPytanieId());
            List<PYTANIA> temp = listaPytanZBazy.stream().filter(byId).limit(1).collect(Collectors.toList());
            if (temp.size() != 1) return "Przes??ano odpowied?? do nieprawid??owego testu";
            PYTANIA toKonkretnePytanie = temp.get(0);
            maxPunktowZaTest += toKonkretnePytanie.getPunkty();
            if (pytanieZRequesta.getZaznaczone().size() != toKonkretnePytanie.getOdpowiedzi().size()) return "Brak odpowiedzi na wszystkie odpowiedzi w pytaniu";
            boolean czyPoprawneOdpowiedzi = true;
            PytaniaIntefaceResponse pytanieResponse = new PytaniaIntefaceResponse();
            pytanieResponse.setPytanieId(toKonkretnePytanie.getId());
            pytanieResponse.setTresc(toKonkretnePytanie.getTresc());
            List<OdpowiedzInterfaceResponse> odpowiedziResponseList = new ArrayList<OdpowiedzInterfaceResponse>();
            for (int j=0; j<pytanieZRequesta.getZaznaczone().size(); j++) {
                OdpowiedzInterface odpowiedzNaPytanieZRequesta = pytanieZRequesta.getZaznaczone().get(j);
                Predicate<ODPOWIEDZI> byId2 = odpowiedz -> odpowiedz.getId().equals(odpowiedzNaPytanieZRequesta.getOdpowiedzId());
                List<ODPOWIEDZI> temp2 = toKonkretnePytanie.getOdpowiedzi().stream().filter(byId2).limit(1).collect(Collectors.toList());
                if (temp2.size() != 1) {
                    this.rozwiazaniaRepository.flushThisRozwiazanie(inputPayload.getRozwiazanieId());
                    return "Nie znaleziono odpowiedzi";
                }
                ODPOWIEDZI taKonkretnaOdpowiedz = temp2.get(0);
                if (!taKonkretnaOdpowiedz.getCzyPoprawna().equals(odpowiedzNaPytanieZRequesta.getCzyZaznaczone())) {
                    czyPoprawneOdpowiedzi = false;
                }

                try {
                    this.odpowiedzRozwiazanieRepository.saveNowaOdpowiedz(inputPayload.getRozwiazanieId(), odpowiedzNaPytanieZRequesta.getOdpowiedzId(), odpowiedzNaPytanieZRequesta.getCzyZaznaczone());
                }
                catch(Exception ex)
                {
                    if(ex.getMessage().contains("ConstraintViolationException")) {
                        return "Na pytanie mo??na udzieli?? odpowiedzi tylko raz";
                    } else return "Nieznany b????d";
                }
                OdpowiedzInterfaceResponse odpowiedzResponse = new OdpowiedzInterfaceResponse(taKonkretnaOdpowiedz.getId(),odpowiedzNaPytanieZRequesta.getCzyZaznaczone(),taKonkretnaOdpowiedz.getCzyPoprawna(),taKonkretnaOdpowiedz.getOdpowiedz());
                odpowiedziResponseList.add(odpowiedzResponse);
            }
            pytanieResponse.setgetUzyskanePunkty(czyPoprawneOdpowiedzi ? toKonkretnePytanie.getPunkty() : 0);
            pytanieResponse.setZaznaczone(odpowiedziResponseList);
            pytaniaResponseList.add(pytanieResponse);
            if(czyPoprawneOdpowiedzi) zdobytePunkty += toKonkretnePytanie.getPunkty();
        }
        BigDecimal bd = new BigDecimal(zdobytePunkty/maxPunktowZaTest*100).setScale(2, RoundingMode.HALF_UP);
        PROGIPUNKTOWE ocena = this.progiPunktoweRepository.findDegree(bd.doubleValue());
        response.setPunkty(zdobytePunkty);
        response.setPunktyMax(maxPunktowZaTest);
        response.setNrAlbumu(inputPayload.getNrAlbumu());
        response.setTestId(inputPayload.getTestId());
        response.setRozwiazanieId(inputPayload.getRozwiazanieId());
        response.setOcena(ocena.getOcena());
        response.setRozwiazanie(pytaniaResponseList);
        try {
            this.rozwiazaniaRepository.saveDegree(ocena.getOcena(), inputPayload.getRozwiazanieId());
        }
        catch(Exception ex) {
            if(ex.getMessage().contains("ConstraintViolationException")) {
                return "Na pytanie mo??na udzieli?? odpowiedzi tylko raz";
            } else return "Nieznany b????d";
        }

        return response;
	  }
}
