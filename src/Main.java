import java.net.Inet4Address;
import java.util.*;

public class Main {

    public static void main(String[] args) {

    }
    //Google Coding Example
    public static String solutionForExample(String S, int K) {
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
    public static String[] morse =
            {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.",
                    "---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
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
    //LeetCode 950. Reveal Cards In Increasing Order
    public static int[] deckRevealedIncreasing(int[] deck) {
        Stack<Integer> sortedDeck = new Stack<>();
        int[] res = new int[deck.length];
        Arrays.sort(deck);
        for( int i = 0; i< deck.length; i++){
            sortedDeck.push(deck[deck.length - i -1]);
            if(i == deck.length -1 ){break;}

            //Last becomes First
            sortedDeck.push(sortedDeck.firstElement());
            sortedDeck.remove(sortedDeck.firstElement());
        }
        for(int i = 0; i < res.length; i++){
            res[i] = sortedDeck.pop();
        }
        return res;
    }
    //LeetCode 890. Find and Replace Pattern
    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        ArrayList<String> res = new ArrayList<>();
        for(String word : words){
            boolean match = true;
            HashMap<Character,Character> key2val = new HashMap<>();
            HashMap<Character,Character> val2key = new HashMap<>();
            for(int i = 0; i<pattern.length(); i++){

                Character key = pattern.charAt(i);
                Character val = word.charAt(i);

                if(key2val.containsKey(key) && ! (key2val.get(key) == val)){
                    match = false;
                    break;
                }
                if(val2key.containsKey(val) && ! (val2key.get(val) == key)){
                    match = false;
                    break;
                }
                key2val.put(key,val);
                val2key.put(val,key);
            }
            if(match){
                res.add(word);
            }

        }
        return res;
    }
    //LeetCode 797. All Paths From Source to Target
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return solve(graph, 0);
    }
    public static List<List<Integer>> solve(int[][] graph, int node) {
        List<List<Integer>> res = new ArrayList<>();

        if(node == graph.length -1){
            List<Integer> path = new ArrayList<>();
            path.add(node);
            res.add(path);
            return res;
        }
        for(int edge : graph[node]){
            List<List<Integer>> allPosiblePathsOfEdge = solve(graph,edge);
            for(List<Integer> path : allPosiblePathsOfEdge){
                path.add(0,node);
                res.add(path);
            }
        }
        return res;

    }
    //LeetCode 832. Flipping an Image
    public static int[][] flipAndInvertImage(int[][] A) {
        int [][] res = new int[A.length][A[0].length];
        for(int row = 0; row < A.length; row++){
            //Iterate Rows
            for(int i = A[row].length -1; i >= 0; i--){
                //Iterate indecies
                res[row][A[row].length - i -1] = (A[row][i] == 1) ? 0: 1; // condition ? then do this : else
            }
        }
        return res;

    }
    //LeetCode 657. Robot Return to Origin
    public static boolean judgeCircle(String moves) {
        int latteral = 0;
        int horizontal = 0;
        moves = moves.toUpperCase();
        for(int i = 0; i < moves.length(); i++){
            switch (moves.charAt(i)){
                case 'U':
                    latteral++;
                    break;
                case 'D':
                    latteral--;
                    break;
                case 'R':
                    horizontal++;
                    break;
                case 'L':
                    horizontal--;
                    break;
            }
        }
        return (latteral == 0) && (horizontal == 0);
    }
    //LeetCode 921. Minimum Add to Make Parentheses Valid
    public static int minAddToMakeValid(String S) {
        if(S.isEmpty()){return 0;}

        int totalNeeded = 0;
        int stack = 0;

        for(int i = 0; i< S.length(); i++){
            if(S.charAt(i) == '('){
                stack++;

            }else{
                if(stack == 0){
                    totalNeeded++;
                }else {
                    stack--;
                }
            }
        }

        return totalNeeded + stack;
    }
    //LeetCode 905. Sort Array By Parity
    public static int[] sortArrayByParity(int[] A) {
        int[] res = new int[A.length];
        int index_even = 0;
        int index_odd = A.length -1;
        for(int i : A){
            if(i % 2 == 0){
                //Even
                res[index_even++] = i;
            }else{
                //Odd
                res[index_odd--] = i;
            }
        }
        return res;
    }
    //LeetCode 961. N-Repeated Element in Size 2N Array
    public static int repeatedNTimes(int[] A) {
        Set<Integer> set = new HashSet<>();
        for(int a : A){
            if (set.contains(a)){
                return a;
            }
            set.add(a);
        }
        return 0;
    }
    //LeetCode 654. Maximum Binary Tree
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums.length == 1) return new TreeNode(nums[0]);

        int max = Integer.MIN_VALUE;
        int max_index = 0;
        for(int i = 0 ; i< nums.length ; i++){
            if(nums[i] > max){
                max = nums[i];
                max_index = i;
            }
        }

        TreeNode node = new TreeNode(max);

        node.left = (max_index != 0) ?
                constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, max_index )) : null;
        node.right = (max_index != nums.length - 1)?
                constructMaximumBinaryTree(Arrays.copyOfRange(nums, max_index + 1 , nums.length)) : null;

        return node;
    }
    //LeetCode 763. Partition Labels
    public static List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        StringBuilder pattern = new StringBuilder();

        for(int i = 0; i < S.length(); i++){
            pattern.append(S.charAt(i));

            if(!S.substring(i + 1, S.length()).matches(".*["+ pattern.toString() +"].*")){
                res.add(pattern.length());
                pattern = new StringBuilder();
            }
        }
        return res;
    }
    //LeetCode 791. Custom Sort String
    public static String customSortString(String S, String T) {
        int og_length = T.length();

        for(int i = S.length() -1 ; i >= 0; i--){
            String pattern = String.valueOf(S.charAt(i));

            if(T.contains(pattern)){
                T = T.replaceAll(pattern,"");
                StringBuilder workingString = new StringBuilder(T);
                while (workingString.length() < og_length){
                    workingString.insert(0,pattern);
                }

                T = workingString.toString();
            }
        }
        return T;
    }
    //LeetCode 557. Reverse Words in a String III
    public static String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        String[] words = s.split(" ");

        for(int i = 0; i < words.length; i++){
            StringBuilder word = new StringBuilder(words[i]).reverse();
            if(i != words.length -1){
                word.append(" ");
            }
            res.append(word.toString());
        }
        return res.toString();
    }
    //LeetCode 461. Hamming Distance
    public static int hammingDistance(int x, int y) {
        String longBit;
        StringBuilder shortBit;
        int res = 0;


        shortBit = new StringBuilder(Integer.toBinaryString((x<y)? x : y));
        longBit = Integer.toBinaryString((x>=y)? x : y);

        while (shortBit.length() < longBit.length()){
            shortBit.insert(0,"0");
        }

        for(int i = 0; i < longBit.length() ; i++){
            if(longBit.charAt(i) != shortBit.charAt(i)){
                res++;
            }
        }
        return  res;
    }
    //LeetCode 944. Delete Columns to Make Sorted
    public static int minDeletionSize(String[] A) {
        int res = 0;
        for(int col = 0; col< A[0].length(); col++){
            for (int row = 0; row < A.length-1 ; row++){

                if(A[row].charAt(col) > A[row+1].charAt(col)){
                    res++;
                    break;
                }
            }
        }
        return res;
    }
    //LeetCode 894. All Possible Full Binary Trees
    public static List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> list = new ArrayList<>();
        if (N == 1){
            list.add(new TreeNode(0));
            return list;
        }

        for(int i = 1; i <= N -2; i+=2){
            List<TreeNode> leftNodes = allPossibleFBT(i);
            List<TreeNode> rightNodes = allPossibleFBT(N - i -1);
            for(TreeNode leftNode : leftNodes){
                for(TreeNode rightNode: rightNodes){
                    TreeNode root = new TreeNode(0);
                    root.left = leftNode;
                    root.right = rightNode;

                    list.add(root);
                }
            }
        }
        return list;
    }
    //LeetCode 852. Peak Index in a Mountain Array
    public int peakIndexInMountainArray(int[] A) {
        for(int i = 0; i < A.length; i++){
            //No peek Found, thus the end has to be the peek
            if(i == A.length-1) return i;

            if(A[i] > A[i+1]){
                //Following int is lower, Thus we found the peek
                return i;
            }
        }
        //Will Never be reached
        return 0;
    }
}
