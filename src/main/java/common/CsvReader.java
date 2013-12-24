package common;

import common.attributes.SlandoAtt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: zapolsks
 * Date: 12/3/13
 * Time: 5:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class CsvReader {
    public static Object[][] read(String filePath) {

        List<Object[]> books = new ArrayList<Object[]>();

        try {

            Scanner scanner = new Scanner(new File(filePath));


            List<SlandoAtt> slandoAttList = new ArrayList<SlandoAtt>();
            for (String attributeName: nextRow(scanner)) {
                slandoAttList.add(SlandoAtt.getByName(attributeName));
            }

            while (scanner.hasNextLine()) {
                List<Item> itemList = new ArrayList<Item>();
                String[] values = nextRow(scanner);
                for (int i = 0; i <  values.length; i++) {
                    itemList.add(new Item(slandoAttList.get(i), values[i]));
                }
                books.add(new Object[] {itemList});
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }



        return books.toArray(new Object[books.size()][]);
    }

    private static String[] nextRow(Scanner scanner) {
        return scanner.nextLine().split(",");
    }

    public static void write(String text, String filename) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(new File(filename), true));
            output.write(text);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
