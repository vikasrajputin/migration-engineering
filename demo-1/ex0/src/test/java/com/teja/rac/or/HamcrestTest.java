package com.teja.rac.or;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.blankString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.matchesRegex;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;

public class HamcrestTest
{

    @Test
    public void oneEqualsOne()
    {
	assertThat("See if 1 is still equal to 1", 1 == 1);
    }

    @Test
    public void oneLessThanOrEqualOne()
    {
	assertThat(1, lessThanOrEqualTo(1));
    }

    @Test
    public void twoGreaterThanOne()
    {
	assertThat(2, greaterThan(1));
    }

    @Test
    public void oneLessThanTwo()
    {
	assertThat(1, lessThan(2));
    }

    @Test
    public void twoHasToString()
    {
	assertThat(2, hasToString("2"));
    }

    @Test
    public void textIsEqual()
    {
	String text1 = "hello";
	String text2 = "hello";
	assertThat(text1, is(equalTo(text2)));
    }

    @Test
    public void textEquals()
    {
	String text1 = "hello";
	String text2 = "hello";
	assertThat(text1, equalTo(text2));
    }

    @Test
    public void nothingIsNotNull()
    {
	assertThat("nothing", notNullValue());
    }

    @Test
    public void twins()
    {
	String twin = "twin";
	assertThat(twin, sameInstance(twin));
    }

    @Test
    public void anInstanceOf()
    {
	String twin = "twin";
	assertThat(twin, instanceOf(String.class));
    }

    @Test
    public void containsIgnoringCase()
    {
	String twin = "containsIgnoringCase";
	assertThat(twin, containsStringIgnoringCase("ignor"));
    }

    @Test
    public void empty()
    {
	String text = "";
	assertThat(text, isEmptyString());
    }

    @Test
    public void verifyLength()
    {
	assertThat("Simon", hasLength(5));
    }

    @Test
    public void matchesBasicPassword()
    {
	assertThat("S@mplePass1!", matchesRegex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}$"));
    }

    @Test
    public void containsItem()
    {
	List<String> items = new ArrayList<>();
	items.add("Hamcrest");
	items.add("assertJ");

	assertThat(items, hasItem("Hamcrest"));
    }

    @Test
    public void notABlankString()
    {
	assertThat("OpenRewrite", not(blankString()));
    }
}
