package rafal.nazarko.restapi.ROZWIAZANIA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@Controller
public class ROZWIAZANIAController {
    @Autowired
    private ROZWIAZANIARepository rozwiazaniaRepository;

    @PostMapping("/ROZWIAZANIE")
    @ResponseBody
	  public Object Start(@RequestBody ROZWIAZANIAPostRequest inputPayload) {
        for (int i=0; i<inputPayload.getRozwiazanie().size(); i++) 
        { 
            for (int j=0; j<inputPayload.getRozwiazanie().get(i).getZaznaczone().size(); j++) {
                System.out.print(inputPayload.getRozwiazanie().get(i).getPytanieId());
                System.out.print("-");
                System.out.print(inputPayload.getRozwiazanie().get(i).getZaznaczone().get(j).getOdpowiedzId());
                System.out.println("");
            }   
        }
      return "ADS";
	  }
}
