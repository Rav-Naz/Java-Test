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

    @PostMapping("/start")
    @ResponseBody
	  public ROZWIAZANIAPostResponse Start(@RequestBody ROZWIAZANIAPostRequest inputPayload) {
      ROZWIAZANIAPostResponse response = new ROZWIAZANIAPostResponse();
      return response;
	  }
}
