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
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_PATH, WRONG_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongTypeFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_TEXT_FILE_PATH, STATE_CODE_TEXT_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_WRONG_DELIMITER_CSV_FILE_PATH, STATE_CODE_WRONG_DELIMITER_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_WRONG_HEADER_CSV_FILE_PATH, STATE_CODE_WRONG_HEADER_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

        @Test
        public void givenIndiaCensusData_whenSortedByState_shouldReturnFirstStateName () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToStateName(CensusAnalyser.Country.INDIA);
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
            } catch (CensusAnalyserException e) {
            }

        }

        @Test
        public void givenIndiaCensusData_whenSortedByState_shouldReturnLastStateName () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToStateName(CensusAnalyser.Country.INDIA);
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("West Bengal", censusCSV[28].state);
            } catch (CensusAnalyserException e) {
            }
        }

    @Test
    public void givenIndiaCensusData_whenSortedByStateCode_shouldReturnFirstStateCode() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToStateCode(CensusAnalyser.Country.INDIA);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("AP", censusCSV[0].stateCode);
        } catch (CensusAnalyserException e) {}
    }

    @Test
    public void givenIndiaCensusData_whenSortedByStateCode_shouldReturnLastStateCode() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToStateCode(CensusAnalyser.Country.INDIA);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("WB", censusCSV[28].stateCode);
        } catch (CensusAnalyserException e) {}
    }

    @Test
        public void givenIndiaCensusData_whenSortedByPopulation_shouldReturnHighestPopulationStateName () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation(CensusAnalyser.Country.INDIA);
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
            } catch (CensusAnalyserException e) { }
        }

        @Test
        public void givenIndiaCensusData_whenSortedByPopulation_shouldReturnLeastPopulationStateName () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation(CensusAnalyser.Country.INDIA);
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Sikkim", censusCSV[28].state);
            } catch (CensusAnalyserException e) {
            }
        }

        @Test
        public void givenIndiaCensusData_whenSortedByPopulationDensity_shouldReturnHighestPopulationDensityStateName ()
        {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation(CensusAnalyser.Country.INDIA);
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Bihar", censusCSV[2].state);
            } catch (CensusAnalyserException e) {
            }
        }

        @Test
        public void givenIndiaCensusData_whenSortedByPopulationDensity_shouldReturnLeastPopulationDensityStateName () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation(CensusAnalyser.Country.INDIA);
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Arunachal Pradesh", censusCSV[26].state);
            } catch (CensusAnalyserException e) {
            }
        }

        @Test
        public void givenIndiaCensusData_whenSortedByArea_shouldReturnLargestAreaStateName () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation(CensusAnalyser.Country.INDIA);
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Rajasthan", censusCSV[6].state);
            } catch (CensusAnalyserException e) {
            }
        }

        @Test
        public void givenIndiaCensusData_whenSortedByArea_shouldReturnSmallestAreaStateName () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
                censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
                String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation(CensusAnalyser.Country.INDIA);
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
                Assert.assertEquals("Goa", censusCSV[25].state);
            } catch (CensusAnalyserException e) {
            }
        }

    @Test
    public void givenIndiaCensusData_whenSortedByArea_shouldReturnStateNameOf7thLargestArea() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToArea(CensusAnalyser.Country.INDIA);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[9].state);
        } catch (CensusAnalyserException e) {}
    }

        @Test
        public void givenUSCensusCSVFile_shouldReturnsTotalNumberOfRecords () {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
                int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
                Assert.assertEquals(51, numOfRecords);
            } catch (CensusAnalyserException e) { }
        }

    @Test
    public void givenUSCensusData_whenSortedByPopulation_shouldReturnHighestPopulationStateName() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation(CensusAnalyser.Country.US);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("California", censusCSV[0].state);
        } catch (CensusAnalyserException e) {}
    }

    @Test
    public void givenUSCensusData_whenSortedByPopulation_shouldReturnLeastPopulationStateName() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulation(CensusAnalyser.Country.US);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Wyoming", censusCSV[50].state);
        } catch (CensusAnalyserException e) {}
    }

    @Test
    public void givenUSCensusData_whenSortedByPopulationDensity_shouldReturnHighestPopulationDensityStateName() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulationDensity(CensusAnalyser.Country.US);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("District of Columbia", censusCSV[0].state);
        } catch (CensusAnalyserException e) {}
    }

    @Test
    public void givenUSCensusData_whenSortedByPopulationDensity_shouldReturnLeastPopulationDensityStateName() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToPopulationDensity(CensusAnalyser.Country.US);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Alaska", censusCSV[50].state);
        } catch (CensusAnalyserException e) {}
    }

    @Test
    public void givenUSCensusData_whenSortedByArea_shouldReturnStateNameWithLargestArea() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToArea(CensusAnalyser.Country.US);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Alaska", censusCSV[0].state);
        } catch (CensusAnalyserException e) {}
    }

    @Test
    public void givenUSCensusData_whenSortedByArea_shouldReturnStateNameWithSmallestArea() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToArea(CensusAnalyser.Country.US);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("District of Columbia", censusCSV[50].state);
        } catch (CensusAnalyserException e) {}
    }

    @Test
    public void givenUSCensusData_whenSortedByStateName_shouldReturnFirstStateName() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToStateName(CensusAnalyser.Country.US);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Alabama", censusCSV[0].state);
        } catch (CensusAnalyserException e) {}
    }

    @Test
    public void givenUSCensusData_whenSortedByStateName_shouldReturnLastStateName() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToStateName(CensusAnalyser.Country.US);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Wyoming", censusCSV[50].state);
        } catch (CensusAnalyserException e) {}
    }

    @Test
    public void givenUSCensusData_whenSortedByStateId_shouldReturnFirstStateId() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToStateCode(CensusAnalyser.Country.US);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("AK", censusCSV[0].stateId);
        } catch (CensusAnalyserException e) {}
    }

    @Test
    public void givenUSCensusData_whenSortedByStateId_shouldReturnLastStateId() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusDataAccordingToStateCode(CensusAnalyser.Country.US);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("WY", censusCSV[50].stateId);
        } catch (CensusAnalyserException e) {}
    }

}

