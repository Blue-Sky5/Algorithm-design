import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        int dim;
        int[][] data;
        Scanner input = new Scanner(System.in);
        int counter = 0;
        boolean flag = true;
        //..................
        dim = input.nextInt();
        data = new int[dim][dim];
        for(int i = 0 ; i < dim ; i++){
            for(int j = 0 ; j < dim ; j++){
                data[i][j] = input.nextInt();
            }
        }
        for (int i = 0 ; i < dim ; i++){
            flag = true;
            for (int j = 0 ; j < i ; j++) {
                if (data[i][j] == 1) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                counter+=1;
            }
        }
        System.out.println(counter);
    }
}