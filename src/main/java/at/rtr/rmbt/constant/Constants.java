package at.rtr.rmbt.constant;

public interface Constants {
    Long NEWS_REQUEST_LAST_NEWS_UID = 0L;
    Long NEWS_REQUEST_SOFTWARE_VERSION_CODE = -1L;
    String UNKNOWN_DEVICE = "Unknown Device";
    int SRID = 900913;
    Integer WEIGHT = 1;
    Integer PRIORITY = 1;
    String SIGNAL_STRENGTH_DBM_TEMPLATE = "%s dBm";
    String SIGNAL_STRENGTH_RSRQ_TEMPLATE = "RSRQ: %s dB";
    String SIGNAL_STRENGTH_TIMING_ADVANCE_TEMPLATE = "TA: %s";
    String SIGNAL_STRENGTH_ACCURACY_TEMPLATE = "+/-%sm";
    String SIGNAL_STRENGTH_ALTITUDE_TEMPLATE = "%s m";
    String SIGNAL_STRENGTH_BEARING_TEMPLATE = "%s\u00B0";
    String SIGNAL_STRENGTH_SPEED_TEMPLATE = "%s km/h";
    String SIGNAL_STRENGTH_DELIMITER = ", ";
    Integer MIN_SPEED = 0;
    Integer MAX_SPEED = 10000000;
    Long MIN_PING = 0L;
    Long MAX_PING = 60000000000L;
}
