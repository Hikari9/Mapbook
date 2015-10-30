package com.mapbook.mapbookparser;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Map;

/**
 * Created by Rico on 10/30/2015.
 * See https://www.parse.com/docs/android/guide
 */
public class MapbookParse {
        public static void initialize(Context context) {
                Parse.enableLocalDatastore(context);
                Parse.initialize(context, "YyN0pW0gRrPy4vQYebW86IT03bYysrQZi8n5XkfC", "AjGE0VZly2K7Qja72nmAgQa0JAt02zpZPWlCN48g");
        }

        /**
         * Creates a new parse object with data map at specific table.
         * @param tableName the name of the table you want to create the object at
         * @param data key-value pairs for data to be inserted
         * @return the ParseObject that handled the creation of data
         */
        public static ParseObject createObject(String tableName, Map<String, Object> data) {
                ParseObject parseObject = new ParseObject(tableName);
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                        parseObject.put(entry.getKey(), entry.getValue());
                }
                parseObject.saveInBackground();
                return parseObject;
        }

        /**
         * Retrieves ParseObject from the Parse database with the specific object id.
         * @param tableName the name of the table where data is to be retrieved
         * @param objectId the objectId of the table data
         * @return the ParseObject representing the element row
         */

        public static ParseObject query(String tableName, String objectId) {
                return query(tableName, objectId, false);
        }

        /**
         * Retrieves ParseObject from the Parse database with the specific object id.
         * @param tableName the name of the table where data is to be retrieved
         * @param objectId the objectId of the table data
         * @param fromLocal denotes retrieval from online or local data store
         * @return the ParseObject representing the element row
         */

        public static ParseObject query(String tableName, String objectId, boolean fromLocal) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery(tableName);
                if (fromLocal)
                        query.fromLocalDatastore();
                try {return query.get(objectId);}
                catch (ParseException e) {return null;}

                /* get in background version
                query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                                if (e == null) {
                                        // object will be your game score
                                } else {
                                        // something went wrong
                                }
                        }
                });
                */
        }

}

/*
// Parse example

// Enable Local datastore.
Parse.enableLocalDatastore(this);

        // initialize parse
        Parse.initialize(this, "YyN0pW0gRrPy4vQYebW86IT03bYysrQZi8n5XkfC", "AjGE0VZly2K7Qja72nmAgQa0JAt02zpZPWlCN48g");

        // create parse test object
        ParseObject test = new ParseObject("TestObject");
        test.put("id", 94);
        test.put("column", "Not Hello World");
        test.saveInBackground();

        // delete parse object
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
        query.whereEqualTo("column", "Hello World");
        query.findInBackground(new FindCallback<ParseObject>() {
public void done(List<ParseObject> invites, ParseException e) {
        if (e == null) {
        // iterate over all messages and delete them
        for (ParseObject invite : invites) {
        invite.deleteInBackground();
        }
        } else {
        //Handle condition here
        }
        }
        });
        */