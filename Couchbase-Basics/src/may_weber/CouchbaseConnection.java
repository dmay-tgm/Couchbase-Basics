package may_weber;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

/**
 * Testing a Couchbase Connection
 * 
 * @author Daniel May
 * @version 1.0
 *
 */
public class CouchbaseConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Create connection");
		Cluster cluster = CouchbaseCluster.create("192.168.38.140");
		System.out.println("Try to open Bucket");
		Bucket bucket = cluster.openBucket();
		System.out.println("Generating JSON Object");
		JsonObject user = JsonObject.empty().put("firstname", "Walter").put("lastname", "White")
				.put("job", "chemistry teacher").put("age", 50);
		System.out.println("Creating JSON Document");
		JsonDocument doc = JsonDocument.create("walter", user);
		System.out.println("Upload it to the server");
		JsonDocument response = bucket.upsert(doc);
		System.out.println("getting document");
		JsonDocument walter = bucket.get("walter");
		System.out.println("Found: " + walter);
		System.out.println("disconnect");
		cluster.disconnect();
	}
}