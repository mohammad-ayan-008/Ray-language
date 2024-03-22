package Ray_language;

public  class Token {
    public TokenType tokentype;
    public String Vset;;
   
   
    public Token(TokenType type,String Value){
     this.tokentype=type;
     this.Vset=Value;
    } 

    @Override
    public String toString() {
        return "(" + tokentype + ", " + Vset + ")";
    }

}
