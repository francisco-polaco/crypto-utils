package pt.ulisboa.tecnico.meic.sec;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import static javax.xml.bind.DatatypeConverter.parseBase64Binary;
import static javax.xml.bind.DatatypeConverter.printBase64Binary;

/**
 * Created by francisco on 04/03/2017.
 */
public class Utils {

    private static final String SHA_1_PRNG = "SHA1PRNG";
    private static final String SHA_256 = "SHA-256";

    public static void main(String[] args){
        System.out.println("HelloWorld!");
    }

    /**
     * It returns a byte array with the size given filled with a secure
     * based on SHA1 random stuff. Great to be used as a Nouce.
     * Note that if you want a array with less then 16 bytes, you probably don't
     * know what you are doing.
     *
     * @param byteNumber - number of bytes of the returning byte[]
     * @return randomBytes - byte[] with random suff
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSecureRandomNumber(int byteNumber) throws NoSuchAlgorithmException {
        if(byteNumber < 16) throw new NotEnoughNumberOfBytesException(); // 16 bytes = 128 bits
        byte[] randomBytes = new byte[byteNumber];
        SecureRandom secureRandom = SecureRandom.getInstance(SHA_1_PRNG);
        secureRandom.nextBytes(randomBytes);
        return randomBytes;
    }

    /**
     * It returns the actual clock and date (obvious) as a Timestamp.
     * @return actualTimestamp
     */
    public static Timestamp getActualTimestamp(){
        GregorianCalendar rightNow = new GregorianCalendar();
        return new Timestamp(rightNow.getTimeInMillis());
    }

    /**
     * It returns the base 64 representation of the given binary.
     * @param binary - binary to be converted
     * @return String
     */
    public static String convertBinaryToBase64(byte[] binary){
        return printBase64Binary(binary);
    }

    /**
     * It returns the binary representation of the given base 64.
     * @param text - base64 to be converted
     * @return byte[]
     */
    public static byte[] convertBase64ToBinary(String text){
        return parseBase64Binary(text);
    }

    /**
     *  It returns SHA-2 digest of the given byte[]
     *  Don't forget SHA-2 is still secure :D
     * @param toBeDigested - bytes to be digested
     * @return byte[] SHA-2 of toBeDigested
     * @throws NoSuchAlgorithmException
     */
    public static byte[] digest(byte[] toBeDigested) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(SHA_256);
        messageDigest.update(toBeDigested);
        return messageDigest.digest();
    }
}
