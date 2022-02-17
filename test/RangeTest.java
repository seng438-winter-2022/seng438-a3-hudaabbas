package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

public class RangeTest {
	
	private Range validRange;
    private Range stubRange;
    private Range exampleRange;
    
    // Setting up valid Range objects to use when testing the methods
    @Before
    public void setUp() throws Exception
    {
    	validRange = new Range(4, 10);
    	stubRange = new Range(-10, 10);
    	exampleRange = new Range(-11, 11);
    }
	    
	// =======================================
	// Tests for scale(Range, double): Range
	// =======================================
    
    // Test for invalid data where Range base is null
    // This test uses an invalid Range object where base is null and a valid 
    // factor value to test method scale(base, factor)
    @Test (expected = IllegalArgumentException.class)
    public void test_scale_invalidData_base()
    {
    	Range nullRange = null;
    	double factor = 2;
    	Range.scale(nullRange, factor);
    }
    
    // Test for invalid data where factor is negative
    // This test uses an invalid value for factor where factor is a negative number 
    // and a valid Range object for base to test the method scale(base, factor)
    @Test (expected = IllegalArgumentException.class)
    public void test_scale_invalidData_factor() 
    {
    	double factor = -2;
    	Range.scale(validRange, factor);
    }
    
    // Test for boundary value where factor is 0
    // This test uses a boundary value for factor and a valid Range object 
    // for base to test the method scale(base, factor)
    @Test 
    public void test_scale_invalidData_factorBoundary()
    {
    	double factor = 0;
    	Range actual = Range.scale(validRange, factor);
    	Range expected = new Range(0, 0);
    	assertEquals("Testing scale with a factor of 0", expected, actual);
    }
    
    // Test with valid data
    // This test uses a valid value for factor where factor is a positive number 
    // and a valid Range object for base to test the method scale(base, factor)
    @Test
    public void test_scale_invalidData_validData()
    {
    	double factor = 2;
    	Range expected = new Range(8, 20);
    	Range actual = Range.scale(validRange, factor);
    	assertEquals("Testing scale with valid arguments", expected, actual);
    }
   
	// =====================================
	// Tests for constrain(double): double
	// =====================================
    
    // Test for value below lower bound
    // This test uses a valid Range object and a valid value where the value 
    // is below the lower bound of the range to test the method constrain(value)
    @Test
    public void test_constrain_valueBelowLower()
    {
    	double actual = validRange.constrain(-2);
    	double expected = 4;
    	assertEquals("Testing constrain with value below lower bound", expected, actual, .000000001d);

    }
    
    // Test for value between lower bound and upper bound
    // This test uses a valid Range object and a valid value where the value 
    // is between the range to test the method constrain(value)
    @Test
    public void test_constrain_valueBetweenRange()
    {
    	double actual = validRange.constrain(9);
    	double expected = 9;
    	assertEquals("Testing constrain with value between the range", expected, actual, .000000001d);
    }
    
    // Test for value above upper bound
    // This test uses a valid Range object and a valid value where the value 
    // is above the upper bound of the range to test the method constrain(value)
    @Test
    public void test_constrain_valueAboveUpper()
    {
    	double actual = validRange.constrain(20);
    	double expected = 10;
    	assertEquals("Testing constrain with value above upper bound", expected, actual, .000000001d);
    }
    
	// Test for value at lower bound
	// This test uses a valid Range object and a valid value where the value 
    // is the boundary of the lower bound of the range to test the method constrain(value)
    @Test
    public void test_constrain_valueAtLower()
    {
    	double actual = validRange.constrain(4);
    	double expected = 4;
    	assertEquals("Testing constrain with value at boundary of lower bound", expected, actual, .000000001d);
    }
    
    // Test for value at upper bound
    // This test uses a valid Range object and a valid value where the value 
    // is the boundary of the upper bound of the range to test the method constrain(value)
    @Test
    public void test_constrain_valueAtUpper()
    {
    	double actual = validRange.constrain(10);
    	double expected = 10;
    	assertEquals("Testing constrain with value at boundary of upper bound", expected, actual, .000000001d);
    }
	    
	//=====================================
	// Tests for contains(double): double
	// =====================================
	 
    // Test for value below lower bound
    // This test uses a valid Range object and a valid value where the value 
    // is below the lower bound of the range to test the method contains(value)
	 @Test
	 public void test_contains_valueBelowLower()
	 {
	 	assertFalse("Testing contains with value below lower bound", validRange.contains(1));
	 }
	 
