package jlodke.dataclasses;

public class UTF8TransliterationTable {
	
/*private static final char[] cyrillicUTF8 = {'\u0430', '\u0431', '\u0432', '\u0433', 
									  '\u0434', '\u0435', '\u0436',
									  '\u0437', '\u0438', '\u0439', '\u043A',
									  '\u043B', '\u043C', '\u043D', '\u043E',
									  '\u043F', '\u0440', '\u0441', '\u0442',
									  '\u0443', '\u0444', '\u0445', '\u0446',
									  '\u0447', '\u0448', '\u0449', '\u044A',
									  '\u044B', '\u044C', '\u044D', '\u044E',
									  '\u044F', '\u0451'};*/
private static final String[] latinUTF8Substitution = {"a", "b", "v", "g", "d", "e", "zh", "z", "i", "y", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "kh", "ts", "ch", "sh", "shch", "\"", "y", "'", "e", "yu", "ya", "e", "e"};
private static final int UTF8TableBeginning = 1072;

public static String getLatinString(String cyrillicString)
{
	String result = "";
	char[] input = cyrillicString.toLowerCase().toCharArray();
	
	for (int i = 0; i< input.length;i++)
	{
		int charCodeDec = input[i]-UTF8TableBeginning;
		if (charCodeDec>=0 && charCodeDec<34) {
			result += latinUTF8Substitution[input[i]-UTF8TableBeginning];
		} else {
			result += Character.toString(input[i]);
		}
	}
	return result;
}


}