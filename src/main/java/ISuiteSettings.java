/**
 * Created by ravit on 05/12/2016.
 */
public interface ISuiteSettings {
    /// <summary>
    /// Gets the launch page url based on the id specified in <paramref name="targetPage"/>
    /// </summary>
    /// <param name="targetPage"></param>
    /// <returns></returns>
    String GetLaunchPage(int targetPage);

    /// <summary>
    /// Gets the launch page url based on the id specified in <paramref name="targetPage"/> using the logic specified in <paramref name="launchPageHandler"/>
    /// </summary>
    /// <param name="targetPage"></param>
    /// <param name="launchPageHandler"></param>
    /// <returns></returns>
    String GetLaunchPage(int targetPage, ILaunchPageHandler launchPageHandler);
}
