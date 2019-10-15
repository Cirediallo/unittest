package roman;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class RomanNumberTest {
  public static final HashMap<Integer, String> KNOWN_VALUES = new HashMap<>();
  static {
    KNOWN_VALUES.put(1, "I");
    KNOWN_VALUES.put(2, "II");
    KNOWN_VALUES.put(3, "III");
    KNOWN_VALUES.put(4, "IV");
    KNOWN_VALUES.put(5, "V");
    KNOWN_VALUES.put(6, "VI");
    KNOWN_VALUES.put(7, "VII");
    KNOWN_VALUES.put(8, "VIII");
    KNOWN_VALUES.put(9, "IX");
    KNOWN_VALUES.put(10, "X");
    KNOWN_VALUES.put(50, "L");
    KNOWN_VALUES.put(100, "C");
    KNOWN_VALUES.put(500, "D");
    KNOWN_VALUES.put(1000, "M");
    KNOWN_VALUES.put(31, "XXXI");
    KNOWN_VALUES.put(148, "CXLVIII");
    KNOWN_VALUES.put(294, "CCXCIV");
    KNOWN_VALUES.put(312, "CCCXII");
    KNOWN_VALUES.put(421, "CDXXI");
    KNOWN_VALUES.put(528, "DXXVIII");
    KNOWN_VALUES.put(621, "DCXXI");
    KNOWN_VALUES.put(782, "DCCLXXXII");
    KNOWN_VALUES.put(870, "DCCCLXX");
    KNOWN_VALUES.put(941, "CMXLI");
    KNOWN_VALUES.put(1043, "MXLIII");
    KNOWN_VALUES.put(1110, "MCX");
    KNOWN_VALUES.put(1226, "MCCXXVI");
    KNOWN_VALUES.put(1301, "MCCCI");
    KNOWN_VALUES.put(1485, "MCDLXXXV");
    KNOWN_VALUES.put(1509, "MDIX");
    KNOWN_VALUES.put(1607, "MDCVII");
    KNOWN_VALUES.put(1754, "MDCCLIV");
    KNOWN_VALUES.put(1832, "MDCCCXXXII");
    KNOWN_VALUES.put(1993, "MCMXCIII");
    KNOWN_VALUES.put(2074, "MMLXXIV");
    KNOWN_VALUES.put(2152, "MMCLII");
    KNOWN_VALUES.put(2212, "MMCCXII");
    KNOWN_VALUES.put(2343, "MMCCCXLIII");
    KNOWN_VALUES.put(2499, "MMCDXCIX");
    KNOWN_VALUES.put(2574, "MMDLXXIV");
    KNOWN_VALUES.put(2646, "MMDCXLVI");
    KNOWN_VALUES.put(2723, "MMDCCXXIII");
    KNOWN_VALUES.put(2892, "MMDCCCXCII");
    KNOWN_VALUES.put(2975, "MMCMLXXV");
    KNOWN_VALUES.put(3051, "MMMLI");
    KNOWN_VALUES.put(3185, "MMMCLXXXV");
    KNOWN_VALUES.put(3250, "MMMCCL");
    KNOWN_VALUES.put(3313, "MMMCCCXIII");
    KNOWN_VALUES.put(3408, "MMMCDVIII");
    KNOWN_VALUES.put(3501, "MMMDI");
    KNOWN_VALUES.put(3610, "MMMDCX");
    KNOWN_VALUES.put(3743, "MMMDCCXLIII");
    KNOWN_VALUES.put(3844, "MMMDCCCXLIV");
    KNOWN_VALUES.put(3888, "MMMDCCCLXXXVIII");
    KNOWN_VALUES.put(3940, "MMMCMXL");
    KNOWN_VALUES.put(3999, "MMMCMXCIX");
  }

  // String -> RomanNumber
  @Test
  public void fromRomanKnownValues() {
    for (Map.Entry<Integer, String> v : KNOWN_VALUES.entrySet()) {
      assertThat(RomanNumber.valueOf(v.getValue()).intValue(), is(v.getKey()));
    }
  }

  // int -> RomanNumber
  @Test(expected = IllegalArgumentException.class)
  public void toRomanZero() {
    RomanNumber.valueOf(0);
  }

	@Test
	public void fromDecimalKnownValues()
	{
		for( Map.Entry<Integer, String> v : KNOWN_VALUES.entrySet() )
		{
			assertThat(RomanNumber.valueOf(v.getKey()).toString(), is(v.getValue()));
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void toRomanNegative()
	{
		RomanNumber.valueOf(-5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void toRomanOutOfBounds()
	{
		RomanNumber.valueOf(6000);
	}
	
	@Test
	public void tropDeRepetitions()
	{
		String[] romanValues = {"CCCC", "XXXX", "IIII", "VVVVI"};
		int counter = 0;
		
		for( String s : romanValues )
		{
			try
			{
				RomanNumber.valueOf(s);
			}
			catch( IllegalArgumentException e )
			{
				counter++;
			}
		}
		
		assertThat(counter, is(romanValues.length));
	} 
	
	@Test
	public void repetitionsPaire()
	{
		String[] romanValues = {"XXCC", "XXVV", "XXVVII"};
		int counter = 0;
		
		for( String s : romanValues )
		{
			try
			{
				RomanNumber.valueOf(s);
			}
			catch( IllegalArgumentException e )
			{
				counter++;
			}
		}
		
		assertThat(counter, is(romanValues.length));
	}
	
	@Test
	public void antecedantIncorrect()
	{
		String[] romanValues = {"XCX", "VX"};
		int counter = 0;
		
		for( String s : romanValues )
		{
			try
			{
				RomanNumber.valueOf(s);
			}
			catch( IllegalArgumentException e )
			{
				counter++;
			}
		}
		
		assertThat(counter, is(romanValues.length));
	}
	
	@Test
	public void testIntervalleInteger()
	{
		for( int i = 1; i < 4000; ++i )
		{
			assertThat(RomanNumber.valueOf(i).intValue(), is(i));
		}
	}
	
	@Test
	public void romanNumbersUppercase()
	{
		String ret = RomanNumber.valueOf(50).toString();
		int test_val = ret.length();
		for( int i = 0; i < ret.length(); ++i )
		{
			if( Character.isLowerCase(ret.charAt(i)) )
			{
				test_val--;
			}
		}
		
		assertThat(test_val, is(ret.length()));
	}
	
	@Test
	public void romanNumberCapitalArgument()
	{
		int caught = 0;
		String[] invalids = {"ii", "Iv", "iV", "mX"};
		
		for( String s : invalids )
		{
			try
			{
				RomanNumber.valueOf(s);
			}
			catch( IllegalArgumentException e )
			{
				caught++;
			}
		}
		
		assertThat(caught, is(invalids.length));
	}

}
