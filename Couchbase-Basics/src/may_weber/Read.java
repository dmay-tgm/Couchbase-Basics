package may_weber;

import static com.couchbase.client.java.query.Select.select;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;

/**
 * Testing read stuff
 * 
 * @author Daniel May
 * @version 1.0
 *
 */
public class Read {

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
		System.out.println("Getting document by id");
		JsonDocument loadedFromId = bucket.get("batman");
		System.out.println(loadedFromId.content());
		System.out.println("Getting data by view");
		ViewResult result = bucket.query(ViewQuery.from("dev_firstnames", "firstnames"));
		for (ViewRow row : result)
			System.out.println(row);
		System.out.println("Creating index");
		bucket.query(N1qlQuery.simple("CREATE PRIMARY INDEX ON `famous-persons` USING GSI"));
		System.out.println("Ad-hoc query");
		N1qlQueryResult queryResult = bucket.query(N1qlQuery.simple("SELECT job FROM `famous-persons`"));
		for (N1qlQueryRow row : queryResult)
			System.out.println(row);
		System.out.println("DSL query");
		N1qlQueryResult queryResult2 = bucket.query(select("age").from("`famous-persons`"));
		for (N1qlQueryRow row : queryResult2)
			System.out.println(row);
		System.out.println("disconnect");
		cluster.disconnect();
	}
}