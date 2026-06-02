///usr/bin/env jbang "$0" "$@" ; exit $?

import java.util.*;

class Solution {

    public static void main(String args[]) {
        try (Scanner in = new Scanner(System.in)) {
            int w = in.nextInt();
            int h = in.nextInt();
            if (in.hasNextLine()) {
                in.nextLine();
            }

            char[][] grid = new char[h][w];
            int startX = -1;
            int startY = -1;

            for (int i = 0; i < h; i++) {
                String row = in.nextLine();
                grid[i] = row.toCharArray();

                for (int j = 0; j < w; j++) {
                    if (grid[i][j] == 'S') {
                        startX = j;
                        startY = i;
                    }
                }
            }

            int[][] dist = new int[h][w];
            for (int i = 0; i < h; i++) {
                Arrays.fill(dist[i], -1);
            }

            ArrayDeque<int[]> queue = new ArrayDeque<>();
            dist[startY][startX] = 0;
            queue.add(new int[] {startX, startY});

            int[] dx = new int[] {-1, 1, 0, 0};
            int[] dy = new int[] {0, 0, -1, 1};

            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0];
                int y = cell[1];

                for (int d = 0; d < 4; d++) {
                    int nx = (x + dx[d] + w) % w;
                    int ny = (y + dy[d] + h) % h;

                    if (grid[ny][nx] == '#') {
                        continue;
                    }

                    if (dist[ny][nx] != -1) {
                        continue;
                    }

                    dist[ny][nx] = dist[y][x] + 1;
                    queue.add(new int[] {nx, ny});
                }
            }

            for (int i = 0; i < h; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < w; j++) {
                    if (grid[i][j] == '#') {
                        line.append('#');
                    } else if (dist[i][j] == -1) {
                        line.append('.');
                    } else {
                        line.append(toSymbol(dist[i][j]));
                    }
                }

                System.out.println(line);
            }
        }
    }

    private static char toSymbol(int distance) {
        if (distance >= 0 && distance <= 9) {
            return (char) ('0' + distance);
        }

        if (distance >= 10 && distance <= 35) {
            return (char) ('A' + (distance - 10));
        }

        return '?';
    }
}