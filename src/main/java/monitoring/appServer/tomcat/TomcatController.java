package monitoring.appServer.tomcat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TomcatController {

    @Autowired
    private TomcatInfoService tomcatInfoService;

    @GetMapping("/tomcatInfo")
    public ResponseEntity<TomcatDTO> getResources(){
        TomcatDTO resourceData = tomcatInfoService.getTomcatStatus();
        return ResponseEntity.ok(resourceData);
    }

}
