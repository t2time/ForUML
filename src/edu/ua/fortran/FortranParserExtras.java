/*
Copyright (c) 2016, Aziz Nanthaamornphong and Anawat Leathongkum

Redistribution and use in source and binary forms, with or without modification, are 
permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of 
conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of 
conditions and the following disclaimer in the documentation and/or other materials 
provided with the distribution.

3. Neither the names of the copyright holders nor the names of its contributors may be 
used to endorse or promote products derived from this software without specific prior 
written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY 
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package edu.ua.fortran;

import edu.ua.util.IActionEnums;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import edu.ua.util.FortranParser;
import edu.ua.util.FortranParserActionFactory;

/**
 * FortranParserExtras.g - this file is needed because adding more rules to
 * FortranParser08 currently will cause javac to fail with a "Code too large"
 * error. Removing some of the rules to an inherited grammar is a workaround to
 * the problem.
 */
@SuppressWarnings({"all", "warnings", "unchecked"})
public class FortranParserExtras extends FortranParser {

  public static final String[] tokenNames = new String[]{
    "<invalid>", "<EOR>", "<DOWN>", "<UP>", "T_NO_LANGUAGE_EXTENSION", "T_EOS", "CONTINUE_CHAR", "SQ_Rep_Char", "DQ_Rep_Char", "T_CHAR_CONSTANT", "Digit_String", "T_DIGIT_STRING", "BINARY_CONSTANT", "OCTAL_CONSTANT", "Digit", "HEX_CONSTANT", "WS", "Letter", "Alphanumeric_Character", "Special_Character", "Rep_Char", "PREPROCESS_LINE", "T_INCLUDE", "T_ASTERISK", "T_COLON", "T_COLON_COLON", "T_COMMA", "T_EQUALS", "T_EQ_EQ", "T_EQ_GT", "T_GREATERTHAN", "T_GREATERTHAN_EQ", "T_LESSTHAN", "T_LESSTHAN_EQ", "T_LBRACKET", "T_LPAREN", "T_MINUS", "T_PERCENT", "T_PLUS", "T_POWER", "T_SLASH", "T_SLASH_EQ", "T_SLASH_SLASH", "T_RBRACKET", "T_RPAREN", "T_UNDERSCORE", "T_AT", "T_EQ", "T_NE", "T_LT", "T_LE", "T_GT", "T_GE", "T_TRUE", "T_FALSE", "T_NOT", "T_AND", "T_OR", "T_EQV", "T_NEQV", "T_PERIOD_EXPONENT", "T_PERIOD", "T_BEGIN_KEYWORDS", "T_INTEGER", "T_REAL", "T_COMPLEX", "T_CHARACTER", "T_LOGICAL", "T_ABSTRACT", "T_ACQUIRED_LOCK", "T_ALL", "T_ALLOCATABLE", "T_ALLOCATE", "T_ASSIGNMENT", "T_ASSIGN", "T_ASSOCIATE", "T_ASYNCHRONOUS", "T_BACKSPACE", "T_BLOCK", "T_BLOCKDATA", "T_CALL", "T_CASE", "T_CLASS", "T_CLOSE", "T_CODIMENSION", "T_COMMON", "T_CONCURRENT", "T_CONTAINS", "T_CONTIGUOUS", "T_CONTINUE", "T_CRITICAL", "T_CYCLE", "T_DATA", "T_DEFAULT", "T_DEALLOCATE", "T_DEFERRED", "T_DO", "T_DOUBLE", "T_DOUBLEPRECISION", "T_DOUBLECOMPLEX", "T_ELEMENTAL", "T_ELSE", "T_ELSEIF", "T_ELSEWHERE", "T_ENTRY", "T_ENUM", "T_ENUMERATOR", "T_ERROR", "T_EQUIVALENCE", "T_EXIT", "T_EXTENDS", "T_EXTERNAL", "T_FILE", "T_FINAL", "T_FLUSH", "T_FORALL", "T_FORMAT", "T_FORMATTED", "T_FUNCTION", "T_GENERIC", "T_GO", "T_GOTO", "T_IF", "T_IMAGES", "T_IMPLICIT", "T_IMPORT", "T_IN", "T_INOUT", "T_INTENT", "T_INTERFACE", "T_INTRINSIC", "T_INQUIRE", "T_LOCK", "T_MEMORY", "T_MODULE", "T_NAMELIST", "T_NONE", "T_NON_INTRINSIC", "T_NON_OVERRIDABLE", "T_NOPASS", "T_NULLIFY", "T_ONLY", "T_OPEN", "T_OPERATOR", "T_OPTIONAL", "T_OUT", "T_PARAMETER", "T_PASS", "T_PAUSE", "T_POINTER", "T_PRINT", "T_PRECISION", "T_PRIVATE", "T_PROCEDURE", "T_PROGRAM", "T_PROTECTED", "T_PUBLIC", "T_PURE", "T_READ", "T_RECURSIVE", "T_RESULT", "T_RETURN", "T_REWIND", "T_SAVE", "T_SELECT", "T_SELECTCASE", "T_SELECTTYPE", "T_SEQUENCE", "T_STOP", "T_SUBMODULE", "T_SUBROUTINE", "T_SYNC", "T_TARGET", "T_THEN", "T_TO", "T_TYPE", "T_UNFORMATTED", "T_UNLOCK", "T_USE", "T_VALUE", "T_VOLATILE", "T_WAIT", "T_WHERE", "T_WHILE", "T_WRITE", "T_WITHTEAM", "T_WITH", "T_TEAM", "T_TOPOLOGY", "T_EVENT", "T_LOCKSET", "T_FINISH", "T_SPAWN", "T_COPOINTER", "T_COTARGET", "T_ENDASSOCIATE", "T_ENDBLOCK", "T_ENDBLOCKDATA", "T_ENDCRITICAL", "T_ENDDO", "T_ENDENUM", "T_ENDFILE", "T_ENDFORALL", "T_ENDFUNCTION", "T_ENDIF", "T_ENDMODULE", "T_ENDINTERFACE", "T_ENDPROCEDURE", "T_ENDPROGRAM", "T_ENDSELECT", "T_ENDSUBMODULE", "T_ENDSUBROUTINE", "T_ENDTYPE", "T_ENDWHERE", "T_END", "T_DIMENSION", "T_KIND", "T_LEN", "T_BIND", "T_END_KEYWORDS", "T_HOLLERITH", "T_DEFINED_OP", "T_LABEL_DO_TERMINAL", "T_DATA_EDIT_DESC", "T_CONTROL_EDIT_DESC", "T_CHAR_STRING_EDIT_DESC", "T_STMT_FUNCTION", "T_ASSIGNMENT_STMT", "T_PTR_ASSIGNMENT_STMT", "T_ARITHMETIC_IF_STMT", "T_ALLOCATE_STMT_1", "T_WHERE_STMT", "T_IF_STMT", "T_FORALL_STMT", "T_WHERE_CONSTRUCT_STMT", "T_FORALL_CONSTRUCT_STMT", "T_INQUIRE_STMT_2", "T_REAL_CONSTANT", "T_INCLUDE_NAME", "T_EOF", "T_IDENT", "T_EDIT_DESC_MISC", "LINE_COMMENT", "MISC_CHAR", "244", "245", "246", "247", "248", "249", "250", "251", "252", "253", "254", "255", "256", "257", "258", "259", "260", "261", "262", "263", "264", "265", "266", "267", "268", "269", "270", "271", "272", "273", "274", "275", "276", "277", "278", "279", "280", "281", "282", "283", "284", "285", "286", "287", "288", "289", "290", "291", "292", "293", "294", "295", "296", "297", "298", "299", "300", "301", "302", "303", "304", "305", "306", "307", "308", "309", "310", "311", "312", "313", "314", "315", "316", "317", "318", "319", "320", "321", "322", "323", "324", "325", "326", "327", "328", "329", "330", "331", "332", "333", "334", "335", "336", "337", "338", "339", "340", "341", "342", "343", "344", "345", "346", "347", "348", "349", "350", "351", "352", "353", "354", "355", "356", "357", "358", "359", "360", "361", "362", "363", "364", "365", "366", "367", "368", "369", "370", "371", "372", "373", "374", "375", "376", "377", "378", "379", "380", "381", "382", "383", "384", "385", "386", "387", "388", "389", "390", "391", "392", "393", "394", "395", "396", "397", "398", "399", "400", "401", "402", "403", "404", "405", "406", "407", "408", "409", "410", "411", "412", "413", "414", "415", "416", "417", "418", "419", "420", "421", "422", "423", "424", "425", "426", "427", "428", "429", "430", "431", "432", "433", "434", "435", "436", "437", "438", "439", "440", "441", "442", "443", "444", "445", "446", "447", "448", "449"
  };
  public static final int EOF = -1;
  public static final int T_CLOSE = 83;
  public static final int T_BLOCK = 78;
  public static final int T_GE = 52;
  public static final int T_CONTAINS = 87;
  public static final int T_ABSTRACT = 68;
  public static final int T_CLASS = 82;
  public static final int T_NOPASS = 139;
  public static final int T_UNFORMATTED = 176;
  public static final int T_LESSTHAN = 32;
  public static final int T_ENDSUBROUTINE = 211;
  public static final int T_INCLUDE_NAME = 238;
  public static final int T_GT = 51;
  public static final int T_IDENT = 240;
  public static final int T_TOPOLOGY = 188;
  public static final int T_CONCURRENT = 86;
  public static final int T_INTERFACE = 129;
  public static final int T_RETURN = 161;
  public static final int T_EOF = 239;
  public static final int T_CALL = 80;
  public static final int T_EOS = 5;
  public static final int T_GO = 120;
  public static final int T_AND = 56;
  public static final int T_PERCENT = 37;
  public static final int T_PRINT = 150;
  public static final int T_ALLOCATE_STMT_1 = 230;
  public static final int T_SUBROUTINE = 170;
  public static final int T_WITH = 186;
  public static final int T_CONTROL_EDIT_DESC = 224;
  public static final int T_ENUMERATOR = 106;
  public static final int Alphanumeric_Character = 18;
  public static final int T_DEFINED_OP = 221;
  public static final int T_WITHTEAM = 185;
  public static final int T_KIND = 216;
  public static final int T_STOP = 168;
  public static final int T_GREATERTHAN_EQ = 31;
  public static final int T_CHAR_STRING_EDIT_DESC = 225;
  public static final int T_ALLOCATABLE = 71;
  public static final int T_ENDINTERFACE = 206;
  public static final int T_END = 214;
  public static final int T_ACQUIRED_LOCK = 69;
  public static final int T_ASTERISK = 23;
  public static final int T_PRIVATE = 152;
  public static final int T_DOUBLEPRECISION = 98;
  public static final int T_ALL = 70;
  public static final int T_CASE = 81;
  public static final int T_IMPLICIT = 124;
  public static final int T_IF = 122;
  public static final int T_THEN = 173;
  public static final int T_DIMENSION = 215;
  public static final int T_GOTO = 121;
  public static final int T_ENDMODULE = 205;
  public static final int T_IN = 126;
  public static final int T_WRITE = 184;
  public static final int T_FORMATTED = 117;
  public static final int WS = 16;
  public static final int T_DATA = 92;
  public static final int T_SUBMODULE = 169;
  public static final int T_FALSE = 54;
  public static final int T_ENDCRITICAL = 198;
  public static final int T_ENDIF = 204;
  public static final int T_WHERE = 182;
  public static final int T_SLASH = 40;
  public static final int SQ_Rep_Char = 7;
  public static final int T_GENERIC = 119;
  public static final int T_RECURSIVE = 159;
  public static final int DQ_Rep_Char = 8;
  public static final int T_ELSEIF = 102;
  public static final int T_BLOCKDATA = 79;
  public static final int OCTAL_CONSTANT = 13;
  public static final int T_SELECTTYPE = 166;
  public static final int T_MINUS = 36;
  public static final int T_SELECT = 164;
  public static final int T_FINISH = 191;
  public static final int T_FINAL = 113;
  public static final int T_UNDERSCORE = 45;
  public static final int T_COPOINTER = 193;
  public static final int T_CODIMENSION = 84;
  public static final int T_IMPORT = 125;
  public static final int T_USE = 178;
  public static final int T_FILE = 112;
  public static final int T_RPAREN = 44;
  public static final int T_INTENT = 128;
  public static final int T_ENDBLOCK = 196;
  public static final int T_ASSIGNMENT_STMT = 227;
  public static final int T_PAUSE = 148;
  public static final int T_ENDFILE = 201;
  public static final int T_BACKSPACE = 77;
  public static final int T_IMAGES = 123;
  public static final int T_EQUALS = 27;
  public static final int T_NON_INTRINSIC = 137;
  public static final int T_SELECTCASE = 165;
  public static final int T_DIGIT_STRING = 11;
  public static final int T_COLON_COLON = 25;
  public static final int T_NON_OVERRIDABLE = 138;
  public static final int Special_Character = 19;
  public static final int T_INCLUDE = 22;
  public static final int T_OPEN = 142;
  public static final int T_POWER = 39;
  public static final int T_ASSOCIATE = 75;
  public static final int T_CHAR_CONSTANT = 9;
  public static final int T_OPERATOR = 143;
  public static final int T_TO = 174;
  public static final int T_EVENT = 189;
  public static final int T_ENDASSOCIATE = 195;
  public static final int T_EQ = 47;
  public static final int T_GREATERTHAN = 30;
  public static final int T_DATA_EDIT_DESC = 223;
  public static final int T_INQUIRE_STMT_2 = 236;
  public static final int T_EQV = 58;
  public static final int HEX_CONSTANT = 15;
  public static final int Digit_String = 10;
  public static final int T_ELEMENTAL = 100;
  public static final int T_CHARACTER = 66;
  public static final int PREPROCESS_LINE = 21;
  public static final int T_NULLIFY = 140;
  public static final int T_REWIND = 162;
  public static final int T_ARITHMETIC_IF_STMT = 229;
  public static final int T_EDIT_DESC_MISC = 241;
  public static final int T_FORALL_CONSTRUCT_STMT = 235;
  public static final int T_BIND = 218;
  public static final int T_ENDFORALL = 202;
  public static final int T_DO = 96;
  public static final int T_WHERE_STMT = 231;
  public static final int T_POINTER = 149;
  public static final int T_PROGRAM = 154;
  public static final int T_ENDTYPE = 212;
  public static final int T_WAIT = 181;
  public static final int T_UNLOCK = 177;
  public static final int T_ELSE = 101;
  public static final int T_IF_STMT = 232;
  public static final int T_SPAWN = 192;
  public static final int T_RBRACKET = 43;
  public static final int T_LPAREN = 35;
  public static final int T_EXTENDS = 110;
  public static final int T_OPTIONAL = 144;
  public static final int T_DOUBLE = 97;
  public static final int T_MODULE = 134;
  public static final int T_READ = 158;
  public static final int T_ALLOCATE = 72;
  public static final int T_INTEGER = 63;
  public static final int T_OR = 57;
  public static final int T_EQUIVALENCE = 108;
  public static final int T_BEGIN_KEYWORDS = 62;
  public static final int T_PERIOD = 61;
  public static final int T_ENTRY = 104;
  public static final int T_COTARGET = 194;
  public static final int T_LABEL_DO_TERMINAL = 222;
  public static final int T_REAL = 64;
  public static final int T_CYCLE = 91;
  public static final int T_PROCEDURE = 153;
  public static final int T_EQ_EQ = 28;
  public static final int T_SLASH_EQ = 41;
  public static final int T_ENDSELECT = 209;
  public static final int T_AT = 46;
  public static final int T_PURE = 157;
  public static final int T_TRUE = 53;
  public static final int T_LOCK = 132;
  public static final int T_NE = 48;
  public static final int T_INTRINSIC = 130;
  public static final int T_PASS = 147;
  public static final int T_REAL_CONSTANT = 237;
  public static final int LINE_COMMENT = 242;
  public static final int T_PERIOD_EXPONENT = 60;
  public static final int T_ENDWHERE = 213;
  public static final int T_ENDSUBMODULE = 210;
  public static final int MISC_CHAR = 243;
  public static final int T_FORMAT = 116;
  public static final int T_DEFAULT = 93;
  public static final int T_TEAM = 187;
  public static final int T_SLASH_SLASH = 42;
  public static final int T_NONE = 136;
  public static final int T_NAMELIST = 135;
  public static final int T_SEQUENCE = 167;
  public static final int T_PRECISION = 151;
  public static final int T_ASYNCHRONOUS = 76;
  public static final int T_LOCKSET = 190;
  public static final int T_CRITICAL = 90;
  public static final int T_COMMA = 26;
  public static final int T_ENDBLOCKDATA = 197;
  public static final int T_RESULT = 160;
  public static final int T_LOGICAL = 67;
  public static final int T_VALUE = 179;
  public static final int Letter = 17;
  public static final int T_FORALL = 115;
  public static final int T_SAVE = 163;
  public static final int T_HOLLERITH = 220;
  public static final int T_FLUSH = 114;
  public static final int T_SYNC = 171;
  public static final int T_WHILE = 183;
  public static final int T_INQUIRE = 131;
  public static final int T_DEFERRED = 95;
  public static final int T_NO_LANGUAGE_EXTENSION = 4;
  public static final int T_FORALL_STMT = 233;
  public static final int T_ASSIGN = 74;
  public static final int T_LBRACKET = 34;
  public static final int T_ERROR = 107;
  public static final int T_EXTERNAL = 111;
  public static final int T_VOLATILE = 180;
  public static final int T_OUT = 145;
  public static final int T_ENDPROCEDURE = 207;
  public static final int CONTINUE_CHAR = 6;
  public static final int T_COLON = 24;
  public static final int T_COMPLEX = 65;
  public static final int T_PLUS = 38;
  public static final int T_STMT_FUNCTION = 226;
  public static final int T_ONLY = 141;
  public static final int T_PROTECTED = 155;
  public static final int T_END_KEYWORDS = 219;
  public static final int T_COMMON = 85;
  public static final int T_INOUT = 127;
  public static final int T_ENDPROGRAM = 208;
  public static final int T_ENDDO = 199;
  public static final int T_NEQV = 59;
  public static final int T_PUBLIC = 156;
  public static final int T_ENDFUNCTION = 203;
  public static final int T_WHERE_CONSTRUCT_STMT = 234;
  public static final int T_CONTIGUOUS = 88;
  public static final int T_ELSEWHERE = 103;
  public static final int T_ENUM = 105;
  public static final int Digit = 14;
  public static final int T_PARAMETER = 146;
  public static final int T_TARGET = 172;
  public static final int T_DOUBLECOMPLEX = 99;
  public static final int T_MEMORY = 133;
  public static final int T_PTR_ASSIGNMENT_STMT = 228;
  public static final int T_TYPE = 175;
  public static final int T_LESSTHAN_EQ = 33;
  public static final int T_LT = 49;
  public static final int T_DEALLOCATE = 94;
  public static final int T_FUNCTION = 118;
  public static final int T_EQ_GT = 29;
  public static final int T_ENDENUM = 200;
  public static final int BINARY_CONSTANT = 12;
  public static final int T_LE = 50;
  public static final int T_LEN = 217;
  public static final int T_CONTINUE = 89;
  public static final int T_NOT = 55;
  public static final int Rep_Char = 20;
  public static final int T_ASSIGNMENT = 73;
  public static final int T_EXIT = 109;
  // delegates
  public FortranParserExtras_FortranParser08 gFortranParser08;

  public FortranParser[] getDelegates() {
    return new FortranParser[]{gFortranParser08};
  }

  // delegators
  public FortranParserExtras(TokenStream input) {
    this(input, new RecognizerSharedState());
  }

  public FortranParserExtras(TokenStream input, RecognizerSharedState state) {
    super(input, state);
    gFortranParser08 = new FortranParserExtras_FortranParser08(input, state, this);
  }

  public String[] getTokenNames() {
    return FortranParserExtras.tokenNames;
  }

  public String getGrammarFileName() {
    return "/Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g";
  }
  int gCount1;
  int gCount2;

  public void initialize(String[] args, String kind, String filename, String path) {
    action = FortranParserActionFactory.newAction(args, this, kind, filename);

    initialize(this, action, filename, path);
    gFortranParser08.initialize(this, action, filename, path);

    action.start_of_file(filename, path);
  }

  public void eofAction() {
    gFortranParser08.eofAction();
  }

