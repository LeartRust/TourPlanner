import com.example.tourplanner.models.TourModel;
import com.example.tourplanner.viewmodel.CreateTourLogViewModel;
import com.example.tourplanner.viewmodel.CreateTourViewModel;
import com.example.tourplanner.viewmodel.DetailsViewModel;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ViewModelTests {

    @Test
    public void setTourDetailsTest(){
        DetailsViewModel viewModel = new DetailsViewModel();
        TourModel tour = new TourModel("testID", "TestTourModel","test123","Vienna","Madrid","Car","100km","false");
        viewModel.setTourDetails(tour);

        assertTrue("TourName should be", (viewModel.getTourName().getValue().equals("TestTourModel")));
        assertTrue("TourDescription should be", (viewModel.getTourDescription().getValue().equals("test123")));
        assertTrue("TourFrom should be", (viewModel.getTourFrom().getValue().equals("Vienna")));
        assertTrue("TourTo should be", (viewModel.getTourTo().getValue().equals("Madrid")));
        assertTrue("TransportType should be", (viewModel.getTourTransportType().getValue().equals("Car")));
        assertTrue("Distance should be", (viewModel.getTourDistance().getValue().equals("100km")));
    }

    @Test
    public void CreateTourSetId(){
        CreateTourViewModel viewModel = new CreateTourViewModel();
        viewModel.setId("TestId");

        assertTrue("TourId should be", (viewModel.getId().equals("TestId")));
    }

    @Test
    public void CreateTourSetOldTourName(){
        CreateTourViewModel viewModel = new CreateTourViewModel();
        viewModel.setOldTourName("TestName");

        assertTrue("OldTourName should be", (viewModel.getOldTourName().equals("TestName")));
    }

    @Test
    public void CreateTourLogSetId(){
        CreateTourLogViewModel viewModel = new CreateTourLogViewModel();
        viewModel.setId("TestId");

        assertTrue("TourLogId should be", (viewModel.getId().equals("TestId")));
    }


}
