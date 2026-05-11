import java.util.*;
public class Transposition {
 // Helper to get column order based on key (e.g., "COL" -> {0, 2, 1})
 private static Integer[] getColumnOrder(String key) {
 int n = key.length();
 Integer[] order = new Integer[n];
 for (int i = 0; i < n; i++) order[i] = i;
 Arrays.sort(order, Comparator.comparingInt(i -> key.charAt(i)));
 return order;
 }
 public static String encrypt(String text, String key) {
 int col = key.length();
 int row = (int) Math.ceil((double) text.length() / col);
 char[][] grid = new char[row][col];
 // Fill row-wise
 int k = 0;
 for (int i = 0; i < row; i++) {
 for (int j = 0; j < col; j++) {
 grid[i][j] = (k < text.length()) ? text.charAt(k++) : 'X'; // Padding
 }
 }
 // Read column-wise based on key order
 StringBuilder result = new StringBuilder();
 for (int c : getColumnOrder(key)) { 
   for (int r = 0; r < row; r++) result.append(grid[r][c]);
 }
 return result.toString();
 }
 public static void main(String[] args) {
 String msg = "HELLOWORLD";
 String key1 = "BCA";
 String key2 = "CAB";
 // Single Transposition
 String singleEnc = encrypt(msg, key1);
 // Double Transposition (Pass 1 result through Pass 2)
 String doubleEnc = encrypt(singleEnc, key2);
 System.out.println("Original: " + msg);
 System.out.println("Single Transposition: " + singleEnc);
 System.out.println("Double Transposition: " + doubleEnc);
 }
} 
