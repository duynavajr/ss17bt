package ra.bt2;

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Nhập vào đường dẫn cho tập tin nguồn: ");
        String sourcePath = sc.nextLine();
        System.out.print("Nhập vào đường dẫn cho tập tin đích: ");
        String destinationPath = sc.nextLine();
        try {
            Files.copy(Paths.get(sourcePath), Paths.get(destinationPath));
        } catch (NoSuchFileException e) {
            System.out.println("Không thể tiến hành việc sao chép do đường dẫn tập tin nguồn không đúng. Vui lòng kiểm tra lại");
            main(args);
        } catch (FileAlreadyExistsException e) {
            System.out.println("Không thể tiến hành việc sao chép do tệp tin đích đã tồn tại. Vui lòng kiểm tra lại");
            main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
