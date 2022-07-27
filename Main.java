import java.util.Scanner;

class Main {
  public static void main(String a[]) throws Exception {
    int i = 2;
    attend A = new attend();
    student S = new student();
    marks M = new marks();
    // Scanner s=new Scanner(System.in);
    while (true) {
      System.out.println(
          "what do you want to do? 1.operate on student record 2.operate on marks 3.operate on attendance 4.exit");
      i = new Scanner(System.in).nextInt();
      // System.out.println(Integer.parseInt(s.nextLine()));
      if (i == 1)
        S.call();
      if (i == 2)
        M.call();
      if (i == 3)
        A.call();
      if (i == 4)
        System.exit(0);
    }
  }
}
