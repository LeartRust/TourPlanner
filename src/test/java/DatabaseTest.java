import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.dataAccessLayer.database.MongoDB;
import com.example.tourplanner.dataAccessLayer.database.tourEditInfo;
import com.example.tourplanner.dataAccessLayer.database.tourInfo;
import com.example.tourplanner.models.TourModel;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DatabaseTest {

    @Test
    public void createTour(){
        BusinessLogicLayer bl = new BusinessLogicLayer();
        bl.addTour(new tourInfo("UnitTestTour","d","da","da","ad","da" ));

        TourModel tour = bl.getTourByName("UnitTestTour");
        System.out.println(tour.getTourName() + " testt " + tour.getTourTo());
        assertTrue("addTour and getTour should work", tour.getTourName().equals("UnitTestTour"));
    }

    @Test
    public void modifyTour(){
        BusinessLogicLayer bl = new BusinessLogicLayer();
        bl.addTour(new tourInfo("UnitTestTour","a1","a2","a3","a4","a5" ));
        TourModel tour = bl.getTourByName("UnitTestTour");

        String id = tour.getTourId();

        bl.editTour(new tourEditInfo(tour.getTourId(),"UnitTestTour","b1","b2","b3","b4", "b5" ));

        TourModel editedTour = bl.getTourById(id);
        assertTrue("addTour and getTour should work", editedTour.getTourFrom().equals("b1"));
    }

    @Test
    public void deleteTour(){
        BusinessLogicLayer bl = new BusinessLogicLayer();
        bl.addTour(new tourInfo("UnitTestTour","d","da","da","ad","da" ));
        ArrayList<TourModel> toursList = bl.getTours();

        TourModel tour = bl.getTourByName("UnitTestTour");
        System.out.println(tour.getTourName() + " testt " + tour.getTourTo());
        assertTrue("addTour and getTour should work", tour.getTourName().equals("UnitTestTour"));
    }




}
