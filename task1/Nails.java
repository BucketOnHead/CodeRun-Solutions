package task1;

import java.io.*;
import java.util.Arrays;

public class Nails {
    private static final int N_MIN = 2;
    private static final int N_MAX = 100;
    private static final int A_MIN = 0;
    private static final int A_MAX = 10_000;
    private static final int[] a = new int[N_MAX + 1];
    private static final int[] dp = new int[N_MAX + 1];
    private static final InputStream in = System.in;
    private static final OutputStream out = System.out;
    private static int n;
    private static int result;

    public static void main(String[] args) throws IOException {
        read();
        Arrays.sort(a, 1, n + 1);
        solve();
        write();
    }

    private static void solve() {
        dp[2] = a[2] - a[1];
        dp[3] = a[3] - a[1];
        for (int i = 4; i <= n; i++) {
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + (a[i] - a[i - 1]);
        }

        result = dp[n];
    }

    private static void read() throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(in))) {
            n = Integer.parseInt(reader.readLine());
            assert n >= N_MIN && n <= N_MAX : String.format(
                    "%d не входит в диапазон [%d, %d]", n, N_MIN, N_MAX);

            String[] data = reader.readLine().split(" ");
            assert data.length == n : String.format(
                    "Неправильный размер входных данных, ожидалось: %d, было: %d", n, data.length);

            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(data[i - 1]);
                assert a[i] >= A_MIN && a[i] <= A_MAX : String.format(
                        "Координата %d не входит в диапазон [%d, %d]", a[i], A_MIN, A_MAX);
                assert (i == 1) || (a[i] != a[i - 1]) : String.format(
                        "Координаты должны быть попарно различными, но было дублирование: %d(index = %d)", a[i], i - 1);
            }
        }
    }

    private static void write() throws IOException {
        try (var writer = new BufferedWriter((new OutputStreamWriter(out)))) {
            writer.write(String.valueOf(result));
        }
    }
}