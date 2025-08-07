package frc.robot.Subsystem.IntakeTrain;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StructArrayPublisher;
import edu.wpi.first.networktables.StructPublisher;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;


public class MALog {

    private static final NetworkTableInstance nt = NetworkTableInstance.getDefault();
    private static final NetworkTable malogTable = NetworkTableInstance.getDefault().getTable("/MALog");
    private static final Map<String, NetworkTableEntry> entries = new HashMap<>();
    private static final Map<String, StructPublisher<Pose2d>> pose2dPublishers = new HashMap<>();
    private static final Map<String, StructPublisher<Pose3d>> pose3dPublishers = new HashMap<>();
    private static final Map<String, StructArrayPublisher<SwerveModuleState>> swerveModuleStatePublishers = new HashMap<>();
    private static final Map<String, StructArrayPublisher<SwerveModulePosition>> swerveModulePositionPublishers = new HashMap<>();
    private static final Map<String, StructArrayPublisher<Pose3d>> pose3dPublishersArry = new HashMap<>();
    private static final Map<String, StructPublisher<ChassisSpeeds>> chassisSpeedsPublishersArry = new HashMap<>();
    private static final String ID_FILE_PATH = "/home/lvuser/malog/lastLogID.txt";
    private static String sessionID = "0000";
    private static final NetworkTableEntry flagEntry = malogTable.getEntry("/System/Flag");
    private static final NetworkTableEntry statusEntry = malogTable.getEntry("/System/Status");

    public enum MALogMode {
        AUTO,
        TELEOP,
        TEST
    }

    public enum LogLevel { //TODO change to private
        INFO, WARN, ERROR, DEBUG
    }

    public static void startLog(MALogMode mode) {

        if (!DriverStation.isFMSAttached()) {
            sessionID = loadNextID();
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(LocalDateTime.now());
            String logName = String.format("MALog_%s_%s_%s", mode.name(), sessionID, timeStamp);
            malogTable.getEntry("LogName").setString(logName);
            malogTable.getEntry("LogID").setString(sessionID);
            DataLogManager.start("", logName);
        } else {
            DataLogManager.start();
        }
        DataLogManager.logNetworkTables(true);// TOOD: Add compition mode in robot continer
    }

    public static void stopLog() {
        DataLogManager.stop();
    }

    public static void log(String key, double value) {
        log(key, () -> value);
    }

    public static void log(String key, boolean value) {
        log(key, () -> value);
    }

    public static void log(String key, String value) {
        getEntry(key).setString(value);
    }

    public static void log(String key, BooleanSupplier supplier) {
        getEntry(key).setBoolean(supplier.getAsBoolean());
    }

    public static void log(String key, DoubleSupplier supplier) {
        getEntry(key).setDouble(supplier.getAsDouble());
    }

    public static void log(String key, IntSupplier supplier) {
        getEntry(key).setInteger(supplier.getAsInt());
    }

    public static void log(String key, int value) {
        getEntry(key).setInteger(value);
    }

    public static NetworkTableEntry get(String key) {
        return getEntry(key); //TODO its should retune a value /change the name to getEntry
    }

    public static void logSwerveModuleStates(String key, SwerveModuleState[] states) {
        if (states == null) {
            states = new SwerveModuleState[] { new SwerveModuleState(100000, Rotation2d.kZero) };
            //TODO need to add her publisher.set()
            return;
        }

        StructArrayPublisher<SwerveModuleState> publisher = swerveModuleStatePublishers.computeIfAbsent(key,
                k -> nt
                        .getStructArrayTopic("MALog/" + k, SwerveModuleState.struct)
                        .publish());

        publisher.set(states);
    }

    public static void logSwerveModulePositions(String key, SwerveModulePosition[] states) {
        if (states == null) {
            states = new SwerveModulePosition[] { new SwerveModulePosition(100000, Rotation2d.kZero) };
                        //TODO need to add her publisher.set()

            return;
        }

        StructArrayPublisher<SwerveModulePosition> publisher = swerveModulePositionPublishers.computeIfAbsent(key,
                k -> nt
                        .getStructArrayTopic("MALog/" + k, SwerveModulePosition.struct)
                        .publish());

        publisher.set(states);
    }

