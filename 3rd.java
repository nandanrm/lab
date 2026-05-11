public class VigenereCipher {
 public static String encrypt(String text, final String key) {
 StringBuilder result = new StringBuilder();
 text = text.toUpperCase();
 String keyUpper = key.toUpperCase();
 for (int i = 0, j = 0; i < text.length(); i++) {
 char c = text.charAt(i);
 if (c < 'A' || c > 'Z') {
 result.append(c);
 continue;
 }
 result.append((char) ((c + keyUpper.charAt(j) - 2 * 'A') % 26 + 'A'));
 j = ++j % keyUpper.length();
 }
 return result.toString();
 }
 public static String decrypt(String text, final String key) {
 StringBuilder result = new StringBuilder();
 text = text.toUpperCase();
 String keyUpper = key.toUpperCase();
 for (int i = 0, j = 0; i < text.length(); i++) {
 char c = text.charAt(i);
 if (c < 'A' || c > 'Z') {
result.append(c);
continue;
 }
 result.append((char) ((c - keyUpper.charAt(j) + 26) % 26 + 'A'));
 j = ++j % keyUpper.length();
 }
 return result.toString();
 }
 public static void main(String[] args) {
 String key = "VIGENERECIPHER";
 String message = "Beware the Jabberwock, my son! The jaws that bite, the claws that
catch!";
 String encryptedMsg = encrypt(message, key);
 System.out.println("Original String: " + message);
 System.out.println("Encrypted message: " + encryptedMsg);
 String decryptedMsg = decrypt(encryptedMsg, key);
 System.out.println("Decrypted message: " + decryptedMsg);
 }
}
