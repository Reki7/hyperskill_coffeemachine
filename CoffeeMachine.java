package machine;

import java.util.Scanner;

enum MachimeStates {
    WAIT_ACTION,
    CHOOSE_COFFEE_TYPE,
    FILL_WATER,
    FILL_MILK,
    FILL_BEANS,
    FILL_CUPS
}

class Machine {
    int amountWater;
    int amountMilk;
    int amountBeans;
    int amountCups;
    int amountMoney;
    MachimeStates state;

    Machine(int water, int milk, int beans, int cups, int money) {
        this.amountWater = water;
        this.amountMilk = milk;
        this.amountBeans = beans;
        this.amountCups = cups;
        this.amountMoney = money;
        this.state = MachimeStates.WAIT_ACTION;
        System.out.print("Write action (buy, fill, take, remaining, exit):\n> ");
    }

    public boolean setInput(String input) {
        boolean result = true;
        switch (this.state) {
            case WAIT_ACTION:
                switch (input) {
                    case "buy":
                        System.out.print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:\n> ");
                        this.state = MachimeStates.CHOOSE_COFFEE_TYPE;
                        break;
                    case "fill":
                        System.out.print("\nWrite how many ml of water do you want to add:\n> ");
                        this.state = MachimeStates.FILL_WATER;
                        break;
                    case "take":
                        System.out.println("\nI gave you $" + this.amountMoney);
                        this.amountMoney = 0;
                        System.out.print("\nWrite action (buy, fill, take, remaining, exit):\n> ");
                        break;
                    case "remaining":
                        System.out.println();
                        this.printStatus();
                        System.out.print("\nWrite action (buy, fill, take, remaining, exit):\n> ");
                        break;
                    case "exit":
                        result = false;
                        break;
                    default:
                        break;
                }
                break;
            case CHOOSE_COFFEE_TYPE:
                int needWater = 0;
                int needMilk = 0;
                int needBeans = 0;
                int price = 0;
                switch (input) {
                    case "back":
                        break;
                    case "1": // espresso
                        needWater = 250;
                        needBeans = 16;
                        price = 4;
                        break;
                    case "2": // latte
                        needWater = 350;
                        needMilk = 75;
                        needBeans = 20;
                        price = 7;
                        break;
                    case "3": // cappuccino
                        needWater = 200;
                        needMilk = 100;
                        needBeans = 12;
                        price = 6;
                        break;
                }
                if (price > 0) {
                    if (needWater > this.amountWater) {
                        System.out.println("Sorry, not enough water!");
                    } else if (needMilk > this.amountMilk) {
                        System.out.println("Sorry, not enough milk!");
                    } else if (needBeans > this.amountBeans) {
                        System.out.println("Sorry, not enough coffee beans!");
                    } else if (this.amountCups < 1) {
                        System.out.println("Sorry, not enough disposable cups!");
                    } else {
                        System.out.println("I have enough resources, making you a coffee!");
                        this.amountWater -= needWater;
                        this.amountMilk -= needMilk;
                        this.amountBeans -= needBeans;
                        this.amountCups--;
                        this.amountMoney += price;
                    }
                }
                System.out.print("\nWrite action (buy, fill, take, remaining, exit):\n> ");
                this.state = MachimeStates.WAIT_ACTION;
                break;
            case FILL_WATER:
                this.amountWater += Integer.parseInt(input);
                System.out.print("Write how many ml of milk do you want to add:\n> ");
                this.state = MachimeStates.FILL_MILK;
                break;
            case FILL_MILK:
                this.amountMilk += Integer.parseInt(input);
                System.out.print("Write how many grams of coffee beans do you want to add:\n> ");
                this.state = MachimeStates.FILL_BEANS;
                break;
            case FILL_BEANS:
                this.amountBeans += Integer.parseInt(input);
                System.out.print("Write how many disposable cups of coffee do you want to add:\n> ");
                this.state = MachimeStates.FILL_CUPS;
                break;
            case FILL_CUPS:
                this.amountCups += Integer.parseInt(input);
                System.out.print("\nWrite action (buy, fill, take, remaining, exit):\n> ");
                this.state = MachimeStates.WAIT_ACTION;
                break;
            default:
                break;
        }
        return result;
    }

    void printStatus() {
        System.out.println("The coffee machine has:");
        System.out.println("" + amountWater + " of water");
        System.out.println("" + amountMilk + " of milk");
        System.out.println("" + amountBeans + " of coffee beans");
        System.out.println("" + amountCups + " of disposable cups");
        System.out.println("$" + amountMoney + " of money");
    }
}


public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int amountWater = 400;
        int amountMilk = 540;
        int amountBeans = 120;
        int amountCups = 9;
        int amountMoney = 550;

        boolean operate = true;
        Machine machine = new Machine(amountWater, amountMilk, amountBeans, amountCups, amountMoney);
        while (operate) {
            String action = scanner.nextLine();
            operate = machine.setInput(action);
        }
    }
}
