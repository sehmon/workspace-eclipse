
import java.util.List;
import java.util.Scanner;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class Search {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    
	    Scanner s = new Scanner(System.in);
	    
		// Sets up the Access keys for twitter
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("EuewTTqzKoumoALW4UubWw")
		  .setOAuthConsumerSecret("eoWcJwrkoGMRzC2geApAlywFbAfVOwXsmgLRJwALE")
		  .setOAuthAccessToken("2370842202-pFSJTXxvSaIaiMgLVeMn4GoUDNzF2u0KmZ73poL")
		  .setOAuthAccessTokenSecret("A4T6gcyGTUarVyiuhUc4PCyRZAde0rx69KhEi26tfozal");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		System.out.print("Please Type in a handle to serach for ");
		String searchName = s.next();
		
		System.out.print("How many tweets do you want to search for? ");
		int i = s.nextInt();
		
		
		
	    try{
	        System.out.println("Getting " + i + " tweets from " + searchName + ":\n" +
	        		"-----------------------------------------------------------------\n");
	    	List<Status> statuses = twitter.getUserTimeline(searchName,new Paging(1,i));
	    	for(Status a:statuses){
	    		System.out.println(a.getUser().getName() + ": " + a.getText() + 
	    				"\n" + "Favorites: " + a.getFavoriteCount() + " " + "Retweets: " 
	    				+ a.getRetweetCount() +"\n" + a.getCreatedAt() + "\n");
	    	
	    	}
	    		
	    	
	    } catch (Exception e){
	    	System.out.println(e);
	    }

	}

}
