package may_weber;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.cluster.BucketSettings;
import com.couchbase.client.java.cluster.ClusterManager;

/**
 * Testing a administrative tasks
 * 
 * @author Daniel May
 * @version 1.0
 *
 */
public class Administration {

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
		System.out.println("Getting all Buckets: ");
		for (BucketSettings bs : clusterManager.getBuckets())
			System.out.println(bs);
		System.out.println("disconnect");
		cluster.disconnect();
	}
}