package modell.da;

import connection.ConnectionDB;
import modell.bl.CategoryManager;
import modell.bl.CategoryManagerImpl;
import modell.to.Brand;
import modell.to.Category;
import modell.to.Shoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:29
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class ShoesDAOImpl implements ShoesDAO {
    Connection connection;
    PreparedStatement preparedStatement;

    public ShoesDAOImpl() {
        connection = new ConnectionDB().getConnection();
    }

    @Override
    public Shoes getShoesById(int shoesId) throws SQLException {
       /*
        Shoes shoes = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM shoes sh " +
                "JOIN brand b on b.id = sh.FK_brand_id  WHERE sh.id = ?");
        preparedStatement.setInt(1, shoesId);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Brand brand = new Brand(rs.getInt(10), rs.getString(11));
            shoes = new Shoes(rs.getInt("id"),
                    rs.getInt("size"),
                    rs.getInt("shoes_number"),
                    brand,
                    rs.getString("color"),
                    (int) rs.getDouble("price"),
                    rs.getInt("quantity"));
        }
        close();
        return shoes;

        */
        return null;
    }
/*
    @Override
    public List<Shoes> select() throws SQLException {
        List<Shoes>shoesResult =new ArrayList<>();
        preparedStatement=connection.prepareStatement("with categories as( select FK_shoes_id,group_concat(c.name separator ', ') as category\n" +
                "    from shoes_category\n" +
                "    join category c on c.id=FK_category_id\n" +
                "    group by FK_shoes_id)\n" +
                "    select shoes.id,size,shoes_number,FK_brand_id,br.name,category,color,price ,quantity\n" +
                "from shoes \n" +
                "join brand br on br.id=shoes.FK_brand_id\n" +
                "left join categories cs on cs.FK_shoes_id=shoes.id \n" +
                "order by shoes.id;");
        ResultSet resultSet =preparedStatement.executeQuery();
        while(resultSet.next()){
            shoesResult.add(new Shoes(resultSet.getInt("id"),resultSet.getInt("size"),resultSet.getInt("shoes_number"),
                    new Brand(resultSet.getInt("FK_brand_id"),resultSet.getString("br.name")), resultSet.getString("category"),
                    resultSet.getString("color"),resultSet.getDouble("price"),resultSet.getInt("quantity")));
        }
        close();
        return shoesResult;
    }
*/

/*
    public List<Shoes> select() throws SQLException {
        //String categoryIds="";
       // String categoryNames="";
        List<Integer>categoryIds;
        List<String>categoryNames;
        List<Category>categoryList;
        List<Shoes>shoesResult =new ArrayList<>();
        preparedStatement=connection.prepareStatement("with categories as(select FK_shoes_id,group_concat(c.name separator ', ') as category,group_concat(c.id separator ', ') as categoryIds\n" +
                "    from shoes_category\n" +
                "    join category c on c.id=FK_category_id\n" +
                "    group by FK_shoes_id)\n" +
                "    select shoes.id,size,shoes_number,FK_brand_id,br.name,categoryIds,category,color,price,quantity \n" +
                "from shoes \n" +
                "join brand br on br.id=shoes.FK_brand_id\n" +
                "left join categories cs on cs.FK_shoes_id=shoes.id \n" +
                "order by shoes.id;");
        ResultSet resultSet =preparedStatement.executeQuery();
        while(resultSet.next()){
            categoryIds=new ArrayList<Integer>((Collection<? extends Integer>) Arrays.asList(
                    resultSet.getString("categoryIds").split(",")).stream().mapToInt(Integer::parseInt));

            categoryNames=new ArrayList<String>(Arrays.asList(resultSet.getString("category").split(",")));


            categoryList=new ArrayList<Category>(categoryIds.stream().map((id)->new Category(id,"have to fix it here!!!!")).collect(Collectors.toList()));




            shoesResult.add(new Shoes(resultSet.getInt("id"),resultSet.getInt("size"),resultSet.getInt("shoes_number"),
                    new Brand(resultSet.getInt("FK_brand_id"),resultSet.getString("br.name")), categoryList,
                    resultSet.getString("color"),resultSet.getDouble("price"),resultSet.getInt("quantity")));
        }
        close();
        return shoesResult;
    }

 */


    public List<Shoes> select() throws SQLException {
        CategoryManager categoryManager=new CategoryManagerImpl();

        List<Integer>categoryIds;
        List<List<Integer>> categoryIdsList=new ArrayList<>();
        List<Category>categoryList=new ArrayList<>();
        List<Shoes>shoesResult =new ArrayList<>();
        preparedStatement=connection.prepareStatement("with categories as(select FK_shoes_id,group_concat(c.name separator ', ') as category,group_concat(c.id separator ', ') as categoryIds\n" +
                "    from shoes_category\n" +
                "    join category c on c.id=FK_category_id\n" +
                "    group by FK_shoes_id)\n" +
                "    select shoes.id,size,shoes_number,FK_brand_id,br.name,categoryIds,category,color,price,quantity \n" +
                "from shoes \n" +
                "join brand br on br.id=shoes.FK_brand_id\n" +
                "left join categories cs on cs.FK_shoes_id=shoes.id \n" +
                "order by shoes.id;");
        ResultSet resultSet =preparedStatement.executeQuery();
        while(resultSet.next()){
            shoesResult.add(new Shoes(resultSet.getInt("id"),resultSet.getInt("size"),resultSet.getInt("shoes_number"),
                    new Brand(resultSet.getInt("FK_brand_id"),resultSet.getString("br.name")), categoryList,
                    resultSet.getString("color"),resultSet.getDouble("price"),resultSet.getInt("quantity")));
        }

shoesResult.forEach(s-> {
    try {
        s.setCategories(new ArrayList<>((Collection<? extends Category>)Arrays.asList(categoryManager.getCategoryListByShoesId(s.getId())).stream().collect(Collectors.toList())));
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
});

        close();

        return shoesResult;
    }
    public void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}