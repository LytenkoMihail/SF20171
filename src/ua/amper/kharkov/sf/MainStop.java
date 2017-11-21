package ua.amper.kharkov.sf;

import org.apache.log4j.Logger;

import static ua.amper.kharkov.sf.SFConstants.LOGGER_END;

public class MainStop {
    private static final Logger LOGGER = Logger.getLogger(MainStop.class);
    public MainStop() {
        LOGGER.info(LOGGER_END);
        System.out.println(LOGGER_END);
        System.exit(0);

    }
}
