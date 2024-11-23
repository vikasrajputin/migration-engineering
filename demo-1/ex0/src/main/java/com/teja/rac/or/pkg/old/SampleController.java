package com.teja.rac.or.pkg.old;

import com.google.common.base.Optional;
import com.google.common.math.IntMath;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SampleController
{
    private static final String ACCOUNT_KEY =
	    "lJzRc1YdHaAA2KCNJJ1tkYwF/+mKK6Ygw0NGe170Xu592euJv2wYUtBlV8z+qnlcNQSnIYVTkLWntUO1F8j8rQ==";
    private static final int MAX_LOANABLE_BOOKS = 10;
    private Logger logger = Logger.getLogger(SampleController.class);

    @Autowired
    private SampleService sampleService;

    @RequestMapping(method = RequestMethod.GET, value = "/greet/{name}")
    public String greet(@PathVariable("name") String name)
    {
	logger.info("We're responding to " + name);
	return "Hello " + name;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/remainingLoanableBooks/{name}")
    public int remainingLoanableBooks(@PathVariable("name") String name)
    {
	Optional<List<String>> loanedBooks = sampleService.getBooks(name);
	if (loanedBooks.isPresent())
	{
	    return IntMath.checkedSubtract(MAX_LOANABLE_BOOKS, loanedBooks.get().size());
	}
	return 0;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/oddMethod/{name}")
    public String oddMethod(@PathVariable("name") String aName)
    {
	int value = 5, compare = 10;
	boolean start = new SomethingSerializable(value, compare).important();

	// useless to alter case
	boolean sameName = "simon".equalsIgnoreCase(aName.toLowerCase());

	// useless empty block
	switch (0)
	{
	}

	// a counter shouldn't be updated in a for loop
	for (int counter = 0; counter <= 10; )
	{
	    System.out.println(counter);
	    counter++;
	}

	// these can be chained
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("We would" + "really like");
	stringBuilder.append("to have " + "text blocks");

	// we could just use the diamond operator
	List<Integer> numberList = new ArrayList<Integer>();
	numberList.add(1);
	numberList.add(2);
	numberList.add(3);
	System.out.println(numberList);

	// quite useful to call toString on a String, and nice to have some extra semicolons
	return stringBuilder.toString().toString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/patternMatch")
    public String patternMatch()
    {
	logIfPatternMatches("patternMatching");
	logIfPatternMatches(BigDecimal.TEN);
	return "We hope to use java 17s instanceof pattern in the invoked function ";
    }


    private void logIfPatternMatches(final Object aInput)
    {
	if (aInput instanceof String && ((String) (aInput)).length() > 0)
	{
	    logger.info(aInput);
	}
	else if (aInput instanceof BigDecimal && ((BigDecimal) (aInput)).intValue() > 5)
	{
	    logger.info(aInput);
	}
    }

    static interface SomeInterface
    {
	boolean important();
    }

    static class SomethingSerializable implements Serializable,
						  SomeInterface
    {
	// should be final
	private int IMPORTANT_VALUE = 42;
	private int value;
	private int compare;

	public SomethingSerializable(final int aValue, int aCompare)
	{
	    value = aValue;
	    compare = aCompare;
	}

	// should be annotated with @Override
	public boolean important()
	{
	    // should not be inverted
	    return !(value == compare);
	}
    }
}