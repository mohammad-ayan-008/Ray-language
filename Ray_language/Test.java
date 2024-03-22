package Ray_language;

import java.util.List;

public class Test {
  public static void main(String[] args) {
     String input = "print 5 + 6 + 5;";

        try {
            // Instantiate lexer and tokenize input
            Ray_lexer lexer = new Ray_lexer(input);
            lexer.tokenize();
            List<Token> tokens = lexer.get_List();

            // Instantiate parser and parse tokens
            RayParser parser = new RayParser(tokens);
            parser.parseProgram();

            System.out.println("Parsing completed successfully.");
            parser.parseProgram();
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        }
  }  
}
