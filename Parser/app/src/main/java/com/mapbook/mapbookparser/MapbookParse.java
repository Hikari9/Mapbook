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

        /**
         * Setting cache policy
         * https://www.parse.com/docs/android/guide#queries
         *
         * Parse provides several different cache policies:
         * IGNORE_CACHE: The query does not load from the cache or save results to the cache. IGNORE_CACHE is the default cache policy.
         * CACHE_ONLY: The query only loads from the cache, ignoring the network. If there are no cached results, that causes a ParseException.
         * NETWORK_ONLY: The query does not load from the cache, but it will save results to the cache.
         * CACHE_ELSE_NETWORK: The query first tries to load from the cache, but if that fails, it loads results from the network. If neither cache nor network succeed, there is a ParseException.
         * NETWORK_ELSE_CACHE: The query first tries to load from the network, but if that fails, it loads results from the cache. If neither network nor cache succeed, there is a ParseException.
         * CACHE_THEN_NETWORK: The query first loads from the cache, then loads from the network. In this case, the FindCallback will actually be called twice - first with the cached results, then with the network results. This cache policy can only be used asynchronously with findInBackground.
         *
        public static ParseQuery<ParseObject> getQuery() {
                ParseQuery<ParseObject> query = ParseQuery.getQuery()
                query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
                return query;
                /*
                query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                                if (e == null) {
                                        // Results were successfully found, looking first on the
                                        // network and then on disk.
                                } else {
                                        // The network was inaccessible and we have no cached data
                                        // for this query.
                                }
                        }
                });
        }*/
}
