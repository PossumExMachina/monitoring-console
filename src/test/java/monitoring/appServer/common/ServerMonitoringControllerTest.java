package monitoring.appServer.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import monitoring.serverResources.ServerDataController;
import monitoring.serverResources.ServerDataService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ServerDataController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ServerMonitoringControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServerDataService serverService;

    @Autowired
    private ObjectMapper objectMapper;


}