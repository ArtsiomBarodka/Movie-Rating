package epam.my.project.dao.impl;

import epam.my.project.dao.CountryDAO;
import epam.my.project.db.handler.select.ResultHandler;
import epam.my.project.db.handler.select.ResultHandlerFactory;
import epam.my.project.db.pool.impl.ConnectionPoolImpl;
import epam.my.project.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {
    private static final ResultHandler<List<Country>> COUNTRY_RESULT =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.COUNTRY_RESULT_HANDLER);

    @Override
    public List<Country> listAllCountries() {
        List<Country> result = Collections.emptyList();
        String sql = "SELECT c.* FROM country c ORDER BY id";

        try(Connection connection = ConnectionPoolImpl.CONNECTION_POOL_INSTANCE.getConnection();
                PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            result = COUNTRY_RESULT.handle(rs);
        } catch (SQLException e){

        }
        return result;
    }
}
set @is_update_rating = IF(MOD(@total_votes,2) > 0, false, true);
        if @is_update_rating then

        update `user` set `user`.`rating` = `user`.`rating` + 1
        where (select `comment`.`fk_user_id` from `comment`) = `user`.`id`
        and (select NEW.`fk_movie_id` from `comment`)  = (select `movie`.`id` from `movie`)
        and (select `comment`.`rating` from `comment`) between @current_movie_rating + 1 and @current_movie_rating - 1;

        update `user` set `user`.`rating` = `user`.`rating` - 1
        where (select `comment`.`fk_user_id` from `comment`) = `user`.`id`
        and (select NEW.`fk_movie_id` from `comment`)  = (select `movie`.`id` from `movie`)
        and ABS(@current_movie_rating - (select `comment`.`rating` from `comment`)) > 2;
        end if;