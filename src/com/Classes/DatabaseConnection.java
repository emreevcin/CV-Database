package com.Classes;

import java.io.File;
import java.sql.*;

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
    private PreparedStatement insertSQLTags;
    private PreparedStatement selectSQLCVID;
    private PreparedStatement selectSQLCVName;

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
                Statement statTag = conn.createStatement();

                stat.executeUpdate("CREATE TABLE cvs(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_name TEXT UNIQUE NOT NULL, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statPersonal.executeUpdate("CREATE TABLE people(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id), " +
                        "photo BLOB, " +
                        "first_name TEXT, " +
                        "last_name TEXT, " +
                        "title TEXT, " +
                        "career_objective TEXT, " +
                        "email TEXT, " +
                        "phone TEXT, " +
                        "city TEXT, " +
                        "country TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statWork.executeUpdate("CREATE TABLE works(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id), " +
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
                        "cv_id INTEGER REFERENCES cvs(id), " +
                        "institution TEXT, " +
                        "department TEXT, " +
                        "gpa TEXT, " +
                        "starting_date TEXT, " +
                        "ending_date TEXT, " +
                        "ongoing TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statCertificate.executeUpdate("CREATE TABLE certificates(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id), " +
                        "education_name TEXT, " +
                        "company TEXT, " +
                        "verified_date TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statSkill.executeUpdate("CREATE TABLE skills(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id), " +
                        "mother_tongue TEXT, " +
                        "other_languages TEXT, " +
                        "soft_skills TEXT, " +
                        "hard_skills TEXT, " +
                        "hobbies_interests TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statProject.executeUpdate("CREATE TABLE projects(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id), " +
                        "title TEXT, " +
                        "starting_date TEXT, " +
                        "ending_date TEXT, " +
                        "ongoing TEXT, " +
                        "description TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statRecommendation.executeUpdate("CREATE TABLE recommendations(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id), " +
                        "name_ TEXT, " +
                        "role_ TEXT, " +
                        "email TEXT, " +
                        "phone TEXT, " +
                        "description TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statOther.executeUpdate("CREATE TABLE others(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cv_id INTEGER REFERENCES cvs(id), " +
                        "header TEXT, " +
                        "title TEXT, " +
                        "description TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");

                statTag.executeUpdate("CREATE TABLE tags(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tag_name TEXT, " +
                        "created_at DATE DEFAULT CURRENT_TIMESTAMP);");
            }

            insertSQL = conn.prepareStatement("INSERT INTO cvs(cv_name) values (?);");

            insertSQLPersonal = conn.prepareStatement("INSERT INTO  people(cv_id, photo, first_name, last_name, title, career_objective, " +
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

            insertSQLOther = conn.prepareStatement("INSERT INTO  others(cv_id, header, title, description) " +
                    "values (?, ?, ?, ?);");

            insertSQLTags = conn.prepareStatement("INSERT INTO  tags(tag_name) values (?);");


            selectSQLCVID = conn.prepareStatement("SELECT id FROM cvs ORDER BY id DESC LIMIT 1;");

            selectSQLCVName = conn.prepareStatement("SELECT cv_name FROM cvs ORDER BY id DESC LIMIT 1;");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void addCV(String firstName , String lastName) {
        String cvName = firstName + "_" + lastName + "_CV";
        try {
            insertSQL.setString(1, cvName);
            insertSQL.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addPerson(int cvId, String imageUrl, String firstName, String lastName, String title, String careerObjective,
                          String email, String phone, String city, String country) {
        try {
            setCommonSQLCommand(cvId, imageUrl, firstName, lastName, title, careerObjective, email, phone, city, insertSQLPersonal);
            insertSQLPersonal.setString(10, country);
            insertSQLPersonal.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void setCommonSQLCommand(int cvId, String imageUrl, String firstName, String lastName, String title, String careerObjective, String email, String phone, String city, PreparedStatement insertSQLPersonal) throws SQLException {
        insertSQLPersonal.setInt(1, cvId);
        insertSQLPersonal.setString(2, imageUrl);
        insertSQLPersonal.setString(3, firstName);
        insertSQLPersonal.setString(4, lastName);
        insertSQLPersonal.setString(5, title);
        insertSQLPersonal.setString(6, careerObjective);
        insertSQLPersonal.setString(7, email);
        insertSQLPersonal.setString(8, phone);
        insertSQLPersonal.setString(9, city);
    }

    public void addWork(int cvId, String occupation, String employer, String city, String country,
                        String startingDate, String endingDate, String ongoing, String activitiesResponsibilities) {
        try {
            setCommonSQLCommand(cvId, occupation, employer, city, country, startingDate, endingDate, ongoing, activitiesResponsibilities, insertSQLWork);
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

    public void addTags(String tagName) {
        try {
            insertSQLTags.setString(1, tagName);
            insertSQLTags.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
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

    public String getCVName() {
        String cvName = "EMPTY";
        try {
            ResultSet rs = selectSQLCVName.executeQuery();
            while (rs.next()) {
                cvName = rs.getString("cv_name");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cvName;
    }

}