package boundary;

import java.util.UUID;
import java.util.HashSet;

public class OrderIDGenerator {
    private static HashSet<String> generatedIDs = new HashSet<>();

    public static String generateOrderID() {
        String orderID = UUID.randomUUID().toString().replaceAll("-", "");

        // Check if the generated ID is unique, if not, regenerate
        while (generatedIDs.contains(orderID)) {
            orderID = UUID.randomUUID().toString().replaceAll("-", "");
        }

        // Add the generated ID to the set to ensure uniqueness
        generatedIDs.add(orderID);

        return orderID;
    }
}
// this should be somewhere in control!