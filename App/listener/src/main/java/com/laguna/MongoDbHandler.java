package com.laguna;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.types.ObjectId;

import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class MongoDbHandler {

    private MongoClient mongoClient;

    public void connect(){

        String connString = "";
        mongoClient = MongoClients.create(connString);
        MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
        MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("countries");

    }//end of Connect
    
    public void InsertNewDocument(String jsonDocument){
        
        Document newDocument = new Document(jsonDocument);
        Mongo
               

    }//end of InsertNewDocument
}//end of MongoDbHandler
