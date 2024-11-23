package com.teja.rac.or.pkg.old;

import java.io.File;

public class JavaSecurity
{
    public void doSecChanges() throws Exception
    {
	File metafile = File.createTempFile("meta", ".json");
	final Byte[] bytes = new Byte[10];
	for (int i = 0; i != bytes.length; i++)
	{
	    System.out.println("count:" + i);
	}
    }
}
