import java.io.*;
import java.util.*;
import java.lang.Thread;

class marks {

	class list {
		long s[][];
		char[] g;

		public list() {
			s = new long[3][6];
			g = new char[5];
		}
	}

	class thread extends Thread {
		ArrayList<list> a;
		boolean exit = true;

		thread(ArrayList<list> a) {
			this.a = a;
		}

		public void run() {
			while (exit) {
				try {
					FileWriter fw = new FileWriter("file1.txt");
					for (list l : a) {
						for (int i = 0; i < 3; i++) {
							for (int k = 0; k < 6; k++) {
								fw.write(l.s[i][k] + "|");
							}
							fw.write("\n");
						}
						fw.write(l.s[0][0] + "|");
						for (int i = 0; i < 5; i++)
							fw.write(l.g[i] + "|");
						fw.write("\n");
					}
					fw.close();
					Thread.sleep(2000);
				} catch (Exception e) {
				}
			}
		}

		void stoprunning() {
			exit = false;
		}
	}

	static ArrayList<list> a = new ArrayList<list>();
	static list l;
	static String field[] = { "DBMS      ", "OS        ", "SE        ", "SS        ", "Psychology" };

	static void getMarks(long r) {
		list m = null;
		for (list i : a) {
			if (i.s[0][0] == r) {
				m = i;
				break;
			}
		}
		if (m == null) {
			System.out.println("no such entry");
			return;
		}
		int j = 0;
		System.out.println("           --Mid1-- --Mid2--  --Sem-- --grade--");
		for (int i = 1; i < 6; i++) {
			System.out.print(field[j++] + ": ");
			for (int k = 0; k < 3; k++) {
				System.out.print(m.s[k][i] + "           ");
			}
			System.out.println(m.g[i - 1]);
		}
	}

	static void grades(list st, long roll) {
		for (int i = 1; i < 6; i++) {
			int grade = (int) ((st.s[0][i] + st.s[1][i]) / 2 + st.s[2][i]) / 10;
			switch (grade) {
				case 9:
					st.g[i - 1] = 'S';
					break;
				case 8:
					st.g[i - 1] = 'A';
					break;
				case 7:
					st.g[i - 1] = 'B';
					break;
				case 6:
					st.g[i - 1] = 'C';
					break;
				case 5:
					st.g[i - 1] = 'D';
					break;
				case 4:
					st.g[i - 1] = 'E';
					break;
				default:
					st.g[i - 1] = 'F';
			}
		}
	}

	static void check(long r) {
		for (list l : a) {
			if (l.s[0][0] == r)
				a.remove(l);
		}
	}

	void call() throws FileNotFoundException, IOException {
		thread t = new thread(a);
		t.start();
		long r;
		boolean p = true;
		try {
			Scanner sc = new Scanner(System.in);
			while (p) {
				System.out.println("1.Enter marks\n2. get Marks\n3.exit");
				int c = sc.nextInt();
				switch (c) {
					case 1:
						System.out.println("Enter roll no: ");
						r = sc.nextLong();
						check(r);
						list st = new list();
						st.s[0][0] = r;
						st.s[1][0] = r;
						st.s[2][0] = r;
						System.out.println("Enter mid1 marks:");
						for (int i = 1; i < 6; i++) {
							System.out.println(field[i - 1] + ": ");
							st.s[0][i] = sc.nextInt();
						}
						System.out.println("Enter mid2 marks:");
						for (int i = 1; i < 6; i++) {
							System.out.println(field[i - 1] + ": ");
							st.s[1][i] = sc.nextInt();
						}
						System.out.println("Enter final marks:");
						for (int i = 1; i < 6; i++) {
							System.out.println(field[i - 1] + ": ");
							st.s[2][i] = sc.nextInt();
						}
						grades(st, r);
						a.add(st);
						break;
					case 2:
						System.out.println("Enter roll no: ");
						r = sc.nextLong();
						getMarks(r);
						break;
					case 3:
						p = false;
						/*
						 * try{
						 * Thread.sleep(2000);
						 * t.stoprunning();}
						 * catch(Exception e){}
						 */
						break;
					default:
						System.out.println("choose again");
				}
			}
		} catch (Exception e) {
			System.out.println("IOException");
		}
	}

}
