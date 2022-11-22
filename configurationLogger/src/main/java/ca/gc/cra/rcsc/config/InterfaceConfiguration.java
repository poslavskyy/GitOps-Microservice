import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import java.util.Optional;

@ConfigMapping(prefix = "poc")
public interface InterfaceConfiguration {

// Note: If you do not provide values for the class properties, the application fails to compile and you receive a javax.enterprise.inject.spi.DeploymentException that indicates a missing value. 
// This does not apply to Optional fields and fields with a default value.

    String name();
    Optional<String> lifecycle();
    PocTeamDeets team();
    PocPhaseDeets phase();

    interface PocTeamDeets {
        String name();
        String developerCount();
    }

    interface PocPhaseDeets {
        String name();
        String status();
    }
}