import java.io.*;
import java.util.*;

class attend {
  class list {
    String s[] = new String[13];
  }

  class save extends Thread {
    FileWriter fw;
    boolean exit = true;

    public void run() {
      while (exit) {
        try {
          FileWriter fw = new FileWriter("abc.txt");
          for (list l : a) {
            for (int i = 0; i < 13; i++) {
              fw.write(l.s[i] + "|");
            }
            fw.write("\n");
          }
          fw.close();
          Thread.sleep(1000);
        } catch (Exception e) {
        }
      }
    }

    public void stopRunning() {
      exit = false;
    }
  }

  static ArrayList<list> a = new ArrayList<list>();
  list l;
  int[] wd = { 23, 20, 22, 22, 21, 22, 23, 21, 22, 22, 21, 23 };
  String field[] = { "Roll no", "January", "February", "March", "April", "May", "June", "July", "August", "September",
      "October", "November", "December" };

  public attend() throws FileNotFoundException, IOException {
    BufferedReader br = new BufferedReader(new FileReader("abc.txt"));
    String line;
    StringTokenizer st;
    while ((line = br.readLine()) != null) {
      int i = 0;
      l = new list();
      st = new StringTokenizer(line, "|");
      while (st.hasMoreTokens()) {
        l.s[i] = st.nextToken("|");
        i++;
      }
      a.add(l);
    }
  }

  void addNew() {
    l = new list();
    Scanner sc = new Scanner(System.in);
    System.out.println("enter roll no");
    l.s[0] = sc.next();
    for (list l1 : a) {
      if (l.s[0].equals(l1.s[0])) {
        System.out.println("roll no already exists!");
        return;
      }
    }
    for (int i = 1; i < 13; i++) {
      System.out.println("Enter " + field[i] + "'s attendance");
      l.s[i] = sc.next();
      while (Integer.parseInt(l.s[i]) > wd[i - 1]) {
        System.out.println("attendance is greater than the no of working days.enter correct number!");
        l.s[i] = sc.next();
      }
    }
    a.add(l);
  }

  int search() {
    int j = equal();
    if (j != -1) {
      for (int i = 1; i < 13; i++) {
        System.out.println(field[i] + "'s attendance : " + a.get(j).s[i] + " out of " + wd[i - 1] + " days");
      }
    }
    return j;
  }

  void delete() {
    System.out.println("enter roll no to delete");
    a.remove(equal());
    System.out.println("removed the student");
  }

  void update() {
    Scanner sc = new Scanner(System.in);
    System.out.println("enter roll no to update");
    int i = search();
    while (i != -1) {
      System.out.println(
          "which month's atendance do you want to update?(enter single number) 1.January, 2.February, 3.March, 4.April, 5.May, 6.June, 7.July, 8.August, 9.September, 10.October, 11.November, 12.December, 13.none");
      int j = sc.nextInt();
      if (j > 0 && j < 13) {
        System.out.println("enter changed " + field[j]);
        a.get(i).s[j] = sc.next();
        while (Integer.parseInt(a.get(i).s[j]) > wd[j - 1]) {
          System.out.println("attendance is greater than the no of working days.enter correct number!");
          a.get(i).s[j] = sc.next();
        }
      } else if (j == 13)
        break;
      else
        System.out.println("enter valid number");
    }
  }

  void percentage() {
    System.out.println("enter roll number");
    double d = 0.00;
    int k = 0;
    int j = equal();
    if (j != -1) {
      for (int i = 1; i < 13; i++) {
        System.out.print("\n" + field[i] + "'s attendance in %: ");
        System.out.printf("%.2f", Integer.parseInt(a.get(j).s[i]) * 100.00 / wd[i - 1]);
        k += Integer.parseInt(a.get(j).s[i]);
      }
      System.out.print("\ncumulative attendance in %: ");
      System.out.printf("%.2f", k * 100.00 / 262);
    }
  }

  int equal() {
    Scanner sc = new Scanner(System.in);
    String i = sc.next();
    int k = 0;
    for (list l : a) {
      if (l.s[0].equals(i)) {
        k = 1;
        return a.indexOf(l);
      }
    }
    if (k == 0)
      System.out.println("no such roll number");
    return -1;
  }

  public void call() throws FileNotFoundException, IOException {
    save s = new save();
    Scanner sc = new Scanner(System.in);
    String ch = "n";
    s.start();
    while (ch.equals("n")) {
      System.out.println(
          "what do you want to do? 1.search contact 2.add new contact 3.update contact 4.calculate percentage 5.delete contact ");
      int i = sc.nextInt();
      if (i == 1) {
        System.out.println("enter roll number to search");
        this.search();
      }
      if (i == 2)
        this.addNew();
      if (i == 3)
        this.update();
      if (i == 4)
        this.percentage();
      if (i == 5)
        this.delete();
      System.out.println("want to exit?y/n");
      sc.nextLine();
      ch = sc.nextLine();
    }
    try {
      Thread.sleep(2000);
      s.stopRunning();
    } catch (Exception e) {
    }
  }
}
