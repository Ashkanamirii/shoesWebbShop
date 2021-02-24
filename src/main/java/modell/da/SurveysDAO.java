package modell.da;

import javafx.collections.ObservableList;
import modell.to.Customer;
import modell.to.Shoes;
import modell.to.Surveys;
import utils.Comments;
import utils.ShoesAverageGrade;

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
    void setRate(int shoesId, int custId, int rate, String comment) throws SQLException;
    List<ShoesAverageGrade> shoesAverageGrade ()throws SQLException;
    double getShoesAverageRate(int shoes) throws SQLException;
    List<Comments> getCommentByShoesId(int shoesNr) throws SQLException;
}
