import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.dataAccessLayer.database.*;
import com.example.tourplanner.models.TourLogModel;
import com.example.tourplanner.models.TourModel;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DatabaseTest {

    BusinessLogicLayer bl = new BusinessLogicLayer();

    @Test
    public void createTour(){
        bl.dropDatabase();
        bl.addTour(new tourInfo("UnitTestTour","d","da","da","ad","da" ));

        TourModel tour = bl.getTourByName("UnitTestTour");
        assertTrue("addTour and getTour should work", tour.getTourName().equals("UnitTestTour"));
    }

    @Test
    public void modifyTour(){
        bl.dropDatabase();
        bl.addTour(new tourInfo("UnitTestTour","a1","a2","a3","a4","a5" ));
        TourModel tour = bl.getTourByName("UnitTestTour");

        String id = tour.getTourId();

        bl.editTour(new tourEditInfo(tour.getTourId(),"UnitTestTour2","b1","b2","b3","b4", "b5" ));

        TourModel editedTour = bl.getTourById(id);
        assertTrue("editTour should work", editedTour.getTourName().equals("UnitTestTour2") && editedTour.getTourDescription().equals("b1") && editedTour.getTourFrom().equals("b2"));
    }

    @Test
    public void deleteTour(){
        bl.dropDatabase();
        bl.addTour(new tourInfo("DeleteUnitTestTour","d","da","da","ad","da" ));

        bl.deleteTour("DeleteUnitTestTour");
        TourModel deletedTour = bl.getTourByName("DeleteUnitTestTour");
        System.out.println("teas " + deletedTour.getTourName());
        assertTrue("deleteTour should work", deletedTour.getTourName().equals(""));
    }


    @Test
    public void createTourLogs(){
        bl.dropDatabase();
        bl.addTourLog(new loginfo("UnitTestTour","d","createdTourLogs unit test","da","100","100", "100" ));
        TourLogModel tourLog = bl.getFirstTourLog();
        assertTrue("addTour and getTour should work", tourLog.getComment().equals("createdTourLogs unit test"));
    }

    @Test
    public void modifyTourLogs(){
        bl.dropDatabase();
        bl.addTourLog(new loginfo("UnitTestTour","d","createdTourLogs unit test","da","100","100", "100" ));
        TourLogModel tourLog = bl.getFirstTourLog();

        bl.editTourLog(new logEditinfo(tourLog.getLogId(),"UnitTestTour2","b1","this comment was changed test123","b3","12345", "1234", "123" ));

        TourLogModel editedTourLog = bl.getFirstTourLog();
        assertTrue("editTourLog should work", editedTourLog.getComment().equals("this comment was changed test123"));
    }

    @Test
    public void deleteTourLogs(){
        bl.dropDatabase();
        bl.addTourLog(new loginfo("UnitTestTour","d","createdTourLogs unit test","da","100","100", "100" ));
        TourLogModel tourLog = bl.getFirstTourLog();
        bl.deleteTourLog(tourLog.getLogId());

        TourLogModel emptyTourLog = bl.getFirstTourLog();
        assertTrue("deleteTour should work", emptyTourLog.getComment().equals(""));
    }


}
