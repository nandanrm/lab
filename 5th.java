import java.util.Scanner;
public class HillCipher {
 // Modular multiplicative inverse of determinant modulo 26
 private static int modInverse(int n) {
 n %= 26;
 for (int x = 1; x < 26; x++) {
 if ((n * x) % 26 == 1) return x;
 }
 return -1;
 }
 public static String encrypt(String text, int[][] key) {
 StringBuilder res = new StringBuilder();
 for (int i = 0; i < text.length(); i += 2) {
 int p1 = text.charAt(i) - 'A';
 int p2 = text.charAt(i + 1) - 'A';
 res.append((char) (((key[0][0] * p1 + key[0][1] * p2) % 26) + 'A'));
 res.append((char) (((key[1][0] * p1 + key[1][1] * p2) % 26) + 'A'));
 }
 return res.toString();
 }
 public static String decrypt(String cipher, int[][] key) {
 int det = (key[0][0] * key[1][1] - key[0][1] * key[1][0]) % 26;
 if (det < 0) det += 26;
 int invDet = modInverse(det);
   int[][] invKey = {
 { (key[1][1] * invDet) % 26, ((-key[0][1] * invDet) % 26 + 26) % 26 },
 { ((-key[1][0] * invDet) % 26 + 26) % 26, (key[0][0] * invDet) % 26 }
 };
 return encrypt(cipher, invKey); // Decryption uses same logic with inverse key
 }
 public static void main(String[] args) {
 int[][] key = {{3, 3}, {2, 5}}; // Valid key: det=9, coprime to 26
 String msg = "HELP";
 String encrypted = encrypt(msg, key);
 String decrypted = decrypt(encrypted, key);
 System.out.println("Original: " + msg);
 System.out.println("Encrypted: " + encrypted);
 System.out.println("Decrypted: " + decrypted);
 }
}
