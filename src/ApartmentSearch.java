import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;

public class ApartmentSearch {
    public static class UnsuitableApartmentsException extends Exception {
        public UnsuitableApartmentsException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String[] cities = {"Sofia", "Varna", "Burgas"};
        int minRooms = 3;
        int minsqrmeters = 100;
        int maxResults = 5;
        String outputFile = "apartment_Brokers.txt";
        Map<String, Set<String>> cityBrokers = new HashMap<>();
        Set<String> citiesWMostListings = new HashSet<>();

        try (FileWriter writer = new FileWriter(outputFile)) {

            Path resource = Paths.get("src", "bg");
            String absolutepath = resource.toFile().getAbsolutePath();
            File file = new File(absolutepath + "/testData_Apartments.txt");
            sc = new Scanner(file);

            while (sc.hasNext()) {

                String city = sc.next().trim();
                int rooms = sc.nextInt();
                int sqrmeters = sc.nextInt();
                int price = sc.nextInt();
                String phone = sc.next();

                if (rooms == minRooms && sqrmeters > minsqrmeters) {
                    cityBrokers.computeIfAbsent(city, k -> new HashSet<>()).add(phone);
                    citiesWMostListings.add(city);
                }
            }
            boolean anyRes = false;
            for (String city : cities) {
                if (cityBrokers.containsKey(city)) {
                    anyRes = true;
                    writer.write("Grad:" + city);
                    int count = 0;
                    for (String broker : cityBrokers.get(city)) {
                        writer.write(broker + "/n");
                        count++;
                        if (count == maxResults) {
                            break;
                        }
                    }
                    writer.write("/n");
                }
            }
            if (!anyRes) {
                throw new UnsuitableApartmentsException("Nqma apartamenti, spete na ulicata :(");
            }
            int maxAparm = 0;
            for (String city : citiesWMostListings) {
                int count = cityBrokers.get(city).size();
                if (count > maxAparm) {
                    maxAparm = count;
                    citiesWMostListings.clear();
                    citiesWMostListings.add(city);
                } else if (count == maxAparm) {
                    citiesWMostListings.add(city);
                }
            }
            writer.write("Gradowe s naj mn apartamenti:" + String.join(", ", citiesWMostListings));
        } catch (FileNotFoundException e) {
            System.out.println("greshka >:(");
        } catch (IOException e) {
            System.out.println("koj znae");
        } catch (UnsuitableApartmentsException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }
}

