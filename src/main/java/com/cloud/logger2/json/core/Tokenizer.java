package com.cloud.logger2.json.core;

import com.cloud.logger2.json.exception.JsonParseException;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option 词法分析器
 */
public class Tokenizer {
    private ArrayList<Token> tokens = new ArrayList<Token>();
    private Reader reader;
    private boolean isUnread = false;
    private int savedChar;
    private int c;

    public Tokenizer(Reader reader) throws IOException {
        this.reader = reader;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void tokenize() throws Exception {
        Token token;
        do {
            token = start();
            tokens.add(token);
        } while (token.getType() != JsonEnum.END_DOC);
    }

    private Token start() throws Exception {
        c = '?';
        Token token = null;
        do {    //先读一个字符，若为空白符（ASCII码在[0, 20H]上）则接着读，直到刚读的字符非空白符
            c = read();
        } while (isSpace(c));
        if (isNull(c)) {
            return new Token(JsonEnum.NULL, null);
        } else if (c == ',') {
            return new Token(JsonEnum.COMMA, ",");
        } else if (c == ':') {
            return new Token(JsonEnum.COLON, ":");
        } else if (c == '{') {
            return new Token(JsonEnum.START_OBJ, "{");
        } else if (c == '[') {
            return new Token(JsonEnum.START_ARRAY, "[");
        } else if (c == ']') {
            return new Token(JsonEnum.END_ARRAY, "]");
        } else if (c == '}') {
            return new Token(JsonEnum.END_OBJ, "}");
        } else if (isTrue(c)) {
            return new Token(JsonEnum.BOOLEAN, "true");
        } else if (isFalse(c)) {
            return new Token(JsonEnum.BOOLEAN, "false");
        } else if (c == '"') {
            return readString();
        } else if (isNum(c)) {
            unread();
            return readNum();
        } else if (c == -1) {
            return new Token(JsonEnum.END_DOC, "EOF");
        } else {
            throw new JsonParseException("Invalid JSON input.");
        }
    }

    private int read() throws IOException {
        if (!isUnread) {
            int c = reader.read();
            savedChar = c;
            return c;
        } else {
            isUnread = false;
            return savedChar;
        }
    }

    private void unread() {
        isUnread = true;
    }

    private boolean isSpace(int c) {
        return c >= 0 && c <= ' ';
    }

    private boolean isTrue(int c) throws IOException {
        if (c == 't') {
            c = read();
            if (c == 'r') {
                c = read();
                if (c == 'u') {
                    c = read();
                    if (c == 'e') {
                        return true;
                    } else {
                        throw new JsonParseException("Invalid JSON input.");
                    }
                } else {
                    throw new JsonParseException("Invalid JSON input.");
                }
            } else {
                throw new JsonParseException("Invalid JSON input.");
            }
        } else {
            return false;
        }
    }

    private boolean isFalse(int c) throws IOException {
        if (c == 'f') {
            c = read();
            if (c == 'a') {
                c = read();
                if (c == 'l') {
                    c = read();
                    if (c == 's') {
                        c = read();
                        if (c == 'e') {
                            return true;
                        } else {
                            throw new JsonParseException("Invalid JSON input.");
                        }
                    } else {
                        throw new JsonParseException("Invalid JSON input.");
                    }
                } else {
                    throw new JsonParseException("Invalid JSON input.");
                }
            } else {
                throw new JsonParseException("Invalid JSON input.");
            }
        } else {
            return false;
        }
    }

    private boolean isEscape() throws IOException {
        if (c == '\\') {
            c = read();
            if (c == '"' || c == '\\' || c == '/' || c == 'b' ||
                    c == 'f' || c == 'n' || c == 't' || c == 'r' || c == 'u') {
                return true;
            } else {
                throw new JsonParseException("Invalid JSON input.");
            }
        } else {
            return false;
        }
    }

    private boolean isNull(int c) throws IOException {
        if (c == 'n') {
            c = read();
            if (c == 'u') {
                c = read();
                if (c == 'l') {
                    c = read();
                    if (c == 'l') {
                        return true;
                    } else {
                        throw new JsonParseException("Invalid JSON input.");
                    }
                } else {
                    throw new JsonParseException("Invalid JSON input.");
                }
            } else {
                throw new JsonParseException("Invalid JSON input.");
            }
        } else {
            return false;
        }
    }

    private boolean isDigit(int c) {
        return c >= '0' && c <= '9';
    }

    private boolean isDigitOne2Nine(int c){
        return c >= '1' && c <= '9';
    }

    private boolean isNum(int c) {
        return isDigit(c) || c == '-';
    }

    private Token readString() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            c = read();
            //判断是否为\", \\, \/, \b, \f, \n, \t, \r.
            if (isEscape()) {
                if (c == 'u') {
                    sb.append('\\' + (char) c);
                    for (int i = 0; i < 4; i++) {
                        c = read();
                        if (isHex(c)) {
                            sb.append((char) c);
                        } else {
                            throw new JsonParseException("Invalid Json input.");
                        }
                    }
                } else {
                    sb.append("\\" + (char) c);
                }
            } else if (c == '"') {
                return new Token(JsonEnum.STRING, sb.toString());
            } else if (c == '\r' || c == '\n'){
                throw new JsonParseException("Invalid JSON input.");
            } else {
                sb.append((char) c);
            }
        }
    }

    private Token readNum() throws IOException {
        StringBuilder sb = new StringBuilder();
        int c = read();
        if (c == '-') {
            sb.append((char) c);
            c = read();
            if (c == '0') {
                sb.append((char) c);
                numAppend(sb);

            } else if (isDigitOne2Nine(c)) {
                do {
                    sb.append((char) c);
                    c = read();
                } while (isDigit(c));
                unread();
                numAppend(sb);
            } else {
                throw new JsonParseException("- not followed by digit");
            }
        } else if (c == '0') {
            sb.append((char) c);
            numAppend(sb);
        } else if (isDigitOne2Nine(c)) {
            do {
                sb.append((char) c);
                c = read();
            } while (isDigit(c));
            unread();
            numAppend(sb);
        }
        unread();
        return new Token(JsonEnum.NUMBER, sb.toString());
    }

    private void appendFrac(StringBuilder sb) throws IOException {
        c = read();
        while (isDigit(c)) {
            sb.append((char) c);
            c = read();
        }
    }

    private void appendExp(StringBuilder sb) throws IOException {
        int c = read();
        if (c == '+' || c == '-') {
            sb.append((char) c);
            c = read();
            if (!isDigit(c)) {
                throw new JsonParseException("e+(-) or E+(-) not followed by digit");
            } else { //e+(-) digit
                do {
                    sb.append((char) c);
                    c = read();
                } while (isDigit(c));
                unread();
            }
        } else if (!isDigit(c)) {
            throw new JsonParseException("e or E not followed by + or - or digit.");
        } else { //e digit
            do {
                sb.append((char) c);
                c = read();
            } while (isDigit(c));
            unread();
        }
    }

    private void numAppend(StringBuilder sb) throws IOException {
        c = read();
        if (c == '.') {
            sb.append((char) c);
            appendFrac(sb);
            if (isExp(c)) {
                sb.append((char) c);
                appendExp(sb);
            }

        } else if (isExp(c)) {
            sb.append((char) c);
            appendExp(sb);
        } else {
            unread();
        }
    }

    private boolean isHex(int c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') ||
                (c >= 'A' && c <= 'F');
    }

    private boolean isExp(int c) throws IOException {
        return c == 'e' || c == 'E';
    }

    // -------------------------------------------------------------------

    public Token next() {
        return tokens.remove(0);
    }

    public Token peek(int i) {
        return tokens.get(i);
    }

    public boolean hasNext() {
        return tokens.get(0).getType() != JsonEnum.END_DOC;
    }

}
