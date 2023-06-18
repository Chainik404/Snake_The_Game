import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;

public class Player implements Comparable<Player>{
    public static int lastID = 0;
    private int id;
    private String name;
    private int bestScore = 0;

    public Player(String name){
        this.id = lastID;
        lastID++;
        this.name = name;
    }
    public Player(String name,int bestScore){
        this.id = lastID;
        lastID++;
        this.name = name;
        this.bestScore = bestScore;
    }
    public void setBestScore(int score){
        this.bestScore = score;
    }
    public void save(FileOutputStream fos){
        try {
            fos.write(this.name.length());

            char[] nameChr = name.toCharArray();
            for (char c: nameChr) {
                fos.write(c);
            }
            byte[] score = toBytes(bestScore,4);
            fos.write(score);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Player load(FileInputStream fis){
        StringBuilder sb = new StringBuilder();
        int bestScore = 0;
        try {
            int len = fis.read();
            byte[] name = new byte[len];
            fis.read(name);
            for (int c: name) {
                sb.append(Character.toChars(c));
            }
            byte[] byteScore = new byte[4];
            fis.read(byteScore);
            bestScore = fromBytes(byteScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Player(sb.toString(), bestScore);
    }
    private static byte[] toBytes(long l, int bytes) {
        // convert given long value to byte array with given "bytes" length

        byte[] result = new byte[bytes];
        for (int i = bytes - 1; i >= 0; i--) {
            result[i] = (byte)l;
            l >>= 8;
        }
        return result;
    }
    private static int fromBytes(byte[] bytes) {
        //convert byte array to long number

        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result <<= 8;
            result |= (bytes[i]&0xFF);
        }
        return result;

    }
    @Override
    public int compareTo(Player o) {
        return (Integer.compare(this.bestScore, o.bestScore));
    }

    @Override
    public String toString() {
        return "name - " + this.name + "\nScore - " + this.bestScore;
    }
}
