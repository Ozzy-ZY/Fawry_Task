package services;

import models.contracts.Shippable;

import java.util.ArrayList;

public class ShippingService {
    private static final double FEE_PER_KG = 10;
    public static double ProcessShipping(ArrayList<Shippable> shippableItems, ArrayList<Integer> quantites){
        if(shippableItems.isEmpty())
            return 0;
        System.out.println("** Shipment notice **");
        double totalWeight = 0.0d;
        for(int i = 0; i < shippableItems.size(); i++){
            Shippable item = shippableItems.get(i);
            System.out.println(quantites.get(i)+"x "+ item.getName()+"\t" + item.getWeight() + "g");
            totalWeight += item.getWeight() * quantites.get(i);
        }
        System.out.println("Total Package Weight "+ totalWeight/1000 + "kg");
        return calculateShippingFee(totalWeight);
    }
    private static double calculateShippingFee(double totalWeight){
        return totalWeight/1000 * FEE_PER_KG;
    }
}
