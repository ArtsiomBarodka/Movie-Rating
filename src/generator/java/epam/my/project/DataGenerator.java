package epam.my.project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DataGenerator {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/movie_raiting?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "password";

    private static final String MOVIES_IMAGE_PATH = "/media/posters/";
    private static final String USERS_IMAGE_PATH = "/media/users/";

    static final String[] ROLES = {"USER", "ADMIN"};

    static final String[] GENRES = { "Action", "Adventure", "Comedy", "Drama", "Crime", "Horror", "Fantasy", "Romance", "Thriller", "Animation", "Sci-fi", "Western", "Musical"};
    static final String[] CATEGORIES = { "Feature film", "Series"};
    static final String[] COUNTRIES = { "USA", "Russia"};
    static final String[] FILMMAKERS = { "Jerry Bruckheimer", "George Lucas", "Michael Bay", "Tyler Perry", "Steven Spielberg", "James Cameron", "Martin Scorsese", "Ridley Scott", "Peter Jackson", "Quentin Tarantino", "Woody Allen", "Christopher Nolan" };

    static final String[] VERBS = {"Body", "Bird", "Aria", "Child", "Car", "Color", "Door", "Eye", "Face", "Farm", "King", "Money", "Night", "Order", "Place"};
    static final String[] ADJECTIVES = {"Cold", "Deep", "Far", "Hard", "Hot", "Big", "Excellent", "Fuming", "Happy", "Lucky", "Difficult"};

    static final String[] CONTENT = {"Ut iaculis convallis luctus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris vitae finibus ex. Suspendisse ut tempor neque. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quis sapien euismod, vestibulum ex vitae, vehicula risus. Duis et gravida arcu.",
            "Donec euismod, diam non ultricies rutrum, odio nisi facilisis dolor, rutrum vulputate nibh justo in dolor. Aliquam ultrices erat a interdum sollicitudin. Vivamus risus mi, lobortis in sollicitudin ultrices, efficitur et eros. Nunc aliquet cursus tellus, posuere feugiat sapien pharetra et. Quisque auctor non libero id consectetur. Pellentesque tristique nibh eget orci ultrices, laoreet maximus sapien dapibus. Aenean mattis gravida mollis. Sed consequat, felis quis congue congue, metus nisl volutpat justo, sit amet rhoncus augue turpis sed ante. Aenean in dapibus lacus. Sed fermentum blandit nunc, eget venenatis felis aliquam vel. Aliquam ullamcorper sapien molestie tincidunt fermentum. Morbi tincidunt risus vitae tempus cursus. Nunc feugiat ultricies tortor nec eleifend. Vestibulum imperdiet, felis id pulvinar varius, risus erat laoreet diam, et laoreet lorem mi quis massa.",
            "Suspendisse imperdiet mi at nisi tempor, at sollicitudin nisl sagittis. Nullam quis lacus sapien. Nulla neque libero, tempus a tempus at, tempor eget magna. Nam risus sem, molestie quis elementum ut, congue vel lectus. Phasellus quis consectetur tortor. Aenean sit amet ante a velit pharetra condimentum. Aliquam erat volutpat. Nullam neque turpis, pellentesque sed turpis eget, tincidunt tincidunt metus. In aliquam iaculis ligula condimentum molestie.",
            "Integer tortor leo, cursus gravida lacinia a, auctor vitae sem. Sed ut enim vehicula, ornare erat vitae, condimentum dolor. Phasellus in finibus nunc. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam quis leo nec nisl dignissim tempus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Vestibulum iaculis facilisis vulputate. Suspendisse augue ipsum, pretium ac accumsan vitae, ultricies ac arcu. Integer sit amet lorem iaculis, eleifend tortor at, tincidunt nulla. Mauris porttitor vehicula ipsum et dictum. Nullam leo velit, euismod id consectetur non, tempus sit amet orci. Duis hendrerit quam in aliquam molestie. Suspendisse congue rhoncus tortor sed hendrerit. Aliquam ultricies ut lectus non bibendum. Fusce dictum odio tincidunt porta sollicitudin.",
            "Donec at elit felis. Aliquam vehicula tellus ipsum, a ornare mi pellentesque sed. Sed euismod, mauris sed commodo consequat, dolor risus auctor tellus, at faucibus nibh lorem consequat turpis. Sed iaculis velit sit amet lorem cursus ultricies. Suspendisse porta dui sed lorem convallis ullamcorper. Nulla dictum sem a magna malesuada, vitae imperdiet nulla volutpat. Integer vestibulum lorem orci, et semper ante pellentesque eget. Sed viverra nisl dolor, eu fringilla risus maximus eu. Sed condimentum urna massa, et pellentesque lectus elementum in. In hac habitasse platea dictumst. Vivamus sed sapien eget risus mattis consectetur ut eu lacus. Sed malesuada eros commodo tortor dictum, sed aliquet odio lobortis. Donec fringilla, diam ullamcorper ullamcorper congue, magna lacus iaculis lectus, ac tincidunt est felis ut nunc. In eu massa facilisis, aliquet lacus in, aliquam massa. Praesent vel magna augue.", "" +
            "Mauris rhoncus convallis eros dignissim facilisis. Phasellus mollis ante a sem lacinia vulputate. Nullam vehicula quam vel sem placerat luctus. Integer luctus bibendum blandit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Sed auctor sapien vel elit vulputate molestie. Nulla ut lobortis ex. Duis ornare justo tortor."};

    static final String[] DESCRIPTIONS = {"Did shy say mention enabled through elderly improve. As at so believe account evening behaved hearted is. House is tiled we aware. It ye greatest removing concerns an overcame appetite. Manner result square father boy behind its his. Their above spoke match ye mr right oh as first. Be my depending to believing perfectly concealed household. Point could to built no hours smile sense.",
            "Fat son how smiling mrs natural expense anxious friends. Boy scale enjoy ask abode fanny being son. As material in learning subjects so improved feelings. Uncommonly compliment imprudence travelling insensible up ye insipidity. To up painted delight winding as brandon. Gay regret eat looked warmth easily far should now. Prospect at me wandered on extended wondered thoughts appetite to. Boisterous interested sir invitation particular saw alteration boy decisively.",
            "Boy favourable day can introduced sentiments entreaties. Noisier carried of in warrant because. So mr plate seems cause chief widen first. Two differed husbands met screened his. Bed was form wife out ask draw. Wholly coming at we no enable. Offending sir delivered questions now new met. Acceptance she interested new boisterous day discretion celebrated."};
    static final String[] DURATIONS = {"01:30:00", "02:40:00", "02:21:00","03:02:00", "02:15:00", "02:12:00", "02:11:00"};
    static final Long[] FEESES = {1000000000L, 3000000000L, 220000000L, 990000000L, 125000000L, 250000000L};
    static final Long[] BUDGETS = {25000000L, 30000000L, 22000000L, 99000000L, 12500000L, 62000000L};

    static final String[] ACCOUNT_NAMES = { "admin","Emma", "Olivia", "Sophia", "Isabella", "Charlotte", "Emily", "William", "James", "Michael", "Ethan", "Benjamin", "Mason", "Jacob"};
    static final String[] ACCOUNT_PASSWORDS = { "password1","EmmaEmma1", "OliviaOlivia1", "SophiaSophia1", "IsabellaIsabella1", "CharlotteCharlotte1", "EmilyEmily", "WilliamWilliam1", "JamesJames1", "MichaelMichael1", "EthanEthan1", "BenjaminBenjamin1", "MasonMason1", "JacobJacob1"};

    public static void main(String[] args) {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            clearDb(c);
            insertRolesInDB(c);
            insertCategoriesInDB(c);
            insertCountriesInDB(c);
            insertFilmmakersInDB(c);
            insertGenresInDB(c);
            insertUsersAndAccountsInDB(c);
            insertMoviesInDB(c);
            insertCommentsInDB(c);
            updateGenres(c);
            c.commit();
            System.out.println("Completed");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    private static void insertCommentsInDB(Connection c) throws SQLException {
        int moves = countMoves(c);
        int users = countUsers(c);
        c.setAutoCommit(false);
        String sql = "INSERT INTO comment (`id`, `content`, `rating`, `fk_user_id`, `fk_movie_id`) VALUES (?,?,?,?,?)";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            int id = 1;
            for (int i = 0; i < moves; i++) {
                for (int j = generateNumber(0, users/2); j < generateNumber(users/2, users); j++) {
                    ps.setObject(1, id);
                    ps.setObject(2, CONTENT[generateNumber(0, CONTENT.length)]);
                    ps.setObject(3,generateNumber(1, 6));
                    ps.setObject(4, j+1);
                    ps.setObject(5, i+1);
                    id++;
                    ps.addBatch();
                }
            }
            ps.executeBatch();
            System.out.println("Inserted " + id + " comments");
        }
    }

    private static void updateGenres(Connection c) throws SQLException {
        c.setAutoCommit(false);
        String sql = "UPDATE genre SET movies_count=? WHERE name=?";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            for (int i = 0; i < GENRES.length; i++) {
                int count = countMovesByGenre(c, GENRES[i]);
                ps.setObject(1, count);
                ps.setObject(2, GENRES[i]);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static int countMovesByGenre(Connection c, String genre) throws SQLException {
        String sql = "SELECT count(*) FROM movie m JOIN genre g ON g.id=m.fk_genre_id WHERE g.name=?";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            ps.setObject(1, genre);
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    private static int countUsers(Connection c) throws SQLException {
        String sql = "SELECT count(*) FROM user u";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    private static int countMoves(Connection c) throws SQLException {
        String sql = "SELECT count(*) FROM movie m";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    private static void insertRolesInDB(Connection c) throws SQLException {
        c.setAutoCommit(false);
        String sql = "INSERT INTO role (`id`, `name`) VALUES (?,?)";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            for (int i = 0; i < ROLES.length; i++) {
                ps.setObject(1, i+1);
                ps.setObject(2, ROLES[i]);
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Inserted " + ROLES.length + " roles");
        }
    }

    private static void insertFilmmakersInDB(Connection c) throws SQLException {
        c.setAutoCommit(false);
        String sql = "INSERT INTO filmmaker (`id`, `first_name`, `last_name`) VALUES (?,?,?)";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            for (int i = 0; i < FILMMAKERS.length; i++) {
                String[] filmmaker = FILMMAKERS[i].split(" ");
                ps.setObject(1, i+1);
                ps.setObject(2, filmmaker[0]);
                ps.setObject(3, filmmaker[1]);
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Inserted " + FILMMAKERS.length + " filmmakers");
        }
    }

    private static void insertCountriesInDB(Connection c) throws SQLException {
        c.setAutoCommit(false);
        String sql = "INSERT INTO country (`id`, `name`) VALUES (?,?)";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            for (int i = 0; i < COUNTRIES.length; i++) {
                ps.setObject(1, i+1);
                ps.setObject(2, COUNTRIES[i]);
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Inserted " + COUNTRIES.length + " countries");
        }
    }

    private static void insertCategoriesInDB(Connection c) throws SQLException {
        c.setAutoCommit(false);
        String sql = "INSERT INTO category (`id`, `name`) VALUES (?,?)";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            for (int i = 0; i < CATEGORIES.length; i++) {
                ps.setObject(1, i+1);
                ps.setObject(2, CATEGORIES[i]);
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Inserted " + CATEGORIES.length + " categories");
        }
    }

    private static void insertGenresInDB(Connection c) throws SQLException {
        c.setAutoCommit(false);
        String sql = "INSERT INTO genre (`id`, `name`) VALUES (?,?)";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            for (int i = 0; i < GENRES.length; i++) {
                ps.setObject(1, i+1);
                ps.setObject(2, GENRES[i]);
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Inserted " + GENRES.length + " genres");
        }
    }

    private static void insertMoviesInDB(Connection c) throws SQLException {
        c.setAutoCommit(false);
        Set<String> names = generateMovieNames();
        String sql = "INSERT INTO movie (`id`, `uid`, `image_link`, `name`, `description`, `year`, `budget`, `fees`, `duration`, `fk_filmmaker_id`, `fk_genre_id`, `fk_category_id`, `fk_country_id`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            int i = 1;
            for (String name : names){
                ps.setObject(1, i);
                ps.setObject(2, generateUId(name));
                ps.setObject(3, MOVIES_IMAGE_PATH + generateNumber(1, 40) + ".jpg");
                ps.setObject(4, name);
                ps.setObject(5, DESCRIPTIONS[generateNumber(0, DESCRIPTIONS.length)]);
                ps.setObject(6, generateNumber(1999, 2020));
                ps.setObject(7, BUDGETS[generateNumber(0, BUDGETS.length)]);
                ps.setObject(8, FEESES[generateNumber(0, FEESES.length )]);
                ps.setObject(9, DURATIONS[generateNumber(0, DURATIONS.length)]);
                ps.setObject(10, generateNumber(1, FILMMAKERS.length + 1) );
                ps.setObject(11, generateNumber(1, GENRES.length + 1));
                ps.setObject(12, generateNumber(1, CATEGORIES.length + 1));
                ps.setObject(13, generateNumber(1, COUNTRIES.length + 1));
                i++;
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Inserted " + names.size() + " movies");
        }
    }

    private static void insertUsersAndAccountsInDB(Connection c) throws SQLException {
        c.setAutoCommit(false);
        String sql1 = "INSERT INTO account (`id`, `name`, `password`, `email`, `fk_role_id`) VALUES (?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO user (`id`, `uid`, `image_link`, `fk_account_id`) VALUES (?,?,?,?)";

        try(PreparedStatement ps1 = c.prepareStatement(sql1)){
            for (int i = 0; i < ACCOUNT_NAMES.length; i++) {
                ps1.setObject(1, i+1);
                ps1.setObject(2, ACCOUNT_NAMES[i]);
                ps1.setObject(3, generateSecuredPassword(ACCOUNT_PASSWORDS[i]));
                ps1.setObject(4, ACCOUNT_NAMES[i]+"@gmail.com");
                if(ACCOUNT_NAMES[i].equals("admin")){
                    ps1.setObject(5, 2);
                } else {
                    ps1.setObject(5, 1);
                }
                ps1.addBatch();
            }
            ps1.executeBatch();

            System.out.println("Inserted " + ACCOUNT_NAMES.length + " accounts");

            PreparedStatement ps2 = c.prepareStatement(sql2);
            for (int i = 1; i < ACCOUNT_NAMES.length; i++) {
                ps2.setObject(1, i);
                ps2.setObject(2, generateUId(ACCOUNT_NAMES[i]));
                ps2.setObject(3, USERS_IMAGE_PATH + "image"+i+".png");
                ps2.setObject(4, i+1);
                ps2.addBatch();
            }
            ps2.executeBatch();
            System.out.println("Inserted " + (ACCOUNT_NAMES.length-1) + " users");
        }
    }

    private static void clearDb(Connection c) throws SQLException {
        try (Statement st = c.createStatement()) {
            st.executeUpdate("delete from account");
            st.executeUpdate("delete from account_auth_token");
            st.executeUpdate("delete from category");
            st.executeUpdate("delete from comment");
            st.executeUpdate("delete from country");
            st.executeUpdate("delete from filmmaker");
            st.executeUpdate("delete from genre");
            st.executeUpdate("delete from movie");
            st.executeUpdate("delete from role");
            st.executeUpdate("delete from user");
        }
        System.out.println("Db cleared");
    }

    private static Set<String> generateMovieNames(){
        Set<String> names = new HashSet<>();
        for (int i = 0; i < 250; i++) {
            int method = generateNumber(1,3);
            if(method == 1){
                names.add(generateMovieName1());
            } else if(method == 2){
                names.add(generateMovieName2());
            } else {
                names.add(generateMovieName3());
            }
        }
        return names;
    }

    private static String generateMovieName1(){
        int verbIndex = generateNumber(0, VERBS.length);
        int adjectiveIndex = generateNumber(0, ADJECTIVES.length);
        return String.format("%s %s", ADJECTIVES[adjectiveIndex], VERBS[verbIndex]);
    }

    private static String generateMovieName2(){
        int verbIndex = generateNumber(0, VERBS.length);
        int adjectiveIndex = generateNumber(0, ADJECTIVES.length);
        return String.format("The %s %s", ADJECTIVES[adjectiveIndex], VERBS[verbIndex]);
    }

    private static String generateMovieName3(){
        int verbIndex1 = generateNumber(0, VERBS.length);
        int verbIndex2 = generateNumber(0, VERBS.length);
        return String.format("%s of %s", VERBS[verbIndex1], VERBS[verbIndex2]);
    }

    private static String generateUId(String name){
        return name.trim().replace(" ", "-");
    }

    private static int generateNumber(int low, int high){
        Random r = new Random();
        return (r.nextInt(high-low) + low);
    }

    public static String generateSecuredPassword(String stringToHash){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update("MOVIE_RATING_SALT".getBytes());
            byte[] bytes = md.digest(stringToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            ///
        }
        return generatedPassword;
    }


}