	 // Test for value between lower bound and upper bound
	 // This test uses a valid Range object and a valid value where the value 
	 // is between the range to test the method contains(value)
	 @Test
	 public void test_contains_valueBetweenRange()
	 {
	 	assertTrue("Testing contains with value between the range", validRange.contains(5));
	 }
	 
	 // Test for value above upper bound
	 // This test uses a valid Range object and a valid value where the value 
	 // is above the upper bound of the range to test the method contains(value)
	 @Test
	 public void test_contains_valueAboveUpper()
	 {
	 	assertFalse("Testing contains with value above upper bound", validRange.contains(15));
	 }
	 
	// Test for value at lower bound
	// This test uses a valid Range object and a valid value where the value 
	// is the boundary of the lower bound of the range to test the method contains(value)
	 @Test
	 public void test_contains_valueAtLower()
	 {
	 	assertTrue("Testing contains with value at boundary of lower bound", validRange.contains(4));
	 }
	 
	// Test for value at upper bound
	// This test uses a valid Range object and a valid value where the value 
	// is the boundary of the upper bound of the range to test the method contains(value)
	 @Test
	 public void test_contains_valueAtUpper()
	 {
	 	assertTrue("Testing contains with value at boundary of upper bound", validRange.contains(10));
	 }
    
	//========================================================
	// Tests for intersects(double b0, double b1): boolean
	//========================================================

	//Tests an invalid input of the lower bound of the intersect function
    @Test
    public void test_intersect_invalidDatab0() {
        assertFalse("Testing an invalid input of the lower bound of the intersect function", stubRange.intersects(11, 10));
    }
    
    //Tests invalid input of upper bound of the intersect function
    @Test
    public void test_intersect_invalidDatab1() {
       
        assertFalse("Testing invalid input of upper bound of the intersect function", stubRange.intersects(11, 8));
    }
    
    //Tests lower bound edge of the intersect function
    @Test
    public void test_intersects_edge_b0()
    {
        assertTrue("Testing lower bound edge of the intersect function", stubRange.intersects(-10, 12));
    }
    
    //Tests upper bound edge of the intersect function
    @Test
    public void test_intersects_edge_b1()
    {
        assertTrue("Testing upper bound edge of the intersect function", exampleRange.intersects(-10, 11));
    }
    
    //Tests upper and lower bound edges of the intersect function
    @Test
    public void test_intersects_bothIntersects()
    { 
        assertTrue("Testing upper and lower bound edges of the intersect function", exampleRange.intersects(-11, 11));
    }
    
    //Tests valid input of intersect function
    @Test
    public void test_intersects_validData()
    {
        assertTrue("Testing valid input of intersect function", exampleRange.intersects(-10, 10));
    }
    
    //Tests range grater than class range
    @Test
    public void test_intersects_validDataBeyondIntersects()
    {
        assertTrue("Testing range grater than class range", exampleRange.intersects(-20, 20));
    }
    
    //======================================
    // Tests for intersects(Range): boolean
    //======================================
    
    //Testing the intersects functions argument Range objects lower edge threshold
    @Test
    public void test_intersects_lower_edge()
    {
        Range stub= new Range(-10, 10);
        Range arg = new Range(-10, 12);
        assertTrue("Testing the intersects objects lower edge threshold", stub.intersects(arg));
    }
    
    //Testing the intersects functions argument Range objects upper edge threshold
    @Test
    public void test_intersects_upper_edge()
    {
        Range stub= new Range(-11, 11);
        Range arg = new Range(-10, 11);
        assertTrue("Testing the intersects functions argument Range objects upper edge threshold", stub.intersects(arg));
    }
    
    //Testing the intersects functions argument Range objects upper edge & lower edge threshold
    @Test
    public void test_intersects_upperAndLowerEdge()
    {
        Range stub= new Range(-11, 11);
        Range arg = new Range(-11, 11);
        assertTrue("Testing the intersects functions argument Range objects upper edge & lower edge threshold", stub.intersects(arg));
    }
    
    //Testing the intersects functions argument Range within the class object Range
    @Test
    public void test_intersects_withinRange()
    { 
        Range stub= new Range(-11, 11);
        Range arg = new Range(-10, 10);
        assertTrue("Testing the intersects functions argument Range within the class object Range", stub.intersects(arg));
    }
}