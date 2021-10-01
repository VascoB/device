package pt.vb.device.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import pt.vb.device.exception.DeviceWrongArgsException;

@Entity
public class Device {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	@Column(name="creation_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime creationTime;
	private String name;
	private String brand;
	
	public Device() {}
	public Device(String name, String brand) {
		checkArgs(name, brand);
		this.creationTime = LocalDateTime.now();
		this.name = name;
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	
	
	private void checkArgs(String name, String brand) {
		if (name == null || brand == null || name.trim().isEmpty() || brand.trim().isEmpty()) {
			throw new DeviceWrongArgsException("Device name and brand must be set!");
		}
		
	}
	
	
	
	
	
}
