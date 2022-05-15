package rafal.nazarko.restapi.PYTANIA;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.stereotype.Controller;

@Controller
public class PYTANIAController {
    @Autowired
    private PYTANIARepository pytaniaRepository;

    @GetMapping("/PYTANIA")
    @ResponseBody
    public Iterable<PYTANIA> getAllAnswers(@RequestParam Integer id_testu) {
        // This returns a JSON or XML with the users
        List<PYTANIA> list = pytaniaRepository.findAllTestAnswers(id_testu);
        PYTANIA[] array = new PYTANIA[list.size()];
        list.toArray(array); // fill the array
        for (int i=0; i<array.length; i++) 
        { 
          Collections.shuffle(array[i].getOdpowiedzi());
        }
        Collections.shuffle(list);
        return list;
      }

}
