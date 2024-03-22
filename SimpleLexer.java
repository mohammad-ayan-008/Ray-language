import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Token class to represent tokens
class Token {
    TokenType type; // Type of the token
    String value;   // Value of the token

    // Constructor to initialize token type and value
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    // Method to convert token to string
    @Override
    public String toString() {
        return "(" + type + ", " + value + ")";
    }
}

// Enum for token types
enum TokenType {
    NUMBER("\\d+"),    // Regular expression for numbers
    PLUS("\\+"),       // Regular expression for the plus operator
    MINUS("-"),        // Regular expression for the minus operator
    TIMES("\\*"),      // Regular expression for the multiplication operator
    DIVIDE("/"),       // Regular expression for the division operator
    LPAREN("\\("),     // Regular expression for the left parenthesis
    RPAREN("\\)"),     // Regular expression for the right parenthesis
    WHITESPACE("\\s+"); // Regular expression for whitespace

    private final String pattern; // Regular expression pattern for each token type

    // Constructor to initialize the pattern for each token type
    TokenType(String pattern) {
        this.pattern = pattern;
    }

    // Method to get the regular expression pattern for a token type
    public String getPattern() {
        return pattern;
    }
}

// Lexer class for tokenizing input string
class Lexer {
    private final List<Token> tokens; // List to store tokens
    private int position;             // Current position in the input string
    private final String input;       // Input string to be tokenized

    // Constructor to initialize the Lexer with input string
    public Lexer(String input) {
        this.input = input;
        this.tokens = new ArrayList<>(); // Initialize tokens list
        this.position = 0;               // Initialize position to 0
        tokenize();                      // Tokenize the input string
    }

    // Method to tokenize the input string
    private void tokenize() {
        while (position < input.length()) {
            String remaining = input.substring(position); // Get remaining substring from current position
            boolean matchFound = false;                   // Flag to track if a token match is found
            for (TokenType tokenType : TokenType.values()) { // Iterate over all token types
                String pattern = tokenType.getPattern();  // Get the regular expression pattern for the token type
                Pattern regex = Pattern.compile("^" + pattern); // Compile the regular expression pattern
                Matcher matcher = regex.matcher(remaining);     // Create a matcher for the pattern and remaining substring
                if (matcher.find()) {                            // Check if a match is found
                    String value = matcher.group(0);             // Get the matched value
                    if (tokenType != TokenType.WHITESPACE) {     // Ignore whitespace tokens
                        tokens.add(new Token(tokenType, value)); // Add token to the list
                    }
                    position += matcher.end();                   // Update position to the end of the matched substring
                    matchFound = true;                           // Set match found flag to true
                    break;                                       // Exit the loop since match is found
                }
            }
            if (!matchFound) {                               // If no match is found for any token type
                throw new RuntimeException("Invalid token at position " + position); // Throw exception
            }
        }
    }

    // Method to get the list of tokens
    public List<Token> getTokens() {
        return tokens;
    }
}

public class SimpleLexer {
    public static void main(String[] args) {
        String input = "3 + 4 * ( 2 - 1 )"; // Input arithmetic expression
        Lexer lexer = new Lexer(input);      // Create a lexer with the input string
        List<Token> tokens = lexer.getTokens(); // Get the list of tokens
        for (Token token : tokens) {             // Iterate over the tokens and print them
            System.out.println(token);
        }
    }
}
