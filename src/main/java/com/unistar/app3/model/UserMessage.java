package com.unistar.app3.model; 

public class UserMessage {

    private String name;

    private long epochSecond;

    private String epochInUTC;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEpochSecond() {
        return epochSecond;
    }

    public void setEpochSecond(long epochSecond) {
        this.epochSecond = epochSecond;
    }

    public String getEpochInUTC() {
        return epochInUTC;
    }

    public void setEpochInUTC(String epochInUTC) {
        this.epochInUTC = epochInUTC;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserMessage [name=").append(name).append(", epochSecond=").append(epochSecond)
			.append(", epochInUTC=").append(epochInUTC).append("]");
		return builder.toString();
	}
}