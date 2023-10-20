package task3;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MostExpensiveWay {
    private static final int N_MIN = 1;
    private static final int N_MAX = 20;
    private static final int M_MIN = 1;
    private static final int M_MAX = 20;
    private static final int K_MIN = 0;
    private static final int K_MAX = 100;
    private static final int[][] k = new int[N_MAX][M_MAX];
    private static final int[][] dp = new int[N_MAX][M_MAX];
    private static final char[][] path = new char[N_MAX][M_MAX];
    private static final char RIGHT = 'R';
    private static final char DOWN = 'D';
    private static final InputStream in = System.in;
    private static final OutputStream out = System.out;
    private static int n;
    private static int m;
    private static int wayCost;
    private static String route;

    public static void main(String[] args) throws IOException {
        read();
        solve();
        write();
    }

    private static void solve() {
        dp[0][0] = k[0][0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + k[i][0];
            path[i][0] = DOWN;
        }

        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + k[0][i];
            path[0][i] = RIGHT;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j] + k[i][j];
                    path[i][j] = DOWN;
                } else {
                    dp[i][j] = dp[i][j - 1] + k[i][j];
                    path[i][j] = RIGHT;
                }
            }
        }

        wayCost = dp[n - 1][m - 1];

        Character[] routeChars = new Character[n + m - 2];
        int k = routeChars.length - 1;
        int i = n - 1;
        int j = m - 1;
        while (i > 0 || j > 0) {
            routeChars[k] = path[i][j];
            if (path[i][j] == 'D') {
                i--;
            } else {
                j--;
            }
            k--;
        }

        route = Arrays.stream(routeChars)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
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
            writer.write(String.valueOf(wayCost));
            writer.newLine();

            writer.write(route);
        }
    }
}

