package pt.vb.device;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import pt.vb.device.exception.DeviceNotFoundException;
import pt.vb.device.model.Device;
import pt.vb.device.service.bo.IDeviceService;

@DataJpaTest
@ActiveProfiles("test")
class DevicePrecistenceTest {
	
	@Autowired
	private IDeviceService service;

	@Test
    public void shouldAddDevice() {
		Device device = new Device("test", "test");
        Device savedDevice = service.addDevice("test", "test");
        assertThat(savedDevice).usingRecursiveComparison().ignoringFields("id", "creationTime").isEqualTo(device);
    }
	
	@Test
    public void shouldUpdateDevice() {
		Device device = new Device("test", "test");
        Device savedDevice = service.addDevice("test", "test");
        service.updateDevice(savedDevice.getId(), Optional.of("new_test"),  Optional.of("new_test"));
        assertThat(savedDevice).usingRecursiveComparison().ignoringFields("id", "creationTime").isNotEqualTo(device);
    }
	
	@Test
    public void shouldDeleteDevice() {
        Device savedDevice = service.addDevice("test", "test");
        assertThat(service.listAllDevices().size()).isEqualTo(1);
        service.deleteDevice(savedDevice.getId());
        Throwable thrown = catchThrowable(() -> service.getDeviceById(savedDevice.getId()));

		// then
		assertThat(thrown).isInstanceOf(DeviceNotFoundException.class);
		assertThat(service.listAllDevices().size()).isEqualTo(0);
    }

	@Test
    public void shouldGetDeviceById() {
        Device savedDevice = service.addDevice("test", "test");
        Device device = service.getDeviceById(savedDevice.getId());
        assertThat(savedDevice).usingRecursiveComparison().ignoringFields("id", "creationTime").isEqualTo(device);
    }
	
	@Test
    public void shouldGetDeviceByBrand() {
        Device savedDevice = service.addDevice("test", "test");
        Device device = service.getDevicesByBrand(savedDevice.getBrand()).stream().findFirst().get();
        assertThat(savedDevice).usingRecursiveComparison().ignoringFields("id", "creationTime").isEqualTo(device);
    }
	
	@Test
    public void shouldGetAllDevices() {
        service.addDevice("test1", "test1");
        service.addDevice("test2", "test2");
        service.addDevice("test3", "test3");
        
        assertThat(service.listAllDevices().size()).isEqualTo(3);
    }
	


}
