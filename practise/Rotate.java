import static java.lang.Integer.parseInt;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

public class Rotate {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numberOfTests = parseInt(sc.nextLine());
		for (int t = 1; t <= numberOfTests; t++) {
			String[] testCase = sc.nextLine().split(" ");
			int N = parseInt(testCase[0]);
			int K = parseInt(testCase[1]);
			
			String padded = string(N, '.');
			List<String> table = new ArrayList<>(N);
			for (int r = 0; r<N; r++) {
				String part = sc.nextLine().replace(".", "");
				String res = (padded + part).substring(part.length());
				table.add(res);
			}
			
			boolean isB = isWinner('B', table, N, K);
			boolean isR = isWinner('R', table, N, K);
			String res = "Neither";
			if (isB && isR) res = "Both";
			else if (isB) res = "Blue";
			else if (isR) res = "Red";
			System.out.println(String.format("Case #%s: %s", t, res));
		}
		sc.close();
	}
	
	static boolean isWinner(char player, List<String> table, int N, int K) {
		String toFind = string(K, player);
		for (String col : table) {
			if (col.contains(toFind)) return true;
		}
		for (int row = 0; row<N; row++) {
			String res = "";
			for (String col : table) {
				res += col.charAt(row);
			}
			if (res.contains(toFind)) return true;
		}
		for (String col : diagonals(table, N)) {
			if (col.contains(toFind)) return true;
		}
		Collections.reverse(table);
		for (String col : diagonals(table, N)) {
			if (col.contains(toFind)) return true;
		}
		return false;
	}
	
	static String string(int len, char filler) {
		char[] chars = new char[len];
		Arrays.fill(chars, filler);
		return new String(chars);
	}
	
	static String[] diagonals(List<String> grid, int size) {
	    int nrows = size;
	    int ncols = size;
	    int nwords = size + size - 1;
	    String[] words = new String[nwords];
	
	    int row = 0;
	    int col = ncols - 1;
	
	    for (int iword = 0; iword < nwords; ++iword) {
	        int n = Math.min(nrows - row, ncols - col);
	        char[] word = new char[n];
	        for (int i = 0; i < n; ++i) {
	            word[i] = grid.get(row + i).charAt(col + i);
	        }
	        words[iword] = new String(word);
	
	        if (col > 0) {
	            --col;
	        } else {
	            ++row;
	        }
	    }
	    return words;
	}
}
