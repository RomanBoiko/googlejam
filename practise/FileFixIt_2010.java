import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileFixIt_2010 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numberOfTests = Integer.parseInt(sc.nextLine());
		for (int i = 1; i <= numberOfTests; i++) {
			String[] ss = sc.nextLine().split(" ");
			int readyDirsN = Integer.parseInt(ss[0]);
			int todoDirs = Integer.parseInt(ss[1]);
			Set<String> readyDirs = new HashSet<>();
			for (int ex = 0; ex < readyDirsN; ex++) {
				readyDirs.add(sc.nextLine());
			}
			int mkdirs = 0;
			for (int td = 0; td < todoDirs; td++) {
				String[] dirsToFind = sc.nextLine().split("/");
				String cd = "";
				for (int j = 1; j < dirsToFind.length; j++) {
					cd += "/" + dirsToFind[j];
					if (!readyDirs.contains(cd)) {
						mkdirs++;
						readyDirs.add(cd);
					}
				}
			}
			System.out.println(String.format("Case #%s: %s", i, mkdirs));
		}
		sc.close();
	}
}
