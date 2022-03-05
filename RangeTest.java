package org.jfree.data;

import static org.junit.Assert.*;

import org.junit.*;

public class RangeTest {
	
	private Range validRange;
    private Range stubRange;
    private Range exampleRange;
    private Range newRange;
    private Range testRange;
    private Range combineRange;
    private Range newValidRange;
    
    // Setting up valid Range objects to use when testing the methods
    @Before
    public void setUp() throws Exception
    {
    	validRange = new Range(4, 10);
    	stubRange = new Range(-10, 10);
    	exampleRange = new Range(-11, 11);
    	combineRange = new Range(-10,10);
    	newValidRange = new Range(-5, 5);
    }
	 // =============================================================================================================
	 // Lab 3 Tests
	 // =============================================================================================================
    
    // =======================================
  	// Tests for combine(Range range1, Range range2): Range
  	// =======================================
     
     //Testing invalid value, null for input variable.
     //This test uses a null value for both Range arguments.
     @Test
     public void test_combine_invalidData_null() {
     	Range actual = Range.combine(testRange, newRange);
     	assertEquals("Testing combine with null argument", null, actual);
     }
     
     //Testing invalid value, null for input variable.
     //THis test uses one null range argument and provides
     // a valid range of (-10, 10) for the other.
     @Test
     public void test_combine_invalidData_range1null()
     {
     	Range actual= Range.combine(newRange, stubRange);
     	Range expected= new Range(-10,10);
     	assertEquals("Testing combine with null argument", expected, actual);
     }
     
     //Testing invalid value, null for input variable.
     //This test uses a valid input variable of (-10, 10)
     // and a null invalid argument for the other.
     @Test
     public void test_combine_invalidData_rang2null() {
     	Range actual = Range.combine(stubRange, newRange);
     	Range expected= new Range(-10,10);
     	assertEquals("Testing combine with null argument", expected, actual);
     }
     
     //Testing edges of valid input data with non null arguments.
     //This test uses valid date with ranges (-10,10) and
     // (-10, 10) with equal ranges to increase branch coverage.
     @Test
     public void test_combine_validData_equalRanges()
     {
     	Range actual = Range.combine(combineRange, stubRange);
     	Range expected= new Range(-10,10);
     	assertEquals("Testing combine with null argument", expected, actual);
     }
     
     //Testing valid inputs with non null arguments.
     //This test uses valid data with two different ranges.
     @Test
     public void test_combine_validData_regular() 
     {
     	Range test1= new Range(-7,-2);
     	Range test2= new Range(11,20);
     	Range actual = Range.combine(test1, test2);
     	Range expected= new Range(-7,20);
     	assertEquals("Testing combine with null argument", expected, actual);
     }
     
 	// =======================================
 	//  Tests for expandToInclude(Range range, double value): Range
 	// =======================================
     
     //Testing invalid Data with null values for range object input
     //This test uses a null Range value and a value of 10.
     @Test
     public void test_expandToInclude_Crossing_invalidData_valueNull() {
     	Range actual = Range.expandToInclude(testRange, 10);
     	Range expected= new Range(10,10);
     	assertEquals("Testing expandToInclude with null argument", expected, actual);
     }
     
     //Testing valid Data with value less than range lower bound, to cover more branches
     //This test uses valid data with a Range value of (-10,10) and a value of -11
     @Test
     public void test_expandToInclude_validData_valueGreaterThan0() {
     	Range actual = Range.expandToInclude(combineRange, -11);
     	Range expected= new Range(-11,10);
     	assertEquals("Testing expandToInclude with value less than range lower bound, to cover more branches", expected, actual);
     }
     
     //Testing valid Data with value greater than range upper bound, to cover more branches
     //This test uses valid data with a Range value of (-10,10) and a value of 11
     @Test
     public void test_expandToInclude_validData_valueLessThan0() {
     	Range actual = Range.expandToInclude(combineRange, 11);
     	Range expected= new Range(-10,11);
     	assertEquals("Testing expandToInclude with valid Data with value greater than range upper bound, to cover more branche", expected, actual);
     }
     
     //Testing valid Data with equivalent values to increase branch coverage
     //Testing expandToInclude with valid Data with value greater than range upper bound, to cover more branches
     @Test
     public void test_expandToInclude_validData_valueEqualToZero() {
     	Range eqRange= new Range(10,10);
     	Range actual = Range.expandToInclude(eqRange, 10);
     	Range expected= new Range(10,10);
     	assertEquals("Testing expandToInclude with valid Data with value greater than range upper bound, to cover more branches", expected, actual);
     }
    
