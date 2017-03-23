import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Radoslav Husar
 */
public class JChannelFactory {
    void createChannel() {
        ProtocolStackConfiguration configuration = new ProtocolStackConfiguration();
        ProtocolStack stack = new ProtocolStack();
        stack.addProtocols(Stream.of(
                Stream.of(configuration.getTransport()),
                configuration.getProtocols().stream()
        ).flatMap(Function.identity()).filter(Objects::nonNull).map(pc -> pc.createProtocol(configuration)).collect(Collectors.toList()));
    }

    public abstract static class Protocol {
    }

    public interface ProtocolConfiguration<P extends Protocol> {
        P createProtocol(ProtocolStackConfiguration stackConfiguration);
    }

    static class ProtocolStackConfiguration {

        ProtocolConfiguration<? extends TP> getTransport() {
            return null;
        }

        List<ProtocolConfiguration<? extends Protocol>> getProtocols() {
            return null;
        }
    }

    static class TP extends Protocol {
    }

    public static class ProtocolStack {

        public ProtocolStack addProtocols(Protocol... prots) {
            return this;
        }

        public ProtocolStack addProtocols(List<Protocol> prots) {
            return this;
        }

    }
}
