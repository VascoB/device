package pt.vb.device.service.bo;

import java.util.Collection;
import java.util.Optional;

import pt.vb.device.exception.DeviceNotFoundException;
import pt.vb.device.model.Device;

public interface IDeviceService {
	Device addDevice(String name, String brand);
	Device getDeviceById(Long id) throws DeviceNotFoundException;
	Collection<Device> listAllDevices();
	Device updateDevice(Long id, Optional<String> name, Optional<String> brand);
	void deleteDevice(Long id) throws DeviceNotFoundException;
	Collection<Device> getDevicesByBrand(String brand) throws DeviceNotFoundException;
}
