import java.util.*;

// Custom Exception
class InvalidDataException extends Exception {
    InvalidDataException(String msg) {
        super(msg);
    }
}

// Account Class (Student)
class Account {
    int accId;
    String accName;
    String accEmail;

    Account(int id, String name, String email) {
        this.accId = id;
        this.accName = name;
        this.accEmail = email;
    }

    public String toString() {
        return accId + " | " + accName + " | " + accEmail;
    }
}

// CourseUnit Class (Course)
class CourseUnit {
    int unitId;
    String unitName;
    double unitFee;

    CourseUnit(int id, String name, double fee) throws InvalidDataException {
        if (fee < 0) {
            throw new InvalidDataException("Fee cannot be negative!");
        }
        this.unitId = id;
        this.unitName = name;
        this.unitFee = fee;
    }

    public String toString() {
        return unitId + " | " + unitName + " | ₹" + unitFee;
    }
}

// Runnable (instead of Thread)
class ProcessTask implements Runnable {
    int accId, unitId;

    ProcessTask(int a, int u) {
        this.accId = a;
        this.unitId = u;
    }

    public void run() {
        System.out.println(">> Processing registration for Account " + accId);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Thread issue");
        }
        System.out.println(">> Completed: " + accId + " -> " + unitId);
    }
}

// Core Logic Class
class Engine {

    Map<Integer, Account> accountDB = new HashMap<>();
    Map<Integer, CourseUnit> courseDB = new HashMap<>();
    Map<Integer, List<Integer>> registrationMap = new HashMap<>();

    void addAccount(Account a) throws InvalidDataException {
        if (accountDB.containsKey(a.accId)) {
            throw new InvalidDataException("Account already exists!");
        }
        accountDB.put(a.accId, a);
    }

    void addCourse(CourseUnit c) throws InvalidDataException {
        if (courseDB.containsKey(c.unitId)) {
            throw new InvalidDataException("Course already exists!");
        }
        courseDB.put(c.unitId, c);
    }

    void register(int accId, int unitId) throws InvalidDataException {

        if (!accountDB.containsKey(accId)) {
            throw new InvalidDataException("Invalid Account ID");
        }
        if (!courseDB.containsKey(unitId)) {
            throw new InvalidDataException("Invalid Course ID");
        }

        registrationMap.putIfAbsent(accId, new ArrayList<>());
        registrationMap.get(accId).add(unitId);

        new Thread(new ProcessTask(accId, unitId)).start();
    }

    void showAccounts() {
        accountDB.values().forEach(System.out::println);
    }

    void showRegistrations() {
        for (int id : registrationMap.keySet()) {
            System.out.println("Account " + id + ":");

            double total = 0;

            for (int cid : registrationMap.get(id)) {
                CourseUnit c = courseDB.get(cid);
                if (c != null) {
                    System.out.println("  -> " + c.unitName);
                    total += c.unitFee;
                }
            }

            System.out.println("  Total Fee: ₹" + total); // UNIQUE FEATURE
        }
    }
}

// Main Class
public class CampusFlow {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Engine engine = new Engine();

        while (true) {
            try {
                System.out.println("\n=== MENU ===");
                System.out.println("1. Add Account");
                System.out.println("2. Add Course");
                System.out.println("3. Register");
                System.out.println("4. View Accounts");
                System.out.println("5. View Registrations");
                System.out.println("6. Exit");

                int ch = sc.nextInt();

                switch (ch) {

                    case 1:
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        engine.addAccount(new Account(id, name, email));
                        break;

                    case 2:
                        System.out.print("Course ID: ");
                        int cid = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Course Name: ");
                        String cname = sc.nextLine();

                        System.out.print("Fee: ");
                        double fee = sc.nextDouble();

                        engine.addCourse(new CourseUnit(cid, cname, fee));
                        break;

                    case 3:
                        System.out.print("Account ID: ");
                        int aid = sc.nextInt();

                        System.out.print("Course ID: ");
                        int coid = sc.nextInt();

                        engine.register(aid, coid);
                        break;

                    case 4:
                        engine.showAccounts();
                        break;

                    case 5:
                        engine.showRegistrations();
                        break;

                    case 6:
                        System.out.println("Bye...");
                        return;

                    default:
                        System.out.println("Invalid choice");
                }

            } catch (InvalidDataException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Wrong input!");
                sc.nextLine();
            }
        }
    }
}