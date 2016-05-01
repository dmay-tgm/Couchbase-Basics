package may_weber;

import java.util.Arrays;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.bucket.BucketManager;
import com.couchbase.client.java.bucket.BucketType;
import com.couchbase.client.java.cluster.BucketSettings;
import com.couchbase.client.java.cluster.ClusterManager;
import com.couchbase.client.java.cluster.DefaultBucketSettings;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.view.DefaultView;
import com.couchbase.client.java.view.DesignDocument;

/**
 * Testing create stuff
 * 
 * @author Daniel May
 * @version 1.0
 *
 */
public class Create {

	/**
	 * Main function for testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Create connection");
		Cluster cluster = CouchbaseCluster.create("192.168.38.140");
		System.out.println("Creating Cluster Manager");
		ClusterManager clusterManager = cluster.clusterManager("Administrator", "secretpassword");
		System.out.println("Creating bucket settings");
		BucketSettings bucketSettings = new DefaultBucketSettings.Builder().type(BucketType.COUCHBASE)
				.name("famous-persons").password("").quota(100).replicas(1).indexReplicas(false).enableFlush(false)
				.build();
		System.out.println("Creating new Bucket");
		clusterManager.insertBucket(bucketSettings);
		System.out.println("Try to open Bucket");
		Bucket bucket = cluster.openBucket("famous-persons");
		System.out.println("Generating JSON Object");
		JsonObject user = JsonObject.empty().put("firstname", "Walter").put("lastname", "White")
				.put("job", "chemistry teacher").put("age", 50);
		System.out.println("Creating JSON Document");
		System.out.println("Generating JSON Object");
		JsonObject user2 = JsonObject.empty().put("firstname", "Bruce").put("lastname", "Wayne").put("job", "batman")
				.put("age", 42);
		System.out.println("Creating JSON Document");
		JsonDocument doc = JsonDocument.create("walter", user);
		System.out.println("Creating JSON Document");
		JsonDocument doc2 = JsonDocument.create("batman", user2);
		System.out.println("Upload it to the server");
		JsonDocument response = bucket.upsert(doc);
		System.out.println("Upload it to the server");
		JsonDocument response2 = bucket.upsert(doc2);
		System.out.println("Get BucketManager");
		BucketManager bucketManager = bucket.bucketManager();
		System.out.println("Generating view");
		DesignDocument designDoc = DesignDocument.create("dev_firstnames", Arrays.asList(
				DefaultView.create("firstnames", "function (doc) { if (doc.firstname) { emit(doc.firstname); } }")));
		System.out.println("Inserting view");
		bucketManager.insertDesignDocument(designDoc);
		System.out.println("disconnect");
		cluster.disconnect();
	}
}