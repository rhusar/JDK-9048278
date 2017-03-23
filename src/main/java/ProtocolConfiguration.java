/**
 * @author Radoslav Husar
 */
public interface ProtocolConfiguration<P extends Protocol> {
    P createProtocol(ProtocolStackConfiguration stackConfiguration);
}