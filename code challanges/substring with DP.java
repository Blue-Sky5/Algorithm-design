/*in dp_lcs function the first loop will compare
 * first string and second string index by index
 * and store the result in arr array
 * and the cords array will keep track of the path
 *
 * the second loop which I got the idea from a website
 * goes through the cords array and find the max substring
 * which avoids counting one character twice.
 * if we don't include this second loop we might count
 * one character twice . like the commented code i
 * wrote previous to this code that had this issue */


import java.util.Scanner;

public class Main{

    public static int dp_lcs(String a , String b){
        int[][] arr = new int[a.length() * 2 + 1][b.length() + 1];
        int[][] cords = new int[a.length() * 2 + 1][b.length() + 1];
        int ans = 0;
        int x_cord;
        int y_cord;
        //.............................
        for (int i = 0; i <= a.length() * 2; i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (j > 0 && arr[i][j - 1] > arr[i][j]) {
                    arr[i][j] = arr[i][j - 1];
                    cords[i][j] = 0;
                }
                if(i > 0 ){
                    if(j > 0){
                        if(a.charAt((i-1)%a.length()) == b.charAt((j-1)%b.length()) && arr[i - 1][j - 1] + 1 > arr[i][j]){
                            arr[i][j] = arr[i - 1][j - 1] + 1;
                            cords[i][j] = 1;
                        }
                    }
                    if ( arr[i - 1][j] > arr[i][j]) {
                        arr[i][j] = arr[i - 1][j];
                        cords[i][j] = 2;
                    }
                }
            }
        }

        for (int i = 0; i < a.length(); i++) {
            ans = Math.max(ans, arr[i + a.length()][b.length()]);
            x_cord = i + 1;
            y_cord = 0;
            while (y_cord <= b.length() && cords[x_cord][y_cord] == 0){
                y_cord+=1;
            }
            for (; y_cord <= b.length() && x_cord <= a.length() * 2; x_cord++) {
                cords[x_cord][y_cord] = 0;
                arr[x_cord][b.length()] -= 1;
                if (x_cord == a.length() * 2){
                    break;
                }
                for (; y_cord <= b.length(); y_cord++) {
                    if (cords[x_cord + 1][y_cord] == 2){
                        break;
                    }
                    if (y_cord + 1 <= b.length() && cords[x_cord + 1][y_cord + 1] == 1) {
                        y_cord+=1;
                        break;
                    }
                }
            }
        }
        return ans;
    }
    public static String reverse(String in){
        String temp = "";
        for(int i = in.length()-1 ; i>0 ; i--){
            temp+=in.charAt(i);
        }
        return temp;
    }
    public static void main(String[] args) {
        String a = "ababdcbcc";
        String b = "aabdccccbd";
        Scanner input = new Scanner(System.in);
        String temp;
        String[] hold;
        int dummy ;
        int out = 0;
        //..............................
        while(true){
            try {
                dummy = 0;
                out = 0;
                temp = input.nextLine();
                hold = temp.split(" ");
                a = hold[0];
                b = hold[1];
                //-----------------------
                dummy = dp_lcs(a, b);
                if(out < dummy){
                    out = dummy;
                }
                dummy = dp_lcs(reverse(a), b);
                if(out < dummy){
                    out = dummy;
                }
                dummy = dp_lcs(a, reverse(b));
                if(out < dummy){
                    out = dummy;
                }
                dummy = dp_lcs(reverse(a), reverse(b));
                if(out < dummy){
                    out = dummy;
                }
                System.out.println(out*2);
            }
            catch(Exception e) {
                break;
            }
        }
    }
}

// codes that don't work for cyclic strings because of the bug ive mentioned :

//public class Main{
//    public static int lcs(String a, String b){
//        int[][] arr = new int[a.length() + 1][b.length() + 1];
//        for(int i = 0 ; i <= a.length() ; i++){
//            for(int j = 0 ; j <= b.length() ; j++ ){
//                if(i == 0 || j == 0){
//                    arr[i][j] = 0;
//                }
//                else{
//                    if(a.charAt(i-1) == b.charAt(j-1)){
//                        arr[i][j] = arr[i-1][j-1] + 1;
//                    }
//                    else{
//                        if(arr[i-1][j]> arr[i][j-1]){
//                            arr[i][j] =arr[i-1][j];
//                        }
//                        else {
//                            arr[i][j] = arr[i][j-1];
//                        }
//                    }
//                }
//            }
//        }
//        return arr[a.length()][b.length()];
//    }
//
//    public static int dp_lcs(String a, String b){
//        String temp;
//        if (a.length() < b.length()){
//            temp = a ;
//            a = b ;
//            b = temp;
//        }
//        a+=a;
//        boolean n = false;
//        temp = "";
//        int[][] arr = new int[a.length() + 1][b.length() + 1];
//        for(int i = 0 ; i <= a.length() ; i++){
//            for(int j = 0 ; j <= b.length() ; j++ ){
//                if(i == 0 || j == 0){
//                    arr[i][j] = 0;
//                }
//                else{
//                    if(a.charAt(i-1) == b.charAt(j-1)){
//                        arr[i][j] = arr[i-1][j-1] + 1;
//                    }
//                    else{
//                        if(arr[i-1][j]> arr[i][j-1]){
//                            arr[i][j] =arr[i-1][j];
//                            temp += (i-1 + " ");
//                        }
//                        else {
//                            arr[i][j] = arr[i][j-1];
//                        }
//                    }
//                }
//            }
//
//        }
//
//        System.out.println(temp);
//        return  1;
//    }
//    public static void main(String[] args){
//        dp_lcs("potterharry" , "plorppothaa");
//    }
//}


//public class Main{
//
//    public static int dp_lcs(String a, String b , int dummy){
//        if(dummy <= 3){
//            return 0;
//        }
//        else {
//            if(a.charAt(0) == b.charAt(0)){
//                if(a.length() == 1 || b.length() == 1){
//                    return  1 ;
//                }
//                else {
//                    return Math.max(dp_lcs(a.substring(1) , b,dummy) ,1 + dp_lcs(a.substring(1),b.substring(1),dummy -1));
//                }
//            }
//            else {
//                if(a.length() == 1 || b.length() == 1){
//                    return 0;
//                }
//                else {
//                    return Math.max(dp_lcs(a.substring(1) , b , dummy) , dp_lcs(a , b.substring(1) , dummy));
//                }
//            }
//        }
//    }
//
//
//    public static void main(String[] args){
//        String temp;
//        int dummy;
//        String a = "potterharry";
//        String b = "plorppothaa";
//        if(a.length() < b.length()){
//            temp = a;
//            a = b;
//            b = temp;
//        }
//        dummy = a.length();
//        a += a;
//        int ans = dp_lcs(a ,b , dummy);
//        System.out.println(ans);
//    }
//}