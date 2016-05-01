package may_weber;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

/**
 * Testing update stuff
 * 
 * @author Daniel May
 * @version 1.0
 *
 */
public class Update {

	/**
	 * Main function for testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Create connection");
		Cluster cluster = CouchbaseCluster.create("192.168.38.140");
		System.out.println("Try to open Bucket");
		Bucket bucket = cluster.openBucket("famous-persons");
		System.out.println("Creating JSON object");
		JsonObject user2 = JsonObject.empty().put("firstname", "Bruce").put("lastname", "Wayne").put("job", "batman")
				.put("age", 42).put("nickname", "Dark Knight");
		System.out.println("Creating JSON Document");
		JsonDocument doc2 = JsonDocument.create("batman", user2);
		System.out.println("Upload it to the server");
		JsonDocument response = bucket.replace(doc2);
		System.out.println("disconnect");
		cluster.disconnect();
	}
}