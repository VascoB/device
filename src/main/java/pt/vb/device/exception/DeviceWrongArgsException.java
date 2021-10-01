package pt.vb.device.exception;

public class DeviceWrongArgsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DeviceWrongArgsException(String msg) {
		super(msg);
	}

}
