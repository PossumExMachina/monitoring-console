package monitoring.docker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class ContainerRepository {

    @Autowired
    DockerContainerService dockerContainerService;


    /**
     * Retrieves a DockerContainer with a specific ID.
     *
     * Searches through available Docker containers and returns the one that matches the given ID.
     * If no matching container is found, returns null.
     *
     * @param id The ID of the Docker container to retrieve.
     * @return DockerContainer The Docker container with the specified ID, or null if not found.
     * @throws IOException If an I/O error occurs during the process.
     */
    public DockerContainer getContainerByID(String id) throws IOException {
        return dockerContainerService.parseDockerJson().stream()
                .filter(container -> container.getID().equals(id))
                .findFirst()
                .orElse(null);
    }
}
