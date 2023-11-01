package co.com.cleanarchitecture.technical.logs.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import technicalogs.gateways.LoggerRepository;


@Repository
public class LoggerAdapter implements LoggerRepository {

    private static final Logger logger = LogManager.getLogger(LoggerAdapter.class);


    public LoggerAdapter() {
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void error(String message, Exception ex) {
        logger.error(getLogger(message, ex));
    }

    @Override
    public void error(String message) {
        logger.error(getLogger(message));
    }

    @Override
    public void info(String message) {
        logger.info(getLogger(message));
    }

    @Override
    public void warn(String message, Exception ex) {
        logger.warn(getLogger(message, ex));
    }

    @Override
    public void warn(String message) {
        logger.fatal(getLogger(message));
    }

    @Override
    public void fatal(String message) {
        logger.fatal(getLogger(message));
    }

    @Override
    public void fatal(String message, Exception ex) {
        logger.fatal(getLogger(message, ex));
    }

    private String getLogger(String message) {
        Gson gson = new Gson();
        LoggerModel log = new LoggerModel(
                message,
                "application",
                "service",
                "name"
        );
        return gson.toJson(log);
    }

    private String getLogger(String message, Exception ex) {
        Gson gson = new Gson();
        LoggerModel log = new LoggerModel(
                "",
                "",
                "", ex
        );
        return gson.toJson(log);
    }
}

