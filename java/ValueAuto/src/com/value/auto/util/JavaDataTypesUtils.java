package com.value.auto.util;

import java.sql.Types;
import java.util.HashMap;

public class JavaDataTypesUtils {
    /**
     * 根据数据库的类型取得java类型（不是完整类名）
     */
	private final static IntStringMap _preferredJavaTypeForSqlType = new IntStringMap();
	/**
	 * 根据数据库的类型取得java类型（完整类名）
	 */
	private final static IntStringMap _fullNameJavaTypeForSqlType = new IntStringMap();
	/**
     * 根据数据库的类型取得flex类型（完整类名）
     */
    private final static IntStringMap _preferredFlexTypeForSqlType = new IntStringMap();
	/**
	 * 根据数据库的类型取得struts2验证框架的类型
	 */
	private final static IntStringMap _valadationTypeForSqlType = new IntStringMap();
	public static boolean isFloatNumber(int sqlType,int size,int decimalDigits) {
		String javaType = getPreferredJavaType(sqlType,size,decimalDigits);
		if(javaType.endsWith("Float") || javaType.endsWith("Double") || javaType.endsWith("BigDecimal")) {
			return true;
		}
		return false;
	}
	
	public static boolean isIntegerNumber(int sqlType,int size,int decimalDigits) {
		String javaType = getPreferredJavaType(sqlType,size,decimalDigits);
		if(javaType.endsWith("Long") || javaType.endsWith("Integer") || javaType.endsWith("Short") || javaType.endsWith("Byte")) {
			return true;
		}
		return false;
	}

	public static boolean isDate(int sqlType,int size,int decimalDigits) {
		String javaType = getPreferredJavaType(sqlType,size,decimalDigits);
		if(javaType.endsWith("Date") || javaType.endsWith("Timestamp") || javaType.endsWith("Time")) {
			return true;
		}
		return false;
	}
	
	public static String getPreferredJavaType(int sqlType, int size,
			int decimalDigits) {
		if ((sqlType == Types.DECIMAL || sqlType == Types.NUMERIC)
				&& decimalDigits == 0) {
			if (size == 1) {
				// https://sourceforge.net/tracker/?func=detail&atid=415993&aid=662953&group_id=36044
				return "Boolean";
			} else if (size < 3) {
				return "Byte";
			} else if (size < 5) {
				return "Short";
			} else if (size < 10) {
				return "Integer";
			} else if (size <= 19) {
				return "Long";
			} else {
				return "java.math.BigDecimal";
			}
		}
		String result = _preferredJavaTypeForSqlType.getString(sqlType);
		if (result == null) {
			result = "Object";
		}
		return result;
	}
	public static String getPreferredFlexType(int sqlType, int size,
	                                          int decimalDigits) {
          if ((sqlType == Types.DECIMAL || sqlType == Types.NUMERIC)
                  && decimalDigits == 0) {
              if (size == 1) {
                  // https://sourceforge.net/tracker/?func=detail&atid=415993&aid=662953&group_id=36044
                  return "Boolean";
              } else if (size < 3) {
                  return "int";
              } else if (size < 5) {
                  return "int";
              } else if (size < 10) {
                  return "int";
              } else if (size <= 19) {
                  return "int";
              } else {
                  return "Number";
              }
          }
          String result = _preferredFlexTypeForSqlType.getString(sqlType);
          if (result == null) {
              result = "Object";
          }
          return result;
      }
	
	public static String getFullNameJavaType(int sqlType, int size,
	                                          int decimalDigits) {
          if ((sqlType == Types.DECIMAL || sqlType == Types.NUMERIC)
                  && decimalDigits == 0) {
              if (size == 1) {
                  // https://sourceforge.net/tracker/?func=detail&atid=415993&aid=662953&group_id=36044
                  return "Boolean";
              } else if (size < 3) {
                  return "Byte";
              } else if (size < 5) {
                  return "Short";
              } else if (size < 10) {
                  return "Integer";
              } else if (size <= 19) {
                  return "Long";
              } else {
                  return "java.math.BigDecimal";
              }
          }
          String result = _fullNameJavaTypeForSqlType.getString(sqlType);
          if (result == null) {
              result = "Object";
          }
          return result;
      }
	public static String getValidatorType(int sqlType, int size,
                                             int decimalDigits) {
         String result = _valadationTypeForSqlType.getString(sqlType);
         if (result == null) {
             result = "string";
         }
         return result;
     }
	
