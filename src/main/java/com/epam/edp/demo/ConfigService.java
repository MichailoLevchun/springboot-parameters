package com.epam.edp.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ConfigService {

    @Value("${application.properties.from.configmap:}")
    private String configMapPath;

    @Value("${application.secret.properties.from.secret:}")
    private String secretPath;

    private String configProperties;
    private String secretProperties;

    @PostConstruct
    public void init() throws IOException {
        if (!configMapPath.isEmpty()) {
            configProperties = new String(Files.readAllBytes(Paths.get(configMapPath)));
        }
        if (!secretPath.isEmpty()) {
            secretProperties = new String(Files.readAllBytes(Paths.get(secretPath)));
        }
    }

    public String getConfigProperties() {
        return configProperties;
    }

    public String getSecretProperties() {
        return secretProperties;
    }
}
