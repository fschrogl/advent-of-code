package at.schrogl.aoc.common;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class SolutionData {

    private final Data data;
    private final LocalDateTime createDateTime;
    private Duration computationDuration;
    private Long expectedResult;
    private Long actualResult;
    private String actualResultDetails;

    private SolutionData(URL resource) {
        data = new Data(resource);
        createDateTime = LocalDateTime.now();
    }

    public static SolutionData from(URL resource, Long expectedResult) {
        SolutionData solutionData = new SolutionData(resource);
        solutionData.setExpectedResult(expectedResult);
        return solutionData;
    }

    public Long getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(Long expectedResult) {
        this.expectedResult = expectedResult;
    }

    public void setActualResult(Long actualResult) {
        computationDuration = Duration.between(createDateTime, LocalDateTime.now());
        this.actualResult = actualResult;
    }

    public void setActualResultDetails(Supplier<String> details) {
        this.actualResultDetails = details.get();
    }

    public Long getActualResult() {
        return actualResult;
    }

    public String getActualResultDetails() {
        return (actualResultDetails == null || actualResultDetails.isBlank()) ? "None" : actualResultDetails;
    }

    public Duration getComputationDuration() {
        return computationDuration;
    }

    public Optional<Boolean> isValid() {
        return (expectedResult == null) ? Optional.empty() : Optional.of(expectedResult.equals(actualResult));
    }

    public SolutionStatus getSolutionStatus() {
        return isValid().map(b -> b ? SolutionStatus.OK : SolutionStatus.NOK).orElse(SolutionStatus.UNKNOWN);
    }

    public Data getInput() {
        return data;
    }

    public static class Data {

        private final URL resource;

        public Data(URL resource) {
            this.resource = resource;
        }

        public String getFilename() {
            return resource.getFile().substring(resource.getFile().lastIndexOf('/') + 1);
        }

        public List<String> asLines() {
            try {
                return Collections.unmodifiableList(Files.readAllLines(Path.of(resource.getPath())));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
