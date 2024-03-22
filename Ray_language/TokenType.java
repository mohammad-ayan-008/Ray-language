package Ray_language;

public enum TokenType {
    WHITESPACE("\\s+"),
    NUMBER("\\d+"),
    PRINT("print"),
    LPAREN("\\("),
    RPAREN("\\)"),
    MINUS("-"),    
    SEMICOLAN(";"),
    DIVIDE("/"),
    MULTIPLY("\\*"),
    PLUS("\\+");    

    
    private final String data;
    TokenType(String data){
         this.data=data;
    }

    public String getData(){
     return data;
    }
}

