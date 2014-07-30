package cc.wdcy.infrastructure;

import java.util.Date;

/**
 * @author Shengzhao Li
 */
public abstract class DateUtils {


    /**
     * Private constructor
     */
    private DateUtils() {
    }

    public static Date now() {
        return new Date();
    }

}