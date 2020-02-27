package scraping;

import classes.Item;

public class VaultOfKundarak {

    private static final String XPATH_ITEM_BONUSES = "/html/body/div[1]/div/div[1]/div[3]/div/div/div/div[1]/div/div[2]/div[2]/div/div/div[2]/div[4]";
    //private static final String XPATH_ITEM_BONUSES = "//*[@class=\"panel-main-effect-list\"]";


    public static Item generateItem(String name) {
        Item item = new Item();

        //System.out.println(Internet.getXPathContents(XPATH_ITEM_BONUSES,Internet.getContents("https://vaultofkundarak.com/item/Legendary_Bracers_of_the_Fallen_Hero")));

        return item;
    }
}
