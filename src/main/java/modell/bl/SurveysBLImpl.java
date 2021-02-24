package modell.bl;

import modell.da.SurveysDAOImpl;
import modell.to.Surveys;
import utils.Comment;
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
public class SurveysBLImpl implements SurveysBL {
    @Override
    public void setSurveys(Surveys s) throws SQLException {
        SurveysDAOImpl surveys = new SurveysDAOImpl();
        surveys.insert(s);

    }

    @Override
    public void setSurveys(int custId, int shoesID, int rate, String comment) throws SQLException {
        SurveysDAOImpl surveys = new SurveysDAOImpl();
        surveys.setRate(shoesID,custId,rate,comment);
    }

    @Override
    public List<ShoesAverageGrade> getAvgGradeForAllShoes() throws SQLException {
        SurveysDAOImpl surveys = new SurveysDAOImpl();
        return surveys.shoesAverageGrade();
    }

    @Override
    public double getAvgForOneShoes(int shoesId) throws SQLException {
        SurveysDAOImpl surveys = new SurveysDAOImpl();
        return surveys.getShoesAverageRate(shoesId);
    }

    @Override
    public List<Comment> getCommentByShoesId(int shoesId) throws SQLException {
        SurveysDAOImpl surveys = new SurveysDAOImpl();
        return surveys.getCommentByShoesId(shoesId);

    }


}
