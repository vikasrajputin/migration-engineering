package com.teja.rac.or.pkg.old;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:secretprops.properties")
@ConfigurationPropertiesScan("com.teja.rac")
public class SampleConfiguration
{
}
