package io.github.plizzzhealme.controller.listener;

import io.github.plizzzhealme.controller.exception.ControllerException;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("/controller")
public class ControllerListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(ControllerListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServiceFactory.INSTANCE.getConnectionService().connect();
        } catch (ServiceException e) {
            logger.error("Error with database connection", e);

            throw new ControllerException();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServiceFactory.INSTANCE.getConnectionService().disconnect();
    }
}
