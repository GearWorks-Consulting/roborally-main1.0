package dk.dtu.compute.se.pisd.roborally.client;

import dk.dtu.compute.se.pisd.roborally.JSON.BoardTemplate;
import dk.dtu.compute.se.pisd.roborally.JSON.LoadBoard;
import dk.dtu.compute.se.pisd.roborally.JSON.PlayerTemplate;
import dk.dtu.compute.se.pisd.roborally.JSON.SpaceTemplate;
import dk.dtu.compute.se.pisd.roborally.model.*;

import java.util.*;
/**
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *
 */
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
                System.out.println("\t4. get boolean Test");
                System.out.println("\t5. get setBool");
                System.out.println("\t6. EXIT");
                System.out.print("Enter option:");
                valg = scan.nextInt();
                switch (valg) {
                    case 1: {

                        BoardTemplate boardTemplate= new BoardTemplate(4,5);

                        PlayerTemplate playerTemplate= new PlayerTemplate();
                        boardTemplate.players.add(playerTemplate);
                        boardTemplate.gameId=2;
                        boardTemplate.players.get(0).name="Moiz";
                        SpaceTemplate space= new SpaceTemplate();
                        space.x=1;
                        space.y=1;

                        boardTemplate.players.get(0).space=space;
                        System.out.println(boardTemplate.players.get(0).name);
                        boardTemplate.players.get(0).cards[0].card=new CommandCard(Command.FORWARD);
                        System.out.println(boardTemplate.players.get(0).cards[0].card);
                        ProductClient.saveBoard(boardTemplate,"test2");
                    }
                    break;
                    case 2: {

                        BoardTemplate board2 = ProductClient.loadBoard("test2");
                        System.out.println(board2.players.get(0).cards[0].card);
                        Board board = new Board(2,2);
                        Player player=new Player(board,null,"aliBaBa");
                        board.players.add(player);
                        board.setCurrentPlayer(player);

                        LoadBoard.upDateBoard(board2,board);

                        System.out.println(board.getPlayer(0).getCardField(0).getCard());
                        //  System.out.println(board2.current.name);
                        // System.out.println(board.getCurrentPlayer().getName());
                        //System.out.println(board2.players.get(0).name);
                        //System.out.println(board.getPlayer(0).getName());


                    }
                    break;
                    case 3: {
                        //System.out.println(ProductClient.printTest());
                        ProductClient.updatePlayerJoinedCounter("5");
                        System.out.println(ProductClient.getPlayerCounter());
                        //ProductClient.updateMaxPlayers("3");
                        //int maxPlayers = Integer.parseInt(ProductClient.getMaxPlayers());
                        //System.out.println("Max Players: " + maxPlayers);
                        //System.out.println(ProductClient.updateMaxPlayers("3"));
                    }
                    break;
                    case 4: {
                        //System.out.println(ProductClient.isCompleteMove());
                        //System.out.println(ProductClient.getPlayerTurn());
                    }
                    break;
                    case 5: {
                        //System.out.println(ProductClient.setCompleteMove("true"));

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
            System.out.println(e);
        }
    }
}