package com.teja.rac.or;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LogExample
{
    private Logger logger = LoggerFactory.getLogger(LogExample.class);

    private void method(final String aName, final double aPercent)
    {
	logger.debug("Process [" + aName + "] is at [" + aPercent * 100 + "%]");
    }

    private void asInteger(final String aNumberString)
    {
	try
	{
	    Integer i = Integer.valueOf(aNumberString);
	    System.out.println("Value of Integer is: " + i);
	}
	catch (NumberFormatException ex)
	{
	    logger.warn("some big error: " + ex.getMessage(), ex);
	}
    }
}