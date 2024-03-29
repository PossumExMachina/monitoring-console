package monitoring.appServer.tomcat;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Service
public class TomcatService {

    private static final Logger logger = LoggerFactory.getLogger(TomcatService.class);


    /**
     * Checks and returns the current state of the Tomcat server.
     *
     * Executes a bash command to determine if the 'catalina.startup.Bootstrap' process
     * is running, indicating that Tomcat is active. Returns 'RUNNING' if the process is found,
     * 'STOPPED' if not found, and 'UNKNOWN' in case of any errors during execution.
     *
     * @return State The state of the Tomcat server - RUNNING, STOPPED, or UNKNOWN.
     */
    @SneakyThrows
    public String getTomcatState() {
        String[] command = {"bash", "-c", "ps aux | grep [c]atalina.startup.Bootstrap"};
        Process process = Runtime.getRuntime().exec(command);
        logger.info("Creating process to check Tomcat state");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            process.waitFor();
            logger.info("Waiiting for the checking process to finish");
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("catalina")) {
                    logger.info("Tomcat is running");
                    return "RUNNING";
                }
            }
        } catch (IOException e) {
            logger.error("Could not check Tomcat state", e);
            return "UNKNOWN";
        } catch (InterruptedException e) {
            logger.error("Interrupted while waiting for the process", e);
            Thread.currentThread().interrupt();
            return "UNKNOWN";
        }
     finally {
        process.destroy();
    }
        return "STOPPED";
    }

}




