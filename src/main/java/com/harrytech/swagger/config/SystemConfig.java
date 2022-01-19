package com.harrytech.swagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>System Configuration class</h1>
 * <p>
 * This custom class stores and configure the basic data
 *
 * @author sk.chandra
 */
@Configuration
@ConfigurationProperties(prefix = "config")
@Data
public class SystemConfig {

    private String appName;

    private String appDescription;

    private String appVersion;

    private String appTos;

    private String appContactName;

    private String appContactUrl;

    private String appContactEmail;

    private String appLicense;

    private String appLicenseUrl;

    private String restrictionpath;


}
