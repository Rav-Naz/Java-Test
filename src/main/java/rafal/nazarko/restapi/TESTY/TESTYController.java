package rafal.nazarko.restapi.TESTY;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import rafal.nazarko.restapi.PYTANIA.PYTANIAController;
import rafal.nazarko.restapi.ROZWIAZANIA.ROZWIAZANIA;

import org.springframework.stereotype.Controller;

@Controller
public class TESTYController {
    @Autowired
    private TESTYRepository testyRepository;

    @Autowired
    private PYTANIAController pytaniaController;

    @GetMapping("/TESTY/{kod_dolaczenia}/{nr_albumu}")
    @ResponseBody
    public Object getTest(@PathVariable String kod_dolaczenia, @PathVariable String nr_albumu) {
        TESTY test = testyRepository.findByInvitationCode(kod_dolaczenia);
        if (test == null || nr_albumu == null) return "Nie znaleziono testu";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime data_rozpoczecia = LocalDateTime.parse(test.getDataRozpoczecia(), format);
        LocalDateTime data_zakonczenia = LocalDateTime.parse(test.getDataZakonczenia(), format);
        LocalDateTime teraz = LocalDateTime.now();
        if (!data_rozpoczecia.isBefore(teraz) || !data_zakonczenia.isAfter(teraz)) return "Czas na wykonanie testu upłynął";
        TESTYResponse response = new TESTYResponse(Integer.parseInt(nr_albumu), test, pytaniaController.getAllAnswers(test.getId()));
        this.testyRepository.saveAttemp(test.getId(), Integer.parseInt(nr_albumu));
        response.setId(this.testyRepository.getLastId(Integer.parseInt(nr_albumu), PageRequest.of(0, 1)).get(0).getId());
        return response;

      }

}
