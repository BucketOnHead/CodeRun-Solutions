package task2;

import java.io.*;

public class CheapestWay {
    private static final int N_MIN = 1;
    private static final int N_MAX = 20;
    private static final int M_MIN = 1;
    private static final int M_MAX = 20;
    private static final int K_MIN = 1;
    private static final int K_MAX = 100;
    private static final int[][] k = new int[N_MAX][M_MAX];
    private static final int[][] dp = new int[N_MAX][M_MAX];
    private static final InputStream in = System.in;
    private static final OutputStream out = System.out;
    private static int n;
    private static int m;
    private static int result;

    public static void main(String[] args) throws IOException {
        read();
        solve();
        write();
    }

    private static void solve() {
        //   1 ? ?
        //   ? ? ?
        //   ? ? ?
        dp[0][0] = k[0][0];

        //   1 ? ?
        //   2 ? ?
        //   2 ? ?
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + k[i][0];
        }

        //   1 3 3
        //   2 ? ?
        //   2 ? ?
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + k[0][i];
        }

        //   1 3 3
        //   2 4 4
        //   2 4 4
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + k[i][j];
            }
        }

        result = dp[n - 1][m - 1];
    }

    private static void read() throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(in))) {
            String[] data;

            data = reader.readLine().split(" ");
            assert data.length == 2 : String.format(
                    "Неверный размер входных данных, ожидалось: %d, было: %d", 2, data.length);

            n = Integer.parseInt(data[0]);
            assert n >= N_MIN && n <= N_MAX : String.format(
                    "N: %d не входит в диапазон [%d, %d]", n, N_MIN, N_MAX);

            m = Integer.parseInt(data[1]);
            assert m >= M_MIN && m <= M_MAX : String.format(
                    "M: %d не входит в диапазон [%d, %d]", m, M_MIN, M_MAX);

            for (int i = 0; i < n; i++) {
                data = reader.readLine().split(" ");
                assert data.length == n : String.format(
                        "M: Неверный размер входных данных, ожидалось: %d, было: %d", n, data.length);

                for (int j = 0; j < m; j++) {
                    k[i][j] = Integer.parseInt(data[j]);
                    assert k[i][j] >= K_MIN && k[i][j] <= K_MAX : String.format(
                            "K: %d не входит в диапазон [%d, %d]", k[i][j], K_MIN, K_MAX);
                }
            }
        }
    }

    private static void write() throws IOException {
        try (var writer = new BufferedWriter(new OutputStreamWriter(out))) {
            writer.write(String.valueOf(result));
        }
    }
}

