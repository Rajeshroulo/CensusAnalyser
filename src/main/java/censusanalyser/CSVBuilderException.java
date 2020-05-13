package censusanalyser;

public class CSVBuilderException extends Exception {
    enum ExceptionType {
        CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE
    }

    ExceptionType type;

    public CSVBuilderException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CSVBuilderException(String message, Throwable cause, ExceptionType type) {
        super(message, cause);
        this.type = type;
    }

}
