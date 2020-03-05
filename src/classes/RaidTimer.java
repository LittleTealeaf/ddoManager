package classes;

import java.time.ZonedDateTime;

public class RaidTimer {

    String raid;
    //NOTE: maybe another date-time class would work better? (using system default, count-down)
    ZonedDateTime time;

    public RaidTimer() {
        this.raid = "";
        this.time = ZonedDateTime.now();
    }

    public RaidTimer(String raid, ZonedDateTime time) {
        this.raid = raid;
        this.time = time;
    }

    public String getRaid() {
        return raid;
    }

    public void setRaid(String raid) {
        this.raid = raid;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public boolean equals(RaidTimer other) {
        return this.time.equals(other.time) && this.raid.equals(other.raid);
    }

    @Override
    public String toString() {
        return raid + " can be replayed at " + time.toString();
    }
}
