package dk.dtu.compute.se.pisd.roborally.client;

import dk.dtu.compute.se.pisd.roborally.JSON.BoardTemplate;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            int valg = 0;
            Scanner scan = new Scanner(System.in);
            //IProductService ps = new exercise12.ProductClient();

            while (valg != 6) {
                System.out.println("MENU TEST RESTFull API");
                System.out.println("\t1. Send board");
                System.out.println("\t2. Get board");
                System.out.println("\t3. Print test line: Test");
                System.out.println("\t6. EXIT");
                System.out.print("Enter option:");
                valg = scan.nextInt();
                switch (valg) {
                    case 1: {

                       ProductClient.saveBoard(new BoardTemplate(4,5),"Test2");


                    }
                    break;
                    case 2: {

                        BoardTemplate board = ProductClient.loadBoard("Test2");
                        System.out.println(board.phase);
                        System.out.println(board.spaces[0][1].x);



                    }
                    break;
                    case 3: {
                        System.out.println(ProductClient.printTest());
                    }
                    break;
                    case 4: {
                        //exercise12.Product p = new exercise12.Product(200, "Micro Owen", 350.0);
                        //boolean success = ps.updateProduct(100, p);
                        //System.out.println(success ? "Updated product 100" : "Failed to update!");
                    }
                    break;
                    case 5: {
                        //boolean success = ps.deleteProductById(100);
                        //System.out.println(success ? "Deleted product 100" : "Failed to delete!");
                    }
                    break;
                    case 6:
                        //System.out.println("Bye");
                        break;
                    default:
                        //System.out.println("Invalid option");
                        break;
                }
            }
            scan.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}