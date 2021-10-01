package pt.vb.device.exception;

public class DeviceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DeviceNotFoundException(String msg) {
		super(msg);
	}

}
