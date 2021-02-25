package model.bl;

import model.to.Surveys;
import model.to.Comments;
//import utils.ShoesAverageGrade;

import java.io.IOException;
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
void setSurveys(Surveys surveys) throws SQLException, IOException, ClassNotFoundException;
void setSurveys(int custId, int shoesID, int rate, String comment) throws SQLException, IOException, ClassNotFoundException;
//List<ShoesAverageGrade> getAvgGradeForAllShoes() throws SQLException;
double getAvgForOneShoes(int shoesId) throws SQLException, IOException, ClassNotFoundException;
List<Comments> getCommentsByShoesId(int shoesId) throws SQLException, IOException, ClassNotFoundException;

}
