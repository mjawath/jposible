package org.biz.app.ui.util;

import java.sql.*;
import java.util.*;
import java.util.regex.*;




public class StringUtility {

  public static final int EQUALS = 0;
  public static final int EQUALS_AND_GREATERTHAN = 1;
  public static final int EQUALS_AND_LESSTHAN = 2;
  public static final int GREATERTHAN = 3;
  public static final int LESSTHAN = 4;
  public static final int LEFTSPACES = 5;
  public static final int RIGHTSPACES = 6;
  public static final int LEFTRIGHTSPACES = 7;
  public static String CR = "\r";
  public static String NL = "\n";
  public static String CRLF = CR + NL;
  public static String TAB = "\t";
  private static char VALUE_SEPARATOR = ';';
  private static int FIXED_LINE_NO_OF_DIGITS = 9; //Can change from country to country
  private static final int MOBILE_NO_OF_DIGITS = 10; //This is fixed, 10 digits for Globe

  /**
   * Constructor of StringUtility class
   */
  public StringUtility() {
  }

  /**
   * Returns true if the given string is not null
   * <br>false in all other cases
   * <p><b>Examples:</b>
   * <br>isValidString(null)  returns false
   * <br>isValidString("")    returns true
   * <br>isValidString("xxx") returns true
   *
   * @param str String
   * @return true if the string is not null
   */

  public static boolean isValidString(String str) {
    return! (str == null);
  }

  public static boolean isEmptyString(Object str) {
    if(str == null )return true;
    return (isEmptyString(str.toString()));
  }

  
  /**
   * Returns true if the given string is empty or null
   * <br>return false if the given string is not empty
   * <p><b>Examples:</b>
   * <br>isEmptyString(null)  returns true
   * <br>isEmptyString("")    returns true
   * <br>isEmptyString("xxx") returns false
   *
   * @param str String
   * @return true if the string is empty or null
   */
  public static boolean isEmptyString(String str) {
    return (str == null || (str.length() == 0));
  }

  /**
   * Returns true if if given two Strings are same
   * else returns false
   * <p><b>Examples:</b>
   * <br>isSameString(null, "second")     returns false
   * <br>isSameString("first", null)      returns false
   * <br>isSameString(null, null)         returns true
   * <br>isSameString("first", "second")  returns false
   * <br>isSameString("same", "same")     returns true
   * @param str1
   * @param str2
   * @return boolean
   */
  public static boolean isSameString(String str1, String str2) {
    return (!StringUtility.isValidString(str1) && !StringUtility.isValidString(str2))
           || (StringUtility.isValidString(str1) && StringUtility.isValidString(str2) && str1.equalsIgnoreCase(str2));
  }

