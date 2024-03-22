package Ray_language;
import java.util.List;
import java.util.List;

public class RayParser {
    private List<Token> tokens;
    private int currentTokenIndex;

    public RayParser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
    }

    public void parseProgram() {
        while (currentTokenIndex < tokens.size()) {
            if (match(TokenType.PRINT)) {
                parsePrintStatement();
            } else {
                int result = parseExpression();
                System.out.println("Result: " + result);
            }
        }
    }

    private void parsePrintStatement() {
        consumeToken(TokenType.PRINT);
        int value = parseExpression();
        System.out.println("Print: " + value);
        consumeToken(TokenType.SEMICOLAN); // Consume semicolon after print statement
    }

    private int parseExpression() {
        int result = parseTerm();
        while (match(TokenType.PLUS) || match(TokenType.MINUS)) {
            Token operator = consumeToken();
            int rightTerm = parseTerm();
            if (operator.tokentype == TokenType.PLUS) {
                result += rightTerm;
            } else {
                result -= rightTerm;
            }
        }
        return result;
    }

    private int parseTerm() {
        int result = parseFactor();
        while (match(TokenType.MULTIPLY) || match(TokenType.DIVIDE)) {
            Token operator = consumeToken();
            int rightFactor = parseFactor();
            if (operator.tokentype == TokenType.MULTIPLY) {
                result *= rightFactor;
            } else {
                result /= rightFactor;
            }
        }
        return result;
    }

    private int parseFactor() {
        if (match(TokenType.NUMBER)) {
            return Integer.parseInt(consumeToken().Vset);
        } else if (match(TokenType.LPAREN)) {
            consumeToken(TokenType.LPAREN); // Consume LPAREN token
            int expressionValue = parseExpression();
            consumeToken(TokenType.RPAREN); // Consume RPAREN token
            return expressionValue;
        } else {
            throw new RuntimeException("Unexpected token: " + peek().tokentype);
        }
    }

    private boolean match(TokenType type) {
        if (currentTokenIndex < tokens.size()) {
            return tokens.get(currentTokenIndex).tokentype == type;
        }
        return false;
    }

    private Token consumeToken() {
        if (currentTokenIndex < tokens.size()) {
            return tokens.get(currentTokenIndex++);
        }
        throw new RuntimeException("No more tokens to consume.");
    }

    private Token consumeToken(TokenType expectedType) {
        Token token = consumeToken();
        if (token.tokentype!= expectedType) {
            throw new RuntimeException("Expected " + expectedType + " but found " + token.tokentype);
        }
        return token;
    }

    private Token peek() {
        if (currentTokenIndex < tokens.size()) {
            return tokens.get(currentTokenIndex);
        }
        throw new RuntimeException("No more tokens to peek.");
    }
}
