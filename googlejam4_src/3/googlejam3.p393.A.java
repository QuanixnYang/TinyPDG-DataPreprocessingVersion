package googlejam3.p393;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class A {

//    private static final String FNAME = "test";
    private static final String FNAME = "small-0";
//    private static final String FNAME = "large-0";

    public static void main(String[] args) throws IOException {
        String fname = "data/" + A.class.getSimpleName() + "-" + FNAME + ".";
        Scanner sc = new Scanner(Paths.get(fname + "in"));
        PrintWriter w = new PrintWriter(fname + "out");

        int T = sc.nextInt();
        for (int tc = 0; tc < T; tc++) {

            int R = sc.nextInt();
            int C = sc.nextInt();
            int W = sc.nextInt();

            int res = R * C / W + W - (C % W == 0 ? 1 : 0);

            w.printf("Case #%d: %d\n", tc + 1, res);
        }

        w.close();
    }
}
