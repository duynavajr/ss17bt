package ra.bt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

            List<User> users = new ArrayList<>();
            users.add(new User(1, "navaduy", "123456", true));


        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("user.dat"));
        ) {
            for (User user : users) {
                objectOutputStream.writeObject(user);
            }
        } catch (IOException e) {
            System.out.println("Đã hoàn tất việc ghi file");
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("user.dat"))) {
            while (true) {
                System.out.println(objectInputStream.readObject());
            }
        } catch (IOException e) {
            System.out.println("Đã hoàn tất việc đọc file");
        } catch (ClassNotFoundException e) {
            System.out.println("Kiểu dữ liệu không đúng");
        }
        long fileSize = 0;
        File file = new File("user.dat");
        if (file.exists()) {
            fileSize = file.length();
        } else {
            System.out.println("Tệp không tồn tại");
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("user.dat"));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("user.txt"));){
//            file => stream (byte)
//            stream => file (character)
            while (true) {
                bufferedWriter.write(objectInputStream.readObject().toString());
            }

        } catch (IOException e) {
            System.out.println("Đã hoàn tất việc đọc file");
            System.out.println("File có kích thước là " + fileSize + " bytes");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
