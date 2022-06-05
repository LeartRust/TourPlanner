import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.dataAccessLayer.database.MongoDB;
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
        ArrayList<TourModel> toursList = bl.getTours();

        TourModel searchedTour = new TourModel("","","","","","","","");
        for (TourModel tour : toursList) {
            if (tour.getTourName().equals("UnitTestTour")){
                searchedTour = tour;
            }
        }
        assertTrue("addTour and getTour should work", searchedTour.getTourName().equals("UnitTestTour"));
    }

}
