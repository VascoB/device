package pt.vb.device.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.vb.device.model.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
	
	@Query(value="select * from device where id=:id", nativeQuery = true)
	Device getDeviceByIdentifier(Long id);
	
	@Query(value="select * from device where brand=:brand", nativeQuery = true)
	Collection<Device> getDeviceByBrand(String brand);
	
	@Query(value="select * from device", nativeQuery = true)
	Collection<Device> getAllDevices();
}
