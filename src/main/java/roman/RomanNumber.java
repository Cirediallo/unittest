
package roman;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class RomanNumber extends Number {

  public static final long serialVersionUID = 1L;

  // Table des symboles
  private static final LinkedHashMap<String, Integer> SYMBOLS = new LinkedHashMap<>();
  static {
    SYMBOLS.put("M",  1000);
    SYMBOLS.put("CM", 900);
    SYMBOLS.put("D",  500);
    SYMBOLS.put("CD", 400);
    SYMBOLS.put("C",  100);
    SYMBOLS.put("XC", 90);
    SYMBOLS.put("L",  50);
    SYMBOLS.put("XL", 40);
    SYMBOLS.put("X",  10);
    SYMBOLS.put("IX", 9);
    SYMBOLS.put("V",  5);
    SYMBOLS.put("IV", 4);
    SYMBOLS.put("I", 1);
  }

  // Expression reguliere de validation
  private static final Pattern VALIDATION_RE = Pattern.compile(
      "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

  private final int value;

	public RomanNumber(String romanValue)
	{
		Matcher m = VALIDATION_RE.matcher(romanValue);
		if( !m.find() )
		{
			throw new IllegalArgumentException("Invalid roman value");
		}
		for( int i = 0; i < romanValue.length(); ++i )
		{
			if( Character.isLowerCase(romanValue.charAt(i)) )
			{
				throw new IllegalArgumentException("tous les caracteres doivent etre majuscules");
			}
		}
		
		this.value = fromRoman(romanValue);
	}

	public RomanNumber(int value)
	{
		if( value == 0 )
		{
			throw new IllegalArgumentException("Number can not be 0");
		}
		else if( value < 0 )
		{
			throw new IllegalArgumentException("Nombre ne doit pas etre negatif");
		}
		else if( value > 3999 )
		{
			throw new IllegalArgumentException("Nombre ne doit pas depasser 3999");
		}
		this.value = value;
	}

  /**
   * @{inheritDoc}
   */
  @Override
  public double doubleValue() {
    // TODO
    return 0;
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public float floatValue() {
    // TODO
    return 0;
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public int intValue() {
    return this.value;
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public long longValue() {
    // TODO
    return 0;
  }

  @Override
  public String toString() {
    return toRoman(this.value);
  }

  public static RomanNumber valueOf(String roman) {
    return new RomanNumber(roman);
  }

  public static RomanNumber valueOf(int value) {
    return new RomanNumber(value);
  }

	private static int fromRoman(String romanStr)
	{
		// cette fonction ne marche pas avec openjdk sous ubuntu
		// int result = 0;
		// int index = 0;
		// int romanStr_len = romanStr.length();
		
		// for( Map.Entry<String, Integer> v : SYMBOLS.entrySet() )
		// {
			// String sym = v.getKey();
			// int nbr = v.getValue();
			// int sym_len = sym.length();
			
			// while( ( sym_len + index ) <= romanStr_len && romanStr.substring(index, index + sym_len).equals(sym) )
			// {
				// result = result + nbr;
				// index = index + sym_len;
			// }
		// }
		
		// return result;
		
		return 0;
	}
	
	private static String toRoman(int number)
	{
		String roman;
		int nbr = number, decimal;
		String result = "";
		
		for( Map.Entry<String, Integer> v : SYMBOLS.entrySet() )
		{
			roman = v.getKey();
			decimal = v.getValue();
			
			while( nbr >= decimal )
			{
				result = result + roman;
				nbr = nbr - decimal;
			}
		}
		
		return result;
	}
}
