package modell.da;

import modell.to.Customer;
import modell.to.Shoes;
import modell.to.Surveys;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:21
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface SurveysDAO {
    List<Surveys> select() throws SQLException;
    void insert (Surveys surveys) throws SQLException;
    void SetRate(Shoes shoesId , Customer custId, int rate, String comment) throws SQLException;
    List<String> productAverageRateView() throws SQLException;
    double getShoesAverageRate(int shoes) throws SQLException;


}
