package Models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.ds.PGSimpleDataSource;
/**
 * @author Ikhlas Jihad
 */
public class DBModel {
    private static final String SCHEMA = "majles";
    private Connection conn = null;
    private String user;
    public DBModel(){}
   
    public void setTransaction(boolean b) throws SQLException{
        conn.setAutoCommit(b);
    }
    
    void commit() throws SQLException{
        conn.commit();
    }
   
    public String getUser(){
        return user;
    }
    
    public void connect(String user, String pswd) {
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerName("localhost");
        source.setDatabaseName("Majles");
       
        source.setUser(user);
        this.user = user;
        source.setPassword(pswd);
        try {
            conn = source.getConnection();
        } catch (SQLException ex) {
        }
    }
    
    public boolean mainConnect(String user, String pswd){
        connect(user,pswd); 
        if(conn == null) return false;
        else {
            String sql = "set search_path to '" + SCHEMA + "'";
            try (Statement s1 = conn.createStatement()){             
                s1.execute(sql);
                System.out.println("Connected to schema "+ SCHEMA);
               // s1.execute("select * from student");
                return true;
            }catch (SQLException ex) {
                return false;
            }
        }
    }
    
    public ArrayList<String> getMajlesManagedBy(String user){
        String query = "select maj_id from responsible_for where username = ? ;";
        ArrayList<String> maj = new ArrayList<>();
        try (PreparedStatement pstm = conn.prepareStatement(query)){
            pstm.setString(1, user);   
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                maj.add(rs.getString(1));
                System.out.println(rs.getString(1));
            }  
            return maj;
        } catch (SQLException ex) {            
            return null;
        }
    
    }
    
    public String getMjlesName(String id)throws SQLException{
        String sql = "select name from majles where maj_id = ? ;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        String  name = rs.getString(1);
        pstm.close();
        rs.close();
        return name;
    }
    
    public ArrayList<String> getRegions(){
        String query = "select region_code from region ;";
        ArrayList<String> regions = new ArrayList<>();
        try (Statement pstm = conn.createStatement();
             ResultSet rs = pstm.executeQuery(query)){
            while(rs.next()){
                regions.add(rs.getString(1));
            }  
            return regions;
        } catch (SQLException ex) {            
            return null;
        }
    }
    
    public String getVolName(String user){
        String query = "select name from volunteer where username = ?;";
        String name = "";
        try (PreparedStatement pstm = conn.prepareStatement(query)){
            pstm.setString(1, user);   
            ResultSet rs = pstm.executeQuery();            
            while(rs.next()){
                 name = rs.getString(1);
            }
            return name;
        }catch (SQLException ex) {            
            return null;
        }
    } 
    
    private int getMaxStdID() throws SQLException{
        String sql = "select max(id) from student;";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        int id =  rs.getInt(1);
        stm.close();
        rs.close();
        return id;
    }
    
    public int getMaxLecID(String maj) throws SQLException{
        String query = "select coalesce(max(lec_id),'0') from lecture " +
                       "where maj_id = ? ;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,maj);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        int id =  rs.getInt(1);
        pstm.close();
        rs.close();
        return id;
    }

    public void insertLecture(String maj, String lec, String title, String date, String time) throws SQLException{
        String query = "insert into lecture(lec_id,maj_id,title,\"date\",\"time\") values(?,?,?,cast(? as date),?);";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,lec);
        pstm.setString(2, maj);
        pstm.setString(3, title);
        pstm.setString(4, date);
        pstm.setString(5, time);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public boolean isDate(String date) throws SQLException{
        String query = "select is_date(?);";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,date);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        boolean isValid = rs.getBoolean(1);
        pstm.close();
        rs.close();
        return isValid;
    }
    
    public String [] getMajlesName_Place(String id)  throws SQLException{
        String sql = "select name, place from majles where maj_id = ? ;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        String [] name_place = { rs.getString(1), rs.getString(2)};
        pstm.close();
        rs.close();
        return name_place;
    }   
    
    public String addStd(String n1, String n2, String n3,String n4, String region,String gender) throws SQLException{
        String query = "insert into student(id, first_name, snd_name,third_name,"
                + "last_name, gender, region_code) values(default,?,?,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,n1);
        pstm.setString(2, n2);
        pstm.setString(3, n3);
        pstm.setString(4, n4);
        pstm.setString(5, gender);
        pstm.setString(6, region);
        pstm.executeUpdate();
        pstm.close();
        return String.valueOf(getMaxStdID());
    }
   
    public void addTojoins(String std, String maj) throws SQLException{
        String query = "insert into joins(id, maj_id, join_in) values (?,?,current_date);";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,std);
        pstm.setString(2, maj);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public boolean isJoined(String std, String maj) throws SQLException{
        String query = "select isJoin(?,?);";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,std);
        pstm.setString(2,maj);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        boolean isJoined = rs.getBoolean(1);
        pstm.close();
        rs.close();
        return isJoined;
    }
    
    public void deletStdFromM(String std, String maj)throws SQLException{
        String query = "delete from joins where id = ? and maj_id = ? ;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,std);
        pstm.setString(2,maj);
        pstm.executeUpdate();
        pstm.close();
    }

    public boolean containsLecture(String maj, String lec) throws SQLException{
        String query = "select contains(?,?);";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,lec);
        pstm.setString(2,maj);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        boolean contains = rs.getBoolean(1);
        pstm.close();
        rs.close();
        return contains;
    }
     
    public void deletLecFromM(String lec, String maj)throws SQLException{
        String query = "delete from lecture where lec_id = ? and maj_id = ? ;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,lec);
        pstm.setString(2,maj);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void updateLecTitle(String lec, String newT) throws SQLException{
        String query = "update lecture set title = (?) where lec_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(2, lec);
        pstm.setString(1, newT);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void updateLecPlace(String lec, String newValue) throws SQLException{
        String query = "update lecture set place = (?) where lec_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(2, lec);
        pstm.setString(1, newValue);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void updateLecTime(String lec, String newValue) throws SQLException{
        String query = "update lecture set time = (?) where lec_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(2, lec);
        pstm.setString(1, newValue);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void updateLecDate(String lec, String newValue) throws SQLException{
        String query = "update lecture set date = (?) where lec_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(2, lec);
        pstm.setString(1, newValue);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void updateFirstName(String id, String newValue) throws SQLException{
        String query = "update student set first_name = (?) where id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(2, id);
        pstm.setString(1, newValue);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void update2Name(String id, String newValue) throws SQLException{
        String query = "update student set snd_name = (?) where id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(2, id);
        pstm.setString(1, newValue);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void update3Name(String id, String newValue) throws SQLException{
        String query = "update student set third_name = (?) where id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(2, id);
        pstm.setString(1, newValue);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void update4Name(String id, String newValue) throws SQLException{
        String query = "update student set last_name = (?) where id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(2, id);
        pstm.setString(1, newValue);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void updateRegion(String id, String newValue) throws SQLException{
        String query = "update student set region = (?) where id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(2, id);
        pstm.setString(1, newValue);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void updateGender(String id, String newValue) throws SQLException{
        String query = "update student set gender = (?) where id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(2, id);
        pstm.setString(1, newValue);
        pstm.executeUpdate();
        pstm.close();
    }

    public Lecture getLecture(String maj, String lec) throws SQLException {
        Lecture lecture = null; 
        String query = "select * from lecture where maj_id = ? and lec_id = ?;" ;
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, maj);
        pstm.setString(2, lec);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        //String majles, String id, String title, String date, String time, String place)
        lecture = new Lecture(lec,maj, rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6));        
        
        pstm.close();
        rs.close();
        return lecture;
       
    }
    
    public ArrayList<String> getLectures(String maj) throws SQLException{
        ArrayList<String> lectures = new ArrayList<>();
        String query = "select lec_id from lecture where maj_id = ? ;" ;
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, maj);
        ResultSet rs = pstm.executeQuery();
        while(rs.next())
            lectures.add(rs.getString(1)); 
        pstm.close();
        rs.close();
        return lectures;
    }
    
    public ArrayList<String> whoJoins(String maj) throws SQLException{
        ArrayList<String> stds = new ArrayList<>();
        String query = "select id from joins where maj_id = ? ;" ;
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, maj);
        ResultSet rs = pstm.executeQuery();
        while(rs.next())
            stds.add(rs.getString(1)); 
        pstm.close();
        rs.close();
        return stds;
    }
    
    public void updateAttendance(String std, String lec, String attends) throws SQLException{
        String query = "";
        switch(attends.trim()){
            case "1": insertIntoAttends(std,lec); break;
            case "0": deleteFromAttends(std,lec); break;
            default: 
        }
    }
    
    public void insertIntoAttends(String std, String lec_id) throws SQLException{
        
        String query = "insert into attends values(?,?);";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, std);
        pstm.setString(2, lec_id);
        pstm.executeUpdate();
        pstm.close();
        
    }
    
    public void deleteFromAttends(String std, String lec_id) throws SQLException{
        String query = "delete from attends where id = ? and lec_id = ? ;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, std);
        pstm.setString(2, lec_id);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public ArrayList<StdAttendance> getStdAttendanceReport(String maj, String std) throws SQLException{
    //select * from std_attends('102', '1421'); // lec, date, true/false
        ArrayList<StdAttendance> list = new ArrayList<>();
        String query = "select * from std_attends(?,?);" ;
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, maj);
        pstm.setString(2, std);
        ResultSet rs = pstm.executeQuery();
        while(rs.next())
            list.add(new StdAttendance(std,rs.getString(1), rs.getString(2), 
                    rs.getBoolean(3) ? "1" : "0")); 
        pstm.close();
        rs.close();
        return list;
    
    }
    
    public ArrayList<Commitment> getWhoAttends90P(String maj) throws SQLException {
        ArrayList<Commitment> list = new ArrayList<>();
        String query = "select * from getcommitmentrecord(?);" ;
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, maj);
        ResultSet rs = pstm.executeQuery();
        while(rs.next())//(id varchar, full_name text, lecCount bigint);
            list.add(new Commitment(rs.getString(1), rs.getString(2), rs.getInt(3))); 
        pstm.close();
        rs.close();
        return list;
    }
    
    public ArrayList<LecAttendance> getLectureReport(String lec, String maj) throws SQLException{
        //select * from lecAttendanceReport('102','11')
        ArrayList<LecAttendance> list = new ArrayList<>();
        String query = "select * from lecAttendanceReport(?,?);" ;
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, maj);
        pstm.setString(2, lec);
        ResultSet rs = pstm.executeQuery();
        while(rs.next())
            list.add(new LecAttendance(rs.getString(1), rs.getString(2), 
                    String.valueOf(rs.getInt(3)))); 
        pstm.close();
        rs.close();
        return list;
    }
    
    public int getHowManyAttends(String maj, String lec)  throws SQLException{
        String query = "select count_lec_students(?,?);" ;
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, lec);
        pstm.setString(2, maj);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        pstm.close();
        rs.close();
        return count;
    }
    
    public double getAttendansePercantage(String maj, String lec)  throws SQLException{
        String query = "select attendance_percentage(?,?);" ;
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, lec);
        pstm.setString(2, maj);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        double percent = rs.getDouble(1);
        pstm.close();
        rs.close();
        return percent;
    }
    
    public String getLectureByTitle(String maj, String title) throws SQLException{
        String query = "select lec_id from lecture where maj_id = ? and title = ?;" ;
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, maj);
        pstm.setString(2, title);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        String id = rs.getString(1);
        pstm.close();
        rs.close();
        return id;    
    }
    
    public String getLectureTitle(String id, String maj) throws SQLException{
        String query = "select title from lecture where maj_id = ? and lec_id = ?;" ;
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, maj);
        pstm.setString(2, id);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        String title = rs.getString(1);
        pstm.close();
        rs.close();
        return title;    
    }
    
    private int getMaxMajID() throws SQLException{
        String sql = "select max(id) from majles;";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        int id =  rs.getInt(1);
        stm.close();
        rs.close();
        return id;
    }
    
    private int getMaxBookID() throws SQLException{
        String sql = "select max(book_id) from book;";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        int id =  Integer.parseInt(rs.getString(1));
        stm.close();
        rs.close();
        return id;
    }
    
    private int getMaxAuthorID() throws SQLException{
        String sql = "select max(author_id) from author;";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        int id =  Integer.parseInt(rs.getString(1));
        stm.close();
        rs.close();
        return id;
    }
    
    public String insertMajles(String name, String sh, String place, String subject, String book) throws SQLException{
        String id = "";
        String query = "insert into majles(maj_id,sh_id,place,name,subject) values"
                + "(default, ?,?,?,?);";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,sh);
        pstm.setString(2, place);
        pstm.setString(3,name);
        pstm.setString(4, subject);
        pstm.executeUpdate();
        id = String.valueOf(getMaxMajID());
        pstm.close();
        return id;
    }
    
    public ArrayList<Book> getBooks() throws SQLException {
        String query = "select book_id, title from book;";
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement pstm = conn.prepareStatement(query);
        ResultSet rs = pstm.executeQuery();
        while(rs.next())
            list.add(new Book(rs.getString(1), rs.getString(2))); 
        pstm.close();
        rs.close();
        return list;
    }
    
    public String insertBook(String title,String place, String price) throws SQLException{
        String id = "";
        String query = "insert into book values(default,?,?,?) ";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,title);
        pstm.setString(2, place);
        pstm.setDouble(3,Double.parseDouble(price));
        pstm.executeUpdate();
        id = String.valueOf(getMaxBookID() + 1);
        pstm.close();
        return id;
    }
    
    public void setCategory(String b, String cat) throws SQLException{
        String query = "insert into category_book values(?,?);";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,cat);
        pstm.setString(2, b);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public String insertAuthor(String name) throws SQLException{
        String id = "";
        String query = "insert into author values(default,?) ";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,name);
        pstm.executeUpdate();
        id = String.valueOf(getMaxAuthorID());
        pstm.close();
        return id;
    }

    public void setBookAuth(String auth, String book) throws SQLException{
        String query = "insert into auth_book values(?,?);";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,auth);
        pstm.setString(2, book);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void setElucidates(String maj, String book) throws SQLException{
        String query = "insert into elucidates values(?,?);";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,maj);
        pstm.setString(2, book);
        pstm.executeUpdate();
        pstm.close();
    }

    public void insertSheikh(String id,String name, String email, String gender, String region) throws SQLException{
    String query = "insert into sheikh(sh_id,\"name\",gender, email, region_code) values(?,?,?,?,?);";
    PreparedStatement pstm = conn.prepareStatement(query);
    pstm.setString(1,id);
    pstm.setString(2,name);
    pstm.setString(3,gender);
    pstm.setString(4,email);
    pstm.setString(5,region);
    pstm.executeUpdate();
    pstm.close();
    }

    public void insertVolunteer(String username, String name, String region, String phone) throws SQLException{
        conn.setAutoCommit(false);
        String query = "insert into volunteer values(?,?,?);";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,username);
        pstm.setString(2,name);
        pstm.setString(3,region);
        pstm.executeUpdate();
        if(!phone.equals("")){
            query = "insert into vol_phone values (?,?);";
            pstm = conn.prepareStatement(query);
            pstm.setString(1,username);
            pstm.setString(2,phone);
            pstm.executeUpdate();
        }
        pstm.close();
        conn.commit();
    }

    public ArrayList<String> getMajales() throws SQLException{
        String query = "select maj_id from majles;";
        ArrayList<String> list = new ArrayList<>();
        PreparedStatement pstm = conn.prepareStatement(query);
        ResultSet rs = pstm.executeQuery();
        while(rs.next())
            list.add(rs.getString(1)); 
        pstm.close();
        rs.close();
        return list;
    }
    
    public ArrayList<String>  getUsers() throws SQLException{
        ArrayList<String> list = new ArrayList<>();
        String query = "select username from volunteer;";
        PreparedStatement pstm = conn.prepareStatement(query);
        ResultSet rs = pstm.executeQuery();
        while(rs.next())
            list.add(rs.getString(1)); 
        pstm.close();
        rs.close();
        return list;
    }
    
    public void setRes(String username, String maj) throws SQLException{
        String query = "insert into responsible_for values(?,?); ";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,username);
        pstm.setString(2,maj);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void insertSTdPhone(String id, String phone) throws SQLException{
        String query = "insert into std_phone values(?,?);";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,id);
        pstm.setString(2, phone);
        pstm.executeUpdate();
        pstm.close();
    
    }
    
    public Student getStudent(String id) throws SQLException{
        String query = "select * from student where id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,id);
        ResultSet rs = pstm.executeQuery();
        Student std ;
        rs.next();
        std = new Student(id,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
            rs.getString(6),rs.getString(7)); 
        pstm.close();
        rs.close();
        return std;
        
    
    
    
    }
    
    public String getJoinsIn(String id, String maj) throws SQLException{
        String date = "";
        String query = "select join_in from joins where maj_id = ? and id = ? ";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,maj);
        pstm.setString(2, id);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        date = rs.getString(1);
        pstm.close();
        rs.close();
        return date;
    }
    public void updateJoinIn(String id, String maj, String newValue) throws SQLException{
        String query = "update joins set join_in = cast(? as date) where maj_id = ? and id = ? ";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,newValue);
        pstm.setString(2, maj);
        pstm.setString(3, id);
        pstm.executeUpdate();
        pstm.close();
    }    
    public void updateStdPhone(String id, String maj, String newValue) throws SQLException{
        String query = "update std_phone set phone = ? where maj_id = ? and id = ? ";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,newValue);
        pstm.setString(2, maj);
        pstm.setString(3, id);
        pstm.executeUpdate();
        pstm.close();
    }
    public String getFirstPhone(String id) throws SQLException{
        String phone = "";
        String query = "select phone from std_phone where id = ? ";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();
        while(rs.next())
            phone = rs.getString(1);
        pstm.close();
        rs.close();
        return phone;
    }
    
    public ArrayList<String> getSheikh() throws SQLException{
        ArrayList<String> list = new ArrayList<>();
        String query = "select sh_id from sheikh ; ";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        while(rs.next())
            list.add(rs.getString(1));
        stm.close();
        rs.close();
        return list;
    }
    
    public void insertStdPhone(String id,String phone) throws SQLException{
        String query = "insert into std_phone values(?,?); ";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, id);
        pstm.setString(2,phone);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void updateMajName(String maj, String newValue) throws SQLException{
        String query = "update majles set name = ? where maj_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,newValue);
        pstm.setString(2, maj);
        pstm.executeUpdate();
        pstm.close();
    }
   
    public void updateMajPlace(String maj, String newValue) throws SQLException{
        String query = "update majles set place = ? where maj_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,newValue);
        pstm.setString(2, maj);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void updateMajSubject(String maj, String newValue) throws SQLException{
        String query = "update majles set subject = ? where maj_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,newValue);
        pstm.setString(2, maj);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void updateMajsheikh(String maj, String newValue) throws SQLException{
        String query = "update majles set sh_id = ? where maj_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,newValue);
        pstm.setString(2, maj);
        pstm.executeUpdate();
        pstm.close();
    }
    
    public void deleteMajles(String id) throws SQLException{
        String query = "delete from majles where maj_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,id);
        pstm.executeUpdate();
        pstm.close();
    
    }
    
    public Majles getMajles(String id) throws SQLException{
        String query = "select * from majles where maj_id = ? ;";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,id);
        ResultSet rs = pstm.executeQuery();
        Majles maj = null ;
        while(rs.next())
            maj = new Majles(id,rs.getString(2),rs.getString(3),rs.getString(4)
            , rs.getString(5)); 
        pstm.close();
        rs.close();
        return maj;
    
    }
    
    public void createVolUser(String user, String pswd,String name)  throws SQLException{
        conn.setAutoCommit(false);
        String query = "CREATE USER ? WITH LOGIN PASSWORD ?  ; ";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,user);
        pstm.setString(2,pswd);        
        pstm.executeUpdate();
        pstm = null;
        query = "grant Volunteer to ? ; ";
        pstm = conn.prepareStatement(query);
        pstm.setString(1,user);
        pstm.executeUpdate();
        pstm = null;
        insertVolunteer(user, name, null, null);
        conn.commit();
        pstm.close();
    }
    
    public void createAdminUser(String user, String pswd)  throws SQLException{
        conn.setAutoCommit(false);
        String query = "CREATE USER ? WITH LOGIN PASSWORD ?  ; ";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,user);
        pstm.setString(2,pswd);        
        pstm.executeUpdate();
        pstm = null;
        query = "grant Adminstrator to ? ; ";
        pstm = conn.prepareStatement(query);
        pstm.setString(1,user);
        pstm.executeUpdate();
        conn.commit();
        pstm.close();
    }
    
    public void deleteUser(String user) throws SQLException {
        conn.setAutoCommit(false);
        String query = "drop user " + user;
        Statement stm = conn.createStatement();
        stm.executeUpdate(query);
        if(getUsers().contains(user)){
            query = "delete from volunteer where username = " + user;
            stm = conn.createStatement();
            stm.executeQuery(query);
        }
        conn.commit();
        stm.close();
    }
    
    public void editUserPswd(String user, String pswd)  throws SQLException{
        String query = "ALTER USER ? WITH LOGIN PASSWORD ? ; ";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1,user);
        pstm.setString(2,pswd);        
        pstm.executeUpdate();
        pstm.close();
    }
    
    private void closeEverything() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exit() {
        closeEverything();
        System.out.println("Exiting... \nBye!");
        System.exit(0);
    }
    
}
