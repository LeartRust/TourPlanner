import com.example.tourplanner.dataAccessLayer.database.*;
import com.example.tourplanner.models.TourLogModel;
import com.example.tourplanner.models.TourModel;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ModelTests {

    @Test
    public void createTourModel() {
        TourModel tour = new TourModel("testID", "TestTourModel","test123","Vienna","Madrid","Car","100km","false");
        assertTrue("TourName should be TestTourModel", tour.getTourName().equals("TestTourModel"));
    }

    @Test
    public void changeTourModelIdTourNameDescriptionFrom() {
        TourModel tour = new TourModel("testID", "TestTourModel","test123","Vienna","Madrid","Car","100km","false");
        tour.setTourId("randomId");
        tour.setTourName("randomName");
        tour.setTourDescription("randomDescription");
        tour.setTourFrom("randomFrom");
        assertTrue("TourId should be", tour.getTourId().equals("randomId"));
        assertTrue("TourName should be", tour.getTourName().equals("randomName"));
        assertTrue("Description should be", tour.getTourDescription().equals("randomDescription"));
        assertTrue("From should be", tour.getTourFrom().equals("randomFrom"));
    }

    @Test
    public void changeTourModelToTransportTypeDistanceFavorite() {
        TourModel tour = new TourModel("testID", "TestTourModel","test123","Vienna","Madrid","Car","100km","false");
        tour.setTourTo("randomTo");
        tour.setTourTransportType("randomTransportType");
        tour.setTourDistance("randomDistance");
        tour.setIsFavorite("randomFavorite");
        assertTrue("To should be", tour.getTourTo().equals("randomTo"));
        assertTrue("TransportType should be", tour.getTourTransportType().equals("randomTransportType"));
        assertTrue("Distance should be", tour.getTourDistance().equals("randomDistance"));
        assertTrue("Favorite should be", tour.getIsFavorite().equals("randomFavorite"));
    }

    @Test
    public void createTourLogModel() {
        TourLogModel tourLog = new TourLogModel("testID", "TestTourModel","random Date","Vienna","easy","100","100","100");
        assertTrue("TourName should be TestTourModel", tourLog.getTourName().equals("TestTourModel"));
    }

    @Test
    public void changeTourLogModel() {
        TourLogModel tourLog = new TourLogModel("testID", "TestTourModel","random Date","Vienna","easy","100","100","100");
        tourLog.setLogId("randomId");
        tourLog.setTourName("randomName");
        tourLog.setDateTime("randomDateTime");
        tourLog.setComment("randomComment");
        assertTrue("TourId should be", tourLog.getLogId().equals("randomId"));
        assertTrue("TourName should be", tourLog.getTourName().equals("randomName"));
        assertTrue("Description should be", tourLog.getDateTime().equals("randomDateTime"));
        assertTrue("From should be", tourLog.getComment().equals("randomComment"));
    }

    @Test
    public void changeTourLogModel2() {
        TourLogModel tourLog = new TourLogModel("testID", "TestTourModel","random Date","Vienna","easy","100","100","100");
        tourLog.setDifficulty("randomDifficulty");
        tourLog.setTotalTime("randomTotalTime");
        tourLog.setRating("randomRating");
        tourLog.setDistance("randomDistance");

        assertTrue("To should be", tourLog.getDifficulty().equals("randomDifficulty"));
        assertTrue("TransportType should be", tourLog.getTotalTime().equals("randomTotalTime"));
        assertTrue("Distance should be", tourLog.getRating().equals("randomRating"));
        assertTrue("Favorite should be", tourLog.getDistance().equals("randomDistance"));
    }


}
