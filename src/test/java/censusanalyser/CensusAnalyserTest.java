package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIA_CENSUS_TEXT_FILE_PATH = "./src/test/resources/IndiaStateCensusData.txt";
    private static final String INDIA_CENSUS_WRONG_DELIMITER_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusDataWrongDelimiter.csv";
    private static final String INDIA_CENSUS_WRONG_HEADER_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusDataWrongHeader.csv";
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String STATE_CODE_TEXT_FILE_PATH = "./src/test/resources/IndiaStateCode.txt";
    private static final String STATE_CODE_WRONG_DELIMITER_CSV_FILE_PATH = "./src/test/resources/IndiaStateCodeWrongDelimiter.csv";
    private static final String STATE_CODE_WRONG_HEADER_CSV_FILE_PATH = "./src/test/resources/IndiaStateCodeWrongHeader.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";


    @Test
    public void givenIndianCensusCSVFile_shouldReturnsTotalNumberOfCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_PATH, WRONG_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongTypeFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_TEXT_FILE_PATH, STATE_CODE_TEXT_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_WRONG_DELIMITER_CSV_FILE_PATH, STATE_CODE_WRONG_DELIMITER_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_WRONG_HEADER_CSV_FILE_PATH, STATE_CODE_WRONG_HEADER_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

        @Test
        public void givenIndiaCensusData_whenSortedByState_shouldReturnSortedResult () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
            } catch (CensusAnalyserException e) {
            }

        }

        @Test
        public void givenIndiaCensusData_whenSortedByState_shouldReturnLastStateName () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("West Bengal", censusCSV[28].state);
            } catch (CensusAnalyserException e) {
            }
        }

        @Test
        public void givenIndiaCensusData_whenSortedByPopulation_shouldReturnHighestPopulationStateName () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation();
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
            } catch (CensusAnalyserException e) {
            }
        }

        @Test
        public void givenIndiaCensusData_whenSortedByPopulation_shouldReturnLeastPopulationStateName () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation();
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Sikkim", censusCSV[28].state);
            } catch (CensusAnalyserException e) {
            }
        }

        @Test
        public void givenIndiaCensusData_whenSortedByPopulationDensity_shouldReturnHighestPopulationDensityStateName ()
        {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation();
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Bihar", censusCSV[2].state);
            } catch (CensusAnalyserException e) {
            }
        }

        @Test
        public void givenIndiaCensusData_whenSortedByPopulationDensity_shouldReturnLeastPopulationDensityStateName () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation();
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Arunachal Pradesh", censusCSV[26].state);
            } catch (CensusAnalyserException e) {
            }
        }

        @Test
        public void givenIndiaCensusData_whenSortedByArea_shouldReturnLargestAreaStateName () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation();
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Rajasthan", censusCSV[6].state);
            } catch (CensusAnalyserException e) {
            }
        }

        @Test
        public void givenIndiaCensusData_whenSortedByArea_shouldReturnSmallestAreaStateName () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation();
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Goa", censusCSV[25].state);
            } catch (CensusAnalyserException e) {
            }
        }

        @Test
        public void givenUSCensusCSVFile_shouldReturnsTotalNumberOfRecords () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
                Assert.assertEquals(51, numOfRecords);
            } catch (CensusAnalyserException e) {
            }
        }
    }

