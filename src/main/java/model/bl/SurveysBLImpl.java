package model.bl;

import model.da.SurveysDAOImpl;
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
public class SurveysBLImpl implements SurveysBL {
    @Override
    public void setSurveys(Surveys s) throws SQLException, IOException, ClassNotFoundException {
        SurveysDAOImpl surveys = new SurveysDAOImpl();
        surveys.insert(s);

    }

    @Override
    public void setSurveys(int custId, int shoesID, int rate, String comment) throws SQLException, IOException, ClassNotFoundException {
        SurveysDAOImpl surveys = new SurveysDAOImpl();
        surveys.setRate(shoesID,custId,rate,comment);
    }

//    @Override
//    public List<ShoesAverageGrade> getAvgGradeForAllShoes() throws SQLException {
//        SurveysDAOImpl surveys = new SurveysDAOImpl();
//        return surveys.shoesAverageGrade();
//    }

    @Override
    public double getAvgForOneShoes(int shoesId) throws SQLException, IOException, ClassNotFoundException {
        SurveysDAOImpl surveys = new SurveysDAOImpl();
        return surveys.getShoesAverageRate(shoesId);
    }

    @Override
    public List<Comments> getCommentsByShoesId(int shoesId) throws SQLException, IOException, ClassNotFoundException {
        SurveysDAOImpl surveys = new SurveysDAOImpl();
        return surveys.getCommentByShoesId(shoesId);
    }

}