  /**
   * Returns the length of the given string
   * <p><b>Examples:</b>
   * <br>getLength(null)  returns -1
   * <br>getLength("")    returns  0
   * <br>getLength("xxx") returns  3
   *
   * @param str String
   * @return int denoting the length of the given string
   */
  public static int getLength(String str) {
    if (! (isValidString(str)))
      return -1;
    else
      return str.length();
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the given string with all the alphabatic characters in uppercase
   * <p><b>Examples:</b>
   * <br>converToUpper("This is My String Class") returns "THIS IS MY STRING CLASS"
   * <br>converToUpper("THIS IS MY STRING CLASS") returns "THIS IS MY STRING CLASS"
   * <br>converToUpper("this is my string class") returns "THIS IS MY STRING CLASS"
   * <br>converToUpper("") returns ""
   * <br>converToUpper(null) returns ""
   *
   * @param str String
   * @return String
   */
  public static String convertToUpper(String str) {
    if ( (!isValidString(str)) ||
        (isEmptyString(str)))
      return "";
    else
      return str.toUpperCase();
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the given string with all the alphabatic characters in lowercase
   * <p><b>Examples:</b>
   * <br>converToLower("THIS IS MY STRING CLASS") returns "this is my string class"
   * <br>converToLower("This is My String Class") returns "this is my string class"
   * <br>converToLower("this is my string class") returns "this is my string class"
   * <br>converToLower("") returns ""
   * <br>converToLower(null) returns ""
   *
   * @param str String
   * @return String
   */
  public static String convertToLower(String str) {
    if ( (!isValidString(str)) ||
        (isEmptyString(str)))
      return "";
    else
      return str.toLowerCase();
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the given string with all the first letters converted to uppercase
   * <p><b>Examples:</b>
   * <br>convertToTitleCase("THIS IS MY STRING CLASS") returns "This Is My String Class"
   * <br>convertToTitleCase("This is My String Class") returns "This Is My String Class"
   * <br>convertToTitleCase("this is my string class") returns "This Is My String Class"
   * <br>convertToTitleCase("") returns ""
   * <br>convertToTitleCase(null) returns ""
   *
   * @param str String
   * @return String
   */
  public static String convertToTitleCase(String str) {
		StringBuilder sb = new StringBuilder();
		if ((!isValidString(str)) || (isEmptyString(str)))
			return "";
		else
			str = str.toLowerCase();
		   StringTokenizer strTitleCase = new StringTokenizer(str);
			while (strTitleCase.hasMoreTokens()) {
				String s = strTitleCase.nextToken();
				sb.append(s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toUpperCase()) + " ");
			}
		return sb.toString();
	}

  /**
	 * Checks for the valid and not empty string and<br>
	 * returns the string chopped in to Length starting from startindex startindex must be validated for >= 0 if startindex < 0 , then take startindex as 0 if startindex >= Length then return empty string <br>
	 * substring("Mline_team", 0,1) returns "M" <br>
	 * substring("Mline_team", 0,0) returns empty string <br>
	 * substring("Mline_team", 5,5) returns "_team" <br>
	 * substring("Mline_team", -1,5) returns "Mline" <br>
	 * substring("", 0,5) returns "" <br>
	 * substring(null, 0,5) returns ""
	 * @param myString String
	 * @param Length int
	 * @param startindex int
	 * @return String
	 */
  public static String substring(String myString, int startindex, int length) {
    if ( (!isValidString(myString)) || (isEmptyString(myString)))
      return "";
    else if (startindex < 0)
      startindex = 0;
    else if (startindex >= getLength(myString))
      return "";
    else if (myString.length() <= length)
      return myString;

    try {
      return myString.substring(startindex, startindex + length); // inclusife start until exclisife end position;
    }
    catch (Exception ex) {
      return "";
    }
  }

  /**
   * Returns the portion of the given string that is contained between the given delimiting strings.
   * Case sensitive matching is performed.
   * 
   * e.g : substring("StarWarsRocks!", "Star", "Ro") will result in "Wars"
   * @param str
   * @param delimiter1
   * @param delimiter2
   * @return
   */
  public static String substring(String str, String delimiter1, String delimiter2) {
    if (isEmptyString(str) || isEmptyString(delimiter1) || isEmptyString(delimiter2))
      return "";
    else {
      String result = "";
      
      int index1 = str.indexOf(delimiter1);
      int index2 = str.indexOf(delimiter2, index1 + delimiter1.length());
      if (index1 > -1 && index2 > -1) {
        try {
          result = str.substring(index1 + delimiter1.length(), index2);
        }
        catch (Exception e) { }
      }
      return result;
    }
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the first character of the string
   * <p><b>Examples:</b>
   * <br>getFirstChar("THIS IS MY STRING CLASS") returns "T"
   * <br>getFirstChar("This is My String Class") returns "T"
   * <br>getFirstChar("this is my string class") returns "t"
   * <br>getFirstChar(null) returns ""
   * <br>getFirstChar("") returns ""
   * @param str String
   * @return String
   */
  public static String getFirstChar(String str) {
    return substring(str, 0, 1);
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the last character of the string
   * <p><b>Examples:</b>
   * <br>getLastChar("THIS IS MY STRING CLASS") returns "S"
   * <br>getLastChar("This is My String Class") returns "s"
   * <br>getLastChar("this is my string class") returns "s"
   * <br>getLastChar(null) returns ""
   * <br>getLastChar("") returns ""
   * @param str String
   * @return String
   */
  public static String getLastChar(String str) {
    return substring(str, getLength(str) - 1, 1);
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the characters in a character array
   * <p><b>Examples:</b>
   * <br>getCharInArray("THIS IS MY STRING CLASS") returns myCharArray[0]='T',
   * <br>myCharArray[1]='H' ,myCharArray[2]='I', and so on
   * <br>getCharInArray(null) returns char [] with size 0
   * <br>getCharInArray("") returns char [] with size 0
   * @param str String
   * @return char[]
   */
  public static char[] getCharInArray(String str) {
    if ( (!isValidString(str)) ||
        (isEmptyString(str)))
      return new char[0];
    else
      return str.toCharArray();
  }

  /**
   * Returns words array by given string
   * @param str
   * @return ArrayList<String>
   */
  public static ArrayList<String> getWordInArray(String str) {
    if ( (!isValidString(str)) || (isEmptyString(str))) {
      return new ArrayList<String>();
    }
    else {
      return split(str, "\\s");
    }
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the ascii value of the characters of the string in a int array
   * <p><b>Examples:</b>
   * <br>getAsciiInArray("THIS IS MY STRING CLASS") returns myIntArray[0]=Ascii value of 'T'
   * <br>myIntArray[1]=Ascii value of 'H' ,myIntArray[2]=Ascii value of 'I', and so on
   * <br>getAsciiInArray(null) returns int [] with size 0
   * <br>getAsciiInArray("") returns int [] with size 0
   * @param str String
   * @return int[]
   */
  public static int[] getAsciiInArray(String str) {
    char[] myCharArray = getCharInArray(str);
    return new int[myCharArray.length];
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the position (starts from zero) of the given character starting from startindex
   * startindex must be greaterthan or equal to 0 else take 0 as startindex
   * <p><b>Examples:</b>
   * <br>getPosition("mline@lk.aexis.com",3,'.')returns 5
   * <br>getPosition("",3,'.')returns -1
   * <br>getPosition(null,3,'.')returns -1
   * @param str String
   * @param startindex int
   * @param character char
   * @return int
   */
  public static int getPosition(String str, int startindex, char myCharacter) {
    int i = 0;
    if ( (!isValidString(str)) ||
        (isEmptyString(str)))
      return -1;
    else if (startindex < 0)
      startindex = 0;
    else if (startindex > getLength(str))
      return -1;
    else {
      i = (str.indexOf(myCharacter, startindex)) - startindex;
    }
    return i;
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the string in the maxLength by adding myCharacter
   * if the length of th string is greater than maxLength then the string<br>
   * is chopped in to the maxLength starting from right
   * if the maxLength <= 0 the maxLength is set to 0 and alert the user
   * <p><b>Examples:</b>
   * <br>stringWithTailPadding("Mline Team",15,'?')returns "Mline Team?????"
   * <br>stringWithTailPadding("Mline Team",5,'?')returns "Mline"
   * <br>stringWithTailPadding("",15,'?')returns "???????????????" (15 "?")
   * <br>stringWithTailPadding(null,15,'?')returns "???????????????" (15 "?")
   * <br>stringWithTailPadding("Mline",-1,'?')returns ""
   * @param str String
   * @param maxLength int
   * @param myCharacter char
   * @return String
   */
  public static String stringWithTailPadding(String str, int maxLength, char myCharacter) {
    if(maxLength <= 0){
//      ProgrammerAlert.notify("StringUtility","stringWithTailPadding","the maxLength is not valid " + maxLength);
      return "";
    }
    if ( (!isValidString(str)) || (isEmptyString(str)))
      return multiplyChar(maxLength , myCharacter);
    else{
      StringBuilder stringBuff = new StringBuilder(str);
      stringBuff.append(multiplyChar(maxLength , myCharacter));
      return stringBuff.substring(0,maxLength).toString();
    }

  }

  /**
  * Checks for the valid and not empty string and<br>
  * returns the string in the maxLength by adding myCharacter
  * if the length of the string is greater than maxLength then the string<br>
  * is chopped in to the maxLength starting from left
  * <p><b>Examples:</b>
  * <br>stringWithFrontPadding("Mline Team",15,'?')returns "?????Mline Team"
  * <br>stringWithFrontPadding("Mline Team",8,'?')returns "ine Team"
  * <br>stringWithFrontPadding("",8,'?')returns "????????"
  * <br>stringWithFrontPadding(null,8,'?')returns "????????"
  * <br>stringWithFrontPadding("Mline",-1,'?')returns ""

  * @param str String
  * @param maxLength int
  * @param myCharacter char
  * @return String
  */

 public static String stringWithFrontPadding(String str, int maxLength,char myCharacter) {
   if(maxLength <= 0){
//      ProgrammerAlert.notify("StringUtility","stringWithFrontPadding","the maxLength is not valid " + maxLength);
      return "";
   }
   if ( (!isValidString(str)) || (isEmptyString(str)))
     str = multiplyChar(maxLength, myCharacter);
   else {
     if (getLength(str) < maxLength)
       str = multiplyChar( (maxLength - getLength(str)), myCharacter) + str;
     else
       str = substring(str, getLength(str) - maxLength, maxLength);
   }
   return str;
 }


  /**
   * Checks for the valid and not empty strings and<br>
   * returns one string
   * <p>addMiddleFix("hello ","there ","")returns "hello there "
   * <p>addMiddleFix("hello","there","")returns "hellothere"
   * <p>addMiddleFix("hello","there"," ")returns "hello there"
   * <p>addMiddleFix("hello","there","-")returns "hello-there"
   * <p>addMiddleFix("","there"," ")returns ""
   * <p>addMiddleFix("hello",""," ")returns ""
   * <p>addMiddleFix("",""," ")returns ""
   * <p>addMiddleFix(null,"there"," ")returns ""
   * <p>addMiddleFix("hello",null," ")returns ""
   * <p>addMiddleFix(null,null," ")returns ""
   * @param firstStr String
   * @param lastStr String
   * @param middleStr String
   * @return String
   */
  public static String addMiddleFix(String firstStr, String lastStr,
                                    String middleStr) {
    if (   (!isValidString(firstStr))
        || (!isValidString(lastStr))
        || (isEmptyString(firstStr))
        || (isEmptyString(lastStr)))
      return "";
    else
      return firstStr + middleStr + lastStr;
  }

 /**
   * Checks for the valid and not empty string and<br>
   * returns a string with adding postfixstr to str
   * <p><b>Examples:</b>
   * <br>addPostFixString("Mline Team",", ")returns "Mline Team, "
   * <br>addPostFixString("",",")returns ""
   * <br>addPostFixString(null,",")returns ""
   *
   * @param str String
   * @param postfixstr String
   * @return String
   */
  public static String addPostFixString(String str, String postfixstr) {
    if ( (!isValidString(str)) || (isEmptyString(str)))
      return "";
    else
      return str + postfixstr;
  }

   /**
   * Checks for the valid and not empty string and<br>
   * returns a string with adding prefixstr to str
   * <p><b>Examples:</b>
   * <br>addPreFixString("Mline Team"," ,")returns " ,Mline Team"
   * <br>addPreFixString("",",")returns ""
   * <br>addPreFixString(null,",")returns ""
   * @param str String
   * @param prefixstr String
   * @return String
   */
  public static String addPreFixString(String str, String prefixstr) {
    if ( (!isValidString(str)) || (isEmptyString(str)))
      return "";
    else
      return prefixstr + str;
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns a string with wrapper added before and after the string
   * <p><b>Examples:</b>
   * <br>addPreFixString("Mline Team"," ,")returns ",Mline Team,"
   * <br>addPreFixString("",",")returns ""
   * <br>addPreFixString(null,",")returns ""
   * <br>addPreFixString("Mline Team","")returns "Mline Team"
   * @param string String
   * @param wrapper String
   * @return String
   */
  public static String wrapStringWith(String string, String wrapper) {
    if ( (!isValidString(string)) || (isEmptyString(string)))
      return "";
    else if(isEmptyString(wrapper))
      return string;
    else return  wrapper +  string + wrapper;
  }


 /**
   * Adds the addedString to the source with in between a seperator only when the added string is not empty.
   * If the added string is empty then the source is returned unchanged.
   * In case source is not a valid string then as a graceful degradation the empty string is returned.
   * In case added string is empty or null then source is returned unchanged.
   * In case source is empty the added String will be returned. - this is useful when adding a series of strings in a loop (for the every first loop when the source could also be empty)
   * @param source the main String
   * @param seperator String holding what needs to separate the two strings (eg. " - ")
   * @param addedString String that is expected to be added to the source string
   * @return String source + seperator + addedString
   */
  public static String addStringPrefixedWithSeperator (String source, String seperator, String addedString) {
    if (!isValidString(source)) return "";
    if (isEmptyString(addedString)) return source;
    if (isEmptyString(source )) return addedString;
    return source + seperator + addedString;
  }


  /**
   * Returns a string consists of spaces having a length of "length"
   * <p><b>Examples:</b>
   * <br>addSpace(5)returns "     "
   * <br>addSpace(0)returns ""
   * @param length int
   * @return String
   */
  public static String addSpace(int length) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < length; i++)
      result.append(" ");

    return result.toString();
  }

  /**
   * Returns a string with c characters multiplied by length
   * if the legnth is less than zero it will be set to 0
   * <p><b>Examples:</b>
   * <br>multiplyChar(5,'X')returns  "XXXXX"
   * @param length int
   * @param c char
   * @return String
   */
  public static String multiplyChar(int length, char c) {
    if(length < 0)length = 0;
    StringBuilder result = new StringBuilder(length);
    int i = 0;
    for (i = 1; i <= length; i++)
      result.append(c);
    return result.toString();
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the count of alphabatic characters
   * <p><b>Examples:</b>
   * <br>numberOfAlphaChars("Hello! ") returns 5
   * <br>numberOfAlphaChars("name@domain.lk") returns 12

   * @param str String
   * @return int
   */
  public static int numberOfAlphaChars(String str) {
    if ( (!isValidString(str)) ||
        (isEmptyString(str)))
      return -1;
    str = getAllAlphaChars(str);
    return getLength(str);
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the string with all the alphabatic characters (removing all others)
   * <p><b>Examples:</b>
   * <br>getAllAlphabaticChars("Mline Team@lk.aexis.com") returns "MlineTeamlkaexiscom"
   * @param str String
   * @return String
   */
  public static String getAllAlphaChars(String str) {
    return getSpecifiedChars(65, 90, 97, 122, 0, 0, str);
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the string with all the alphanumeric characters (removing all others)
   * <p><b>Examples:</b>
   * <br>getAllAlphaNumeric("MlineTeam123@lk.aexis.com") returns "MlineTeam123lkaexiscom"
   * @param str String
   * @param str String
   * @return String
   */
  public static String getAllAlphaNumChars(String str) {
    return getSpecifiedChars(48, 57, 65, 90, 97, 122, str);
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the string with all the numeric characters (removing all others)
   * <p><b>Examples:</b>
   * <br>getAllAlphaNumeric("MlineTeam123@lk.aexis.com") returns "123"
   * @param str String
   * @param str String
   * @return String
   */
  public static String getAllNumChars(String str) {
    return getSpecifiedChars(48, 57, 0, 0, 0, 0, str);
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the string with all other characters (removing all alphabatic,alphanumeric characters)
   * <p><b>Examples:</b>
   * <br>getAllAlphaNumeric("MlineTeam123@lk.aexis.com") returns "@.."
   * @param str String
   * @param str String
   * @return String
   */
  public static String getAllNonAlphaNumChars(String str) {
    str = convertToUpper(str);
    StringBuilder strBuff = new StringBuilder(getLength(str));
    int j;
    for (int i = 0; i < getLength(str); i++) {
      j = (int) str.charAt(i);
      if (! ( (j >= 65 && j <= 90) || (j >= 48 && j <= 57))) {
        strBuff.append( (char) j);
      }
    }
    return strBuff.toString();
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the no of time the specificchar exists
   * <p><b>Examples:</b>
   * <br>countSpecifiedChar("3,456,789.23",',') returns 2
   * @param str String
   * @param specificchar char
   * @return int
   */
  public static int countSpecifiedChar(String str, char specificChar) {
    int count = 0;
    if ( (!isValidString(str)) ||
        (isEmptyString(str)))
      return -1;
    for (int length = 0; length < getLength(str); length++) {
      if (str.charAt(length) == specificChar) {
        count = count + 1;
      }
    }
    return count;
  }

  /**
   * Counts the number of new line character in the passed in string and returns
   * it, If there are no new line characters or if  the string is empty 0 is returned
   * <p><b>Examples:</b>
   * <br>countSpecifiedChar("Test \n sentence \n to \n check \n new \n line") returns 5
   * @param msg String
   * @return int
   */
  public static int countNewLineCharacters(String msg) {
    int count = 0;
    if (!isEmptyString(msg)) {
      Pattern p = Pattern.compile(NL);
      Matcher m = p.matcher(msg);
      while (m.find()) {
        count++;
      }
    }
    return count;
  }

  /**
   * Returns the index of the specified occurence of the specified character
   * e.g : indexOfChar("Star.Wars.Rocks!", '.', 2) => should return 9
   *
   * @param c char
   * @param occurence int
   * @return index of the specified occurence of the specified character. -1 if the specified occurence doesn't exist
   */
  public static int indexOfChar(String str, char c, int occurence) {
    if (!StringUtility.isEmptyString(str)) {
      int startingPos = 0, count = 0;

      while (startingPos < str.length()) {
        startingPos = str.indexOf(c, startingPos);
        if (startingPos == -1)
          break;
        else {
          count++;
          if (count == occurence) {
            return startingPos;    //the only correct point of return
          }

          startingPos++; //increment to point to next char
        }
      }
    }
    return -1;
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the string with the replacement of oldstr by newstr
   * <p><b>Examples:</b>
   * <br>replaceString("ABC\n","\n","/CR/") returns "ABC/CR/"
   * <br>replaceString("ABC/CR/","/CR/","\n") returns "ABC\n"
   * <br>replaceString("ABC@","@","#") returns "ABC#"
   * <br>replaceString("","@","#") returns ""
   * <br>replaceString(null,"@","#") returns ""
   * @param str String
   * @param oldstr String
   * @param newstr String
   * @return String
   */
  public static String replaceString(String str, String oldstr, String newstr) {

    if ( (!isValidString(str)) ||
        (isEmptyString(str)))
      return "";
    return str.replaceAll(oldstr, newstr);
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the string by replacing all ' with '' inorder to use in ORACLE RDBMS
   * <p><b>Examples:</b>
   * <br>toSQLstringFormat("o'conner") returns "o''conner"
   * <br>toSQLstringFormat("conner") returns "conner"
   * <br>toSQLstringFormat("o''conner") returns "o''''conner"

   * @param str String
   * @return String
   */
  public static String toSQLstringFormat(String str) {
    return replaceString(str, "'", "''");
  }

  /**
   * Returns the string value ofclob field.
   * If argument is null, or if an error occurs while retrieving data, an empty string is returned

   * @param clobFld java.sql.Clob object
   */
  public static String getStringFromClob(Clob clobFld) {
    try {
      if (clobFld != null) {
        return clobFld.getSubString(1, (int) clobFld.length());
      }
      else {
        return "";
      }
    }
    catch (SQLException ex) {
      return "";
    }

  }

  /**
   * Tokenize the String according to the delimeter
   * If the token is null then s String " " will be added to the ArrayList.
   * This method was written because the StringTokenizer doesn't
   * support the null value
   * @param str String
   * @param delimeter char
   * @return ArrayList
   * take from MGD
   */
  public static ArrayList tokenizeString(String str, char delimeter) {
    ArrayList tokens = new ArrayList();
    String token = "";
    char c;
    for (int i = 0; i < str.length(); i++) {
      c = str.charAt(i);
      if (c == delimeter) {
        if (token == null || token.length() == 0)
          token = " ";
        tokens.add(token);
        token = "";
      }
      else {
        token = token + c;
      }
      if (i == str.length() - 1)
        tokens.add(token);
    }
    return tokens;
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns a string as follows
   * if choice =1 then returns the string removing left spaces
   * if choice =2 then returns the string removing right spaces
   * if choice =3 then returns the string removing left,right spaces
   * returns the string removing left,right spaces as default
   * <p><b>Examples:</b>
   * <br>trim(" Mline Team  ",3) returns "Mline Team"
   * <br>trim("",3) returns ""
   * <br>trim(null,3) returns ""
   * @param str String
   * @param choice int
   * @return String
   */
  public static String trim(String str, int choice) {
    if ( (!isValidString(str)) ||
        (isEmptyString(str)))
      return "";
    else {
      String result = "";
      switch (choice) {
        case LEFTRIGHTSPACES:
          result = str.trim();
          break;
        case LEFTSPACES:
          break;

        case RIGHTSPACES:
          break;

        default:
          result = str.trim();
          break;

      }
      return result;
    }
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the string by removing the specified character
   * <p><b>Examples:</b>
   * <br>filterChar("ABC@123.DD",'@') returns "ABC123.DD"
   * @param str String
   * @param character char
   * @return String
   */
  public static String filterChar(String str, char mycharacter) {
    if ( (!isValidString(str)) ||
        (isEmptyString(str)))
      return "";
    StringBuilder strBuff = new StringBuilder(str);
    int i = getPosition(str, 0, mycharacter);
    return strBuff.deleteCharAt(i).toString();
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the string in the given format
   * <p><b>Examples:</b>
   * <br>formatNumberEUstyle("345,124,789.23") returns "345.124.789,23"
   * <br>formatNumberEUstyle("4,789.23") returns "4.789,23"
   * <br>formatNumberEUstyle(null) returns "0"
   * <br>formatNumberEUstyle("") returns "0"
   * @param str String
   * @return String
   */
  public static String formatNumberEUstyle(String str) {
    if ( (!isValidString(str)) ||
        (isEmptyString(str)))
      return "0";
    String newStr = replaceString(str, ",", ".");
    int i = getPosition(str, 0, '.');
    StringBuilder strBuff = new StringBuilder(newStr);
    strBuff.setCharAt(i, ',');
    return strBuff.toString();
  }

  /**
   * Checks for the valid and not empty string and<br>
   * returns the string by removing the format
   * <p><b>Examples:</b>
   * <br>formatNumberUSstyle("3.456.789,23") returns "3,456,789.23"
   * <br>formatNumberUSstyle("789,23") returns "789.23"
   * <br>formatNumberUSstyle("") returns ""
   * <br>formatNumberUSstyle(null) returns ""
   * @param str String
   * @return String
   */
  public static String formatNumberUSstyle(String str) {
    if ( (!isValidString(str)) ||
        (isEmptyString(str)))
      return "0";
    String newStr = replaceString(str, "[.]", ",");
    int i = getPosition(str, 0, ',');
    StringBuilder strBuff = new StringBuilder(newStr);
    strBuff.setCharAt(i, '.');
    return strBuff.toString();
  }

  /**
   * Returns true if valid email address else false
   * <p><b>Examples:</b>
   * <br>isValidEmailAddress(mline@lk.aexis.com") returns true
   * <br>isValidEmailAddress(mline.lk.aexis.com") returns false
   * <br>isValidEmailAddress(mline@lk@aexis.com") returns false
   * <br>isValidEmailAddress(mline@aexis.com") returns true
   * <br>isValidEmailAddress(mline@aexis.lk") returns true
   * <br>isValidEmailAddress(mline@aexis.net") returns true
   * <br>isValidEmailAddress(mline@aexis.edu") returns true

   * @param emailaddr String
   * @return boolean
   */
  public static boolean isValidEmailAddress(String emailaddr) {
    int indexOfAt = emailaddr.indexOf("@");
    int count = countSpecifiedChar(emailaddr, '@');
    if (count == 1) {
      int indexOfPeriod = emailaddr.indexOf(".", indexOfAt + 1);
      if (indexOfPeriod > 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * check whether second parameter string is contains in the first parameter string
   * <p><b>Example:</b>
   * <br>containsStringIgnoreCase("ABCDEF","abcd") returns true
   * <br>containsStringIgnoreCase("ABCDEF","ef") returns true
   * <br>containsStringIgnoreCase("ABCDEF","xyz") returns false
   * <br>containsStringIgnoreCase("ABCDEF","ABD") returns false
   * <br>containsStringIgnoreCase("","ABD") returns false
   * <br>containsStringIgnoreCase(null,"ABD") returns false
   * <br>containsStringIgnoreCase("ABCDEF","") returns false
   * <br>containsStringIgnoreCase("ABCDEF",null) returns false
   * @param firstStr String
   * @param compStr String
   * @return boolean
   */

  public static boolean containsStringIgnoreCase(String firstStr,
                                                 String compStr) {
    if ( (!isValidString(firstStr)) ||
        (!isValidString(compStr)) ||
        (isEmptyString(firstStr)) ||
        (isEmptyString(compStr)))
      return false;
    if (convertToUpper(trim(firstStr,
                            LEFTRIGHTSPACES)).indexOf(convertToUpper(trim(
        compStr, LEFTRIGHTSPACES))) == -1)
      return false;
    else
      return true;
  }

  /**
   * check whether second parameter string starts with the first parameter string
   * <p><b>Example:</b>
   * <br>startsWithStringIgnoreCase("ABCDEF","abcd") returns true
   * <br>startsWithStringIgnoreCase("ABCDEF","ABc") returns true
   * <br>startsWithStringIgnoreCase("ABCDEF","Abe") returns false
   * <br>startsWithStringIgnoreCase("ABCDEF","A") returns true
   * <br>startsWithStringIgnoreCase("","ABD") returns false
   * <br>startsWithStringIgnoreCase(null,"ABD") returns false
   * <br>startsWithStringIgnoreCase("ABCDEF","") returns false
   * <br>startsWithStringIgnoreCase("ABCDEF",null) returns false
   * @param firstStr String
   * @param compStr String
   * @return boolean
   */

  public static boolean startsWithStringIgnoreCase(String firstStr,
      String compStr) {
    if ( (!isValidString(firstStr)) ||
        (!isValidString(compStr)) ||
        (isEmptyString(firstStr)) ||
        (isEmptyString(compStr)))
      return false;
    if (convertToUpper(trim(firstStr, LEFTRIGHTSPACES)).startsWith(
        convertToUpper(trim(compStr, LEFTRIGHTSPACES))))
      return true;
    else
      return false;
  }

  /**
   * Return specified characters for the given range
   * <p><b>Examples:</b>
   * <br>getSpecifiedChars(65,90,97,122"gayani&champika") return "gayanichampika"
   * <br>getSpecifiedChars(48,57,0,0,"gaya856champika") return "856"
   * <br>getSpecifiedChars(65,90,97,122,"") return ""
   * <br>getSpecifiedChars(65,90,97,122,null) return ""
   * @param lb1 int
   * @param ub1 int
   * @param lb2 int
   * @param ub2 int
   * @param str String
   * @return String
   */
  private static String getSpecifiedChars(int lb1, int ub1, int lb2, int ub2,
                                          int lb3, int ub3, String str) {
    if ( (!isValidString(str)) ||
        (isEmptyString(str)))
      return "";
    StringBuilder strBuff = new StringBuilder(getLength(str));
    int eachChar = 0;
    for (int i = 0; i < getLength(str); i++) {
      eachChar = (int) str.charAt(i);
      if ( (eachChar >= lb1 && eachChar <= ub1)
          || (eachChar >= lb2 && eachChar <= ub2)
          || (eachChar >= lb3 && eachChar <= ub3)) {
        strBuff.append( (char) eachChar);
      }
    }
    return strBuff.toString();
  }

  /**
   * Replace all occurences of /CR/ with \n
   * @param val input string
   * @return modified string
   */
  public static String replaceCR(String val) {
    try {
      return replaceString(val, "/CR/", "\n");
    }
    catch (Exception e) {
      return val;
    }
  }

  /**
   * Replace all occurences of \n with /CR/
   * @param val input string
   * @return modified string
   */
  public static String replaceNewLine(String val) {
    return replaceString(val, "\n", "/CR/");
  }

  /**
   * Replace all occurences of \r\n with /CR/
   * @param val input string
   * @return modified string
   */
  public static String replaceNewLineCarriageReturn(String val) {
    return replaceString(val, "\r\n", "/CR/");
  }

  /**
   * Return the string with the replacement of the single quotes by two single quotes
   * <br>replaceQuotes("gay\'a") return "gay\"a"
   * <br>replaceQuotes("gay'a") return "gay\"a"
   * <br>replaceQuotes("") return null
   * <br>replaceQuotes(null) return null
   * @param str String
   * @return String
   */
  public static String replaceQuotes(String str) {
    return replaceString(str, "\'", "\"");
  }

  /**
   * Adds spaces to end of the string until specifed length is reached
   * <p><b>Examples:</b>
   * <br>   setUpWordWithSpaces("MLine General Data", 7, true) returns "MLine G"
   * <br>   setUpWordWithSpaces("MLine General Data", 7, false) returns "MLine G"
   * <br>   setUpWordWithSpaces("MLine", 8, true) returns "   MLine"
   * <br>   setUpWordWithSpaces("MLine", 8, false) returns "MLine   "
   * <br>   setUpWordWithSpaces("", 3, false) returns "   "
   * <br>   setUpWordWithSpaces(null, 3, true) returns "   "
   * @param str String
   * @param length int
   * @param rightAlign boolean
   * @return String
   */
  public static String setUpWordWithSpaces(String str, int length,
                                           boolean rightAlign) {
    str = trim(str, StringUtility.LEFTRIGHTSPACES);
    if (str.length() < length) {
      if (rightAlign) {
        str = addSpace(length - str.length()) + str;
      }
      else {
        str = str + addSpace(length - str.length());
      }
    }
    else {
      str = substring(str, 0, length);
    }
    return str;
  }

  /**
   * Adds spaces to end of the string until specifed length is reached
   * @param longValue long
   * @param length int
   * @param rightAlign boolean
   * @return String
   */
  public static String setUpWordWithSpaces(long longValue, int length, boolean rightAlign) {
   return StringUtility.setUpWordWithSpaces(Long.toString(longValue), length, rightAlign);
 }


  /**
   * Replaces the parameters ["$" followed by "digit(s)"] by the relevent values taken from the
   * array & returns the ORIGINAL string along with the replaced values.
   * 
   * @param str      - The original string with parameters
   * @param strArray - The array which contains replacer values for parameters
   * @return String  - The original string along with the replaced values
   */
  public static String replaceString(String str, String[] strArray) {
    int index = 0;
    String regex = "\\$\\d+"; // The regular expression to check for valid parameters
    
    Pattern pattern = Pattern.compile(regex); // Compile the regular expression & create a pattern
    Matcher matcher = pattern.matcher(str); // Create a matcher for the pattern for the given string

    boolean result = matcher.find(); // Find & return whether a parameter is found
    while (result) {
      String group = matcher.group(); // The GROUP which contains a parameter

      try{
        index = Integer.parseInt(group.substring(1)); // Remove the "$" & get the "digit(s)" as an INTEGER
      }
      catch(NumberFormatException e){
//        ProgrammerAlert.notify("Number expected, but something else found in : " +group);

        // Graceful degradation.
        // If INVALID number format is found, then replace the relevent parameter by "???"
        str = str.replace(group, "???");
        result = matcher.find(); // Find & return whether another parameter is found
        continue;
      }

      // Return the matching value from the ARRAY. If not found then return "???"
      String replaceStr = (strArray.length >= index ? strArray[index-1] : "???");

      str = str.replace(group, replaceStr); // Replace the matching value in the ORIGINAL string
      result = matcher.find(); // Find & return whether another parameter is found
    }

    return str; // Return the ORIGINAL string along with the replaced values
  }

  /**
   * this method converts the passed in array to
   * a string adding the user passed in seperator
   * in between items, if an empty array is passed a blank string
   * is returned,
   * @param strArray
   * @param seperator
   * @return
   */
  public static String arrayToString(String[] strArray, String seperator) {
    StringBuilder striBuff = new StringBuilder();
    if (strArray != null) {
      for (int i = 0; i < strArray.length; i++) {
        appendCSV(striBuff, strArray[i], seperator);
      }
    }
    return striBuff.toString().trim();
  }

  /**
   * Foreach loop was not used as if we do we will need to chop the last seperator.
   * A valid String to be added to the output (which is not empty after trimming the start and end spaces).
   * arrayToStringWithSeperator("Mline", "", "  ", "OR")  -> Mline, OR
   *
   * @param strArray
   * @param seperator
   * @return
   */
  public static String arrayToStringWithSeperator( String seperator, String... strArray){
    if (strArray == null || strArray.length == 0) return "";
    StringBuilder values = new StringBuilder();
    for (int i = 0; i < strArray.length; i++) {
      String value = strArray[i].trim();
      if (!StringUtility.isEmptyString(value)){
        if (values.length() > 0){
          values.append(seperator);
          values.append(value);
        }
        else
          values.append(value);
      }
    }
    return values.toString();
  }

  /**
   * A private method required for <code> replaceString(str,strArray)</code> method.
   * @param str String - A string starts with $
   * @return String - the consecutive numbers from the $ symbol upto a space or a character.
   * Ex 1: if str = "$12" then return string = "12"
   * Ex 2: if str = "$a" then return string = "0"
   */
  private static String getToken(String str) {
    String retStr = "";
    int index = 0;
    String s = "";
    String temp = "";
    if (str.startsWith("$")) {
      while (index < str.length() - 1) {
        s = str.substring(index + 1, index + 2);
        if (s.matches("[1-9]")) { //Check whether the string is a number or not.
          temp += s;
          index++;
        }
        else {
          break;
        }
      }
      if (temp.length() == 0)
        temp = "0";
      retStr = temp;
    }
    return retStr;
  }

  /**
   * Returns the class name of the given object
   * @param obj Object
   * @return String
   */
  public static String getClassName(Object obj) {
    String className = obj.getClass().toString();
    return className.substring(className.lastIndexOf('.') + 1); //lastIndexOf() is used to return only the class name, without the package hierarchy
  }

  /**
   * Prefixes zeros if the given length is greater than the length of the string
   * <p><b>Examples:</b>
   * <br>    addPrefixZerosForFixedLength ("MLine", 10) returns "00000MLine"
   * <br>    addPrefixZerosForFixedLength ("MLine", 4) returns "MLine"
   * <br>    addPrefixZerosForFixedLength ("MLine", -2) returns "MLine"
   * <br>    addPrefixZerosForFixedLength (null, 10) returns ""
   * <br>    addPrefixZerosForFixedLength ("", 10) returns ""
   *
   * @param str string
   * @param length required length
   * @return string after prefixing
   */
  public static String addPrefixZerosForFixedLength(String str, int length) {
    String result = "";
    if (isEmptyString(str))
      return result;
    int strLen = getLength(str);
    if (strLen < length)
      result = multiplyChar( (length - strLen), '0') + str;
    else
      result = str;
    return result;
  }

  /**
   * Returns the substring of the firstStr that is before the compStr
   * if the comparable string(compStr) argument occurs one or more times as a substring
   * within the given string(firstStr), then the first substring is returned.
   * <p><b>Examples:</b>
   * <br>    getHead ("","") returns ""
   * <br>    getHead ("","C") returns ""
   * <br>    getHead ("CCCSS","") returns "CCCSS"
   * <br>    getHead ("CCCSS",null) returns "CCCSS"
   * <br>    getHead (null,"") returns ""
   * <br>    getHead ("",null) returns ""
   * <br>    getHead (null,null) returns ""
   * <br>    getHead ("AAABBCDDD","C") returns "AAABB"
   * <br>    getHead ("AAABBCDDDCVV","C") returns "AAABBCDDD"
   * <br>    getHead ("AAABBCDDDCVV","CDDD") returns "AAABB"
   *
   * @param firstStr String
   * @param compStr String
   * @return String
   */
  public static String getHead(String firstStr, String compStr) {
    if (!StringUtility.isEmptyString(firstStr) &&
        StringUtility.isEmptyString(compStr))
      return firstStr;
    else if (!StringUtility.isEmptyString(firstStr) &&
             !StringUtility.isEmptyString(compStr)) {
      firstStr += compStr;
      return substring(firstStr, 0, firstStr.indexOf(compStr));
    }
    else
      return "";
  }

  /**
   * Returns the substring of the mainstring that is after the token
   * if the comparable string(token) argument occurs one or more times as a substring
   * within the given string(mainstring), then the last substring is returned.
   * <p><b>Examples:</b>
   * <br>    getTail ("","") returns ""
   * <br>    getTail ("","C") returns ""
   * <br>    getTail ("CCCSS","") returns ""
   * <br>    getTail (null,"") returns ""
   * <br>    getTail ("",null) returns ""
   * <br>    getTail (null,null) returns ""
   * <br>    getTail ("AAABBCDDD","C") returns ""DDD""
   * <br>    getTail ("AAABBCDDDCVV","C") returns "VV"
   * <br>    getTail ("AAABBCDDDCVV","CDDD") returns "CVV"

   * @param firstStr String
   * @param compStr String
   * @return String
   */
  public static String getTail(String mainString, String token) {
    if (!StringUtility.isEmptyString(mainString) && StringUtility.isEmptyString(token))
      return mainString;
    else if (!StringUtility.isEmptyString(mainString) && !StringUtility.isEmptyString(token)) {
      mainString = token + mainString;
      return mainString.substring(mainString.lastIndexOf(token) + token.length());
    }
    else
      return "";
  }

  /**
   * Returns the mainstring where the tail is choped off starting from the token.
   * <p><b>Examples:</b>
   * <br>    chopTail ("Jan.Verhulst",".") returns "Jan"
   * @param mainString String
   * @param token String
   * @return String
   */
  public static String chopTail(String mainString, String token) {
      return chopTail(mainString,token,false);
  }

  /**
   * Same as the chopTail(String mainString, String token) method,
   * but the programmer has the ability set the case sensitivity
   * sensitivity
   * @param mainString
   * @param token
   * @param ignoreCase
   * @return String
   */
  public static String chopTail(String mainString, String token, boolean ignoreCase) {
    if (!StringUtility.isEmptyString(mainString) && StringUtility.isEmptyString(token)) {
      return mainString;
    }
    else if (!StringUtility.isEmptyString(mainString) && !StringUtility.isEmptyString(token)) {
      int lastIndex = -1;
      if (ignoreCase) {
        // convert the case of both main string and the token to equal case
        //the value assignment is done to preserve the original case format of the passed in phrase
        String UpperCaseMainString = mainString.toUpperCase();
        String UpperCaseToken = token.toUpperCase();
        lastIndex = UpperCaseMainString.lastIndexOf(UpperCaseToken);
      }
      else {
        lastIndex = mainString.lastIndexOf(token);
      }
      if (lastIndex < 0) // token does not appear in the main string
      {
        return mainString;
      }
      else {
        return mainString.substring(0, lastIndex);
      }
    }
    else {
      return "";
    }
  }

  /**
   * Returns the mainstring where the tail is chopped off starting from the given length.
   * This will add '...' to the return string after chopped.
   * @param mainString
   * @param length
   * @return
   */
  public static String chopTail(String mainString, int length) {
    if (mainString.length() >  length)
      return StringUtility.substring(mainString, 0,length) + "...";
    else
      return mainString;
  }

  /**
   * Same as the chopHead (String mainString, String token),
   * but the programmer has the ability set the case sensitivity
   * @param mainString
   * @param token
   * @param ignoreCase
   * @return String
   */
   public static String chopHead (String mainString, String token, boolean ignoreCase) {
     if (!StringUtility.isEmptyString(mainString) && StringUtility.isEmptyString(token)) {
       return mainString;
     }
     else if (!StringUtility.isEmptyString(mainString) && !StringUtility.isEmptyString(token)) {
       int index = -1;
       if (ignoreCase) {
         // convert the case of both main string and the token to equal case
         //the value assignment is done to preserve the original case format of the passed in phrase
         String UpperCaseMainString = mainString.toUpperCase();
         String UpperCaseToken = token.toUpperCase();
         index = UpperCaseMainString.indexOf(UpperCaseToken);
       }
       else {
         index = mainString.indexOf(token);
       }

       if (index == -1) {
         return mainString;
       }
       else {
         return mainString.substring(index + token.length());
       }
     }
     else {
       return "";
     }
   }

  /**
   * Returns the mainstring where the head is choped off until the token
   * <p><b>Examples:</b>
   * <br>    chopHead ("Jan.Verhulst",".") returns "Verhulst"
   * <br>    chopHead ("Jan",".") returns "Jan"
   * <br>    chopHead ("Jan.",".") returns ""
   * @param mainString String
   * @param token String
   * @return String
   */
  public static String chopHead (String mainString, String token) {
    return chopHead(mainString, token, false);
  }


  /**
   * Return a string value if the start sequence is found from the text.
   * Then text will be subdivided from starts sequence until delimeterChar
   * This method has been programmed using sentinel trick
   * <br>Return null if the start sequence is not found.
   * <p><b>Examples:</b>
   * <br>getToken("MlineCR##EcodeCRTest##CR",�##�)  returns Ecode
   * <br>getToken("MlineCREcodeCRTest##CR",�##�)  returns ""
   * <br>getToken("MlineCR##EcodeCRTest##CR",�88�)   returns null
   * <br>getToken("MlineCR##EcodeCRTest##CR","") returns null
   * <br>getToken("MlineCR##EcodeCRTest##CR",null) returns null
   * <br>getToken("","") returns null
   * <br>getToken(null,"") returns null

   * @param text String
   * @param nextSequence String
   * @return String
   */
  public static String getToken(String text, String startSequence, String delimiterChar) {
    if (StringUtility.isEmptyString(text) || StringUtility.isEmptyString(startSequence))
      return null;
    else {
      int index = text.indexOf(startSequence);
      int length = getLength(startSequence);
      if (index > -1) {
        text = text + delimiterChar;
        int i = text.indexOf(delimiterChar, index);
        return text.substring(index + length, i);
      }
      else {
        return null;
      }
    }
  }

  /**
   * Returns the string that start with the startsequence in the text up until the CR.
   * @param text String
   * @param startSequence String
   * @return String
   */
  public static String getToken(String text, String startSequence) {
    return getToken (text, startSequence, StringUtility.CR);
  }


  /**
   * Appends the given String to the given StringBuilder, followed by a separator character.
   * @param buf StringBuilder
   * @param value String
   */
  public static void appendCSV(StringBuilder buf, String value) {
    buf.append(value);
    buf.append(VALUE_SEPARATOR);
  }

  /**
   * Returns the strings found in the string list as a seperator seperated string.
   * When unique is true then only the unique strings of the string list are returned.
   * When the string list holds strings that are null or empty then they are filtered out
   * @param strList ArrayList of Strings
   * @param separator String
   * @param unique boolean (false => the resulting string can hold doubles)
   */
  public static String stringCSV(ArrayList strList, String separator, boolean unique) {
    StringBuilder result = new StringBuilder();
    if (strList != null && !strList.isEmpty()) {
      Iterator loop = strList.iterator();
      while (loop.hasNext()) {
        String tmpVal = (loop.next().toString());
        if (!StringUtility.isEmptyString(tmpVal))
          if (!unique)
            result.append(tmpVal + separator);
          else if (result.indexOf(tmpVal) < 0) // this string does not already appear in the result ?
            result.append(tmpVal + separator);
      }
      if (result.length() > 0) result.deleteCharAt(result.length() - separator.length()); // removing trailing seperator
    }
    return result.toString();
  }

   public static String stringCSV(ArrayList strList, String separator) {
    return stringCSV (strList, separator, false);
   }


  /**
   * Appends the given String to the given StringBuilder, followed by a separator character.
   * @param buf StringBuilder
   * @param value String
   * @param separator String
   */
  public static void appendCSV(StringBuilder buf, String value, String separator) {
    buf.append(value);
    buf.append(separator);
  }

  /**
   * Return the given string if it is not null
   * else return empty string
   * @param value String
   * @return String
   */
  public static String validate(String value){
    if(isValidString(value))
      return value;
    else
      return "";
  }

  /**
   * returns the no of alpharbertical characers found in a given string
   * if the passes string is empty 0 will be returned
   * @param str string
   * @return int
   */

  public static int countNumberOfAlphaCharacters(String str) {
    int count = 0;
    if (!StringUtility.isEmptyString(str)) {
      for (int index = 0; index < str.length(); index++) {
        char character = str.charAt(index);
        if ((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z')) {
          count += 1;
        }
      }
      return count;
    }
    return 0;
  }

  /**
   * returns the no of numeric characers found in a given string
   * if the passed string is empty 0 will be returned
   * @param str string
   * @return int
   */

 public static int countNumberOfNumericCharacters(String str) {
    int count = 0;
    if (!StringUtility.isEmptyString(str)) {
      for (int index = 0; index < str.length(); index++) {
        char character = str.charAt(index);
        if ((character >= '0' && character <= '9')) {
          count += 1;
        }
      }
      return count;
    }
    return 0;
  }

 /**
  * returns the no of special characers found in a given string
  * if the passed string is empty 0 will be returned
  * @param str string
  * @return int
  */
 public static int countNumberOfSpecialCharacters(String str) {
   int count = 0;
   if (!StringUtility.isEmptyString(str)) {
     for (int index = 0; index < str.length(); index++) {
       char character = str.charAt(index);
       if (!(character >= '0' && character <= '9')&&(!((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z')))) {
         count += 1;
       }
     }
     return count;
   }
   return 0;
 }


    /**
     * return a new ArrayList containing values of str splitted by a coma and trimmed.
     * see method StringUtility.split(String str, String splitRegEx)  for details
     * @param str
     * @return
     */
    public static ArrayList<String> split(String str){
        return split(str, ",");
    }

    /**
     * return a new ArrayList containing values of str splitted by splitRegEx and trimmed
     * IF splitRegEx is null, or str is null, or str is empty, THEN an empty list is returned.
     * @param str
     * @param splitRegEx
     * @return
     */
    public static ArrayList<String> split(String str, String splitRegEx){
        ArrayList<String> result = new ArrayList<String>();
        if (!isEmptyString(str) && splitRegEx != null) {
            String[] splitted = str.split(splitRegEx);
            for (int i=0; i<splitted.length; i++)  {
                if (!splitted[i].trim().equals(""))
                    result.add(splitted[i].trim());
            }
        }
        return result;
    }


  /**
   * Returns ??? in case the string is null or empty
   * @param str
   * @return str or ???
   */
  public static String unknownWhenEmpty (String str) {
    if (isEmptyString(str))
      return "???";
    else
      return str;
  }

  /**
   * Checks & returns the common set of characters of the values in the given String array.
   *
   * Meaning:
   *  Takes the characters(by index) of the values of the array one by one & checks whether that
   *  particular character matches the characters of the other values (in the same index) case insensitively.
   *  By the end of all iterations, all the common characters will be returned as a String.  
   *  The return string will be in the very first value's case.
   *  [Eg. If the values were NAlin1, NalIn2, NaLiN, then the return string will be NAlin]
   * 
   * EX:
   *    <param>Abc1,ABC2,ABc3</param><return>Abc</return>
   *    <param>ABC,CBC,ABC</param><return>""</return>
   *    <param>ABCDE,ABCEE,ABCDE</param><return>ABC</return>
   * 
   * @param names
   * @return String - The first common String part of the String array items.
   */
  public static String getCommonString(String[] names) {
    if (names == null || names.length == 0) return "";
    else if (names.length == 1 || StringUtility.isEmptyString(names[0])) return names[0];

    StringBuilder result = new StringBuilder();   // holds the common string part of the strings in the given array
    String referenceString = names[0];            // first string is used to be compared with all others
    Character referenceChar;                      // char of first string which is used to compare with
    int referencePos = 0;                         // position of the referenceChar in the referenceString
    boolean isTheSameChar = true;                 // true as long as referenceChar is the same for all strings
    String compareString;                         // holds the string with which the reference string is compared
    int compareRow = 1;                           // row in the names array : 1 >= row < names.size

    while (isTheSameChar && referencePos < referenceString.length()) {
      referenceChar = Character.toUpperCase(referenceString.charAt(referencePos));
      while (isTheSameChar && compareRow < names.length) {
        compareString = names[compareRow];
        if (!StringUtility.isEmptyString(compareString)
            && referencePos < compareString.length()
            && referenceChar.equals(Character.toUpperCase(compareString.charAt(referencePos)))) {
          compareRow++;
        }
        else {
          isTheSameChar = false;
        }
      }
      if (isTheSameChar) {
        result.append(referenceString.charAt(referencePos));    // respect upper and lowercase of referenceString
        referencePos++; // let's check the next char
        compareRow = 1; // start with the string after the referenceString
      }
    }
    return result.toString();
  }


    /**
   *  Checks the codeFormat & value  and returns a Formatted String.
   *
   * EX:
   *    <param>AB#,1</param><return>AB1</return>
   *    <param>AB##,1</param><return>AB01</return>
   *    <param>AB###,1</param><return>AB001</return>
   *
   * @param codeFormat , value.
   * @return String - a formmated String.
   */
   public static String getFormattedCode(String codeFormat, String value){
      String rtnValue = "";
      String prefix   = codeFormat.substring(0,codeFormat.indexOf("#"));
      int  len  =  (codeFormat.substring(codeFormat.indexOf("#"))).length();
      rtnValue += prefix;

      if(len>value.length()){
         for(int i=0; i<(len-value.length()); i++){
              rtnValue += "0";
          }
       }
       rtnValue += value;
       return rtnValue;
   }

  /***
   * Returns the initial of given name by following rules
   * The rules are :
   * 
   * 1.     A blank can not be an initial
   * 2.     Take the first char of the first name
   * 3.     Take the char that follows a non alpha char (a blank, -, ., etc)
   *
   * Eg.
   * Jan Verhulst               JV
   * Pieter-Jan Peremans        PJP
   * Jef van de Weyer           JvdW
   * Fons Vander Bom            FVB
   * Karel P.J.D. Kriekemans    KPJDK
   * 
   * @param name
   * @return String
   */
  public static String getInitials(String name) {

    if (isEmptyString(name)) {
      return "";
    }

    name = name.trim(); // remove left right spaces

    StringBuilder initials = new StringBuilder();
    boolean speCharMet = false;

    for (int index = 0; index < name.length(); index++) {

      char character = name.charAt(index);

      // First character should always added
      if (index == 0) {
        initials.append(character);
      }
      // Special character(not a alphabetic char) is met
      else if (!Character.isLetter(character)) {
        speCharMet = true;
      }
      // This is a letter and previous one is a special char
      else if (speCharMet) {
        initials.append(character);
        speCharMet = false;
      }
      // This is a letter but previous one is NOT a special char
      else {
        // no need to append
      }
    }
    return initials.toString();
  }

  /**
   * Return true if the passed string holds the value "1", "true", "Y", "TRUE"
   * else if the passed value holds "0", "false", "N", "FALSE" returns false.
   * If passed String doesnt represent any of the above returns false.
   * @param value
   * @return
   */
  public static boolean isBoolean(String value){
    if (value.equals("1") || value.equalsIgnoreCase("true") || (value.equalsIgnoreCase("Y"))) {
      return true;
    }
    else if (value.equals("0") || value.equalsIgnoreCase("false") || (value.equalsIgnoreCase("N"))) {
      return false;
    }
    else {
      return false;
    }
  }

  public static int getFixedLineNoOfDigits() {
    return FIXED_LINE_NO_OF_DIGITS;
  }

  public static int getMobileNoOfDigits() {
    return MOBILE_NO_OF_DIGITS;
  }

  public static void setFixedLineNoOfDigitsS(int noOfDigits) {
    StringUtility.FIXED_LINE_NO_OF_DIGITS = noOfDigits;
  }

  /**
   * Returns true if valid fixed line number
   * Check for number of digits (FIXED_LINE_NO_OF_DIGITS) exist in a string mixed
   * with characters and numbers
   * @param telephoneNo
   * @return boolean
   */
  public static boolean isValidFixedLineNo(String telephoneNo){
    if(StringUtility.isEmptyString(telephoneNo)){
      return false;
    }
    //Check for exact number of digits between start and end of string
    return telephoneNo.matches("([\\(\\)\\-\\/\\+\\.x\\s]*\\d){" + getFixedLineNoOfDigits() +"}[\\(\\)\\-\\/\\+\\.x\\s]*");
  }

  /**
   * Returns true if valid fixed line number
   * Check for number of digits (MOBILE_NO_OF_DIGITS) exist in a string mixed
   * with characters and numbers
   * @param telephoneNo
   * @return boolean
   */
  public static boolean isValidMobileNo(String telephoneNo){
    if(StringUtility.isEmptyString(telephoneNo)){
      return false;
    }
    //Check for exact number of digits between start and end of string
    return telephoneNo.matches("([\\(\\)\\-\\/\\+\\.x\\s]*\\d){" + getMobileNoOfDigits() +"}[\\(\\)\\-\\/\\+\\.x\\s]*");
  }

  /**
   * Extract all positive digits from string with mixed numbers
   * NOTE: negative  (-) sign omitted
   * e.g  +44 (0)33 22-99 456  -> 440332299456
   * @param stringWithNumbers
   * @return String
   */
  public static String extractDigits(String stringWithNumbers){
    StringBuilder builder = new StringBuilder(100);
    Pattern p = Pattern.compile("\\d+");
    Matcher m = p.matcher(stringWithNumbers);
    while (m.find()) {
     builder.append(m.group());
    }
    return builder.toString();
  }

  /**
   * Sets a value that has multiple lines of text, separated by newline characters (\n).
   * The argument is converted to HTML to achieve this.
   * @param val String
   */
  public static String setValueWithMultipleLines(String val) {
    val = StringUtility.replaceString(val, "\n", "<br>");
    return ("<html>" + val + "</html>");
  }


  
}
