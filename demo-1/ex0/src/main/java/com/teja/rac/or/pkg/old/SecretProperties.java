package com.teja.rac.or.pkg.old;

import org.apache.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "secret")
@ConstructorBinding
public class SecretProperties
{
    Logger logger = Logger.getLogger(SecretProperties.class);

    private String life;

    public SecretProperties(final String aLife)
    {
	logger.info("The secret of aLife is: " + aLife);
	life = aLife;
    }
}
