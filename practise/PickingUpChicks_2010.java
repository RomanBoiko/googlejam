import static java.lang.Integer.parseInt;

import java.util.Scanner;

public class PickingUpChicks_2010 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numberOfTests = parseInt(sc.nextLine());
		for (int t = 1; t <= numberOfTests; t++) {
			String[] inputs = sc.nextLine().split(" ");
			int N=parseInt(inputs[0]);
			int K=parseInt(inputs[1]);
			int B=parseInt(inputs[2]);
			int T=parseInt(inputs[3]);
			String[] positions = sc.nextLine().split(" ");
			String[] speeds = sc.nextLine().split(" ");
			int out=0;
			int toPass=0;
			int elligible=0;
			if(K > 0) {
				for (int i = N-1; i >=0; i--) {
					int pos = parseInt(positions[i]);
					int speed = parseInt(speeds[i]);
					if (speed * T < (B-pos)) {
						toPass++;
					} else {
						out+=toPass;
						elligible++;
					}
					if (elligible==K) {
						break;
					}
				}
			}
			if (elligible < K) {
				System.out.println(String.format("Case #%s: IMPOSSIBLE", t));
			} else {
				System.out.println(String.format("Case #%s: %s", t, out));
			}
		}
		sc.close();
	}
}
