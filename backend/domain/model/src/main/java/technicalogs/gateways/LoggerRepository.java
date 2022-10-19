package technicalogs.gateways;

public interface LoggerRepository {

    void debug(String message);
    void error(String message, Exception ex);
    void error(String message);
    void info(String message);
    void warn(String message, Exception ex);
    void warn(String message);
    void fatal(String message);
    void fatal(String message, Exception ex);
}