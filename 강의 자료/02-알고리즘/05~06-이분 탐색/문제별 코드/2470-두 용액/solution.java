import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    static int lower_bound(int[] A, int L, int R, int X) {
        // A[L...R] 에서 X 이상의 수 중 제일 왼쪽 인덱스를 return 하는 함수
        // 그런 게 없다면 R + 1 을 return 한다

        int res = R + 1;  // 만약 A[L...R] 중
        while (L <= R) {
            int mid = (L + R) / 2;
            if (A[mid] >= X) {
                res = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return res;
    }

    static void pro() {
        // A 에 대해 이분 탐색을 할 예정이니까, 정렬을 미리 해주자.
        Arrays.sort(A, 1, N + 1);

        int best_sum = Integer.MAX_VALUE;
        int v1 = 0, v2 = 0;
        for (int i = 1; i <= N - 1; i++) {
            // A[i] 용액을 쓸 것이다. 고로 -A[i] 와 가장 가까운 용액을 자신의 오른쪽 구간에서 찾자.
            int j = lower_bound(A, i + 1, N, -A[i]);

            // A[j - 1] 와 A[j] 중에 A[i] 와 섞었을 때의 정보를 정답에 갱신시킨다.

            // 1. A[i] + A[j - 1]
            if (i < j - 1 && Math.abs(A[i] + A[j - 1]) < best_sum) {
                best_sum = Math.abs(A[i] + A[j - 1]);
                v1 = A[i];
                v2 = A[j - 1];
            }

            // 2. A[i] + A[j]
            if (j <= N && Math.abs(A[i] + A[j]) < best_sum) {
                best_sum = Math.abs(A[i] + A[j]);
                v1 = A[i];
                v2 = A[j];
            }
            
        }
        sb.append(v1).append(' ').append(v2);
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}