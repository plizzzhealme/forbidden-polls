package io.github.plizzzhealme.service;

import io.github.plizzzhealme.service.exception.ServiceException;

public interface ConnectionService {

    void connect() throws ServiceException;

    void disconnect();
}
