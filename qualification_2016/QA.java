import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import java.util.Scanner;
import java.util.*;

public class QA {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numberOfTests = parseInt(sc.nextLine());
		for (int t = 1; t <= numberOfTests; t++) {
			long n = parseLong(sc.nextLine());
			long res = 0;
			Set<Character> chars = new HashSet<>();
			for (int i = 0; i <= 1000000; i++) {
				res = res + n;
				addChars(res, chars);
				if (chars.size() == 10) {
					break;
				}
			}
			String out = chars.size() == 10 ? Long.toString(res) : "INSOMNIA";
			System.out.println(String.format("Case #%s: %s", t, out));
		}
		sc.close();
	}
	public static void addChars(Long number, Set<Character> chars) {
		for (char c : number.toString().toCharArray()) {
			chars.add(c);
		}
	}
}
