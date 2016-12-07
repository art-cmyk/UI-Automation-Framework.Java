/**
 * Created by ravit on 05/12/2016.
 */
public interface IDriverSession {
        void start(String url);
        void deleteAllCookies();
        void dispose();
}