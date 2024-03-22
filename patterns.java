import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class patterns {
    public static void main(String[] args) {
      
       String input="5 + 6";  
       int pos =0;
       while(pos<input.length()){
   
        String data = input.substring(pos);
   
       for (String pat: new String[]{"\\s+","\\d+","\\+"}){
   
          Pattern p= Pattern.compile("^"+pat);
          Matcher matcher = p.matcher(data);
   
          if (matcher.find()) {
              System.out.println(matcher.group(0));
              pos += matcher.end(); 
          //    System.out.println(pos);
              break;
     
         }
       }
     }   
   }
}