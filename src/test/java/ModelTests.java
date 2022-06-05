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
    public void changeTourModelIdTourName() {
        TourModel tour = new TourModel("testID", "TestTourModel","test123","Vienna","Madrid","Car","100km","false");
        tour.setTourId("randomId");
        tour.setTourName("randomName");
        assertTrue("TourId should be", tour.getTourId().equals("randomId"));
        assertTrue("TourName should be", tour.getTourName().equals("randomName"));
    }

    @Test
    public void changeTourModelDescriptionFrom() {
        TourModel tour = new TourModel("testID", "TestTourModel","test123","Vienna","Madrid","Car","100km","false");
        tour.setTourDescription("randomDescription");
        tour.setTourFrom("randomFrom");
        assertTrue("Description should be", tour.getTourDescription().equals("randomDescription"));
        assertTrue("From should be", tour.getTourFrom().equals("randomFrom"));
    }

    @Test
    public void changeTourModelToTransportType() {
        TourModel tour = new TourModel("testID", "TestTourModel","test123","Vienna","Madrid","Car","100km","false");
        tour.setTourTo("randomTo");
        tour.setTourTransportType("randomTransportType");
        assertTrue("To should be", tour.getTourTo().equals("randomTo"));
        assertTrue("TransportType should be", tour.getTourTransportType().equals("randomTransportType"));
    }

    @Test
    public void changeTourModelDistanceFavorite() {
        TourModel tour = new TourModel("testID", "TestTourModel","test123","Vienna","Madrid","Car","100km","false");
        tour.setTourDistance("randomDistance");
        tour.setIsFavorite("randomFavorite");
        assertTrue("Distance should be", tour.getTourDistance().equals("randomDistance"));
        assertTrue("Favorite should be", tour.getIsFavorite().equals("randomFavorite"));
    }

    @Test
    public void createTourLogModel() {
        TourLogModel tourLog = new TourLogModel("testID", "TestTourModel","random Date","Vienna","easy","100","100","100");
        assertTrue("TourName should be TestTourModel", tourLog.getTourName().equals("TestTourModel"));
    }

    @Test
    public void changeTourLogModelIdTourName() {
        TourLogModel tourLog = new TourLogModel("testID", "TestTourModel","random Date","Vienna","easy","100","100","100");
        tourLog.setLogId("randomId");
        tourLog.setTourName("randomName");
        assertTrue("TourId should be", tourLog.getLogId().equals("randomId"));
        assertTrue("TourName should be", tourLog.getTourName().equals("randomName"));
    }

    @Test
    public void changeTourLogModelDateTimeComment() {
        TourLogModel tourLog = new TourLogModel("testID", "TestTourModel","random Date","Vienna","easy","100","100","100");
        tourLog.setDateTime("randomDateTime");
        tourLog.setComment("randomComment");
        assertTrue("Description should be", tourLog.getDateTime().equals("randomDateTime"));
        assertTrue("From should be", tourLog.getComment().equals("randomComment"));
    }

    @Test
    public void changeTourLogModelDifficultyTotalTime() {
        TourLogModel tourLog = new TourLogModel("testID", "TestTourModel","random Date","Vienna","easy","100","100","100");
        tourLog.setDifficulty("randomDifficulty");
        tourLog.setTotalTime("randomTotalTime");
        assertTrue("To should be", tourLog.getDifficulty().equals("randomDifficulty"));
        assertTrue("TransportType should be", tourLog.getTotalTime().equals("randomTotalTime"));
    }

    @Test
    public void changeTourLogModelRatingDistance() {
        TourLogModel tourLog = new TourLogModel("testID", "TestTourModel","random Date","Vienna","easy","100","100","100");
        tourLog.setRating("randomRating");
        tourLog.setDistance("randomDistance");
        assertTrue("Distance should be", tourLog.getRating().equals("randomRating"));
        assertTrue("Favorite should be", tourLog.getDistance().equals("randomDistance"));
    }


}
