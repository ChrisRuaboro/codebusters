package com.codebusters.game;
/**
 * TextParser.java takes the input from the player and checks it against valid
 * commands. If invalid, it sets a bool value which Game.java can then check.
 * Otherwise it updates with the next chapter and inventory changes, which
 * Game.java can then pull.
 * <p>
 * Authors: Bradley Pratt & Debbie Bitencourt
 * Last Edited: 01/29/2021
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class TextParser {
//    private final ArrayList<String> ITEM_VERBS_GAIN;
    private String nextChapter;
    private Chapter currentChapter;
    private boolean validInput;
    private ArrayList<Items> itemsToAdd;
    private ArrayList<Items> itemsToRemove;
    private ArrayList<Items> inventory;

    public TextParser(){
//        nextChapter = new Chapter();
        currentChapter = new Chapter();
        setValidInput(false);
        itemsToAdd = new ArrayList<>();
        itemsToRemove = new ArrayList<>();
        inventory = new ArrayList<>();
//         ITEM_VERBS_GAIN = new ArrayList<>();
//         ITEM_VERBS_GAIN.add("take");
//         ITEM_VERBS_GAIN.add("grab");
//         ITEM_VERBS_GAIN.add("pickup");
//         ITEM_VERBS_GAIN.add("open");
    }

    public void parseInput(String input){
        System.out.println(input+" from parser");
        setValidInput(false);
        if (!input.equals("")){
            String delims = " \t,.:;?!\"'";
            StringTokenizer parse = new StringTokenizer(input.toLowerCase(), delims);
            ArrayList<String> command = new ArrayList<>();

            while (parse.hasMoreTokens()){
                command.add(parse.nextToken());
            }

            if (command.size() == 2){
                String verb = command.get(0);
                String noun = command.get(1);

                /*
                @TODO: we have the verb and the noun. We need to check it against list of acceptable ones for the current Chapter. Then take the appropriate action.
                 */

                ArrayList<HashMap> paths = currentChapter.getPaths();
                for (HashMap path: paths){
                    // if we have a valid input
                    if (verb.equals(path.get("verb")) && noun.equals(path.get("noun"))){
                        // first, we check for required items
                        if (!path.get("requiredItems").equals("")){
                            ArrayList<String> reqItems = (ArrayList<String>) path.get("requiredItems");

                            // we need to make sure at least one required item is in inventory
                            boolean itemFound = false;
                            for (String item: reqItems){
                                for (Items possession: inventory){
                                    if (item.equals(possession.getName().toLowerCase())){
                                        itemFound = true;
                                        break;
                                    }
                                }

                                if (itemFound){
                                    break;
                                }
                            }

                            // if a required item is found, then proceed, otherwise invalid input
                            if (itemFound){
                                setValidInput(true);
                                nextChapter = (String) path.get("nextId");
                                ArrayList<String> gainItems = (ArrayList<String>) path.get("gainItems");
                                ArrayList<String> loseItems = (ArrayList<String>) path.get("loseItems");

                                for (String item : gainItems){
                                    itemsToAdd.add(new Items(item));
                                }
                                for (String item : loseItems){
                                    itemsToRemove.add(new Items(item));
                                }
                            }
                        }else{
                            setValidInput(true);
                            nextChapter = (String) path.get("nextId");
                            ArrayList<String> gainItems = (ArrayList<String>) path.get("gainItems");
                            ArrayList<String> loseItems = (ArrayList<String>) path.get("loseItems");

                            for (String item : gainItems){
                                itemsToAdd.add(new Items(item));
                            }
                            for (String item : loseItems){
                                itemsToRemove.add(new Items(item));
                            }
                        }
                    }
                }
            }
        }
    }

    //***************GETTERS/SETTERS***************
    public String getNextChapter() {
        return nextChapter;
    }

    private void setNextChapter(String nextChapter) {
        this.nextChapter = nextChapter;
    }

    private Chapter getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(Chapter currentChapter, ArrayList<Items> inventory) {
        this.currentChapter = currentChapter;
        this.inventory = inventory;
    }

    public boolean isValidInput() {
        return validInput;
    }

    private void setValidInput(boolean validInput) {
        this.validInput = validInput;
    }

    public ArrayList<Items> getItemsToAdd() {
        return itemsToAdd;
    }

    private void setItemsToAdd(ArrayList<Items> toAdd) {
        this.itemsToAdd = toAdd;
    }

    public ArrayList<Items> getItemsToRemove() {
        return itemsToRemove;
    }

    private void setItemsToRemove(ArrayList<Items> toRemove) {
        this.itemsToRemove = toRemove;
    }
}
