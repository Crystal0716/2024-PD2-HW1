import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class RegExp
{
    public static void main(String[] args)
    {
        String str1 = args[1];
        String str2 = args[2];
        int s2Count = Integer.parseInt(args[3]);
        str1 = str1.toLowerCase() ;
        str2 = str2.toLowerCase();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = reader.readLine()) != null)
            {
                line = line.trim().toLowerCase() ;
                System.out.print(checkplan(line));
                if(check(line,str1))
                {
                    System.out.print("Y,");
                }
                else
                {
                    System.out.print("N,") ;
                }
                int str2Count = countOccurrences(line , str2);
                if (str2Count >= s2Count)
                {
                    System.out.print("Y,");
                }
                else
                {
                    System.out.print("N,");
                }
                if (containsPattern(line))
                {
                    System.out.println("Y");
                }
                else
                {
                    System.out.println("N");
                }
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private static String checkplan(String line)
    {
        String reversedStr = new StringBuilder(line).reverse().toString();
        if(line.equals(reversedStr))
        {
            return "Y,";
        }
        else
        {
            return "N,";
        }
    }
    private static boolean  check ( String line , String str )
    {
        if( line == null  ||  str == null  || line.length() < str.length())
        {
            return false ;
        }
        for(int i = 0 ; i <= line.length()-str.length() ; i++ )
        {
            boolean found = true ;
            for(int j = 0 ; j < str.length() ; j++)
            {
                if(line.charAt(i+j) != str.charAt(j))
                {
                    found = false ;
                    break ;
                }
            }
            if(found)
            {
                return true ;
            }
        }
        return false ;
    }
    private static int countOccurrences(String line, String searchword) 
    {
        int count = 0;
        int length = searchword.length();
        int lineLength = line.length();
        for (int i = 0; i <= lineLength - length; i++) 
        {
            boolean found = true;
            for (int j = 0; j < length; j++) 
            {
                if (line.charAt(i + j) != searchword.charAt(j)) 
                {
                    found = false;
                    break;
                }
            }
            if (found) 
            {
                count++;
            }
        }
        return count;
    }
    private static boolean containsPattern(String line) 
    {
        boolean foundA = false;
        boolean foundBB = false;
        for (int i = 0; i < line.length() - 1; i++) {
            if (line.charAt(i) == 'a') {
                foundA = true;
            } else if (foundA && line.charAt(i) == 'b' && line.charAt(i + 1) == 'b') {
                foundBB = true;
                break; 
            }
        }
        return foundA && foundBB;
    }
}