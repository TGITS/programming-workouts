package tgits.hbasetuto.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class Main {

    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static final String ZK_QUORUM = "255.255.255.254";//Aremplacer par l'IP ou le hostname adéquat
    private static final String ZK_CLIENT_PORT = "2181";
    private static final String HBASE_MASTER = "255.255.255.254:16000";

    private static final String TABLE_NAME = "HOUSES_OF_WESTEROS";
    private static final String DEFAULT_CF = "MAIN_CHARACTERISTICS_COLUMN_FAMILY";

    /*
     * Sources d'inspiration diverses pour ce petit bout de programme :
     * - https://hbase.apache.org/book.html#hbase_apis
     * - DZone Refcardz #159
     * - HBase Succintly by Elton Stoneman (Syncfusion)
     * */
    public static void main(String[] args) throws IOException {

        Configuration config = HBaseConfiguration.create();
        config.clear();
        config.set("hbase.zookeeper.quorum", ZK_QUORUM);
        config.set("hbase.zookeeper.property.clientPort", ZK_CLIENT_PORT);
        config.set("hbase.master", HBASE_MASTER);

        try (Connection connection = ConnectionFactory.createConnection(config);
             Admin admin = connection.getAdmin()) {
            System.out.println("Connected to HBase");
            HTableDescriptor table = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
            table.addFamily(new HColumnDescriptor(DEFAULT_CF).setCompressionType(Compression.Algorithm.NONE));
            System.out.println("\nCreating table or overwriting it if it already exist");
            createOrOverwrite(admin, table);
            System.out.println("\nPopulating the base with some data");
            populateTable(connection);
            System.out.println("\nRetrieving and displaying some data from HBase");
            Map<String, String> familyWordsByHouseName = getFamilyWordsByHouseName(connection);
            System.out.println("\nSome famous noble Houses of Westeros and their family words");
            for (Map.Entry<String, String> entry : familyWordsByHouseName.entrySet()) {
                System.out.println(String.format("%s : %s", entry.getKey(), entry.getValue()));
            }
            System.out.println("\nGoing scanning on the houses of Westeros from arryn to targaryen (inclusive)\n");
            System.out.println(mapToString(scanAllHouses(connection, "arryn", "targaryen")));
            System.out.println("\nScanning only the house which name starts by a name between 'a' and 'm' (inclusive)\n");
            System.out.println(mapToString(scanAllHouses(connection, "a", "m")));
            System.out.println("\nGoing scanning full scan on the houses of Westeros from 'a' to 'zzzzzz'\n");
            System.out.println(mapToString(scanAllHouses(connection, "a", "zzzzzz")));
            System.out.println("\nCompleting some data to make the region appears for all the houses\n");
            completingData(connection);
            System.out.println("\nLet's have a look at the new version of the data\n");
            System.out.println(mapToString(scanAllHouses(connection, "a", "zzzzzz")));
            System.out.println("\nWe are done here. Hasta la vista ! Remember (and the North Remembers) : Valar Morghulis !\n");
        }
    }

    private static void createOrOverwrite(Admin admin, HTableDescriptor table) throws IOException {
        if (admin.tableExists(table.getTableName())) {
            admin.disableTable(table.getTableName());
            admin.deleteTable(table.getTableName());
        }
        admin.createTable(table);
    }

    private static void populateTable(Connection connection) throws IOException {
        Table houses = connection.getTable(TableName.valueOf(TABLE_NAME));

        //Populated the Table the hard and long way
        /* House Stark
         * name: "Stark",
         * main_seat: "Winterfell",
         * ancestral_seat: "Winterfell",
         * sigil: "A Gray Direwolf",
         * family_words: "Winter is coming"
         */
        // 1 - Creating the "Put" operation and specifying the RowKey
        Put stark = new Put(Bytes.toBytes("stark")); //The RowKey is "stark" all in lower caps
        // 2 - Adding the data in the Put for each column
        stark.addColumn(
                Bytes.toBytes(DEFAULT_CF), //Column Family
                Bytes.toBytes("name"),  //Column Qualifier
                Bytes.toBytes("Stark")  //The value
        );
        stark.addColumn(
                Bytes.toBytes(DEFAULT_CF), //Column Family
                Bytes.toBytes("main_seat"),  //Column Qualifier
                Bytes.toBytes("Winterfell")  //The value
        );
        stark.addColumn(
                Bytes.toBytes(DEFAULT_CF), //Column Family
                Bytes.toBytes("ancestral_seat"),  //Column Qualifier
                Bytes.toBytes("Winterfell")  //The value
        );
        stark.addColumn(
                Bytes.toBytes(DEFAULT_CF), //Column Family
                Bytes.toBytes("sigil"),  //Column Qualifier
                Bytes.toBytes("A Gray Direwolf")  //The value
        );
        stark.addColumn(
                Bytes.toBytes(DEFAULT_CF), //Column Family
                Bytes.toBytes("family_words"),  //Column Qualifier
                Bytes.toBytes("Winter is coming")  //The value
        );
        // 3 - Puting the data in the table
        houses.put(stark);

        //A little bit less fastidious
        //Beware we are inserting House Stark a second time
        Map<String, Map<String, String>> data = createData();
        Put row = null;
        for (Map.Entry<String, Map<String, String>> entry : data.entrySet()) {
            // 1 - Creating the "Put" operation and specifying the RowKey
            row = new Put(Bytes.toBytes(entry.getKey())); //The RowKey is here the key of container map
            // 2 - Adding the data in the Put for each entry for which the value is different from null
            String entryKey = null;
            String entryValue = null;
            for (Map.Entry<String, String> house_characteristics : entry.getValue().entrySet()) {
                entryKey = house_characteristics.getKey();
                entryValue = house_characteristics.getValue();
                row.addColumn(
                        Bytes.toBytes(DEFAULT_CF), //Column Family
                        Bytes.toBytes(entryKey),  //Column Qualifier
                        Bytes.toBytes(entryValue)  //The value
                );
            }
            // 3 - Puting the data in the table
            houses.put(row);
        }
    }

    private static Map<String, Map<String, String>> createData() {
        Map<String, Map<String, String>> data = new HashMap<>();

        //House Stark
        Map<String, String> stark = new HashMap<>();
        stark.put("name", "Stark");
        stark.put("main_seat", "Winterfell");
        stark.put("ancestral_seat", "Winterfell");
        stark.put("sigil", "A Gray Direwolf");
        stark.put("family_words", "Winter is coming");
        data.put("stark", stark);

        //House Lannister
        Map<String, String> lannister_of_casterly_rock = new HashMap<>();
        lannister_of_casterly_rock.put("name", "Lannister of Casterly Rock");
        lannister_of_casterly_rock.put("main_seat", "Casterly Rock");
        lannister_of_casterly_rock.put("ancestral_seat", "Casterly Rock");
        lannister_of_casterly_rock.put("sigil", "A Golden Lion");
        lannister_of_casterly_rock.put("family_words", "Hear me roar !");
        data.put("lannister_of_casterly_rock", lannister_of_casterly_rock);

        //House Baratheon
        Map<String, String> baratheon = new HashMap<>();
        baratheon.put("name", "Baratheon");
        baratheon.put("main_seat", "King's Landing");
        baratheon.put("ancestral_seat", "Storm's End");
        baratheon.put("secondary_seat", "Dragonstone");
        baratheon.put("sigil", "A Crowned Black Stag");
        baratheon.put("family_words", "Our is the fury !");
        data.put("baratheon", baratheon);

        //House Greyjoy
        Map<String, String> greyjoy = new HashMap<>();
        greyjoy.put("name", "Greyjoy");
        greyjoy.put("main_seat", "Pyke");
        greyjoy.put("ancestral_seat", "Pyke");
        greyjoy.put("sigil", "A Golden Kraken");
        greyjoy.put("family_words", "We do not sow");
        data.put("greyjoy", greyjoy);

        //House Arryn of the Eyrie
        Map<String, String> arryn_of_the_eyrie = new HashMap<>();
        arryn_of_the_eyrie.put("name", "Arryn of the Eyrie");
        arryn_of_the_eyrie.put("main_seat", "The Eyrie");
        arryn_of_the_eyrie.put("ancestral_seat", "The Eyrie");
        arryn_of_the_eyrie.put("sigil", "Moon and Falcon");
        arryn_of_the_eyrie.put("family_words", "As high as Honor");
        data.put("arryn_of_the_eyrie", arryn_of_the_eyrie);

        //House Arryn of Gulltown
        Map<String, String> arryn_of_gulltown = new HashMap<>();
        arryn_of_gulltown.put("name", "Arryn of Gulltown");
        arryn_of_gulltown.put("main_seat", "Gulltown");
        data.put("arryn_of_gulltown", arryn_of_gulltown);

        //House Targaryen
        Map<String, String> targaryen = new HashMap<>();
        targaryen.put("name", "Targaryen");
        targaryen.put("ancestral_seat", "Dragonstone");
        targaryen.put("sigil", "Three-Headed Dragon");
        targaryen.put("family_words", "Fire and Blood");
        data.put("targaryen", targaryen);

        //House Tully
        Map<String, String> tully = new HashMap<>();
        tully.put("name", "Tully");
        tully.put("main_seat", "Riverun");
        tully.put("ancestral_seat", "Riverun");
        tully.put("sigil", "A leaping silver trout on a field of blue and mud red");
        tully.put("family_words", "Family, Duty, Honor");
        tully.put("region", "Riverlands");
        data.put("tully", tully);

        //House Tyrell
        Map<String, String> tyrell = new HashMap<>();
        tyrell.put("name", "Tyrell");
        tyrell.put("main_seat", "Highgarden");
        tyrell.put("ancestral_seat", "Highgarden");
        tyrell.put("sigil", "A golden rose, on a green field");
        tyrell.put("family_words", "Growing Strong");
        tyrell.put("region", "Reach");
        data.put("tyrell", tyrell);

        //House Martell
        Map<String, String> martell = new HashMap<>();
        martell.put("name", "Martell");
        martell.put("main_seat", "Old Palace within Sunspear");
        martell.put("ancestral_seat", "Old Palace within Sunspear");
        martell.put("sigil", "A gold spear piercing a red sun on an orange field");
        martell.put("family_words", "Unbowed, Unbent, Unbroken");
        martell.put("region", "Dorne");
        data.put("martell", martell);

        return data;
    }

    private static Map<String, String> getFamilyWordsByHouseName(Connection connection) throws IOException {
        Map<String, String> familyWordByHouseName = new HashMap<>();
        Table houses = connection.getTable(TableName.valueOf(TABLE_NAME));
        List<String> rowkeys = Arrays.asList(new String[]{"arryn_of_the_eyrie", "stark", "targaryen", "baratheon", "greyjoy", "lannister_of_casterly_rock", "tully", "tyrell", "martell"});
        Get get = null;
        for (String rowkey : rowkeys) {
            // 1 - Specifiying the rowkey, always in Bytes
            get = new Get(Bytes.toBytes(rowkey));
            // 2 - Specifying the columns in the row to get
            get.addColumn(
                    Bytes.toBytes(DEFAULT_CF), //First specifying the Column Family
                    Bytes.toBytes("name") //Next the column in the CF
            );
            get.addColumn(
                    Bytes.toBytes(DEFAULT_CF), //First specifying the Column Family
                    Bytes.toBytes("family_words") //Next the column in the CF
            );
            Result result = houses.get(get);
            familyWordByHouseName.put(
                    Bytes.toString(result.getValue(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes("name"))),
                    Bytes.toString(result.getValue(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes("family_words"))));

        }
        return familyWordByHouseName;
    }

    private static List<Map<String, String>> scanAllHouses(Connection connection, String start, String end) throws IOException {
        List<Map<String, String>> allHouses = new ArrayList<>();
        Table houses = connection.getTable(TableName.valueOf(TABLE_NAME));
        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes(DEFAULT_CF));
        scan.withStartRow(Bytes.toBytes(start), true);
        scan.withStopRow(Bytes.toBytes(end), true);
        List<String> columns = Arrays.asList(new String[]{"name", "main_seat", "ancestral_seat", "secondary_seat", "sigil", "family_words", "region"});
        try (ResultScanner scanner = houses.getScanner(scan)) {
            Map<String, String> house = null;
            for (Result result : scanner) {
                house = new HashMap<>();
                for (String column : columns) {
                    if (result.containsNonEmptyColumn(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes(column))) {
                        house.put(column, Bytes.toString(result.getValue(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes(column))));
                    }
                }
                allHouses.add(house);
            }
        }
        return allHouses;
    }

    private static String mapToString(List<Map<String, String>> maps) {
        StringBuilder sb = new StringBuilder();
        for (Map<String, String> map : maps) {
            for (Map.Entry<String, String> house_characteristics : map.entrySet()) {
                sb.append(house_characteristics.getKey()).append(" : ").append(house_characteristics.getValue()).append("\n");
            }
            sb.append("\n-------\n\n");
        }
        return sb.toString();
    }

    private static void completingData(Connection connection) throws IOException {
        Table houses = connection.getTable(TableName.valueOf(TABLE_NAME));

        Put row = new Put(Bytes.toBytes("stark"));
        row.addColumn(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes("region"), Bytes.toBytes("North"));
        houses.put(row);

        row = new Put(Bytes.toBytes("lannister_of_casterly_rock"));
        row.addColumn(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes("region"), Bytes.toBytes("Westerlands"));
        houses.put(row);

        row = new Put(Bytes.toBytes("baratheon"));
        row.addColumn(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes("region"), Bytes.toBytes("Crownlands"));
        row.addColumn(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes("other_region"), Bytes.toBytes("Stormlands"));
        houses.put(row);

        row = new Put(Bytes.toBytes("greyjoy"));
        row.addColumn(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes("region"), Bytes.toBytes("Iron Islands"));
        houses.put(row);

        row = new Put(Bytes.toBytes("arryn_of_the_eyrie"));
        row.addColumn(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes("region"), Bytes.toBytes("Vale"));
        houses.put(row);

        row = new Put(Bytes.toBytes("arryn_of_gulltown"));
        row.addColumn(Bytes.toBytes(DEFAULT_CF), Bytes.toBytes("region"), Bytes.toBytes("Vale"));
        houses.put(row);
    }
}
