package pl.jaca.jbo.report;

/**
 * @author Jaca777
 *         Created 2016-02-24 at 22
 */
public abstract class TaskReport implements Reportable {
    public static final String TAG = "tsk";

    @Override
    public String getTag() {
        return TAG;
    }

    public abstract String getTaskName();

    public abstract String getTarget();
}
