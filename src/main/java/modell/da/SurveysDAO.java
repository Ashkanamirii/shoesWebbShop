package modell.da;

import modell.to.Surveys;
import utils.Comment;
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
    List<Comment> getCommentByShoesId(int shoesNr) throws SQLException;
}
