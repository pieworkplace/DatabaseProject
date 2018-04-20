package database.classes;

public class Property {
    private int ID;
    private String name;
    private double size;
    private boolean isCommercial;
    private boolean isPublic;
    private String city;
    private String street;
    private int zip;
    private PropertyType propertyType;
    private String owner;
    private String approvedBy;
    private double avg_rating;

    public enum PropertyType{
        GARDEN, FARM, ORCHARD
    }

    public static PropertyType stringToPropertyType(String s){
        if (s.equals("GARDEN"))
            return PropertyType.GARDEN;
        if (s.equals("FARM"))
            return PropertyType.FARM;
        if (s.equals("ORCHARD"))
            return PropertyType.ORCHARD;
        return null;
    }

    public Property(int ID, String name, double size, boolean isCommercial, boolean isPublic, String city, String street, int zip, PropertyType propertyType, String owner, String approvedBy) {
        this.ID = ID;
        this.name = name;
        this.size = size;
        this.isCommercial = isCommercial;
        this.isPublic = isPublic;
        this.city = city;
        this.street = street;
        this.zip = zip;
        this.propertyType = propertyType;
        this.owner = owner;
        this.approvedBy = approvedBy;
    }

    public Property(int ID, String name, double size, boolean isCommercial, boolean isPublic, String city, String street, int zip, PropertyType propertyType, String owner, String approvedBy, double avg_rating) {
        this.ID = ID;
        this.name = name;
        this.size = size;
        this.isCommercial = isCommercial;
        this.isPublic = isPublic;
        this.city = city;
        this.street = street;
        this.zip = zip;
        this.propertyType = propertyType;
        this.owner = owner;
        this.approvedBy = approvedBy;
        this.avg_rating = avg_rating;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean isCommercial() {
        return isCommercial;
    }

    public void setCommercial(boolean commercial) {
        isCommercial = commercial;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public double getAvg_rating() { return this.avg_rating; }

    public void setAvg_rating(double avg_rating) { this.avg_rating = avg_rating; }

}
