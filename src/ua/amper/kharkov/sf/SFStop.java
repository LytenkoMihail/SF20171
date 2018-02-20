package ua.amper.kharkov.sf;

import org.apache.log4j.Logger;

import static ua.amper.kharkov.sf.SFConstants.LOGGER_END;

public class SFStop {
    private static final Logger LOGGER = Logger.getLogger(SFStop.class);

    public void stop() {
        System.out.println(LOGGER_END);
        System.exit(0);

    }

    public SFStop() {
        LOGGER.info(LOGGER_END);

    }
}
