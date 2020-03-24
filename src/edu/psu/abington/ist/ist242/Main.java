package edu.psu.abington.ist.ist242;
/*
Project: Lab 9
Purpose Details: Pizza ordering application
Course: IST 242
Author: William Perez
Date Developed: 3/22/20
Last Date Changed: 3/22/20
Rev: 1
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    int cCount = 0;
    private static Scanner scnr = new Scanner(System.in);
    public static void main(String[] args) {

        Main main = new Main();

        final char EXIT_CODE = 'E';
        final char CUST_CODE = 'C';
        final char MENU_CODE = 'M';
        final char ORDE_CODE = 'O';
        final char TRAN_CODE = 'T';
        final char CUST_PRNT = 'P';
        final char HELP_CODE = '?';
        char userAction;
        final String PROMPT_ACTION = "Add 'C'ustomer, 'P'rint Customer, List 'M'enu, Add 'O'rder, List 'T'ransaction or 'E'xit: ";
        ArrayList<Customer> cList = new ArrayList<>();
        ArrayList<Menu> mList = new ArrayList<>();
        ArrayList<Order> oList = new ArrayList<>();
        ArrayList<Transaction> tList = new ArrayList<>();

        Order order1 = new Order(1);
        Transaction trans1 = new Transaction(1, order1, PaymentType.cash);

        Menu menu1 = new Menu(1, "Plain", 6.50);
        Menu menu2 = new Menu(2, "Meat", 9.00);
        Menu menu3 = new Menu(3, "Extra cheese", 7.50);
        Menu menu4 = new Menu(4, "Veg", 8.50);
        Menu menu5 = new Menu(5, "Hawaiian", 8.75);

        mList.add(menu1);
        mList.add(menu2);
        mList.add(menu3);
        mList.add(menu4);
        mList.add(menu5);

        oList.add(order1);
        tList.add(trans1);

        userAction = getAction(PROMPT_ACTION);

        while (userAction != EXIT_CODE) {
            switch(userAction) {
                case CUST_CODE : cList.add(main.addCustomer());
                    break;
                case CUST_PRNT : Customer.printCustomer(cList);
                    break;
                case MENU_CODE : Menu.listMenu(mList);
                    break;
                case ORDE_CODE : System.out.print("Please enter customer ID: ");
                    int cid = scnr.nextInt();

                    ArrayList<Menu> cMenu = selectMenu(mList);
                    Order.addOrders(order1, cList.get(cid), cMenu);
                    oList.add(order1);
                    trans1 = payment(order1);
                    tList.add(trans1);
                    break;
                case TRAN_CODE : Transaction.listTransactions(tList);
                    break;
            }

            userAction = getAction(PROMPT_ACTION);
        }
    }

    public static ArrayList<Menu> selectMenu(ArrayList<Menu> menus) {
        System.out.println("Select from the menu, press zero when finished");
        for (Menu menu : menus)
            System.out.println("'" + menu.getmenuId() + "' for " + menu.getmenuItem());
        int f;
        ArrayList<Menu> menus1 = new ArrayList<>();
        while(true) {
            f = scnr.nextInt();
            if(f == 0)
                break;
            Menu item = menus.get(f-1);
            menus1.add(item);
        }
        return menus1;
    }

    public static char getAction(String prompt) {
        Scanner scnr = new Scanner(System.in);
        String answer = "";
        System.out.println(prompt);
        answer = scnr.nextLine().toUpperCase() + " ";
        char firstChar = answer.charAt(0);
        return firstChar;
    }

    public Customer addCustomer(){
        Customer cust = new Customer(cCount++);
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please Enter your Name: ");
        cust.setCustomerName(scnr.nextLine());
        System.out.println("Please Enter your Phone: ");
        cust.setCustomerPhoneNumber(scnr.nextLine());
        return cust;
    }

    private static Transaction payment(Order order1) {
        double total = 0;
        double amount;
        System.out.println("Total amount owed: ");
        for(Menu menu : order1.getmenuItem()){
            System.out.print(menu.getmenuItem());

            System.out.print("$ ");
            amount = menu.getitemPrice();
            total = total + amount;
            System.out.println(amount);
        }
        System.out.println("Total bill is: $" + total);
        int option;
        Transaction tra;
        while(true) {
            System.out.println("Select Payment Option: ");
            System.out.println("1. Cash");
            System.out.println("2. Credit");
            option = scnr.nextInt();
            if (option == 1) {
                tra = new Transaction(order1.getorderId(), order1, PaymentType.cash);
                return tra;
            }
            else if(option==2) {
                tra = new Transaction(order1.getorderId(), order1, PaymentType.credit);
                return tra;
            }
        }
    }
}
