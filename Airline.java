//Nouf Alsadhan 441201104
//Jumanah Aldawsri 441201034
//Sarah Alkhurayyif 441200979
//Raghad Aldaen 441201046
//27228
//15/04/2021



import java.util.Scanner;
public class Airline {
private static int count=0;
private static Flight FlightsList[]=new Flight[100];
public static void main(String[]args){
Scanner input=new Scanner(System.in);

String[] citiesList = {"Dammam", "Jeddah", "Yanbu","Hail", "Abha","Tabuk", "Taif"};//we declared an array to make it easier for the user to choose the city
int ch;

System.out.println("Welcome to Riyadh (King Khalid Airport)");
System.out.print("**************");



do {
System.out.println();
System.out.println("Enter your choice");
System.out.println("1. Add a Flight");
System.out.println("2. Find a flight");
System.out.println("3. List all flights");
System.out.println("4. List flights for a given date");
System.out.println("5. Update Departure & Arrival Time");
System.out.println("6. Total number of flights");
System.out.println("7. Exit");

ch=input.nextInt();

switch(ch){
case 1:
String destination;
System.out.println("Enter destination number");
System.out.println("0-Dammam 1-Jeddah 2-Yanbu 3-Hail 4-Abha 5-Tabuk 6-Taif");
int num = input.nextInt();
if(num>=0 && num<=6)
destination=citiesList[num];
else
{
System.out.println("invalid city");
break;
}

System.out.println("Enter date in format dd/mm");
String date=input.next();
System.out.println("Enter gate number for boarding the plane");
int gate=input.nextInt();
System.out.println("Enter departure time in format hh:mm and in 24-hours system");
String dep=input.next();

boolean add=addFlight(destination,date,gate,dep);

if(add==true)//method addFlight will return true if it is allowed to add the flight
System.out.println("Flight been added sucessfully");
else
System.out.println("Cannot add flight");
break;


case 2:
System.out.println("How do you want to search for the flight?");
System.out.println("1.By Flight number");
System.out.println("2.By destination,gate,date and departure time");
int choice=input.nextInt();

if(choice==1){

System.out.println("Enter Flight number that you want to search for");
String Fnumber = input.next();
int loc =findFlight(Fnumber);
if(loc!=-1){
System.out.println("Flight found");
System.out.println("Flight is in location "+loc);}
else
System.out.println("Flight not found");

}
else

if(choice==2){
System.out.println("Enter destination,date,gate and departure time (in the order) ");
String destination2=input.next();
destination2=destination2.substring(0,1).toUpperCase()+destination2.substring(1).toLowerCase();
String date2=input.next();
int gate2=input.nextInt();
String departure2=input.next();
int Loc=findFlight(destination2,date2,gate2,departure2);
if(Loc!=-1){
System.out.println("Flight found");
System.out.println("Flight is in location "+Loc);}
else
System.out.println("Flight not found");

}
else
System.out.println("invalid input");

break;

case 3:
printAll();
break;

case 4:
System.out.println("Enter the flight date to search for in the following format (dd/mm)");
String Date =input.next();
printAll(Date);
break;

case 5:
System.out.println("Enter flight number, and departure time in the following format (hh:mm) in 24-hours system");
String Fnum=input.next();
String departure=input.next();
updateTime(Fnum,departure);
break;

case 6:
System.out.println("Total number of flights: "+getNumberOfFlights());
break;


case 7:
System.out.println("Thank you for choosing our system");
break;


}

} while(ch!=7);



}



public static boolean addFlight(String destination,String date,int gate,String departure){


//from line 121 to 126 and 136 to 139 date validation
if(date.length()!=5 || !(Character.isDigit(date.charAt(0))) || !(Character.isDigit(date.charAt(1))) )
return false;
if(date.charAt(2)!='/')
return false;
if(!(Character.isDigit(date.charAt(3))) || !(Character.isDigit(date.charAt(4))))
return false;

//from line 129 to 134 and 141 to 145 departure time validation
if(departure.length()!=5 || !(Character.isDigit(departure.charAt(0))) || !(Character.isDigit(departure.charAt(1))) )
return false;
if(departure.charAt(2)!=':')
return false;
if(!(Character.isDigit(departure.charAt(3))) || !(Character.isDigit(departure.charAt(4))))
return false;

int day = Integer.parseInt( date.substring( 0,2)) ; 
int month = Integer.parseInt( date.substring( 3)) ;
if( day<1 || day >31 || month <1 || month >12)
return false; 

int hours = Integer.parseInt( departure.substring( 0,2)) ; 
int minutes = Integer.parseInt( departure.substring( 3)) ;
 
if(hours > 23 || hours < 0 || minutes < 0 || minutes > 59)
return false ;

if(FlightsList.length==count)//Don't exceed 100
return false;

Flight f1 = new Flight(destination,date,gate,departure) ;//to make sure flight is not already in array

 int index = findFlight(destination,date,gate,departure) ; 
 if(index != -1)
 {
 System.out.println("Flight already exist");
 return false ; 
 }
 
 index = findFlight(f1.getFlightNumber());
  if(index != -1)
 {
 System.out.println("Flight already exist");
 return false ; 
 }
 
 FlightsList[count] = f1; 
 count++ ; 
 
 return true; 

}


public static int findFlight(String FlightNumber){//to make sure no flight with same flight number
int loc = -1;
for(int i = 0 ; i<count ; i++){
if(FlightsList[i].getFlightNumber().equals(FlightNumber))
loc=i;
}

return loc;

}


public static int findFlight(String destination,String date,int gate,String departure){//to make sure no flight with same destination,date,gate and departure time
int loc = -1;
for(int i = 0 ; i<count ; i++){
if(FlightsList[i].getDestination().equals(destination))
  if(FlightsList[i].getDate().equals(date))
     if(FlightsList[i].getGate()==gate)
        if(FlightsList[i].getDepartureTime().equals(departure))
       
       
loc=i;

}

return loc;

}


public static void printAll(){
if(count!=0) {

for(int i = 0 ; i<count ; i++)
FlightsList[i].printFlightInfo();

}

else

System.out.println("There is no flights to print");

}


public static void printAll(String date){
boolean found=false;
for(int i = 0 ; i<count ; i++) {
if(FlightsList[i].getDate().equals(date)) {
found=true;
FlightsList[i].printFlightInfo();
}
}
if(found==false)
System.out.println("Flights not found");


}

public static void updateTime(String FlightNumber, String departure){
int loc = findFlight(FlightNumber);

if(loc!=-1){
FlightsList[loc].setDepartureTime(departure);
System.out.println("Flight information after update");
FlightsList[loc].printFlightInfo();
}


else
System.out.println("Flight not found");
}



public static int getNumberOfFlights(){
return count;
}

}