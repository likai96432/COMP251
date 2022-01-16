

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class a1_real {

    public static int silence(int[] positions) {
        HashMap<Integer, ArrayList<Integer>> table = new HashMap<>();
        int numOfStudents = positions.length, ans = Integer.MAX_VALUE;
        for (int i = 0; i < numOfStudents; i++) insert(table, positions[i], i);
        for (ArrayList<Integer> p : table.values()) ans = Math.min(countMinimumDistance(p, numOfStudents), ans);
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    private static int countMinimumDistance(ArrayList<Integer> positions, int numOfStudents) {
        int size = positions.size(), ans = Integer.MAX_VALUE;
        if (size == 1) ans = numOfStudents;
        else if (size != 0) for (int i = 1; i < size; i++) ans = Math.min(ans, positions.get(i) - positions.get(i - 1));
        return ans;
    }

    private static void insert(HashMap<Integer, ArrayList<Integer>> table, int language, int position) {
        if (table.containsKey(language)) table.get(language).add(position);
        else table.put(language, new ArrayList<Integer>() {{
            add(position);
        }});
    }

    public static void main(String[] args) {
        try {
            String path = args[0];
            File myFile = new File(path);
            Scanner sc = new Scanner(myFile);
            int num_students = sc.nextInt();
            int[] positions = new int[num_students];
            for (int student = 0; student < num_students; student++) {
                positions[student] = sc.nextInt();
            }
            sc.close();
            int answer = silence(positions);
            System.out.println(answer);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}