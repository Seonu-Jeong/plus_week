package com.example.demo.entity;

import lombok.Getter;

@Getter
public enum ReservationStatus {

	// PENDING, APPROVED, CANCELED, EXPIRED
	PENDING("PENDING"),
	APPROVED("APPROVED"),
	CANCELED("CANCELED"),
	EXPIRED("EXPIRED");

	private final String status;

	ReservationStatus(String status) {
		this.status = status;
	}

	public static ReservationStatus of(String status) {
		for (ReservationStatus st : values()) {
			if (st.getStatus().equals(status)) {
				return st;
			}
		}

		throw new IllegalArgumentException("해당하는 이름의 상태을 찾을 수 없습니다: " + status);
	}
}
