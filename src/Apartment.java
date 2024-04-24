public class Apartment {
    private String city;
    private int rooms;
    private int sqrmeters;
    private int price;
    private String phone;

    public Apartment(String city, int rooms, int sqrmeters, int price, String phone) {
        this.city = city;
        this.rooms = rooms;
        this.sqrmeters = sqrmeters;
        this.price = price;
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getSqrmeters() {
        return sqrmeters;
    }

    public void setSqrmeters(int sqrmeters) {
        this.sqrmeters = sqrmeters;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean isSuitable(){
        return rooms>=3 && sqrmeters>100;
    }
}
