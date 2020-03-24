package edu.psu.abington.ist.ist242;

import java.util.ArrayList;

public class Menu {

    //Class Level Variables - Protect the data
    private int menuId;
    private String menuItem;
    private double itemPrice;

    //Constructor Method
    public Menu(int _menuId, String _menuItem, double _itemPrice){
        this.menuId = _menuId;
        this.menuItem = _menuItem;
        this.itemPrice = _itemPrice;
    }

    //Setters and Getters
    public int getmenuId() { return menuId; }
    public void setmenuId(int _menuId) {this.menuId = _menuId;}

    public String getmenuItem() { return menuItem; }
    public void setmenuItem(String _menuItem) {this.menuItem = _menuItem;}

    public double getitemPrice(){return itemPrice; }
    public void setitemPrice(double _itemPrice) {this.itemPrice = _itemPrice;}

    public static void listMenu(ArrayList<Menu> mList){
        for (Menu menu: mList){
            System.out.println(menu.getmenuItem() + " " + menu.getitemPrice());
        }
    }
}
