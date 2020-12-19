package at.schrogl.aoc.common;

/*-
 * #%L
 * Advent-Of-Code
 * %%
 * Copyright (C) 2020 Fritz Schrogl
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class SolutionData {

    private final Data data;
    private final LocalDateTime createDateTime;
    private Duration computationDuration;
    private Long expectedResult;
    private Long actualResult;
    private Supplier<String> actualResultDetails;

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
        this.actualResultDetails = details;
    }

    public Long getActualResult() {
        return actualResult;
    }

    public String getActualResultDetails() {
        if (actualResultDetails != null) {
            String resultDetails = actualResultDetails.get();
            return (resultDetails == null || resultDetails.isBlank()) ? "None" : resultDetails;
        }
        return "None";
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
                return Files.readAllLines(Path.of(resource.getPath()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        public Map<Integer, String> asJoinedGroupLines() {
            try (LineNumberReader fileReader = new LineNumberReader(new InputStreamReader(asInputStream()))) {
                Map<Integer, String> groupLines = new HashMap<>();
                Integer lineNumber = null;
                StringBuilder tmpLine = new StringBuilder();
                String line = null;
                while ((line = fileReader.readLine()) != null) {
                    if (line.isBlank() && tmpLine.length() > 0) {
                        groupLines.put(lineNumber, tmpLine.toString().trim());
                        tmpLine = new StringBuilder();
                        lineNumber = null;
                    } else {
                        lineNumber = fileReader.getLineNumber();
                        tmpLine.append(line.trim()).append(" ");
                    }
                }
                if (!tmpLine.toString().isBlank()) {
                    groupLines.put(lineNumber, tmpLine.toString().trim());
                }
                return groupLines;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public InputStream asInputStream() {
            try {
                File dataFile = new File(resource.getFile());
                return new BufferedInputStream(new FileInputStream(dataFile), (int) dataFile.length());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
