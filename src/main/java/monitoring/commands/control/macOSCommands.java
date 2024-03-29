package monitoring.commands.control;

import org.springframework.stereotype.Component;

@Component
public final class macOSCommands implements CommandStrategy {
    public static final String dockerExists = "docker -v";
    public static final String dockerControlOutput = "Docker version";

 public static final String listContainers = "docker container ls -a --format '{{json .}}'";
    public static final String tomcatExists = "ls " + System.getenv("CATALINA_HOME");
    public static final String tomcatControlOutput = "webapps";
    public static final String startTomcat = "sudo systemctl start tomcat";
    public static final String stopTomcat = "sudo systemctl stop tomcat";
    public static final String getDiskUsage = "echo \"Filesystem Size Used Avail Use% Mounted on\"; df -h | awk 'NR>1 {print $1, $2, $3, $4, $5, $9}'";

    public static final String getFreeMemory = """
            vm_stat | awk -v FS="[^0-9]+" '
                /Pages free/ {free=$2}
                /Pages active/ {active=$2}
                /Pages inactive/ {inactive=$2}
                /Pages speculative/ {speculative=$2}
                /Pages wired down/ {wired=$2}
                END {
                    used=(active+inactive+wired)*4096/1048576;
                    total=(free+active+inactive+speculative+wired)*4096/1048576;
                    free=free*4096/1048576;
                    printf "Total\\tUsed\\tFree\\n";
                    printf "%d MB\\t%d MB\\t%d MB\\n", total, used, free
                }'
                """;

    @Override
    public String getDockerInstalledCmd() {
        return dockerExists;
    }

    @Override
    public String getFreeMemoryCmd() {
        return getFreeMemory;
    }

    @Override
    public String getListContainersCmd() {
        return listContainers;
    }

    @Override
    public String getStartTomcatCmd() {
        return startTomcat;
    }

    @Override
    public String getStopTomcatCmd() {
        return stopTomcat;
    }

    @Override
    public String getTomcatInstalledCmd() {
        return tomcatExists;
    }

    @Override
    public String getTomcatControlOutput() {
        return tomcatControlOutput;
    }

    @Override
    public String getDiskUsageCmd() {
        return getDiskUsage;
    }

    @Override
    public String getDockeControlOutput() {
        return dockerControlOutput;
    }

}
