package sgcib.frm.kdobot.eventgenerator.model;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class DataHolder {
    // data
    private ConcurrentMap<String, Command> commandsByName = new ConcurrentHashMap<>();
    private ConcurrentMap<String, RunningCommand> runningCommandByJobId = new ConcurrentHashMap<>();
    private ConcurrentMap<String, CommandStatus> commandStatusByJobId = new ConcurrentHashMap<>();

    // lock
    private ReadWriteLock commandsLock = new ReentrantReadWriteLock();
    private ReadWriteLock runningCommandsLock = new ReentrantReadWriteLock();
    private ReadWriteLock commandStatusLock = new ReentrantReadWriteLock();

    public void setCommandsByName(ConcurrentMap<String, Command> commandsByName) {
        commandsLock.writeLock().lock();
        this.commandsByName = commandsByName;
        commandsLock.writeLock().unlock();
    }

    public ConcurrentMap<String, Command> getCommandsByName() {
        commandsLock.readLock().lock();
        try{
            return commandsByName;
        }finally {
            commandsLock.readLock().unlock();
        }

    }

    public ConcurrentMap<String, RunningCommand> getRunningCommandByJobId() {
        runningCommandsLock.readLock().lock();
        try {
            return runningCommandByJobId;
        }finally {
            runningCommandsLock.readLock().unlock();
        }
    }

    public void setRunningCommandByJobId(ConcurrentMap<String, RunningCommand> runningCommandByJobId) {
        runningCommandsLock.writeLock().lock();
        this.runningCommandByJobId = runningCommandByJobId;
        runningCommandsLock.writeLock().unlock();
    }

    public ConcurrentMap<String, CommandStatus> getCommandStatusByJobId() {
        commandStatusLock.readLock().lock();
        try {
            return commandStatusByJobId;
        }finally {
            commandStatusLock.readLock().unlock();
        }
    }

    public void setCommandStatusByJobId(ConcurrentMap<String, CommandStatus> commandStatusByJobId) {
        commandStatusLock.writeLock().lock();
        this.commandStatusByJobId = commandStatusByJobId;
        commandStatusLock.writeLock().unlock();
    }


}