    // ============================================================ //
    // shift (Range base, double delta)
    // ============================================================ //
    
    	
    // Test for invalid data where Range base is null
    // This test uses an invalid Range object where base is null and a valid 
    // delta value to test method shift(base, delta)
       @Test (expected = IllegalArgumentException.class)
       public void test_shift_nullRange()
       {
           Range nullRange = null;
           double delta = 6;
           Range.shift(nullRange, delta);
       }
       
       
       // Test for shift from positive to zero and positive delta value
       // This test uses a valid Range object and a valid delta where the value 
       // is a positive number to test the method shift (Range base, double delta)
       @Test
       public void test_shift_positiveDelta()
       {
           double delta = 10;
           Range actual = Range.shift(newValidRange, delta);
           Range expected = new Range(0, 15);
           assertEquals("Testing shift with a delta of 5", expected, actual);
       }
       
       
       // Test for shift from negative to zero and negative delta value
       // This test uses a valid Range object and a valid delta where the value 
       // is a negative number to test the method shift (Range base, double delta)
       @Test
       public void test_shift_negativeDelta()
       {
           double delta = -5;
           Range actual = Range.shift(validRange, delta);
           Range expected = new Range(0, 5);
           assertEquals("Testing shift with a delta of -15", expected, actual);
           
       }
       
       // Test for zero delta value
       // This test uses a valid Range object and a valid delta where the value 
       // is a zero (boundary value) to test the method shift (Range base, double delta)
       @Test
       public void test_shift_zeroDelta()
       {
           double delta = 0;
           Range actual = Range.shift(validRange, delta);
           Range expected = new Range(4, 10);
           assertEquals("Testing shift with a delta of 0", expected, actual);
           
       }
       
       
       // ============================================================ //
       // shift (Range base, double delta, boolean allowZeroCrossing)
       // ============================================================ //
       
       
       // Test for invalid data where Range base is null
       // This test uses an invalid Range object where base is null and a valid 
       // delta value to test method shift(base, delta, allowZeroCrossing)
       @Test (expected = IllegalArgumentException.class)
       public void test_shift_override_nullRange()
       {
           Range nullRange = null;
           double delta = 6;
           Range.shift(nullRange, delta, true);
       }
       
       // Test for positive delta value and true allow for crossing zero. Tests lower bound crossing zero
       // This test uses a valid Range object and a valid delta where the value 
       // is a positive number and allow for crossing zero is true to test the method shift (Range base, double delta, boolean allowZeroCrossing)
       @Test
       public void test_shift_override_positiveDeltaTrue()
       {
           double delta = 10;
           Range actual = Range.shift(newValidRange, delta, true);
           Range expected = new Range(5, 15);
           assertEquals("Testing shift with a delta of 10 and zero crossing true", expected, actual);
       }
       
       
       // Test for positive delta value and false allow for crossing zero from. Tests lower bound not crossing zero
       // This test uses a valid Range object and a valid delta where the value 
       // is a positive number and allow for crossing zero is false to test the method shift (Range base, double delta, boolean allowZeroCrossing)
       @Test
       public void test_shift_override_positiveDeltaFalse()
       {
           double delta = 10;
           Range actual = Range.shift(newValidRange, delta, false);
           Range expected = new Range(0, 15);
           assertEquals("Testing shift with a delta of 10 and zero crossing false", expected, actual);
           
       }
       
       
       // Test for negative delta value and true allow for crossing zero. Tests upper bound crossing zero
       // This test uses a valid Range object and a valid delta where the value 
       // is a negative number and allow for crossing zero is true to test the method shift (Range base, double delta, boolean allowZeroCrossing)
       @Test
       public void test_shift_override_negativeDeltaTrue()
       {
           double delta = -10;
           Range actual = Range.shift(newValidRange, delta, true);
           Range expected = new Range(-15, -5);
           assertEquals("Testing shift with a delta of -10 and zero crossing true", expected, actual);
           
       }
       
       
       // Test for negative delta value and false allow for crossing zero. Tests upper bound not crossing zero
       // This test uses a valid Range object and a valid delta where the value 
       // is a negative number and allow for crossing zero is false to test the method shift (Range base, double delta, boolean allowZeroCrossing)
       @Test
       public void test_shift_override_negativeDeltaFalse()
       {
           double delta = -10;
           Range actual = Range.shift(newValidRange, delta, false);
           Range expected = new Range(-15, 0);
           assertEquals("Testing shift with a delta of -10 and zero crossing false", expected, actual);
           
       }
       
       
       // Test for zero delta value and true allow for crossing zero. 
       // This test uses a valid Range object and a valid delta where the value 
       // is zero and allow for crossing zero is true to test the method shift (Range base, double delta, boolean allowZeroCrossing)       
       @Test
       public void test_shift_override_zeroDeltaTrue()
       {
           double delta = 0;
           Range actual = Range.shift(newValidRange, delta, true);
           Range expected = new Range(-5, 5);
           assertEquals("Testing shift with a delta of 0 and zero crossing true", expected, actual);
           
       }
       
       // Test for zero delta value and false allow for crossing zero
       // This test uses a valid Range object and a valid delta where the value 
       // is zero and allow for crossing zero is false to test the method shift (Range base, double delta, boolean allowZeroCrossing)    
       @Test
       public void test_shift_override_zeroDeltaFalse()
       {
           double delta = 0;
           Range actual = Range.shift(newValidRange, delta, false);
           Range expected = new Range(-5, 5);
           assertEquals("Testing shift with a delta of 0 and zero crossing false", expected, actual);
           
       }

