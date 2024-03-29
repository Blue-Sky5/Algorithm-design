import java.util.*;
import java.util.Stack;


public class Main {
    public static HashMap<Integer, Integer> union = new HashMap<>();
    public static int key_finder(int target) {
        Stack<Integer> holder = new Stack<>();
        int key = union.get(target);
        while (true) {
            if (key == target) {
                while (!holder.empty()) {
                    union.replace(holder.pop(), key);
                }
                return key;
            } else {
                holder.push(target);
                target = key;
                key = union.get(target);
            }
        }
    }

    public static void unifier(int child, int parent) {
        union.replace(child, parent);
    }

    static void quickSort(edge[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(edge[] arr, int low, int high) {
        int pivot = arr[high].value;
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr[j].value < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    static void swap(edge[] arr, int i, int j) {
        edge temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        edge[] edges;
        int total_weight = 0;
        int edge_count = 0;
        int node_count = 0;
        int kruskal_counter;

        //...................................................
        node_count = input.nextInt();
        edge_count = input.nextInt();
        edges = new edge[edge_count];
        for(int i = 0 ; i < edge_count  ; i++){
            edges[i] = new edge(input.nextInt(), input.nextInt(), input.nextInt());
            total_weight+=edges[i].value;
        }


        for(int i = 0 ; i < node_count ; i ++){
            union.put(i, i);
        }

        // Sort the edges
        quickSort(edges, 0, edges.length - 1);

        // Kruskal's algorithm
        kruskal_counter = 0;
        int connectedComponents = 1; // Number of connected components in the MST

        while(connectedComponents < node_count && kruskal_counter < edges.length){
            if (key_finder(edges[kruskal_counter].from) != key_finder(edges[kruskal_counter].to)) {
                unifier(key_finder(edges[kruskal_counter].from), key_finder(edges[kruskal_counter].to));
                total_weight -= edges[kruskal_counter].value;
                connectedComponents += 1;
            }
            kruskal_counter += 1;
        }

        System.out.println(total_weight);
    }
}

class edge{
    int from ;
    int to ;
    int value;
    public edge(int from, int to , int value){
        this.from = from;
        this.to = to;
        this.value = value;
    }
}

