package lab3.model.oracleDB;


import lab3.database.jdbc.JDBCDatabase;
import lab3.model.Check;
import lab3.model.storage.Storage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleStorage extends JDBCDatabase implements Storage {

    public OracleStorage(){
        super();
        if (connection() == null){
            throw new RuntimeException("123");
        }
    }

    @Override
    protected String url() {
        return "jdbc:oracle:thin:@localhost:1521:orbis";
    }

    @Override
    protected String name() {
        return "s263919";
    }

    @Override
    protected String password() {
        return "hux779";
    }

    @Override
    protected void loadDriver() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
    }

    @Override
    public void addCheck(Check check) {
        try (PreparedStatement statement = connection().
                prepareStatement("INSERT INTO checks (x, y, r, hit, id) " +
                        "VALUES (?, ?, ?, ?, ?)")){

            statement.setFloat(1, check.getX());
            statement.setFloat(2, check.getY());
            statement.setFloat(3, check.getR());
            String dbhit = check.isHit() ? "Y" : "N";
            statement.setString(4, dbhit);
            statement.setString(5, check.getId());

            statement.executeUpdate();

        } catch (SQLException e){
            System.out.println("AddCheck:errorCode:" + e.getErrorCode());
            System.out.println("Message" + e.getMessage());
        }

    }

    @Override
    public List<Check> getChecks(String id) {

        try (PreparedStatement statement = connection().
                prepareStatement("SELECT * FROM checks WHERE id=\'" + id+"\'")){

            List<Check> list = new ArrayList<>();

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                float x = rs.getFloat("x");
                float y = rs.getFloat("y");
                float r = rs.getFloat("r");
                boolean hit = rs.getString("hit").equals("Y");
                Check check = new Check(x, y, r, hit, id);
                list.add(0,check);

            }
            return list;

        } catch (SQLException e){

            System.out.println("getChecks:ErrorCode:" + e.getErrorCode());
            System.out.println("Message" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
