import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Flight extends FlightDistance {

    private final String flightSchedule;
    private final String flightNumber;
    private final String fromWhichCity;
    private final String gate;
    private final String toWhichCity;
    private double distanceInMiles;
    private double distanceInKm;
    private String flightTime;
    private int numOfSeatsInTheFlight;
    //private List<Customer> listOfRegisteredCustomersInAFlight;
    private int customerIndex;
    static int nextFlightDay = 0;
    private static final List<Flight> flightList = new ArrayList<>();

    Flight() {
        this.flightSchedule = null;
        this.flightNumber = null;
        this.numOfSeatsInTheFlight = 0;
        toWhichCity = null;
        fromWhichCity = null;
        this.gate = null;
    }

    Flight(String flightSchedule, String flightNumber, int numOfSeatsInTheFlight, String[][] chosenDestinations, String[] distanceBetweenTheCities, String gate) {
        this.flightSchedule = flightSchedule;
        this.flightNumber = flightNumber;
        this.numOfSeatsInTheFlight = numOfSeatsInTheFlight;
        this.fromWhichCity = chosenDestinations[0][0];
        this.toWhichCity = chosenDestinations[1][0];
        this.distanceInMiles = Double.parseDouble(distanceBetweenTheCities[0]);
        this.distanceInKm = Double.parseDouble(distanceBetweenTheCities[1]);
        this.flightTime = FlightManager.calculateFlightTime(distanceInMiles);
        //this.listOfRegisteredCustomersInAFlight = new ArrayList<>();
        this.gate = gate;
    }

    public String fetchArrivalTime() {
        /*These lines convert the String of flightSchedule to LocalDateTIme and add the arrivalTime to it....*/
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, HH:mm a ");
        LocalDateTime departureDateTime = LocalDateTime.parse(flightSchedule, formatter);

        /*Getting the Flight Time, plane was in air*/
        String[] flightTime = getFlightTime().split(":");
        int hours = Integer.parseInt(flightTime[0]);
        int minutes = Integer.parseInt(flightTime[1]);


        LocalDateTime arrivalTime;

        arrivalTime = departureDateTime.plusHours(hours).plusMinutes(minutes);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("EE, dd-MM-yyyy HH:mm a");
        return arrivalTime.format(formatter1);

    }

    private double degreeToRadian(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double radianToDegree(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    @Override
    public String toString(int i) {
        return String.format("| %-5d| %-41s | %-9s | \t%-9s | %-21s | %-22s | %-10s  |   %-6sHrs |  %-4s  |  %-8s / %-11s|", i, flightSchedule, flightNumber, numOfSeatsInTheFlight, fromWhichCity, toWhichCity, fetchArrivalTime(), flightTime, gate, distanceInMiles, distanceInKm);
    }

    public int getNoOfSeats() {
        return numOfSeatsInTheFlight;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setNoOfSeatsInTheFlight(int numOfSeatsInTheFlight) {
        this.numOfSeatsInTheFlight = numOfSeatsInTheFlight;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public static List<Flight> getFlightList() {
        return flightList;
    }

    public String getFlightSchedule() {
        return flightSchedule;
    }

    public String getFromWhichCity() {
        return fromWhichCity;
    }

    public String getGate() {
        return gate;
    }

    public String getToWhichCity() {
        return toWhichCity;
    }

}