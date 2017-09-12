

package theTrails;

import colors.color;


public class LinkedList {
	public String name, state; 
	public int rating;
	public double latitude, longitude;
	public boolean haveHiked; 
	public String[] landmarks; 
	public LinkedList next;
	public static int size = 0;
	public static LinkedList head = new LinkedList();
	public static int start =0;
	
	public LinkedList(){}
	
	public LinkedList( String theName, String theState, int theRating, double theLat, double theLong, boolean hiked, String[] sights, LinkedList next){
		this.name = theName;
		this.state = theState;
		this.rating = theRating;
		this.latitude = theLat;
		this.longitude = theLong;
		this.haveHiked = hiked;
		this.landmarks = sights;
		this.next = next;
		size++;
		
	}
	
	public static void promptsForNewTrail(){
		System.out.println("What is the name of the trail?");
		String name = TextIO.getln();
		name = name.trim();
		System.out.println("How would you rate it? "+color.ANSI_BLUE+"(*must be a number between 1-5)"+color.ANSI_RESET);
		int rating = TextIO.getlnInt();
		while (rating<1 || rating>5){
			System.out.println(color.ANSI_RED+"Please enter an number within the given range."+color.ANSI_RESET);
			rating = TextIO.getlnInt();
		}
		System.out.println("Latitude: "+color.ANSI_BLUE+"(0-90)"+color.ANSI_RESET);
		double latitude = (double)TextIO.getlnDouble();
		while (latitude<0 || latitude>90){
			System.out.println(color.ANSI_RED+"Please enter an number within the given range."+color.ANSI_RESET);
			latitude = (double)TextIO.getlnDouble();
		}
		System.out.println("Longitude: "+color.ANSI_BLUE+"(0-180)"+color.ANSI_RESET);
		double longitude = (double)TextIO.getlnDouble();
		while (longitude<0 || longitude>180){
			System.out.println(color.ANSI_RED+"Please enter an number within the given range."+color.ANSI_RESET);
			longitude = (double)TextIO.getDouble();
		}
		System.out.println("State: "+color.ANSI_BLUE+"(Must be two letter abbreviation)"+color.ANSI_RESET);
		String state = TextIO.getln();
		while (state.length()!=2){
			System.out.println(color.ANSI_RED+"Please enter a two(2) letter abbreviation"+color.ANSI_RESET);
			state = TextIO.getln();
		}
		state = state.trim();
		
		System.out.println("Have you hiked this trail before? "+color.ANSI_BLUE+"(Please type 'y' or '0' for yes, and 'n' or '1' for no)"+color.ANSI_RESET);
		boolean hiked = TextIO.getlnBoolean();

		
		String sights[] = new String[15];
		int numCount = 0;
		boolean go = true;
		System.out.println("Any landmarks?");
		
		while (go){
			String landmark = TextIO.getln();
			landmark = landmark.trim();
			if (landmark.length()==0){
				go = false;
			}
			if (landmark.length()!=0){
				sights[numCount]=landmark;
				numCount++;
			}
		}
		if (start == 0){
			head = new LinkedList(name, state, rating, latitude, longitude, hiked,sights, null);
			start++;
		}
		else{
			new LinkedList(name, state, rating, latitude, longitude, hiked,sights, null).insert();
		
		}
		
	}
	
	public static void remove(){
		System.out.println("Which trail would you like to remove?");
		String trail = TextIO.getln();
		trail = trail.trim();
		if (head.name.equalsIgnoreCase(trail)){
			head = head.next;
			System.out.println(trail+" has been removed from the LinkedList.\n\n");
			return;
		}
		if (head.name.equalsIgnoreCase(trail) && head.next==null){
			head = null;
			start--;
			System.out.println(trail+" has been removed from the LinkedList.\n\n");
		}
		if (!head.name.equalsIgnoreCase(trail) && head.next== null){
			System.out.println("This trail does not exist in our database.\n\n");
			return;
		}
		else{
			head.next.toRemove(trail);
		}
	}
	
