/* The following code was generated by JFlex 1.4.3 on 28/03/20 0:48 */

package konquest.backed.analizador.partida;

import java_cup.runtime.Symbol;
import konquest.gui.DialogoErrores;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 28/03/20 0:48 from the specification file
 * <tt>src/konquest/backed/analizador/partida/LexerGuardarPartida.flex</tt>
 */
public class LexerGuardarPartida implements java_cup.runtime.Scanner {

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
     0,  0,  0,  0,  0,  0,  0,  0,  0,  3,  4,  0,  0,  3,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     8,  0, 58,  0,  0,  0,  0,  0,  0,  0,  0,  0, 53,  0, 60,  0, 
    59,  2,  2,  2,  2,  2,  2,  2,  2,  2, 52,  0,  0,  0,  0,  0, 
     0, 10,  1, 33, 22, 14, 51, 21, 49, 50, 20,  1, 12,  9, 13, 23, 
    11, 24, 19, 16, 15, 18,  1,  1,  1,  1,  1, 56,  0, 57,  0, 17, 
     0, 26, 46, 41, 44, 35, 37, 36,  1, 34, 48,  1, 39, 27, 38, 30, 
    32,  1, 43, 45, 25, 42, 47,  1,  1,  1, 40, 54,  0, 55,  0,  0, 
     6,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0, 29,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0, 28,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  5,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0, 31,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\1\1\2\1\3\2\1\1\2\1\1\16\2\1\4"+
    "\1\5\1\6\1\7\1\10\1\11\1\2\1\3\31\0"+
    "\1\1\31\0\1\12\1\13\1\0\1\14\5\0\1\15"+
    "\1\0\1\16\6\0\1\17\11\0\1\20\6\0\1\21"+
    "\4\0\1\22\1\0\1\23\1\24\5\0\1\25\1\0"+
    "\1\26\4\0\1\27\5\0\1\30\3\0\1\31\3\0"+
    "\1\32\1\33\6\0\1\34\1\35\1\0\1\36\5\0"+
    "\1\37\3\0\1\40\2\0\1\41\1\42\13\0\1\43"+
    "\20\0\1\44\5\0\1\45\4\0\1\46\10\0\1\47"+
    "\2\0\1\50\1\51\1\52";

