package com.zhang.roughshop.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "roughshop.auth")
public class UserProperties {

    private List<String> noAuthUrls;
}
