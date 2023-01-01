package com.Classes;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.ListView;


public class DatabaseConnection {
    private final String fileName;
    private Connection conn;
    private PreparedStatement insertSQL;
    private PreparedStatement insertSQLPersonal;
    private PreparedStatement insertSQLWork;
    private PreparedStatement insertSQLEducation;
    private PreparedStatement insertSQLProject;
    private PreparedStatement insertSQLCertificate;
    private PreparedStatement insertSQLSkill;
    private PreparedStatement insertSQLRecommendation;
    private PreparedStatement insertSQLOther;
    private PreparedStatement selectSQLCVID;
    private PreparedStatement selectSQLLastCVName;
    private PreparedStatement selectSQLCVTag;
    private PreparedStatement selectSQLCounterCV;
    private PreparedStatement selectSQLCVNames;
    private PreparedStatement deleteSQL;
    private PreparedStatement multi ;


    private javafx.scene.control.ListView<String> cvList;




    private PreparedStatement selectIDwithParam;
    private PreparedStatement updateCV;
    private PreparedStatement deleteFromTable;

    public DatabaseConnection() {
        fileName = "cvdb.db";
        File file = new File(fileName);
        boolean firstRun = !file.exists();
        conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + fileName);

            if (firstRun) {
                Statement stat = conn.createStatement();
                Statement statPersonal = conn.createStatement();
                Statement statWork = conn.createStatement();
                Statement statEducation = conn.createStatement();
                Statement statProject = conn.createStatement();
                Statement statCertificate = conn.createStatement();
                Statement statSkill = conn.createStatement();
                Statement statRecommendation = conn.createStatement();
                Statement statOther = conn.createStatement();


                stat.executeUpdate("CREATE TABLE cvs(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_name TEXT UNIQUE NOT NULL, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statPersonal.executeUpdate("CREATE TABLE people(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id) ON DELETE CASCADE, " +
                        "first_name TEXT, " +
                        "last_name TEXT, " +
                        "tag TEXT," +
                        "title TEXT, " +
                        "career_objective TEXT, " +
                        "email TEXT, " +
                        "phone TEXT, " +
                        "city TEXT, " +
                        "country TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statWork.executeUpdate("CREATE TABLE works(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id) ON DELETE CASCADE, " +
                        "occupation TEXT, " +
                        "employer TEXT, " +
                        "city TEXT, " +
                        "country TEXT, " +
                        "starting_date TEXT, " +
                        "ending_date TEXT, " +
                        "ongoing TEXT, " +
                        "activities_responsibilities TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statEducation.executeUpdate("CREATE TABLE educations(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id) ON DELETE CASCADE, " +
                        "institution TEXT, " +
                        "department TEXT, " +
                        "gpa TEXT, " +
                        "starting_date TEXT, " +
                        "ending_date TEXT, " +
                        "ongoing TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statCertificate.executeUpdate("CREATE TABLE certificates(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id) ON DELETE CASCADE, " +
                        "education_name TEXT, " +
                        "company TEXT, " +
                        "verified_date TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statSkill.executeUpdate("CREATE TABLE skills(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id) ON DELETE CASCADE, " +
                        "mother_tongue TEXT, " +
                        "other_languages TEXT, " +
                        "soft_skills TEXT, " +
                        "hard_skills TEXT, " +
                        "hobbies_interests TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statProject.executeUpdate("CREATE TABLE projects(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id) ON DELETE CASCADE, " +
                        "title TEXT, " +
                        "starting_date TEXT, " +
                        "ending_date TEXT, " +
                        "ongoing TEXT, " +
                        "description TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statRecommendation.executeUpdate("CREATE TABLE recommendations(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id) ON DELETE CASCADE, " +
                        "name_ TEXT, " +
                        "role_ TEXT, " +
                        "email TEXT, " +
                        "phone TEXT, " +
                        "description TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statOther.executeUpdate("CREATE TABLE other_information(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id) ON DELETE CASCADE, " +
                        "header TEXT, " +
                        "title TEXT, " +
                        "description TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

            }

            insertSQL = conn.prepareStatement("INSERT INTO cvs(cv_name) values (?);");

            insertSQLPersonal = conn.prepareStatement("INSERT INTO  people(cv_id, tag, first_name, last_name, title, career_objective, " +
                    "email, phone, city, country) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            insertSQLWork = conn.prepareStatement("INSERT INTO  works(cv_id, occupation, employer, city, country, " +
                    "starting_date, ending_date, ongoing, activities_responsibilities) values (?, ?, ?, ?, ?, ?, ?, ?, ?);");

            insertSQLEducation = conn.prepareStatement("INSERT INTO  educations(cv_id, institution, department, gpa, starting_date, " +
                    "ending_date, ongoing) values (?, ?, ?, ?, ?, ?, ?);");

            insertSQLCertificate = conn.prepareStatement("INSERT INTO  certificates(cv_id, education_name, company, verified_date) " +
                    "values (?, ?, ?, ?);");

            insertSQLSkill = conn.prepareStatement("INSERT INTO  skills(cv_id, mother_tongue, other_languages, soft_skills, hard_skills, " +
                    "hobbies_interests) values (?, ?, ?, ?, ?, ?);");

            insertSQLProject = conn.prepareStatement("INSERT INTO  projects(cv_id, title, starting_date, ending_date, ongoing, " +
                    "description) values (?, ?, ?, ?, ?, ?);");

            insertSQLRecommendation = conn.prepareStatement("INSERT INTO  recommendations(cv_id, name_, role_, email, phone, " +
                    "description) values (?, ?, ?, ?, ?, ?);");

            insertSQLOther = conn.prepareStatement("INSERT INTO  other_information(cv_id, header, title, description) " +
                    "values (?, ?, ?, ?);");

            selectSQLCVID = conn.prepareStatement("SELECT id FROM cvs ORDER BY id DESC LIMIT 1;");

            selectSQLLastCVName = conn.prepareStatement("SELECT cv_name FROM cvs ORDER BY id DESC LIMIT 1;");

            selectSQLCVTag = conn.prepareStatement("SELECT tag FROM people ORDER BY id DESC LIMIT 1;");

            selectSQLCounterCV = conn.prepareStatement("SELECT COUNT(*) FROM cvs;");

            selectSQLCVNames = conn.prepareStatement("SELECT cv_name FROM cvs;");

            deleteSQL = conn.prepareStatement("DELETE FROM cvs WHERE id = (?);");

            //these are the part for edit
            selectIDwithParam = conn.prepareStatement("SELECT id FROM cvs WHERE cv_name = (?);");
//
            updateCV = conn.prepareStatement("UPDATE cvs SET cv_name = (?) WHERE id = (?);");
//
//            deleteFromTable = conn.prepareStatement("DELETE FROM (?) WHERE cv_id = (?);");


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }



    public void addCV(String firstName , String lastName) {
        String cvName = firstName + " " + lastName;
        try {
            insertSQL.setString(1, cvName);
            insertSQL.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addPerson(int cvId, String firstName, String lastName, String tag,  String title, String careerObjective,
                          String email, String phone, String city, String country) {
        try {
            insertSQLPersonal.setInt(1, cvId);
            insertSQLPersonal.setString(2, firstName);
            insertSQLPersonal.setString(3, lastName);
            insertSQLPersonal.setString(4, tag);
            insertSQLPersonal.setString(5, title);
            insertSQLPersonal.setString(6, careerObjective);
            insertSQLPersonal.setString(7, email);
            insertSQLPersonal.setString(8, phone);
            insertSQLPersonal.setString(9, city);
            insertSQLPersonal.setString(10, country);
            insertSQLPersonal.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public void addWork(int cvId, String occupation, String employer, String city, String country,
                        String startingDate, String endingDate, String ongoing, String activitiesResponsibilities) {
        try {
            insertSQLWork.setInt(1, cvId);
            insertSQLWork.setString(2, occupation);
            insertSQLWork.setString(3, employer);
            insertSQLWork.setString(4, city);
            insertSQLWork.setString(5, country);
            insertSQLWork.setString(6, startingDate);
            insertSQLWork.setString(7, endingDate);
            insertSQLWork.setString(8, ongoing);
            insertSQLWork.setString(9, activitiesResponsibilities);
            insertSQLWork.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addEducation(int cvId, String institution, String department, String gpa, String startingDate,
                             String endingDate, String ongoing) {
        try {
            insertSQLEducation.setInt(1, cvId);
            insertSQLEducation.setString(2, institution);
            insertSQLEducation.setString(3, department);
            insertSQLEducation.setString(4, gpa);
            insertSQLEducation.setString(5, startingDate);
            insertSQLEducation.setString(6, endingDate);
            insertSQLEducation.setString(7, ongoing);
            insertSQLEducation.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addCertificates(int cvId, String educationName, String company, String verifiedDate) {
        try {
            insertSQLCertificate.setInt(1, cvId);
            insertSQLCertificate.setString(2, educationName);
            insertSQLCertificate.setString(3, company);
            insertSQLCertificate.setString(4, verifiedDate);
            insertSQLCertificate.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addSkills(int cvId, String motherTongue, String otherLanguages, String softSkills, String hardSkills,
                          String hobbies_interests) {
        try {
            insertSQLSkill.setInt(1, cvId);
            insertSQLSkill.setString(2, motherTongue);
            insertSQLSkill.setString(3, otherLanguages);
            insertSQLSkill.setString(4, softSkills);
            insertSQLSkill.setString(5, hardSkills);
            insertSQLSkill.setString(6, hobbies_interests);
            insertSQLSkill.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addProjects(int cvId, String title, String startingDate, String endingDate, String ongoing,
                            String description) {
        try {
            insertSQLProject.setInt(1, cvId);
            insertSQLProject.setString(2, title);
            insertSQLProject.setString(3, startingDate);
            insertSQLProject.setString(4, endingDate);
            insertSQLProject.setString(5, ongoing);
            insertSQLProject.setString(6, description);
            insertSQLProject.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addRecommendations(int cvId, String name, String role, String email, String phone,
                                   String description) {
        try {
            insertSQLRecommendation.setInt(1, cvId);
            insertSQLRecommendation.setString(2, name);
            insertSQLRecommendation.setString(3, role);
            insertSQLRecommendation.setString(4, email);
            insertSQLRecommendation.setString(5, phone);
            insertSQLRecommendation.setString(6, description);
            insertSQLRecommendation.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addOthers(int cvId, String header, String title, String description) {
        try {
            insertSQLOther.setInt(1, cvId);
            insertSQLOther.setString(2, header);
            insertSQLOther.setString(3, title);
            insertSQLOther.setString(4, description);
            insertSQLOther.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getNumberOfCV() {
        int numberOfCV = 0;
        try {
            ResultSet rs = selectSQLCounterCV.executeQuery();
            if (rs.next()) {
                numberOfCV = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return numberOfCV;
    }

    public int getCVID() {
        int cvID = 0;
        try {
            ResultSet rs = selectSQLCVID.executeQuery();
            while (rs.next()) {
                cvID = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cvID;
    }

    public void reloadCV(ListView <String> cvList){
        try {
            ResultSet rs = selectSQLLastCVName.executeQuery();
            while (rs.next()){
                cvList.getItems().add(getCVName());
            }

        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public String getCVName() {
        String cvName = "EMPTY";
        try {
            ResultSet rs = selectSQLLastCVName.executeQuery();
            while (rs.next()) {
                cvName = rs.getString("cv_name");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cvName;
    }
    public String getCVTag() {
        String tag = "EMPTY";
        try {
            ResultSet rs = selectSQLCVTag.executeQuery();
            while (rs.next()) {
                tag = rs.getString("tag");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return tag;
    }
    // Delete CV from database with Cascade
    public void deleteCV(int cvId) {
        try {
            deleteSQL.setInt(1, cvId);
            deleteSQL.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public HashMap<Integer,String> searchCV(String key , String field ) throws SQLException {
        PreparedStatement statement ;

        if(!key.equals("")){
            switch (field){
                case "Name":{
                    statement = conn.prepareStatement("SELECT cvs.cv_name , cvs.id FROM people INNER JOIN cvs ON cvs.id = people.cv_id WHERE first_name LIKE '%"+key+"%'");;
                    break;
                }
                case "Surname":{
                    statement = conn.prepareStatement("SELECT cvs.cv_name , cvs.id FROM people INNER JOIN cvs ON cvs.id = people.cv_id WHERE last_name LIKE '%" +
                            key+"%' ");
                    break;
                }
                case "Name-Surname":{
                    String[] s = key.trim().split(" ");
                    if(s.length!=2)
                        return null;
                    String name = s[0];
                    String surname = s[1];
                    System.out.println(name+""+surname);
                    statement = conn.prepareStatement("SELECT cvs.cv_name , cvs.id FROM people INNER JOIN cvs ON cvs.id = people.cv_id WHERE first_name LIKE '%" +
                            name+"%' AND last_name LIKE '%"+surname+"%'");
                    break;
                }
                case "Institution":{
                    statement = conn.prepareStatement("SELECT cvs.cv_name , cvs.id FROM education INNER JOIN cvs ON cvs.id = education.cv_id WHERE instituion LIKE '%" +
                            key+"%'");;
                    break;
                }
                case"Title":{
                    statement = conn.prepareStatement("SELECT cvs.cv_name , cvs.id FROM works INNER JOIN cvs ON cvs.id = works.cv_id WHERE employer LIKE '%" +
                            key+"%'");;
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + field);
            }
        }
        else {
            statement = conn.prepareStatement("SELECT id  ,cv_name  FROM cvs ");
        }
        if(statement==null)
            return null;

        HashMap<Integer ,String> cvs =new HashMap<>();

        ResultSet resultSet = statement.executeQuery();


        while (resultSet.next()){
            Integer cv_id = resultSet.getInt("id");
            String cv_name = resultSet.getString("cv_name");
            cvs.put(cv_id,cv_name);
        }
        return cvs ;
    }

    public int getIDwithParam(String firstName, String lastName) {
        int id = 0;
        String param= firstName + "_" + lastName;
        try {
            selectIDwithParam.setString(1, param);
            ResultSet rs = selectIDwithParam.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }
    public void updateCV(String old_cv_name , String new_cv_name) {
        try {
            updateCV.setString(1, new_cv_name);
            updateCV.setString(2, old_cv_name);
            updateCV.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void deleteFromTable(String table , int id) {
        try {
            deleteFromTable.setString(1, table);
            deleteFromTable.setInt(2, id);
            deleteFromTable.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }



    public HashMap<Integer,ArrayList<HashMap<String,String>>> returnCV(Integer cv_id) throws SQLException {

        HashMap<Integer,ArrayList<HashMap<String,String>>> result = new HashMap<>();

        /**
         * -Explanation for getting cv -
         * CV
         *  ->works
         *      ->work1
         *      ->work2
         *  ->education
         *      ->education1
         *      ->education2
         *
         * finding cv example
         * ->>>
         * cv_id = 5
         * cv_id 5 -work1
         * cv_id 5 -work2
         *
         * to store data we use hashmap
         * ->integer represents index. For example : 0 represents personal information
         * ->For those values that have same indexes we store each of them in the Arraylist
         * ->In the Arraylist -> work1, work2
         * ->result.get(0) returns a ArrayList that stores multi personal information
         * ->result.get(0).get(0) returns a HashMap which is a one of the personal information for in this context
         * ->In the hashmap each key represents a attribute .For instance ,  result.get(0).get(0).put("firstName","Emre") stores data
         *
         * **/


        PreparedStatement statement ;
        String[] tableArr = {"certificates","cvs","educations","others","people","skills","recommendations","works" };

        for (int i=0 ; i<tableArr.length ; i++){
            String table = tableArr[i];
            statement = conn.prepareStatement("SELECT * FROM "+table+"WHERE cv_id ="+cv_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
            }
        }




        return  result ;
    }





}