	public static LinkedList back = head;
	public static LinkedList front;
	public void toRemove(String trail){
		front = this.next;
		if (start==0){
			System.out.println(color.ANSI_RED+"This trail does not exist in our database.\n\n"+color.ANSI_RESET);
			return; 
		}
		if (!this.name.equalsIgnoreCase(trail) && this.next == null){
			System.out.println(color.ANSI_RED+"This trail does not exist in our database.\n\n"+color.ANSI_RESET);
			return; 
		}
		if (this.name.equalsIgnoreCase(trail)){
			back.next = front;
			System.out.println(trail+" has been removed from the LinkedList.\n\n");
			return;
		}
		else{
			back = this;
			this.next.toRemove(trail);
		}
			
		
	}
	
	
	public void insert(){
		
		LinkedList previous = null;
	    LinkedList current = head;

	    while(current != null && current.name.compareToIgnoreCase(this.name) <= 0){
	        previous = current;
	        current = current.next;
	    }

	    if(previous == null){
	        head = this;
	    }
	    else{
	        previous.next = this;
	    }
	    this.next = current;
	}
	
	public void printTrails(){
		if (start==0){
			System.out.println(color.ANSI_RED+"No trails in database.\n\n"+color.ANSI_RESET);
			return; 
		}
		if (this.next == null){
			if (this.haveHiked == true){
				System.out.print("*");
			}
			System.out.println(color.ANSI_BLUE+color.underline+this.name+color.ANSI_RESET+", "+this.state+"("+this.rating+")\n"+
	                   "    Latitude: "+this.latitude+
	                   "\n    Longtidue: "+this.longitude+
	                   "\nLandmarks: ");
			for (int j = 0; j<this.landmarks.length; j++){
					if (landmarks[j]!=null){
						System.out.println("    "+(j+1)+") "+landmarks[j]);
					}
			}
			System.out.println();
			System.out.println(color.ANSI_RED + "All Trails Have Been Printed." + color.ANSI_RESET);
			return;
		}
		if (this.haveHiked == true){
			System.out.print("*");
		}
		System.out.println(color.ANSI_BLUE+color.underline+this.name+color.ANSI_RESET+", "+this.state+"("+this.rating+")\n"+
		                   "    Latitude: "+this.latitude+
		                   "\n    Longtidue: "+this.longitude+
		                   "\nLandmarks: ");
		for (int i = 0; i<this.landmarks.length; i++){
			if (landmarks[i]!=null){
				System.out.println("    "+(i+1)+") "+landmarks[i]);
			}
		}
		System.out.println();
		
		this.next.printTrails();
	}
	
	public static int getRating(){
		System.out.println("Please enter a specified rating: "+color.ANSI_BLUE+"(Must be between 1-5)"+color.ANSI_RESET);
		int rating = TextIO.getlnInt();
		while (rating<1 || rating>5){
			System.out.println(color.ANSI_RED+"Please enter a number within the given range."+color.ANSI_RESET);
			rating = TextIO.getlnInt();
		}
		return rating;
	}
	
	public static int countOfRated = 0;
	
	public void filter(int rating){
		if (start==0){
			System.out.println(color.ANSI_RED+"No trails in database.\n\n"+color.ANSI_RESET);
			return; 
		}
		if (this.next == null){
			if(this.rating > rating || this.rating == rating){
				if (this.haveHiked == true){
					System.out.print("*");
				}
				System.out.println(color.ANSI_BLUE+color.underline+this.name+color.ANSI_RESET+", "+this.state+"("+this.rating+")\n"+
		                   "    Latitude: "+this.latitude+
		                   "\n    Longtidue: "+this.longitude+
		                   "\nLandmarks: ");
				for (int i = 0; i<this.landmarks.length; i++){
					if (landmarks[i]!=null){
						System.out.println("    "+(i+1)+") "+landmarks[i]);
					}
				}
				System.out.println();
				return;
			}
			if(countOfRated == 0){
				System.out.println(color.ANSI_RED+"Sorry, no trails meet or exceed that rating."+color.ANSI_RESET);
				return;
			}
			return;
		}
		if (this.rating > rating || this.rating == rating){
			if (this.haveHiked == true){
				System.out.print("*");
			}
			System.out.println(color.ANSI_BLUE+color.underline+this.name+color.ANSI_RESET+", "+this.state+"("+this.rating+")\n"+
	                   "    Latitude: "+this.latitude+
	                   "\n    Longtidue: "+this.longitude+
	                   "\nLandmarks: ");
			for (int i = 0; i<this.landmarks.length; i++){
				if (landmarks[i]!=null){
					System.out.println("    "+(i+1)+") "+landmarks[i]);
				}
			}
			System.out.println();
			countOfRated++;
		}
		this.next.filter(rating);
	}
	
