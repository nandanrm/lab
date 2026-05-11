import java.util.*;
public class PlayfairCipher {
 private char[][] matrix = new char[5][5];
 public PlayfairCipher(String key) {
 generateMatrix(key);
 }
 private void generateMatrix(String key) {
 String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ"; // 'J' is excluded
 String combined = (key.toUpperCase().replace("J", "I") + alphabet);
 StringBuilder uniqueStr = new StringBuilder();
 for (char c : combined.toCharArray()) {
 if (uniqueStr.indexOf(String.valueOf(c)) == -1) uniqueStr.append(c);
 }
 for (int i = 0; i < 25; i++) {
 matrix[i / 5][i % 5] = uniqueStr.charAt(i);
 }
 }
 private String formatText(String text, boolean encrypt) {
 text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
 if (!encrypt) return text; // Ciphertext should already be even
 StringBuilder sb = new StringBuilder(text);
 for (int i = 0; i < sb.length() - 1; i += 2) {
 if (sb.charAt(i) == sb.charAt(i + 1)) sb.insert(i + 1, 'X'); 
   }
 if (sb.length() % 2 != 0) sb.append('X');
 return sb.toString();
 }
 private int[] findPos(char c) {
 for (int r = 0; r < 5; r++) {
 for (int col = 0; col < 5; col++) {
 if (matrix[r][col] == c) return new int[]{r, col};
 }
 }
 return null;
 }
 public String process(String text, boolean encrypt) {
 String formatted = formatText(text, encrypt);
 StringBuilder result = new StringBuilder();
 int delta = encrypt ? 1 : 4; // Use +1 for encryption, -1 (mod 5 is +4) for decryption
 for (int i = 0; i < formatted.length(); i += 2) {
 int[] a = findPos(formatted.charAt(i));
 int[] b = findPos(formatted.charAt(i + 1));
 if (a[0] == b[0]) { // Same Row
 result.append(matrix[a[0]][(a[1] + delta) % 5]);
 result.append(matrix[b[0]][(b[1] + delta) % 5]);
 } else if (a[1] == b[1]) { // Same Column
 result.append(matrix[(a[0] + delta) % 5][a[1]]);
 result.append(matrix[(b[0] + delta) % 5][b[1]]);
  } else { // Rectangle Rule
 result.append(matrix[a[0]][b[1]]);
 result.append(matrix[b[0]][a[1]]);
 }
 }
 return result.toString();
 }
 public static void main(String[] args) {
 PlayfairCipher pc = new PlayfairCipher("MONARCHY");
 String original = "INSTRUMENTS";
 String encrypted = pc.process(original, true);
 String decrypted = pc.process(encrypted, false);
 System.out.println("Key: MONARCHY");
 System.out.println("Plaintext: " + original);
 System.out.println("Encrypted: " + encrypted);
 System.out.println("Decrypted: " + decrypted);
 }
}
