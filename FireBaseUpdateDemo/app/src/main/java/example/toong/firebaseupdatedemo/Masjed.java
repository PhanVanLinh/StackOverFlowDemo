package example.toong.firebaseupdatedemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Masjed {
    private String userID;
    private String id;
    private String name;
    private String address;
    private String phone;
    private long mark;
    private boolean matloopEmam;

    public Masjed(String userID, String id, String name, String address, String phone,
            boolean matloopEmam, long mark) {
        this.userID = userID;
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.matloopEmam = matloopEmam;
        this.mark = mark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isMatloopEmam() {
        return matloopEmam;
    }

    public void setMatloopEmam(boolean matloopEmam) {
        this.matloopEmam = matloopEmam;
    }

    public Masjed(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Masjed() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getMark() {
        return mark;
    }

    public void setMark(long mark) {
        this.mark = mark;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("address", address);
        map.put("phone", phone);
        map.put("id", id);
        map.put("userID", userID);
        map.put("mark", mark);
        return map;
    }

    public static String listToString(List<Masjed> masjedArrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Masjed masjed : masjedArrayList) {
            stringBuilder.append("Name: " + masjed.getName());
            stringBuilder.append("\nId: " + masjed.getId());
            stringBuilder.append("\nAddress: " + masjed.getAddress());
            stringBuilder.append("\nPhone: " + masjed.getPhone());
            stringBuilder.append("\nMark: " + masjed.getMark());
            stringBuilder.append("\n----------------\n");
        }
        return stringBuilder.toString();
    }

}