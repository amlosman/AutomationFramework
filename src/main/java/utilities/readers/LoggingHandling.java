package utilities.readers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LoggingHandling {
    public static Logger logger = Logger.getLogger(LoggingHandling.class.getName());
    public static String logFileName;
    private static FileHandler fh =null;
    static Logger globalLogger= Logger.getLogger("global");
    static {

        CreateNewLoger();
    }
    public static void CreateNewLoger()
    {
        Handler[]handlers ;
        handlers = globalLogger.getHandlers();
        for (Handler handler :handlers)
        {
            globalLogger.removeHandler(handler);
        }
        handlers = logger.getHandlers();
        for (Handler handler :handlers)
        {
            logger.removeHandler(handler);
        }
        SimpleDateFormat format = new SimpleDateFormat("d-M-y HH-mm-ss");
        try {
            String filename = ("Logs/"+format.format(Calendar.getInstance().getTime())+".log");
            fh = new FileHandler(filename);
            LoggingHandling.logFileName = filename;

        } catch (IOException e) {
            e.printStackTrace();
        }
        LoggingHandling.logger.addHandler(fh);
        LoggingHandling.logger.addHandler(new ConsoleHandler());
        LoggingHandling.logger.setUseParentHandlers(false);
        //BriefLogFormatter.init();
        //logger.info("");

    }
    public static void logError(Exception e)
    {
        //  logger.severe(e.getMessage())  to print error message only;
        //or  to print StackTrace
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        logger.severe("Error"+sw.toString());


    }

}
