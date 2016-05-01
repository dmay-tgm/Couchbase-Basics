package may_weber;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.cluster.ClusterManager;
import com.couchbase.client.java.document.JsonDocument;

/**
 * Testing delete stuff
 * 
 * @author Daniel May
 * @version 1.0
 *
 */
public class Delete {

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
		System.out.println("Removing document");
		JsonDocument doc = bucket.remove("batman");
		System.out.println("Closing bucket");
		bucket.close();
		System.out.println("Creating Cluster Manager");
		ClusterManager clusterManager = cluster.clusterManager("Administrator", "secretpassword");
		System.out.println("Removing bucket");
		clusterManager.removeBucket("famous-persons");
		System.out.println("disconnect");
		cluster.disconnect();
	}
}