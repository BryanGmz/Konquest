/* The following code was generated by JFlex 1.4.3 on 28/03/20 20:54 */

package konquest.backed.analizador;

import java_cup.runtime.Symbol;
import konquest.gui.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 28/03/20 20:54 from the specification file
 * <tt>src/konquest/backed/analizador/Lexer.flex</tt>
 */
public class Lexico implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  5,  0,  0,  4,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     9,  0, 58,  0, 61,  0,  0,  0,  0,  0,  0,  0, 53,  3, 60,  0, 
    59,  2,  2,  2,  2,  2,  2,  2,  2,  2, 52,  0,  0,  0,  0,  0, 
     0, 11,  1, 42, 23, 15, 51, 22, 49, 50, 21,  1, 13, 10, 14, 24, 
    12,  1, 20, 17, 16, 19,  1,  1,  1,  1,  1, 56,  0, 57,  0, 18, 
     0, 28, 47, 44, 26, 39, 45, 43,  1, 25, 48,  1, 34, 29, 38, 32, 
    37,  1, 36, 40, 27, 41, 46,  1,  1,  1, 35, 54,  0, 55,  0,  0, 
     7,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  8,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0, 31,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0, 30,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  6,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0, 33,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\1\1\2\1\3\2\1\1\2\1\1\17\2\1\4"+
    "\1\5\1\6\1\7\1\10\1\11\1\2\1\3\6\0"+
    "\1\12\24\0\1\1\31\0\1\13\1\14\1\15\1\0"+
    "\1\16\4\0\1\17\1\0\1\20\21\0\1\21\15\0"+
    "\1\22\2\0\1\23\1\0\1\24\1\0\1\25\6\0"+
    "\1\26\1\27\6\0\1\30\2\0\1\31\4\0\1\32"+
    "\1\33\11\0\1\34\2\0\1\35\4\0\1\36\1\0"+
    "\1\37\3\0\1\40\1\41\1\42\14\0\1\43\12\0"+
    "\1\44\3\0\1\45\22\0\1\46\1\47\1\50\1\0"+
    "\1\51";

  private static int [] zzUnpackAction() {
    int [] result = new int[235];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\76\0\174\0\272\0\370\0\u0136\0\u0174\0\u01b2"+
    "\0\u01f0\0\u022e\0\u026c\0\u02aa\0\u02e8\0\u0326\0\u0364\0\u03a2"+
    "\0\u03e0\0\u041e\0\u045c\0\u049a\0\u04d8\0\u0516\0\76\0\76"+
    "\0\76\0\76\0\76\0\76\0\u0554\0\u0592\0\u05d0\0\u060e"+
    "\0\u064c\0\u068a\0\u06c8\0\u0706\0\76\0\u0744\0\u0782\0\u07c0"+
    "\0\u07fe\0\u083c\0\u087a\0\u08b8\0\u08f6\0\u0934\0\u0972\0\u09b0"+
    "\0\u09ee\0\u0a2c\0\u0a6a\0\u0aa8\0\u0ae6\0\u0b24\0\u0b62\0\u0ba0"+
    "\0\u0bde\0\u0c1c\0\u0c5a\0\u0c98\0\u0cd6\0\u0d14\0\u0d52\0\u0d90"+
    "\0\u0dce\0\u0e0c\0\u0e4a\0\u0e88\0\u0ec6\0\u0f04\0\u0f42\0\u0f80"+
    "\0\u0fbe\0\u0ffc\0\u103a\0\u1078\0\u10b6\0\u10f4\0\u1132\0\u1170"+
    "\0\u11ae\0\u11ec\0\u122a\0\76\0\76\0\u0bde\0\u0136\0\76"+
    "\0\u1268\0\u12a6\0\u12e4\0\u1322\0\76\0\u1360\0\76\0\u139e"+
    "\0\u13dc\0\u141a\0\u1458\0\u1496\0\u14d4\0\u1512\0\u1550\0\u158e"+
    "\0\u15cc\0\u160a\0\u1648\0\u1686\0\u16c4\0\u1702\0\u1740\0\u177e"+
    "\0\76\0\u17bc\0\u17fa\0\u1838\0\u1876\0\u18b4\0\u18f2\0\u1930"+
    "\0\u196e\0\u19ac\0\u19ea\0\u1a28\0\u1a66\0\u1aa4\0\76\0\u1ae2"+
    "\0\u1b20\0\76\0\u1b5e\0\76\0\u1b9c\0\76\0\u1bda\0\u1c18"+
    "\0\u1c56\0\u1c94\0\u1cd2\0\u1d10\0\76\0\76\0\u1d4e\0\u1d8c"+
    "\0\u1dca\0\u1e08\0\u1e46\0\u1e84\0\76\0\u1ec2\0\u1f00\0\76"+
    "\0\u1f3e\0\u1f7c\0\u1fba\0\u1ff8\0\76\0\76\0\u2036\0\u2074"+
    "\0\u20b2\0\u20f0\0\u212e\0\u216c\0\u21aa\0\u21e8\0\u2226\0\u2264"+
    "\0\u22a2\0\u22e0\0\76\0\u231e\0\u235c\0\u239a\0\u23d8\0\u2416"+
    "\0\u2454\0\76\0\u2492\0\u24d0\0\u250e\0\76\0\76\0\76"+
    "\0\u254c\0\u258a\0\u25c8\0\u2606\0\u2644\0\u2682\0\u26c0\0\u26fe"+
    "\0\u273c\0\u277a\0\u27b8\0\u27f6\0\76\0\u2834\0\u2872\0\u28b0"+
    "\0\u28ee\0\u292c\0\u296a\0\u29a8\0\u29e6\0\u2a24\0\u2a62\0\76"+
    "\0\u2aa0\0\u2ade\0\u2b1c\0\76\0\u2b5a\0\u2b98\0\u2bd6\0\u2c14"+
    "\0\u2c52\0\u2c90\0\u2cce\0\u2d0c\0\u2d4a\0\u2d88\0\u2dc6\0\u2e04"+
    "\0\u2e42\0\u2e80\0\u2ebe\0\u2efc\0\u2f3a\0\u2f78\0\76\0\76"+
    "\0\76\0\u2fb6\0\76";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[235];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\2\2\1\3\1\2\1\4\1\5\1\6\2\2\1\7"+
    "\1\10\1\2\1\11\1\2\1\12\6\2\1\13\1\2"+
    "\1\14\1\2\1\15\1\2\1\16\1\17\1\20\7\2"+
    "\1\21\1\22\5\2\1\23\1\24\3\2\1\25\1\2"+
    "\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35"+
    "\1\36\2\2\100\0\1\3\70\0\1\3\6\0\1\4"+
    "\76\0\1\5\77\0\1\37\77\0\1\7\77\0\1\40"+
    "\77\0\1\41\77\0\1\42\101\0\1\43\134\0\1\44"+
    "\45\0\1\45\74\0\1\46\2\0\1\47\7\0\1\50"+
    "\73\0\1\51\11\0\1\52\55\0\1\53\3\0\1\54"+
    "\75\0\1\55\1\0\1\56\1\0\1\57\65\0\1\60"+
    "\3\0\1\61\75\0\1\62\66\0\1\63\2\0\1\64"+
    "\64\0\1\65\65\0\1\66\63\0\1\67\10\0\10\67"+
    "\1\70\13\67\2\0\1\67\1\0\22\67\11\0\1\70"+
    "\2\0\1\3\70\0\1\3\1\71\11\0\1\72\101\0"+
    "\1\73\74\0\1\74\105\0\1\75\100\0\1\76\132\0"+
    "\1\77\57\0\1\100\65\0\1\101\111\0\1\102\37\0"+
    "\1\103\133\0\1\104\71\0\1\105\100\0\1\106\71\0"+
    "\1\107\65\0\1\110\101\0\1\111\113\0\1\112\54\0"+
    "\1\113\102\0\1\114\75\0\1\115\3\0\1\116\71\0"+
    "\1\117\45\0\1\120\135\0\1\121\24\0\1\122\1\123"+
    "\1\70\6\0\10\122\1\70\13\122\2\0\1\122\1\0"+
    "\22\122\6\0\1\124\1\123\1\0\1\70\1\0\3\70"+
    "\6\0\24\70\2\0\1\70\1\0\22\70\6\0\1\125"+
    "\1\70\1\0\1\70\2\0\1\126\70\0\1\126\10\0"+
    "\1\127\102\0\1\130\100\0\1\131\77\0\1\132\70\0"+
    "\1\133\144\0\1\134\53\0\1\135\71\0\1\136\110\0"+
    "\1\137\71\0\1\140\67\0\1\141\74\0\1\142\74\0"+
    "\1\143\116\0\1\144\67\0\1\145\61\0\1\146\112\0"+
    "\1\147\105\0\1\150\67\0\1\151\60\0\1\152\75\0"+
    "\1\153\111\0\1\154\40\0\1\155\144\0\1\156\14\0"+
    "\1\157\1\160\1\70\6\0\10\157\1\70\13\157\2\0"+
    "\1\157\1\0\22\157\6\0\1\124\1\160\1\0\1\70"+
    "\1\0\2\160\1\70\6\0\10\160\1\70\13\160\2\0"+
    "\1\160\1\0\22\160\6\0\1\161\1\160\1\0\1\70"+
    "\17\0\1\162\102\0\1\163\100\0\1\164\120\0\1\165"+
    "\61\0\1\166\2\0\1\167\70\0\1\170\112\0\1\171"+
    "\76\0\1\172\67\0\1\173\100\0\1\174\75\0\1\175"+
    "\77\0\1\176\74\0\1\177\71\0\1\200\66\0\1\201"+
    "\110\0\1\202\67\0\1\203\102\0\1\204\44\0\1\205"+
    "\74\0\1\206\61\0\2\207\1\70\6\0\10\207\1\70"+
    "\13\207\2\0\1\207\1\0\22\207\6\0\1\124\1\207"+
    "\1\0\1\70\1\0\2\207\1\70\6\0\10\207\1\70"+
    "\13\207\2\0\1\207\1\0\22\207\6\0\1\161\1\207"+
    "\1\0\1\70\20\0\1\210\70\0\1\211\112\0\1\212"+
    "\127\0\1\213\52\0\1\214\76\0\1\215\101\0\1\216"+
    "\73\0\1\217\64\0\1\220\100\0\1\221\107\0\1\222"+
    "\62\0\1\223\116\0\1\224\70\0\1\225\74\0\1\226"+
    "\60\0\1\227\74\0\1\230\46\0\2\231\1\70\6\0"+
    "\10\231\1\70\13\231\2\0\1\231\1\0\22\231\6\0"+
    "\1\161\1\231\1\0\1\70\13\0\1\232\77\0\1\233"+
    "\104\0\1\234\66\0\1\235\120\0\1\236\71\0\1\237"+
    "\110\0\1\240\72\0\1\241\64\0\1\242\76\0\1\243"+
    "\115\0\1\244\55\0\1\245\104\0\1\246\33\0\2\247"+
    "\1\70\6\0\10\247\1\70\13\247\2\0\1\247\1\0"+
    "\22\247\6\0\1\161\1\247\1\0\1\70\21\0\1\250"+
    "\73\0\1\251\75\0\1\252\122\0\1\253\104\0\1\254"+
    "\40\0\1\255\1\256\112\0\1\257\111\0\1\260\56\0"+
    "\1\261\114\0\1\262\61\0\1\263\42\0\2\264\1\70"+
    "\6\0\10\264\1\70\13\264\2\0\1\264\1\0\22\264"+
    "\6\0\1\161\1\264\1\0\1\70\22\0\1\265\74\0"+
    "\1\266\75\0\1\267\114\0\1\270\71\0\1\271\111\0"+
    "\1\272\105\0\1\273\33\0\1\274\117\0\1\275\111\0"+
    "\1\276\22\0\2\277\1\70\6\0\10\277\1\70\13\277"+
    "\2\0\1\277\1\0\22\277\6\0\1\161\1\277\1\0"+
    "\1\70\16\0\1\300\135\0\1\301\52\0\1\302\111\0"+
    "\1\303\75\0\1\304\74\0\1\305\60\0\1\306\45\0"+
    "\2\307\1\70\6\0\10\307\1\70\13\307\2\0\1\307"+
    "\1\0\22\307\6\0\1\161\1\307\1\0\1\70\17\0"+
    "\1\310\125\0\1\311\62\0\1\312\53\0\1\313\134\0"+
    "\1\314\64\0\1\315\36\0\2\316\1\70\6\0\10\316"+
    "\1\70\13\316\2\0\1\316\1\0\22\316\6\0\1\161"+
    "\1\316\1\0\1\70\23\0\1\317\122\0\1\320\57\0"+
    "\1\321\114\0\1\322\57\0\1\323\110\0\1\324\30\0"+
    "\3\70\6\0\24\70\2\0\1\70\1\0\22\70\6\0"+
    "\1\161\1\70\1\0\1\70\20\0\1\325\106\0\1\326"+
    "\113\0\1\327\72\0\1\330\55\0\1\331\121\0\1\332"+
    "\71\0\1\333\65\0\1\334\54\0\1\335\115\0\1\336"+
    "\75\0\1\337\104\0\1\340\50\0\1\341\111\0\1\342"+
    "\113\0\1\343\75\0\1\344\45\0\1\345\132\0\1\346"+
    "\71\0\1\347\75\0\1\350\46\0\1\351\110\0\1\352"+
    "\111\0\1\353\25\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[12276];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\1\1\11\24\1\6\11\2\1\6\0\1\11\24\0"+
    "\1\1\31\0\2\11\1\1\1\0\1\11\4\0\1\11"+
    "\1\0\1\11\21\0\1\11\15\0\1\11\2\0\1\11"+
    "\1\0\1\11\1\0\1\11\6\0\2\11\6\0\1\11"+
    "\2\0\1\11\4\0\2\11\11\0\1\1\2\0\1\11"+
    "\4\0\1\1\1\0\1\11\3\0\3\11\14\0\1\11"+
    "\12\0\1\11\3\0\1\11\22\0\3\11\1\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[235];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    private DialogoErrores errores;
    
    public void setErrores(DialogoErrores errores) {
        this.errores = errores;
    }

    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }

    private Symbol symbol(int type){
        return new Symbol(type, yyline + 1, yycolumn + 1);
    }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexico(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Lexico(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 21: 
          { return new Symbol(sym.Facil, yychar, yyline, yytext());
          }
        case 42: break;
        case 18: 
          { return new Symbol(sym.Naves, yychar, yyline, yytext());
          }
        case 43: break;
        case 30: 
          { return new Symbol(sym.PlanetasJugadores, yychar, yyline, yytext());
          }
        case 44: break;
        case 32: 
          { return new Symbol(sym.Neutrales, yychar, yyline, yytext());
          }
        case 45: break;
        case 8: 
          { return new Symbol(sym.LlaveA, yychar, yyline, yytext());
          }
        case 46: break;
        case 39: 
          { return new Symbol(sym.PlanetasNeutralesMapa, yychar, yyline, yytext());
          }
        case 47: break;
        case 2: 
          { errores.agregarErroresTXTLexico(
    "Error Lexico: " + " token: << " + yytext() + " >> columna: " + (yycolumn + 1) +
    " linea: " + (yyline + 1));
    //return new Symbol(sym.Error, yychar, yyline, new String(yytext()));
          }
        case 48: break;
        case 33: 
          { return new Symbol(sym.Jugadores, yychar, yyline, yytext());
          }
        case 49: break;
        case 28: 
          { return new Symbol(sym.Planetas, yychar, yyline, yytext());
          }
        case 50: break;
        case 22: 
          { return new Symbol(sym.Tamaño, yychar, yyline, yytext());
          }
        case 51: break;
        case 41: 
          { return new Symbol(sym.MostrarEstadisticas, yychar, yyline, yytext());
          }
        case 52: break;
        case 29: 
          { return new Symbol(sym.Acumular, yychar, yyline, yytext());
          }
        case 53: break;
        case 37: 
          { return new Symbol(sym.Finalizacion, yychar, yyline, yytext());
          }
        case 54: break;
        case 12: 
          { return new Symbol(sym.Identificador, yychar, yyline, new String(yytext()));
          }
        case 55: break;
        case 31: 
          { return new Symbol(sym.Columnas, yychar, yyline, yytext());
          }
        case 56: break;
        case 34: 
          { return new Symbol(sym.MapaCiego, yychar, yyline, yytext());
          }
        case 57: break;
        case 6: 
          { return new Symbol(sym.CorcheteA, yychar, yyline, yytext());
          }
        case 58: break;
        case 9: 
          { return new Symbol(sym.LlaveC, yychar, yyline, yytext());
          }
        case 59: break;
        case 4: 
          { return new Symbol(sym.DosPuntos, yychar, yyline, yytext());
          }
        case 60: break;
        case 15: 
          { return new Symbol(sym.Tipo, yychar, yyline, yytext());
          }
        case 61: break;
        case 11: 
          { return new Symbol(sym.NombrePlanetas, yychar, yyline, new String(yytext()));
          }
        case 62: break;
        case 26: 
          { return new Symbol(sym.Dificil, yychar, yyline, yytext());
          }
        case 63: break;
        case 3: 
          { return new Symbol(sym.Numero, yychar, yyline, new String(yytext()));
          }
        case 64: break;
        case 13: 
          { return new Symbol(sym.Decimal, yychar, yyline, new String(yytext()));
          }
        case 65: break;
        case 24: 
          { return new Symbol(sym.Nombre, yychar, yyline, yytext());
          }
        case 66: break;
        case 10: 
          { return new Symbol(sym.ID, yychar, yyline, yytext());
          }
        case 67: break;
        case 36: 
          { return new Symbol(sym.MostrarNaves, yychar, yyline, yytext());
          }
        case 68: break;
        case 25: 
          { return new Symbol(sym.Humano, yychar, yyline, yytext());
          }
        case 69: break;
        case 19: 
          { return new Symbol(sym.Filas, yychar, yyline, yytext());
          }
        case 70: break;
        case 35: 
          { return new Symbol(sym.Produccion, yychar, yyline, yytext());
          }
        case 71: break;
        case 7: 
          { return new Symbol(sym.CorcheteC, yychar, yyline, yytext());
          }
        case 72: break;
        case 20: 
          { return new Symbol(sym.False, yychar, yyline, yytext());
          }
        case 73: break;
        case 38: 
          { return new Symbol(sym.PorcentajeMuertes, yychar, yyline, yytext());
          }
        case 74: break;
        case 1: 
          { /*Ignore*/
          }
        case 75: break;
        case 5: 
          { return new Symbol(sym.Coma, yychar, yyline, yytext());
          }
        case 76: break;
        case 40: 
          { return new Symbol(sym.PlanetasNeutrales, yychar, yyline, yytext());
          }
        case 77: break;
        case 16: 
          { return new Symbol(sym.True, yychar, yyline, yytext());
          }
        case 78: break;
        case 27: 
          { return new Symbol(sym.TamañoS, yychar, yyline, yytext());
          }
        case 79: break;
        case 17: 
          { return new Symbol(sym.NombreJugadores, yychar, yyline, new String(yytext()));
          }
        case 80: break;
        case 14: 
          { return new Symbol(sym.Mapa, yychar, yyline, yytext());
          }
        case 81: break;
        case 23: 
          { return new Symbol(sym.AlAzar, yychar, yyline, yytext());
          }
        case 82: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }

  /**
   * Converts an int token code into the name of the
   * token by reflection on the cup symbol class/interface sym
   *
   * This code was contributed by Karl Meissner <meissnersd@yahoo.com>
   */
  private String getTokenName(int token) {
    try {
      java.lang.reflect.Field [] classFields = sym.class.getFields();
      for (int i = 0; i < classFields.length; i++) {
        if (classFields[i].getInt(null) == token) {
          return classFields[i].getName();
        }
      }
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }

    return "UNKNOWN TOKEN";
  }

  /**
   * Same as next_token but also prints the token to standard out
   * for debugging.
   *
   * This code was contributed by Karl Meissner <meissnersd@yahoo.com>
   */
  public java_cup.runtime.Symbol debug_next_token() throws java.io.IOException {
    java_cup.runtime.Symbol s = next_token();
    System.out.println( "line:" + (yyline+1) + " col:" + (yycolumn+1) + " --"+ yytext() + "--" + getTokenName(s.sym) + "--");
    return s;
  }

  /**
   * Runs the scanner on input files.
   *
   * This main method is the debugging routine for the scanner.
   * It prints debugging information about each returned token to
   * System.out until the end of file is reached, or an error occured.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java Lexico <inputfile>");
    }
    else {
      for (int i = 0; i < argv.length; i++) {
        Lexico scanner = null;
        try {
          scanner = new Lexico( new java.io.FileReader(argv[i]) );
          while ( !scanner.zzAtEOF ) scanner.debug_next_token();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}