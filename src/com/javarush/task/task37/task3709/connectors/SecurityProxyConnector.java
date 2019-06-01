package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.*;

public class SecurityProxyConnector implements Connector {
    private SecurityChecker securityChecker = new SecurityCheckerImpl();
    private SimpleConnector simpleConnector;

    public SecurityProxyConnector(String s) {
        simpleConnector = new SimpleConnector(s);
    }

    @Override
    public void connect() {
        if (securityChecker.performSecurityCheck())
            simpleConnector.connect();
    }
}
