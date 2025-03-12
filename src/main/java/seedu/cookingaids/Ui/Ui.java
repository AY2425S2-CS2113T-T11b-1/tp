package seedu.cookingaids.Ui;

import seedu.cookingaids.Collections.DishCalendar;
import seedu.cookingaids.Items.Dish;
import seedu.cookingaids.Parser.Parser;
import seedu.cookingaids.Storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Ui class holds methods relating to the User interface of the program
 * including accepting commands and printing outputs
 */
public class Ui {


    /**
     * Offset required to convert between 1-indexing and 0-indexing.
     */
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";
    static final String LINE_DIVIDER = "_________________________________________________________________________________________";

    public static void printLineDivider() {
        System.out.println(LINE_DIVIDER);
    }


    public static void printItems(String... messages) { //print items to console
        for (String s : messages) {
            System.out.println(s.replace("\n", System.lineSeparator()));
        }
    }

    /**
     * outputs taskList on console
     *
     * @param dishes is list of items to be output
     */
    public static void printDishListView(ArrayList<Dish> dishes) {
        //prints TaskList on console
        final List<String> formattedItems = new ArrayList<>();
        for (Dish d : dishes) {
            formattedItems.add(d.toString());
        }
        printAsIndexedList(formattedItems);
    }

    /**
     * print items as an indexed list
     */
    private static void printAsIndexedList(List<String> list) {
        printItems(getIndexedListForViewing(list));

    }

    /**
     * formats list into an indexed list
     */
    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        //sets offset so it starts from 1
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    /**
     * extracts items from each list entry
     *
     * @return a formatted version of items
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }

    /**
     * waits for user input.
     */
    public static void waitForCommand() {

        //initialize list
        DishCalendar.initializeDishCalendar();
        Scanner scanner = new Scanner(System.in);
        String scannedText;
        while (!(scannedText = scanner.nextLine()).equals("bye")) {     //bye breaks the while loop
            Parser.decipherCommand(scannedText);
        }

        //store list
        Storage.storeList(DishCalendar.getDishCalendar());
    }




}