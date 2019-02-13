import java.util.*;

public class Main {

    public static void main(String[] args) {

    }

    //Google Coding Example
    public static String solution(String S, int K) {
        S = S.replaceAll("-","");
        S = S.toUpperCase();
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < S.length(); i++){
            if(i % K == 0 && i != 0) {
                result.insert(0,"-");
            }
            result.insert(0,S.charAt(S.length()-1 - i));
        }
        return result.toString();
    }
    //LeetCode 771. Jewels and Stones
    public static int numJewelsInStones(String J, String S) {
        if(J.isEmpty()){return 0;}

        int numberOfStones = S.length();
        J = "["+J+"]";
        S = S.replaceAll(J,"");

        return numberOfStones - S.length();

    }
    //LeetCode 929. Unique Email Addresses
    public static int numUniqueEmails(String[] emails) {
        HashSet<String> set = new HashSet<>();

        for(String email : emails){
            String[] cutmail = email.split("@");

            cutmail[0] = cutmail[0].replaceAll("\\.", "");
            cutmail[0] = cutmail[0].replaceAll( "\\+.*", "");

            set.add(cutmail[0] + "@" +cutmail[1]);
        }

        return set.size();
    }
    //LeetCode 804. Unique Morse Code Words
    public static String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
    public static int uniqueMorseRepresentations(String[] words) {
        HashSet<String> set = new HashSet<>();
        for (String word : words){
            StringBuilder morseTranlation = new StringBuilder();
            for(int i = 0; i < word.length(); i++){
                morseTranlation.append(morse[Character.getNumericValue(word.charAt(i))-10]);
            }
            set.add(morseTranlation.toString());
        }

        return set.size();
    }
    //LeetCode 807. Max Increase to Keep City Skyline
    public static int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] max_x_hights = new int[grid.length];
        int[] max_y_hights = new int[grid[0].length];

        for(int x = 0; x < grid.length; x++){
            int biggest = -1;
            for(int y = 0 ; y < grid[0].length; y++){
                if(grid[x][y] > biggest){
                    biggest = grid[x][y];
                }
            }
            max_x_hights[x] = biggest;
        }

        for(int y = 0; y< grid[0].length; y++){
            int biggest = -1;
            for(int x = 0 ; x < grid.length; x++){
                if(grid[x][y] > biggest){
                    biggest = grid[x][y];
                }
            }
            max_y_hights[y] = biggest;
        }

        int storiesadded = 0;

        for(int x = 0; x < grid.length; x++){
            for(int y = 0 ; y < grid[0].length; y++){
               if(max_x_hights[x] > max_y_hights[y]){
                   storiesadded += max_y_hights[y] - grid[x][y];
               }else{
                   storiesadded += max_x_hights[x] - grid[x][y];
               }
            }
        }
        return storiesadded;
    }
    //LeetCode 938. Range Sum of BST
    public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }
    public static int rangeSumBST(TreeNode root, int L, int R) {
        int result = 0;
        if (root.val >= L && root.val <= R) {
            result += root.val;
        }
        if (root.val >= L && root.left != null) {
            result += rangeSumBST(root.left, L, R);
        }
        if (root.val <= R && root.right != null) {
            result += rangeSumBST(root.right, L, R);
        }
        return result;

    }
    //LeetCode 701. Insert into a Binary Search Tree
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            root = new TreeNode(val);
            return root;
        }
        if(val > root.val){
            root.right = insertIntoBST(root.right,val);
        }else {
            root.left = insertIntoBST(root.left,val);
        }
        return root;
    }
    //LeetCode 977. Squares of a Sorted Array
    public static int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];

        for(int i = 0; i< A.length; i++){
            res[i] = (int) Math.pow(A[i],2);
        }
        Arrays.sort(res);
        return res;
    }
}
