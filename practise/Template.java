import static java.lang.Integer.parseInt;

import java.util.Scanner;

public class Template {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numberOfTests = parseInt(sc.nextLine());
		for (int t = 1; t <= numberOfTests; t++) {
			int out = 0;
			System.out.println(String.format("Case #%s: %s", t, out));
		}
		sc.close();
	}
}
