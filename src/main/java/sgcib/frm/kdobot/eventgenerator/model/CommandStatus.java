package sgcib.frm.kdobot.eventgenerator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CommandStatus {
    private final String jobId;

    @JsonCreator
    public CommandStatus(@JsonProperty("jobId") String jobId) {
        this.jobId = jobId;
    }

    public String getJobId() {
        return jobId;
    }

    public boolean isFinished(){
        return true;
    }

    public boolean isLate(){
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandStatus that = (CommandStatus) o;
        return Objects.equals(jobId, that.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId);
    }

}
