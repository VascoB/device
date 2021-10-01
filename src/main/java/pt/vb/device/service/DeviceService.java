package pt.vb.device.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.vb.device.exception.DeviceExistsEception;
import pt.vb.device.exception.DeviceNotFoundException;
import pt.vb.device.model.Device;
import pt.vb.device.repository.DeviceRepository;
import pt.vb.device.service.bo.IDeviceService;

@Service
public class DeviceService implements IDeviceService {
	
	@Autowired
	private DeviceRepository repo;

	@Override
	public Device addDevice(String name, String brand) throws DeviceExistsEception {
		List<Device> existingDevices = getDevicesByBrand(brand).stream().collect(Collectors.toList());
		if (existingDevices != null && existingDevices.stream().filter(d->d.getName().equals(name)).findFirst().isPresent()) {
			throw new DeviceExistsEception("Device with brand: " + brand + " and name: " + name + " already exists!");
		}
		return repo.save(new Device(name, brand));
		
	}

	@Override
	public Device getDeviceById(Long id) throws DeviceNotFoundException {
		return repo.findById(id).orElseThrow(() -> new DeviceNotFoundException("Unable too find device with id: " + id));
	}

	@Override
	public Collection<Device> listAllDevices() {
		return repo.getAllDevices().stream().collect(Collectors.toList());
	}

	@Override
	public Device updateDevice(Long id, Optional<String> name, Optional<String> brand) {
		Device device;
		try {
			device = getDeviceById(id);
		} catch (Exception e) {
			throw new DeviceNotFoundException(e.getMessage());
		}
		
		if (name != null && name.isPresent()) {
			device.setName(name.get());
		}
		if (brand != null && brand.isPresent()) {
			device.setBrand(brand.get());
		}
		
		return repo.save(device);
		
	}

	@Override
	public void deleteDevice(Long id) throws DeviceNotFoundException {
		if (repo.findById(id).isPresent()){
			repo.deleteById(id);
        }
        else throw new DeviceNotFoundException("Unable too find device with id: " + id);
		
		
	}

	@Override
	public Collection<Device> getDevicesByBrand(String brand) throws DeviceNotFoundException {
		return repo.getDeviceByBrand(brand);
		
	}

}
