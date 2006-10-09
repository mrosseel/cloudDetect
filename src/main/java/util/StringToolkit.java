package util;

import java.lang.String;

/**
* This Stringtoolkit handles several function that can beperformed on Strings 
* that are not included in the Standard Java Libraries  
*
*
* @author Nico De Groote
* @version $Revision: 1.2 $
*/
public class StringToolkit
{
	/**
	* This method gives you the count of aSubstring in aString <p>
	* e.g. 
	*   <b> StringToolkit.getSubString("Select ? From ?","?") 
	*		    returns a count of 2  </b>
	*
	* @param aString the original string where we are looking for SubStrings.
	* @param aSubString the SubString to be retrieved in the aString
	* @return int the count of substrings in aString.
	*/
	public static int getSubStringCount(String aString, String aSubString)
	{
		String tmp = aString;
		int count = 0;
		int index = 0;
		
		while((index = tmp.indexOf(aSubString)) != -1)  
		{
			count++;
			tmp = tmp.substring(index + aSubString.length());
			tmp.trim();
		}
			
		return count;
	}  
	
  /**
   * Returns a string containing the specified number of occurrences of the specified string.
   *
   * @param aString a <code>String</code> object.
   * @param aTimes an <code>int</code> object.
   * @return a string containing the specified number of occurrences of the specified string.
   */
  public static String repeatString(String aString, int aTimes)
  {
    String result = new String();
    for (int i = 0; i != aTimes; i++) 
      result += aString;

    return result;
  }
	
	/**
	* This method gives you the count of aSubstring in aString <p>
	* e.g. 
	*   <b> StringToolkit.replaceSubString("Select ? From ?","?", {"*","aTable"}) 
	*		    returns "Select * From aTable" </b>
	*
	* @param aString the original string where we are replacing SubStrings.
	* @param aSubString the SubString to be replaced in the aString
	* @param aReplacementStrings is the list of String to replace the SubStrings
	* @return a String with the substring replaced by the list of replacement strings
	*/	
	public static String replaceSubString(String aString, String aSubString, String[] aReplacementStrings)
	{
		int startIndex = 0;
		int endIndex = 0;
		int subStringLength = aSubString.length();
		StringBuffer stringBuffer = new StringBuffer(aString);
		
		for(int i=0; i < aReplacementStrings.length ; i++)
		{ 
			startIndex = aString.indexOf(aSubString);
			endIndex = startIndex + subStringLength;
			stringBuffer.replace(startIndex, endIndex, aReplacementStrings[i]);
			aString = stringBuffer.toString();
		}
		
		return stringBuffer.toString();
	}
	
	/**
	* This method gives you the count of aSubstring in aString <p>
	* e.g. 
	*   <b> StringToolkit.replaceSubString("Select ? From ?","?", "*") 
	*		    returns "Select * From *" </b>
	*
	* @param aString the original string where we are replacing SubStrings.
	* @param aSubString the SubString to be replaced in the aString
	* @param aReplacementString is a String to replace the SubStrings
	* @return a String with the substring replaced by the list of replacement strings
	*/	
	public static String replaceSubString(String aString, String aSubString, String aReplacementString)
	{
		int startIndex = 0;
		int endIndex = 0;
		int subStringLength = aSubString.length();
		StringBuffer stringBuffer = new StringBuffer(aString);
		String tempResult = new String();
		
		while((startIndex = aString.indexOf(aSubString)) != -1)
		{ 
			endIndex = startIndex + subStringLength;
			stringBuffer.replace(startIndex, endIndex, aReplacementString);
			tempResult = tempResult + stringBuffer.toString().substring(0, startIndex + aReplacementString.length());
			stringBuffer = new StringBuffer(stringBuffer.toString().substring(startIndex + aReplacementString.length()));
			aString = stringBuffer.toString();
		}	
		
		// add the last piece of the Stringbuffer which do not conatin any substrings anymore
		tempResult += stringBuffer.toString();
		
		return tempResult;		
	}
  
  
  /**
  * This method adds padding (white spaces) to a String until it has reached a certain length.
  * This is useful when you want to have fixed-width columns.<br>
  * If the String exceeds the given length, it is returned as is.
  * @param aString the original string you want to pad.
  * @param aLength the length up to which you want to pad.
  * @return a String with added padding.
  */    
  public static String padString(String aString, int aLength)
  {
    StringBuffer buffer = new StringBuffer(aLength);
    buffer.append(aString);

    while (buffer.length() < aLength)
    {
      buffer.append(" ");
    }
    
    return buffer.toString();
  }

  /**
  * This method allows you to extract substrings from a string formatted using a separator.
  * @param aString the original string
  * @param aSeparator the separator can be any string.
  * @return a array of substrings
  */    
  public static String[] parseString(String aString, String aSeparator)
  {
    java.util.Vector strings = new java.util.Vector();
    int sepLength = aSeparator.length();
    int fromIndex = 0;
    int toIndex = aString.indexOf(aSeparator);
    while (toIndex>0)
    {
      strings.add(aString.substring(fromIndex,toIndex));
      fromIndex = toIndex + sepLength;
      toIndex = aString.indexOf(aSeparator,fromIndex); 
    }
    strings.add(aString.substring(fromIndex,aString.length()));
    return (String[])strings.toArray(new String[strings.size()]);
  }

  public static void main(String[] args)
  {
    String[] result = parseString("kldsfjdsf::Dsdsfsd:dd:::POEI::]{}{::ddd::","::");
    for (int i=0;i<result.length;i++)
    {
      System.out.println(result[i]);
    }
  }
}
