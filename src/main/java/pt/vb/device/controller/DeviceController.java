package pt.vb.device.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiResponses;
import pt.vb.device.model.Device;
import pt.vb.device.payload.DeviceRequest;
import pt.vb.device.service.bo.IDeviceService;

@RestController
@RequestMapping("/device")
@ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized"),
        @io.swagger.annotations.ApiResponse(code = 500, message = "The server is down. Please bear with us."),
}
)
public class DeviceController {
	
	@Autowired
	private IDeviceService service;
	
	@GetMapping("/all")
    public ResponseEntity<List<Device>> getAllEmployees () {
        List<Device> devices = service.listAllDevices().stream().collect(Collectors.toList());
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Device> getDeviceById (@PathVariable("id") Long id) {
    	Device device = service.getDeviceById(id);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity<List<Device>> getDevicesByBrand (@RequestParam("brand") String brand) {
    	List<Device> devices = service.getDevicesByBrand(brand).stream()
    			.sorted((d1, d2) -> d1.getName().compareTo(d2.getName())).collect(Collectors.toList());
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Device> addDeviceWithParams(@RequestParam String name, @RequestParam String brand) {
       return addDevice(new DeviceRequest(name, brand));
    }
    @PostMapping("/add")
    public ResponseEntity<Device> addDevice(@RequestBody DeviceRequest device) {
        Device d = service.addDevice(device.getName(), device.getBrand());
        return new ResponseEntity<>(d, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Device> updateEmployee( @PathVariable Long id, @RequestParam Optional<String> name, @RequestParam Optional<String> brand) {
        Device device = service.updateDevice(id, name, brand);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        service.deleteDevice(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
