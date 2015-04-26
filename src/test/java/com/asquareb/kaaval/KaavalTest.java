package com.asquareb.kaaval;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class KaavalTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public KaavalTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( KaavalTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testKaaval()
    {
        assertTrue( true );
    }
}