  // $ANTLR start "specification_part"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:68:1: specification_part : ( use_stmt )* ( import_stmt )* implicit_part_recursion ( declaration_construct )* ;
  public final void specification_part() throws RecognitionException {
    int numUseStmts = 0;
    int numImportStmts = 0;
    gCount1 = 0;
    gCount2 = 0;
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:70:4: ( ( use_stmt )* ( import_stmt )* implicit_part_recursion ( declaration_construct )* )
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:70:8: ( use_stmt )* ( import_stmt )* implicit_part_recursion ( declaration_construct )*
      {
        // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:70:8: ( use_stmt )*
        loop1:
        do {
          int alt1 = 2;
          int LA1_0 = input.LA(1);

          if ((LA1_0 == T_DIGIT_STRING)) {
            int LA1_1 = input.LA(2);

            if ((LA1_1 == T_USE)) {
              alt1 = 1;
            }


          } else if ((LA1_0 == T_USE)) {
            alt1 = 1;
          }


          switch (alt1) {
            case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:70:10: use_stmt
            {
              pushFollow(FOLLOW_use_stmt_in_specification_part95);
              use_stmt();

              state._fsp--;
              if (state.failed) {
                return;
              }

              if (state.backtracking == 0) {
                numUseStmts++;
              }

            }
            break;

            default:
              break loop1;
          }
        } while (true);


        // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:71:8: ( import_stmt )*
        loop2:
        do {
          int alt2 = 2;
          int LA2_0 = input.LA(1);

          if ((LA2_0 == T_DIGIT_STRING)) {
            int LA2_1 = input.LA(2);

            if ((LA2_1 == T_IMPORT)) {
              alt2 = 1;
            }


          } else if ((LA2_0 == T_IMPORT)) {
            alt2 = 1;
          }


          switch (alt2) {
            case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:71:10: import_stmt
            {
              pushFollow(FOLLOW_import_stmt_in_specification_part111);
              import_stmt();

              state._fsp--;
              if (state.failed) {
                return;
              }

              if (state.backtracking == 0) {
                numImportStmts++;
              }

            }
            break;

            default:
              break loop2;
          }
        } while (true);


        pushFollow(FOLLOW_implicit_part_recursion_in_specification_part125);
        implicit_part_recursion();

        state._fsp--;
        if (state.failed) {
          return;
        }

        // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:73:8: ( declaration_construct )*
        loop3:
        do {
          int alt3 = 2;
          int LA3_0 = input.LA(1);

          if ((LA3_0 == T_DIGIT_STRING)) {
            int LA3_1 = input.LA(2);

            if (((LA3_1 >= T_INTEGER && LA3_1 <= T_ABSTRACT) || LA3_1 == T_ALLOCATABLE || LA3_1 == T_ASYNCHRONOUS || LA3_1 == T_CLASS || (LA3_1 >= T_CODIMENSION && LA3_1 <= T_COMMON) || LA3_1 == T_DATA || (LA3_1 >= T_DOUBLE && LA3_1 <= T_DOUBLECOMPLEX) || (LA3_1 >= T_ENTRY && LA3_1 <= T_ENUM) || LA3_1 == T_EQUIVALENCE || LA3_1 == T_EXTERNAL || LA3_1 == T_FORMAT || (LA3_1 >= T_INTENT && LA3_1 <= T_INTRINSIC) || LA3_1 == T_NAMELIST || LA3_1 == T_OPTIONAL || LA3_1 == T_PARAMETER || LA3_1 == T_POINTER || (LA3_1 >= T_PRIVATE && LA3_1 <= T_PROCEDURE) || (LA3_1 >= T_PROTECTED && LA3_1 <= T_PUBLIC) || LA3_1 == T_SAVE || LA3_1 == T_TARGET || LA3_1 == T_TYPE || (LA3_1 >= T_VALUE && LA3_1 <= T_VOLATILE) || LA3_1 == T_DIMENSION || LA3_1 == T_BIND || LA3_1 == T_STMT_FUNCTION)) {
              alt3 = 1;
            }


          } else if (((LA3_0 >= T_INTEGER && LA3_0 <= T_ABSTRACT) || LA3_0 == T_ALLOCATABLE || LA3_0 == T_ASYNCHRONOUS || LA3_0 == T_CLASS || (LA3_0 >= T_CODIMENSION && LA3_0 <= T_COMMON) || LA3_0 == T_DATA || (LA3_0 >= T_DOUBLE && LA3_0 <= T_DOUBLECOMPLEX) || (LA3_0 >= T_ENTRY && LA3_0 <= T_ENUM) || LA3_0 == T_EQUIVALENCE || LA3_0 == T_EXTERNAL || LA3_0 == T_FORMAT || (LA3_0 >= T_INTENT && LA3_0 <= T_INTRINSIC) || LA3_0 == T_NAMELIST || LA3_0 == T_OPTIONAL || LA3_0 == T_PARAMETER || LA3_0 == T_POINTER || (LA3_0 >= T_PRIVATE && LA3_0 <= T_PROCEDURE) || (LA3_0 >= T_PROTECTED && LA3_0 <= T_PUBLIC) || LA3_0 == T_SAVE || LA3_0 == T_TARGET || LA3_0 == T_TYPE || (LA3_0 >= T_VALUE && LA3_0 <= T_VOLATILE) || LA3_0 == T_DIMENSION || LA3_0 == T_BIND || LA3_0 == T_STMT_FUNCTION)) {
            alt3 = 1;
          }


          switch (alt3) {
            case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:73:10: declaration_construct
            {
              pushFollow(FOLLOW_declaration_construct_in_specification_part137);
              declaration_construct();

              state._fsp--;
              if (state.failed) {
                return;
              }

              if (state.backtracking == 0) {
                gCount2++;
              }

            }
            break;

            default:
              break loop3;
          }
        } while (true);


        if (state.backtracking == 0) {
          action.specification_part(numUseStmts, numImportStmts, gCount1, gCount2);
        }

      }

    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "specification_part"

  // $ANTLR start "implicit_part_recursion"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:93:1: implicit_part_recursion : ( ( ( label )? T_IMPLICIT )=> implicit_stmt implicit_part_recursion | ( ( label )? T_PARAMETER )=> parameter_stmt implicit_part_recursion | ( ( label )? T_FORMAT )=> format_stmt implicit_part_recursion | ( ( label )? T_ENTRY )=> entry_stmt implicit_part_recursion |);
  public final void implicit_part_recursion() throws RecognitionException {
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:94:4: ( ( ( label )? T_IMPLICIT )=> implicit_stmt implicit_part_recursion | ( ( label )? T_PARAMETER )=> parameter_stmt implicit_part_recursion | ( ( label )? T_FORMAT )=> format_stmt implicit_part_recursion | ( ( label )? T_ENTRY )=> entry_stmt implicit_part_recursion |)
      int alt4 = 5;
      alt4 = dfa4.predict(input);
      switch (alt4) {
        case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:94:8: ( ( label )? T_IMPLICIT )=> implicit_stmt implicit_part_recursion
        {
          pushFollow(FOLLOW_implicit_stmt_in_implicit_part_recursion194);
          implicit_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

          if (state.backtracking == 0) {
            gCount1++;
          }

          pushFollow(FOLLOW_implicit_part_recursion_in_implicit_part_recursion199);
          implicit_part_recursion();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 2: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:95:8: ( ( label )? T_PARAMETER )=> parameter_stmt implicit_part_recursion
        {
          pushFollow(FOLLOW_parameter_stmt_in_implicit_part_recursion219);
          parameter_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

          if (state.backtracking == 0) {
            gCount2++;
          }

          pushFollow(FOLLOW_implicit_part_recursion_in_implicit_part_recursion223);
          implicit_part_recursion();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 3: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:96:8: ( ( label )? T_FORMAT )=> format_stmt implicit_part_recursion
        {
          pushFollow(FOLLOW_format_stmt_in_implicit_part_recursion246);
          format_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

          if (state.backtracking == 0) {
            gCount2++;
          }

          pushFollow(FOLLOW_implicit_part_recursion_in_implicit_part_recursion253);
          implicit_part_recursion();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 4: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:97:8: ( ( label )? T_ENTRY )=> entry_stmt implicit_part_recursion
        {
          pushFollow(FOLLOW_entry_stmt_in_implicit_part_recursion277);
          entry_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

          if (state.backtracking == 0) {
            gCount2++;
          }

          pushFollow(FOLLOW_implicit_part_recursion_in_implicit_part_recursion285);
          implicit_part_recursion();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 5: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:99:4: 
        {
        }
        break;

      }
    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "implicit_part_recursion"

  // $ANTLR start "executable_construct"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:118:1: executable_construct : ( action_stmt | associate_construct | block_construct | case_construct | critical_construct | do_construct | forall_construct | if_construct | select_type_construct | where_construct );
  public final void executable_construct() throws RecognitionException {
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:120:4: ( action_stmt | associate_construct | block_construct | case_construct | critical_construct | do_construct | forall_construct | if_construct | select_type_construct | where_construct )
      int alt5 = 10;
      switch (input.LA(1)) {
        case T_DIGIT_STRING: {
          switch (input.LA(2)) {
            case T_ALLOCATE:
            case T_ASSIGN:
            case T_BACKSPACE:
            case T_CALL:
            case T_CLOSE:
            case T_CONTINUE:
            case T_CYCLE:
            case T_DEALLOCATE:
            case T_ERROR:
            case T_EXIT:
            case T_FLUSH:
            case T_GO:
            case T_GOTO:
            case T_INQUIRE:
            case T_LOCK:
            case T_NULLIFY:
            case T_OPEN:
            case T_PAUSE:
            case T_PRINT:
            case T_READ:
            case T_RETURN:
            case T_REWIND:
            case T_STOP:
            case T_SYNC:
            case T_UNLOCK:
            case T_WAIT:
            case T_WRITE:
            case T_ENDFILE:
            case T_END:
            case T_ASSIGNMENT_STMT:
            case T_PTR_ASSIGNMENT_STMT:
            case T_ARITHMETIC_IF_STMT:
            case T_ALLOCATE_STMT_1:
            case T_WHERE_STMT:
            case T_IF_STMT:
            case T_FORALL_STMT:
            case T_INQUIRE_STMT_2: {
              alt5 = 1;
            }
            break;
            case T_IDENT: {
              int LA5_14 = input.LA(3);

              if ((LA5_14 == T_COLON)) {
                switch (input.LA(4)) {
                  case T_ASSOCIATE: {
                    alt5 = 2;
                  }
                  break;
                  case T_BLOCK: {
                    alt5 = 3;
                  }
                  break;
                  case T_SELECT: {
                    int LA5_6 = input.LA(5);

                    if ((LA5_6 == T_CASE)) {
                      alt5 = 4;
                    } else if ((LA5_6 == T_TYPE)) {
                      alt5 = 9;
                    } else {
                      if (state.backtracking > 0) {
                        state.failed = true;
                        return;
                      }
                      NoViableAltException nvae =
                              new NoViableAltException("", 5, 6, input);

                      throw nvae;

                    }
                  }
                  break;
                  case T_SELECTCASE: {
                    alt5 = 4;
                  }
                  break;
                  case T_CRITICAL: {
                    alt5 = 5;
                  }
                  break;
                  case T_DO: {
                    alt5 = 6;
                  }
                  break;
                  case T_FORALL_CONSTRUCT_STMT: {
                    alt5 = 7;
                  }
                  break;
                  case T_IF: {
                    alt5 = 8;
                  }
                  break;
                  case T_SELECTTYPE: {
                    alt5 = 9;
                  }
                  break;
                  default:
                    if (state.backtracking > 0) {
                      state.failed = true;
                      return;
                    }
                    NoViableAltException nvae =
                            new NoViableAltException("", 5, 16, input);

                    throw nvae;

                }

              } else {
                if (state.backtracking > 0) {
                  state.failed = true;
                  return;
                }
                NoViableAltException nvae =
                        new NoViableAltException("", 5, 14, input);

                throw nvae;

              }
            }
            break;
            case T_ASSOCIATE: {
              alt5 = 2;
            }
            break;
            case T_BLOCK: {
              alt5 = 3;
            }
            break;
            case T_SELECT: {
              int LA5_6 = input.LA(3);

              if ((LA5_6 == T_CASE)) {
                alt5 = 4;
              } else if ((LA5_6 == T_TYPE)) {
                alt5 = 9;
              } else {
                if (state.backtracking > 0) {
                  state.failed = true;
                  return;
                }
                NoViableAltException nvae =
                        new NoViableAltException("", 5, 6, input);

                throw nvae;

              }
            }
            break;
            case T_SELECTCASE: {
              alt5 = 4;
            }
            break;
            case T_CRITICAL: {
              alt5 = 5;
            }
            break;
            case T_DO: {
              alt5 = 6;
            }
            break;
            case T_FORALL_CONSTRUCT_STMT: {
              alt5 = 7;
            }
            break;
            case T_IF: {
              alt5 = 8;
            }
            break;
            case T_SELECTTYPE: {
              alt5 = 9;
            }
            break;
            default:
              if (state.backtracking > 0) {
                state.failed = true;
                return;
              }
              NoViableAltException nvae =
                      new NoViableAltException("", 5, 1, input);

              throw nvae;

          }

        }
        break;
        case T_ALLOCATE:
        case T_ASSIGN:
        case T_BACKSPACE:
        case T_CALL:
        case T_CLOSE:
        case T_CONTINUE:
        case T_CYCLE:
        case T_DEALLOCATE:
        case T_ERROR:
        case T_EXIT:
        case T_FLUSH:
        case T_GO:
        case T_GOTO:
        case T_INQUIRE:
        case T_LOCK:
        case T_NULLIFY:
        case T_OPEN:
        case T_PAUSE:
        case T_PRINT:
        case T_READ:
        case T_RETURN:
        case T_REWIND:
        case T_STOP:
        case T_SYNC:
        case T_UNLOCK:
        case T_WAIT:
        case T_WRITE:
        case T_ENDFILE:
        case T_END:
        case T_ASSIGNMENT_STMT:
        case T_PTR_ASSIGNMENT_STMT:
        case T_ARITHMETIC_IF_STMT:
        case T_ALLOCATE_STMT_1:
        case T_WHERE_STMT:
        case T_IF_STMT:
        case T_FORALL_STMT:
        case T_INQUIRE_STMT_2: {
          alt5 = 1;
        }
        break;
        case T_IDENT: {
          int LA5_3 = input.LA(2);

          if ((LA5_3 == T_COLON)) {
            switch (input.LA(3)) {
              case T_ASSOCIATE: {
                alt5 = 2;
              }
              break;
              case T_BLOCK: {
                alt5 = 3;
              }
              break;
              case T_SELECT: {
                int LA5_6 = input.LA(4);

                if ((LA5_6 == T_CASE)) {
                  alt5 = 4;
                } else if ((LA5_6 == T_TYPE)) {
                  alt5 = 9;
                } else {
                  if (state.backtracking > 0) {
                    state.failed = true;
                    return;
                  }
                  NoViableAltException nvae =
                          new NoViableAltException("", 5, 6, input);

                  throw nvae;

                }
              }
              break;
              case T_SELECTCASE: {
                alt5 = 4;
              }
              break;
              case T_CRITICAL: {
                alt5 = 5;
              }
              break;
              case T_DO: {
                alt5 = 6;
              }
              break;
              case T_FORALL_CONSTRUCT_STMT: {
                alt5 = 7;
              }
              break;
              case T_IF: {
                alt5 = 8;
              }
              break;
              case T_SELECTTYPE: {
                alt5 = 9;
              }
              break;
              case T_WHERE_CONSTRUCT_STMT: {
                alt5 = 10;
              }
              break;
              default:
                if (state.backtracking > 0) {
                  state.failed = true;
                  return;
                }
                NoViableAltException nvae =
                        new NoViableAltException("", 5, 15, input);

                throw nvae;

            }

          } else {
            if (state.backtracking > 0) {
              state.failed = true;
              return;
            }
            NoViableAltException nvae =
                    new NoViableAltException("", 5, 3, input);

            throw nvae;

          }
        }
        break;
        case T_ASSOCIATE: {
          alt5 = 2;
        }
        break;
        case T_BLOCK: {
          alt5 = 3;
        }
        break;
        case T_SELECT: {
          int LA5_6 = input.LA(2);

          if ((LA5_6 == T_CASE)) {
            alt5 = 4;
          } else if ((LA5_6 == T_TYPE)) {
            alt5 = 9;
          } else {
            if (state.backtracking > 0) {
              state.failed = true;
              return;
            }
            NoViableAltException nvae =
                    new NoViableAltException("", 5, 6, input);

            throw nvae;

          }
        }
        break;
        case T_SELECTCASE: {
          alt5 = 4;
        }
        break;
        case T_CRITICAL: {
          alt5 = 5;
        }
        break;
        case T_DO: {
          alt5 = 6;
        }
        break;
        case T_FORALL_CONSTRUCT_STMT: {
          alt5 = 7;
        }
        break;
        case T_IF: {
          alt5 = 8;
        }
        break;
        case T_SELECTTYPE: {
          alt5 = 9;
        }
        break;
        case T_WHERE_CONSTRUCT_STMT: {
          alt5 = 10;
        }
        break;
        default:
          if (state.backtracking > 0) {
            state.failed = true;
            return;
          }
          NoViableAltException nvae =
                  new NoViableAltException("", 5, 0, input);

          throw nvae;

      }

      switch (alt5) {
        case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:120:8: action_stmt
        {
          pushFollow(FOLLOW_action_stmt_in_executable_construct321);
          action_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 2: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:121:8: associate_construct
        {
          pushFollow(FOLLOW_associate_construct_in_executable_construct330);
          associate_construct();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 3: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:122:8: block_construct
        {
          pushFollow(FOLLOW_block_construct_in_executable_construct339);
          block_construct();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 4: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:123:8: case_construct
        {
          pushFollow(FOLLOW_case_construct_in_executable_construct365);
          case_construct();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 5: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:124:8: critical_construct
        {
          pushFollow(FOLLOW_critical_construct_in_executable_construct374);
          critical_construct();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 6: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:125:8: do_construct
        {
          pushFollow(FOLLOW_do_construct_in_executable_construct397);
          do_construct();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 7: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:126:8: forall_construct
        {
          pushFollow(FOLLOW_forall_construct_in_executable_construct406);
          forall_construct();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 8: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:127:8: if_construct
        {
          pushFollow(FOLLOW_if_construct_in_executable_construct415);
          if_construct();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 9: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:128:8: select_type_construct
        {
          pushFollow(FOLLOW_select_type_construct_in_executable_construct424);
          select_type_construct();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 10: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:129:8: where_construct
        {
          pushFollow(FOLLOW_where_construct_in_executable_construct433);
          where_construct();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;

      }
      if (state.backtracking == 0) {
        action.executable_construct();
      }
    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "executable_construct"

  // $ANTLR start "action_stmt"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:186:1: action_stmt : ( allocate_stmt | assignment_stmt | backspace_stmt | call_stmt | close_stmt | continue_stmt | cycle_stmt | deallocate_stmt | endfile_stmt | errorstop_stmt | exit_stmt | flush_stmt | forall_stmt | goto_stmt | if_stmt | inquire_stmt | lock_stmt | nullify_stmt | open_stmt | pointer_assignment_stmt | print_stmt | read_stmt | return_stmt | rewind_stmt | stop_stmt | sync_all_stmt | sync_images_stmt | sync_memory_stmt | unlock_stmt | wait_stmt | where_stmt | write_stmt | arithmetic_if_stmt | computed_goto_stmt | assign_stmt | assigned_goto_stmt | pause_stmt );
  public final void action_stmt() throws RecognitionException {
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:194:4: ( allocate_stmt | assignment_stmt | backspace_stmt | call_stmt | close_stmt | continue_stmt | cycle_stmt | deallocate_stmt | endfile_stmt | errorstop_stmt | exit_stmt | flush_stmt | forall_stmt | goto_stmt | if_stmt | inquire_stmt | lock_stmt | nullify_stmt | open_stmt | pointer_assignment_stmt | print_stmt | read_stmt | return_stmt | rewind_stmt | stop_stmt | sync_all_stmt | sync_images_stmt | sync_memory_stmt | unlock_stmt | wait_stmt | where_stmt | write_stmt | arithmetic_if_stmt | computed_goto_stmt | assign_stmt | assigned_goto_stmt | pause_stmt )
      int alt6 = 37;
      switch (input.LA(1)) {
        case T_DIGIT_STRING: {
          switch (input.LA(2)) {
            case T_ALLOCATE:
            case T_ALLOCATE_STMT_1: {
              alt6 = 1;
            }
            break;
            case T_ASSIGNMENT_STMT: {
              alt6 = 2;
            }
            break;
            case T_BACKSPACE: {
              alt6 = 3;
            }
            break;
            case T_CALL: {
              alt6 = 4;
            }
            break;
            case T_CLOSE: {
              alt6 = 5;
            }
            break;
            case T_CONTINUE: {
              alt6 = 6;
            }
            break;
            case T_CYCLE: {
              alt6 = 7;
            }
            break;
            case T_DEALLOCATE: {
              alt6 = 8;
            }
            break;
            case T_ENDFILE:
            case T_END: {
              alt6 = 9;
            }
            break;
            case T_ERROR: {
              alt6 = 10;
            }
            break;
            case T_EXIT: {
              alt6 = 11;
            }
            break;
            case T_FLUSH: {
              alt6 = 12;
            }
            break;
            case T_FORALL_STMT: {
              alt6 = 13;
            }
            break;
            case T_GO: {
              int LA6_15 = input.LA(3);

              if ((LA6_15 == T_TO)) {
                switch (input.LA(4)) {
                  case T_DIGIT_STRING: {
                    alt6 = 14;
                  }
                  break;
                  case T_LPAREN: {
                    alt6 = 34;
                  }
                  break;
                  case T_IDENT: {
                    alt6 = 36;
                  }
                  break;
                  default:
                    if (state.backtracking > 0) {
                      state.failed = true;
                      return;
                    }
                    NoViableAltException nvae =
                            new NoViableAltException("", 6, 36, input);

                    throw nvae;

                }

              } else {
                if (state.backtracking > 0) {
                  state.failed = true;
                  return;
                }
                NoViableAltException nvae =
                        new NoViableAltException("", 6, 15, input);

                throw nvae;

              }
            }
            break;
            case T_GOTO: {
              switch (input.LA(3)) {
                case T_DIGIT_STRING: {
                  alt6 = 14;
                }
                break;
                case T_LPAREN: {
                  alt6 = 34;
                }
                break;
                case T_IDENT: {
                  alt6 = 36;
                }
                break;
                default:
                  if (state.backtracking > 0) {
                    state.failed = true;
                    return;
                  }
                  NoViableAltException nvae =
                          new NoViableAltException("", 6, 16, input);

                  throw nvae;

              }

            }
            break;
            case T_IF_STMT: {
              alt6 = 15;
            }
            break;
            case T_INQUIRE:
            case T_INQUIRE_STMT_2: {
              alt6 = 16;
            }
            break;
            case T_LOCK: {
              alt6 = 17;
            }
            break;
            case T_NULLIFY: {
              alt6 = 18;
            }
            break;
            case T_OPEN: {
              alt6 = 19;
            }
            break;
            case T_PTR_ASSIGNMENT_STMT: {
              alt6 = 20;
            }
            break;
            case T_PRINT: {
              alt6 = 21;
            }
            break;
            case T_READ: {
              alt6 = 22;
            }
            break;
            case T_RETURN: {
              alt6 = 23;
            }
            break;
            case T_REWIND: {
              alt6 = 24;
            }
            break;
            case T_STOP: {
              alt6 = 25;
            }
            break;
            case T_SYNC: {
              switch (input.LA(3)) {
                case T_ALL: {
                  alt6 = 26;
                }
                break;
                case T_IMAGES: {
                  alt6 = 27;
                }
                break;
                case T_MEMORY: {
                  alt6 = 28;
                }
                break;
                default:
                  if (state.backtracking > 0) {
                    state.failed = true;
                    return;
                  }
                  NoViableAltException nvae =
                          new NoViableAltException("", 6, 28, input);

                  throw nvae;

              }

            }
            break;
            case T_UNLOCK: {
              alt6 = 29;
            }
            break;
            case T_WAIT: {
              alt6 = 30;
            }
            break;
            case T_WHERE_STMT: {
              alt6 = 31;
            }
            break;
            case T_WRITE: {
              alt6 = 32;
            }
            break;
            case T_ARITHMETIC_IF_STMT: {
              alt6 = 33;
            }
            break;
            case T_ASSIGN: {
              alt6 = 35;
            }
            break;
            case T_PAUSE: {
              alt6 = 37;
            }
            break;
            default:
              if (state.backtracking > 0) {
                state.failed = true;
                return;
              }
              NoViableAltException nvae =
                      new NoViableAltException("", 6, 1, input);

              throw nvae;

          }

        }
        break;
        case T_ALLOCATE:
        case T_ALLOCATE_STMT_1: {
          alt6 = 1;
        }
        break;
        case T_ASSIGNMENT_STMT: {
          alt6 = 2;
        }
        break;
        case T_BACKSPACE: {
          alt6 = 3;
        }
        break;
        case T_CALL: {
          alt6 = 4;
        }
        break;
        case T_CLOSE: {
          alt6 = 5;
        }
        break;
        case T_CONTINUE: {
          alt6 = 6;
        }
        break;
        case T_CYCLE: {
          alt6 = 7;
        }
        break;
        case T_DEALLOCATE: {
          alt6 = 8;
        }
        break;
        case T_ENDFILE:
        case T_END: {
          alt6 = 9;
        }
        break;
        case T_ERROR: {
          alt6 = 10;
        }
        break;
        case T_EXIT: {
          alt6 = 11;
        }
        break;
        case T_FLUSH: {
          alt6 = 12;
        }
        break;
        case T_FORALL_STMT: {
          alt6 = 13;
        }
        break;
        case T_GO: {
          int LA6_15 = input.LA(2);

          if ((LA6_15 == T_TO)) {
            switch (input.LA(3)) {
              case T_DIGIT_STRING: {
                alt6 = 14;
              }
              break;
              case T_LPAREN: {
                alt6 = 34;
              }
              break;
              case T_IDENT: {
                alt6 = 36;
              }
              break;
              default:
                if (state.backtracking > 0) {
                  state.failed = true;
                  return;
                }
                NoViableAltException nvae =
                        new NoViableAltException("", 6, 36, input);

                throw nvae;

            }

          } else {
            if (state.backtracking > 0) {
              state.failed = true;
              return;
            }
            NoViableAltException nvae =
                    new NoViableAltException("", 6, 15, input);

            throw nvae;

          }
        }
        break;
        case T_GOTO: {
          switch (input.LA(2)) {
            case T_DIGIT_STRING: {
              alt6 = 14;
            }
            break;
            case T_LPAREN: {
              alt6 = 34;
            }
            break;
            case T_IDENT: {
              alt6 = 36;
            }
            break;
            default:
              if (state.backtracking > 0) {
                state.failed = true;
                return;
              }
              NoViableAltException nvae =
                      new NoViableAltException("", 6, 16, input);

              throw nvae;

          }

        }
        break;
        case T_IF_STMT: {
          alt6 = 15;
        }
        break;
        case T_INQUIRE:
        case T_INQUIRE_STMT_2: {
          alt6 = 16;
        }
        break;
        case T_LOCK: {
          alt6 = 17;
        }
        break;
        case T_NULLIFY: {
          alt6 = 18;
        }
        break;
        case T_OPEN: {
          alt6 = 19;
        }
        break;
        case T_PTR_ASSIGNMENT_STMT: {
          alt6 = 20;
        }
        break;
        case T_PRINT: {
          alt6 = 21;
        }
        break;
        case T_READ: {
          alt6 = 22;
        }
        break;
        case T_RETURN: {
          alt6 = 23;
        }
        break;
        case T_REWIND: {
          alt6 = 24;
        }
        break;
        case T_STOP: {
          alt6 = 25;
        }
        break;
        case T_SYNC: {
          switch (input.LA(2)) {
            case T_ALL: {
              alt6 = 26;
            }
            break;
            case T_IMAGES: {
              alt6 = 27;
            }
            break;
            case T_MEMORY: {
              alt6 = 28;
            }
            break;
            default:
              if (state.backtracking > 0) {
                state.failed = true;
                return;
              }
              NoViableAltException nvae =
                      new NoViableAltException("", 6, 28, input);

              throw nvae;

          }

        }
        break;
        case T_UNLOCK: {
          alt6 = 29;
        }
        break;
        case T_WAIT: {
          alt6 = 30;
        }
        break;
        case T_WHERE_STMT: {
          alt6 = 31;
        }
        break;
        case T_WRITE: {
          alt6 = 32;
        }
        break;
        case T_ARITHMETIC_IF_STMT: {
          alt6 = 33;
        }
        break;
        case T_ASSIGN: {
          alt6 = 35;
        }
        break;
        case T_PAUSE: {
          alt6 = 37;
        }
        break;
        default:
          if (state.backtracking > 0) {
            state.failed = true;
            return;
          }
          NoViableAltException nvae =
                  new NoViableAltException("", 6, 0, input);

          throw nvae;

      }

      switch (alt6) {
        case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:194:8: allocate_stmt
        {
          pushFollow(FOLLOW_allocate_stmt_in_action_stmt476);
          allocate_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 2: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:195:8: assignment_stmt
        {
          pushFollow(FOLLOW_assignment_stmt_in_action_stmt485);
          assignment_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 3: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:196:8: backspace_stmt
        {
          pushFollow(FOLLOW_backspace_stmt_in_action_stmt494);
          backspace_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 4: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:197:8: call_stmt
        {
          pushFollow(FOLLOW_call_stmt_in_action_stmt503);
          call_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 5: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:198:8: close_stmt
        {
          pushFollow(FOLLOW_close_stmt_in_action_stmt512);
          close_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 6: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:199:8: continue_stmt
        {
          pushFollow(FOLLOW_continue_stmt_in_action_stmt521);
          continue_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 7: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:200:8: cycle_stmt
        {
          pushFollow(FOLLOW_cycle_stmt_in_action_stmt530);
          cycle_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 8: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:201:8: deallocate_stmt
        {
          pushFollow(FOLLOW_deallocate_stmt_in_action_stmt539);
          deallocate_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 9: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:209:8: endfile_stmt
        {
          pushFollow(FOLLOW_endfile_stmt_in_action_stmt555);
          endfile_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 10: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:210:8: errorstop_stmt
        {
          pushFollow(FOLLOW_errorstop_stmt_in_action_stmt564);
          errorstop_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 11: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:211:8: exit_stmt
        {
          pushFollow(FOLLOW_exit_stmt_in_action_stmt589);
          exit_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 12: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:212:8: flush_stmt
        {
          pushFollow(FOLLOW_flush_stmt_in_action_stmt598);
          flush_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 13: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:213:8: forall_stmt
        {
          pushFollow(FOLLOW_forall_stmt_in_action_stmt607);
          forall_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 14: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:214:8: goto_stmt
        {
          pushFollow(FOLLOW_goto_stmt_in_action_stmt616);
          goto_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 15: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:215:8: if_stmt
        {
          pushFollow(FOLLOW_if_stmt_in_action_stmt625);
          if_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 16: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:216:8: inquire_stmt
        {
          pushFollow(FOLLOW_inquire_stmt_in_action_stmt634);
          inquire_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 17: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:217:8: lock_stmt
        {
          pushFollow(FOLLOW_lock_stmt_in_action_stmt645);
          lock_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 18: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:218:8: nullify_stmt
        {
          pushFollow(FOLLOW_nullify_stmt_in_action_stmt675);
          nullify_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 19: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:219:8: open_stmt
        {
          pushFollow(FOLLOW_open_stmt_in_action_stmt684);
          open_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 20: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:220:8: pointer_assignment_stmt
        {
          pushFollow(FOLLOW_pointer_assignment_stmt_in_action_stmt693);
          pointer_assignment_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 21: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:221:8: print_stmt
        {
          pushFollow(FOLLOW_print_stmt_in_action_stmt702);
          print_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 22: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:222:8: read_stmt
        {
          pushFollow(FOLLOW_read_stmt_in_action_stmt711);
          read_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 23: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:223:8: return_stmt
        {
          pushFollow(FOLLOW_return_stmt_in_action_stmt720);
          return_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 24: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:224:8: rewind_stmt
        {
          pushFollow(FOLLOW_rewind_stmt_in_action_stmt729);
          rewind_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 25: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:225:8: stop_stmt
        {
          pushFollow(FOLLOW_stop_stmt_in_action_stmt738);
          stop_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 26: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:226:8: sync_all_stmt
        {
          pushFollow(FOLLOW_sync_all_stmt_in_action_stmt747);
          sync_all_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 27: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:227:8: sync_images_stmt
        {
          pushFollow(FOLLOW_sync_images_stmt_in_action_stmt773);
          sync_images_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 28: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:228:8: sync_memory_stmt
        {
          pushFollow(FOLLOW_sync_memory_stmt_in_action_stmt796);
          sync_memory_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 29: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:229:8: unlock_stmt
        {
          pushFollow(FOLLOW_unlock_stmt_in_action_stmt819);
          unlock_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 30: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:230:8: wait_stmt
        {
          pushFollow(FOLLOW_wait_stmt_in_action_stmt847);
          wait_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 31: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:231:8: where_stmt
        {
          pushFollow(FOLLOW_where_stmt_in_action_stmt856);
          where_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 32: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:232:8: write_stmt
        {
          pushFollow(FOLLOW_write_stmt_in_action_stmt865);
          write_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 33: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:233:8: arithmetic_if_stmt
        {
          pushFollow(FOLLOW_arithmetic_if_stmt_in_action_stmt874);
          arithmetic_if_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 34: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:234:8: computed_goto_stmt
        {
          pushFollow(FOLLOW_computed_goto_stmt_in_action_stmt883);
          computed_goto_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 35: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:235:8: assign_stmt
        {
          pushFollow(FOLLOW_assign_stmt_in_action_stmt892);
          assign_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 36: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:236:8: assigned_goto_stmt
        {
          pushFollow(FOLLOW_assigned_goto_stmt_in_action_stmt920);
          assigned_goto_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;
        case 37: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:237:8: pause_stmt
        {
          pushFollow(FOLLOW_pause_stmt_in_action_stmt941);
          pause_stmt();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;

      }
      if (state.backtracking == 0) {
        action.action_stmt();
      }
    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "action_stmt"

  // $ANTLR start "type_declaration_stmt"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:256:1: type_declaration_stmt : ( label )? declaration_type_spec ( ( T_COMMA attr_spec )* T_COLON_COLON )? entity_decl_list end_of_stmt ;
  public final void type_declaration_stmt() throws RecognitionException {
    Token label1 = null;

    Token end_of_stmt2 = null;


    Token lbl = null;
    int numAttrSpecs = 0;
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:259:5: ( ( label )? declaration_type_spec ( ( T_COMMA attr_spec )* T_COLON_COLON )? entity_decl_list end_of_stmt )
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:259:7: ( label )? declaration_type_spec ( ( T_COMMA attr_spec )* T_COLON_COLON )? entity_decl_list end_of_stmt
      {
        // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:259:7: ( label )?
        int alt7 = 2;
        int LA7_0 = input.LA(1);

        if ((LA7_0 == T_DIGIT_STRING)) {
          alt7 = 1;
        }
        switch (alt7) {
          case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:259:8: label
          {
            pushFollow(FOLLOW_label_in_type_declaration_stmt1000);
            label1 = label();

            state._fsp--;
            if (state.failed) {
              return;
            }

            if (state.backtracking == 0) {
              lbl = label1;
            }

          }
          break;

        }


        pushFollow(FOLLOW_declaration_type_spec_in_type_declaration_stmt1006);
        declaration_type_spec();

        state._fsp--;
        if (state.failed) {
          return;
        }

        // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:260:3: ( ( T_COMMA attr_spec )* T_COLON_COLON )?
        int alt9 = 2;
        int LA9_0 = input.LA(1);

        if (((LA9_0 >= T_COLON_COLON && LA9_0 <= T_COMMA))) {
          alt9 = 1;
        }
        switch (alt9) {
          case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:260:5: ( T_COMMA attr_spec )* T_COLON_COLON
          {
            // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:260:5: ( T_COMMA attr_spec )*
            loop8:
            do {
              int alt8 = 2;
              int LA8_0 = input.LA(1);

              if ((LA8_0 == T_COMMA)) {
                alt8 = 1;
              }


              switch (alt8) {
                case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:260:6: T_COMMA attr_spec
                {
                  match(input, T_COMMA, FOLLOW_T_COMMA_in_type_declaration_stmt1013);
                  if (state.failed) {
                    return;
                  }

                  pushFollow(FOLLOW_attr_spec_in_type_declaration_stmt1015);
                  attr_spec();

                  state._fsp--;
                  if (state.failed) {
                    return;
                  }

                  if (state.backtracking == 0) {
                    numAttrSpecs += 1;
                  }

                }
                break;

                default:
                  break loop8;
              }
            } while (true);


            match(input, T_COLON_COLON, FOLLOW_T_COLON_COLON_in_type_declaration_stmt1021);
            if (state.failed) {
              return;
            }

          }
          break;

        }


        pushFollow(FOLLOW_entity_decl_list_in_type_declaration_stmt1028);
        entity_decl_list();

        state._fsp--;
        if (state.failed) {
          return;
        }

        pushFollow(FOLLOW_end_of_stmt_in_type_declaration_stmt1030);
        end_of_stmt2 = end_of_stmt();

        state._fsp--;
        if (state.failed) {
          return;
        }

        if (state.backtracking == 0) {
          action.type_declaration_stmt(lbl, numAttrSpecs, end_of_stmt2);
        }

      }

      if (state.backtracking == 0) {
        checkForInclude();
      }
    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "type_declaration_stmt"

  // $ANTLR start "part_ref"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:308:1: part_ref options {k=2; } : ( ( T_IDENT T_LPAREN )=> T_IDENT T_LPAREN section_subscript_list T_RPAREN ( image_selector )? | ( T_IDENT T_LBRACKET )=> T_IDENT image_selector | T_IDENT );
  public final void part_ref() throws RecognitionException {
    Token T_IDENT3 = null;
    Token T_IDENT4 = null;
    Token T_IDENT5 = null;

    boolean hasSSL = false;
    boolean hasImageSelector = false;
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:311:4: ( ( T_IDENT T_LPAREN )=> T_IDENT T_LPAREN section_subscript_list T_RPAREN ( image_selector )? | ( T_IDENT T_LBRACKET )=> T_IDENT image_selector | T_IDENT )
      int alt11 = 3;
      int LA11_0 = input.LA(1);

      if ((LA11_0 == T_IDENT)) {
        int LA11_1 = input.LA(2);

        if ((LA11_1 == T_LPAREN)) {
          int LA11_2 = input.LA(3);

          if ((synpred5_FortranParserExtras())) {
            alt11 = 1;
          } else if ((true)) {
            alt11 = 3;
          } else {
            if (state.backtracking > 0) {
              state.failed = true;
              return;
            }
            NoViableAltException nvae =
                    new NoViableAltException("", 11, 2, input);

            throw nvae;

          }
        } else if ((LA11_1 == T_LBRACKET) && (synpred6_FortranParserExtras())) {
          alt11 = 2;
        } else if ((LA11_1 == EOF || LA11_1 == T_EOS || (LA11_1 >= T_ASTERISK && LA11_1 <= T_LESSTHAN_EQ) || (LA11_1 >= T_MINUS && LA11_1 <= T_RPAREN) || (LA11_1 >= T_EQ && LA11_1 <= T_GE) || (LA11_1 >= T_AND && LA11_1 <= T_NEQV) || LA11_1 == T_DEFINED_OP)) {
          alt11 = 3;
        } else {
          if (state.backtracking > 0) {
            state.failed = true;
            return;
          }
          NoViableAltException nvae =
                  new NoViableAltException("", 11, 1, input);

          throw nvae;

        }
      } else {
        if (state.backtracking > 0) {
          state.failed = true;
          return;
        }
        NoViableAltException nvae =
                new NoViableAltException("", 11, 0, input);

        throw nvae;

      }
      switch (alt11) {
        case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:311:8: ( T_IDENT T_LPAREN )=> T_IDENT T_LPAREN section_subscript_list T_RPAREN ( image_selector )?
        {
          T_IDENT3 = (Token) match(input, T_IDENT, FOLLOW_T_IDENT_in_part_ref1125);
          if (state.failed) {
            return;
          }

          match(input, T_LPAREN, FOLLOW_T_LPAREN_in_part_ref1127);
          if (state.failed) {
            return;
          }

          pushFollow(FOLLOW_section_subscript_list_in_part_ref1129);
          section_subscript_list();

          state._fsp--;
          if (state.failed) {
            return;
          }

          match(input, T_RPAREN, FOLLOW_T_RPAREN_in_part_ref1131);
          if (state.failed) {
            return;
          }

          // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:312:30: ( image_selector )?
          int alt10 = 2;
          int LA10_0 = input.LA(1);

          if ((LA10_0 == T_LBRACKET)) {
            alt10 = 1;
          }
          switch (alt10) {
            case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:312:31: image_selector
            {
              pushFollow(FOLLOW_image_selector_in_part_ref1163);
              image_selector();

              state._fsp--;
              if (state.failed) {
                return;
              }

              if (state.backtracking == 0) {
                hasImageSelector = true;
              }

            }
            break;

          }


          if (state.backtracking == 0) {
            hasSSL = true;
            action.part_ref(T_IDENT3, hasSSL, hasImageSelector);
          }

        }
        break;
        case 2: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:314:8: ( T_IDENT T_LBRACKET )=> T_IDENT image_selector
        {
          T_IDENT4 = (Token) match(input, T_IDENT, FOLLOW_T_IDENT_in_part_ref1197);
          if (state.failed) {
            return;
          }

          pushFollow(FOLLOW_image_selector_in_part_ref1199);
          image_selector();

          state._fsp--;
          if (state.failed) {
            return;
          }

          if (state.backtracking == 0) {
            hasImageSelector = true;
            action.part_ref(T_IDENT4, hasSSL, hasImageSelector);
          }

        }
        break;
        case 3: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:316:8: T_IDENT
        {
          T_IDENT5 = (Token) match(input, T_IDENT, FOLLOW_T_IDENT_in_part_ref1221);
          if (state.failed) {
            return;
          }

          if (state.backtracking == 0) {
            action.part_ref(T_IDENT5, hasSSL, hasImageSelector);
          }

        }
        break;

      }
    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "part_ref"

  // $ANTLR start "part_ref_no_image_selector"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:320:1: part_ref_no_image_selector options {k=2; } : ( ( T_IDENT T_LPAREN )=> T_IDENT T_LPAREN section_subscript_list T_RPAREN | T_IDENT );
  public final void part_ref_no_image_selector() throws RecognitionException {
    Token T_IDENT6 = null;
    Token T_IDENT7 = null;

    boolean hasSSL = false;
    boolean hasImageSelector = false;
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:323:4: ( ( T_IDENT T_LPAREN )=> T_IDENT T_LPAREN section_subscript_list T_RPAREN | T_IDENT )
      int alt12 = 2;
      int LA12_0 = input.LA(1);

      if ((LA12_0 == T_IDENT)) {
        int LA12_1 = input.LA(2);

        if ((LA12_1 == T_LPAREN) && (synpred7_FortranParserExtras())) {
          alt12 = 1;
        } else if ((LA12_1 == EOF || LA12_1 == T_COMMA || LA12_1 == T_LBRACKET || LA12_1 == T_PERCENT || LA12_1 == T_RPAREN)) {
          alt12 = 2;
        } else {
          if (state.backtracking > 0) {
            state.failed = true;
            return;
          }
          NoViableAltException nvae =
                  new NoViableAltException("", 12, 1, input);

          throw nvae;

        }
      } else {
        if (state.backtracking > 0) {
          state.failed = true;
          return;
        }
        NoViableAltException nvae =
                new NoViableAltException("", 12, 0, input);

        throw nvae;

      }
      switch (alt12) {
        case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:323:8: ( T_IDENT T_LPAREN )=> T_IDENT T_LPAREN section_subscript_list T_RPAREN
        {
          T_IDENT6 = (Token) match(input, T_IDENT, FOLLOW_T_IDENT_in_part_ref_no_image_selector1270);
          if (state.failed) {
            return;
          }

          match(input, T_LPAREN, FOLLOW_T_LPAREN_in_part_ref_no_image_selector1272);
          if (state.failed) {
            return;
          }

          pushFollow(FOLLOW_section_subscript_list_in_part_ref_no_image_selector1274);
          section_subscript_list();

          state._fsp--;
          if (state.failed) {
            return;
          }

          match(input, T_RPAREN, FOLLOW_T_RPAREN_in_part_ref_no_image_selector1276);
          if (state.failed) {
            return;
          }

          if (state.backtracking == 0) {
            hasSSL = true;
            action.part_ref(T_IDENT6, hasSSL, hasImageSelector);
          }

        }
        break;
        case 2: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:325:8: T_IDENT
        {
          T_IDENT7 = (Token) match(input, T_IDENT, FOLLOW_T_IDENT_in_part_ref_no_image_selector1298);
          if (state.failed) {
            return;
          }

          if (state.backtracking == 0) {
            action.part_ref(T_IDENT7, hasSSL, hasImageSelector);
          }

        }
        break;

      }
    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "part_ref_no_image_selector"

  // $ANTLR start "section_subscript"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:343:1: section_subscript returns [boolean isEmpty] : ( expr section_subscript_ambiguous | T_COLON ( expr )? ( T_COLON expr )? | T_COLON_COLON expr | T_IDENT T_EQUALS expr | T_IDENT T_EQUALS T_ASTERISK label | T_ASTERISK label |);
  public final boolean section_subscript() throws RecognitionException {
    boolean isEmpty = false;


    Token T_IDENT8 = null;
    Token T_IDENT10 = null;
    Token label9 = null;

    Token label11 = null;



    boolean hasLowerBounds = false;
    boolean hasUpperBounds = false;
    boolean hasStride = false;
    boolean hasExpr = false;

    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:350:4: ( expr section_subscript_ambiguous | T_COLON ( expr )? ( T_COLON expr )? | T_COLON_COLON expr | T_IDENT T_EQUALS expr | T_IDENT T_EQUALS T_ASTERISK label | T_ASTERISK label |)
      int alt15 = 7;
      switch (input.LA(1)) {
        case T_CHAR_CONSTANT:
        case T_DIGIT_STRING:
        case BINARY_CONSTANT:
        case OCTAL_CONSTANT:
        case HEX_CONSTANT:
        case T_LBRACKET:
        case T_LPAREN:
        case T_MINUS:
        case T_PLUS:
        case T_TRUE:
        case T_FALSE:
        case T_NOT:
        case T_HOLLERITH:
        case T_DEFINED_OP:
        case T_REAL_CONSTANT: {
          alt15 = 1;
        }
        break;
        case T_IDENT: {
          int LA15_2 = input.LA(2);

          if ((LA15_2 == T_CHAR_CONSTANT || (LA15_2 >= T_ASTERISK && LA15_2 <= T_COMMA) || LA15_2 == T_EQ_EQ || (LA15_2 >= T_GREATERTHAN && LA15_2 <= T_SLASH_SLASH) || LA15_2 == T_RPAREN || (LA15_2 >= T_EQ && LA15_2 <= T_GE) || (LA15_2 >= T_AND && LA15_2 <= T_NEQV) || LA15_2 == T_DEFINED_OP)) {
            alt15 = 1;
          } else if ((LA15_2 == T_EQUALS)) {
            int LA15_7 = input.LA(3);

            if ((LA15_7 == T_ASTERISK)) {
              alt15 = 5;
            } else if ((LA15_7 == T_CHAR_CONSTANT || (LA15_7 >= T_DIGIT_STRING && LA15_7 <= OCTAL_CONSTANT) || LA15_7 == HEX_CONSTANT || (LA15_7 >= T_LBRACKET && LA15_7 <= T_MINUS) || LA15_7 == T_PLUS || (LA15_7 >= T_TRUE && LA15_7 <= T_NOT) || (LA15_7 >= T_HOLLERITH && LA15_7 <= T_DEFINED_OP) || LA15_7 == T_REAL_CONSTANT || LA15_7 == T_IDENT)) {
              alt15 = 4;
            } else {
              if (state.backtracking > 0) {
                state.failed = true;
                return isEmpty;
              }
              NoViableAltException nvae =
                      new NoViableAltException("", 15, 7, input);

              throw nvae;

            }
          } else {
            if (state.backtracking > 0) {
              state.failed = true;
              return isEmpty;
            }
            NoViableAltException nvae =
                    new NoViableAltException("", 15, 2, input);

            throw nvae;

          }
        }
        break;
        case T_COLON: {
          alt15 = 2;
        }
        break;
        case T_COLON_COLON: {
          alt15 = 3;
        }
        break;
        case T_ASTERISK: {
          alt15 = 6;
        }
        break;
        case T_COMMA:
        case T_RPAREN: {
          alt15 = 7;
        }
        break;
        default:
          if (state.backtracking > 0) {
            state.failed = true;
            return isEmpty;
          }
          NoViableAltException nvae =
                  new NoViableAltException("", 15, 0, input);

          throw nvae;

      }

      switch (alt15) {
        case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:350:8: expr section_subscript_ambiguous
        {
          pushFollow(FOLLOW_expr_in_section_subscript1347);
          expr();

          state._fsp--;
          if (state.failed) {
            return isEmpty;
          }

          pushFollow(FOLLOW_section_subscript_ambiguous_in_section_subscript1349);
          section_subscript_ambiguous();

          state._fsp--;
          if (state.failed) {
            return isEmpty;
          }

        }
        break;
        case 2: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:351:8: T_COLON ( expr )? ( T_COLON expr )?
        {
          match(input, T_COLON, FOLLOW_T_COLON_in_section_subscript1358);
          if (state.failed) {
            return isEmpty;
          }

          // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:351:16: ( expr )?
          int alt13 = 2;
          int LA13_0 = input.LA(1);

          if ((LA13_0 == T_CHAR_CONSTANT || (LA13_0 >= T_DIGIT_STRING && LA13_0 <= OCTAL_CONSTANT) || LA13_0 == HEX_CONSTANT || (LA13_0 >= T_LBRACKET && LA13_0 <= T_MINUS) || LA13_0 == T_PLUS || (LA13_0 >= T_TRUE && LA13_0 <= T_NOT) || (LA13_0 >= T_HOLLERITH && LA13_0 <= T_DEFINED_OP) || LA13_0 == T_REAL_CONSTANT || LA13_0 == T_IDENT)) {
            alt13 = 1;
          }
          switch (alt13) {
            case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:351:17: expr
            {
              pushFollow(FOLLOW_expr_in_section_subscript1361);
              expr();

              state._fsp--;
              if (state.failed) {
                return isEmpty;
              }

              if (state.backtracking == 0) {
                hasUpperBounds = true;
              }

            }
            break;

          }


          // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:351:47: ( T_COLON expr )?
          int alt14 = 2;
          int LA14_0 = input.LA(1);

          if ((LA14_0 == T_COLON)) {
            alt14 = 1;
          }
          switch (alt14) {
            case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:351:48: T_COLON expr
            {
              match(input, T_COLON, FOLLOW_T_COLON_in_section_subscript1368);
              if (state.failed) {
                return isEmpty;
              }

              pushFollow(FOLLOW_expr_in_section_subscript1370);
              expr();

              state._fsp--;
              if (state.failed) {
                return isEmpty;
              }

              if (state.backtracking == 0) {
                hasStride = true;
              }

            }
            break;

          }


          if (state.backtracking == 0) {
            action.section_subscript(hasLowerBounds, hasUpperBounds, hasStride, false);
          }

        }
        break;
        case 3: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:353:8: T_COLON_COLON expr
        {
          match(input, T_COLON_COLON, FOLLOW_T_COLON_COLON_in_section_subscript1396);
          if (state.failed) {
            return isEmpty;
          }

          pushFollow(FOLLOW_expr_in_section_subscript1398);
          expr();

          state._fsp--;
          if (state.failed) {
            return isEmpty;
          }

          if (state.backtracking == 0) {
            hasStride = true;
            action.section_subscript(hasLowerBounds, hasUpperBounds, hasStride, false);
          }

        }
        break;
        case 4: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:356:8: T_IDENT T_EQUALS expr
        {
          T_IDENT8 = (Token) match(input, T_IDENT, FOLLOW_T_IDENT_in_section_subscript1420);
          if (state.failed) {
            return isEmpty;
          }

          match(input, T_EQUALS, FOLLOW_T_EQUALS_in_section_subscript1422);
          if (state.failed) {
            return isEmpty;
          }

          pushFollow(FOLLOW_expr_in_section_subscript1424);
          expr();

          state._fsp--;
          if (state.failed) {
            return isEmpty;
          }

          if (state.backtracking == 0) {
            hasExpr = true;
            action.actual_arg(hasExpr, null);
            action.actual_arg_spec(T_IDENT8);
          }

        }
        break;
        case 5: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:359:8: T_IDENT T_EQUALS T_ASTERISK label
        {
          T_IDENT10 = (Token) match(input, T_IDENT, FOLLOW_T_IDENT_in_section_subscript1447);
          if (state.failed) {
            return isEmpty;
          }

          match(input, T_EQUALS, FOLLOW_T_EQUALS_in_section_subscript1449);
          if (state.failed) {
            return isEmpty;
          }

          match(input, T_ASTERISK, FOLLOW_T_ASTERISK_in_section_subscript1451);
          if (state.failed) {
            return isEmpty;
          }

          pushFollow(FOLLOW_label_in_section_subscript1453);
          label9 = label();

          state._fsp--;
          if (state.failed) {
            return isEmpty;
          }

          if (state.backtracking == 0) {
            action.actual_arg(hasExpr, label9);
            action.actual_arg_spec(T_IDENT10);
          }

        }
        break;
        case 6: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:361:8: T_ASTERISK label
        {
          match(input, T_ASTERISK, FOLLOW_T_ASTERISK_in_section_subscript1476);
          if (state.failed) {
            return isEmpty;
          }

          pushFollow(FOLLOW_label_in_section_subscript1478);
          label11 = label();

          state._fsp--;
          if (state.failed) {
            return isEmpty;
          }

          if (state.backtracking == 0) {
            action.actual_arg(hasExpr, label11);
            action.actual_arg_spec(null);
          }

        }
        break;
        case 7: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:363:12: 
        {
          if (state.backtracking == 0) {
            isEmpty = true; /* empty could be an actual-arg, see R1220 */ }

        }
        break;

      }
    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return isEmpty;
  }
  // $ANTLR end "section_subscript"

  // $ANTLR start "section_subscript_ambiguous"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:366:1: section_subscript_ambiguous : ( T_COLON ( expr )? ( T_COLON expr )? | T_COLON_COLON expr |);
  public final void section_subscript_ambiguous() throws RecognitionException {

    boolean hasLowerBound = true;
    boolean hasUpperBound = false;
    boolean hasStride = false;
    boolean isAmbiguous = false;

    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:373:4: ( T_COLON ( expr )? ( T_COLON expr )? | T_COLON_COLON expr |)
      int alt18 = 3;
      switch (input.LA(1)) {
        case T_COLON: {
          alt18 = 1;
        }
        break;
        case T_COLON_COLON: {
          alt18 = 2;
        }
        break;
        case T_COMMA:
        case T_RPAREN: {
          alt18 = 3;
        }
        break;
        default:
          if (state.backtracking > 0) {
            state.failed = true;
            return;
          }
          NoViableAltException nvae =
                  new NoViableAltException("", 18, 0, input);

          throw nvae;

      }

      switch (alt18) {
        case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:373:8: T_COLON ( expr )? ( T_COLON expr )?
        {
          match(input, T_COLON, FOLLOW_T_COLON_in_section_subscript_ambiguous1528);
          if (state.failed) {
            return;
          }

          // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:373:16: ( expr )?
          int alt16 = 2;
          int LA16_0 = input.LA(1);

          if ((LA16_0 == T_CHAR_CONSTANT || (LA16_0 >= T_DIGIT_STRING && LA16_0 <= OCTAL_CONSTANT) || LA16_0 == HEX_CONSTANT || (LA16_0 >= T_LBRACKET && LA16_0 <= T_MINUS) || LA16_0 == T_PLUS || (LA16_0 >= T_TRUE && LA16_0 <= T_NOT) || (LA16_0 >= T_HOLLERITH && LA16_0 <= T_DEFINED_OP) || LA16_0 == T_REAL_CONSTANT || LA16_0 == T_IDENT)) {
            alt16 = 1;
          }
          switch (alt16) {
            case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:373:17: expr
            {
              pushFollow(FOLLOW_expr_in_section_subscript_ambiguous1531);
              expr();

              state._fsp--;
              if (state.failed) {
                return;
              }

              if (state.backtracking == 0) {
                hasUpperBound = true;
              }

            }
            break;

          }


          // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:373:46: ( T_COLON expr )?
          int alt17 = 2;
          int LA17_0 = input.LA(1);

          if ((LA17_0 == T_COLON)) {
            alt17 = 1;
          }
          switch (alt17) {
            case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:373:47: T_COLON expr
            {
              match(input, T_COLON, FOLLOW_T_COLON_in_section_subscript_ambiguous1538);
              if (state.failed) {
                return;
              }

              pushFollow(FOLLOW_expr_in_section_subscript_ambiguous1540);
              expr();

              state._fsp--;
              if (state.failed) {
                return;
              }

              if (state.backtracking == 0) {
                hasStride = true;
              }

            }
            break;

          }


          if (state.backtracking == 0) {
            action.section_subscript(hasLowerBound, hasUpperBound, hasStride, isAmbiguous);
          }

        }
        break;
        case 2: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:380:7: T_COLON_COLON expr
        {
          match(input, T_COLON_COLON, FOLLOW_T_COLON_COLON_in_section_subscript_ambiguous1605);
          if (state.failed) {
            return;
          }

          pushFollow(FOLLOW_expr_in_section_subscript_ambiguous1607);
          expr();

          state._fsp--;
          if (state.failed) {
            return;
          }

          if (state.backtracking == 0) {
            hasStride = true;
            action.section_subscript(hasLowerBound, hasUpperBound, hasStride, isAmbiguous);
          }

        }
        break;
        case 3: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:383:12: 
        {
          if (state.backtracking == 0) { /* empty, could be an actual-arg, see R1220 */
            isAmbiguous = true;
            action.section_subscript(hasLowerBound, hasUpperBound, hasStride, isAmbiguous);
          }

        }
        break;

      }
    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "section_subscript_ambiguous"

  // $ANTLR start "section_subscript_list"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:403:1: section_subscript_list :isEmpty= section_subscript ( T_COMMA section_subscript )* ;
  public final void section_subscript_list() throws RecognitionException {
    boolean isEmpty = false;


    int count = 0;
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:405:4: (isEmpty= section_subscript ( T_COMMA section_subscript )* )
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:405:12: isEmpty= section_subscript ( T_COMMA section_subscript )*
      {
        if (state.backtracking == 0) {
          action.section_subscript_list__begin();
        }

        pushFollow(FOLLOW_section_subscript_in_section_subscript_list1679);
        isEmpty = section_subscript();

        state._fsp--;
        if (state.failed) {
          return;
        }

        if (state.backtracking == 0) {
          if (isEmpty == false) {
            count += 1;
          }
        }

        // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:410:8: ( T_COMMA section_subscript )*
        loop19:
        do {
          int alt19 = 2;
          int LA19_0 = input.LA(1);

          if ((LA19_0 == T_COMMA)) {
            alt19 = 1;
          }


          switch (alt19) {
            case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:410:9: T_COMMA section_subscript
            {
              match(input, T_COMMA, FOLLOW_T_COMMA_in_section_subscript_list1702);
              if (state.failed) {
                return;
              }

              pushFollow(FOLLOW_section_subscript_in_section_subscript_list1704);
              section_subscript();

              state._fsp--;
              if (state.failed) {
                return;
              }

              if (state.backtracking == 0) {
                count += 1;
              }

            }
            break;

            default:
              break loop19;
          }
        } while (true);


        if (state.backtracking == 0) {
          action.section_subscript_list(count);
        }

      }

    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "section_subscript_list"

  // $ANTLR start "image_selector"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:423:1: image_selector : T_LBRACKET cosubscript_list T_RBRACKET ;
  public final void image_selector() throws RecognitionException {
    Token T_LBRACKET12 = null;
    Token T_RBRACKET13 = null;

    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:424:4: ( T_LBRACKET cosubscript_list T_RBRACKET )
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:424:8: T_LBRACKET cosubscript_list T_RBRACKET
      {
        T_LBRACKET12 = (Token) match(input, T_LBRACKET, FOLLOW_T_LBRACKET_in_image_selector1745);
        if (state.failed) {
          return;
        }

        pushFollow(FOLLOW_cosubscript_list_in_image_selector1747);
        cosubscript_list();

        state._fsp--;
        if (state.failed) {
          return;
        }

        T_RBRACKET13 = (Token) match(input, T_RBRACKET, FOLLOW_T_RBRACKET_in_image_selector1749);
        if (state.failed) {
          return;
        }

        if (state.backtracking == 0) {
          action.image_selector(T_LBRACKET12, T_RBRACKET13);
        }

      }

    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "image_selector"

  // $ANTLR start "cosubscript"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:436:1: cosubscript : scalar_int_expr ;
  public final void cosubscript() throws RecognitionException {
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:437:4: ( scalar_int_expr )
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:437:8: scalar_int_expr
      {
        pushFollow(FOLLOW_scalar_int_expr_in_cosubscript1785);
        scalar_int_expr();

        state._fsp--;
        if (state.failed) {
          return;
        }

      }

    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "cosubscript"

  // $ANTLR start "cosubscript_list"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:440:1: cosubscript_list : cosubscript ( T_COMMA cosubscript )* ;
  public final void cosubscript_list() throws RecognitionException {
    int count = 0;
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:442:4: ( cosubscript ( T_COMMA cosubscript )* )
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:442:12: cosubscript ( T_COMMA cosubscript )*
      {
        if (state.backtracking == 0) {
          action.cosubscript_list__begin();
        }

        pushFollow(FOLLOW_cosubscript_in_cosubscript_list1819);
        cosubscript();

        state._fsp--;
        if (state.failed) {
          return;
        }

        if (state.backtracking == 0) {
          count++;
        }

        // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:443:31: ( T_COMMA cosubscript )*
        loop20:
        do {
          int alt20 = 2;
          int LA20_0 = input.LA(1);

          if ((LA20_0 == T_COMMA)) {
            alt20 = 1;
          }


          switch (alt20) {
            case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:443:33: T_COMMA cosubscript
            {
              match(input, T_COMMA, FOLLOW_T_COMMA_in_cosubscript_list1825);
              if (state.failed) {
                return;
              }

              pushFollow(FOLLOW_cosubscript_in_cosubscript_list1827);
              cosubscript();

              state._fsp--;
              if (state.failed) {
                return;
              }

              if (state.backtracking == 0) {
                count++;
              }

            }
            break;

            default:
              break loop20;
          }
        } while (true);


        if (state.backtracking == 0) {
          action.cosubscript_list(count, null);
        }

      }

    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "cosubscript_list"

  // $ANTLR start "allocation"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:458:1: allocation : ( ( allocate_object T_LBRACKET )=> allocate_object T_LBRACKET allocate_coarray_spec T_RBRACKET | ( allocate_object )=> allocate_object );
  public final void allocation() throws RecognitionException {
    boolean hasAllocateShapeSpecList = false;
    boolean hasAllocateCoarraySpec = false;
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:460:4: ( ( allocate_object T_LBRACKET )=> allocate_object T_LBRACKET allocate_coarray_spec T_RBRACKET | ( allocate_object )=> allocate_object )
      int alt21 = 2;
      int LA21_0 = input.LA(1);

      if ((LA21_0 == T_IDENT)) {
        int LA21_1 = input.LA(2);

        if ((synpred8_FortranParserExtras())) {
          alt21 = 1;
        } else if ((synpred9_FortranParserExtras())) {
          alt21 = 2;
        } else {
          if (state.backtracking > 0) {
            state.failed = true;
            return;
          }
          NoViableAltException nvae =
                  new NoViableAltException("", 21, 1, input);

          throw nvae;

        }
      } else {
        if (state.backtracking > 0) {
          state.failed = true;
          return;
        }
        NoViableAltException nvae =
                new NoViableAltException("", 21, 0, input);

        throw nvae;

      }
      switch (alt21) {
        case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:460:8: ( allocate_object T_LBRACKET )=> allocate_object T_LBRACKET allocate_coarray_spec T_RBRACKET
        {
          pushFollow(FOLLOW_allocate_object_in_allocation1892);
          allocate_object();

          state._fsp--;
          if (state.failed) {
            return;
          }

          match(input, T_LBRACKET, FOLLOW_T_LBRACKET_in_allocation1894);
          if (state.failed) {
            return;
          }

          pushFollow(FOLLOW_allocate_coarray_spec_in_allocation1896);
          allocate_coarray_spec();

          state._fsp--;
          if (state.failed) {
            return;
          }

          match(input, T_RBRACKET, FOLLOW_T_RBRACKET_in_allocation1898);
          if (state.failed) {
            return;
          }

          if (state.backtracking == 0) {
            hasAllocateCoarraySpec = true;
          }

          if (state.backtracking == 0) {
            action.allocation(hasAllocateShapeSpecList, hasAllocateCoarraySpec);
          }

        }
        break;
        case 2: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:473:8: ( allocate_object )=> allocate_object
        {
          pushFollow(FOLLOW_allocate_object_in_allocation1970);
          allocate_object();

          state._fsp--;
          if (state.failed) {
            return;
          }

          if (state.backtracking == 0) {
            action.allocation(hasAllocateShapeSpecList, hasAllocateCoarraySpec);
          }

        }
        break;

      }
    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "allocation"

  // $ANTLR start "allocate_object"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:495:1: allocate_object : part_ref_no_image_selector ( T_PERCENT part_ref_no_image_selector )* ;
  public final void allocate_object() throws RecognitionException {
    int numPartRefs = 0;
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:497:4: ( part_ref_no_image_selector ( T_PERCENT part_ref_no_image_selector )* )
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:497:8: part_ref_no_image_selector ( T_PERCENT part_ref_no_image_selector )*
      {
        pushFollow(FOLLOW_part_ref_no_image_selector_in_allocate_object2024);
        part_ref_no_image_selector();

        state._fsp--;
        if (state.failed) {
          return;
        }

        if (state.backtracking == 0) {
          numPartRefs += 1;
        }

        // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:498:8: ( T_PERCENT part_ref_no_image_selector )*
        loop22:
        do {
          int alt22 = 2;
          int LA22_0 = input.LA(1);

          if ((LA22_0 == T_PERCENT)) {
            alt22 = 1;
          }


          switch (alt22) {
            case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:498:9: T_PERCENT part_ref_no_image_selector
            {
              match(input, T_PERCENT, FOLLOW_T_PERCENT_in_allocate_object2036);
              if (state.failed) {
                return;
              }

              pushFollow(FOLLOW_part_ref_no_image_selector_in_allocate_object2038);
              part_ref_no_image_selector();

              state._fsp--;
              if (state.failed) {
                return;
              }

              if (state.backtracking == 0) {
                numPartRefs += 1;
              }

            }
            break;

            default:
              break loop22;
          }
        } while (true);


        if (state.backtracking == 0) {
          action.data_ref(numPartRefs);
          action.allocate_object();
        }

      }

    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "allocate_object"

  // $ANTLR start "allocate_coarray_spec"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:510:1: allocate_coarray_spec options {k=3; } : ( ( T_ASTERISK )=> T_ASTERISK | ( expr T_COLON T_ASTERISK )=> expr T_COLON T_ASTERISK );
  public final void allocate_coarray_spec() throws RecognitionException {
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:513:4: ( ( T_ASTERISK )=> T_ASTERISK | ( expr T_COLON T_ASTERISK )=> expr T_COLON T_ASTERISK )
      int alt23 = 2;
      int LA23_0 = input.LA(1);

      if ((LA23_0 == T_ASTERISK) && (synpred10_FortranParserExtras())) {
        alt23 = 1;
      } else if ((LA23_0 == T_NOT) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == T_PLUS) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == T_MINUS) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == T_DEFINED_OP) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == T_IDENT) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == T_DIGIT_STRING) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == T_CHAR_CONSTANT) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == T_REAL_CONSTANT) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == T_LPAREN) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == T_TRUE) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == T_FALSE) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == BINARY_CONSTANT) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == OCTAL_CONSTANT) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == HEX_CONSTANT) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == T_HOLLERITH) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else if ((LA23_0 == T_LBRACKET) && (synpred11_FortranParserExtras())) {
        alt23 = 2;
      } else {
        if (state.backtracking > 0) {
          state.failed = true;
          return;
        }
        NoViableAltException nvae =
                new NoViableAltException("", 23, 0, input);

        throw nvae;

      }
      switch (alt23) {
        case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:513:8: ( T_ASTERISK )=> T_ASTERISK
        {
          match(input, T_ASTERISK, FOLLOW_T_ASTERISK_in_allocate_coarray_spec2109);
          if (state.failed) {
            return;
          }

        }
        break;
        case 2: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:514:8: ( expr T_COLON T_ASTERISK )=> expr T_COLON T_ASTERISK
        {
          pushFollow(FOLLOW_expr_in_allocate_coarray_spec2128);
          expr();

          state._fsp--;
          if (state.failed) {
            return;
          }

          match(input, T_COLON, FOLLOW_T_COLON_in_allocate_coarray_spec2130);
          if (state.failed) {
            return;
          }

          match(input, T_ASTERISK, FOLLOW_T_ASTERISK_in_allocate_coarray_spec2132);
          if (state.failed) {
            return;
          }

        }
        break;

      }
      if (state.backtracking == 0) {
        action.allocate_coarray_spec();
      }
    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "allocate_coarray_spec"

  // $ANTLR start "logical_expr"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:532:1: logical_expr : expr ;
  public final void logical_expr() throws RecognitionException {
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:533:4: ( expr )
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:533:8: expr
      {
        pushFollow(FOLLOW_expr_in_logical_expr2161);
        expr();

        state._fsp--;
        if (state.failed) {
          return;
        }

      }

    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "logical_expr"

  // $ANTLR start "scalar_logical_expr"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:536:1: scalar_logical_expr : expr ;
  public final void scalar_logical_expr() throws RecognitionException {
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:537:4: ( expr )
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:537:8: expr
      {
        pushFollow(FOLLOW_expr_in_scalar_logical_expr2178);
        expr();

        state._fsp--;
        if (state.failed) {
          return;
        }

      }

    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "scalar_logical_expr"

  // $ANTLR start "int_expr"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:549:1: int_expr : expr ;
  public final void int_expr() throws RecognitionException {
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:550:4: ( expr )
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:550:8: expr
      {
        pushFollow(FOLLOW_expr_in_int_expr2202);
        expr();

        state._fsp--;
        if (state.failed) {
          return;
        }

      }

    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "int_expr"

  // $ANTLR start "scalar_int_expr"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:553:1: scalar_int_expr : expr ;
  public final void scalar_int_expr() throws RecognitionException {
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:554:4: ( expr )
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:554:8: expr
      {
        pushFollow(FOLLOW_expr_in_scalar_int_expr2219);
        expr();

        state._fsp--;
        if (state.failed) {
          return;
        }

      }

    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "scalar_int_expr"

  // $ANTLR start "scalar_variable"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:562:1: scalar_variable : expr ;
  public final void scalar_variable() throws RecognitionException {
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:563:4: ( expr )
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:563:8: expr
      {
        pushFollow(FOLLOW_expr_in_scalar_variable2241);
        expr();

        state._fsp--;
        if (state.failed) {
          return;
        }

      }

    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "scalar_variable"

  // $ANTLR start "lock_variable"
  // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:580:1: lock_variable : scalar_variable ;
  public final void lock_variable() throws RecognitionException {
    try {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:581:4: ( scalar_variable )
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:581:8: scalar_variable
      {
        pushFollow(FOLLOW_scalar_variable_in_lock_variable2270);
        scalar_variable();

        state._fsp--;
        if (state.failed) {
          return;
        }

        if (state.backtracking == 0) {
          action.lock_variable();
        }

      }

    } catch (RecognitionException re) {
      reportError(re);
      recover(input, re);
    } finally {
      // do for sure before leaving
    }
    return;
  }
  // $ANTLR end "lock_variable"

  // $ANTLR start synpred1_FortranParserExtras
  public final void synpred1_FortranParserExtras_fragment() throws RecognitionException {
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:94:8: ( ( label )? T_IMPLICIT )
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:94:9: ( label )? T_IMPLICIT
    {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:94:9: ( label )?
      int alt24 = 2;
      int LA24_0 = input.LA(1);

      if ((LA24_0 == T_DIGIT_STRING)) {
        alt24 = 1;
      }
      switch (alt24) {
        case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:94:10: label
        {
          pushFollow(FOLLOW_label_in_synpred1_FortranParserExtras184);
          label();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;

      }


      match(input, T_IMPLICIT, FOLLOW_T_IMPLICIT_in_synpred1_FortranParserExtras188);
      if (state.failed) {
        return;
      }

    }

  }
  // $ANTLR end synpred1_FortranParserExtras

  // $ANTLR start synpred2_FortranParserExtras
  public final void synpred2_FortranParserExtras_fragment() throws RecognitionException {
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:95:8: ( ( label )? T_PARAMETER )
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:95:9: ( label )? T_PARAMETER
    {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:95:9: ( label )?
      int alt25 = 2;
      int LA25_0 = input.LA(1);

      if ((LA25_0 == T_DIGIT_STRING)) {
        alt25 = 1;
      }
      switch (alt25) {
        case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:95:10: label
        {
          pushFollow(FOLLOW_label_in_synpred2_FortranParserExtras210);
          label();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;

      }


      match(input, T_PARAMETER, FOLLOW_T_PARAMETER_in_synpred2_FortranParserExtras214);
      if (state.failed) {
        return;
      }

    }

  }
  // $ANTLR end synpred2_FortranParserExtras

  // $ANTLR start synpred3_FortranParserExtras
  public final void synpred3_FortranParserExtras_fragment() throws RecognitionException {
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:96:8: ( ( label )? T_FORMAT )
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:96:9: ( label )? T_FORMAT
    {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:96:9: ( label )?
      int alt26 = 2;
      int LA26_0 = input.LA(1);

      if ((LA26_0 == T_DIGIT_STRING)) {
        alt26 = 1;
      }
      switch (alt26) {
        case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:96:10: label
        {
          pushFollow(FOLLOW_label_in_synpred3_FortranParserExtras234);
          label();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;

      }


      match(input, T_FORMAT, FOLLOW_T_FORMAT_in_synpred3_FortranParserExtras238);
      if (state.failed) {
        return;
      }

    }

  }
  // $ANTLR end synpred3_FortranParserExtras

  // $ANTLR start synpred4_FortranParserExtras
  public final void synpred4_FortranParserExtras_fragment() throws RecognitionException {
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:97:8: ( ( label )? T_ENTRY )
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:97:9: ( label )? T_ENTRY
    {
      // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:97:9: ( label )?
      int alt27 = 2;
      int LA27_0 = input.LA(1);

      if ((LA27_0 == T_DIGIT_STRING)) {
        alt27 = 1;
      }
      switch (alt27) {
        case 1: // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:97:10: label
        {
          pushFollow(FOLLOW_label_in_synpred4_FortranParserExtras264);
          label();

          state._fsp--;
          if (state.failed) {
            return;
          }

        }
        break;

      }


      match(input, T_ENTRY, FOLLOW_T_ENTRY_in_synpred4_FortranParserExtras268);
      if (state.failed) {
        return;
      }

    }

  }
  // $ANTLR end synpred4_FortranParserExtras

  // $ANTLR start synpred5_FortranParserExtras
  public final void synpred5_FortranParserExtras_fragment() throws RecognitionException {
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:311:8: ( T_IDENT T_LPAREN )
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:311:9: T_IDENT T_LPAREN
    {
      match(input, T_IDENT, FOLLOW_T_IDENT_in_synpred5_FortranParserExtras1118);
      if (state.failed) {
        return;
      }

      match(input, T_LPAREN, FOLLOW_T_LPAREN_in_synpred5_FortranParserExtras1120);
      if (state.failed) {
        return;
      }

    }

  }
  // $ANTLR end synpred5_FortranParserExtras

  // $ANTLR start synpred6_FortranParserExtras
  public final void synpred6_FortranParserExtras_fragment() throws RecognitionException {
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:314:8: ( T_IDENT T_LBRACKET )
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:314:9: T_IDENT T_LBRACKET
    {
      match(input, T_IDENT, FOLLOW_T_IDENT_in_synpred6_FortranParserExtras1190);
      if (state.failed) {
        return;
      }

      match(input, T_LBRACKET, FOLLOW_T_LBRACKET_in_synpred6_FortranParserExtras1192);
      if (state.failed) {
        return;
      }

    }

  }
  // $ANTLR end synpred6_FortranParserExtras

  // $ANTLR start synpred7_FortranParserExtras
  public final void synpred7_FortranParserExtras_fragment() throws RecognitionException {
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:323:8: ( T_IDENT T_LPAREN )
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:323:9: T_IDENT T_LPAREN
    {
      match(input, T_IDENT, FOLLOW_T_IDENT_in_synpred7_FortranParserExtras1263);
      if (state.failed) {
        return;
      }

      match(input, T_LPAREN, FOLLOW_T_LPAREN_in_synpred7_FortranParserExtras1265);
      if (state.failed) {
        return;
      }

    }

  }
  // $ANTLR end synpred7_FortranParserExtras

  // $ANTLR start synpred8_FortranParserExtras
  public final void synpred8_FortranParserExtras_fragment() throws RecognitionException {
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:460:8: ( allocate_object T_LBRACKET )
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:460:9: allocate_object T_LBRACKET
    {
      pushFollow(FOLLOW_allocate_object_in_synpred8_FortranParserExtras1875);
      allocate_object();

      state._fsp--;
      if (state.failed) {
        return;
      }

      match(input, T_LBRACKET, FOLLOW_T_LBRACKET_in_synpred8_FortranParserExtras1877);
      if (state.failed) {
        return;
      }

    }

  }
  // $ANTLR end synpred8_FortranParserExtras

  // $ANTLR start synpred9_FortranParserExtras
  public final void synpred9_FortranParserExtras_fragment() throws RecognitionException {
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:473:8: ( allocate_object )
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:473:9: allocate_object
    {
      pushFollow(FOLLOW_allocate_object_in_synpred9_FortranParserExtras1955);
      allocate_object();

      state._fsp--;
      if (state.failed) {
        return;
      }

    }

  }
  // $ANTLR end synpred9_FortranParserExtras

  // $ANTLR start synpred10_FortranParserExtras
  public final void synpred10_FortranParserExtras_fragment() throws RecognitionException {
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:513:8: ( T_ASTERISK )
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:513:9: T_ASTERISK
    {
      match(input, T_ASTERISK, FOLLOW_T_ASTERISK_in_synpred10_FortranParserExtras2091);
      if (state.failed) {
        return;
      }

    }

  }
  // $ANTLR end synpred10_FortranParserExtras

  // $ANTLR start synpred11_FortranParserExtras
  public final void synpred11_FortranParserExtras_fragment() throws RecognitionException {
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:514:8: ( expr T_COLON T_ASTERISK )
    // /Users/ananthaamornphong/NetBeansProjects/ANTLRTest/src/edu/ua/fortran/FortranParserExtras.g:514:9: expr T_COLON T_ASTERISK
    {
      pushFollow(FOLLOW_expr_in_synpred11_FortranParserExtras2119);
      expr();

      state._fsp--;
      if (state.failed) {
        return;
      }

      match(input, T_COLON, FOLLOW_T_COLON_in_synpred11_FortranParserExtras2121);
      if (state.failed) {
        return;
      }

      match(input, T_ASTERISK, FOLLOW_T_ASTERISK_in_synpred11_FortranParserExtras2123);
      if (state.failed) {
        return;
      }

    }

  }
  // $ANTLR end synpred11_FortranParserExtras

  // Delegated rules
  public void case_stmt() throws RecognitionException {
    gFortranParser08.case_stmt();
  }

  public void variable() throws RecognitionException {
    gFortranParser08.variable();
  }

  public void format_specification() throws RecognitionException {
    gFortranParser08.format_specification();
  }

  public void declaration_construct() throws RecognitionException {
    gFortranParser08.declaration_construct();
  }

  public void proc_component_attr_spec() throws RecognitionException {
    gFortranParser08.proc_component_attr_spec();
  }

  public void binding_attr_list() throws RecognitionException {
    gFortranParser08.binding_attr_list();
  }

  public void proc_component_attr_spec_list() throws RecognitionException {
    gFortranParser08.proc_component_attr_spec_list();
  }

  public void output_item() throws RecognitionException {
    gFortranParser08.output_item();
  }

  public void io_implied_do_object() throws RecognitionException {
    gFortranParser08.io_implied_do_object();
  }

  public void end_enum_stmt() throws RecognitionException {
    gFortranParser08.end_enum_stmt();
  }

  public void sync_all_stmt() throws RecognitionException {
    gFortranParser08.sync_all_stmt();
  }

  public void component_data_source() throws RecognitionException {
    gFortranParser08.component_data_source();
  }

  public void backspace_stmt() throws RecognitionException {
    gFortranParser08.backspace_stmt();
  }

  public void cray_pointer_assoc() throws RecognitionException {
    gFortranParser08.cray_pointer_assoc();
  }

  public void equivalence_object_list() throws RecognitionException {
    gFortranParser08.equivalence_object_list();
  }

  public void data_stmt() throws RecognitionException {
    gFortranParser08.data_stmt();
  }

  public void stop_code() throws RecognitionException {
    gFortranParser08.stop_code();
  }

  public void null_init() throws RecognitionException {
    gFortranParser08.null_init();
  }

  public void assignment_stmt() throws RecognitionException {
    gFortranParser08.assignment_stmt();
  }

  public void pause_stmt() throws RecognitionException {
    gFortranParser08.pause_stmt();
  }

  public Token and_op() throws RecognitionException {
    return gFortranParser08.and_op();
  }

  public void else_if_stmt() throws RecognitionException {
    gFortranParser08.else_if_stmt();
  }

  public void pointer_object_list() throws RecognitionException {
    gFortranParser08.pointer_object_list();
  }

  public void type_guard_stmt() throws RecognitionException {
    gFortranParser08.type_guard_stmt();
  }

  public void forall_triplet_spec() throws RecognitionException {
    gFortranParser08.forall_triplet_spec();
  }

  public void scalar_default_char_variable() throws RecognitionException {
    gFortranParser08.scalar_default_char_variable();
  }

  public void ext_function_subprogram() throws RecognitionException {
    gFortranParser08.ext_function_subprogram();
  }

  public void generic_name_list() throws RecognitionException {
    gFortranParser08.generic_name_list();
  }

  public void arithmetic_if_stmt() throws RecognitionException {
    gFortranParser08.arithmetic_if_stmt();
  }

  public void ac_implied_do() throws RecognitionException {
    gFortranParser08.ac_implied_do();
  }

  public void designator_or_func_ref() throws RecognitionException {
    gFortranParser08.designator_or_func_ref();
  }

  public void derived_type_spec() throws RecognitionException {
    gFortranParser08.derived_type_spec();
  }

  public void alloc_opt_list() throws RecognitionException {
    gFortranParser08.alloc_opt_list();
  }

  public void scalar_int_variable() throws RecognitionException {
    gFortranParser08.scalar_int_variable();
  }

  public void assigned_goto_stmt() throws RecognitionException {
    gFortranParser08.assigned_goto_stmt();
  }

  public void lock_stat_list() throws RecognitionException {
    gFortranParser08.lock_stat_list();
  }

  public void equivalence_set_list() throws RecognitionException {
    gFortranParser08.equivalence_set_list();
  }

  public void masked_elsewhere_stmt() throws RecognitionException {
    gFortranParser08.masked_elsewhere_stmt();
  }

  public void type_param_or_comp_def_stmt() throws RecognitionException {
    gFortranParser08.type_param_or_comp_def_stmt();
  }

  public void allocate_shape_spec() throws RecognitionException {
    gFortranParser08.allocate_shape_spec();
  }

  public void close_spec() throws RecognitionException {
    gFortranParser08.close_spec();
  }

  public void end_forall_stmt() throws RecognitionException {
    gFortranParser08.end_forall_stmt();
  }

  public void type_param_decl_list() throws RecognitionException {
    gFortranParser08.type_param_decl_list();
  }

  public void forall_stmt() throws RecognitionException {
    gFortranParser08.forall_stmt();
  }

  public void contains_stmt() throws RecognitionException {
    gFortranParser08.contains_stmt();
  }

  public void end_select_stmt() throws RecognitionException {
    gFortranParser08.end_select_stmt();
  }

  public void else_stmt() throws RecognitionException {
    gFortranParser08.else_stmt();
  }

  public void association_list() throws RecognitionException {
    gFortranParser08.association_list();
  }

  public void allocate_stmt() throws RecognitionException {
    gFortranParser08.allocate_stmt();
  }

  public void real_literal_constant() throws RecognitionException {
    gFortranParser08.real_literal_constant();
  }

  public void rewind_stmt() throws RecognitionException {
    gFortranParser08.rewind_stmt();
  }

  public void bind_stmt() throws RecognitionException {
    gFortranParser08.bind_stmt();
  }

  public void procedure_designator() throws RecognitionException {
    gFortranParser08.procedure_designator();
  }

  public void end_if_stmt() throws RecognitionException {
    gFortranParser08.end_if_stmt();
  }

  public void t_prefix() throws RecognitionException {
    gFortranParser08.t_prefix();
  }

  public void type_bound_procedure_part() throws RecognitionException {
    gFortranParser08.type_bound_procedure_part();
  }

  public void end_critical_stmt() throws RecognitionException {
    gFortranParser08.end_critical_stmt();
  }

  public void sequence_stmt() throws RecognitionException {
    gFortranParser08.sequence_stmt();
  }

  public Token extended_intrinsic_op() throws RecognitionException {
    return gFortranParser08.extended_intrinsic_op();
  }

  public void data_implied_do() throws RecognitionException {
    gFortranParser08.data_implied_do();
  }

  public void allocate_shape_spec_list() throws RecognitionException {
    gFortranParser08.allocate_shape_spec_list();
  }

  public void allocate_coshape_spec_list() throws RecognitionException {
    gFortranParser08.allocate_coshape_spec_list();
  }

  public void substring() throws RecognitionException {
    gFortranParser08.substring();
  }

  public void associate_stmt() throws RecognitionException {
    gFortranParser08.associate_stmt();
  }

  public void implicit_spec_list() throws RecognitionException {
    gFortranParser08.implicit_spec_list();
  }

  public void subroutine_stmt() throws RecognitionException {
    gFortranParser08.subroutine_stmt();
  }

  public Token name() throws RecognitionException {
    return gFortranParser08.name();
  }

  public void final_binding() throws RecognitionException {
    gFortranParser08.final_binding();
  }

  public void procedure_stmt() throws RecognitionException {
    gFortranParser08.procedure_stmt();
  }

  public void explicit_shape_spec() throws RecognitionException {
    gFortranParser08.explicit_shape_spec();
  }

  public void dimension_stmt() throws RecognitionException {
    gFortranParser08.dimension_stmt();
  }

  public void mult_operand() throws RecognitionException {
    gFortranParser08.mult_operand();
  }

  public void entity_decl() throws RecognitionException {
    gFortranParser08.entity_decl();
  }

  public void io_control_spec() throws RecognitionException {
    gFortranParser08.io_control_spec();
  }

  public void array_spec_element() throws RecognitionException {
    gFortranParser08.array_spec_element();
  }

  public void equivalence_stmt() throws RecognitionException {
    gFortranParser08.equivalence_stmt();
  }

  public void dealloc_opt_list() throws RecognitionException {
    gFortranParser08.dealloc_opt_list();
  }

  public void proc_binding_stmt() throws RecognitionException {
    gFortranParser08.proc_binding_stmt();
  }

  public void interface_specification() throws RecognitionException {
    gFortranParser08.interface_specification();
  }

  public void connect_spec_list() throws RecognitionException {
    gFortranParser08.connect_spec_list();
  }

  public void attr_spec_extension() throws RecognitionException {
    gFortranParser08.attr_spec_extension();
  }

  public void block_stmt() throws RecognitionException {
    gFortranParser08.block_stmt();
  }

  public void language_binding_spec() throws RecognitionException {
    gFortranParser08.language_binding_spec();
  }

  public void component_attr_spec() throws RecognitionException {
    gFortranParser08.component_attr_spec();
  }

  public void case_value() throws RecognitionException {
    gFortranParser08.case_value();
  }

  public void target_decl() throws RecognitionException {
    gFortranParser08.target_decl();
  }

  public void io_implied_do() throws RecognitionException {
    gFortranParser08.io_implied_do();
  }

  public void private_or_sequence() throws RecognitionException {
    gFortranParser08.private_or_sequence();
  }

  public void deferred_shape_spec_list() throws RecognitionException {
    gFortranParser08.deferred_shape_spec_list();
  }

  public void data_stmt_constant() throws RecognitionException {
    gFortranParser08.data_stmt_constant();
  }

  public void data_stmt_value() throws RecognitionException {
    gFortranParser08.data_stmt_value();
  }

  public void end_subroutine_stmt() throws RecognitionException {
    gFortranParser08.end_subroutine_stmt();
  }

  public void end_interface_stmt() throws RecognitionException {
    gFortranParser08.end_interface_stmt();
  }

  public void component_def_stmt() throws RecognitionException {
    gFortranParser08.component_def_stmt();
  }

  public void kind_selector() throws RecognitionException {
    gFortranParser08.kind_selector();
  }

  public Token equiv_op() throws RecognitionException {
    return gFortranParser08.equiv_op();
  }

  public void use_stmt() throws RecognitionException {
    gFortranParser08.use_stmt();
  }

  public void end_do() throws RecognitionException {
    gFortranParser08.end_do();
  }

  public void char_variable() throws RecognitionException {
    gFortranParser08.char_variable();
  }

  public Token end_of_stmt() throws RecognitionException {
    return gFortranParser08.end_of_stmt();
  }

  public void defined_io_generic_spec() throws RecognitionException {
    gFortranParser08.defined_io_generic_spec();
  }

  public void connect_spec() throws RecognitionException {
    gFortranParser08.connect_spec();
  }

  public void io_implied_do_suffix() throws RecognitionException {
    gFortranParser08.io_implied_do_suffix();
  }

  public void nullify_stmt() throws RecognitionException {
    gFortranParser08.nullify_stmt();
  }

  public Token add_op() throws RecognitionException {
    return gFortranParser08.add_op();
  }

  public void named_constant_def() throws RecognitionException {
    gFortranParser08.named_constant_def();
  }

  public void initialization() throws RecognitionException {
    gFortranParser08.initialization();
  }

  public void format_item_list() throws RecognitionException {
    gFortranParser08.format_item_list();
  }

  public void module_nature() throws RecognitionException {
    gFortranParser08.module_nature();
  }

  public void end_mp_subprogram_stmt() throws RecognitionException {
    gFortranParser08.end_mp_subprogram_stmt();
  }

  public void real_part() throws RecognitionException {
    gFortranParser08.real_part();
  }

  public Token concat_op() throws RecognitionException {
    return gFortranParser08.concat_op();
  }

  public void codimension_decl() throws RecognitionException {
    gFortranParser08.codimension_decl();
  }

  public void sync_images_stmt() throws RecognitionException {
    gFortranParser08.sync_images_stmt();
  }

  public void import_stmt() throws RecognitionException {
    gFortranParser08.import_stmt();
  }

  public void type_spec() throws RecognitionException {
    gFortranParser08.type_spec();
  }

  public void allocatable_stmt() throws RecognitionException {
    gFortranParser08.allocatable_stmt();
  }

  public void do_variable() throws RecognitionException {
    gFortranParser08.do_variable();
  }

  public void wait_spec_list() throws RecognitionException {
    gFortranParser08.wait_spec_list();
  }

  public void separate_module_subprogram() throws RecognitionException {
    gFortranParser08.separate_module_subprogram();
  }

  public void add_operand() throws RecognitionException {
    gFortranParser08.add_operand();
  }

  public void signed_int_literal_constant() throws RecognitionException {
    gFortranParser08.signed_int_literal_constant();
  }

  public void proc_component_def_stmt() throws RecognitionException {
    gFortranParser08.proc_component_def_stmt();
  }

  public void parameter_stmt() throws RecognitionException {
    gFortranParser08.parameter_stmt();
  }

  public void equivalence_set() throws RecognitionException {
    gFortranParser08.equivalence_set();
  }

  public void cycle_stmt() throws RecognitionException {
    gFortranParser08.cycle_stmt();
  }

  public void specification_part_and_block() throws RecognitionException {
    gFortranParser08.specification_part_and_block();
  }

  public void selector() throws RecognitionException {
    gFortranParser08.selector();
  }

  public void intent_spec() throws RecognitionException {
    gFortranParser08.intent_spec();
  }

  public void alloc_opt() throws RecognitionException {
    gFortranParser08.alloc_opt();
  }

  public void enumerator_def_stmt() throws RecognitionException {
    gFortranParser08.enumerator_def_stmt();
  }

  public void other_specification_stmt() throws RecognitionException {
    gFortranParser08.other_specification_stmt();
  }

  public void type_param_value() throws RecognitionException {
    gFortranParser08.type_param_value();
  }

  public void assign_stmt() throws RecognitionException {
    gFortranParser08.assign_stmt();
  }

  public void char_selector() throws RecognitionException {
    gFortranParser08.char_selector();
  }

  public void end_where_stmt() throws RecognitionException {
    gFortranParser08.end_where_stmt();
  }

  public void only_list() throws RecognitionException {
    gFortranParser08.only_list();
  }

  public void dummy_arg() throws RecognitionException {
    gFortranParser08.dummy_arg();
  }

  public void generic_spec() throws RecognitionException {
    gFortranParser08.generic_spec();
  }

  public Token not_op() throws RecognitionException {
    return gFortranParser08.not_op();
  }

  public void stmt_function_stmt() throws RecognitionException {
    gFortranParser08.stmt_function_stmt();
  }

  public void scalar_char_constant() throws RecognitionException {
    gFortranParser08.scalar_char_constant();
  }

  public void module_subprogram() throws RecognitionException {
    gFortranParser08.module_subprogram();
  }

  public void intent_stmt() throws RecognitionException {
    gFortranParser08.intent_stmt();
  }

  public void end_program_stmt() throws RecognitionException {
    gFortranParser08.end_program_stmt();
  }

  public void level_2_expr() throws RecognitionException {
    gFortranParser08.level_2_expr();
  }

  public void bounds_remapping() throws RecognitionException {
    gFortranParser08.bounds_remapping();
  }

  public void case_construct() throws RecognitionException {
    gFortranParser08.case_construct();
  }

  public void write_stmt() throws RecognitionException {
    gFortranParser08.write_stmt();
  }

  public void block() throws RecognitionException {
    gFortranParser08.block();
  }

  public void format_item() throws RecognitionException {
    gFortranParser08.format_item();
  }

  public void output_item_list() throws RecognitionException {
    gFortranParser08.output_item_list();
  }

  public void where_construct() throws RecognitionException {
    gFortranParser08.where_construct();
  }

  public void default_logical_variable() throws RecognitionException {
    gFortranParser08.default_logical_variable();
  }

  public void imag_part() throws RecognitionException {
    gFortranParser08.imag_part();
  }

  public void close_stmt() throws RecognitionException {
    gFortranParser08.close_stmt();
  }

  public void proc_decl_list() throws RecognitionException {
    gFortranParser08.proc_decl_list();
  }

  public void forall_construct_stmt() throws RecognitionException {
    gFortranParser08.forall_construct_stmt();
  }

  public void prefix_spec() throws RecognitionException {
    gFortranParser08.prefix_spec();
  }

  public void type_param_spec() throws RecognitionException {
    gFortranParser08.type_param_spec();
  }

  public void declaration_type_spec() throws RecognitionException {
    gFortranParser08.declaration_type_spec();
  }

  public Token power_op() throws RecognitionException {
    return gFortranParser08.power_op();
  }

  public void actual_arg_spec_list() throws RecognitionException {
    gFortranParser08.actual_arg_spec_list();
  }

  public Token intrinsic_operator() throws RecognitionException {
    return gFortranParser08.intrinsic_operator();
  }

  public Token mult_op() throws RecognitionException {
    return gFortranParser08.mult_op();
  }

  public void exit_stmt() throws RecognitionException {
    gFortranParser08.exit_stmt();
  }

  public void save_stmt() throws RecognitionException {
    gFortranParser08.save_stmt();
  }

  public void type_param_decl() throws RecognitionException {
    gFortranParser08.type_param_decl();
  }

  public void level_3_expr() throws RecognitionException {
    gFortranParser08.level_3_expr();
  }

  public void case_value_range() throws RecognitionException {
    gFortranParser08.case_value_range();
  }

  public void vector_subscript() throws RecognitionException {
    gFortranParser08.vector_subscript();
  }

  public void codimension_stmt() throws RecognitionException {
    gFortranParser08.codimension_stmt();
  }

  public void flush_spec() throws RecognitionException {
    gFortranParser08.flush_spec();
  }

  public void type_param_or_comp_def_stmt_list() throws RecognitionException {
    gFortranParser08.type_param_or_comp_def_stmt_list();
  }

  public void interface_body() throws RecognitionException {
    gFortranParser08.interface_body();
  }

  public void program_stmt() throws RecognitionException {
    gFortranParser08.program_stmt();
  }

  public void external_stmt() throws RecognitionException {
    gFortranParser08.external_stmt();
  }

  public void execution_part() throws RecognitionException {
    gFortranParser08.execution_part();
  }

  public void asynchronous_stmt() throws RecognitionException {
    gFortranParser08.asynchronous_stmt();
  }

  public void elsewhere_stmt() throws RecognitionException {
    gFortranParser08.elsewhere_stmt();
  }

  public void scalar_int_constant() throws RecognitionException {
    gFortranParser08.scalar_int_constant();
  }

  public void end_associate_stmt() throws RecognitionException {
    gFortranParser08.end_associate_stmt();
  }

  public void rename() throws RecognitionException {
    gFortranParser08.rename();
  }

  public void end_function_stmt() throws RecognitionException {
    gFortranParser08.end_function_stmt();
  }

  public void ac_implied_do_control() throws RecognitionException {
    gFortranParser08.ac_implied_do_control();
  }

  public void actual_arg_spec() throws RecognitionException {
    gFortranParser08.actual_arg_spec();
  }

  public void implicit_spec() throws RecognitionException {
    gFortranParser08.implicit_spec();
  }

  public void close_spec_list() throws RecognitionException {
    gFortranParser08.close_spec_list();
  }

  public void select_type_stmt() throws RecognitionException {
    gFortranParser08.select_type_stmt();
  }

  public void end_block_stmt() throws RecognitionException {
    gFortranParser08.end_block_stmt();
  }

  public void access_id() throws RecognitionException {
    gFortranParser08.access_id();
  }

  public void critical_stmt() throws RecognitionException {
    gFortranParser08.critical_stmt();
  }

  public void logical_literal_constant() throws RecognitionException {
    gFortranParser08.logical_literal_constant();
  }

  public void int_variable() throws RecognitionException {
    gFortranParser08.int_variable();
  }

  public void stmt_label_list() throws RecognitionException {
    gFortranParser08.stmt_label_list();
  }

  public void internal_subprogram() throws RecognitionException {
    gFortranParser08.internal_subprogram();
  }

  public void forall_body_construct() throws RecognitionException {
    gFortranParser08.forall_body_construct();
  }

  public void optional_stmt() throws RecognitionException {
    gFortranParser08.optional_stmt();
  }

  public void flush_stmt() throws RecognitionException {
    gFortranParser08.flush_stmt();
  }

  public void proc_attr_spec() throws RecognitionException {
    gFortranParser08.proc_attr_spec();
  }

  public void component_spec_list() throws RecognitionException {
    gFortranParser08.component_spec_list();
  }

  public void data_component_def_stmt() throws RecognitionException {
    gFortranParser08.data_component_def_stmt();
  }

  public void block_data() throws RecognitionException {
    gFortranParser08.block_data();
  }

  public void parent_identifier() throws RecognitionException {
    gFortranParser08.parent_identifier();
  }

  public void volatile_stmt() throws RecognitionException {
    gFortranParser08.volatile_stmt();
  }

  public void bind_entity_list() throws RecognitionException {
    gFortranParser08.bind_entity_list();
  }

  public void component_decl_list() throws RecognitionException {
    gFortranParser08.component_decl_list();
  }

  public Token rel_op() throws RecognitionException {
    return gFortranParser08.rel_op();
  }

  public void continue_stmt() throws RecognitionException {
    gFortranParser08.continue_stmt();
  }

  public void access_id_list() throws RecognitionException {
    gFortranParser08.access_id_list();
  }

  public void signed_real_literal_constant() throws RecognitionException {
    gFortranParser08.signed_real_literal_constant();
  }

  public void select_type() throws RecognitionException {
    gFortranParser08.select_type();
  }

  public void rename_list() throws RecognitionException {
    gFortranParser08.rename_list();
  }

  public void data_stmt_value_list() throws RecognitionException {
    gFortranParser08.data_stmt_value_list();
  }

  public void flush_spec_list() throws RecognitionException {
    gFortranParser08.flush_spec_list();
  }

  public void attr_spec() throws RecognitionException {
    gFortranParser08.attr_spec();
  }

  public void constant() throws RecognitionException {
    gFortranParser08.constant();
  }

  public void inquire_stmt() throws RecognitionException {
    gFortranParser08.inquire_stmt();
  }

  public void end_select_type_stmt() throws RecognitionException {
    gFortranParser08.end_select_type_stmt();
  }

  public void where_stmt() throws RecognitionException {
    gFortranParser08.where_stmt();
  }

  public void forall_assignment_stmt() throws RecognitionException {
    gFortranParser08.forall_assignment_stmt();
  }

  public void equivalence_object() throws RecognitionException {
    gFortranParser08.equivalence_object();
  }

  public void submodule() throws RecognitionException {
    gFortranParser08.submodule();
  }

  public void case_value_range_list() throws RecognitionException {
    gFortranParser08.case_value_range_list();
  }

  public void format_stmt() throws RecognitionException {
    gFortranParser08.format_stmt();
  }

  public void do_term_action_stmt() throws RecognitionException {
    gFortranParser08.do_term_action_stmt();
  }

  public void do_stmt() throws RecognitionException {
    gFortranParser08.do_stmt();
  }

  public void data_i_do_object() throws RecognitionException {
    gFortranParser08.data_i_do_object();
  }

  public void block_data_stmt() throws RecognitionException {
    gFortranParser08.block_data_stmt();
  }

  public void generic_binding() throws RecognitionException {
    gFortranParser08.generic_binding();
  }

  public void print_stmt() throws RecognitionException {
    gFortranParser08.print_stmt();
  }

  public void allocate_coshape_spec() throws RecognitionException {
    gFortranParser08.allocate_coshape_spec();
  }

  public void if_construct() throws RecognitionException {
    gFortranParser08.if_construct();
  }

  public void if_then_stmt() throws RecognitionException {
    gFortranParser08.if_then_stmt();
  }

  public void end_type_stmt() throws RecognitionException {
    gFortranParser08.end_type_stmt();
  }

  public void sync_stat_list() throws RecognitionException {
    gFortranParser08.sync_stat_list();
  }

  public void allocatable_decl_list() throws RecognitionException {
    gFortranParser08.allocatable_decl_list();
  }

  public void prefix() throws RecognitionException {
    gFortranParser08.prefix();
  }

  public void codimension_decl_list() throws RecognitionException {
    gFortranParser08.codimension_decl_list();
  }

  public void common_block_object_list() throws RecognitionException {
    gFortranParser08.common_block_object_list();
  }

  public void module_stmt() throws RecognitionException {
    gFortranParser08.module_stmt();
  }

  public void saved_entity() throws RecognitionException {
    gFortranParser08.saved_entity();
  }

  public void file_unit_number() throws RecognitionException {
    gFortranParser08.file_unit_number();
  }

  public void submodule_stmt() throws RecognitionException {
    gFortranParser08.submodule_stmt();
  }

  public void endfile_stmt() throws RecognitionException {
    gFortranParser08.endfile_stmt();
  }

  public void forall_header() throws RecognitionException {
    gFortranParser08.forall_header();
  }

  public void access_spec() throws RecognitionException {
    gFortranParser08.access_spec();
  }

  public void saved_entity_list() throws RecognitionException {
    gFortranParser08.saved_entity_list();
  }

  public void io_implied_do_control() throws RecognitionException {
    gFortranParser08.io_implied_do_control();
  }

  public void bind_entity() throws RecognitionException {
    gFortranParser08.bind_entity();
  }

  public void proc_interface() throws RecognitionException {
    gFortranParser08.proc_interface();
  }

  public void end_block_data_stmt() throws RecognitionException {
    gFortranParser08.end_block_data_stmt();
  }

  public void logical_variable() throws RecognitionException {
    gFortranParser08.logical_variable();
  }

  public void module_subprogram_part() throws RecognitionException {
    gFortranParser08.module_subprogram_part();
  }

  public void label_do_stmt() throws RecognitionException {
    gFortranParser08.label_do_stmt();
  }

  public void call_stmt() throws RecognitionException {
    gFortranParser08.call_stmt();
  }

  public void derived_type_stmt() throws RecognitionException {
    gFortranParser08.derived_type_stmt();
  }

  public void lock_stat() throws RecognitionException {
    gFortranParser08.lock_stat();
  }

  public void data_stmt_object() throws RecognitionException {
    gFortranParser08.data_stmt_object();
  }

  public void component_array_spec() throws RecognitionException {
    gFortranParser08.component_array_spec();
  }

  public void expr() throws RecognitionException {
    gFortranParser08.expr();
  }

  public void enumerator_list() throws RecognitionException {
    gFortranParser08.enumerator_list();
  }

  public void goto_stmt() throws RecognitionException {
    gFortranParser08.goto_stmt();
  }

  public void where_construct_stmt() throws RecognitionException {
    gFortranParser08.where_construct_stmt();
  }

  public void named_constant_def_list() throws RecognitionException {
    gFortranParser08.named_constant_def_list();
  }

  public void main_program() throws RecognitionException {
    gFortranParser08.main_program();
  }

  public void end_submodule_stmt() throws RecognitionException {
    gFortranParser08.end_submodule_stmt();
  }

  public void forall_triplet_spec_list() throws RecognitionException {
    gFortranParser08.forall_triplet_spec_list();
  }

  public void data_stmt_set() throws RecognitionException {
    gFortranParser08.data_stmt_set();
  }

  public void select_case_stmt() throws RecognitionException {
    gFortranParser08.select_case_stmt();
  }

  public void value_stmt() throws RecognitionException {
    gFortranParser08.value_stmt();
  }

  public void dealloc_opt() throws RecognitionException {
    gFortranParser08.dealloc_opt();
  }

  public void dimension_decl() throws RecognitionException {
    gFortranParser08.dimension_decl();
  }

  public Token object_name() throws RecognitionException {
    return gFortranParser08.object_name();
  }

  public void where_body_construct() throws RecognitionException {
    gFortranParser08.where_body_construct();
  }

  public void errorstop_stmt() throws RecognitionException {
    gFortranParser08.errorstop_stmt();
  }

  public void pointer_decl_list() throws RecognitionException {
    gFortranParser08.pointer_decl_list();
  }

  public Token kind_param() throws RecognitionException {
    return gFortranParser08.kind_param();
  }

  public void sync_stat() throws RecognitionException {
    gFortranParser08.sync_stat();
  }

  public void unlock_stmt() throws RecognitionException {
    gFortranParser08.unlock_stmt();
  }

  public void power_operand() throws RecognitionException {
    gFortranParser08.power_operand();
  }

  public void select_type_construct() throws RecognitionException {
    gFortranParser08.select_type_construct();
  }

  public void image_set() throws RecognitionException {
    gFortranParser08.image_set();
  }

  public void entry_stmt() throws RecognitionException {
    gFortranParser08.entry_stmt();
  }

  public void end_module_stmt() throws RecognitionException {
    gFortranParser08.end_module_stmt();
  }

  public void proc_decl() throws RecognitionException {
    gFortranParser08.proc_decl();
  }

  public void literal_constant() throws RecognitionException {
    gFortranParser08.literal_constant();
  }

  public void io_unit() throws RecognitionException {
    gFortranParser08.io_unit();
  }

  public void sync_memory_stmt() throws RecognitionException {
    gFortranParser08.sync_memory_stmt();
  }

  public void wait_spec() throws RecognitionException {
    gFortranParser08.wait_spec();
  }

  public void pointer_assignment_stmt() throws RecognitionException {
    gFortranParser08.pointer_assignment_stmt();
  }

  public void execution_part_construct() throws RecognitionException {
    gFortranParser08.execution_part_construct();
  }

  public void intrinsic_type_spec() throws RecognitionException {
    gFortranParser08.intrinsic_type_spec();
  }

  public void stop_stmt() throws RecognitionException {
    gFortranParser08.stop_stmt();
  }

  public void label_list() throws RecognitionException {
    gFortranParser08.label_list();
  }

  public void letter_spec_list() throws RecognitionException {
    gFortranParser08.letter_spec_list();
  }

  public Token defined_binary_op() throws RecognitionException {
    return gFortranParser08.defined_binary_op();
  }

  public void type_attr_spec() throws RecognitionException {
    gFortranParser08.type_attr_spec();
  }

  public void type_param_attr_spec() throws RecognitionException {
    gFortranParser08.type_param_attr_spec();
  }

  public Token or_op() throws RecognitionException {
    return gFortranParser08.or_op();
  }

  public void case_value_range_suffix() throws RecognitionException {
    gFortranParser08.case_value_range_suffix();
  }

  public boolean substr_range_or_arg_list_suffix() throws RecognitionException {
    return gFortranParser08.substr_range_or_arg_list_suffix();
  }

  public void bounds_spec_list() throws RecognitionException {
    gFortranParser08.bounds_spec_list();
  }

  public void access_stmt() throws RecognitionException {
    gFortranParser08.access_stmt();
  }

  public void binding_private_stmt() throws RecognitionException {
    gFortranParser08.binding_private_stmt();
  }

  public void data_i_do_object_list() throws RecognitionException {
    gFortranParser08.data_i_do_object_list();
  }

  public void equiv_operand() throws RecognitionException {
    gFortranParser08.equiv_operand();
  }

  public void enumerator() throws RecognitionException {
    gFortranParser08.enumerator();
  }

  public void derived_type_def() throws RecognitionException {
    gFortranParser08.derived_type_def();
  }

  public void structure_constructor() throws RecognitionException {
    gFortranParser08.structure_constructor();
  }

  public void procedure_declaration_stmt() throws RecognitionException {
    gFortranParser08.procedure_declaration_stmt();
  }

  public void char_constant() throws RecognitionException {
    gFortranParser08.char_constant();
  }

  public void proc_attr_spec_extension() throws RecognitionException {
    gFortranParser08.proc_attr_spec_extension();
  }

  public void namelist_group_object_list() throws RecognitionException {
    gFortranParser08.namelist_group_object_list();
  }

  public void and_operand() throws RecognitionException {
    gFortranParser08.and_operand();
  }

  public void enum_def_stmt() throws RecognitionException {
    gFortranParser08.enum_def_stmt();
  }

  public void entity_decl_list() throws RecognitionException {
    gFortranParser08.entity_decl_list();
  }

  public void scalar_constant() throws RecognitionException {
    gFortranParser08.scalar_constant();
  }

  public void input_item_list() throws RecognitionException {
    gFortranParser08.input_item_list();
  }

  public void level_5_expr() throws RecognitionException {
    gFortranParser08.level_5_expr();
  }

  public void inquire_spec_list() throws RecognitionException {
    gFortranParser08.inquire_spec_list();
  }

  public void result_name() throws RecognitionException {
    gFortranParser08.result_name();
  }

  public void boz_literal_constant() throws RecognitionException {
    gFortranParser08.boz_literal_constant();
  }

  public void protected_stmt() throws RecognitionException {
    gFortranParser08.protected_stmt();
  }

  public void letter_spec() throws RecognitionException {
    gFortranParser08.letter_spec();
  }

  public void binding_attr() throws RecognitionException {
    gFortranParser08.binding_attr();
  }

  public void dummy_arg_list() throws RecognitionException {
    gFortranParser08.dummy_arg_list();
  }

  public void specific_binding() throws RecognitionException {
    gFortranParser08.specific_binding();
  }

  public void block_construct() throws RecognitionException {
    gFortranParser08.block_construct();
  }

  public void common_stmt() throws RecognitionException {
    gFortranParser08.common_stmt();
  }

  public void dtv_type_spec() throws RecognitionException {
    gFortranParser08.dtv_type_spec();
  }

  public void return_stmt() throws RecognitionException {
    gFortranParser08.return_stmt();
  }

  public void actual_arg() throws RecognitionException {
    gFortranParser08.actual_arg();
  }

  public void int_literal_constant() throws RecognitionException {
    gFortranParser08.int_literal_constant();
  }

  public void module() throws RecognitionException {
    gFortranParser08.module();
  }

  public Token label() throws RecognitionException {
    return gFortranParser08.label();
  }

  public void pointer_decl() throws RecognitionException {
    gFortranParser08.pointer_decl();
  }

  public void int_constant() throws RecognitionException {
    gFortranParser08.int_constant();
  }

  public void data_stmt_object_list() throws RecognitionException {
    gFortranParser08.data_stmt_object_list();
  }

  public void function_subprogram() throws RecognitionException {
    gFortranParser08.function_subprogram();
  }

  public void proc_pointer_object() throws RecognitionException {
    gFortranParser08.proc_pointer_object();
  }

  public void declaration_construct_and_block() throws RecognitionException {
    gFortranParser08.declaration_construct_and_block();
  }

  public void loop_control() throws RecognitionException {
    gFortranParser08.loop_control();
  }

  public void computed_goto_stmt() throws RecognitionException {
    gFortranParser08.computed_goto_stmt();
  }

  public void length_selector() throws RecognitionException {
    gFortranParser08.length_selector();
  }

  public void association() throws RecognitionException {
    gFortranParser08.association();
  }

  public void if_stmt() throws RecognitionException {
    gFortranParser08.if_stmt();
  }

  public void private_components_stmt() throws RecognitionException {
    gFortranParser08.private_components_stmt();
  }

  public void scalar_default_logical_variable() throws RecognitionException {
    gFortranParser08.scalar_default_logical_variable();
  }

  public void open_stmt() throws RecognitionException {
    gFortranParser08.open_stmt();
  }

  public void implicit_stmt() throws RecognitionException {
    gFortranParser08.implicit_stmt();
  }

  public void data_pointer_object() throws RecognitionException {
    gFortranParser08.data_pointer_object();
  }

  public void array_spec() throws RecognitionException {
    gFortranParser08.array_spec();
  }

  public void ac_spec() throws RecognitionException {
    gFortranParser08.ac_spec();
  }

  public void enum_def() throws RecognitionException {
    gFortranParser08.enum_def();
  }

  public void explicit_shape_spec_list() throws RecognitionException {
    gFortranParser08.explicit_shape_spec_list();
  }

  public void function_stmt() throws RecognitionException {
    gFortranParser08.function_stmt();
  }

  public void bounds_remapping_list() throws RecognitionException {
    gFortranParser08.bounds_remapping_list();
  }

  public void proc_language_binding_spec() throws RecognitionException {
    gFortranParser08.proc_language_binding_spec();
  }

  public void bounds_spec() throws RecognitionException {
    gFortranParser08.bounds_spec();
  }

  public void coarray_spec() throws RecognitionException {
    gFortranParser08.coarray_spec();
  }

  public void allocate_object_list() throws RecognitionException {
    gFortranParser08.allocate_object_list();
  }

  public void end_do_stmt() throws RecognitionException {
    gFortranParser08.end_do_stmt();
  }

  public void level_1_expr() throws RecognitionException {
    gFortranParser08.level_1_expr();
  }

  public void io_control_spec_list() throws RecognitionException {
    gFortranParser08.io_control_spec_list();
  }

  public void complex_literal_constant() throws RecognitionException {
    gFortranParser08.complex_literal_constant();
  }

  public void input_item() throws RecognitionException {
    gFortranParser08.input_item();
  }

  public Token defined_unary_op() throws RecognitionException {
    return gFortranParser08.defined_unary_op();
  }

  public void suffix() throws RecognitionException {
    gFortranParser08.suffix();
  }

  public void internal_subprogram_part() throws RecognitionException {
    gFortranParser08.internal_subprogram_part();
  }

  public void signed_operand() throws RecognitionException {
    gFortranParser08.signed_operand();
  }

  public void default_char_variable() throws RecognitionException {
    gFortranParser08.default_char_variable();
  }

  public void only() throws RecognitionException {
    gFortranParser08.only();
  }

  public void inquire_spec() throws RecognitionException {
    gFortranParser08.inquire_spec();
  }

  public void mp_subprogram_stmt() throws RecognitionException {
    gFortranParser08.mp_subprogram_stmt();
  }

  public void component_spec() throws RecognitionException {
    gFortranParser08.component_spec();
  }

  public void position_spec_list() throws RecognitionException {
    gFortranParser08.position_spec_list();
  }

  public void namelist_stmt() throws RecognitionException {
    gFortranParser08.namelist_stmt();
  }

  public void target_decl_list() throws RecognitionException {
    gFortranParser08.target_decl_list();
  }

  public Token common_block_name() throws RecognitionException {
    return gFortranParser08.common_block_name();
  }

  public void hollerith_literal_constant() throws RecognitionException {
    gFortranParser08.hollerith_literal_constant();
  }

  public void component_attr_spec_extension() throws RecognitionException {
    gFortranParser08.component_attr_spec_extension();
  }

  public void type_attr_spec_list() throws RecognitionException {
    gFortranParser08.type_attr_spec_list();
  }

  public void critical_construct() throws RecognitionException {
    gFortranParser08.critical_construct();
  }

  public void pointer_stmt() throws RecognitionException {
    gFortranParser08.pointer_stmt();
  }

  public void defined_operator() throws RecognitionException {
    gFortranParser08.defined_operator();
  }

  public void component_attr_spec_list() throws RecognitionException {
    gFortranParser08.component_attr_spec_list();
  }

  public void interface_stmt() throws RecognitionException {
    gFortranParser08.interface_stmt();
  }

  public void substring_range() throws RecognitionException {
    gFortranParser08.substring_range();
  }

  public void common_block_object() throws RecognitionException {
    gFortranParser08.common_block_object();
  }

  public void data_ref() throws RecognitionException {
    gFortranParser08.data_ref();
  }

  public void position_spec() throws RecognitionException {
    gFortranParser08.position_spec();
  }

  public void designator() throws RecognitionException {
    gFortranParser08.designator();
  }

  public void component_decl() throws RecognitionException {
    gFortranParser08.component_decl();
  }

  public boolean substring_range_or_arg_list() throws RecognitionException {
    return gFortranParser08.substring_range_or_arg_list();
  }

  public void read_stmt() throws RecognitionException {
    gFortranParser08.read_stmt();
  }

  public void cray_pointer_assoc_list() throws RecognitionException {
    gFortranParser08.cray_pointer_assoc_list();
  }

  public void subroutine_subprogram() throws RecognitionException {
    gFortranParser08.subroutine_subprogram();
  }

  public void format() throws RecognitionException {
    gFortranParser08.format();
  }

  public void primary() throws RecognitionException {
    gFortranParser08.primary();
  }

  public void t_prefix_spec() throws RecognitionException {
    gFortranParser08.t_prefix_spec();
  }

  public void char_literal_constant() throws RecognitionException {
    gFortranParser08.char_literal_constant();
  }

  public void allocation_list() throws RecognitionException {
    gFortranParser08.allocation_list();
  }

  public void block_do_construct() throws RecognitionException {
    gFortranParser08.block_do_construct();
  }

  public void array_constructor() throws RecognitionException {
    gFortranParser08.array_constructor();
  }

  public void intrinsic_stmt() throws RecognitionException {
    gFortranParser08.intrinsic_stmt();
  }

  public void deallocate_stmt() throws RecognitionException {
    gFortranParser08.deallocate_stmt();
  }

  public void or_operand() throws RecognitionException {
    gFortranParser08.or_operand();
  }

  public void associate_construct() throws RecognitionException {
    gFortranParser08.associate_construct();
  }

  public void pointer_object() throws RecognitionException {
    gFortranParser08.pointer_object();
  }

  public void char_length() throws RecognitionException {
    gFortranParser08.char_length();
  }

  public void allocatable_decl() throws RecognitionException {
    gFortranParser08.allocatable_decl();
  }

  public void ac_value() throws RecognitionException {
    gFortranParser08.ac_value();
  }

  public void case_selector() throws RecognitionException {
    gFortranParser08.case_selector();
  }

  public void component_initialization() throws RecognitionException {
    gFortranParser08.component_initialization();
  }

  public void forall_construct() throws RecognitionException {
    gFortranParser08.forall_construct();
  }

  public void interface_block() throws RecognitionException {
    gFortranParser08.interface_block();
  }

  public void type_param_spec_list() throws RecognitionException {
    gFortranParser08.type_param_spec_list();
  }

  public void v_list() throws RecognitionException {
    gFortranParser08.v_list();
  }

  public void scalar_int_literal_constant() throws RecognitionException {
    gFortranParser08.scalar_int_literal_constant();
  }

  public void wait_stmt() throws RecognitionException {
    gFortranParser08.wait_stmt();
  }

  public void lock_stmt() throws RecognitionException {
    gFortranParser08.lock_stmt();
  }

  public Token keyword() throws RecognitionException {
    return gFortranParser08.keyword();
  }

  public void target_stmt() throws RecognitionException {
    gFortranParser08.target_stmt();
  }

  public void ac_value_list() throws RecognitionException {
    gFortranParser08.ac_value_list();
  }

  public void do_construct() throws RecognitionException {
    gFortranParser08.do_construct();
  }

  public final boolean synpred6_FortranParserExtras() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred6_FortranParserExtras_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred4_FortranParserExtras() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred4_FortranParserExtras_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred8_FortranParserExtras() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred8_FortranParserExtras_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred11_FortranParserExtras() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred11_FortranParserExtras_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred10_FortranParserExtras() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred10_FortranParserExtras_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred3_FortranParserExtras() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred3_FortranParserExtras_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred5_FortranParserExtras() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred5_FortranParserExtras_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred7_FortranParserExtras() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred7_FortranParserExtras_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred2_FortranParserExtras() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred2_FortranParserExtras_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred1_FortranParserExtras() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred1_FortranParserExtras_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred9_FortranParserExtras() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred9_FortranParserExtras_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }
  protected DFA4 dfa4 = new DFA4(this);
  static final String DFA4_eotS =
          "\145\uffff";
  static final String DFA4_eofS =
          "\145\uffff";
  static final String DFA4_minS =
          "\1\13\1\0\1\uffff\3\0\137\uffff";
  static final String DFA4_maxS =
          "\1\u00f0\1\0\1\uffff\3\0\137\uffff";
  static final String DFA4_acceptS =
          "\2\uffff\1\1\3\uffff\1\5\133\uffff\1\2\1\3\1\4";
  static final String DFA4_specialS =
          "\1\0\1\1\1\uffff\1\2\1\3\1\4\137\uffff}>";
  static final String[] DFA4_transitionS = {
    "\1\1\63\uffff\6\6\2\uffff\2\6\1\uffff\5\6\1\uffff\1\6\1\uffff"
    + "\4\6\1\uffff\1\6\1\uffff\4\6\1\uffff\1\6\1\uffff\4\6\4\uffff"
    + "\1\5\1\6\1\uffff\3\6\1\uffff\1\6\2\uffff\1\6\1\uffff\1\4\3\uffff"
    + "\3\6\1\uffff\1\2\3\uffff\5\6\2\uffff\1\6\4\uffff\1\6\1\uffff"
    + "\1\6\1\uffff\1\6\1\uffff\1\3\1\uffff\3\6\1\uffff\2\6\1\uffff"
    + "\2\6\1\uffff\1\6\2\uffff\6\6\1\uffff\1\6\2\uffff\2\6\2\uffff"
    + "\1\6\1\uffff\1\6\1\uffff\3\6\2\uffff\1\6\13\uffff\2\6\3\uffff"
    + "\1\6\1\uffff\1\6\1\uffff\1\6\1\uffff\2\6\2\uffff\1\6\2\uffff"
    + "\2\6\2\uffff\1\6\7\uffff\13\6\3\uffff\1\6",
    "\1\uffff",
    "",
    "\1\uffff",
    "\1\uffff",
    "\1\uffff",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    ""
  };
  static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
  static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
  static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
  static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
  static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
  static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
  static final short[][] DFA4_transition;

  static {
    int numStates = DFA4_transitionS.length;
    DFA4_transition = new short[numStates][];
    for (int i = 0; i < numStates; i++) {
      DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
    }
  }

  class DFA4 extends DFA {

    public DFA4(BaseRecognizer recognizer) {
      this.recognizer = recognizer;
      this.decisionNumber = 4;
      this.eot = DFA4_eot;
      this.eof = DFA4_eof;
      this.min = DFA4_min;
      this.max = DFA4_max;
      this.accept = DFA4_accept;
      this.special = DFA4_special;
      this.transition = DFA4_transition;
    }

    public String getDescription() {
      return "93:1: implicit_part_recursion : ( ( ( label )? T_IMPLICIT )=> implicit_stmt implicit_part_recursion | ( ( label )? T_PARAMETER )=> parameter_stmt implicit_part_recursion | ( ( label )? T_FORMAT )=> format_stmt implicit_part_recursion | ( ( label )? T_ENTRY )=> entry_stmt implicit_part_recursion |);";
    }

    public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
      TokenStream input = (TokenStream) _input;
      int _s = s;
      switch (s) {
        case 0:
          int LA4_0 = input.LA(1);


          int index4_0 = input.index();
          input.rewind();

          s = -1;
          if ((LA4_0 == T_DIGIT_STRING)) {
            s = 1;
          } else if ((LA4_0 == T_IMPLICIT) && (synpred1_FortranParserExtras())) {
            s = 2;
          } else if ((LA4_0 == T_PARAMETER)) {
            s = 3;
          } else if ((LA4_0 == T_FORMAT)) {
            s = 4;
          } else if ((LA4_0 == T_ENTRY)) {
            s = 5;
          } else if (((LA4_0 >= T_INTEGER && LA4_0 <= T_ABSTRACT) || (LA4_0 >= T_ALLOCATABLE && LA4_0 <= T_ALLOCATE) || (LA4_0 >= T_ASSIGN && LA4_0 <= T_BLOCK) || LA4_0 == T_CALL || (LA4_0 >= T_CLASS && LA4_0 <= T_COMMON) || LA4_0 == T_CONTAINS || (LA4_0 >= T_CONTINUE && LA4_0 <= T_DATA) || LA4_0 == T_DEALLOCATE || (LA4_0 >= T_DO && LA4_0 <= T_DOUBLECOMPLEX) || LA4_0 == T_ENUM || (LA4_0 >= T_ERROR && LA4_0 <= T_EXIT) || LA4_0 == T_EXTERNAL || LA4_0 == T_FLUSH || (LA4_0 >= T_GO && LA4_0 <= T_IF) || (LA4_0 >= T_INTENT && LA4_0 <= T_LOCK) || LA4_0 == T_NAMELIST || LA4_0 == T_NULLIFY || LA4_0 == T_OPEN || LA4_0 == T_OPTIONAL || (LA4_0 >= T_PAUSE && LA4_0 <= T_PRINT) || (LA4_0 >= T_PRIVATE && LA4_0 <= T_PROCEDURE) || (LA4_0 >= T_PROTECTED && LA4_0 <= T_PUBLIC) || LA4_0 == T_READ || (LA4_0 >= T_RETURN && LA4_0 <= T_SELECTTYPE) || LA4_0 == T_STOP || (LA4_0 >= T_SYNC && LA4_0 <= T_TARGET) || LA4_0 == T_TYPE || LA4_0 == T_UNLOCK || (LA4_0 >= T_VALUE && LA4_0 <= T_WAIT) || LA4_0 == T_WRITE || (LA4_0 >= T_ENDBLOCK && LA4_0 <= T_ENDBLOCKDATA) || LA4_0 == T_ENDFILE || LA4_0 == T_ENDFUNCTION || LA4_0 == T_ENDMODULE || (LA4_0 >= T_ENDPROCEDURE && LA4_0 <= T_ENDPROGRAM) || LA4_0 == T_ENDSUBROUTINE || (LA4_0 >= T_END && LA4_0 <= T_DIMENSION) || LA4_0 == T_BIND || (LA4_0 >= T_STMT_FUNCTION && LA4_0 <= T_INQUIRE_STMT_2) || LA4_0 == T_IDENT)) {
            s = 6;
          }


          input.seek(index4_0);

          if (s >= 0) {
            return s;
          }
          break;

        case 1:
          int LA4_1 = input.LA(1);


          int index4_1 = input.index();
          input.rewind();

          s = -1;
          if ((synpred1_FortranParserExtras())) {
            s = 2;
          } else if ((synpred2_FortranParserExtras())) {
            s = 98;
          } else if ((synpred3_FortranParserExtras())) {
            s = 99;
          } else if ((synpred4_FortranParserExtras())) {
            s = 100;
          } else if ((true)) {
            s = 6;
          }


          input.seek(index4_1);

          if (s >= 0) {
            return s;
          }
          break;

        case 2:
          int LA4_3 = input.LA(1);


          int index4_3 = input.index();
          input.rewind();

          s = -1;
          if ((synpred2_FortranParserExtras())) {
            s = 98;
          } else if ((true)) {
            s = 6;
          }


          input.seek(index4_3);

          if (s >= 0) {
            return s;
          }
          break;

        case 3:
          int LA4_4 = input.LA(1);


          int index4_4 = input.index();
          input.rewind();

          s = -1;
          if ((synpred3_FortranParserExtras())) {
            s = 99;
          } else if ((true)) {
            s = 6;
          }


          input.seek(index4_4);

          if (s >= 0) {
            return s;
          }
          break;

        case 4:
          int LA4_5 = input.LA(1);


          int index4_5 = input.index();
          input.rewind();

          s = -1;
          if ((synpred4_FortranParserExtras())) {
            s = 100;
          } else if ((true)) {
            s = 6;
          }


          input.seek(index4_5);

          if (s >= 0) {
            return s;
          }
          break;
      }
      if (state.backtracking > 0) {
        state.failed = true;
        return -1;
      }

      NoViableAltException nvae =
              new NoViableAltException(getDescription(), 4, _s, input);
      error(nvae);
      throw nvae;
    }
  }
  public static final BitSet FOLLOW_use_stmt_in_specification_part95 = new BitSet(new long[]{0x8000000000000800L, 0x3010930E1034109FL, 0x001C90081B250087L, 0x0000000404800000L});
  public static final BitSet FOLLOW_import_stmt_in_specification_part111 = new BitSet(new long[]{0x8000000000000800L, 0x3010930E1034109FL, 0x001890081B250087L, 0x0000000404800000L});
  public static final BitSet FOLLOW_implicit_part_recursion_in_specification_part125 = new BitSet(new long[]{0x8000000000000802L, 0x0010930E1034109FL, 0x001890081B250087L, 0x0000000404800000L});
  public static final BitSet FOLLOW_declaration_construct_in_specification_part137 = new BitSet(new long[]{0x8000000000000802L, 0x0010930E1034109FL, 0x001890081B250087L, 0x0000000404800000L});
  public static final BitSet FOLLOW_implicit_stmt_in_implicit_part_recursion194 = new BitSet(new long[]{0x0000000000000800L, 0x1010010000000000L, 0x0000000000040000L});
  public static final BitSet FOLLOW_implicit_part_recursion_in_implicit_part_recursion199 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_parameter_stmt_in_implicit_part_recursion219 = new BitSet(new long[]{0x0000000000000800L, 0x1010010000000000L, 0x0000000000040000L});
  public static final BitSet FOLLOW_implicit_part_recursion_in_implicit_part_recursion223 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_format_stmt_in_implicit_part_recursion246 = new BitSet(new long[]{0x0000000000000800L, 0x1010010000000000L, 0x0000000000040000L});
  public static final BitSet FOLLOW_implicit_part_recursion_in_implicit_part_recursion253 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_entry_stmt_in_implicit_part_recursion277 = new BitSet(new long[]{0x0000000000000800L, 0x1010010000000000L, 0x0000000000040000L});
  public static final BitSet FOLLOW_implicit_part_recursion_in_implicit_part_recursion285 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_action_stmt_in_executable_construct321 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_associate_construct_in_executable_construct330 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_block_construct_in_executable_construct339 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_case_construct_in_executable_construct365 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_critical_construct_in_executable_construct374 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_do_construct_in_executable_construct397 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_forall_construct_in_executable_construct406 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_if_construct_in_executable_construct415 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_select_type_construct_in_executable_construct424 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_where_construct_in_executable_construct433 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_allocate_stmt_in_action_stmt476 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_assignment_stmt_in_action_stmt485 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_backspace_stmt_in_action_stmt494 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_call_stmt_in_action_stmt503 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_close_stmt_in_action_stmt512 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_continue_stmt_in_action_stmt521 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_cycle_stmt_in_action_stmt530 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_deallocate_stmt_in_action_stmt539 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_endfile_stmt_in_action_stmt555 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_errorstop_stmt_in_action_stmt564 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_exit_stmt_in_action_stmt589 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_flush_stmt_in_action_stmt598 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_forall_stmt_in_action_stmt607 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_goto_stmt_in_action_stmt616 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_if_stmt_in_action_stmt625 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_inquire_stmt_in_action_stmt634 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_lock_stmt_in_action_stmt645 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_nullify_stmt_in_action_stmt675 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_open_stmt_in_action_stmt684 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_pointer_assignment_stmt_in_action_stmt693 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_print_stmt_in_action_stmt702 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_read_stmt_in_action_stmt711 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_return_stmt_in_action_stmt720 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_rewind_stmt_in_action_stmt729 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_stop_stmt_in_action_stmt738 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_sync_all_stmt_in_action_stmt747 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_sync_images_stmt_in_action_stmt773 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_sync_memory_stmt_in_action_stmt796 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_unlock_stmt_in_action_stmt819 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_wait_stmt_in_action_stmt847 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_where_stmt_in_action_stmt856 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_write_stmt_in_action_stmt865 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_arithmetic_if_stmt_in_action_stmt874 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_computed_goto_stmt_in_action_stmt883 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_assign_stmt_in_action_stmt892 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_assigned_goto_stmt_in_action_stmt920 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_pause_stmt_in_action_stmt941 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_label_in_type_declaration_stmt1000 = new BitSet(new long[]{0x8000000000000000L, 0x0000000E0004000FL, 0x0000800000000000L});
  public static final BitSet FOLLOW_declaration_type_spec_in_type_declaration_stmt1006 = new BitSet(new long[]{0x0000000006000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0001000000000000L});
  public static final BitSet FOLLOW_T_COMMA_in_type_declaration_stmt1013 = new BitSet(new long[]{0x0000000000000010L, 0x0000800001101080L, 0x0018100819250005L, 0x0000000007800000L});
  public static final BitSet FOLLOW_attr_spec_in_type_declaration_stmt1015 = new BitSet(new long[]{0x0000000006000000L});
  public static final BitSet FOLLOW_T_COLON_COLON_in_type_declaration_stmt1021 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0001000000000000L});
  public static final BitSet FOLLOW_entity_decl_list_in_type_declaration_stmt1028 = new BitSet(new long[]{0x0000000000000020L});
  public static final BitSet FOLLOW_end_of_stmt_in_type_declaration_stmt1030 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_IDENT_in_part_ref1125 = new BitSet(new long[]{0x0000000800000000L});
  public static final BitSet FOLLOW_T_LPAREN_in_part_ref1127 = new BitSet(new long[]{0x00E0005C0780BA00L, 0x0000000000000000L, 0x0000000000000000L, 0x0001200030000000L});
  public static final BitSet FOLLOW_section_subscript_list_in_part_ref1129 = new BitSet(new long[]{0x0000100000000000L});
  public static final BitSet FOLLOW_T_RPAREN_in_part_ref1131 = new BitSet(new long[]{0x0000000400000002L});
  public static final BitSet FOLLOW_image_selector_in_part_ref1163 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_IDENT_in_part_ref1197 = new BitSet(new long[]{0x0000000400000000L});
  public static final BitSet FOLLOW_image_selector_in_part_ref1199 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_IDENT_in_part_ref1221 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_IDENT_in_part_ref_no_image_selector1270 = new BitSet(new long[]{0x0000000800000000L});
  public static final BitSet FOLLOW_T_LPAREN_in_part_ref_no_image_selector1272 = new BitSet(new long[]{0x00E0005C0780BA00L, 0x0000000000000000L, 0x0000000000000000L, 0x0001200030000000L});
  public static final BitSet FOLLOW_section_subscript_list_in_part_ref_no_image_selector1274 = new BitSet(new long[]{0x0000100000000000L});
  public static final BitSet FOLLOW_T_RPAREN_in_part_ref_no_image_selector1276 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_IDENT_in_part_ref_no_image_selector1298 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_expr_in_section_subscript1347 = new BitSet(new long[]{0x0000000003000000L});
  public static final BitSet FOLLOW_section_subscript_ambiguous_in_section_subscript1349 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_COLON_in_section_subscript1358 = new BitSet(new long[]{0x00E0005C0100BA02L, 0x0000000000000000L, 0x0000000000000000L, 0x0001200030000000L});
  public static final BitSet FOLLOW_expr_in_section_subscript1361 = new BitSet(new long[]{0x0000000001000002L});
  public static final BitSet FOLLOW_T_COLON_in_section_subscript1368 = new BitSet(new long[]{0x00E0005C0000BA00L, 0x0000000000000000L, 0x0000000000000000L, 0x0001200030000000L});
  public static final BitSet FOLLOW_expr_in_section_subscript1370 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_COLON_COLON_in_section_subscript1396 = new BitSet(new long[]{0x00E0005C0000BA00L, 0x0000000000000000L, 0x0000000000000000L, 0x0001200030000000L});
  public static final BitSet FOLLOW_expr_in_section_subscript1398 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_IDENT_in_section_subscript1420 = new BitSet(new long[]{0x0000000008000000L});
  public static final BitSet FOLLOW_T_EQUALS_in_section_subscript1422 = new BitSet(new long[]{0x00E0005C0000BA00L, 0x0000000000000000L, 0x0000000000000000L, 0x0001200030000000L});
  public static final BitSet FOLLOW_expr_in_section_subscript1424 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_IDENT_in_section_subscript1447 = new BitSet(new long[]{0x0000000008000000L});
  public static final BitSet FOLLOW_T_EQUALS_in_section_subscript1449 = new BitSet(new long[]{0x0000000000800000L});
  public static final BitSet FOLLOW_T_ASTERISK_in_section_subscript1451 = new BitSet(new long[]{0x0000000000000800L});
  public static final BitSet FOLLOW_label_in_section_subscript1453 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_ASTERISK_in_section_subscript1476 = new BitSet(new long[]{0x0000000000000800L});
  public static final BitSet FOLLOW_label_in_section_subscript1478 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_COLON_in_section_subscript_ambiguous1528 = new BitSet(new long[]{0x00E0005C0100BA02L, 0x0000000000000000L, 0x0000000000000000L, 0x0001200030000000L});
  public static final BitSet FOLLOW_expr_in_section_subscript_ambiguous1531 = new BitSet(new long[]{0x0000000001000002L});
  public static final BitSet FOLLOW_T_COLON_in_section_subscript_ambiguous1538 = new BitSet(new long[]{0x00E0005C0000BA00L, 0x0000000000000000L, 0x0000000000000000L, 0x0001200030000000L});
  public static final BitSet FOLLOW_expr_in_section_subscript_ambiguous1540 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_COLON_COLON_in_section_subscript_ambiguous1605 = new BitSet(new long[]{0x00E0005C0000BA00L, 0x0000000000000000L, 0x0000000000000000L, 0x0001200030000000L});
  public static final BitSet FOLLOW_expr_in_section_subscript_ambiguous1607 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_section_subscript_in_section_subscript_list1679 = new BitSet(new long[]{0x0000000004000002L});
  public static final BitSet FOLLOW_T_COMMA_in_section_subscript_list1702 = new BitSet(new long[]{0x00E0005C0780BA00L, 0x0000000000000000L, 0x0000000000000000L, 0x0001200030000000L});
  public static final BitSet FOLLOW_section_subscript_in_section_subscript_list1704 = new BitSet(new long[]{0x0000000004000002L});
  public static final BitSet FOLLOW_T_LBRACKET_in_image_selector1745 = new BitSet(new long[]{0x00E0005C0000BA00L, 0x0000000000000000L, 0x0000000000000000L, 0x0001200030000000L});
  public static final BitSet FOLLOW_cosubscript_list_in_image_selector1747 = new BitSet(new long[]{0x0000080000000000L});
  public static final BitSet FOLLOW_T_RBRACKET_in_image_selector1749 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_scalar_int_expr_in_cosubscript1785 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_cosubscript_in_cosubscript_list1819 = new BitSet(new long[]{0x0000000004000002L});
  public static final BitSet FOLLOW_T_COMMA_in_cosubscript_list1825 = new BitSet(new long[]{0x00E0005C0000BA00L, 0x0000000000000000L, 0x0000000000000000L, 0x0001200030000000L});
  public static final BitSet FOLLOW_cosubscript_in_cosubscript_list1827 = new BitSet(new long[]{0x0000000004000002L});
  public static final BitSet FOLLOW_allocate_object_in_allocation1892 = new BitSet(new long[]{0x0000000400000000L});
  public static final BitSet FOLLOW_T_LBRACKET_in_allocation1894 = new BitSet(new long[]{0x00E0005C0080BA00L, 0x0000000000000000L, 0x0000000000000000L, 0x0001200030000000L});
  public static final BitSet FOLLOW_allocate_coarray_spec_in_allocation1896 = new BitSet(new long[]{0x0000080000000000L});
  public static final BitSet FOLLOW_T_RBRACKET_in_allocation1898 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_allocate_object_in_allocation1970 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_part_ref_no_image_selector_in_allocate_object2024 = new BitSet(new long[]{0x0000002000000002L});
  public static final BitSet FOLLOW_T_PERCENT_in_allocate_object2036 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0001000000000000L});
  public static final BitSet FOLLOW_part_ref_no_image_selector_in_allocate_object2038 = new BitSet(new long[]{0x0000002000000002L});
  public static final BitSet FOLLOW_T_ASTERISK_in_allocate_coarray_spec2109 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_expr_in_allocate_coarray_spec2128 = new BitSet(new long[]{0x0000000001000000L});
  public static final BitSet FOLLOW_T_COLON_in_allocate_coarray_spec2130 = new BitSet(new long[]{0x0000000000800000L});
  public static final BitSet FOLLOW_T_ASTERISK_in_allocate_coarray_spec2132 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_expr_in_logical_expr2161 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_expr_in_scalar_logical_expr2178 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_expr_in_int_expr2202 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_expr_in_scalar_int_expr2219 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_expr_in_scalar_variable2241 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_scalar_variable_in_lock_variable2270 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_label_in_synpred1_FortranParserExtras184 = new BitSet(new long[]{0x0000000000000000L, 0x1000000000000000L});
  public static final BitSet FOLLOW_T_IMPLICIT_in_synpred1_FortranParserExtras188 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_label_in_synpred2_FortranParserExtras210 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000040000L});
  public static final BitSet FOLLOW_T_PARAMETER_in_synpred2_FortranParserExtras214 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_label_in_synpred3_FortranParserExtras234 = new BitSet(new long[]{0x0000000000000000L, 0x0010000000000000L});
  public static final BitSet FOLLOW_T_FORMAT_in_synpred3_FortranParserExtras238 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_label_in_synpred4_FortranParserExtras264 = new BitSet(new long[]{0x0000000000000000L, 0x0000010000000000L});
  public static final BitSet FOLLOW_T_ENTRY_in_synpred4_FortranParserExtras268 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_IDENT_in_synpred5_FortranParserExtras1118 = new BitSet(new long[]{0x0000000800000000L});
  public static final BitSet FOLLOW_T_LPAREN_in_synpred5_FortranParserExtras1120 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_IDENT_in_synpred6_FortranParserExtras1190 = new BitSet(new long[]{0x0000000400000000L});
  public static final BitSet FOLLOW_T_LBRACKET_in_synpred6_FortranParserExtras1192 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_IDENT_in_synpred7_FortranParserExtras1263 = new BitSet(new long[]{0x0000000800000000L});
  public static final BitSet FOLLOW_T_LPAREN_in_synpred7_FortranParserExtras1265 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_allocate_object_in_synpred8_FortranParserExtras1875 = new BitSet(new long[]{0x0000000400000000L});
  public static final BitSet FOLLOW_T_LBRACKET_in_synpred8_FortranParserExtras1877 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_allocate_object_in_synpred9_FortranParserExtras1955 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_T_ASTERISK_in_synpred10_FortranParserExtras2091 = new BitSet(new long[]{0x0000000000000002L});
  public static final BitSet FOLLOW_expr_in_synpred11_FortranParserExtras2119 = new BitSet(new long[]{0x0000000001000000L});
  public static final BitSet FOLLOW_T_COLON_in_synpred11_FortranParserExtras2121 = new BitSet(new long[]{0x0000000000800000L});
  public static final BitSet FOLLOW_T_ASTERISK_in_synpred11_FortranParserExtras2123 = new BitSet(new long[]{0x0000000000000002L});
}