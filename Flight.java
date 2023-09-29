public class Flight {
private String flightNumber;
private String destination;
private int gate;
private String date;
private String departureTime;
private String arrivalTime;
private static int totalFlights=0;


public Flight(){//default constructor

totalFlights++;
gate = 0;
flightNumber = "";
destination = "";
date= "";
departureTime= "";
arrivalTime= "";


}


public Flight(String destination , String date, int gate, String departure) {

totalFlights++;
this.destination=destination;
this.date=date;
this.gate=gate;
departureTime=departure;

calculateArrivalTime() ; 
generateFlightNumber() ; 

}



public void setDestination(String destination){
this.destination=destination;
}


public void setDate( String date){
this.date=date;
}


public void setGate(int gate){
this.gate=gate;
}


public void setDepartureTime(String departureTime){
this.departureTime=departureTime;
calculateArrivalTime();
}


public String getDestination(){
return destination;
}



public String getDate(){
return date;
}



public int getGate(){
return gate;
}



public String getDepartureTime(){
return departureTime;
}


public String getArrivalTime(){

return arrivalTime;
}



public String getFlightNumber(){
return flightNumber;
}



private void generateFlightNumber() {



String ch3 = destination.substring(0,3) ;
ch3= ch3.toUpperCase();
flightNumber=""+ch3+"00"+totalFlights ;


}




private void calculateArrivalTime() {
int loc = departureTime.indexOf(":");
String hr = departureTime.substring(0,loc);
String min= departureTime.substring(loc+1);

int hours = Integer.parseInt(hr);
int minutes = Integer.parseInt(min);
int time=0;
String str;

switch (destination) {
case "Dammam":
time = 65;
break;

case "Jeddah":
case "Yanbu":
case "Abha":
time = 105;
break;

case "Hail":
time = 75;
break;

case "Tabuk":
time = 80;
break;

case "Taif":
time= 95;
break;


}

boolean newDay=false;


int h = time / 60;
int m = time % 60;

minutes=minutes+m;
hours=hours+h;


if(minutes>59)
{

hours=hours+h;
minutes=minutes-60;
}

if(hours>23)
{
newDay=true;
hours=hours-24;

}

if(minutes<10 && hours<10)
str="0"+(hours)+":0"+(minutes);
else
if(hours<10)
str="0"+(hours)+":"+(minutes);
else
if(minutes<10)
str=""+(hours)+":0"+(minutes);
else
str=""+(hours)+":"+(minutes);


if(newDay==true)
str=str+"+1";

arrivalTime=str;
}




public void printFlightInfo(){



System.out.printf("%-15s %-15s %-15s (%d) %n" , "Flight Number:" , flightNumber , "Gate: " , gate);
System.out.printf("%-15s %-15s %-15s %-15s %n" , "Destination:" , destination , "Date: " , date);
System.out.printf("%-15s %-15s %-15s %-15s %n" , "Departure Time:", departureTime , "Arrival Time: " , arrivalTime);


}



}
















