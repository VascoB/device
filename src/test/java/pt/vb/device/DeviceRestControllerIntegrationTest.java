package pt.vb.device;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import pt.vb.device.model.Device;
import pt.vb.device.service.bo.IDeviceService;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = 
  SpringBootTest.WebEnvironment.MOCK,
  classes = DeviceApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DeviceRestControllerIntegrationTest {
	 @Autowired
	    private MockMvc mvc;

	    @Autowired
	    private IDeviceService service;
	    
	    @Test
	    public void listAll()
	      throws Exception {
	    	
	    	Device d = service.addDevice("macbook", "apple");

	   

	        mvc.perform(get("/device/find/" + d.getId())
	          .contentType(MediaType.APPLICATION_JSON))
	          .andExpect(status().isOk())
	          .andExpect(content()
	          .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	          .andExpect(jsonPath("$.name", is("macbook")));
	    }
	    
	    @Test
	    public void updateDevice()
	      throws Exception {
	    	
	    	Device d = service.addDevice("ipad", "apple");
	    	service.updateDevice(d.getId(), Optional.of("ipad-pro"), null);

	   

	        mvc.perform(get("/device/all")
	          .contentType(MediaType.APPLICATION_JSON))
	          .andExpect(status().isOk())
	          .andExpect(content()
	          .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	          .andExpect(jsonPath("$[0].name", is("ipad-pro")))
	          .andExpect(jsonPath("$[0].brand", is("apple")));
	    }
	    
	    @Test
	    public void getDevicesByBrand()
	      throws Exception {
	    	
	    	Device d = service.addDevice("ipad", "apple");

	   

	        mvc.perform(get("/device/find")
	        .param("brand", d.getBrand())
	          .contentType(MediaType.APPLICATION_JSON))
	          .andExpect(status().isOk())
	          .andExpect(content()
	          .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	          .andExpect(jsonPath("$[0].name", is("ipad")))
	          .andExpect(jsonPath("$[0].brand", is("apple")));
	    }
	    
	    @Test
	    public void delete()
	      throws Exception {
	    	
	    	Device d = service.addDevice("ipad", "apple");

	   

	        mvc.perform(MockMvcRequestBuilders.delete("/device/delete/{id}", d.getId())
	          .contentType(MediaType.APPLICATION_JSON))
	          .andExpect(status().isOk());
	    }
}
