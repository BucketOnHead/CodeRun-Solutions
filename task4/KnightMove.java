package task4;

import java.io.*;

public class KnightMove {
    private static final int N_MIN = 1;
    private static final int N_MAX = 50;
    private static final int M_MIN = 1;
    private static final int M_MAX = 50;
    private static final int[][] dp = new int[N_MAX][M_MAX];
    private static final InputStream in = System.in;
    private static final OutputStream out = System.out;
    private static int n;
    private static int m;
    private static int waysCount;

    public static void main(String[] args) throws IOException {
        read();
        solve();
        write();
    }

    private static void solve() {
        dp[0][0] = 1;   // единственный способ - ничего не делать

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i + 1) < n && (j + 2) < m) {   // o
                    dp[i + 1][j + 2] += dp[i][j];   // o o x
                }

                if ((i + 2) < n && (j + 1) < m) {   // o
                    dp[i + 2][j + 1] += dp[i][j];   // o
                }                                   // o x
            }
        }

        waysCount = dp[n - 1][m - 1];
    }

    private static void read() throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(in))) {
            String[] data;

            // Чтение N и M
            data = reader.readLine().split(" ");
            assert data.length == 2 : String.format(
                    "Неверный размер входных данных, ожидалось: %d, было: %d", 2, data.length);

            // Расшифровка N
            n = Integer.parseInt(data[0]);
            assert n >= N_MIN && n <= N_MAX : String.format(
                    "N: %d не входит в диапазон [%d, %d]", n, N_MIN, N_MAX);

            // Расшифровка M
            m = Integer.parseInt(data[1]);
            assert m >= M_MIN && m <= M_MAX : String.format(
                    "M: %d не входит в диапазон [%d, %d]", m, M_MIN, M_MAX);
        }
    }

    private static void write() throws IOException {
        try (var writer = new BufferedWriter(new OutputStreamWriter(out))) {
            writer.write(String.valueOf(waysCount));
        }
    }
}
