package pt.vb.device.payload;

public class MessageResponse {
	private String msg;

	public MessageResponse(String message){
		this.msg = message;
	}

	public void setMessage(String message){
		this.msg = message;
	}

	public String getMessage(){
		return msg;
	}
}
