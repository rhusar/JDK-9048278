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
}
