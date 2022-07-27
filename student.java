import java.io.*;
import java.util.*;

class student {
  class list {
    String s[] = new String[6];
  }

  class save extends Thread {
    FileWriter fw;
    boolean exit = true;

    public void run() {
      while (exit) {
        try {
          FileWriter fw = new FileWriter("student.txt");
          for (list l : a) {
            for (int i = 0; i < 6; i++) {
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
  String field[] = { "Roll no.", "Name", "Branch", "Email", "Phone no.", "Location" };

  student() throws FileNotFoundException, IOException {
    BufferedReader br = new BufferedReader(new FileReader("student.txt"));
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
    System.out.println("enter roll no: ");
    l.s[0] = sc.next();
    for (list l1 : a) {
      if (l.s[0].equals(l1.s[0])) {
        System.out.println("roll no already exists!");
        return;
      }
    }
    for (int i = 1; i < 6; i++) {
      System.out.println("Enter " + field[i]);
      l.s[i] = sc.next();
    }
    a.add(l);
  }

  int search() {
    int j = equal();
    if (j != -1) {
      for (int i = 0; i < 6; i++) {
        System.out.println(field[i] + " : " + a.get(j).s[i]);
      }
    }
    return j;
  }

  void delete() {
    System.out.println("enter roll no to delete");
    a.remove(equal());
    System.out.println("removed the contact");
  }

  void update() {
    Scanner sc = new Scanner(System.in);
    System.out.println("enter roll no to update");
    int i = search();
    while (i != -1) {
      System.out.println(
          "which field do you want to change?(enter single number)1.Name, 2.Branch, 3.Email, 4.Phone no., 5.Location, 6.none");
      int j = sc.nextInt();
      if (j > 0 && j < 6) {
        System.out.println("enter changed " + field[j]);
        a.get(i).s[j] = sc.next();
      } else if (j == 6)
        break;
      else
        System.out.println("enter valid number");
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

  void call() throws FileNotFoundException, IOException {
    save s = new save();
    Scanner sc = new Scanner(System.in);
    String ch = "n";
    s.start();
    while (ch.equals("n")) {
      System.out
          .println("what do you want to do? 1.search contact 2.add new contact 3.update contact 4.delete contact ");
      int i = sc.nextInt();
      if (i == 1) {
        System.out.println("enter roll number to search");
        search();
      }
      if (i == 2)
        addNew();
      if (i == 3)
        update();
      if (i == 4)
        delete();
      System.out.println("want to exit?y/n");
      sc.nextLine();
      ch = sc.nextLine();
    }
    s.stopRunning();
  }
}
