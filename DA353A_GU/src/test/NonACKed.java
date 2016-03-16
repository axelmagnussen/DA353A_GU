package test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.function.Predicate;

public class NonACKed {

    public final String filename = "/varnufilenligger/out.tr";
    public final String encoding = "UTF-8";

    static Predicate<String[]> predOut = s -> {
        return (s.length == 12 && s[0].equals("+") && s[2].equals("0") && s[3].equals("1"));
    };

    static Predicate<String[]> predIn = s -> {
        return (s.length == 12 && s[0].equals("r") && s[2].equals("1") && s[3].equals("0"));
    };

    private static BufferedReader createReader(String filename, String encoding) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return br;
    }

    public NonACKed() {
        ArrayDeque<Integer> inTransit = new ArrayDeque<>();
        ArrayDeque<Integer> recieved = new ArrayDeque<>();
        try (BufferedReader br = createReader(filename, encoding)) {
            String text = null;
            int eventId = 0;
            while ((text = br.readLine()) != null) {
                String[] parts = text.split(" ");
                double time = Double.parseDouble(parts[1]);
                Integer seqNumber = Integer.parseInt(parts[10]);

                if (predOut.test(parts)) {
                    String reTrans = (inTransit.contains(seqNumber)) ? "Retransmission: " + seqNumber : "";
                    inTransit.addLast(seqNumber);
                    if (reTrans.length() > 1)
                        inTransit.removeFirstOccurrence(seqNumber);
                    System.out.printf("EventId: %2d Time: %.2f SeqNbr -> %d Non-ACKed: %s %s\n", ++eventId, time,
                            seqNumber, inTransit, reTrans);
                }
                if (predIn.test(parts)) {
                    for (Integer seq : inTransit) {
                        if (seq <= seqNumber)
                            inTransit.remove(seq);
                    }
                    inTransit.remove(seqNumber);
                    recieved.addLast(seqNumber);
                    System.out.printf("EventId: %2d Time: %.2f SeqNbr <- %d Non-ACKed : %s\n", ++eventId, time,
                            seqNumber, inTransit);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NonACKed();
    }

}
