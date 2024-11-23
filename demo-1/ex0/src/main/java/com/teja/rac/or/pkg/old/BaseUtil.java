package com.teja.rac.or.pkg.old;

// this class can be final given the private constructor
class BaseUtil
{
    private BaseUtil()
    {
    }

    public void defaultInWrongSpot()
    {
	int counter = 3;

	// default should be last
	switch (counter)
	{
	    case 1:
		break;
	    case 2:
		break;
	    default:
		System.out.println("default");
		break;
	    case 3:
		System.out.println("case 3");
	}
    }

    static public strictfp Integer importantValue()
    {
	return 42;
    }
}