	public static String getState(){
		System.out.println("Please enter a two(2) letter abbreviation of the state.");
		String state = TextIO.getln();
		while (state.length()!=2){
			System.out.println(color.ANSI_RED+"Please enter a two(2) letter abbreviation"+color.ANSI_RESET);
			state = TextIO.getln();
		}
		return state.trim();
	}
	
	public static int countOfState=0;
	
	public void findTrailsInSameState(String state){
		if (start==0){
			System.out.println(color.ANSI_RED+"No trails in database.\n\n"+color.ANSI_RESET);
			return; 
		}
		if (this.next==null){
			if (this.state.equalsIgnoreCase(state) && this.haveHiked == false){
				System.out.println(this.name);
				return;
			}
			if (countOfState==0){
				System.out.println(color.ANSI_RED+"Sorry, no trails are located in your state."+color.ANSI_RESET);
				return;
			}
			return;
		}
		if (this.state.equalsIgnoreCase(state) && this.haveHiked == false){
			System.out.println(this.name);
			countOfState++;
		}
		this.next.findTrailsInSameState(state);
	}
	


public static LinkedList tempShortest;
public static double shortDist = 1000000000;


public static String getTrail(){
	System.out.println("Which trail would you like to use as base?");
	String trail = TextIO.getln();
	trail = trail.trim();
	return trail;
}

public LinkedList findNode(String trail){
	if (this.name == null){
		System.out.println(color.ANSI_RED+"Sorry, no trails have been added yet at this time, please check back later."+color.ANSI_RESET);
		return null;
	}
	if (this.next == null){
		if (this.name.equalsIgnoreCase(trail)){
			return this;
		}
		else{
			System.out.println(color.ANSI_RED+"Sorry, this trail does not exist."+color.ANSI_RESET);
			return null;
		}
	}
	if (this.name.equalsIgnoreCase(trail)){
		return this;
	}
	return this.next.findNode(trail);
}

public void calcDistance(LinkedList node){
	if (this.name == null){
		return;
	}
	double phi1 = this.latitude;
	double phi2 = node.latitude;
	double ro1 = this.longitude;
	double ro2 = node.longitude;
	double phi = Math.abs(phi1 - phi2);
	double ro = Math.abs(ro1 - ro2);
	double angle = 1 / ( Math.cos((Math.sin(phi1)*Math.sin(phi2)) + ((Math.cos(phi1)*Math.cos(phi2)*Math.cos(ro)))));
	double distance = 3959 * angle;
	if (distance < shortDist && this.name!=node.name){
		shortDist = distance;
		tempShortest = this;		
	}
	if (this.next == null){
		System.out.println("The closest trail to you is: "+ shortDist + " miles away");
		if (tempShortest.haveHiked == true){
			System.out.print("*");
		}
		System.out.println(color.ANSI_BLUE+color.underline+tempShortest.name+color.ANSI_RESET+", "+tempShortest.state+"("+tempShortest.rating+")\n"+
                   "    Latitude: "+tempShortest.latitude+
                   "\n    Longtidue: "+tempShortest.longitude+
                   "\nLandmarks: ");
		for (int i = 0; i<tempShortest.landmarks.length; i++){
			if (tempShortest.landmarks[i]!=null){
				System.out.println("    "+(i+1)+") "+tempShortest.landmarks[i]);
			}
		}
		return;	
	}
	this.next.calcDistance(node);
}

public static String findLandmark(){
	System.out.println("What landmark are you looking for?");
	String landmark = TextIO.getln();
	landmark = landmark.trim();
	while (landmark.length()==0){
		System.out.println(color.ANSI_RED+"You have not provided us a landmark, please enter a landmark."+color.ANSI_RESET);
		landmark = TextIO.getln().trim();
	}
	return landmark;
}

public void findLandmark(String mark){
	if (this.name == null){
		System.out.println(color.ANSI_RED+"Sorry, no trails have been added yet at this time, please check back later."+color.ANSI_RESET);
		return;
	}
	if (this.landmarks[0]!=null)
	{for (int i = 0; i<this.landmarks.length; i++){
		if (this.landmarks[i]!=null && this.landmarks[i].equalsIgnoreCase(mark)){
			if (this.haveHiked == true){
				System.out.print("*");
			}
			System.out.println(color.ANSI_BLUE+color.underline+this.name+color.ANSI_RESET+", "+this.state+"("+this.rating+")\n"+
	                   "    Latitude: "+this.latitude+
	                   "\n    Longtidue: "+this.longitude+
	                   "\nLandmarks: ");
			for (int j = 0; j<this.landmarks.length; j++){
				if (landmarks[j]!=null){
					System.out.println("    "+(j+1)+") "+landmarks[j]);
				}
			}
			break;
		}
	}}
	if (this.next == null){
		return;
	}
	this.next.findLandmark(mark);
}

public static String best = "";
public static int bestRating = -1;
public static int unhiked = 0;

public void findUnhiked(){
	if (this.name == null){
		System.out.println(color.ANSI_RED+"Sorry, no trails have been added yet at this time, please check back later."+color.ANSI_RESET);
		return;
	}
	if (this.haveHiked == false){
		unhiked++;
		System.out.println(color.ANSI_BLUE+color.underline+this.name+color.ANSI_RESET+", "+this.state+"("+this.rating+")\n"+
                "    Latitude: "+this.latitude+
                "\n    Longtidue: "+this.longitude+
                "\nLandmarks: ");
		for (int j = 0; j<this.landmarks.length; j++){
			if (landmarks[j]!=null){
				System.out.println("    "+(j+1)+") "+landmarks[j]);
			}
		}
		if (this.rating > bestRating){
			bestRating = this.rating;
			best = this.name;
		}
	}
	if (this.next == null){
		if (unhiked == 0){
			System.out.println("\n\n\n"+color.ANSI_YELLOW_BACKGROUND+color.underline+"YOU HAVE HIKED ALL TRAILS, CONGRATULATIONS!!!!"+color.ANSI_RESET+"\n\n\n");
			return;
		}
		System.out.println(color.ANSI_BLUE+"We recommend hiking: "+color.ANSI_RESET+ best);
		return;
	}
	this.next.findUnhiked();
}

	
	public static void main(String[] args){
		boolean go = true;
		while (go == true){
			System.out.println(color.ANSI_CYAN_BACKGROUND+"Welcome to Happy Trails! Select an option below:"+color.ANSI_RESET);
			System.out.println("1) add a hiking trail");
			System.out.println("2) remove a hiking trail");
			System.out.println("3) display hiking trails alphabetically");
			System.out.println("4) display hiking trails >= a certain rating");
			System.out.println("5) display hiking trails in a specified state");
			System.out.println("6) display the nearest hiking trail to specified hiking trail");
			System.out.println("7) list all trails with a specified landmark");
			System.out.println("8) list all hiking trails the user has yet to hike");
			System.out.println("9) quit");
			System.out.println("Select an option above:");
			int chosen = TextIO.getlnInt();
			if (chosen == 1){
				promptsForNewTrail();
			}
			if (chosen == 2){
				head.printTrails();
				remove();
			}
			if (chosen == 3){
				if (head==null){
					System.out.println(color.ANSI_RED+"No trails in database."+color.ANSI_RESET);
					continue;
				}
				head.printTrails();
			}
			if (chosen == 4){
				head.filter(getRating());
			}
			if (chosen == 5){
				head.findTrailsInSameState(getState());
			}
			if (chosen == 6){
				head.calcDistance(head.findNode(getTrail()));
			}
			if (chosen == 7){
				head.findLandmark(findLandmark());
			}
			if (chosen == 8){
				head.findUnhiked();
			}
			if (chosen == 9){
				System.out.println("Goodbye!");
				return;
			}
			
		}
	}
}