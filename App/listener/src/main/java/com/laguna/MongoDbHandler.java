package main.java.com.laguna;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class MongoDbHandler {

    private MongoDBClient final ;

    public void connect(){

        string connString = "";
         try (mongoClient = MongoClients.create(connString)) {

            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("countries");

        }//end of try catch
    }//end of Connect
    
    public void InsertNewDocument(){
        
        Document newDocument = new Document();

    }//end of InsertNewDocument
}//end of MongoDbHandler
