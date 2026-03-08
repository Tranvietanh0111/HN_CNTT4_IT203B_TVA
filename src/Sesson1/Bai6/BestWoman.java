package Sesson1.Bai6;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BestWoman {
    private String id;
    private String fullName;
    private Date dateOfBirth;

    public BestWoman() {
    }

    public BestWoman(String id, String fullName, Date dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void inputData(Scanner sc) {
        // nhập id
        this.id = inputId(sc);
        // nhập fullname
        this.fullName = inputFullName(sc);
        // nhập ngày sinh
        this.dateOfBirth = inputDateOfBirth(sc);
    }

    public Date inputDateOfBirth(Scanner sc) {
        do {
            System.out.println("Nhập ngày sinh: ");
            String date = sc.nextLine();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateOfBirth = null;
            try {
                dateOfBirth = simpleDateFormat.parse(date);
            } catch (ParseException e) {
                System.err.println("Vui lòng nhập đúng định dạng dd/MM/yyyy");
            }
            return dateOfBirth;
        }while (true);
    }

    public String inputFullName(Scanner sc) {
        do {
            System.out.println("Nhập họ và tên: ");
            String fullName = sc.nextLine();
            String regex = "[a-zA-Z\\s]+";
            if(fullName.matches(regex)) {
                return fullName;
            }
            System.err.println("Vui lòng nhập đúng định dạng của họ và tên");
        }while (true);
    }

    public String inputId(Scanner sc) {
        do {
            System.out.println("Nhập vào id: ");
            String newId = sc.nextLine();
            // id: SV001
            String regex = "SV\\d{3}";
            if(newId.matches(regex)) {
                return newId;
            }
            System.err.println("Vui lòng nhập đúng định dạng");
        } while (true);
    }

    public void displayData() {
        System.out.println(id);
        System.out.println(fullName);
        System.out.println(dateOfBirth);
    }
}
