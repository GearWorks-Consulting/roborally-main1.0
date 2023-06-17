package dk.dtu.compute.se.pisd.roborally.model;
/**
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *
 */
public class CheckPoint {
    public int orderNo;
    public CheckPoint(int orderNo){
        this.orderNo=orderNo;
    }

    public int getOrderNo() {
        return orderNo;
    }
}
