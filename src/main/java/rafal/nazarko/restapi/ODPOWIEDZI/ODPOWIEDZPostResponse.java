package rafal.nazarko.restapi.ODPOWIEDZI;

public class ODPOWIEDZPostResponse {
	int id;
	String message;
	String extra;

    public ODPOWIEDZPostResponse() {
        this.id = 1;
        this.message = "ASD";
        this.extra = "";
    }

	public String getExtra() {
		return extra;
	}
	public int getId() {
		return id;
	}
	public String getMessage() {
		return message;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}