 // =============================================================================================================
 // END Lab 3 Tests
 // =============================================================================================================
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
    public void test_intersect_invalidDatab0() 
    {
        assertFalse("Testing an invalid input of the lower bound of the intersect function", stubRange.intersects(11, 10));
    }
    
    //Tests invalid input of upper bound of the intersect function
    @Test
    public void test_intersect_invalidDatab1()
    {
       
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
    
    
    //======================================
    // Tests for hashCode(): int
    //======================================
    
  //This test covers the valid class partition for the hashCode method
    @Test
    public void test_hashCode_valid() {
    	Range stub = new Range(10, 11);
    	int expected = -2076573696;
        assertEquals("Testing the hashcode functions Range within the class object Range", expected, stub.hashCode());
    }
    
    //This test covers the invalid class/partition for the Range class where lower is great than upper
    @Test(expected = IllegalArgumentException.class)
    public void test_hashCode_invalid() {
    	Range stub = new Range(10, 0);
    }
    
    // ============================================================ //
    // Tests for equals(Object obj)
    // ============================================================ //
    
    
    // Test for equals for branch if(not range object)
    // This test uses a non Range object to test the method equals(Object obj)
    @Test
    public void test_equals_notRangeObj()
    {
        String str = new String("This is a Test");
        boolean actual = newValidRange.equals(str);
        boolean expected = false;
        assertEquals("Testing equals with non Range object", expected, actual);
    }
    
    
    // Test for equals for an input of an equal Range object
    // This test uses an equal Range object to test the method equals(Object obj)
    @Test
    public void test_equals_equalRange()
    {
            Range obj = new Range(-5, 5);
            boolean actual = newValidRange.equals(obj);
            boolean expected = true;
            assertEquals("Testing equals with equal Range object", expected, actual);
    }
        
    // Test for equals for branch when the lower value does not match
    // This test uses a unequal Range object to test the method equals(Object obj)
    @Test 
    public void test_equals_lowerBranch()
    {
        Range obj = new Range(-1, 5);
        boolean actual = newValidRange.equals(obj);
        boolean expected = false;
        assertEquals("Testing equals with not equal lower value", expected, actual);
    }
      
    // Test for equals for branch when the upper value does not match
    // This test uses a unequal Range object to test the method equals(Object obj)
    @Test
    public void test_equals_upperBranch()
    {
        Range obj = new Range(-5, 30);
        boolean actual = newValidRange.equals(obj);
        boolean expected = false;
        assertEquals("Testing equals with not equal upper value", expected, actual);
    }
    
    // ============================================================ //
    // expand(Range range,double lowerMargin, double upperMargin)
    // ============================================================ //

    //Test for expand, testing null input for Range input
    @Test (expected = IllegalArgumentException.class)
   public void test_expand_null() {
       Range actual = Range.expand(testRange, 10, 10);
   }

    //Test for expand, testing non null parameters
    @Test
    public void test_validData_expand() {
        Range big_lower_margin= new Range(5,10);
        Range actual = Range.expand(big_lower_margin, 0.5, 5);
        Range expected= new Range(2.5,35);
        assertEquals("Testing expandToInclude with valid Data with value greater than range upper bound, to cover more branches", expected, actual);

    }

    //Test for expand, testing non null parameters where
    //lower is greater than upper
    @Test
    public void test_validData_expand_LowerGreaterThanUpper() {
        Range big_lower_margin= new Range(9,10);
        Range actual = Range.expand(big_lower_margin, 0.5, -5);
        Range expected= new Range(6.75,6.75);
        assertEquals("Test for expand, testing non null parameters where lower is greater than upper", expected, actual);
    }
    
    //================================================================
    // Tests for combineIgnoringNaN(Range range1, Range range2): Range
    //================================================================

    //Testing the combineIgnoringNaN with both values equaling to null for the first range object and the second range object
    @Test
    public void test_combineIgnoringNaN_both_ranges_null_value()
    {
        Range object1 = null; 
        Range object2 = null; 
        Range actual = Range.combineIgnoringNaN(object1, object2);
        Range expected = null;
        assertEquals("Testing the null value for both ranges", expected, actual);
    }
    //Testing the combineIgnoringNaN with first Range value being null and second range object is isNaN
    @Test
    public void test_combineIgnoringNaN_range1_null_only()
    {
        Range actual = Range.combineIgnoringNaN(null, new Range(Double.NaN, Double.NaN));
        Range expected = null; 
        assertEquals("Testing the null value for the first range and nan_number for the second", expected, actual);
    }
    //Testing the comineIgnoringNaN with first object value isNaN and second object value being null
    @Test
    public void test_combineIgnoringNaN_range2_null_only()
    {
        Range actual = Range.combineIgnoringNaN(new Range(Double.NaN, Double.NaN), null);
        Range expected = null; 
        assertEquals("Testing the null value for the first range and null for the second", expected, actual);
    }
    //Testing the combineIgnoringNaN with both values being valid
    @Test
    public void test_combineIgnoringNaN_valid_values()
    {
        Range actual = Range.combineIgnoringNaN(new Range(2,3), new Range(4,5));
        Range expected = new Range(2,5);
        assertEquals("Testing both objects with valid values", expected, actual);
    }
}