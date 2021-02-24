package modell.bl;

import modell.to.Surveys;
import utils.Comments;
import utils.ShoesAverageGrade;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  22:42
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface SurveysBL {
void setSurveys(Surveys surveys) throws SQLException;
void setSurveys(int custId, int shoesID, int rate, String comment) throws SQLException;
List<ShoesAverageGrade> getAvgGradeForAllShoes() throws SQLException;
double getAvgForOneShoes(int shoesId) throws SQLException;
List<Comments> getCommentsByShoesId(int shoesId) throws SQLException;

}