    public static void log(String key, Pose3d[] poses) {
        if (poses == null) {
            poses = new Pose3d[] { new Pose3d(0, 0, 1000, new Rotation3d()) };
                       //TODO need to add her publisher.set()

            return;
        }

        StructArrayPublisher<Pose3d> publisher = pose3dPublishersArry.computeIfAbsent(
                key,
                k -> nt.getStructArrayTopic("MALog/" + k, Pose3d.struct).publish());

        publisher.set(poses);
    }

    public static void log(String key, Pose2d pose) {
        if (pose == null) {
            pose = new Pose2d(100, 100, Rotation2d.kZero);
                      //TODO need to add her publisher.set()

            return;
        }

        StructPublisher<Pose2d> publisher = pose2dPublishers.computeIfAbsent(
                key,
                k -> nt.getStructTopic("MALog/" + k, Pose2d.struct).publish());

        publisher.set(pose);
    }

    public static void log(String key, Pose3d pose) {
        if (pose == null) {
            pose = new Pose3d(0, 0, 1000, new Rotation3d());
                       //TODO need to add her publisher.set()

            return;
        }

        StructPublisher<Pose3d> publisher = pose3dPublishers.computeIfAbsent(
                key,
                k -> nt.getStructTopic("MALog/" + k, Pose3d.struct).publish());

        publisher.set(pose);
    }

    public static void log(String key, ChassisSpeeds chassisSpeeds) {
        if (chassisSpeeds == null) {
            chassisSpeeds = new ChassisSpeeds(0, 0, 1000);
                        //TODO need to add her publisher.set()

            return;
        }

        StructPublisher<ChassisSpeeds> publisher = chassisSpeedsPublishersArry.computeIfAbsent(
                key,
                k -> nt.getStructTopic("MALog/" + k, ChassisSpeeds.struct).publish());

        publisher.set(chassisSpeeds);
    }

    private static NetworkTableEntry getEntry(String key) {
        return entries.computeIfAbsent(key, k -> malogTable.getEntry(k));
    }

    public static void flag(Flag falg) {
        flagEntry.setString(falg.level.name() + " - " + falg.label);
    }

    public static void addStatus(String status) {
        statusEntry.setString(status); //TODO need to expande. what the point? if we have flag what the dif?
    }

    public static void resetID() {
        if (!RobotBase.isReal())
            return;

        try {
            File dir = new File("/home/lvuser/malog");
            if (!dir.exists())
                dir.mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(ID_FILE_PATH));
            writer.write("0");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String loadNextID() {
        if (!RobotBase.isReal()) {
            return "0000";
        }

        try {
            File dir = new File("/home/lvuser/malog");
            if (!dir.exists())
                dir.mkdirs();

            File file = new File(ID_FILE_PATH);
            int lastID = 0;
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                lastID = Integer.parseInt(reader.readLine().trim());
                reader.close();
            }

            int newID = (lastID + 1) % 1000;
            if (newID == 0)
                newID = 1;

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(Integer.toString(newID));
            writer.close();

            return String.format("%04d", newID);
        } catch (IOException e) {
            e.printStackTrace();
            return "0000";
        }

    }

    public static class Flag { //TODO need to expentd we should give it to kaplan 
        public static final Flag DRIVER_FLAG = new Flag(LogLevel.DEBUG, "Driver Flag");
        public static final Flag SOFTWARE_FLAG = new Flag(LogLevel.DEBUG, "Software Flag");

        public final LogLevel level; //TODO change to private
        public final String label; //TODO change to private

        

        public Flag(LogLevel level, String label) { //TODO change to private? we need to think were we write this
            this.level = level;
            this.label = label;
        }

        

    }
}
