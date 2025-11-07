package com.base.scheduling;

/**
 * 잡 스케줄러의 실행 상태를 나타낸다.
 * 
 * @author 조용상
 * @version 1.0
 * 
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.01.27 조용상 최초 작성
 * </pre>
 */
public enum JobState {
    /**
     * 정상
     */
    NORMAL("0"),

    /**
     * 비정상
     */
    ABNORMAL("1"),

    /**
     * 수행중
     */
    UNDERWAY("2");

    private String value;

    JobState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Text value 값에 해당하는 잡상태(JobState)를 반환한다.
     * <p>e.g. 0: NORMAL, 1: ABNORMAL, 2: UNDERWAY</p>
     * @param value JobState의 String 값
     * @return String 값에 해당하는 JobStat
     */
    public static JobState textValueOf(String value) {
        switch(value) {
            case "0": return NORMAL;
            case "1": return ABNORMAL;
            case "2": return UNDERWAY;
            default : throw new AssertionError("Unknown value : " + value);
        }
    }

    /**
     * 현재 잡상태(JobState)에 해당하는 Text(Label) 값을 반환한다.
     * <p>e.g. NORMAL: 정상, ABNORMAL: 비정상, UNDERWAY: 수행중</p>
     * @return 현재 잡상태의 Text(Label)
     */
    public String getLabel() {
        switch (this) {
            case NORMAL: return "정상";
            case ABNORMAL: return "비정상";
            case UNDERWAY: return "수행중";
            default: return null;
        }
    }
}