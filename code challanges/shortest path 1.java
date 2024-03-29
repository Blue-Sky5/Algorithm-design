import java.util.Scanner;

public class Main {
    public static int vertex = 0;
    public static int[][] adjacency_matrix;

    public static int nearest_unvisited(int[] distance, boolean[] is_visited) {
        int min = Integer.MAX_VALUE;
        int nearest_index = -1;

        for (int i = 0; i < vertex; i++) {
            if (!is_visited[i] && distance[i] <= min) {
                min = distance[i];
                nearest_index = i;
            }
        }

        return nearest_index;
    }

    public static int dijkstra(int source) {
        int[] distance = new int[vertex];
        int[] count = new int[vertex];
        boolean[] is_visited = new boolean[vertex];
        int current_index;

        for (int i = 0; i < vertex; i++) {
            distance[i] = Integer.MAX_VALUE;
            is_visited[i] = false;
            count[i] = 0;
        }

        distance[source] = 0;
        count[source] = 1;

        for (int i = 0; i < vertex - 1; i++) {
            current_index = nearest_unvisited(distance, is_visited);
            is_visited[current_index] = true;

            for (int v = 0; v < vertex; v++) {
                if (!is_visited[v] && adjacency_matrix[current_index][v] != 0 && distance[current_index] != Integer.MAX_VALUE) {
                    if (distance[current_index] + adjacency_matrix[current_index][v] < distance[v]) {
                        distance[v] = distance[current_index] + adjacency_matrix[current_index][v];
                        count[v] = count[current_index]; // Update the count of minimum paths
                    } else if (distance[current_index] + adjacency_matrix[current_index][v] == distance[v]) {
                        count[v] += count[current_index]; // Add to the count of minimum paths
                    }
                }
            }
        }

        return count[vertex - 1];
    }

    public static void main(String[] args) {
        int edge;
        String[] buffer;
        Scanner input = new Scanner(System.in);
        int ans = 0;

        vertex = input.nextInt();
        edge = input.nextInt();
        input.nextLine();

        adjacency_matrix = new int[vertex][vertex];
        for (int i = 0; i < edge; i++) {
            buffer = input.nextLine().split(" ");
            adjacency_matrix[Integer.parseInt(buffer[0])][Integer.parseInt(buffer[1])] = Integer.parseInt(buffer[2]);
            adjacency_matrix[Integer.parseInt(buffer[1])][Integer.parseInt(buffer[0])] = Integer.parseInt(buffer[2]);
        }

        ans = dijkstra(0);
        System.out.println(ans);

        input.close();
    }
}
