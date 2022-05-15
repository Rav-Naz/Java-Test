package rafal.nazarko.restapi.ODPOWIEDZI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ODPOWIEDZIController {
    @PostMapping("/test")
    @ResponseBody
	public ODPOWIEDZPostResponse Test(@RequestBody ODPOWIEDZPostRequest inputPayload) {
		ODPOWIEDZPostResponse response = new ODPOWIEDZPostResponse();
		response.setId(inputPayload.getId()*100);
		response.setMessage("Hello " + inputPayload.getName());
		response.setExtra("Some text");
		return response;
	}

}
