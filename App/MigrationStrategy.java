import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static final String MONGODB_USER = "admin";
    private static final String MONGODB_PASSWORD = "passwordone";

    private static final int CORES = Runtime.getRuntime().availableProcessors();

    private static JSONObject user_input = new JSONObject("""
        {
            "mongodb_user": "admin",
            "mongodb_password": "passwordone",
            "mapping_config_file_name": "mapping_config.json",
            "mapping_config_file_path": ""
        }
    """);

    public static void main(String[] args) {
        // Read the config.json file to get the mapping
        JSONObject mapping_config = readJsonFile(user_input.getString("mapping_config_file_name"));

        String db_name = mapping_config.getString("db_name");
        String collection_name = mapping_config.getString("collection_name");
        JSONObject document_structure = mapping_config.getJSONObject("document_structure");

        MongoClientURI uri = new MongoClientURI(
            "mongodb://" + MONGODB_USER + ":" + MONGODB_PASSWORD + "@localhost:27018,localhost:27019,localhost:27020/?replicaSet=rs0"
        );
        MongoClient client = new MongoClient(uri);
        MongoDatabase mongodb_database = client.getDatabase(db_name);

        String[] base_tables = {"departments", "employees"};
        String[] derived_tables = {"dept_manager", "titles", "dept_emp", "salaries"};

        // Reading the base tables into a dictionary
        Map<String, JSONObject> base_table_dict = new HashMap<String, JSONObject>();
        for (String base_table : base_tables) {
            base_table_dict.put(base_table, readJsonFile(base_table + ".json"));
        }

        // Create a new dictionary to store the filtered data
        Map<String, Object> new_data = new HashMap<String, Object>();
        String base_table_to_be_used = "";
        // Insert the base data first into the root elements of the JSON
        for (String key : document_structure.getJSONObject("employees").keySet()) {
            Object value = document_structure.getJSONObject("employees").get(key);
            if (!(value instanceof JSONObject) && !(value instanceof JSONArray) && !value.equals("ObjectId")) {
                new_data.put(key, value);
                base_table_to_be_used = value.toString().split("\\.")[1];
            }
        }

        List<Document> mongodb_docs = new ArrayList<Document>();

       System.out.println("Inserting the base table data into the MongoDb collection - " + collectionName);
        System.out.println("Prepping the data into document format...");

        List<Document> mongodbDocs = new ArrayList<>();
        for (String primKey : baseTableDict.get(baseTableToBeUsed).keySet()) {
            Document valueDict = baseTableDict.get(baseTableToBeUsed).get(primKey);

            Document insertDoc = new Document();
            for (String key : new_data.keySet()) {
                String value = new_data.get(key);
                String[] parts = value.split("\\.");
                String valueDictKey = parts[parts.length - 1];
                Object valueDictValue = valueDict.get(valueDictKey);
                insertDoc.append(key, valueDictValue);
            }
            mongodbDocs.add(insertDoc);
        }

        // Insert the base data into the MongoDB collection, Get a reference to the collection
        MongoDatabase mongodbDatabase = null; // Replace this with your MongoDB database object
        MongoCollection<Document> collection = mongodbDatabase.getCollection(collectionName);

        // Dropping the collection if it already exists
        collection.drop();

        // insert the documents into the collection
        System.out.println("Inserting " + mongodbDocs.size() + " into the collection: " + collectionName);
        collection.insertMany(mongodbDocs);
        System.out.println("Success...!!");

        // Create Index on the Primary key
        // TODO: get the primary key to be used info generalistically
        System.out.println("Creating the index on the field: " + primary_key);
        collection.createIndex(new Document(primary_key, 1), new IndexOptions().unique(true));
        System.out.println("Success...!!");

        String dirPath = "chunks";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory '" + dirPath + "' created successfully.");
            } else {
                System.out.println("Error creating directory '" + dirPath + "'.");
            }
        } else {
            System.out.println("Directory '" + dirPath + "' already exists.");
        }