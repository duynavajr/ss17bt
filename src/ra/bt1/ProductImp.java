package ra.bt1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ProductImp {
    public static Scanner sc = new Scanner(System.in);
    public static List<Product> products = new ArrayList<>();

    public static void main(String[] args) throws IOException {
//        load products data into program
        loadProducData();
        do {
            System.out.println("*************QUẢN LÝ SẢN PHẨM*************");
            System.out.println("1. Thêm thông tin sản phẩm");
            System.out.println("2. Hiển thị danh sách các sản phẩm");
            System.out.println("3. Tìm kiếm thông tin sản phẩm");
            System.out.println("4. Thoát");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addProducts();
                    break;
                case 2:
                    showProducts();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    sc.close();
                    System.exit(0);
                default:
                    break;
            }
        } while (true);

    }

    private static void loadProducData() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data.dat"))) {
            products = (List<Product>) objectInputStream.readObject();
            System.out.println("Đã nạp dữ liệu từ file");
            for (Product product: products) {
                System.out.println(product);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void addProducts() throws IOException {
        Product product = new Product();
        System.out.println("Nhập vào mã sản phẩm: ");
        product.setProductId(Integer.parseInt(sc.nextLine()));
        System.out.println("Nhập vào tên sản phẩm: ");
        product.setProductName(sc.nextLine());
        System.out.println("Nhập vào hãng sản xuất: ");
        product.setBrand(sc.nextLine());
        System.out.println("Nhập vào giá sản phẩm: ");
        product.setPrice(Float.parseFloat(sc.nextLine()));
        System.out.println("Nhập vào mô tả sản phẩm: ");
        product.setDescription(sc.nextLine());
        products.add(product);
        // save product data into file
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            objectOutputStream.writeObject(products);
            System.out.println("Đã xuất file dữ liệu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }
    private static void searchProduct() {
        System.out.println("Nhập vào tên sản phẩm muốn tìm kiếm");
        String searchTerm = sc.nextLine();
        for (Product product : products) {
            if (product.getProductName().trim().contains(searchTerm.trim())) {
                System.out.println(product);
            }
        }
    }
}
