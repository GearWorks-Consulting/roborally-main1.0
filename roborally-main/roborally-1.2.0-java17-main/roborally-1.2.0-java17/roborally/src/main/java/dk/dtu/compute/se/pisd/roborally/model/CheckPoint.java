package dk.dtu.compute.se.pisd.roborally.model;
/**
 *
 * The CheckPoint class represents a checkpoint in the game.
 * Each checkpoint has an order number.
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *,
 */
public class CheckPoint {
    public int orderNo;
    /**
     * Constructs a new CheckPoint with the given order number.
     *
     * @param orderNo the order number of the checkpoint
     */
    public CheckPoint(int orderNo){
        this.orderNo=orderNo;
    }

    /**
     * Retrieves the order number of the checkpoint.
     *
     * @return the order number of the checkpoint
     */

    public int getOrderNo() {
        return orderNo;
    }
}
