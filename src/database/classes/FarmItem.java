package database.classes;

public class FarmItem {
    private String name;
    private boolean isApproved;
    private ItemType type;

    public enum ItemType{
        ANIMAL, NUT, FRUIT, FLOWER, VEGETABLE
    }

    public static ItemType stringToItemType(String s){
        if (s.equals("ANIMAL"))
            return ItemType.ANIMAL;
        if (s.equals("NUT"))
            return ItemType.NUT;
        if (s.equals("FRUIT"))
            return ItemType.FRUIT;
        if (s.equals("FLOWER"))
            return ItemType.FLOWER;
        if (s.equals("VEGETABLE"))
            return ItemType.VEGETABLE;
        return null;
    }

    public FarmItem(String name, boolean isApproved, ItemType type) {
        this.name = name;
        this.isApproved = isApproved;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