	static {
	      _fullNameJavaTypeForSqlType.put(Types.TINYINT, "java.lang.Integer");
	      _fullNameJavaTypeForSqlType.put(Types.SMALLINT, "java.lang.Short");
	      _fullNameJavaTypeForSqlType.put(Types.INTEGER, "java.lang.Long");
	      _fullNameJavaTypeForSqlType.put(Types.BIGINT, "java.lang.Long");
	      _fullNameJavaTypeForSqlType.put(Types.REAL, "java.lang.Float");
	      _fullNameJavaTypeForSqlType.put(Types.FLOAT, "java.lang.Double");
	      _fullNameJavaTypeForSqlType.put(Types.DOUBLE, "java.lang.Double");
	      _fullNameJavaTypeForSqlType.put(Types.DECIMAL, "java.math.BigDecimal");
	      _fullNameJavaTypeForSqlType.put(Types.NUMERIC, "java.math.BigDecimal");
	      _fullNameJavaTypeForSqlType.put(Types.BIT, "java.lang.Boolean");
	      _fullNameJavaTypeForSqlType.put(Types.BOOLEAN, "java.lang.Boolean");
	      _fullNameJavaTypeForSqlType.put(Types.CHAR, "java.lang.String");
	      _fullNameJavaTypeForSqlType.put(Types.VARCHAR, "java.lang.String");
	      _fullNameJavaTypeForSqlType.put(Types.LONGVARCHAR, "java.lang.String");
	      _fullNameJavaTypeForSqlType.put(Types.BINARY, "byte[]");
	      _fullNameJavaTypeForSqlType.put(Types.VARBINARY, "byte[]");
	      _fullNameJavaTypeForSqlType.put(Types.LONGVARBINARY, "byte[]");
	      _fullNameJavaTypeForSqlType.put(Types.DATE, "java.util.Date");
	      _fullNameJavaTypeForSqlType.put(Types.TIME, "java.sql.Time");
	      _fullNameJavaTypeForSqlType.put(Types.TIMESTAMP, "java.util.Date");
	      _fullNameJavaTypeForSqlType.put(Types.CLOB, "java.sql.Clob");
	      _fullNameJavaTypeForSqlType.put(Types.BLOB, "java.sql.Blob");
	      _fullNameJavaTypeForSqlType.put(Types.ARRAY, "java.sql.Array");
	      _fullNameJavaTypeForSqlType.put(Types.REF, "java.sql.Ref");
	      _fullNameJavaTypeForSqlType.put(Types.STRUCT, "java.lang.Object");
	      _fullNameJavaTypeForSqlType.put(Types.JAVA_OBJECT, "java.lang.Object");
	   }	   
   static {
      _preferredJavaTypeForSqlType.put(Types.TINYINT, "Integer");
      _preferredJavaTypeForSqlType.put(Types.SMALLINT, "Short");
      _preferredJavaTypeForSqlType.put(Types.INTEGER, "Long");
      _preferredJavaTypeForSqlType.put(Types.BIGINT, "Long");
      _preferredJavaTypeForSqlType.put(Types.REAL, "Float");
      _preferredJavaTypeForSqlType.put(Types.FLOAT, "Double");
      _preferredJavaTypeForSqlType.put(Types.DOUBLE, "Double");
      _preferredJavaTypeForSqlType.put(Types.DECIMAL, "java.math.BigDecimal");
      _preferredJavaTypeForSqlType.put(Types.NUMERIC, "java.math.BigDecimal");
      _preferredJavaTypeForSqlType.put(Types.BIT, "Boolean");
      _preferredJavaTypeForSqlType.put(Types.BOOLEAN, "Boolean");
      _preferredJavaTypeForSqlType.put(Types.CHAR, "String");
      _preferredJavaTypeForSqlType.put(Types.VARCHAR, "String");
      // according to resultset.gif, we should use java.io.Reader, but String is more convenient for EJB
      _preferredJavaTypeForSqlType.put(Types.LONGVARCHAR, "String");
      _preferredJavaTypeForSqlType.put(Types.BINARY, "byte[]");
      _preferredJavaTypeForSqlType.put(Types.VARBINARY, "byte[]");
      _preferredJavaTypeForSqlType.put(Types.LONGVARBINARY, "byte[]");
      _preferredJavaTypeForSqlType.put(Types.DATE, "java.util.Date");
      _preferredJavaTypeForSqlType.put(Types.TIME, "java.sql.Time");
      _preferredJavaTypeForSqlType.put(Types.TIMESTAMP, "java.util.Date");
      _preferredJavaTypeForSqlType.put(Types.CLOB, "java.sql.Clob");
      _preferredJavaTypeForSqlType.put(Types.BLOB, "java.sql.Blob");
      _preferredJavaTypeForSqlType.put(Types.ARRAY, "java.sql.Array");
      _preferredJavaTypeForSqlType.put(Types.REF, "java.sql.Ref");
      _preferredJavaTypeForSqlType.put(Types.STRUCT, "Object");
      _preferredJavaTypeForSqlType.put(Types.JAVA_OBJECT, "Object");
   }
   static {
       _valadationTypeForSqlType.put(Types.TINYINT, "int");
       _valadationTypeForSqlType.put(Types.SMALLINT, "short");
       _valadationTypeForSqlType.put(Types.INTEGER, "int");
       _valadationTypeForSqlType.put(Types.BIGINT, "long");
       _valadationTypeForSqlType.put(Types.REAL, "double");
       _valadationTypeForSqlType.put(Types.FLOAT, "double");
       _valadationTypeForSqlType.put(Types.DOUBLE, "double");
       _valadationTypeForSqlType.put(Types.DECIMAL, "double");
       _valadationTypeForSqlType.put(Types.NUMERIC, "double");
       _valadationTypeForSqlType.put(Types.BIT, "int");
       _valadationTypeForSqlType.put(Types.BOOLEAN, "int");
       _valadationTypeForSqlType.put(Types.CHAR, "string");
       _valadationTypeForSqlType.put(Types.VARCHAR, "string");
       _valadationTypeForSqlType.put(Types.LONGVARCHAR, "string");
       _valadationTypeForSqlType.put(Types.BINARY, "string");
       _valadationTypeForSqlType.put(Types.VARBINARY, "string");
       _valadationTypeForSqlType.put(Types.LONGVARBINARY, "string");
       _valadationTypeForSqlType.put(Types.DATE, "date");
       _valadationTypeForSqlType.put(Types.TIME, "date");
       _valadationTypeForSqlType.put(Types.TIMESTAMP, "date");
       _valadationTypeForSqlType.put(Types.CLOB, "string");
       _valadationTypeForSqlType.put(Types.BLOB, "string");
       _valadationTypeForSqlType.put(Types.ARRAY, "string");
       _valadationTypeForSqlType.put(Types.REF, "string");
       _valadationTypeForSqlType.put(Types.STRUCT, "string");
       _valadationTypeForSqlType.put(Types.JAVA_OBJECT, "string");
    }       
   static {
       _preferredFlexTypeForSqlType.put(Types.TINYINT, "int");
       _preferredFlexTypeForSqlType.put(Types.SMALLINT, "int");
       _preferredFlexTypeForSqlType.put(Types.INTEGER, "int");
       _preferredFlexTypeForSqlType.put(Types.BIGINT, "int");
       _preferredFlexTypeForSqlType.put(Types.REAL, "int");
       _preferredFlexTypeForSqlType.put(Types.FLOAT, "Number");
       _preferredFlexTypeForSqlType.put(Types.DOUBLE, "Number");
       _preferredFlexTypeForSqlType.put(Types.DECIMAL, "Number");
       _preferredFlexTypeForSqlType.put(Types.NUMERIC, "Number");
       _preferredFlexTypeForSqlType.put(Types.BIT, "Boolean");
       _preferredFlexTypeForSqlType.put(Types.BOOLEAN, "Boolean");
       _preferredFlexTypeForSqlType.put(Types.CHAR, "String");
       _preferredFlexTypeForSqlType.put(Types.VARCHAR, "String");
       // according to resultset.gif, we should use java.io.Reader, but String is more convenient for EJB
       _preferredFlexTypeForSqlType.put(Types.LONGVARCHAR, "String");
       _preferredFlexTypeForSqlType.put(Types.BINARY, "String");
       _preferredFlexTypeForSqlType.put(Types.VARBINARY, "String");
       _preferredFlexTypeForSqlType.put(Types.LONGVARBINARY, "String");
       _preferredFlexTypeForSqlType.put(Types.DATE, "Date");
       _preferredFlexTypeForSqlType.put(Types.TIME, "Date");
       _preferredFlexTypeForSqlType.put(Types.TIMESTAMP, "Date");
    }	
   private static class IntStringMap extends HashMap {

		public String getString(int i) {
			return (String) get(new Integer(i));
		}

		public String[] getStrings(int i) {
			return (String[]) get(new Integer(i));
		}

		public void put(int i, String s) {
			put(new Integer(i), s);
		}

		public void put(int i, String[] sa) {
			put(new Integer(i), sa);
		}
	}
	   
}