  private static int [] zzUnpackAction() {
    int [] result = new int[230];
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
    "\0\0\0\75\0\172\0\267\0\364\0\u0131\0\u016e\0\u01ab"+
    "\0\u01e8\0\u0225\0\u0262\0\u029f\0\u02dc\0\u0319\0\u0356\0\u0393"+
    "\0\u03d0\0\u040d\0\u044a\0\u0487\0\u04c4\0\75\0\75\0\75"+
    "\0\75\0\75\0\75\0\u0501\0\u053e\0\u057b\0\u05b8\0\u05f5"+
    "\0\u0632\0\u066f\0\u06ac\0\u06e9\0\u0726\0\u0763\0\u07a0\0\u07dd"+
    "\0\u081a\0\u0857\0\u0894\0\u08d1\0\u090e\0\u094b\0\u0988\0\u09c5"+
    "\0\u0a02\0\u0a3f\0\u0a7c\0\u0ab9\0\u0af6\0\u0b33\0\u0b70\0\u0bad"+
    "\0\u0bea\0\u0c27\0\u0c64\0\u0ca1\0\u0cde\0\u0d1b\0\u0d58\0\u0d95"+
    "\0\u0dd2\0\u0e0f\0\u0e4c\0\u0e89\0\u0ec6\0\u0f03\0\u0f40\0\u0f7d"+
    "\0\u0fba\0\u0ff7\0\u1034\0\u1071\0\u10ae\0\u10eb\0\u1128\0\u1165"+
    "\0\75\0\u0b33\0\u0131\0\75\0\u11a2\0\u11df\0\u121c\0\u1259"+
    "\0\u1296\0\75\0\u12d3\0\75\0\u1310\0\u134d\0\u138a\0\u13c7"+
    "\0\u1404\0\u1441\0\u147e\0\u14bb\0\u14f8\0\u1535\0\u1572\0\u15af"+
    "\0\u15ec\0\u1629\0\u1666\0\u16a3\0\75\0\u16e0\0\u171d\0\u175a"+
    "\0\u1797\0\u17d4\0\u1811\0\75\0\u184e\0\u188b\0\u18c8\0\u1905"+
    "\0\75\0\u1942\0\75\0\75\0\u197f\0\u19bc\0\u19f9\0\u1a36"+
    "\0\u1a73\0\75\0\u1ab0\0\75\0\u1aed\0\u1b2a\0\u1b67\0\u1ba4"+
    "\0\75\0\u1be1\0\u1c1e\0\u1c5b\0\u1c98\0\u1cd5\0\75\0\u1d12"+
    "\0\u1d4f\0\u1d8c\0\75\0\u1dc9\0\u1e06\0\u1e43\0\75\0\75"+
    "\0\u1e80\0\u1ebd\0\u1efa\0\u1f37\0\u1f74\0\u1fb1\0\u1fee\0\75"+
    "\0\u202b\0\u2068\0\u20a5\0\u20e2\0\u211f\0\u215c\0\u2199\0\75"+
    "\0\u21d6\0\u2213\0\u2250\0\75\0\u228d\0\u22ca\0\75\0\75"+
    "\0\u2307\0\u2344\0\u2381\0\u23be\0\u23fb\0\u2438\0\u2475\0\u24b2"+
    "\0\u24ef\0\u252c\0\u2569\0\75\0\u25a6\0\u25e3\0\u2620\0\u265d"+
    "\0\u269a\0\u26d7\0\u2714\0\u2751\0\u278e\0\u27cb\0\u2808\0\u2845"+
    "\0\u2882\0\u28bf\0\u28fc\0\u2939\0\75\0\u2976\0\u29b3\0\u29f0"+
    "\0\u2a2d\0\u2a6a\0\75\0\u2aa7\0\u2ae4\0\u2b21\0\u2b5e\0\75"+
    "\0\u2b9b\0\u2bd8\0\u2c15\0\u2c52\0\u2c8f\0\u2ccc\0\u2d09\0\u2d46"+
    "\0\75\0\u2d83\0\u2dc0\0\75\0\75\0\75";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[230];
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
    "\2\2\1\3\1\4\1\5\1\6\2\2\1\7\1\10"+
    "\1\11\1\12\10\2\1\13\1\2\1\14\2\2\1\15"+
    "\1\2\1\16\4\2\1\17\4\2\1\20\1\21\2\2"+
    "\1\22\6\2\1\23\1\24\1\2\1\25\1\26\1\27"+
    "\1\30\1\31\1\32\1\33\1\34\1\35\1\2\77\0"+
    "\1\3\70\0\1\3\4\0\1\4\75\0\1\5\76\0"+
    "\1\36\76\0\1\7\76\0\1\37\101\0\1\40\71\0"+
    "\1\41\102\0\1\42\134\0\1\43\44\0\1\44\7\0"+
    "\1\45\7\0\1\46\1\47\53\0\1\50\100\0\1\51"+
    "\10\0\1\52\3\0\1\53\53\0\1\54\7\0\1\55"+
    "\64\0\1\56\3\0\1\57\70\0\1\60\3\0\1\61"+
    "\110\0\1\62\44\0\1\63\64\0\1\64\63\0\1\65"+
    "\7\0\10\65\1\0\12\65\2\0\1\65\1\0\24\65"+
    "\13\0\1\3\70\0\1\3\1\66\7\0\1\67\100\0"+
    "\1\70\73\0\1\71\74\0\1\72\107\0\1\73\132\0"+
    "\1\74\44\0\1\75\101\0\1\76\107\0\1\77\73\0"+
    "\1\100\62\0\1\101\107\0\1\102\53\0\1\103\100\0"+
    "\1\104\105\0\1\105\73\0\1\106\1\107\104\0\1\110"+
    "\50\0\1\111\107\0\1\112\75\0\1\113\71\0\1\114"+
    "\41\0\1\115\124\0\1\116\34\0\1\117\1\120\6\0"+
    "\10\117\1\0\12\117\2\0\1\117\1\0\24\117\6\0"+
    "\1\121\1\120\3\0\1\122\70\0\1\122\6\0\1\123"+
    "\101\0\1\124\112\0\1\125\61\0\1\126\71\0\1\127"+
    "\144\0\1\130\44\0\1\131\100\0\1\132\104\0\1\133"+
    "\71\0\1\134\63\0\1\135\113\0\1\136\71\0\1\137"+
    "\102\0\1\140\75\0\1\141\51\0\1\142\74\0\1\143"+
    "\105\0\1\144\107\0\1\145\47\0\1\146\115\0\1\147"+
    "\54\0\1\150\54\0\1\151\144\0\1\152\13\0\1\153"+
    "\1\154\6\0\10\153\1\0\12\153\2\0\1\153\1\0"+
    "\24\153\6\0\1\121\1\154\2\0\2\154\6\0\10\154"+
    "\1\0\12\154\2\0\1\154\1\0\24\154\6\0\1\155"+
    "\1\154\23\0\1\156\70\0\1\157\104\0\1\160\107\0"+
    "\1\161\67\0\1\162\2\0\1\163\73\0\1\164\77\0"+
    "\1\165\76\0\1\166\74\0\1\167\103\0\1\170\65\0"+
    "\1\171\100\0\1\172\102\0\1\173\74\0\1\174\72\0"+
    "\1\175\63\0\1\176\65\0\1\177\115\0\1\200\35\0"+
    "\1\201\73\0\1\202\61\0\2\203\6\0\10\203\1\0"+
    "\12\203\2\0\1\203\1\0\24\203\6\0\1\121\1\203"+
    "\2\0\2\203\6\0\10\203\1\0\12\203\2\0\1\203"+
    "\1\0\24\203\6\0\1\155\1\203\17\0\1\204\75\0"+
    "\1\205\104\0\1\206\127\0\1\207\47\0\1\210\75\0"+
    "\1\211\100\0\1\212\100\0\1\213\57\0\1\214\114\0"+
    "\1\215\65\0\1\216\75\0\1\217\105\0\1\220\66\0"+
    "\1\221\64\0\1\222\65\0\1\223\46\0\2\224\6\0"+
    "\10\224\1\0\12\224\2\0\1\224\1\0\24\224\6\0"+
    "\1\155\1\224\13\0\1\225\105\0\1\226\65\0\1\227"+
    "\116\0\1\230\101\0\1\231\62\0\1\232\75\0\1\233"+
    "\113\0\1\234\73\0\1\235\56\0\1\236\74\0\1\237"+
    "\115\0\1\240\22\0\2\241\6\0\10\241\1\0\12\241"+
    "\2\0\1\241\1\0\24\241\6\0\1\155\1\241\21\0"+
    "\1\242\72\0\1\243\122\0\1\244\62\0\1\245\70\0"+
    "\1\246\1\247\25\0\1\250\61\0\1\251\64\0\1\252"+
    "\116\0\1\253\75\0\1\254\20\0\2\255\6\0\10\255"+
    "\1\0\12\255\2\0\1\255\1\0\24\255\6\0\1\155"+
    "\1\255\22\0\1\256\73\0\1\257\112\0\1\260\116\0"+
    "\1\261\57\0\1\262\104\0\1\263\57\0\1\264\107\0"+
    "\1\265\36\0\1\266\62\0\2\267\6\0\10\267\1\0"+
    "\12\267\2\0\1\267\1\0\24\267\6\0\1\155\1\267"+
    "\16\0\1\270\122\0\1\271\106\0\1\272\61\0\1\273"+
    "\100\0\1\274\70\0\1\275\101\0\1\276\3\0\1\277"+
    "\22\0\2\300\6\0\10\300\1\0\12\300\2\0\1\300"+
    "\1\0\24\300\6\0\1\155\1\300\17\0\1\301\67\0"+
    "\1\302\114\0\1\303\107\0\1\304\66\0\1\305\70\0"+
    "\1\306\100\0\1\307\37\0\2\310\6\0\10\310\1\0"+
    "\12\310\2\0\1\310\1\0\24\310\6\0\1\155\1\310"+
    "\23\0\1\311\124\0\1\312\64\0\1\313\75\0\1\314"+
    "\77\0\1\315\74\0\1\316\102\0\1\317\112\0\1\155"+
    "\21\0\1\320\120\0\1\321\77\0\1\322\74\0\1\323"+
    "\71\0\1\324\103\0\1\325\45\0\1\326\124\0\1\327"+
    "\57\0\1\330\67\0\1\331\114\0\1\332\35\0\1\333"+
    "\113\0\1\334\75\0\1\335\104\0\1\336\46\0\1\337"+
    "\123\0\1\340\106\0\1\341\73\0\1\342\36\0\1\343"+
    "\133\0\1\344\51\0\1\345\62\0\1\346\54\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[11773];
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
    "\1\1\1\11\23\1\6\11\2\1\31\0\1\1\31\0"+
    "\1\11\1\1\1\0\1\11\5\0\1\11\1\0\1\11"+
    "\6\0\1\1\11\0\1\11\6\0\1\11\4\0\1\11"+
    "\1\0\2\11\5\0\1\11\1\0\1\11\4\0\1\11"+
    "\5\0\1\11\3\0\1\11\3\0\2\11\6\0\1\1"+
    "\1\11\1\0\1\1\5\0\1\11\3\0\1\11\2\0"+
    "\2\11\13\0\1\11\20\0\1\11\5\0\1\11\4\0"+
    "\1\11\10\0\1\11\2\0\3\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[230];
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
  public LexerGuardarPartida(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public LexerGuardarPartida(java.io.InputStream in) {
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
        case 43: break;
        case 20: 
          { return new Symbol(sym.Naves, yychar, yyline, yytext());
          }
        case 44: break;
        case 31: 
          { return new Symbol(sym.PlanetasJugadores, yychar, yyline, yytext());
          }
        case 45: break;
        case 28: 
          { return new Symbol(sym.Columna, yychar, yyline, yytext());
          }
        case 46: break;
        case 8: 
          { return new Symbol(sym.LlaveA, yychar, yyline, yytext());
          }
        case 47: break;
        case 37: 
          { return new Symbol(sym.PlanetaOrigen, yychar, yyline, yytext());
          }
        case 48: break;
        case 2: 
          { errores.agregarErroresTXTLexico(
    "Error Lexico: " + " token: << " + yytext() + " >> columna: " + (yycolumn + 1) +
    " linea: " + (yyline + 1));
    //return new Symbol(sym.Error, yychar, yyline, new String(yytext()));
          }
        case 49: break;
        case 33: 
          { return new Symbol(sym.Jugadores, yychar, yyline, yytext());
          }
        case 50: break;
        case 30: 
          { return new Symbol(sym.Planetas, yychar, yyline, yytext());
          }
        case 51: break;
        case 23: 
          { return new Symbol(sym.Tamaño, yychar, yyline, yytext());
          }
        case 52: break;
        case 36: 
          { return new Symbol(sym.Finalizacion, yychar, yyline, yytext());
          }
        case 53: break;
        case 32: 
          { return new Symbol(sym.Columnas, yychar, yyline, yytext());
          }
        case 54: break;
        case 34: 
          { return new Symbol(sym.MapaCiego, yychar, yyline, yytext());
          }
        case 55: break;
        case 6: 
          { return new Symbol(sym.CorcheteA, yychar, yyline, yytext());
          }
        case 56: break;
        case 9: 
          { return new Symbol(sym.LlaveC, yychar, yyline, yytext());
          }
        case 57: break;
        case 4: 
          { return new Symbol(sym.DosPuntos, yychar, yyline, yytext());
          }
        case 58: break;
        case 13: 
          { return new Symbol(sym.Tipo, yychar, yyline, yytext());
          }
        case 59: break;
        case 10: 
          { return new Symbol(sym.NombrePlanetas, yychar, yyline, new String(yytext()));
          }
        case 60: break;
        case 17: 
          { return new Symbol(sym.Turno, yychar, yyline, yytext());
          }
        case 61: break;
        case 26: 
          { return new Symbol(sym.Dificil, yychar, yyline, yytext());
          }
        case 62: break;
        case 39: 
          { return new Symbol(sym.CantidadPlanetas, yychar, yyline, yytext());
          }
        case 63: break;
        case 3: 
          { return new Symbol(sym.Numero, yychar, yyline, new String(yytext()));
          }
        case 64: break;
        case 11: 
          { return new Symbol(sym.Decimal, yychar, yyline, new String(yytext()));
          }
        case 65: break;
        case 24: 
          { return new Symbol(sym.Nombre, yychar, yyline, yytext());
          }
        case 66: break;
        case 29: 
          { return new Symbol(sym.Jugador, yychar, yyline, yytext());
          }
        case 67: break;
        case 25: 
          { return new Symbol(sym.Humano, yychar, yyline, yytext());
          }
        case 68: break;
        case 19: 
          { return new Symbol(sym.Filas, yychar, yyline, yytext());
          }
        case 69: break;
        case 35: 
          { return new Symbol(sym.Produccion, yychar, yyline, yytext());
          }
        case 70: break;
        case 22: 
          { return new Symbol(sym.Ataques, yychar, yyline, yytext());
          }
        case 71: break;
        case 38: 
          { return new Symbol(sym.PlanetaDestino, yychar, yyline, yytext());
          }
        case 72: break;
        case 15: 
          { return new Symbol(sym.Fila, yychar, yyline, yytext());
          }
        case 73: break;
        case 7: 
          { return new Symbol(sym.CorcheteC, yychar, yyline, yytext());
          }
        case 74: break;
        case 18: 
          { return new Symbol(sym.False, yychar, yyline, yytext());
          }
        case 75: break;
        case 40: 
          { return new Symbol(sym.PorcentajeMuertes, yychar, yyline, yytext());
          }
        case 76: break;
        case 1: 
          { /*Ignore*/
          }
        case 77: break;
        case 5: 
          { return new Symbol(sym.Coma, yychar, yyline, yytext());
          }
        case 78: break;
        case 42: 
          { return new Symbol(sym.PlanetasNeutrales, yychar, yyline, yytext());
          }
        case 79: break;
        case 14: 
          { return new Symbol(sym.True, yychar, yyline, yytext());
          }
        case 80: break;
        case 27: 
          { return new Symbol(sym.TamañoS, yychar, yyline, yytext());
          }
        case 81: break;
        case 16: 
          { return new Symbol(sym.NombreJugadores, yychar, yyline, new String(yytext()));
          }
        case 82: break;
        case 12: 
          { return new Symbol(sym.Mapa, yychar, yyline, yytext());
          }
        case 83: break;
        case 41: 
          { return new Symbol(sym.CantidadProducida, yychar, yyline, yytext());
          }
        case 84: break;
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
      System.out.println("Usage : java LexerGuardarPartida <inputfile>");
    }
    else {
      for (int i = 0; i < argv.length; i++) {
        LexerGuardarPartida scanner = null;
        try {
          scanner = new LexerGuardarPartida( new java.io.FileReader(argv[i]) );
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
