package Ray_language;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ray_lexer {
  private List<Token> data;
  private int position;
  private String input;
   
   public Ray_lexer(String input) {
        this.input = input;
        this.data = new ArrayList<>();
        this.position = 0;

        // Tokenize the input string
        tokenize();

         }

         public void tokenize(){
           while (position <input.length()){
                 String remaining = input.substring(position);
                 boolean found=false;
                 for ( TokenType type :TokenType.values()){
                    
                    Pattern pattern = Pattern.compile("^"+type.getData());
                    Matcher matcher = pattern.matcher(remaining);
                 
                    if(matcher.find()){
                 
                        String value = matcher.group(0);
                 
                        if(type !=  TokenType.WHITESPACE){
                            if(type==TokenType.PRINT){
                              data.add(new Token(type, "print"));
                           
                            }else{
                               data.add(new Token(type, value));
                            }
                        }
                 
                        position+=matcher.end();
                        found=true;
                        break;
                    }

                 }

                 if(!found){
                    System.out.println("Illegal token");
                 }
            }
         }

         public List<Token> get_List(){
             return data;
         }

}
