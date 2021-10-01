package pt.vb.device.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DeviceRequest {
	
	@NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String brand;
    
    
	public DeviceRequest(@NotBlank @NotNull String name, @NotBlank @NotNull String brand) {
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
    
    
    

}
