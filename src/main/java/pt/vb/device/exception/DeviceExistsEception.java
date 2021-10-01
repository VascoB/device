package pt.vb.device.exception;

public class DeviceExistsEception extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DeviceExistsEception(String msg) {
		super(msg);
	}

}
